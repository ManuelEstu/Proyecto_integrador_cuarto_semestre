/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoInspTec;

public class ControladorInspeccionTecnica {
    private final DaoInspTec dao;

    public ControladorInspeccionTecnica() {
        this.dao = new DaoInspTec();
    }

    /**
     * Recibe la data de la vista, construye el objeto y lo persiste.
     */
    public boolean registrarInspeccion(
        String ID_ORDEN, String AREA_ACOPIO, String AREA_MAN_RESIDUOS_VEGETALES, 
        String AREA_ALMAC_INSUMOS_AGRICOLAS, String AREA_DOSIF_PREP_MEZCLAS, 
        String AREA_RESIDUOS_MEZ_LAVADO, String AREA_SANITARIA_LAVAMANOS, 
        String AREA_HERRAMIENTAS, String COMENTARIOS) 
    {
        // 1. Generar el objeto con el patrón Builder
        InspeccionTecnica insp = new InspeccionTecnica.InspeccionTecnicaBuilder(ID_ORDEN)
            .conAreaAcopio(AREA_ACOPIO)
            .conAreaManResiduosVegetales(AREA_MAN_RESIDUOS_VEGETALES)
            .conAreaAlmacInsumosAgricolas(AREA_ALMAC_INSUMOS_AGRICOLAS)
            .conAreaDosifPrepMezclas(AREA_DOSIF_PREP_MEZCLAS)
            .conAreaResiduosMezLavado(AREA_RESIDUOS_MEZ_LAVADO)
            .conAreaSanitariaLavamanos(AREA_SANITARIA_LAVAMANOS)
            .conAreaHerramientas(AREA_HERRAMIENTAS)
            .conComentarios(COMENTARIOS)
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
        System.err.println("❌ Falló el registro de la Inspección Técnica en la base de datos.");
        return false;
    }
}
