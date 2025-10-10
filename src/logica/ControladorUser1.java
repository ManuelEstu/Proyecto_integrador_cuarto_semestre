/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoFuncionario;
import persistencia.DaoProductor; // NUEVA IMPORTACIÓN
import persistencia.DaoPropietario; // NUEVA IMPORTACIÓN
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ControladorUser1 {
    
    private DaoFuncionario daoFuncionario = new DaoFuncionario();
    private DaoProductor daoProductor = new DaoProductor();    // NUEVA INSTANCIA
    private DaoPropietario daoPropietario = new DaoPropietario(); // NUEVA INSTANCIA

    // Método principal para orquestar validaciones y el guardado
    public String validarRegistro(String tipoUsuario, long documento, String nomUser,
            String clave, String nombre, String apellido, long telefono, String correo) {

        // --- VALIDACIONES (Se mantienen como estaban: consistentes para todos) ---
        String errorNumerico = validarNumeros(documento, telefono);
        if (errorNumerico != null) return errorNumerico;

        String errorNomUser = validarNombreUsuario(nomUser);
        if (errorNomUser != null) return errorNomUser;

        String errorClave = validarClave(clave);
        if (errorClave != null) return errorClave;

        String errorNombreApellido = validarNombreYApellido(nombre, apellido);
        if (errorNombreApellido != null) return errorNombreApellido;
        
        if (!correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            return "El formato del Correo Electrónico no es válido.";
        }

        // --- GUARDADO DE DATOS (Centralizado según el tipo de usuario) ---
        
        boolean exitoGuardado = false;
        String tabla = "";

        switch (tipoUsuario) {
            case "Funcionario ICA":
                exitoGuardado = daoFuncionario.guardarFuncionario(documento, nomUser, clave, nombre, apellido, telefono, correo);
                tabla = "Funcionario_ICA";
                break;
            case "Productor":
                exitoGuardado = daoProductor.guardarProductor(documento, nomUser, clave, nombre, apellido, telefono, correo);
                tabla = "Productor";
                break;
            case "Propietario":
                exitoGuardado = daoPropietario.guardarPropietario(documento, nomUser, clave, nombre, apellido, telefono, correo);
                tabla = "Propietario";
                break;
            default:
                // Esto no debería suceder si la vista lo gestiona bien
                return "Tipo de usuario no soportado para el registro base."; 
        }

        // 5. Manejo del resultado del guardado
        if (exitoGuardado) {
            return null; // Éxito: Validación OK y Guardado OK
        } else {
            // Error general de guardado, probablemente por clave única duplicada.
            return "Fallo al registrar en la tabla " + tabla + " (Posible Documento/Usuario duplicado).";
        }
    }

    // --- Métodos de validación publicos y específicos ---

    /**
     * Valida que el documento y el teléfono sean exactamente 10 dígitos.
     * @param documento El documento de identidad (long).
     * @param telefono El número de teléfono (long).
     * @return null si son válidos, o un mensaje de error.
     */
    public String validarNumeros(long documento, long telefono) {
        String docStr = String.valueOf(documento);
        String telStr = String.valueOf(telefono);

        if (docStr.length() != 10) {
            return "El Documento debe contener exactamente 10 dígitos.";
        }
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
