/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

/**
 *
 * @author CASA
 */
// Archivo: persistencia/DaoTecnico.java

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import persistencia.ConexionOracle; // Asumiendo que está en el paquete persistencia

public class DaoTecnico {

    /**
     * Guarda un nuevo registro de técnico con todos los datos en la tabla TECNICO.
     *
     * @param documento Documento de identidad.
     * @param nomUser Nombre de usuario.
     * @param clave Contraseña.
     * @param nombre Nombres.
     * @param apellido Apellidos.
     * @param telefono Teléfono.
     * @param correo Correo electrónico.
     * @param tarjetaProfesional Tarjeta Profesional.
     * @return true si el registro fue exitoso, false en caso de error.
     */
    public boolean guardarTecnico(long documento, String nomUser, String clave,
                                  String nombre, String apellido, long telefono,
                                  String correo, long tarjetaProfesional) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // La consulta inserta todos los campos en la tabla TECNICO
        String sql = "INSERT INTO Tecnico (" +
                     "Documento, nombre_Usuario, clave, nombre, apellido, " +
                     "telefono, email, targeta_Profecional) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConexionOracle.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                
                // Asigna los valores a los parámetros de la consulta
                stmt.setLong(1, documento);
                stmt.setString(2, nomUser);
                stmt.setString(3, clave);
                stmt.setString(4, nombre);
                stmt.setString(5, apellido);
                stmt.setLong(6, telefono);
                stmt.setString(7, correo);
                stmt.setLong(8, tarjetaProfesional); // Se convierte a long para DB

                int filasAfectadas = stmt.executeUpdate();
                
                if (filasAfectadas > 0) {
                    exito = true;
                    System.out.println("✅ Técnico con documento " + documento + " registrado exitosamente.");
                } else {
                    System.out.println("⚠️ No se insertó el registro del técnico. (0 filas afectadas)");
                }
            }
        } catch (SQLException e) {
            // Manejo de errores específicos (ej: PK duplicada, Unique key duplicada)
            if (e.getErrorCode() == 1) { // Código de error 1: Unique constraint violated (PK o Targeta_Profecional)
                System.out.println("💥 Error: El documento o la tarjeta profesional ya existen.");
            }
            System.out.println("💥 Error de SQL al guardar el técnico: " + e.getMessage());
        } finally {
            // Cierre de recursos
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionOracle.cerrarConexion(conn);
        }

        return exito;
    }
    
    public boolean eliminarTecnico(String documento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // SQL para eliminar de la tabla FUNCIONARIO_ICA, filtrando por el Documento.
        String sql = "DELETE FROM Tecnico WHERE Documento = ?";

        try {
            conn = ConexionOracle.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                
                // Asigna el valor del documento al parámetro de la consulta
                stmt.setString(1, documento);

                int filasAfectadas = stmt.executeUpdate();
                
                if (filasAfectadas > 0) {
                    exito = true;
                    System.out.println("🗑️ Técnico con documento " + documento + " eliminado exitosamente.");
                } else {
                    // Esto indica que no se encontró un funcionario con ese documento
                    System.out.println("⚠️ No se encontró el Técnico con documento " + documento + " para eliminar.");
                }
            }
        } catch (SQLException e) {
            System.out.println("💥 Error de SQL al eliminar el Técnico: " + e.getMessage());
            // Opcionalmente, puedes manejar errores de integridad referencial aquí
            // (ej: si el funcionario está referenciado en otra tabla y la base de datos lo prohíbe)
        } finally {
            // Cierre de recursos
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConexionOracle.cerrarConexion(conn);
        }

        return exito;
    }
    
}
