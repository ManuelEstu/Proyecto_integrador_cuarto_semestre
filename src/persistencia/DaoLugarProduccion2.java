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
import logica.DatosLugar2;
import persistencia.ConexionOracle;

public class DaoLugarProduccion2 {
    
    public List<DatosLugar2> buscarLugares(String documento, String nombre) {
        List<DatosLugar2> listaLugares = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Mapeo del tipo de usuario en el JComboBox a la tabla en la base de datos
        String tablaDB = "LUGAR_PRODUCCION";

        // Construcción dinámica del query
        String sql = "SELECT NUMERO_REGISTRO_ICA, NUMERO_PREDIAL, NOMBRE, NOMBRE_EMPRESA, TELEFONO_EMPRESA, DEPARTAMENTO, MUNICIPIO, VEREDA, DOCUMENTO_PRODUCTOR";
        sql += " FROM " + tablaDB;
        sql += " WHERE DOCUMENTO_PRODUCTOR =  " + documento;
        
        // Si se proporciona un documento, se añade la cláusula WHERE
        boolean buscarPorNombre = (nombre != null && !nombre.trim().isEmpty());
        if (buscarPorNombre) {
            sql += " AND Nombre = ?";
        }

        try {
            conn = ConexionOracle.getConnection(); // Asumo este método en tu clase Conexion
            ps = conn.prepareStatement(sql);

            // Asignar el parámetro si se busca por documento
            if (buscarPorNombre) {
                ps.setString(1, nombre.trim());
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                DatosLugar2 lugar = new DatosLugar2();
                lugar.setNumero_registro_ICA(rs.getString("NUMERO_REGISTRO_ICA"));
                lugar.setNumero_predial(rs.getString("NUMERO_PREDIAL"));
                lugar.setNombre(rs.getString("NOMBRE")); // Nota: En sistemas reales, ¡NO expongas la clave!
                lugar.setNombre_empresa(rs.getString("NOMBRE_EMPRESA"));
                lugar.setTelefono_empresa(rs.getString("TELEFONO_EMPRESA"));
                lugar.setDepartamento(rs.getString("DEPARTAMENTO"));
                lugar.setMunicipio(rs.getString("MUNICIPIO"));
                lugar.setVereda(rs.getString("VEREDA"));
                lugar.setDocumento_productor(rs.getString("DOCUMENTO_PRODUCTOR"));

                listaLugares.add(lugar);
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

        return listaLugares;
    }
    
}
