/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.UsuarioDao;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ControladorEditPerfil {
    
    private UsuarioDao usuDAO;

    public ControladorEditPerfil() {
        this.usuDAO = new UsuarioDao();
    }
    
    /**
     * Llama al DAO para obtener la información del perfil.
     * * @param documento Documento del usuario.
     * @param tipoUsuario Tipo de usuario ("Funcionario" o "Tecnico").
     * @return El array de datos del DAO, o null.
     */
    public Object[] traerDatosPerfil(String documento, String tipoUsuario) {
        return usuDAO.buscarPerfil(documento, tipoUsuario);
    }
    
    //métodos de validación nuevos datos
    public String validarNumeros(String telefono) {
        
        String telStr = String.valueOf(telefono);

        if (telStr.length() != 10) {
            return "El Teléfono debe contener exactamente 10 dígitos.";
        }
        // No necesitamos verificar si son números porque al pasarse como 'long' ya lo son
        return null;
    }

    /**
     * Valida que el nombre de usuario inicie con una letra.
     * @param nomUser El nombre de usuario.
     * @return null si es válido, o un mensaje de error.
     */
    public String validarNombreUsuario(String nomUser) {
        if (!nomUser.matches("^[a-zA-Z].*")) {
            return "El Nombre de Usuario debe iniciar con una letra.";
        }
        return null;
    }

    /**
     * Valida la clave: al menos 5 caracteres, con al menos 1 letra y 1 número.
     * @param clave La contraseña.
     * @return null si es válida, o un mensaje de error.
     */
    public String validarClave(String clave) {
        if (clave.length() < 5) {
            return "La Contraseña debe tener al menos 5 caracteres.";
        }

        // Expresión regular para verificar al menos una letra y al menos un número
        // (?=.*[a-zA-Z]) -> debe contener una letra
        // (?=.*\d) -> debe contener un dígito
        Pattern patronClave = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d).{5,}$");
        Matcher matcherClave = patronClave.matcher(clave);

        if (!matcherClave.matches()) {
            return "La Contraseña debe tener al menos 5 caracteres, incluyendo al menos una letra y un número.";
        }
        return null;
    }

    /**
     * Valida que el nombre y apellido solo contengan letras y espacios.
     * @param nombre El nombre.
     * @param apellido El apellido.
     * @return null si son válidos, o un mensaje de error.
     */
    public String validarNombreYApellido(String nombre, String apellido) {
        // Permite letras (incluyendo tildes y ñ) y espacios
        String patronLetras = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"; 
        
        if (!nombre.matches(patronLetras)) {
            return "El Nombre solo debe contener letras.";
        }
        if (!apellido.matches(patronLetras)) {
            return "El Apellido solo debe contener letras.";
        }
        return null;
    }
    
}
