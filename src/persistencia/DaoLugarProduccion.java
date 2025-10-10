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

public class DaoLugarProduccion {
    
    // Método para obtener el siguiente número de Registro ICA
    public int obtenerSiguienteICA() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int siguienteICA = 1; // Valor inicial por defecto si la tabla está vacía

        String sql = "SELECT MAX(numero_registro_ICA) FROM Lugar_produccion";
        
        try {
            conn = ConexionOracle.getConnection(); // Asumo que tienes un método 'conectar()'
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Obtener el valor máximo. Si es NULL (tabla vacía), se usará 0.
                int maxICA = rs.getInt(1); 
                if (maxICA > 0) {
                    siguienteICA = maxICA + 1; // Incrementar el valor máximo encontrado
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el siguiente ICA: " + e.getMessage());
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
        return siguienteICA;
    }
    
    // Método para guardar el lugar de producción en la base de datos
    public boolean guardarLugar(int registroICA, String numeroPredial, String nombreLugar, String empresa,
                                String telefonoEmpresa, String vereda, String documentoDueno, 
                                String departamento, String municipio) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;
        
        // La sentencia INSERT para tu tabla Lugar_produccion
        String sql = "INSERT INTO Lugar_produccion ("
                   + "numero_registro_ICA, numero_predial, nombre, nombre_empresa, "
                   + "telefono_empresa, departamento, municipio, vereda, documento_productor"
                   + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            conn = ConexionOracle.getConnection();
            stmt = conn.prepareStatement(sql);
            
            // 1. numero_registro_ICA (NUMBER(6)) - ¡Usamos el valor generado!
            stmt.setInt(1, registroICA); 
            // 2. numero_predial (NUMBER(30))
            stmt.setString(2, numeroPredial); 
            // 3. nombre (VARCHAR2(50))
            stmt.setString(3, nombreLugar);
            // 4. nombre_empresa (VARCHAR2(50))
            stmt.setString(4, empresa);
            // 5. telefono_empresa (NUMBER(10))
            stmt.setLong(5, Long.parseLong(telefonoEmpresa));
            // 6. departamento (VARCHAR2(50))
            stmt.setString(6, departamento);
            // 7. municipio (VARCHAR2(50))
            stmt.setString(7, municipio);
            // 8. vereda (VARCHAR2(50))
            stmt.setString(8, vereda);
            // 9. documento_productor (NUMBER(10))
            stmt.setLong(9, Long.parseLong(documentoDueno)); 
            
            // Ejecutar la inserción
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                exito = true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al guardar el lugar: " + e.getMessage());
            // Manejo de errores específicos (ej. clave foránea, duplicados, etc.)
            exito = false;
        } catch (NumberFormatException e) {
             System.err.println("Error de formato de número en los parámetros: " + e.getMessage());
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
    
    public String eliminarLugar(String numero_ica) {
        Connection conn = null;
        PreparedStatement stmt = null;
        // Ahora devuelve un String que indica el resultado
        String resultado = "ERROR_DESCONOCIDO"; 

        // SQL para eliminar de la tabla Productor
        String sql = "DELETE FROM Lugar_produccion WHERE numero_registro_ICA = ?"; 

        try {
            conn = ConexionOracle.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, numero_ica);

                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    // Éxito en la eliminación
                    System.out.println("🗑️ Lugar de producción con número de registro ICA " + numero_ica + " eliminado exitosamente.");
                    resultado = "EXITO";
                } else {
                    // No se encontró el documento
                    System.out.println("⚠️ No se encontró el Lugar de producción con número de registro ICA " + numero_ica + " para eliminar.");
                    resultado = "NO_ENCONTRADO";
                }
            }
        } catch (SQLException e) {
            // --- MANEJO DE INTEGRIDAD REFERENCIAL (Oracle ORA-02292) ---
            if (e.getErrorCode() == 2292) { 
                // Esto significa que tiene Lugares de Producción asociados
                System.out.println("⛔️ El Lugar de producción tiene Predios asociados (ORA-02292).");
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
