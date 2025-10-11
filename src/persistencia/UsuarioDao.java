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
import logica.DatosUsuario;

/**
 * Clase para el acceso a datos de usuarios (Funcionarios y Técnicos).
 */
public class UsuarioDao {
    

    /**
     * Busca la información de un usuario por su documento y tipo.
     * * @param documento El documento de identificación del usuario.
     * @param tipoUsuario El tipo de usuario ("Funcionario" o "Tecnico").
     * @return Un array de Object[] con la información del usuario, o null si no se encuentra.
     * El orden de los datos es (documento, usuario, clave, nombres, apellidos, telefono, correo, tarjetaProfesional).
     */
    public Object[] buscarPerfil(String documento, String tipoUsuario) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        // 🚨 Define la tabla y la columna de Tarjeta Profesional según el tipo
        String tabla = "";
        String campoTarjeta = "";
        
        if (tipoUsuario.equalsIgnoreCase("Funcionario ICA")) {
            tabla = "FUNCIONARIO_ICA"; 
            campoTarjeta = "NULL AS targeta_Profecional"; 
        }else if (tipoUsuario.equalsIgnoreCase("Productor")) {
            tabla = "PRODUCTOR"; 
            campoTarjeta = "NULL AS targeta_Profecional";
        }else if (tipoUsuario.equalsIgnoreCase("Propietario")) {
            tabla = "PROPIETARIO"; 
            campoTarjeta = "NULL AS targeta_Profecional";
        }else if (tipoUsuario.equalsIgnoreCase("Técnico")) {
            tabla = "TECNICO"; 
            campoTarjeta = "targeta_Profecional"; 
        } else {
            // Tipo de usuario no válido
            return null; 
        }

        // Consulta SQL genérica que se adapta al tipo de usuario
        String sql = "SELECT DOCUMENTO, NOMBRE_USUARIO, CLAVE, NOMBRE, APELLIDO, TELEFONO, EMAIL, " + campoTarjeta + 
                     " FROM " + tabla + " WHERE DOCUMENTO = ?";

        try {
            conn = ConexionOracle.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, documento);
            rs = ps.executeQuery();

            if (rs.next()) {
                // Si se encuentra el registro, extrae los datos
                Object[] datos = new Object[8];
                datos[0] = rs.getString("DOCUMENTO");
                datos[1] = rs.getString("NOMBRE_USUARIO");
                datos[2] = rs.getString("CLAVE"); // ⚠️ ¡Recuerda manejar la clave de forma segura!
                datos[3] = rs.getString("NOMBRE");
                datos[4] = rs.getString("APELLIDO");
                datos[5] = rs.getString("TELEFONO");
                datos[6] = rs.getString("EMAIL");
                
                // El campo tarjeta profesional solo aplica a Técnicos (es NULL para Funcionarios)
                if (tipoUsuario.equalsIgnoreCase("Tecnico")) {
                    datos[7] = rs.getString("TARJETA_PROFESIONAL");
                } else {
                    datos[7] = ""; // Cadena vacía para funcionarios
                }
                
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
    
    public boolean actualizarUsuario(DatosUsuario datos) {
        Connection conn = null;
        PreparedStatement ps = null;
        String tabla = "";
        
        // 1. Determinar la tabla según el tipo de usuario
        if (datos.tipoUsuario.equalsIgnoreCase("Funcionario ICA")) {
            tabla = "FUNCIONARIO_ICA"; 
        } else if (datos.tipoUsuario.equalsIgnoreCase("Productor")) {
            tabla = "PRODUCTOR"; 
        } else if (datos.tipoUsuario.equalsIgnoreCase("Propietario")) {
            tabla = "PROPIETARIO"; 
        } else if (datos.tipoUsuario.equalsIgnoreCase("Técnico")) {
            tabla = "TECNICO"; 
        } else {
            System.out.println("❌ Tipo de usuario no válido para actualizar: " + datos.tipoUsuario);
            return false; 
        }

        // 2. Construir la consulta SQL con SÓLO los campos editables
        // Se excluye DOCUMENTO y TARJETA_PROFESIONAL
        String sql = "UPDATE " + tabla + " SET " +
                     "NOMBRE_USUARIO = ?, " +
                     "CLAVE = ?, " + 
                     "NOMBRE = ?, " +
                     "APELLIDO = ?, " +
                     "TELEFONO = ?, " +
                     "EMAIL = ? " +
                     "WHERE DOCUMENTO = ?"; // El documento solo se usa para la condición

        try {
            // ⚠️ Asumo que tienes una clase llamada 'ConexionOracle' con el método 'getConnection()'
            conn = ConexionOracle.getConnection();
            ps = conn.prepareStatement(sql);
            
            int index = 1;
            // 3. Asignar los valores a los campos editables
            ps.setString(index++, datos.nombreUsuario);
            ps.setString(index++, datos.clave); // ¡Manejar la clave de forma segura (hasheada)!
            ps.setString(index++, datos.nombre);
            ps.setString(index++, datos.apellido);
            ps.setString(index++, datos.telefono);
            ps.setString(index++, datos.correo);

            // 4. Asignar el DOCUMENTO (cláusula WHERE)
            ps.setString(index, datos.documento); 

            // 5. Ejecutar la actualización
            int filasAfectadas = ps.executeUpdate();
            
            // 6. Verificar el resultado
            return filasAfectadas == 1;

        } catch (SQLException e) {
            System.out.println("💥 Error al actualizar usuario en la BD (" + datos.tipoUsuario + "): " + e.getMessage());
            return false;
        } finally {
            // Cerrar recursos
            try { if (ps != null) ps.close(); } catch (SQLException e) { /* Ignorar */ }
            // ⚠️ Asumo que tienes un método estático llamado 'cerrarConexion(Connection conn)'
            ConexionOracle.cerrarConexion(conn);
        }
    }
    
}
