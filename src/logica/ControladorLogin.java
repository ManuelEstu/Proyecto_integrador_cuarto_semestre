/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import persistencia.ConexionOracle; // Ajusta este paquete si es necesario

public class ControladorLogin {

    /**
     * Intenta iniciar sesión.
     * La lógica fuerza la comparación del nombre de usuario y clave a MAYÚSCULAS 
     * para evitar errores de case-sensitive.
     */
    public boolean iniciarSesion(String tipoUsuario, String nombreUsuario, String contrasenia) {
        
        // 1. Determinar el nombre de la tabla
        String nombreTabla;
        
        switch (tipoUsuario) {
            case "Funcionario ICA":
                nombreTabla = "FUNCIONARIO_ICA"; // Nombre de tabla confirmado
                break;
            case "Productor":
                nombreTabla = "PRODUCTOR";
                break;
            case "Técnico":
                nombreTabla = "TECNICO";
                break;
            case "Propietario":
                nombreTabla = "PROPIETARIO";
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipo de usuario no válido.", "Error de Lógica", JOptionPane.ERROR_MESSAGE);
                return false;
        }

        // 2. Definir la consulta SQL
        // **CORRECCIÓN CLAVE:** Usamos UPPER() en los parámetros de la consulta para 
        // asegurar que los datos coincidan independientemente de las mayúsculas/minúsculas 
        // ingresadas por el usuario.
        String sql = "SELECT COUNT(*) FROM " + nombreTabla + 
                     " WHERE UPPER(DOCUMENTO) = ? AND UPPER(CLAVE) = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean credencialesValidas = false;

        try {
            conn = ConexionOracle.getConnection();
            ps = conn.prepareStatement(sql);
            
            // 3. Asignar parámetros y convertir a MAYÚSCULAS para la comparación
            // Así, 'Admin' ingresado se compara como 'ADMIN' con el valor de la BD.
            ps.setString(1, nombreUsuario.toUpperCase());
            ps.setString(2, contrasenia.toUpperCase());
            
            rs = ps.executeQuery();

            // 4. Procesar el resultado
            if (rs.next() && rs.getInt(1) == 1) {
                credencialesValidas = true;
            }

        } catch (SQLException e) {
            // Este bloque captura errores como "Tabla no encontrada" o problemas de sintaxis.
            JOptionPane.showMessageDialog(null, 
                                          "Error al verificar credenciales en la base de datos (SQL): " + e.getMessage(), 
                                          "Error de Conexión/SQL", 
                                          JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // 5. Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionOracle.cerrarConexion(conn);
        }

        return credencialesValidas;
    }
}
