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
import persistencia.DaoOrden;

public class ControladorDarOrden {
    
    private DaoOrden DaoOrden = new DaoOrden();
    
    public String validarRegistro(String numeroICA, String tipo, String funcionario,
                                String comentarios, String fecha, String tecnico) {
        
        // --- 1. VALIDACIONES ---
        // (Se mantienen como estaban, pero ajusté la validación de Predial a 30 dígitos según tu tabla)
        String errorNumerico = validarNumeros(numeroICA, tecnico);
        if (errorNumerico != null) return errorNumerico;

        String errorFecha = validarFormatoFecha(fecha);
        if (errorFecha != null) return errorFecha;
        
        // --- 2. GENERACIÓN DEL ICA Y GUARDADO DE DATOS ---
        
        // 2.1. Generar el número de registro ICA incremental
        int nuevoid = DaoOrden.obtenerSiguienteId();
        
        if (nuevoid == 0) {
            return "Error grave al obtener el número de registro ICA. Intente de nuevo.";
        }
        
        // 2.2. Intentar el guardado
        boolean exitoGuardado = DaoOrden.guardarOrden(
            nuevoid,
            tipo, 
            funcionario, 
            numeroICA,
            tecnico, 
            fecha, 
            comentarios
        );
        
        if (exitoGuardado) {
            // Se retorna el ICA generado para mostrar un mensaje al usuario
            return "Registro exitoso. Id asignado: " + nuevoid; 
        } else {
            return "Error al guardar los datos en la base de datos.";
        }
    }
    
    public String validarNumeros(String numeroICA, String documentoTec) {
        String icaStr = String.valueOf(numeroICA);
        String docTecStr = String.valueOf(documentoTec);

        // AVISO: Tu tabla dice NUMBER(30) para predial, pero tu validación decía 12
        // Ajusto a 30 para ser consistente con la tabla, pero verifica si 12 era correcto.
        if (icaStr.length() != 6) { 
            return "El número de registro ICA debe contener exactamente 6 dígitos. (Actualmente tiene " + icaStr.length() + ")";
        }
        // Agregando la validación para el documento del dueño según tu tabla (NUMBER(10))
        if (docTecStr.length() != 10) {
            return "El Documento del técnico debe contener exactamente 10 dígitos. (Actualmente tiene " + docTecStr.length() + ")";
        }
        
        // Una validación adicional y más robusta sería asegurar que todos son solo dígitos
        if (!icaStr.matches("\\d+") || !docTecStr.matches("\\d+")) {
            return "Los campos numéricos (Número de registro ICA, Documento técnico) solo deben contener dígitos.";
        }
        
        return null;
    }
    
    public static String validarFormatoFecha(String textoFecha) {
        if (textoFecha == null || !textoFecha.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            return "El formato de la fecha debe ser DD/MM/AAAA."; // Retorna el mensaje de error
        }

        try {
            String[] partes = textoFecha.split("/");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anio = Integer.parseInt(partes[2]);

            if (mes < 1 || mes > 12) return "El mes debe estar entre 01 y 12.";
            if (dia < 1 || dia > 31) return "El día debe estar entre 01 y 31.";

            // Lógica de año bisiesto
            boolean esBisiesto = (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);

            // Validación de días por mes
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                if (dia > 30) return "El mes " + mes + " solo tiene 30 días."; // Abril, Junio, Septiembre, Noviembre
            } else if (mes == 2) { // Febrero
                int maxDias = esBisiesto ? 29 : 28;
                if (dia > maxDias) return "Febrero en el año " + anio + " solo tiene " + maxDias + " días.";
            }
            
            // Si llega aquí, la fecha es estructuralmente válida.
            return null; // Retorna null si la validación es exitosa
        } catch (NumberFormatException e) {
            // Esto solo ocurriría si el regex fallara o si alguna parte no es un número.
            return "Error interno al procesar la fecha. Verifique que solo contiene números y '/'";
        }
    }
    
}
