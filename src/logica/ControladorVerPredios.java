/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoPredio2;
import logica.DatosPredio2;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorVerPredios {
    
    private final DaoPredio2 DaoPredio2;
    
    public ControladorVerPredios() {
        this.DaoPredio2 = new DaoPredio2();
    }
    
    public List<DatosPredio2> buscarPredios(String documento, String nombre) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return DaoPredio2.buscarPredio(documento, nombre);
    }
    
    public List<DatosPredio2> buscarPredios2(String documento, String nombre) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return DaoPredio2.buscarPredio2(documento, nombre);
    }
    
    public DefaultTableModel cargarTablaPredios(List<DatosPredio2> listaPredios) {
        // Nuevas columnas: Se elimina "Tipo" y se añaden "Usuario" y "Clave"
        String[] titulos = {"NUMERO_REGISTRO_ICA", "NUMERO_PREDIAL", "NOMBRE", "DEPARTAMENTO", "MUNICIPIO", "VEREDA", "NUMERO_ICA_LUGAR_PRODUCCION", "EXTENSION"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
             // Esto hace que las celdas no sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };

        for (DatosPredio2 predio : listaPredios) {
            
            Object[] fila = {
                predio.getNUMERO_REGISTRO_ICA(),
                predio.getNUMERO_PREDIAL(),
                predio.getNOMBRE(),
                predio.getDEPARTAMENTO(), 
                predio.getMUNICIPIO(),         
                predio.getVEREDA(),
                predio.getNUMERO_ICA_LUGAR_PRODUCCION(),
                predio.getEXTENSION()
            };
            modelo.addRow(fila);
        }
        
        return modelo;
    }
    
}
