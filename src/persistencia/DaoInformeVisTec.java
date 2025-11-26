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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional; 
import logica.InformeVisTec;
// Importa tu DTO y tu clase ConexionOracle (si están en el mismo paquete, no es necesario el import)

public class DaoInformeVisTec { 

    // La sentencia SQL final que definimos.
    private static final String SQL_ULTIMO_INFORME = 
        "SELECT " +
    "    ldp.nombre, " +
    "    t.nombre AS nombre_tecnico, " +
    "    t.apellido AS apellido_tecnico, " +
    "    oi.fecha_estimada AS fecha_ultima_inspeccion, " +
    "    it.AREA_ACOPIO, " +
    "    it.AREA_MAN_RESIDUOS_VEGETALES, " +
    "    it.AREA_ALMAC_INSUMOS_AGRICOLAS, " +
    "    it.AREA_DOSIF_PREP_MEZCLAS, " +
    "    it.AREA_RESIDUOS_MEZ_LAVADO, " +
    "    it.AREA_SANITARIA_LAVAMANOS, " +
    "    it.AREA_HERRAMIENTAS, " +
    "    it.COMENTARIOS " +
    "FROM " +
    "    LUGAR_PRODUCCION ldp " +
    "INNER JOIN " +
    "    ORDEN_INSPECCION oi ON ldp.numero_registro_ica = oi.NUMERO_ICA_LUGAR_PRODUCCION " +
    "INNER JOIN " +
    "    TECNICO t ON oi.NUMERO_DOCUMENTO_TECNICO = t.documento " +
    "INNER JOIN " +
    "    INSPECCION_TECNICA it ON oi.id = it.id_orden " +
    "WHERE " +
    "    ldp.numero_registro_ica = ? " +
    "    AND oi.tipo = 'Tecnica' " +         // <-- FILTRO CLAVE 1: Solo inspecciones Técnicas
    "    AND oi.estado = 'REALIZADA' " +     // <-- FILTRO CLAVE 2: Solo inspecciones ya realizadas
    "    AND oi.fecha_estimada = ( " +
    "        SELECT MAX(fecha_estimada) " +
    "        FROM ORDEN_INSPECCION " +
    "        WHERE NUMERO_ICA_LUGAR_PRODUCCION = ? " +
    "        AND tipo = 'Tecnica' " +         // <-- FILTRO CLAVE 3 (en subconsulta): Solo Técnicas
    "        AND estado = 'REALIZADA' " +     // <-- FILTRO CLAVE 4 (en subconsulta): Solo Realizadas
    "    )";


    /**
     * Recupera el informe de la inspección técnica más reciente para un número ICA dado.
     * * @param icaNumber El número de registro ICA del lugar de producción.
     * @return Un Optional<InformeUltimaInspeccionDTO> con el resultado, o vacío.
     */
    public Optional<InformeVisTec> obtenerUltimoInformePorIca(String icaNumber) {
        
        // 1. Obtener la conexión usando tu utilidad
        Connection connection = ConexionOracle.getConnection();
        
        // Manejo de recursos con try-with-resources (cierra stmt y rs automáticamente)
        try (PreparedStatement stmt = connection.prepareStatement(SQL_ULTIMO_INFORME)) {
            
            // 2. Comprobación y Configuración del PreparedStatement
            if (connection == null) {
                // Si ConexionOracle falló (e imprimió el error), salimos.
                return Optional.empty(); 
            }

            stmt.setString(1, icaNumber);
            stmt.setString(2, icaNumber); 

            // 3. Ejecutar y Mapear el ResultSet
            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    // Mapeo utilizando el Builder que creamos
                    InformeVisTec informe = new InformeVisTec.InformeUltimaInspeccionBuilder()
                        .conNombreLugar(rs.getString("nombre"))
                        .conNombreTecnico(rs.getString("nombre_tecnico"))
                        .conApellidoTecnico(rs.getString("apellido_tecnico"))
                        .conFechaUltimaInspeccion(rs.getString("fecha_ultima_inspeccion")) 
                        .conAreaAcopio(rs.getString("AREA_ACOPIO"))
                        .conAreaManResiduosVegetales(rs.getString("AREA_MAN_RESIDUOS_VEGETALES"))
                        .conAreaAlmacInsumosAgricolas(rs.getString("AREA_ALMAC_INSUMOS_AGRICOLAS"))
                        .conAreaDosifPrepMezclas(rs.getString("AREA_DOSIF_PREP_MEZCLAS"))
                        .conAreaResiduosMezLavado(rs.getString("AREA_RESIDUOS_MEZ_LAVADO"))
                        .conAreaSanitariaLavamanos(rs.getString("AREA_SANITARIA_LAVAMANOS"))
                        .conAreaHerramientas(rs.getString("AREA_HERRAMIENTAS"))
                        .conComentarios(rs.getString("COMENTARIOS"))
                        .build();
                    
                    return Optional.of(informe); 
                }
                
                return Optional.empty(); // No hay resultados
            }

        } catch (SQLException e) {
            // Manejo de errores de ejecución de la consulta (SQL, parámetros, etc.)
            System.out.println("💥 Error al ejecutar la consulta SQL: " + e.getMessage());
            return Optional.empty();
        } finally {
            // 4. Cerrar la conexión SÍ O SÍ
            ConexionOracle.cerrarConexion(connection);
        }
    }
}
