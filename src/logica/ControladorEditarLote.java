/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoLote;
import persistencia.PlantaDao;
import logica.DatosLote;
import logica.Planta;

public class ControladorEditarLote {
    private final DaoLote daoLote;
    // 🌟 NUEVO CAMPO: Necesitamos acceder al DAO de Plantas 🌟
    private final PlantaDao plantaDao;

    public ControladorEditarLote() {
        this.daoLote = new DaoLote();
        // 🌟 Inicialización del DAO de Plantas 🌟
        this.plantaDao = new PlantaDao();
    }

    /**
     * Valida los campos y llama al DAO para actualizar el lote.
     * @param lote Objeto DatosLote con la información a actualizar.
     * @return String con un mensaje de error si falla la validación, o null si es exitoso.
     */
    public String actualizarLote(DatosLote lote) {
        
        // --- 1. Validaciones de campos obligatorios (Trim y Vacío) ---
        String idLote = lote.getID_LOTE().trim();
        String nroLote = lote.getNRO_LOTE().trim();
        // El ID_PLANTA ahora viene del JComboBox, pero sigue siendo un String
        String idPlanta = lote.getID_PLANTA().trim(); 
        String nroIcaLugar = lote.getNRO_REGISTRO_ICA_LUGARP().trim();
        String areaLote = lote.getAREA_DE_LOTE().trim();
        String fechaSiembra = lote.getFECHA_DE_SIEMBRA().trim();
        String estado = lote.getESTADO().trim();
        String cantPlantas = lote.getCANTIDAD_DE_PLANTAS().trim();
        String fechaProyRecolec = lote.getFECHA_PROYEC_RECOLEC().trim();
        String cantProyRecolec = lote.getCANT_PROYEC_RECOLEC().trim();
        
        // Campos opcionales (si están vacíos, se manejan como null en el DAO)
        String fechaElim = lote.getFECHA_DE_ELIMINACION() != null ? lote.getFECHA_DE_ELIMINACION().trim() : "";
        String fechaRecoleccion = lote.getFECHA_RECOLECCION() != null ? lote.getFECHA_RECOLECCION().trim() : "";
        String cantRecolectada = lote.getCANT_RECOLECTADA() != null ? lote.getCANT_RECOLECTADA().trim() : "";


        if (idLote.isEmpty()) return "El ID del Lote no puede estar vacío.";
        if (nroLote.isEmpty()) return "El Número de Lote no puede estar vacío.";
        if (idPlanta.isEmpty()) return "El ID de la Planta no puede estar vacío.";
        if (nroIcaLugar.isEmpty()) return "El Número ICA del Lugar no puede estar vacío.";
        if (areaLote.isEmpty()) return "El Área del Lote no puede estar vacía.";
        if (fechaSiembra.isEmpty()) return "La Fecha de Siembra no puede estar vacía.";
        if (estado.isEmpty()) return "El Estado del Lote no puede estar vacío.";
        if (cantPlantas.isEmpty()) return "La Cantidad de Plantas no puede estar vacía.";
        if (fechaProyRecolec.isEmpty()) return "La Fecha Proyectada de Recolección no puede estar vacía.";
        if (cantProyRecolec.isEmpty()) return "La Cantidad Proyectada de Recolección no puede estar vacía.";

        // --- 2. Validaciones de tipo de datos ---

        // ID_LOTE, ID_PLANTA, NRO_LOTE, NRO_REGISTRO_ICA_LUGARP
        if (!validarNumeroEntero(idLote)) return "El ID del Lote debe ser un número entero positivo válido.";
        if (!validarNumeroEntero(idPlanta)) return "El ID de la Planta debe ser un número entero positivo válido.";
        if (!validarNumeroEntero(nroLote)) return "El Número de Lote debe ser un número entero positivo válido.";
        if (!validarNumeroEntero(nroIcaLugar)) return "El Número ICA del Lugar debe ser un número entero positivo válido.";
        if (!validarNumeroEntero(cantPlantas)) return "La Cantidad de Plantas debe ser un número entero positivo válido.";

        // AREA_DE_LOTE, CANT_PROYEC_RECOLEC, CANT_RECOLECTADA
        if (!validarNumeroDecimal(areaLote)) return "El Área del Lote debe ser un número decimal positivo válido (ej: 10.50).";
        if (!validarNumeroDecimal(cantProyRecolec)) return "La Cantidad Proyectada de Recolección debe ser un número decimal positivo válido.";
        // CANT_RECOLECTADA es opcional (puede ser ""). Si no está vacío, debe ser válido.
        if (!cantRecolectada.isEmpty() && !validarNumeroDecimal(cantRecolectada)) return "La Cantidad Recolectada debe ser un número decimal positivo válido.";
        
        // Fechas (Formato DD-MM-AAAA) - Se usa el formato 'DD-MM-YYYY' de la BD
        if (!validarFormatoFecha(fechaSiembra)) return "La Fecha de Siembra tiene un formato incorrecto (DD-MM-AAAA) o no es válida.";
        if (!validarFormatoFecha(fechaProyRecolec)) return "La Fecha Proyectada de Recolección tiene un formato incorrecto (DD-MM-AAAA) o no es válida.";
        
        // Fechas Opcionales: solo se validan si el campo no está vacío
        if (!fechaElim.isEmpty() && !validarFormatoFecha(fechaElim)) return "La Fecha de Eliminación tiene un formato incorrecto (DD-MM-AAAA) o no es válida.";
        if (!fechaRecoleccion.isEmpty() && !validarFormatoFecha(fechaRecoleccion)) return "La Fecha de Recolección tiene un formato incorrecto (DD-MM-AAAA) o no es válida.";

        // --- 3. Llamada a la capa DAO ---
        boolean exito = daoLote.editarLote(
            idLote,
            nroLote,
            idPlanta,
            nroIcaLugar,
            areaLote,
            fechaSiembra,
            fechaElim, 
            estado,
            cantPlantas,
            fechaProyRecolec,
            cantProyRecolec,
            fechaRecoleccion, 
            cantRecolectada
        );

        if (exito) {
            return null; // Éxito, no hay mensaje de error
        } else {
            return "Ocurrió un error al intentar actualizar el lote en la base de datos. (Puede que el ID no exista o haya un error en la conexión).";
        }
    }
    
