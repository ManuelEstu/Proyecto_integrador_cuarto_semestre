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

public class ControladorEliminarPredio {
    private DaoPredio daoPredio = new DaoPredio();
    
    /**
     * Intenta eliminar un Predio utilizando su número de registro ICA.
     * El nombre del método se corrige a 'eliminarPredio' para ser más específico.
     */
    public String eliminarPredio(String numero_ica) { // Cambié el nombre del método a 'eliminarPredio'
        // --- 1. VALIDACIONES ---
        String errorValidacion = validarRegistro(numero_ica);
        if (errorValidacion != null) {
            return "❌ Error de validación: " + errorValidacion;
        }

        // --- 2. ELIMINACIÓN ---
        String mensaje = "";

        // Enlazar la eliminación al DAO correspondiente. El DAO retorna un String con el resultado.
        String resultadoEliminacion = daoPredio.eliminarPredio(numero_ica);
        
        // CORRECCIÓN: Se verifica el String de resultado del DAO.
        if (resultadoEliminacion.equals("EXITO")) {
            mensaje = "✅ El Predio con número de registro ICA " + numero_ica + " ha sido **eliminado exitosamente**.";
        } else if (resultadoEliminacion.equals("NO_ENCONTRADO")) {
            mensaje = "⚠️ No se pudo eliminar el predio con número de registro ICA " + numero_ica + ". **No existe** en la base de datos.";
        } else { // Asume que es "ERROR_DESCONOCIDO" o cualquier otra falla.
            mensaje = "💥 Error al intentar eliminar el predio con número de registro ICA " + numero_ica + ". Consulte el log.";
        }

        return mensaje;
    }
    
    // --- MÉTODOS EXISTENTES (Se mantienen igual) ---

    public String validarRegistro(String numero_ica){
        // En este controlador, la validación principal es del formato del documento, 
        // ya que el DAO maneja si el documento existe o no.
        String errorNumerico = validarNumeros(numero_ica);
        if (errorNumerico != null) return errorNumerico;
        
        return null;
    }
    
    public String validarNumeros(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return "El número de registro ICA no puede estar vacío.";
        }
        
        String doc = documento.trim();
        
        // Una validación adicional y más robusta sería asegurar que todos son solo dígitos
        if (!doc.matches("\\d+")) {
            return "El Número de registro ICA solo debe contener dígitos.";
        }
        
        return null;
    }
}
