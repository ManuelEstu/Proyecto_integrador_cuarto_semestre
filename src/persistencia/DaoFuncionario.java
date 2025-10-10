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
import persistencia.ConexionOracle; // Ajusta el paquete si es necesario

public class DaoFuncionario {

    /**
     * Guarda los datos de un nuevo Funcionario ICA en la tabla Funcionario_ICA.
     *
     * @return true si el registro fue exitoso, false en caso de error.
     */
    public boolean guardarFuncionario(long documento, String nomUser, String clave,
                                      String nombre, String apellido, long telefono,
                                      String correo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // SQL para insertar en la tabla FUNCIONARIO_ICA. Ajusta los nombres de las columnas si son diferentes.
        String sql = "INSERT INTO Funcionario_ICA (" +
                     "Documento, nombre_Usuario, clave, nombre, apellido, " +
                     "telefono, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

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

                int filasAfectadas = stmt.executeUpdate();
                
                if (filasAfectadas > 0) {
                    exito = true;
                    System.out.println("✅ Funcionario ICA con documento " + documento + " registrado exitosamente.");
                } else {
                    System.out.println("⚠️ No se insertó el registro del Funcionario ICA. (0 filas afectadas)");
                }
            }
        } catch (SQLException e) {
            // Manejo de errores específicos (ej: PK duplicada)
            if (e.getErrorCode() == 1) { // Código de error 1: Unique constraint violated (PK o campos UNIQUE)
                System.out.println("💥 Error: El Documento o el Nombre de Usuario ya existen.");
            }
            System.out.println("💥 Error de SQL al guardar el Funcionario ICA: " + e.getMessage());
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
    
    public boolean eliminarFuncionario(String documento) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // SQL para eliminar de la tabla FUNCIONARIO_ICA, filtrando por el Documento.
        String sql = "DELETE FROM Funcionario_ICA WHERE Documento = ?";

        try {
            conn = ConexionOracle.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                
                // Asigna el valor del documento al parámetro de la consulta
                stmt.setString(1, documento);

                int filasAfectadas = stmt.executeUpdate();
                
                if (filasAfectadas > 0) {
                    exito = true;
                    System.out.println("🗑️ Funcionario ICA con documento " + documento + " eliminado exitosamente.");
                } else {
                    // Esto indica que no se encontró un funcionario con ese documento
                    System.out.println("⚠️ No se encontró el Funcionario ICA con documento " + documento + " para eliminar.");
                }
            }
        } catch (SQLException e) {
            System.out.println("💥 Error de SQL al eliminar el Funcionario ICA: " + e.getMessage());
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
