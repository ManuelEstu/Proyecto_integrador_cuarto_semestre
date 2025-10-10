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
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {

    private static final String usuario = "proyectoICA";       // tu usuario en Oracle
    private static final String contrasenia = "proyectoICA";   // tu contraseña
    private static final String host = "localhost";
    private static final String puerto = "1521";
    private static final String servicio = "xe";            // puede ser SID o SERVICE_NAME

    // Método principal para obtener conexión
    public static Connection getConnection() {
        Connection conectar = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // ✅ Opción 1: con SID
            String url = "jdbc:oracle:thin:@" + host + ":" + puerto + ":" + servicio;

            // ✅ Opción 2: si arriba falla, cambia a esta:
            // String url = "jdbc:oracle:thin:@//" + host + ":" + puerto + "/" + servicio;

            conectar = DriverManager.getConnection(url, usuario, contrasenia);
            System.out.println("🔥 Conexión exitosa a Oracle como usuario: " + usuario);
        } catch (ClassNotFoundException e) {
            System.out.println("💥 Error: No se encontró el driver JDBC de Oracle.");
        } catch (SQLException e) {
            System.out.println("💥 Error de conexión a la BD: " + e.getMessage());
        }
        return conectar;
    }

    // Método para cerrar conexión
    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("🔒 Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("💥 Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        getConnection();
    }
}