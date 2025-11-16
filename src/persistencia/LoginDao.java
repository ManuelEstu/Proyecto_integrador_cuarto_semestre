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

/**
 * Clase DAO para validar los datos de inicio de sesión
 * y devolver el rol correcto del usuario.
 */
public class LoginDao {

    private Connection conexionRol; // guarda la conexión activa del usuario logueado

    public Connection getConexionRol() {
        return conexionRol;
    }

    public String validarUsuario(String correo, String contrasena) {
        String rol = null;

        // ✅ Paso 1: Usamos ADMINISTRADOR solo para verificar las credenciales
        try (Connection conn = CConexion.getConnectionPorUsuario("proyectoica", "proyectoica")) {

            // 🔹 1️⃣ Propietario
            String sqlProp = "SELECT COUNT(*) FROM PROPIETARIO WHERE documento = ? AND clave = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlProp)) {
                ps.setString(1, correo);
                ps.setString(2, contrasena);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        rol = "propietario";
                    }
                }
            }

            // 🔹 2️⃣ Técnico
            if (rol == null) {
                String sqlTec = "SELECT COUNT(*) FROM tecnico WHERE documento = ? AND clave = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlTec)) {
                    ps.setString(1, correo);
                    ps.setString(2, contrasena);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            rol = "tecnico";
                        }
                    }
                }
            }

            // 🔹 3️⃣ Productor
            if (rol == null) {
                String sqlProd = "SELECT COUNT(*) FROM productor WHERE documento = ? AND clave = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlProd)) {
                    ps.setString(1, correo);
                    ps.setString(2, contrasena);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            rol = "productor";
                        }
                    }
                }
            }

            // 🔹 4️⃣ Administrador
            if (rol == null) {
                String sqlAdm = "SELECT COUNT(*) FROM funcionario_ica WHERE documento = ? AND clave = ?";
                try (PreparedStatement ps = conn.prepareStatement(sqlAdm)) {
                    ps.setString(1, correo);
                    ps.setString(2, contrasena);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            rol = "administrador";
                        }
                    }
                }
            }

            // ✅ Paso 2: si se encontró el rol, abrir la conexión con ese usuario Oracle
            if (rol != null) {
                conexionRol = CConexion.getConnectionPorRol(rol);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rol;
    }
}
