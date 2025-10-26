/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoLugarProduccion;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ControladorEditLugar {
    private DaoLugarProduccion lugDAO;

    public ControladorEditLugar() {
        this.lugDAO = new DaoLugarProduccion();
    }
    
    public Object[] traerDatosLugar(String documento, String numica) {
        return lugDAO.buscarLugar(documento, numica);
    }
    
     // Método para llamar al DAO y realizar la actualización
    public boolean actualizarDatos(DatosLugar datos) {
        // El método actualizarUsuario ya maneja la lógica para omitir la tarjeta 
        // profesional y el documento (solo usa el documento para el WHERE).
        return lugDAO.actualizarLugar(datos);
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
    
    public String validarNombreYNombreEmpresa(String nombre, String nombreE) {
        // Permite letras (incluyendo tildes y ñ) y espacios
        String patronLetras = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"; 
        
        if (!nombre.matches(patronLetras)) {
            return "El Nombre solo debe contener letras.";
        }
        if (!nombreE.matches(patronLetras)) {
            return "El Apellido solo debe contener letras.";
        }
        return null;
    }
    
}
