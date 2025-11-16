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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 
import logica.InformePro;

public class DaoInformePro {
    private static final String SQL_INFORME = 
        "WITH REGISTRO_ICA_PARAM AS ( " +
        "    SELECT ? AS ICA_BUSCADO FROM DUAL" + // <-- SOLO UN PLACEHOLDER (?)
        "), " +
        "LotesPorEspecie AS ( " +
        "    SELECT " +
        "        LP.NOMBRE AS nombre_lugar, " +
        "        LP.NUMERO_REGISTRO_ICA AS registro_ica, " +
        "        P.nombre_comun, " +
        "        SUM(L.CANT_RECOLECTADA) AS \"Cantidad Rec por Especie\", " +
        "        SUM(L.CANT_RECOLECTADA) / NULLIF(SUM(L.cantidad_de_plantas), 0) AS \"Prom Recolec (por planta)\", " +
        "        SUM(L.Area_de_lote) AS \"Área por Especie (m²)\" " +
        "    FROM " +
        "        LUGAR_PRODUCCION LP " +
        "    JOIN " +
        "        LOTE L ON LP.NUMERO_REGISTRO_ICA = L.NRO_REGISTRO_ICA_LUGARP " +
        "    JOIN " +
        "        Planta P ON L.id_planta = P.id_planta " +
        "    JOIN " +
        "        REGISTRO_ICA_PARAM P1 ON LP.NUMERO_REGISTRO_ICA = P1.ICA_BUSCADO " + // Filtrado por el CTE
        "    GROUP BY " +
        "        LP.nombre, " +
        "        LP.NUMERO_REGISTRO_ICA, " +
        "        P.nombre_comun " +
        "), " +
        "AreaTotal AS ( " +
        "    SELECT " +
        "        LP.NUMERO_REGISTRO_ICA AS registro_ica, " +
        "        SUM(L.Area_de_lote) AS Area_Total_Cultivada " +
        "    FROM " +
        "        LUGAR_PRODUCCION LP " +
        "    JOIN " +
        "        LOTE L ON LP.NUMERO_REGISTRO_ICA = L.NRO_REGISTRO_ICA_LUGARP " +
        "    JOIN " +
        "        REGISTRO_ICA_PARAM P1 ON LP.NUMERO_REGISTRO_ICA = P1.ICA_BUSCADO " + // Filtrado por el CTE
        "    GROUP BY " +
        "        LP.NUMERO_REGISTRO_ICA " +
        ") " +
        "SELECT " +
        "    A.nombre_lugar AS \"Lugar de Producción\", " +
        "    TO_CHAR(T.Area_Total_Cultivada, 'FM99999.00') AS \"Área Cultivada Total (m²)\", " +
        "    A.nombre_comun AS \"Nombre Común de la Planta\", " +
        "    TO_CHAR(A.\"Cantidad Rec por Especie\", 'FM99999.00') AS \"Cantidad Rec por Especie\", " +
        "    TO_CHAR(A.\"Prom Recolec (por planta)\", 'FM99999.00') AS \"Prom Recolec (por planta)\", " +
        "    TO_CHAR(A.\"Área por Especie (m²)\", 'FM99999.00') AS \"Área por Especie (m²)\" " +
        "FROM " +
        "    LotesPorEspecie A " +
        "JOIN " +
        "    AreaTotal T ON A.registro_ica = T.registro_ica " +
        "ORDER BY " +
        "    A.nombre_comun";

    /**
     * Obtiene el informe de producción para un registro ICA específico.
     * @param registroIca El Nro de registro ICA del lugar de producción.
     * @return Una lista de objetos InformePro con los resultados.
     */
    public List<InformePro> obtenerInforme(String registroIca) {
        List<InformePro> informe = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Obtener conexión
            conn = ConexionOracle.getConnection();
            if (conn == null) {
                System.out.println("❌ Fallo al obtener la conexión. Terminando operación DAO.");
                return informe;
            }

            // 2. Preparar la sentencia SQL
            stmt = conn.prepareStatement(SQL_INFORME);
            
            // 3. Establecer el ÚNICO parámetro del SQL
            // Usamos setString/setObject para el ÚNICO PLACEHOLDER
            stmt.setString(1, registroIca); 
            // ¡ELIMINAMOS la línea stmt.setString(2, registroIca); !

            // 4. Ejecutar la consulta
            rs = stmt.executeQuery();

            // 4. Procesar el ResultSet
            while (rs.next()) {
                // Las columnas se seleccionan por el alias definido en la consulta SQL
                InformePro.InformeProduccionBuilder builder = new InformePro.InformeProduccionBuilder()
                    .conNombreLugar(rs.getString("Lugar de Producción"))
                    // Ahora se usa getString() para todos los campos numéricos
                    .conAreaTotal(rs.getString("Área Cultivada Total (m²)")) 
                    .conPlanta(rs.getString("Nombre Común de la Planta"))
                    .conRecolectado(rs.getString("Cantidad Rec por Especie"))
                    .conPromedio(rs.getString("Prom Recolec (por planta)"))
                    .conAreaCultivo(rs.getString("Área por Especie (m²)"));
                
                informe.add(builder.build());
            }

        } catch (SQLException e) {
            System.err.println("❌ Error de SQL al ejecutar el informe: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 5. Cerrar recursos (en orden inverso de apertura)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar recursos: " + e.getMessage());
            }
            ConexionOracle.cerrarConexion(conn);
        }
        
        return informe;
    }
}
