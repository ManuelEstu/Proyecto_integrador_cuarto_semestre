/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import java.util.List;
import logica.Planta;
import persistencia.PlantaDao;
import persistencia.DaoCajitaLugares;
import persistencia.DaoLote;
import java.sql.SQLException;

public class ControladorCrearLote {
    
    private PlantaDao PlantaDao;
    private DaoCajitaLugares DaoCajitaLugares;
    private DaoLote DaoLote;

    public ControladorCrearLote() {
        this.PlantaDao = new PlantaDao(); // Inicializa el DAO
        this.DaoCajitaLugares = new DaoCajitaLugares();
        this.DaoLote = new DaoLote();
    }

    // La vista llama a este método.
    public List<Planta> cargarPlantas() {
        return PlantaDao.obtenerTodasLasPlantas();
    }
    
    public List<LugarCajita> cargarLugares(String documentoProductor) {
        return DaoCajitaLugares.obtenerLugaresPorProductor(documentoProductor);
    }
    
    public static boolean validarNumeroEntero(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        try {
            int numero = Integer.parseInt(texto.trim());
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida si un String representa un número decimal con hasta 2 decimales.
     * Soporta coma (,) o punto (.) como separador decimal.
     * (Usado para Área lote, Cantidad Proyectada/Recolectada).
     * * NOTA SOBRE ORACLE: En Java es mejor estandarizar la entrada a '.' para facilitar
     * la conversión a Double, ya que los drivers JDBC de Oracle generalmente prefieren el punto.
     * En la persistencia (DAO), te encargarías de convertir el String a Double.
     * * @param texto El texto a validar.
     * @return true si es un número decimal positivo válido (max. 2 decimales), false en caso contrario.
     */
    public static boolean validarNumeroDecimal(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        
        String textoLimpio = texto.trim();
        
        // Expresión regular para validar:
        // ^\\d+           -> Inicia con uno o más dígitos (parte entera)
        // (\\.\\d{1,2})?  -> Opcionalmente, sigue un PUNTO (.) seguido de 1 o 2 dígitos
        // $               -> Fin de la cadena
        String regex = "^\\d+(\\.\\d{1,2})?$"; 
        
        if (!textoLimpio.matches(regex)) {
            return false;
        }
        
        try {
            // Se convierte a Double usando el punto, lo que es seguro para Oracle.
            double numero = Double.parseDouble(textoLimpio);
            // Verifica que sea un número positivo
            return numero > 0; 
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida si un String tiene el formato de fecha DD/MM/AAAA y si es una fecha real.
     * Incluye la validación de límites de días según el mes (ej: Febrero <= 29 días).
     * * @param textoFecha La fecha a validar en formato DD/MM/AAAA.
     * @param esBisiesto Determina si el año puede ser bisiesto (se puede mejorar la lógica para que lo calcule).
     * @return true si el formato es correcto y la fecha es válida, false en caso contrario.
     */
    public static boolean validarFormatoFecha(String textoFecha) {
        if (textoFecha == null || !textoFecha.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            return false; // No cumple el formato DD/MM/AAAA
        }

        try {
            String[] partes = textoFecha.split("/");
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anio = Integer.parseInt(partes[2]);

            if (mes < 1 || mes > 12) return false;
            if (dia < 1 || dia > 31) return false;

            // Lógica de año bisiesto
            boolean esBisiesto = (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);

            // Validación de días por mes
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                if (dia > 30) return false; // Abril, Junio, Septiembre, Noviembre
            } else if (mes == 2) { // Febrero
                int maxDias = esBisiesto ? 29 : 28;
                if (dia > maxDias) return false;
            }
            
            // Si llega aquí, la fecha es estructuralmente válida (DD, MM, AAAA y límites de días).
            return true; 
        } catch (NumberFormatException e) {
            // Esto solo ocurriría si el regex fallara, pero es una guardia.
            return false;
        }
    }
    
    public void crearLote(
        String nroLote, int idPlanta, String registroIcaLugar, 
        String areaLote, String cantidadPlantas, String fechaSiembra, 
        String estado, String fechaProyRecoleccion, String cantidadProyRecoleccion, 
        String fechaEliminacion
    ) throws Exception {

        // 1. Los datos ya vienen validados y limpios (usando '.' para decimales) desde la vista.

        // 2. OBTENER ID SECUENCIAL Y LLAMAR AL DAO con los Strings

        try {
            // Generar ID
            int idLoteGenerado = DaoLote.obtenerIdLote(); 

            // Definir los valores de recolección como String vacío al crear (serán NULL o 0 en DB)
            String fechaRecoleccion = ""; // Se asume vacío en la creación
            String cantRecolectada = "";  // Se asume vacío en la creación (el DAO usará "0")

            // Llamada al DaoLote enviando los Strings/Int
            boolean exito = DaoLote.guardarLote(
                idLoteGenerado, 
                nroLote.trim(), 
                idPlanta, 
                registroIcaLugar, 
                areaLote.trim(), 
                fechaSiembra.trim(), 
                fechaEliminacion.trim(), 
                estado, 
                cantidadPlantas.trim(), 
                fechaProyRecoleccion.trim(), 
                cantidadProyRecoleccion.trim(), 
                fechaRecoleccion, 
                cantRecolectada
            );

            if (!exito) {
                throw new Exception("Error desconocido al intentar guardar el lote en la base de datos.");
            }

        } catch (SQLException e) {
            // Capturar errores de la DB y relanzarlos como una excepción de negocio
            throw new Exception("Error de base de datos al guardar el lote: " + e.getMessage(), e);
        }
    }
}
