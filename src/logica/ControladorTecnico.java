/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoTecnico;
// import java.util.regex.Pattern; // Ya importado

public class ControladorTecnico extends ControladorUser1 {

    private DaoTecnico daoTecnico = new DaoTecnico();

    /**
     * Valida la información completa de un usuario Técnico y lo guarda.
     *
     * @return null si el registro (validación + guardado) fue exitoso, o un mensaje de error.
     */
    public String validarRegistroTecnico(long documento, String nomUser,
                                         String clave, String nombre, String apellido,
                                         long telefono, String correo, String tarjetaProfesionalStr) {

        // 1. Validaciones de Formato (asumiendo que las heredas o están en esta clase)
        // Llama a las validaciones heredadas si aplican (ej. validarCorreo, validarNombreUsuario, etc.)
        
        // Simulo una validación de ejemplo para el nombre de usuario y correo:
        // String errorBase = validarDatosBase(nomUser, correo); 
        // if (errorBase != null) { return errorBase; }
        
        // 2. Validación Específica para la Tarjeta Profesional (debe ser 10 dígitos)
        String errorTarjeta = validarTarjetaProfesional(tarjetaProfesionalStr);
        if (errorTarjeta != null) {
            return errorTarjeta;
        }
        
        // 3. Conversión a long
        long tarjetaProfesional;
        try {
            tarjetaProfesional = Long.parseLong(tarjetaProfesionalStr);
        } catch (NumberFormatException e) {
            return "Error interno: La Tarjeta Profesional no se pudo convertir a número.";
        }

        // 4. Guardar en la tabla TECNICO
        boolean guardadoExitoso = daoTecnico.guardarTecnico(
            documento, nomUser, clave, nombre, apellido, telefono, correo, tarjetaProfesional);

        if (guardadoExitoso) {
            return null; // Éxito: Registro completo
        } else {
            // Esto manejará errores como documento duplicado o tarjeta profesional duplicada.
            return "Error al guardar los datos del Técnico. Verifique que el Documento y la Tarjeta Profesional no estén registrados.";
        }
    }

    /**
     * Valida que la Tarjeta Profesional contenga exactamente 10 dígitos.
     * NOTA: La vista ya pre-valida que sea numérico. Aquí verificamos la longitud.
     */
    private String validarTarjetaProfesional(String tarjetaProfesional) {
        if (!tarjetaProfesional.matches("\\d{10}")) {
            return "La Tarjeta Profesional debe contener exactamente 10 dígitos numéricos.";
        }
        return null;
    }
}
