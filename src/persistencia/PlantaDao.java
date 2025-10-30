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
import logica.Planta;

public class PlantaDao {

    public List<Planta> obtenerTodasLasPlantas() {
        List<Planta> listaPlantas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // La consulta trae los campos necesarios: ID y Nombre Común.
        String sql = "SELECT ID_PLANTA, NOMBRE_COMUN, NOMBRE_CIENTIFICO FROM PLANTA ORDER BY NOMBRE_COMUN";

        try {
            conn = ConexionOracle.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_PLANTA");
                String nombreComun = rs.getString("NOMBRE_COMUN");
                String nombreCientifico = rs.getString("NOMBRE_CIENTIFICO");
                
                // Crea un objeto Planta con los datos de la DB
                Planta planta = new Planta(id, nombreComun, nombreCientifico);
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
