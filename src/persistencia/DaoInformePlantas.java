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
import logica.InformePlantas;

/**
 * Clase DAO para acceder a los datos de las plantas y generar el informe.
 * Utiliza gestión explícita de recursos (try-catch-finally).
 */
public class DaoInformePlantas {

    // La consulta SQL que definiste
    private static final String SQL_INFORME_PLANTAS =
        "SELECT " +
        "    p.nombre_comun AS \"Nombre de la Planta\", " +
        "    COUNT(l.id_lote) AS \"Cantidad de Lotes\", " +
        "    COUNT(DISTINCT l.NRO_REGISTRO_ICA_LUGARP) AS \"Cantidad de Lugares\" " +
        "FROM " +
        "    PLANTA p " +
        "JOIN " +
        "    LOTE l ON p.id_planta = l.id_planta " +
        "GROUP BY " +
        "    p.nombre_comun " +
        "ORDER BY " +
        "    \"Cantidad de Lotes\" DESC";

    // Método para obtener el informe (No recibe la conexión y la gestiona internamente)
    public List<InformePlantas> obtenerInformePlantas() { 
        
        List<InformePlantas> informe = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Obtener conexión
            conn = ConexionOracle.getConnection();
            if (conn == null) {
                System.out.println("❌ Fallo al obtener la conexión. Terminando operación DAO.");
                return informe;
            }

            // 2. Preparar la sentencia SQL (No hay parámetros en tu consulta)
            stmt = conn.prepareStatement(SQL_INFORME_PLANTAS);
            
            // 3. Ejecutar la consulta
            rs = stmt.executeQuery();

            // 4. Procesar el ResultSet
            while (rs.next()) {
                // Mapeamos los resultados del ResultSet a la clase DTO
                
                String nombrePlanta = rs.getString("Nombre de la Planta");
                int cantidadLotes = rs.getInt("Cantidad de Lotes");
                int cantidadLugares = rs.getInt("Cantidad de Lugares");
                
                InformePlantas dto = new InformePlantas(nombrePlanta, cantidadLotes, cantidadLugares);
                informe.add(dto);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error de SQL al ejecutar el informe: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 5. Cerrar recursos (en orden inverso: ResultSet, PreparedStatement, Connection)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar recursos (RS/STMT): " + e.getMessage());
            }
            // La clase ConexionOracle tiene un método para cerrar la conexión
            ConexionOracle.cerrarConexion(conn);
        }
        
        return informe;
    }
}
