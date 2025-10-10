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
import persistencia.DaoTecnico;
public class ControladorEliminarUser {
    
    private DaoFuncionario daoFuncionario = new DaoFuncionario();
    private DaoProductor daoProductor = new DaoProductor();    
    private DaoPropietario daoPropietario = new DaoPropietario();
    private DaoTecnico daoTecnico = new DaoTecnico();
    
    /**
     * Intenta eliminar un usuario según su tipo y documento.
     * @param tipoUsuario El tipo de usuario a eliminar (ej: "Funcionario").
     * @param documento El número de documento del usuario.
     * @return Un mensaje de éxito o error.
     */
    public String eliminarUsuario(String tipoUsuario, String documento) {
        // --- 1. VALIDACIONES ---
        String errorValidacion = validarRegistro(documento);
        if (errorValidacion != null) {
            return "❌ Error de validación: " + errorValidacion;
        }

        // --- 2. ELIMINACIÓN ---
        boolean exitoEliminacion = false;
        String mensaje = "";
        String resultadoEliminacion = "";
        // Normalizar el tipo de usuario para la comparación
        String tipo = tipoUsuario.trim().toLowerCase(); 

        // Enlazar la eliminación al DAO correspondiente
        switch (tipo) {
            case "funcionario ica":
                exitoEliminacion = daoFuncionario.eliminarFuncionario(documento);
                if (exitoEliminacion) {
                    mensaje = "✅ El Funcionario ICA con documento " + documento + " ha sido **eliminado exitosamente**.";
                } else {
                    // El DAO ya maneja el mensaje en consola, pero retornamos un mensaje de error al UI
                    mensaje = "⚠️ No se pudo eliminar el Funcionario ICA con documento " + documento + ". (Puede que no exista).";
                }
                break;
            case "productor":
                // El DAO ahora devuelve un String con el código de resultado
                resultadoEliminacion = daoProductor.eliminarProductor(documento);

                switch (resultadoEliminacion) {
                    case "EXITO":
                        mensaje = "✅ El Productor con documento " + documento + " ha sido **eliminado exitosamente**.";
                        break;
                    case "ERROR_FK":
                        // **Este es el mensaje clave que solicitaste**
                        mensaje = "🚫 **Error de Restricción (FK):** El Productor con documento " + documento + " no se puede eliminar **porque tiene Lugares de Producción asociados**. Por favor, elimine primero todos sus registros de Lugar de Producción.";
                        break;
                    case "NO_ENCONTRADO":
                        mensaje = "⚠️ No se pudo eliminar el Productor con documento " + documento + " porque no se encontró en la base de datos.";
                        break;
                    default:
                        // Para otros errores, como problemas de conexión o SQL genérico
                        mensaje = "❌ Error inesperado al intentar eliminar el Productor. Detalle: " + resultadoEliminacion;
                        break;
                }
                break;
            // Contenido de ControladorEliminarUser.java (solo el case "propietario")

            case "propietario":
                resultadoEliminacion = daoPropietario.eliminarPropietario(documento);

                switch (resultadoEliminacion) {
                    case "EXITO":
                        // CORREGIDO: "Propietario"
                        mensaje = "✅ El Propietario con documento " + documento + " ha sido **eliminado exitosamente**.";
                        break;
                    case "ERROR_FK":
                        // CORREGIDO: "Propietario" y el motivo de la FK (asumimos "predios")
                        mensaje = "🚫 **Error de Restricción (FK):** El Propietario con documento " + documento + " no se puede eliminar **porque tiene Predios asociados**. Por favor, elimine primero todos sus registros de Predios asociados.";
                        break;
                    case "NO_ENCONTRADO":
                        // CORREGIDO: "Propietario"
                        mensaje = "⚠️ No se pudo eliminar el Propietario con documento " + documento + " porque no se encontró en la base de datos.";
                        break;
                    default:
                        // Para otros errores, como problemas de conexión o SQL genérico
                        mensaje = "❌ Error inesperado al intentar eliminar el Propietario. Detalle: " + resultadoEliminacion;
                        break;
                }
                break; // Fin del case "propietario"
            case "técnico":
                exitoEliminacion = daoTecnico.eliminarTecnico(documento);
                if (exitoEliminacion) {
                    mensaje = "✅ El Técnico con documento " + documento + " ha sido **eliminado exitosamente**.";
                } else {
                    // El DAO ya maneja el mensaje en consola, pero retornamos un mensaje de error al UI
                    mensaje = "⚠️ No se pudo eliminar el Técnico con documento " + documento + ". (Puede que no exista).";
                }
                break;
            default:
                mensaje = "❌ Error: El tipo de usuario **" + tipoUsuario + "** no es válido para la eliminación.";
                break;
        }

        return mensaje;
    }
    
    // --- MÉTODOS EXISTENTES ---

    public String validarRegistro(String documento){
        // En este controlador, la validación principal es del formato del documento, 
        // ya que el DAO maneja si el documento existe o no.
        String errorNumerico = validarNumeros(documento);
        if (errorNumerico != null) return errorNumerico;
        
        return null;
    }
    
    public String validarNumeros(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return "El Documento no puede estar vacío.";
        }
        
        String doc = documento.trim();
        
        // Una validación adicional y más robusta sería asegurar que todos son solo dígitos
        if (!doc.matches("\\d+")) {
            return "El Documento solo debe contener dígitos.";
        }
        
        return null;
    }
    
}