    /**
     * Obtiene el lote por ID y carga la lista completa de plantas disponibles.
     * @param idLoteTexto El ID del lote a buscar.
     * @return DatosLote con la información del lote y la lista de plantas disponibles.
     */
    public DatosLote obtenerLote(String idLoteTexto) {
        if (idLoteTexto == null || idLoteTexto.trim().isEmpty()) {
            return null; 
        }

        try {
            // Validación de formato antes de llamar al DAO
            Integer.parseInt(idLoteTexto.trim());
        } catch (NumberFormatException e) {
            // El ID no es un número válido.
            return null;
        }

        // 1. Llamar al DAO para obtener los datos del lote
        DatosLote loteEncontrado = daoLote.buscarLotePorId(idLoteTexto);

        if (loteEncontrado != null) {
             // 2. 🌟 CARGAR LA LISTA COMPLETA DE PLANTAS 🌟
            try {
                 // Llama al DAO de plantas para obtener la lista (ID y Nombre)
                loteEncontrado.setListaPlantasDisponibles(plantaDao.obtenerTodasLasPlantas());
            } catch (Exception e) {
                // Manejar error si no se pueden cargar las plantas. El lote igual se puede mostrar.
                System.err.println("Advertencia: No se pudo cargar la lista completa de plantas: " + e.getMessage());
                // Se retorna el lote sin la lista de plantas, pero se imprime la advertencia.
            }
        }
        
        return loteEncontrado;
    }

    public static boolean validarNumeroEntero(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        try {
            int numero = Integer.parseInt(texto.trim());
            return numero >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarNumeroDecimal(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        
        String textoLimpio = texto.trim();
        
        // Expresión regular para validar:
        // ^\d+ -> Inicia con uno o más dígitos (parte entera)
        // (\.\d{1,2})? -> Opcionalmente, sigue un PUNTO (.) seguido de 1 o 2 dígitos
        // $ -> Fin de la cadena
        String regex = "^\\d+(\\.\\d{1,2})?$"; 
        
        if (!textoLimpio.matches(regex)) {
            return false;
        }
        
        try {
            // Se convierte a Double usando el punto, lo que es seguro para Oracle.
            double numero = Double.parseDouble(textoLimpio);
            // Verifica que sea un número positivo
            return numero >= 0; 
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Valida el formato de fecha para DD-MM-AAAA.
     * @param textoFecha La fecha en formato String.
     * @return true si la fecha es válida, false en caso contrario.
     */
    public static boolean validarFormatoFecha(String textoFecha) {
        // 🌟 CAMBIO CLAVE: Se actualiza el regex para aceptar el guión (-) en lugar de barra (/) 🌟
        if (textoFecha == null || !textoFecha.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            return false; // No cumple el formato DD-MM-AAAA
        }

        try {
            String[] partes = textoFecha.split("-");
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
            
            return true; 
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
