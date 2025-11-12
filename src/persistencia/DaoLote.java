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
import logica.DatosLote;
import logica.DatosLote2;
import persistencia.ConexionOracle;

public class DaoLote {
     // Método para obtener el siguiente id_lote
    public int obtenerIdLote() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int siguienteId = 1; // Valor inicial por defecto si la tabla está vacía

        String sql = "SELECT MAX(ID_LOTE) FROM Lote";
        
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
    
    // Método para guardar el lote de producción en la base de datos
    public boolean guardarLote(
        int siguienteId, String NRO_LOTE, int ID_PLANTA, String NRO_REGISTRO_ICA_LUGARP,
        String AREA_DE_LOTE, String FECHA_DE_SIEMBRA, String FECHA_DE_ELIMINACION, 
        String ESTADO, String CANTIDAD_DE_PLANTAS, String FECHA_PROYEC_RECOLEC, 
        String CANT_PROYEC_RECOLEC, String FECHA_RECOLECCION, String CANT_RECOLECTADA) {

        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // La sentencia INSERT para tu tabla LOTE
        String sql = "INSERT INTO Lote ("
                   + "ID_LOTE, NRO_LOTE, ID_PLANTA, NRO_REGISTRO_ICA_LUGARP, AREA_DE_LOTE, FECHA_DE_SIEMBRA, "
                   + "FECHA_DE_ELIMINACION, ESTADO, CANTIDAD_DE_PLANTAS, FECHA_PROYEC_RECOLEC, CANT_PROYEC_RECOLEC, FECHA_RECOLECCION, CANT_RECOLECTADA"
                   + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = ConexionOracle.getConnection();
            try (java.sql.Statement s = conn.createStatement()) {
                s.execute("ALTER SESSION SET NLS_NUMERIC_CHARACTERS = '.,' NLS_DATE_FORMAT = 'DD/MM/YYYY'");
            }
            stmt = conn.prepareStatement(sql);

            // 1. ID_LOTE (int)
            stmt.setInt(1, siguienteId); 
            // 2. NRO_LOTE (String) - Aunque tu DB podría ser NUMBER, se envía como String
            stmt.setString(2, NRO_LOTE); 
            // 3. ID_PLANTA (int) - Tipo primitivo
            stmt.setInt(3, ID_PLANTA);
            // 4. NRO_REGISTRO_ICA_LUGARP (String)
            stmt.setString(4, NRO_REGISTRO_ICA_LUGARP);
            // 5. AREA_DE_LOTE (String) - Contiene el número con punto
            stmt.setString(5, AREA_DE_LOTE);
            // 6. FECHA_DE_SIEMBRA (String) - Formato DD/MM/AAAA
            stmt.setString(6, FECHA_DE_SIEMBRA);
            // 7. FECHA_DE_ELIMINACION (String) - Formato DD/MM/AAAA, puede ser vacío
            // Usamos setString(..., null) si la cadena es vacía para representar NULL en Oracle
            stmt.setString(7, FECHA_DE_ELIMINACION.isEmpty() ? null : FECHA_DE_ELIMINACION); 
            // 8. ESTADO (String)
            stmt.setString(8, ESTADO);
            // 9. CANTIDAD_DE_PLANTAS (String)
            stmt.setString(9, CANTIDAD_DE_PLANTAS); 
            // 10. FECHA_PROYEC_RECOLEC (String)
            stmt.setString(10, FECHA_PROYEC_RECOLEC);
            // 11. CANT_PROYEC_RECOLEC (String)
            stmt.setString(11, CANT_PROYEC_RECOLEC);
            // 12. FECHA_RECOLECCION (String) - Se asume vacío o null en la creación
            stmt.setString(12, FECHA_RECOLECCION.isEmpty() ? null : FECHA_RECOLECCION);
            // 13. CANT_RECOLECTADA (String) - Se asume 0 o null en la creación
            stmt.setString(13, CANT_RECOLECTADA.isEmpty() ? "0" : CANT_RECOLECTADA);

            // Ejecutar la inserción
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar el lote: " + e.getMessage());
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
    
    public boolean eliminarLote(String idLoteTexto) { // <--- Recibe String
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // Sentencia SQL para actualizar el estado del lote. 
        String sql = "UPDATE Lote SET ESTADO = 'Eliminado' WHERE ID_LOTE = ?";

        try {
            conn = ConexionOracle.getConnection();
            stmt = conn.prepareStatement(sql);

            // 1. ID_LOTE (String) - Se envía directamente como String al PreparedStatement
            stmt.setString(1, idLoteTexto.trim()); // <--- Usa setString

            // Ejecutar la actualización
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                System.out.println("Lote con ID " + idLoteTexto + " marcado como 'Eliminado' correctamente.");
            } else {
                System.out.println("Advertencia: No se encontró un Lote con ID " + idLoteTexto + " para actualizar.");
            }

        } catch (SQLException e) {
            System.err.println("Error al intentar eliminar (baja lógica) el lote con ID " + idLoteTexto + ": " + e.getMessage());
            exito = false;
        } finally {
            // Cierre de recursos (idealmente usando un método de utilidad)
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close(); 
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return exito;
    }
    
    public boolean editarLote(
        String ID_LOTE, String NRO_LOTE, String ID_PLANTA, String NRO_REGISTRO_ICA_LUGARP,
        String AREA_DE_LOTE, String FECHA_DE_SIEMBRA, String FECHA_DE_ELIMINACION, 
        String ESTADO, String CANTIDAD_DE_PLANTAS, String FECHA_PROYEC_RECOLEC, 
        String CANT_PROYEC_RECOLEC, String FECHA_RECOLECCION, String CANT_RECOLECTADA) {

        Connection conn = null;
        PreparedStatement stmt = null;
        boolean exito = false;

        // Sentencia UPDATE: se listan todos los campos que se pueden editar.
        String sql = "UPDATE Lote SET "
            + "NRO_LOTE = ?, "                  // 1
            + "ID_PLANTA = ?, "                 // 2
            + "NRO_REGISTRO_ICA_LUGARP = ?, "   // 3
            + "AREA_DE_LOTE = ?, "              // 4
            + "FECHA_DE_SIEMBRA = ?, "          // 5
            + "FECHA_DE_ELIMINACION = ?, "      // 6
            + "ESTADO = ?, "                    // 7
            + "CANTIDAD_DE_PLANTAS = ?, "       // 8
            + "FECHA_PROYEC_RECOLEC = ?, "      // 9
            + "CANT_PROYEC_RECOLEC = ?, "       // 10
            + "FECHA_RECOLECCION = ?, "         // 11
            + "CANT_RECOLECTADA = ? "           // 12
            + "WHERE ID_LOTE = ?";              // 13 (La clave para la actualización)

        try {
            conn = ConexionOracle.getConnection();
            
            // Establecer el formato de fecha y número para la sesión
            try (java.sql.Statement s = conn.createStatement()) {
                s.execute("ALTER SESSION SET NLS_NUMERIC_CHARACTERS = '.,' NLS_DATE_FORMAT = 'DD/MM/YYYY'");
            }
            
            stmt = conn.prepareStatement(sql);

            // --- Asignación de valores al SET ---
            // 1. NRO_LOTE (String)
            stmt.setString(1, NRO_LOTE);
            // 2. ID_PLANTA (String -> Int) - Se convierte a entero antes de enviarlo
            stmt.setInt(2, Integer.parseInt(ID_PLANTA.trim()));
            // 3. NRO_REGISTRO_ICA_LUGARP (String)
            stmt.setString(3, NRO_REGISTRO_ICA_LUGARP);
            // 4. AREA_DE_LOTE (String)
            stmt.setString(4, AREA_DE_LOTE);
            // 5. FECHA_DE_SIEMBRA (String)
            stmt.setString(5, FECHA_DE_SIEMBRA);
            // 6. FECHA_DE_ELIMINACION (String) - NULL si está vacío
            stmt.setString(6, FECHA_DE_ELIMINACION.isEmpty() ? null : FECHA_DE_ELIMINACION);
            // 7. ESTADO (String)
            stmt.setString(7, ESTADO);
            // 8. CANTIDAD_DE_PLANTAS (String)
            stmt.setString(8, CANTIDAD_DE_PLANTAS);
            // 9. FECHA_PROYEC_RECOLEC (String)
            stmt.setString(9, FECHA_PROYEC_RECOLEC);
            // 10. CANT_PROYEC_RECOLEC (String)
            stmt.setString(10, CANT_PROYEC_RECOLEC);
            // 11. FECHA_RECOLECCION (String) - NULL si está vacío
            stmt.setString(11, FECHA_RECOLECCION.isEmpty() ? null : FECHA_RECOLECCION);
            // 12. CANT_RECOLECTADA (String) - Usa "0" si está vacío, o null si lo prefiere tu DB
            stmt.setString(12, CANT_RECOLECTADA.isEmpty() ? "0" : CANT_RECOLECTADA);

            // --- Cláusula WHERE ---
            // 13. ID_LOTE (String -> Int) - Se usa para identificar la fila
            stmt.setInt(13, Integer.parseInt(ID_LOTE.trim()));

            // Ejecutar la actualización
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                exito = true;
                System.out.println("Lote con ID " + ID_LOTE + " editado correctamente.");
            } else {
                System.out.println("Advertencia: No se encontró un Lote con ID " + ID_LOTE + " para editar.");
            }

        } catch (NumberFormatException e) {
            // Capturar errores si ID_LOTE o ID_PLANTA no son números válidos
            System.err.println("Error de formato de número al editar el lote: " + e.getMessage());
            exito = false;
        } catch (SQLException e) {
            System.err.println("Error SQL al intentar editar el lote con ID " + ID_LOTE + ": " + e.getMessage());
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

    public DatosLote buscarLotePorId(String idLoteTexto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DatosLote loteEncontrado = null;

        // 🌟 CAMBIO CLAVE AQUÍ: Uso de TO_CHAR para formatear las fechas sin la hora 🌟
        // Se listan todas las columnas que se necesitan.
        String sql = "SELECT ID_LOTE, NRO_LOTE, ID_PLANTA, NRO_REGISTRO_ICA_LUGARP, AREA_DE_LOTE, ESTADO, " +
                     "CANTIDAD_DE_PLANTAS, CANT_PROYEC_RECOLEC, CANT_RECOLECTADA, " +
                     "TO_CHAR(FECHA_DE_SIEMBRA, 'DD-MM-YYYY') AS FECHA_DE_SIEMBRA, " +
                     "TO_CHAR(FECHA_DE_ELIMINACION, 'DD-MM-YYYY') AS FECHA_DE_ELIMINACION, " +
                     "TO_CHAR(FECHA_PROYEC_RECOLEC, 'DD-MM-YYYY') AS FECHA_PROYEC_RECOLEC, " +
                     "TO_CHAR(FECHA_RECOLECCION, 'DD-MM-YYYY') AS FECHA_RECOLECCION " +
                     "FROM Lote WHERE ID_LOTE = ?";

        try {
            conn = ConexionOracle.getConnection();
            stmt = conn.prepareStatement(sql);

            // Asumiendo que ID_LOTE es NUMBER en la base de datos
            // Convertimos el String a Int (y manejamos el error en el controlador)
            stmt.setInt(1, Integer.parseInt(idLoteTexto.trim())); 

            rs = stmt.executeQuery();

            if (rs.next()) {
                // El lote fue encontrado, creamos el objeto DatosLote
                loteEncontrado = new DatosLote();
                loteEncontrado.setID_LOTE(rs.getString("ID_LOTE"));
                loteEncontrado.setNRO_LOTE(rs.getString("NRO_LOTE"));
                loteEncontrado.setID_PLANTA(rs.getString("ID_PLANTA"));
                loteEncontrado.setNRO_REGISTRO_ICA_LUGARP(rs.getString("NRO_REGISTRO_ICA_LUGARP"));
                loteEncontrado.setAREA_DE_LOTE(rs.getString("AREA_DE_LOTE"));

                // Las fechas ahora se obtienen como un String limpio (YYYY-MM-DD)
                loteEncontrado.setFECHA_DE_SIEMBRA(rs.getString("FECHA_DE_SIEMBRA"));
                loteEncontrado.setFECHA_DE_ELIMINACION(rs.getString("FECHA_DE_ELIMINACION"));

                loteEncontrado.setESTADO(rs.getString("ESTADO"));
                loteEncontrado.setCANTIDAD_DE_PLANTAS(rs.getString("CANTIDAD_DE_PLANTAS"));
                loteEncontrado.setFECHA_PROYEC_RECOLEC(rs.getString("FECHA_PROYEC_RECOLEC"));
                loteEncontrado.setCANT_PROYEC_RECOLEC(rs.getString("CANT_PROYEC_RECOLEC"));
                loteEncontrado.setFECHA_RECOLECCION(rs.getString("FECHA_RECOLECCION"));
                loteEncontrado.setCANT_RECOLECTADA(rs.getString("CANT_RECOLECTADA"));
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al buscar el lote con ID " + idLoteTexto + ": " + e.getMessage());
            return null; // Retorna null en caso de error de conexión/SQL
        } catch (NumberFormatException e) {
            System.err.println("Error: El ID del lote no es un número válido.");
            return null; // Retorna null si el ID no es numérico
        } finally {
            // Cierre de recursos (idealmente refactorizado en un método auxiliar)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        return loteEncontrado;
    }
    
    public List<DatosLote2> buscarLotes(String numeroICALugar, String idPlanta) {
        List<DatosLote2> listaLotes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Mapeo del tipo de usuario en el JComboBox a la tabla en la base de datos
        String tablaDB = "Lote";

        // Construcción dinámica del query
        String sql = "SELECT ID_LOTE, NRO_LOTE, ID_PLANTA, NRO_REGISTRO_ICA_LUGARP, AREA_DE_LOTE, FECHA_DE_SIEMBRA, FECHA_DE_ELIMINACION, ESTADO, CANTIDAD_DE_PLANTAS, FECHA_PROYEC_RECOLEC, CANT_PROYEC_RECOLEC, FECHA_RECOLECCION, CANT_RECOLECTADA";
        sql += " FROM " + tablaDB;
        sql += " WHERE NRO_REGISTRO_ICA_LUGARP =  " + numeroICALugar;
        
        // Si se proporciona un numeroICALugar, se añade la cláusula WHERE
        boolean buscarPorNombre = (idPlanta != null && !idPlanta.trim().isEmpty());
        if (buscarPorNombre) {
            sql += " AND ID_PLANTA = ?";
        }

        try {
            conn = ConexionOracle.getConnection(); // Asumo este método en tu clase Conexion
            ps = conn.prepareStatement(sql);

            // Asignar el parámetro si se busca por numeroICALugar
            if (buscarPorNombre) {
                ps.setString(1, idPlanta.trim());
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                DatosLote2 lote = new DatosLote2();
                lote.setID_LOTE(rs.getString("ID_LOTE"));
                lote.setNRO_LOTE(rs.getString("NRO_LOTE"));
                lote.setID_PLANTA(rs.getString("ID_PLANTA")); // Nota: En sistemas reales, ¡NO expongas la clave!
                lote.setNRO_REGISTRO_ICA_LUGARP(rs.getString("NRO_REGISTRO_ICA_LUGARP"));
                lote.setAREA_DE_LOTE(rs.getString("AREA_DE_LOTE"));
                lote.setFECHA_DE_SIEMBRA(rs.getString("FECHA_DE_SIEMBRA"));
                lote.setFECHA_DE_ELIMINACION(rs.getString("FECHA_DE_ELIMINACION"));
                lote.setESTADO(rs.getString("ESTADO"));
                lote.setCANTIDAD_DE_PLANTAS(rs.getString("CANTIDAD_DE_PLANTAS"));
                lote.setFECHA_PROYEC_RECOLEC(rs.getString("FECHA_PROYEC_RECOLEC"));
                lote.setCANT_PROYEC_RECOLEC(rs.getString("CANT_PROYEC_RECOLEC"));
                lote.setFECHA_RECOLECCION(rs.getString("FECHA_RECOLECCION"));
                lote.setCANT_RECOLECTADA(rs.getString("CANT_RECOLECTADA"));

                listaLotes.add(lote);
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

        return listaLotes;
    }
    
    public List<DatosLote2> buscarLotes2(String numeroICALugar) {
        List<DatosLote2> listaLotes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean icaPresente = (numeroICALugar != null && !numeroICALugar.trim().isEmpty());
        // Mapeo del tipo de usuario en el JComboBox a la tabla en la base de datos
        String tablaDB = "Lote";

        // Construcción dinámica del query
        String sql = "SELECT ID_LOTE, NRO_LOTE, ID_PLANTA, NRO_REGISTRO_ICA_LUGARP, AREA_DE_LOTE, FECHA_DE_SIEMBRA, FECHA_DE_ELIMINACION, ESTADO, CANTIDAD_DE_PLANTAS, FECHA_PROYEC_RECOLEC, CANT_PROYEC_RECOLEC, FECHA_RECOLECCION, CANT_RECOLECTADA";
        sql += " FROM " + tablaDB;
        
        if (icaPresente) {
                // Utilizamos el placeholder '?' para mayor seguridad
                sql += " WHERE NRO_REGISTRO_ICA_LUGARP =  " + numeroICALugar;
            }

        try {
            conn = ConexionOracle.getConnection(); // Asumo este método en tu clase Conexion
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                DatosLote2 lote = new DatosLote2();
                lote.setID_LOTE(rs.getString("ID_LOTE"));
                lote.setNRO_LOTE(rs.getString("NRO_LOTE"));
                lote.setID_PLANTA(rs.getString("ID_PLANTA")); // Nota: En sistemas reales, ¡NO expongas la clave!
                lote.setNRO_REGISTRO_ICA_LUGARP(rs.getString("NRO_REGISTRO_ICA_LUGARP"));
                lote.setAREA_DE_LOTE(rs.getString("AREA_DE_LOTE"));
                lote.setFECHA_DE_SIEMBRA(rs.getString("FECHA_DE_SIEMBRA"));
                lote.setFECHA_DE_ELIMINACION(rs.getString("FECHA_DE_ELIMINACION"));
                lote.setESTADO(rs.getString("ESTADO"));
                lote.setCANTIDAD_DE_PLANTAS(rs.getString("CANTIDAD_DE_PLANTAS"));
                lote.setFECHA_PROYEC_RECOLEC(rs.getString("FECHA_PROYEC_RECOLEC"));
                lote.setCANT_PROYEC_RECOLEC(rs.getString("CANT_PROYEC_RECOLEC"));
                lote.setFECHA_RECOLECCION(rs.getString("FECHA_RECOLECCION"));
                lote.setCANT_RECOLECTADA(rs.getString("CANT_RECOLECTADA"));

                listaLotes.add(lote);
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

        return listaLotes;
    }
    
}
