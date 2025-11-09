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
import java.sql.ResultSet;
import persistencia.ConexionOracle; // Ajusta el paquete si es necesario
import logica.DatosOrden;
import java.util.ArrayList;
import java.util.List;

public class DaoOrden {
    
    public int obtenerSiguienteId() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int siguienteId = 1; // Valor inicial por defecto si la tabla está vacía

        String sql = "SELECT MAX(id) FROM ORDEN_INSPECCION";
        
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
            System.err.println("Error al obtener el siguiente Id: " + e.getMessage());
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
    
    public boolean guardarOrden(int id, String tipo, String funcionario, String lugar,
                                String tecnico, String fecha, 
                                String comentarios) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;
        
        // La sentencia INSERT para tu tabla Lugar_produccion
        String sql = "INSERT INTO ORDEN_INSPECCION ("
                   + "ID, TIPO, DOCUMENTO_FUNCIONARIO_ORDENA, NUMERO_ICA_LUGAR_PRODUCCION, "
                   + "NUMERO_DOCUMENTO_TECNICO, FECHA_ESTIMADA, ESTADO, COMENTARIOS"
                   + ") VALUES (?, ?, ?, ?, ?, ?, 'pendiente', ?)";
        
        try {
            conn = ConexionOracle.getConnection();
            stmt = conn.prepareStatement(sql);
            
            // 1. numero_registro_ICA (NUMBER(6)) - ¡Usamos el valor generado!
            stmt.setInt(1, id); 
            // 2. numero_predial (NUMBER(30))
            stmt.setString(2, tipo); 
            // 3. nombre (VARCHAR2(50))
            stmt.setString(3, funcionario);
            // 4. nombre_empresa (VARCHAR2(50))
            stmt.setString(4, lugar);
            // 5. telefono_empresa (NUMBER(10))
            stmt.setString(5, tecnico);
            // 6. departamento (VARCHAR2(50))
            stmt.setString(6, fecha);
            // 7. municipio (VARCHAR2(50))
            stmt.setString(7, comentarios); 
            
            // Ejecutar la inserción
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                exito = true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al guardar la orden: " + e.getMessage());
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
    
    public List<DatosOrden> obtenerTodasLasOrdenes(String documento, String tipo) {
        List<DatosOrden> listaOrdenes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        // La consulta trae los campos necesarios: ID y Nombre Común.
        String sql = "SELECT ID, TIPO, DOCUMENTO_FUNCIONARIO_ORDENA, NUMERO_ICA_LUGAR_PRODUCCION, NUMERO_DOCUMENTO_TECNICO, FECHA_ESTIMADA, ESTADO, COMENTARIOS FROM ORDEN_INSPECCION WHERE NUMERO_DOCUMENTO_TECNICO = ? and ESTADO = 'pendiente'";
        
        // Si se proporciona un documento, se añade la cláusula WHERE
        boolean buscarPorTipo = (tipo != null && !tipo.trim().isEmpty());
        if (buscarPorTipo) {
            sql += " AND TIPO = ?";
        }
        
        try {
            conn = ConexionOracle.getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, documento.trim());
            // Asignar el parámetro si se busca por tipo
            if (buscarPorTipo) {
                ps.setString(2, tipo.trim());
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String tip = rs.getString("TIPO");
                String funcionario = rs.getString("DOCUMENTO_FUNCIONARIO_ORDENA");
                String lugar = rs.getString("NUMERO_ICA_LUGAR_PRODUCCION");
                String tec = rs.getString("NUMERO_DOCUMENTO_TECNICO");
                String fecha = rs.getString("FECHA_ESTIMADA");
                String estado = rs.getString("ESTADO");
                String comentarios = rs.getString("COMENTARIOS");
                
                // Crea un objeto Planta con los datos de la DB
                DatosOrden orden = new DatosOrden(id, tip, funcionario, lugar, tec, fecha, estado, comentarios);
                listaOrdenes.add(orden);
            }

        } catch (SQLException e) {
            System.err.println("💥 Error al obtener las ordenes de la BD: " + e.getMessage());
        } finally {
            // Asegurarse de cerrar los recursos
            ConexionOracle.cerrarConexion(conn);
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
            } catch (SQLException ex) {
                System.err.println("Error cerrando recursos: " + ex.getMessage());
            }
        }

        return listaOrdenes;
    }
    
}
