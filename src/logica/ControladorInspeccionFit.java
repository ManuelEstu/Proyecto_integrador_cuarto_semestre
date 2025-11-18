/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoInspFit;

public class ControladorInspeccionFit {
    private final DaoInspFit dao;

    public ControladorInspeccionFit() {
        this.dao = new DaoInspFit();
    }
    
    public boolean registrarInspeccion(
        String ID_ORDEN, String NRO_PLANTAS_EVALUADAS, String ESTADO_FENOLOGICO, 
        String CANTIDAD_PLANTAS_INFESTADAS, String PORCENTAJE_DE_INFESTACION, 
        String COMENTARIOS) 
    {
        // 🚨 INICIO DE VALIDACIONES 🚨
        
        int numPlantasEvaluadas;
        int cantPlantasInfestadas;
        double porcentajeInfestacion;

        // 1. Validar NRO_PLANTAS_EVALUADAS (Entero positivo)
        try {
            numPlantasEvaluadas = Integer.parseInt(NRO_PLANTAS_EVALUADAS);
            if (numPlantasEvaluadas <= 0) {
                System.err.println("❌ Validación fallida: 'NRO_PLANTAS_EVALUADAS' debe ser un número entero positivo.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("❌ Validación fallida: 'NRO_PLANTAS_EVALUADAS' no es un número entero válido.");
            return false;
        }

        // 2. Validar CANTIDAD_PLANTAS_INFESTADAS (Entero no negativo y <= numPlantasEvaluadas)
        try {
            cantPlantasInfestadas = Integer.parseInt(CANTIDAD_PLANTAS_INFESTADAS);
            if (cantPlantasInfestadas < 0) {
                System.err.println("❌ Validación fallida: 'CANTIDAD_PLANTAS_INFESTADAS' no puede ser negativo.");
                return false;
            }
            if (cantPlantasInfestadas > numPlantasEvaluadas) {
                System.err.println("❌ Validación fallida: 'CANTIDAD_PLANTAS_INFESTADAS' (" + cantPlantasInfestadas + ") no puede ser mayor que 'NRO_PLANTAS_EVALUADAS' (" + numPlantasEvaluadas + ").");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("❌ Validación fallida: 'CANTIDAD_PLANTAS_INFESTADAS' no es un número entero válido.");
            return false;
        }

        // 3. Validar PORCENTAJE_DE_INFESTACION (Número entre 0 y 100)
        try {
            porcentajeInfestacion = Double.parseDouble(PORCENTAJE_DE_INFESTACION);
            if (porcentajeInfestacion < 0 || porcentajeInfestacion > 100) {
                System.err.println("❌ Validación fallida: 'PORCENTAJE_DE_INFESTACION' debe estar entre 0 y 100.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("❌ Validación fallida: 'PORCENTAJE_DE_INFESTACION' no es un número válido.");
            return false;
        }
        
        // 1. Generar el objeto con el patrón Builder
        InspeccionFito insp = new InspeccionFito.InspeccionFitoBuilder(ID_ORDEN)
            .conNRO_PLANTAS_EVALUADAS(NRO_PLANTAS_EVALUADAS)
            .conESTADO_FENOLOGICO(ESTADO_FENOLOGICO)
            .conCANTIDAD_PLANTAS_INFESTADAS(CANTIDAD_PLANTAS_INFESTADAS)
            .conPORCENTAJE_DE_INFESTACION(PORCENTAJE_DE_INFESTACION)
            .conCOMENTARIOS(COMENTARIOS)
            .build(); 

        // 2. Obtener el ID para la inspección
        int siguienteId = dao.obtenerId();
        
        if (siguienteId == 0) {
            System.err.println("❌ Error: No se pudo obtener el ID consecutivo. Abortando registro.");
            return false;
        }

        // 3. 🎯 Intentar guardar la Inspección Técnica
        boolean registroInspeccionExitoso = dao.guardarInspeccion(siguienteId, insp);

        if (registroInspeccionExitoso) {
            
            // 4. ✅ ÉXITO en la inserción. Ahora, actualiza el estado de la ORDEN.
            boolean actualizacionOrdenExitosa = dao.actualizarEstadoOrden(ID_ORDEN);
            
            if (actualizacionOrdenExitosa) {
                System.out.println("✅ Registro completo: Inspección guardada y Orden " + ID_ORDEN + " actualizada a 'REALIZADA'.");
            } else {
                // Si la actualización falla, es un problema secundario pero crítico.
                // La inspección ya se registró, pero la orden sigue 'PENDIENTE'. ¡Alarma!
                System.err.println("⚠️ ATENCIÓN: La inspección fue guardada (ID: " + siguienteId + "), pero FALLÓ al actualizar el estado de la Orden " + ID_ORDEN + ".");
            }
            
            // Retornamos true porque el dato más importante (la inspección) ya está en la DB.
            return true; 
        } 
        
        // Si el registro de la inspección falló (paso 3)
        System.err.println("❌ Falló el registro de la Inspección fitosanitaria en la base de datos.");
        return false;
    }
    
}
