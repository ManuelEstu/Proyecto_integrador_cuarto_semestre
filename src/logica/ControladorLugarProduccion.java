/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import persistencia.DaoLugarProduccion;
// Asumo que tu controlador está en un paquete 'controlador'

public class ControladorLugarProduccion {
    
    private DaoLugarProduccion DaoLugar = new DaoLugarProduccion();
    
    public String validarRegistro(String numeroPredial, String nombreLugar, String empresa,
                                String telefonoEmpresa, String vereda, String documentoDueno, 
                                String departamento, String municipio) {
        
        // --- 1. VALIDACIONES ---
        // (Se mantienen como estaban, pero ajusté la validación de Predial a 30 dígitos según tu tabla)
        String errorNumerico = validarNumeros(numeroPredial, telefonoEmpresa, documentoDueno);
        if (errorNumerico != null) return errorNumerico;

        String errorVereda = validarVereda(vereda);
        if (errorVereda != null) return errorVereda;

        String errorNombres = validarNombreLugarYEmpresa(nombreLugar, empresa);
        if (errorNombres != null) return errorNombres;
        
        // --- 2. GENERACIÓN DEL ICA Y GUARDADO DE DATOS ---
        
        // 2.1. Generar el número de registro ICA incremental
        int nuevoRegistroICA = DaoLugar.obtenerSiguienteICA();
        
        if (nuevoRegistroICA == 0) {
            return "Error grave al obtener el número de registro ICA. Intente de nuevo.";
        }
        
        // 2.2. Intentar el guardado
        boolean exitoGuardado = DaoLugar.guardarLugar(
            nuevoRegistroICA,
            numeroPredial, 
            nombreLugar, 
            empresa,
            telefonoEmpresa, 
            vereda, 
            documentoDueno, 
            departamento, 
            municipio
        );
        
        if (exitoGuardado) {
            // Se retorna el ICA generado para mostrar un mensaje al usuario
            return "Registro exitoso. Número ICA asignado: " + nuevoRegistroICA; 
        } else {
            return "Error al guardar los datos en la base de datos.";
        }
    }
    
    // Modificado para validar también el documento del dueño
    public String validarNumeros(String numeroPredial, String telefonoEmpresa, String documentoDueno) {
        String preStr = String.valueOf(numeroPredial);
        String telEmStr = String.valueOf(telefonoEmpresa);
        String docDuenoStr = String.valueOf(documentoDueno);

        // AVISO: Tu tabla dice NUMBER(30) para predial, pero tu validación decía 12
        // Ajusto a 30 para ser consistente con la tabla, pero verifica si 12 era correcto.
        if (preStr.length() != 30) { 
            return "El Predial debe contener exactamente 30 dígitos. (Actualmente tiene " + preStr.length() + ")";
        }
        if (telEmStr.length() != 10) {
            return "El Teléfono debe contener exactamente 10 dígitos. (Actualmente tiene " + telEmStr.length() + ")";
        }
        // Agregando la validación para el documento del dueño según tu tabla (NUMBER(10))
        if (docDuenoStr.length() != 10) {
            return "El Documento del dueño debe contener exactamente 10 dígitos. (Actualmente tiene " + docDuenoStr.length() + ")";
        }
        
        // Una validación adicional y más robusta sería asegurar que todos son solo dígitos
        if (!preStr.matches("\\d+") || !telEmStr.matches("\\d+") || !docDuenoStr.matches("\\d+")) {
            return "Los campos numéricos (Predial, Teléfono, Documento) solo deben contener dígitos.";
        }
        
        return null;
    }
    
    // (Métodos validarVereda y validarNombreLugarYEmpresa se mantienen iguales)
    public String validarVereda(String vereda) {
        if (!vereda.matches("^[a-zA-Z].*")) {
            return "La vereda debe ser valida.";
        }
        return null;
    }
    
    public String validarNombreLugarYEmpresa(String nombreLugar, String empresa) {
        // Permite letras (incluyendo tildes y ñ) y espacios
        String patronLetras = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"; 
        
        if (!nombreLugar.matches(patronLetras)) {
            return "El Nombre del lugar de producción solo debe contener letras.";
        }
        if (!empresa.matches(patronLetras)) {
            return "El Nombre de la empresa solo debe contener letras.";
        }
        return null;
    }
}
