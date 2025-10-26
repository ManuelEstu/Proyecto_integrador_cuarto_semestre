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
import logica.DatosPredio;

public class DaoPredio {
    
    // Método para obtener el siguiente número de Registro ICA
    public int obtenerSiguienteICA() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int siguienteICA = 1; // Valor inicial por defecto si la tabla está vacía

        String sql = "SELECT MAX(numero_registro_ICA) FROM Predio";
        
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
    public boolean guardarLugar(int registroICA, String numeroPredial, String nombre, String numICALugar,
                                String extencion, String vereda, String documentoDueno, 
                                String departamento, String municipio) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;
        
        // La sentencia INSERT para tu tabla Lugar_produccion
        String sql = "INSERT INTO Predio ("
                   + "numero_registro_ICA, numero_predial, nombre, departamento, municipio, vereda, "
                   + "numero_ICA_lugar_produccion, extension, documento_propietario"
                   + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            conn = ConexionOracle.getConnection();
            stmt = conn.prepareStatement(sql);
            
            // 1. numero_registro_ICA (NUMBER(9)) - ¡Usamos el valor generado!
            stmt.setInt(1, registroICA); 
            // 2. numero_predial (NUMBER(30))
            stmt.setString(2, numeroPredial); 
            // 3. nombre (VARCHAR2(50))
            stmt.setString(3, nombre);
            // 4. nombre_empresa (VARCHAR2(50))
            stmt.setString(4, departamento);
            // 5. telefono_empresa (NUMBER(10))
            stmt.setString(5, municipio);
            // 6. departamento (VARCHAR2(50))
            stmt.setString(6, vereda);
            // 7. municipio (VARCHAR2(50))
            stmt.setString(7, numICALugar);
            // 8. vereda (VARCHAR2(50))
            stmt.setString(8, extencion);
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
    
    public String eliminarPredio(String numero_ica) {
        Connection conn = null;
        PreparedStatement stmt = null;
        // Ahora devuelve un String que indica el resultado
        String resultado = "ERROR_DESCONOCIDO"; 

        // SQL para eliminar de la tabla Productor
        String sql = "DELETE FROM Predio WHERE numero_registro_ICA = ?"; 

        try {
            conn = ConexionOracle.getConnection();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, numero_ica);

                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    // Éxito en la eliminación
                    System.out.println("🗑️ Predio con número de registro ICA " + numero_ica + " eliminado exitosamente.");
                    resultado = "EXITO";
                } else {
                    // No se encontró el documento
                    System.out.println("⚠️ No se encontró el Predio con número registro ICA " + numero_ica + " para eliminar.");
                    resultado = "NO_ENCONTRADO";
                }
            }
        } catch (SQLException e) {
            System.out.println("💥 Error de SQL al eliminar el Predio: " + e.getMessage());
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
    
    public Object[] buscarLugar(String documento, String numica) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        // 🚨 Define la tabla y la columna de Tarjeta Profesional según el tipo
        String tabla = "Predio";

        // Consulta SQL genérica que se adapta al tipo de usuario
        String sql = "SELECT Numero_registro_ICA, Numero_predial, Nombre, Departamento, Municipio, Vereda, Numero_ica_lugar_produccion, Extension, Documento_propietario" + 
                     " FROM " + tabla + " WHERE Numero_registro_ICA = ? AND Documento_propietario = ?";

        try {
            conn = ConexionOracle.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, numica);
            ps.setString(2, documento);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si se encuentra el registro, extrae los datos
                Object[] datos = new Object[9];
                datos[0] = rs.getString("Numero_registro_ICA");
                datos[1] = rs.getString("Numero_predial");
                datos[2] = rs.getString("Nombre"); // ⚠️ ¡Recuerda manejar la clave de forma segura!
                datos[3] = rs.getString("Departamento");
                datos[4] = rs.getString("Municipio");
                datos[5] = rs.getString("Vereda");
                datos[6] = rs.getString("Numero_ica_lugar_produccion");
                datos[7] = rs.getString("Extension");
                datos[8] = rs.getString("Documento_propietario");
                
                return datos;
            }
        } catch (SQLException e) {
            System.out.println("💥 Error al buscar perfil en la BD: " + e.getMessage());
        } finally {
            // Cerrar recursos en orden inverso (ResultSet, PreparedStatement, Connection)
            try { if (rs != null) rs.close(); } catch (SQLException e) { /* Ignorar */ }
            try { if (ps != null) ps.close(); } catch (SQLException e) { /* Ignorar */ }
            ConexionOracle.cerrarConexion(conn);
        }
        
        return null; // Retorna null si no se encuentra o hay un error
    }
    
    public boolean actualizarLugar(DatosPredio datos) {
        Connection conn = null;
        PreparedStatement ps = null;
        String tabla = "Predio";

        // 2. Construir la consulta SQL con SÓLO los campos editables
        String sql = "UPDATE " + tabla + " SET " +
                     "NOMBRE = ?, " +
                     "Numero_ica_lugar_produccion = ?, " +
                     "Extension = ? " +
                     "WHERE Numero_registro_ICA = ?"; // El documento solo se usa para la condición

        try {
            // ⚠️ Asumo que tienes una clase llamada 'ConexionOracle' con el método 'getConnection()'
            conn = ConexionOracle.getConnection();
            ps = conn.prepareStatement(sql);
            
            int index = 1;
            // 3. Asignar los valores a los campos editables
            ps.setString(index++, datos.nombre);
            ps.setString(index++, datos.numero_ica_lugar_produccion);
            ps.setString(index++, datos.extension);

            // 4. Asignar el DOCUMENTO (cláusula WHERE)
            ps.setString(index, datos.numero_registro_ICA); 

            // 5. Ejecutar la actualización
            int filasAfectadas = ps.executeUpdate();
            
            // 6. Verificar el resultado
            return filasAfectadas == 1;

        } catch (SQLException e) {
            System.out.println("💥 Error al actualizar lugar de produccion en la BD: " + e.getMessage());
            return false;
        } finally {
            // Cerrar recursos
            try { if (ps != null) ps.close(); } catch (SQLException e) { /* Ignorar */ }
            // ⚠️ Asumo que tienes un método estático llamado 'cerrarConexion(Connection conn)'
            ConexionOracle.cerrarConexion(conn);
        }
    }
}
