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
import java.util.Optional;
import logica.InformeEstadoLug;

/**
 * Data Access Object para gestionar la recuperación del Informe de Estado
 * de un Lugar de Producción, utilizando la clase ConexionOracle.
 */
public class DaoInformeEstadoLug {

    // La consulta SQL proporcionada (NO MODIFICADA, solo formateada como bloque de texto)
    private static final String SQL_INFORME_ESTADO = """
        SELECT
            lp.nombre AS "Nombre Lugar Producción",
            predios_info.area_total AS "Área Total",
            predios_info.nro_predios AS "Número de Predios Asociados",
            lotes_info.nro_lotes AS "Cantidad de Lotes Asociados",
            lotes_info.area_lotes_ocupada AS "Área Ocupada por Lotes",
            (predios_info.area_total - lotes_info.area_lotes_ocupada) AS "Área Restante"
        FROM
            LUGAR_PRODUCCION lp
        LEFT JOIN
            (
                SELECT
                    p.NUMERO_ICA_LUGAR_PRODUCCION,
                    SUM(p.EXTENSION) AS area_total,
                    COUNT(p.NUMERO_REGISTRO_ICA) AS nro_predios
                FROM
                    PREDIO p
                GROUP BY
                    p.NUMERO_ICA_LUGAR_PRODUCCION
            ) predios_info
        ON
            lp.NUMERO_REGISTRO_ICA = predios_info.NUMERO_ICA_LUGAR_PRODUCCION
        LEFT JOIN
            (
                SELECT
                    l.NRO_REGISTRO_ICA_LUGARP AS nro_registro_ica_lote,
                    COUNT(l.id_lote) AS nro_lotes,
                    SUM(l.area_de_lote) AS area_lotes_ocupada
                FROM
                    LOTE l
                GROUP BY
                    l.NRO_REGISTRO_ICA_LUGARP
            ) lotes_info
        ON
            lp.NUMERO_REGISTRO_ICA = lotes_info.nro_registro_ica_lote
        WHERE
            lp.NUMERO_REGISTRO_ICA = ?
        """;
        /*
         * Nota: Para un mejor manejo de NULLs en el cálculo de "Área Restante" (específico de Oracle si los campos
         * no están presentes en las subconsultas), se recomienda envolver las columnas en NVL()
         * en la consulta SQL, pero mantengo la consulta original solicitada.
         */

    /**
     * Obtiene el informe de estado de un Lugar de Producción por su número de registro ICA.
     * @param numeroRegistroIca El número ICA del Lugar de Producción (el parámetro '?').
     * @return Un Optional que contiene el InformeEstadoLug si se encuentra, o un Optional vacío.
     */
    public Optional<InformeEstadoLug> obtenerInformePorIca(String numeroRegistroIca) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1. Obtener la conexión usando tu clase
            conn = ConexionOracle.getConnection();
            if (conn == null) {
                // Si la conexión falló en ConexionOracle (ya mostró el error), salimos.
                return Optional.empty();
            }

            ps = conn.prepareStatement(SQL_INFORME_ESTADO);

            // 2. Establecer el parámetro del PreparedStatement
            ps.setString(1, numeroRegistroIca);

            // 3. Ejecutar la consulta
            rs = ps.executeQuery();

            // 4. Procesar el ResultSet y mapear a la entidad
            if (rs.next()) {
                // Mapeo utilizando el Builder de InformeEstadoLug.
                // Usamos rs.getString() para todas las columnas, ya que el DTO las acepta como String.
                InformeEstadoLug informe = new InformeEstadoLug.InformeProduccionBuilder()
                    .conNombreLugar(rs.getString("Nombre Lugar Producción"))
                    .conAreaTotal(rs.getString("Área Total"))
                    .conPredios(rs.getString("Número de Predios Asociados"))
                    .conLotes(rs.getString("Cantidad de Lotes Asociados"))
                    .conAreaOcupada(rs.getString("Área Ocupada por Lotes"))
                    .conAreaRestante(rs.getString("Área Restante"))
                    .build();

                return Optional.of(informe);
            } else {
                // No se encontró ningún Lugar de Producción con ese ICA
                return Optional.empty();
            }

        } catch (SQLException e) {
            // Manejo de errores de ejecución de la consulta
            System.err.println("Error al ejecutar la consulta SQL para ICA " + numeroRegistroIca + ": " + e.getMessage());
            e.printStackTrace();
            return Optional.empty(); // Retornamos vacío si hubo un error de ejecución
        } finally {
            // 5. Cerrar los recursos de forma segura
            // Usamos el método de cierre de tu clase de conexión
            ConexionOracle.cerrarConexion(conn);
            // Los recursos Statement y ResultSet deben cerrarse manualmente si es necesario
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) { /* Ignorar al cerrar */ }
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) { /* Ignorar al cerrar */ }
        }
    }
}
