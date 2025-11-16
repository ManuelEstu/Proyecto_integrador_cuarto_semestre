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

public class CConexion {

    // ==============================================
    // 🔧 CONFIGURACIÓN GENERAL DE LA BASE DE DATOS
    // ==============================================
    private static final String HOST = "localhost";
    private static final String PUERTO = "1521";
    private static final String SERVICIO = "xe"; // puede ser SID o SERVICE_NAME

    // ==============================================
    // 🔹 1️⃣ Conexión base (por defecto)
    //      -> se conecta como ADMINISTRADOR
    // ==============================================
    public static Connection getConnection() {
        // Esta conexión solo debe usarse para tareas generales o pruebas
        return getConnectionPorUsuario("proyectoica", "proyectoica");
    }

    // ==============================================
    // 🔹 2️⃣ Conexión según ROL
    //      -> se conecta automáticamente con el usuario correcto
    // ==============================================
    public static Connection getConnectionPorRol(String rol) {
        String usuario;
        String contrasena;

        switch (rol.toLowerCase()) {
            case "Productor":
                usuario = "PRODUCTOR";
                contrasena = "PRODUCTOR";
                break;
            case "Técnico":
                usuario = "TECNICO";
                contrasena = "TECNICO";
                break;
            case "Funcionario ica":
                usuario = "funcionario";
                contrasena = "funcionario";
                break;
            default: // propietario
                usuario = "PROPIETARIO";
                contrasena = "PROPIETARIO";
                break;
        }

        return getConnectionPorUsuario(usuario, contrasena);
    }

    // ==============================================
    // 🔹 3️⃣ Conexión directa por usuario y contraseña
    //      -> se usa internamente o en el LoginDAO
    // ==============================================
    public static Connection getConnectionPorUsuario(String usuario, String contrasena) {
        Connection conexion = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 💡 Usa esta URL si trabajas con SID (por ejemplo "xe")
            String url = "jdbc:oracle:thin:@" + HOST + ":" + PUERTO + ":" + SERVICIO;

            // 💡 O esta si trabajas con SERVICE_NAME:
            // String url = "jdbc:oracle:thin:@//" + HOST + ":" + PUERTO + "/" + SERVICIO;

            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("🔥 Conectado a Oracle como: " + usuario);

        } catch (ClassNotFoundException e) {
            System.out.println("💥 Error: No se encontró el driver JDBC de Oracle.");
        } catch (SQLException e) {
            System.out.println("💥 Error de conexión con " + usuario + ": " + e.getMessage());
        }

        return conexion;
    }

    // ==============================================
    // 🔹 4️⃣ Cerrar conexión
    // ==============================================
    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("🔒 Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("💥 Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}
