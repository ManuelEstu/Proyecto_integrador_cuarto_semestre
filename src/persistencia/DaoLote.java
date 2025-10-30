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
import persistencia.ConexionOracle;

public class DaoLote {
     // Método para obtener el siguiente id_lote
    public int obtenerIdLote() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int siguienteId = 1; // Valor inicial por defecto si la tabla está vacía

        String sql = "SELECT MAX(ID_LOTE) FROM Lote";
        
        try {
            conn = ConexionOracle.getConnection(); // Asumo que tienes un método 'conectar()'
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener el valor máximo. Si es NULL (tabla vacía), se usará 0.
                int maxId = rs.getInt(1); 
                if (maxId > 0) {
                    siguienteId = maxId + 1; // Incrementar el valor máximo encontrado
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el siguiente ID: " + e.getMessage());
            // En caso de error, puedes lanzar una excepción o retornar un valor especial.
            // Por simplicidad, retornaremos 0 para indicar un error grave.
            return 0; 
        } finally {
            // Cierre de recursos (idealmente usando un método de utilidad)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return siguienteId;
    }
    
    // Método para guardar el lugar de producción en la base de datos
    public boolean guardarLote(
        int siguienteId, String NRO_LOTE, int ID_PLANTA, String NRO_REGISTRO_ICA_LUGARP,
        String AREA_DE_LOTE, String FECHA_DE_SIEMBRA, String FECHA_DE_ELIMINACION, 
        String ESTADO, String CANTIDAD_DE_PLANTAS, String FECHA_PROYEC_RECOLEC, 
        String CANT_PROYEC_RECOLEC, String FECHA_RECOLECCION, String CANT_RECOLECTADA) {

        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // La sentencia INSERT para tu tabla LOTE
        String sql = "INSERT INTO Lote ("
                   + "ID_LOTE, NRO_LOTE, ID_PLANTA, NRO_REGISTRO_ICA_LUGARP, AREA_DE_LOTE, FECHA_DE_SIEMBRA, "
                   + "FECHA_DE_ELIMINACION, ESTADO, CANTIDAD_DE_PLANTAS, FECHA_PROYEC_RECOLEC, CANT_PROYEC_RECOLEC, FECHA_RECOLECCION, CANT_RECOLECTADA"
                   + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConexionOracle.getConnection();
            try (java.sql.Statement s = conn.createStatement()) {
                s.execute("ALTER SESSION SET NLS_NUMERIC_CHARACTERS = '.,' NLS_DATE_FORMAT = 'DD/MM/YYYY'");
            }
            stmt = conn.prepareStatement(sql);

            // 1. ID_LOTE (int)
            stmt.setInt(1, siguienteId); 
            // 2. NRO_LOTE (String) - Aunque tu DB podría ser NUMBER, se envía como String
            stmt.setString(2, NRO_LOTE); 
            // 3. ID_PLANTA (int) - Tipo primitivo
            stmt.setInt(3, ID_PLANTA);
            // 4. NRO_REGISTRO_ICA_LUGARP (String)
            stmt.setString(4, NRO_REGISTRO_ICA_LUGARP);
            // 5. AREA_DE_LOTE (String) - Contiene el número con punto
            stmt.setString(5, AREA_DE_LOTE);
            // 6. FECHA_DE_SIEMBRA (String) - Formato DD/MM/AAAA
            stmt.setString(6, FECHA_DE_SIEMBRA);
            // 7. FECHA_DE_ELIMINACION (String) - Formato DD/MM/AAAA, puede ser vacío
            // Usamos setString(..., null) si la cadena es vacía para representar NULL en Oracle
            stmt.setString(7, FECHA_DE_ELIMINACION.isEmpty() ? null : FECHA_DE_ELIMINACION); 
            // 8. ESTADO (String)
            stmt.setString(8, ESTADO);
            // 9. CANTIDAD_DE_PLANTAS (String)
            stmt.setString(9, CANTIDAD_DE_PLANTAS); 
            // 10. FECHA_PROYEC_RECOLEC (String)
            stmt.setString(10, FECHA_PROYEC_RECOLEC);
            // 11. CANT_PROYEC_RECOLEC (String)
            stmt.setString(11, CANT_PROYEC_RECOLEC);
            // 12. FECHA_RECOLECCION (String) - Se asume vacío o null en la creación
            stmt.setString(12, FECHA_RECOLECCION.isEmpty() ? null : FECHA_RECOLECCION);
            // 13. CANT_RECOLECTADA (String) - Se asume 0 o null en la creación
            stmt.setString(13, CANT_RECOLECTADA.isEmpty() ? "0" : CANT_RECOLECTADA);

            // Ejecutar la inserción
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar el lote: " + e.getMessage());
            exito = false;
        } finally {
            // Cierre de recursos
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return exito;
    }
    
}
