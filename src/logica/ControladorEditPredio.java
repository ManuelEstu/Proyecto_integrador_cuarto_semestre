/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoPredio;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ControladorEditPredio {
    private DaoPredio preDAO;

    public ControladorEditPredio() {
        this.preDAO = new DaoPredio();
    }
    
     public Object[] traerDatosLugar(String documento, String numica) {
        return preDAO.buscarLugar(documento, numica);
    }
    
     // Método para llamar al DAO y realizar la actualización
    public boolean actualizarDatos(DatosPredio datos) {
        // El método actualizarUsuario ya maneja la lógica para omitir la tarjeta 
        // profesional y el documento (solo usa el documento para el WHERE).
        return preDAO.actualizarLugar(datos);
    }
    
    //métodos de validación nuevos datos
    public String validarNumeros(String numero_ica_lugar_produccion, String extension) {
        
        String numLugStr = String.valueOf(numero_ica_lugar_produccion);

        if (numLugStr.length() != 6) {
            return "El número de registro ICA del lugar de producción debe contener exactamente 6 dígitos.";
        }
        // No necesitamos verificar si son números porque al pasarse como 'long' ya lo son
        return null;
    }
    
    public String validarNombre(String nombre) {
        // Permite letras (incluyendo tildes y ñ) y espacios
        String patronLetras = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"; 
        
        if (!nombre.matches(patronLetras)) {
            return "El Nombre solo debe contener letras.";
        }
        return null;
    }
}
