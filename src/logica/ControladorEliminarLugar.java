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
public class ControladorEliminarLugar {
    
    private DaoLugarProduccion daoLugarProduccion = new DaoLugarProduccion();
    
    /**
     * Intenta eliminar un usuario según su tipo y documento.
     * @param tipoUsuario El tipo de usuario a eliminar (ej: "Funcionario").
     * @param documento El número de documento del usuario.
     * @return Un mensaje de éxito o error.
     */
    public String eliminarLugar(String numero_ica) {
        // --- 1. VALIDACIONES ---
        String errorValidacion = validarRegistro(numero_ica);
        if (errorValidacion != null) {
            return "❌ Error de validación: " + errorValidacion;
        }

        // --- 2. ELIMINACIÓN ---
        boolean exitoEliminacion = false;
        String mensaje = "";
        String resultadoEliminacion = "";
        
        resultadoEliminacion = daoLugarProduccion.eliminarLugar(numero_ica);

                switch (resultadoEliminacion) {
                    case "EXITO":
                        mensaje = "✅ El lugar de producción con número de registro ICA " + numero_ica + " ha sido **eliminado exitosamente**.";
                        break;
                    case "ERROR_FK":
                        // **Este es el mensaje clave que solicitaste**
                        mensaje = "🚫 **Error de Restricción (FK):** El lugar de producción con número de registro ICA " + numero_ica + " no se puede eliminar **porque tiene predios asociados**. Por favor, elimine primero todos sus registros.";
                        break;
                    case "NO_ENCONTRADO":
                        mensaje = "⚠️ No se pudo eliminar El lugar de producción con número de registro ICA " + numero_ica + " porque no se encontró en la base de datos.";
                        break;
                    default:
                        // Para otros errores, como problemas de conexión o SQL genérico
                        mensaje = "❌ Error inesperado al intentar eliminar el Lugar de producción. Detalle: " + resultadoEliminacion;
                        break;
                }

        return mensaje;
    }
    
    // --- MÉTODOS EXISTENTES ---

    public String validarRegistro(String numero_ica){
        // En este controlador, la validación principal es del formato del documento, 
        // ya que el DAO maneja si el documento existe o no.
        String errorNumerico = validarNumeros(numero_ica);
        if (errorNumerico != null) return errorNumerico;
        
        return null;
    }
    
    public String validarNumeros(String numero_ica) {
        if (numero_ica == null || numero_ica.trim().isEmpty()) {
            return "El Documento no puede estar vacío.";
        }
        
        String doc = numero_ica.trim();
        
        // Una validación adicional y más robusta sería asegurar que todos son solo dígitos
        if (!doc.matches("\\d+")) {
            return "El Documento solo debe contener dígitos.";
        }
        
        return null;
    }
    
}
