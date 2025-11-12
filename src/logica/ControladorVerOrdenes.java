/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoOrden;
import logica.DatosOrden;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorVerOrdenes {
    private final DaoOrden DaoOrden;
    
    public ControladorVerOrdenes() {
        this.DaoOrden = new DaoOrden();
    }
    
    public List<DatosOrden> buscarOrdenes(String documento, String tipo) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return DaoOrden.obtenerTodasLasOrdenes(documento, tipo);
    }
    
    public List<DatosOrden> buscarOrdenes2(String numica, String tipo) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return DaoOrden.obtenerTodasLasOrdenes2(numica, tipo);
    }
    
    public DefaultTableModel cargarTablaOrdenes(List<DatosOrden> listaOrdenes) {
        // Nuevas columnas: Se elimina "Tipo" y se añaden "Usuario" y "Clave"
        String[] titulos = {"ID", "FUNCIONARIO", "LUGAR", "FECHA", "COMENTARIOS"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
             // Esto hace que las celdas no sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };

        for (DatosOrden orden : listaOrdenes) {
            
            Object[] fila = {
                orden.getID(),
                orden.getDOCUMENTO_FUNCIONARIO_ORDENA(),
                orden.getNUMERO_ICA_LUGAR_PRODUCCION(),
                orden.getFECHA_ESTIMADA(), 
                orden.getCOMENTARIOS()
            };
            modelo.addRow(fila);
        }
        
        return modelo;
    }
}
