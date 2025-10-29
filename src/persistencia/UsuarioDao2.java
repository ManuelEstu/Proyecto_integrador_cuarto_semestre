/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author CASA
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.DatosUsuario2;
import persistencia.ConexionOracle; // Asumo la ruta de tu clase de conexión

public class UsuarioDao2 {

    /**
     * Busca usuarios según el tipo y opcionalmente el número de documento.
     * @param tipoTabla El nombre de la tabla de usuario (ej: "PRODUCTOR").
     * @param documento El número de documento a buscar (puede ser null o vacío).
     * @return Una lista de objetos Usuario.
     */
    public List<DatosUsuario2> buscarUsuarios(String tipoTabla, String documento) {
        List<DatosUsuario2> listaUsuarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Mapeo del tipo de usuario en el JComboBox a la tabla en la base de datos
        String tablaDB;
        boolean tieneInfoAdicional = false;

        switch (tipoTabla.toUpperCase().trim()) {
            case "FUNCIONARIO ICA":
                tablaDB = "FUNCIONARIO_ICA";
                break;
            case "PRODUCTOR":
                tablaDB = "PRODUCTOR";
                break;
            case "TÉCNICO": // El técnico tiene la columna Nro_tarjeta_profesional
                tablaDB = "TECNICO";
                tieneInfoAdicional = true;
                break;
            case "PROPIETARIO":
                tablaDB = "PROPIETARIO";
                break;
            default:
                return listaUsuarios; // Tipo no válido
        }

        // Construcción dinámica del query
        String sql = "SELECT DOCUMENTO, NOMBRE_USUARIO, CLAVE, NOMBRE, APELLIDO, TELEFONO, EMAIL";
        if (tieneInfoAdicional) {
            sql += ", TARGETA_PROFECIONAL";
        }
        sql += " FROM " + tablaDB;
        
        // Si se proporciona un documento, se añade la cláusula WHERE
        boolean buscarPorDocumento = (documento != null && !documento.trim().isEmpty());
        if (buscarPorDocumento) {
            sql += " WHERE DOCUMENTO = ?";
        }

        try {
            conn = ConexionOracle.getConnection(); // Asumo este método en tu clase Conexion
            ps = conn.prepareStatement(sql);

            // Asignar el parámetro si se busca por documento
            if (buscarPorDocumento) {
                ps.setString(1, documento.trim());
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                DatosUsuario2 user = new DatosUsuario2();
                user.setTipo(tipoTabla);
                user.setNroDocumento(rs.getString("DOCUMENTO"));
                user.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
                user.setClave(rs.getString("CLAVE")); // Nota: En sistemas reales, ¡NO expongas la clave!
                user.setNombre(rs.getString("NOMBRE"));
                user.setApellido(rs.getString("APELLIDO"));
                user.setTelefono(rs.getString("TELEFONO"));
                user.setEmail(rs.getString("EMAIL"));
                
                // Si es un TÉCNICO, se recupera el campo adicional
                if (tieneInfoAdicional) {
                    user.setInfoAdicional(rs.getString("TARGETA_PROFECIONAL"));
                } else {
                    user.setInfoAdicional(""); // Asegurar que sea vacío para otros tipos
                }

                listaUsuarios.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones adecuado en producción
        } finally {
            // Cierre de recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return listaUsuarios;
    }
}
