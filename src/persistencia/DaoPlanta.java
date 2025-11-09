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
import persistencia.ConexionOracle; // Ajusta este paquete si es necesario
import logica.DatosPlanta;

public class DaoPlanta {

    public List<DatosPlanta> obtenerTodasLasPlantas(String nombre) {
        List<DatosPlanta> listaPlantas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        // La consulta trae los campos necesarios: ID y Nombre Común.
        String sql = "SELECT ID_PLANTA, NOMBRE_COMUN, NOMBRE_CIENTIFICO, CICLO_DE_CULTIVO, NOMBRE_DE_VARIEDAD, DESCRIPCION FROM PLANTA";
        
        // Si se proporciona un documento, se añade la cláusula WHERE
        boolean buscarPorNombre = (nombre != null && !nombre.trim().isEmpty());
        if (buscarPorNombre) {
            sql += " WHERE NOMBRE_COMUN = ?";
        }
        
        try {
            conn = ConexionOracle.getConnection();
            ps = conn.prepareStatement(sql);
            // Asignar el parámetro si se busca por documento
            if (buscarPorNombre) {
                ps.setString(1, nombre.trim());
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_PLANTA");
                String nombreComun = rs.getString("NOMBRE_COMUN");
                String nombreCientifico = rs.getString("NOMBRE_CIENTIFICO");
                String ciclo = rs.getString("CICLO_DE_CULTIVO");
                String nombreVariedad = rs.getString("NOMBRE_DE_VARIEDAD");
                String descripcion = rs.getString("DESCRIPCION");
                
                // Crea un objeto Planta con los datos de la DB
                DatosPlanta planta = new DatosPlanta(id, nombreComun, nombreCientifico, ciclo, nombreVariedad, descripcion);
                listaPlantas.add(planta);
            }

        } catch (SQLException e) {
            System.err.println("💥 Error al obtener las plantas de la BD: " + e.getMessage());
        } finally {
            // Asegurarse de cerrar los recursos
            ConexionOracle.cerrarConexion(conn);
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException ex) {
                System.err.println("Error cerrando recursos: " + ex.getMessage());
            }
        }

        return listaPlantas;
    }
}
