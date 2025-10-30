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
import persistencia.ConexionOracle; // Asegúrate de que esta ruta sea correcta
import logica.LugarCajita;

public class DaoCajitaLugares {

    /**
     * Obtiene los lugares de producción asociados a un documento de productor específico.
     * @param documentoProductor El documento de identificación del productor.
     * @return Una lista de objetos LugarProduccion.
     */
    public List<LugarCajita> obtenerLugaresPorProductor(String documentoProductor) {
        List<LugarCajita> listaLugares = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Utilizamos una consulta preparada para evitar inyección SQL y filtrar por DOCUMENTO_PRODUCTOR
        String sql = "SELECT NUMERO_REGISTRO_ICA, NOMBRE FROM LUGAR_PRODUCCION WHERE DOCUMENTO_PRODUCTOR = ?";

        try {
            conn = ConexionOracle.getConnection();
            ps = conn.prepareStatement(sql);
            
            // Establecer el parámetro del filtro
            // Usamos Long.parseLong() ya que el tipo de documento en la BD es NUMBER(10)
            ps.setString(1,(documentoProductor)); 
            
            rs = ps.executeQuery();

            while (rs.next()) {
                String regIca = rs.getString("NUMERO_REGISTRO_ICA");
                String nombre = rs.getString("NOMBRE");
                
                LugarCajita lugar = new LugarCajita(regIca, nombre);
                listaLugares.add(lugar);
            }

        } catch (SQLException e) {
            System.err.println("💥 Error al obtener lugares de producción de la BD: " + e.getMessage());
        } catch (NumberFormatException e) {
             System.err.println("💥 Error: El documento del productor no es un número válido.");
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

        return listaLugares;
    }
}
