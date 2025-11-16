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
import logica.InspeccionTecnica;

public class DaoInspTec {
    
    public int obtenerId() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int siguienteId = 1; // Valor inicial por defecto si la tabla está vacía

        String sql = "SELECT MAX(ID) FROM INSPECCION_TECNICA";
        
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
            System.err.println("Error al obtener el siguiente ID: " + e.getMessage());
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
    
    public boolean guardarInspeccion(int siguienteId, InspeccionTecnica insp) {

        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        String sql = "INSERT INTO INSPECCION_TECNICA ("
                       + "ID, ID_ORDEN, AREA_ACOPIO, AREA_MAN_RESIDUOS_VEGETALES, AREA_ALMAC_INSUMOS_AGRICOLAS, AREA_DOSIF_PREP_MEZCLAS, "
                       + "AREA_RESIDUOS_MEZ_LAVADO, AREA_SANITARIA_LAVAMANOS, AREA_HERRAMIENTAS, COMENTARIOS"
                       + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConexionOracle.getConnection();
            stmt = conn.prepareStatement(sql);

            // 1. ID
            stmt.setInt(1, siguienteId);
            // Usamos los Getters del objeto InspeccionTecnica
            stmt.setString(2, insp.getIdOrden());
            stmt.setString(3, insp.getAreaAcopio());
            stmt.setString(4, insp.getAreaManResiduosVegetales());
            stmt.setString(5, insp.getAreaAlmacInsumosAgricolas());
            stmt.setString(6, insp.getAreaDosifPrepMezclas());
            stmt.setString(7, insp.getAreaResiduosMezLavado());
            stmt.setString(8, insp.getAreaSanitariaLavamanos());
            stmt.setString(9, insp.getAreaHerramientas());
            stmt.setString(10, insp.getComentarios());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar la inspección: " + e.getMessage());
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
    
    public boolean actualizarEstadoOrden(String idOrden) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // Supongo que el campo que quieres actualizar es 'ESTADO'
        String sql = "UPDATE ORDEN_INSPECCION SET ESTADO = 'REALIZADA' WHERE ID = ?"; 

        try {
            conn = ConexionOracle.getConnection();
            stmt = conn.prepareStatement(sql);

            // 1. Establecer el ID de la orden que se va a actualizar
            stmt.setString(1, idOrden);

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
            } else {
                // Esto podría significar que el ID de Orden no existe
                System.err.println("Advertencia: No se encontró la Orden " + idOrden + " para actualizar su estado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado de la orden: " + e.getMessage());
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
    
}
