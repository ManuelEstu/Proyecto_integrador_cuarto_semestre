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
import java.sql.SQLException;
import persistencia.ConexionOracle;

public class DaoProductor {

    public boolean guardarProductor(long documento, String nomUser, String clave,
                                    String nombre, String apellido, long telefono,
                                    String correo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // SQL para insertar en la tabla PRODUCTOR.
        String sql = "INSERT INTO Productor (" +
                     "Documento, nombre_Usuario, clave, nombre, apellido, " +
                     "telefono, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConexionOracle.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                
                stmt.setLong(1, documento);
                stmt.setString(2, nomUser);
                stmt.setString(3, clave);
                stmt.setString(4, nombre);
                stmt.setString(5, apellido);
                stmt.setLong(6, telefono);
                stmt.setString(7, correo);

                int filasAfectadas = stmt.executeUpdate();
                exito = filasAfectadas > 0;
                
                if (exito) {
                    System.out.println("✅ Productor con documento " + documento + " registrado exitosamente.");
                } else {
                    System.out.println("⚠️ No se insertó el registro del Productor.");
                }
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1) { // Error de violación de restricción (PK o UNIQUE)
                System.out.println("💥 Error: El Documento o el Nombre de Usuario del Productor ya existen.");
            } else {
                System.out.println("💥 Error de SQL al guardar el Productor: " + e.getMessage());
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionOracle.cerrarConexion(conn);
        }

        return exito;
    }
    
    public String eliminarProductor(String documento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        // Ahora devuelve un String que indica el resultado
        String resultado = "ERROR_DESCONOCIDO"; 

        // SQL para eliminar de la tabla Productor
        String sql = "DELETE FROM Productor WHERE Documento = ?"; 

        try {
            conn = ConexionOracle.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, documento);

                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    // Éxito en la eliminación
                    System.out.println("🗑️ Productor con documento " + documento + " eliminado exitosamente.");
                    resultado = "EXITO";
                } else {
                    // No se encontró el documento
                    System.out.println("⚠️ No se encontró el Productor con documento " + documento + " para eliminar.");
                    resultado = "NO_ENCONTRADO";
                }
            }
        } catch (SQLException e) {
            // --- MANEJO DE INTEGRIDAD REFERENCIAL (Oracle ORA-02292) ---
            if (e.getErrorCode() == 2292) { 
                // Esto significa que tiene Lugares de Producción asociados
                System.out.println("⛔️ El Productor tiene Lugares de Producción asociados (ORA-02292).");
                resultado = "ERROR_FK";
            } else {
                // Otros errores de SQL
                System.out.println("💥 Error de SQL al intentar eliminar el Productor: " + e.getMessage());
                resultado = "ERROR_SQL: " + e.getMessage();
            }
        } finally {
            // Cierre de recursos
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionOracle.cerrarConexion(conn);
        }

        return resultado;
    }
    
}
