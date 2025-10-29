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
import logica.DatosPredio2;
import persistencia.ConexionOracle;

public class DaoPredio2 {
    
    public List<DatosPredio2> buscarPredio(String documento, String nombre) {
        List<DatosPredio2> listaPredios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Mapeo del tipo de usuario en el JComboBox a la tabla en la base de datos
        String tablaDB = "PREDIO";

        // Construcción dinámica del query
        String sql = "SELECT NUMERO_REGISTRO_ICA, NUMERO_PREDIAL, NOMBRE, DEPARTAMENTO, MUNICIPIO, VEREDA, NUMERO_ICA_LUGAR_PRODUCCION, EXTENSION, DOCUMENTO_PROPIETARIO";
        sql += " FROM " + tablaDB;
        sql += " WHERE DOCUMENTO_PROPIETARIO =  " + documento;
        
        // Si se proporciona un documento, se añade la cláusula WHERE
        boolean buscarPorNombre = (nombre != null && !nombre.trim().isEmpty());
        if (buscarPorNombre) {
            sql += " AND Nombre = ?";
        }

        try {
            conn = ConexionOracle.getConnection(); // Asumo este método en tu clase Conexion
            ps = conn.prepareStatement(sql);

            // Asignar el parámetro si se busca por documento
            if (buscarPorNombre) {
                ps.setString(1, nombre.trim());
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                DatosPredio2 predio = new DatosPredio2();
                predio.setNUMERO_REGISTRO_ICA(rs.getString("NUMERO_REGISTRO_ICA"));
                predio.setNUMERO_PREDIAL(rs.getString("NUMERO_PREDIAL"));
                predio.setNOMBRE(rs.getString("NOMBRE")); // Nota: En sistemas reales, ¡NO expongas la clave!
                predio.setDEPARTAMENTO(rs.getString("DEPARTAMENTO"));
                predio.setMUNICIPIO(rs.getString("MUNICIPIO"));
                predio.setVEREDA(rs.getString("VEREDA"));
                predio.setNUMERO_ICA_LUGAR_PRODUCCION(rs.getString("NUMERO_ICA_LUGAR_PRODUCCION"));
                predio.setEXTENSION(rs.getString("EXTENSION"));
                predio.setDOCUMENTO_PROPIETARIO(rs.getString("DOCUMENTO_PROPIETARIO"));

                listaPredios.add(predio);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones adecuado en producción
        } finally {
            // Cierre de recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return listaPredios;
    }
    
}
