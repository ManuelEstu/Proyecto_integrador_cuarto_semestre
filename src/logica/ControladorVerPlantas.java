/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoPlanta;
import logica.DatosPlanta;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorVerPlantas {
    private final DaoPlanta DaoPlanta;
    
    public ControladorVerPlantas() {
        this.DaoPlanta = new DaoPlanta();
    }
    
    public List<DatosPlanta> buscarPlantas(String nombre) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return DaoPlanta.obtenerTodasLasPlantas(nombre);
    }
    
    public DefaultTableModel cargarTablaPlantas(List<DatosPlanta> listaPlantas) {
        // Nuevas columnas: Se elimina "Tipo" y se añaden "Usuario" y "Clave"
        String[] titulos = {"ID_PLANTA", "NOMBRE_COMUN", "NOMBRE_CIENTIFICO", "CICLO_DE_CULTIVO", "NOMBRE_DE_VARIEDAD", "DESCRIPCION"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
             // Esto hace que las celdas no sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };

        for (DatosPlanta planta : listaPlantas) {
            
            Object[] fila = {
                planta.getID_PLANTA(),
                planta.getNOMBRE_COMUN(),
                planta.getNOMBRE_CIENTIFICO(),
                planta.getCICLO_DE_CULTIVO(), 
                planta.getNOMBRE_DE_VARIEDAD(),         
                planta.getDESCRIPCION()
            };
            modelo.addRow(fila);
        }
        
        return modelo;
    }
}
