/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoInformePlantas;
import igu.InformePlanta;
import java.util.List;

public class ControladorInformePlantas {

    private final DaoInformePlantas plantaDAO;
    
    // Constructor
    public ControladorInformePlantas() {
        this.plantaDAO = new DaoInformePlantas();
    }

    /**
     * Obtiene los datos del informe y los formatea como una cadena de texto 
     * para ser mostrada en un JTextArea, replicando el formato de la imagen.
     * @return El informe formateado como String.
     */
    public String generarInformeFormateado() {
        // Usamos el DAO para obtener la lista de datos.
        // Asumimos el uso del PlantaDAO que no recibe conexión y la gestiona internamente.
        List<InformePlantas> informeData = plantaDAO.obtenerInformePlantas();
        
        // 1. Cabecera del informe
        StringBuilder sb = new StringBuilder();
        sb.append("=================== INFORME DE ESTADO DE FRECUENCIA DE CULTIVO ===================\n");
        sb.append("\n");
        
        // Definición de anchos de columna fijos para alinear el texto
        int anchoPlanta = 40;
        int anchoLotes = 25;
        int anchoLugares = 35;
        
        // 2. Línea de encabezados principales
        sb.append(String.format("%-" + anchoPlanta + "s", "Especie vegetal"));
        sb.append("|");
        sb.append(String.format("%-" + anchoLotes + "s", "Cantidad de lotes en donde se cultiva"));
        sb.append("|");
        sb.append(String.format("%-" + anchoLugares + "s", "Cantidad de lugares de producción donde se cultiva"));
        sb.append("\n");

        // Línea separadora
        String separador = "-";
        sb.append(separador.repeat(anchoPlanta + 1));
        sb.append("+");
        sb.append(separador.repeat(anchoLotes + 1));
        sb.append("+");
        sb.append(separador.repeat(anchoLugares + 1));
        sb.append("\n");
        
        // 3. Línea de sub-encabezados (aliases)
        sb.append(String.format("%-" + anchoPlanta + "s", "Nombre de la Planta"));
        sb.append("|");
        sb.append(String.format("%-" + anchoLotes + "s", "Cantidad de Lotes"));
        sb.append("|");
        sb.append(String.format("%-" + anchoLugares + "s", "Cantidad de Lugares"));
        sb.append("\n");
        
        // 4. Agregar los datos
        if (informeData.isEmpty()) {
            sb.append("\n\n*** No se encontraron datos de cultivo para generar el informe. ***\n");
        } else {
            for (InformePlantas dto : informeData) {
                sb.append(String.format("%-" + anchoPlanta + "s", dto.getNombrePlanta()));
                sb.append("|");
                // Alineamos los números a la derecha para mejor legibilidad
                sb.append(String.format("%" + anchoLotes + "d", dto.getCantidadLotes()));
                sb.append("|");
                sb.append(String.format("%" + anchoLugares + "d", dto.getCantidadLugares()));
                sb.append("\n");
            }
        }
        
        // 5. Pie de página opcional
        sb.append("\n===================================================================================\n");
        
        return sb.toString();
    }
}
