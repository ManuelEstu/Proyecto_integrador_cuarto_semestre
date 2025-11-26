/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.DaoLugarProduccion2;
import logica.DatosLugar2;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorVerLugares {
    
    private final DaoLugarProduccion2 DaoLugarProduccion2;
    
    public ControladorVerLugares() {
        this.DaoLugarProduccion2 = new DaoLugarProduccion2();
    }
    
    public List<DatosLugar2> buscarLugares(String documento, String nombre) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return DaoLugarProduccion2.buscarLugares(documento, nombre);
    }
    
    public List<DatosLugar2> buscarLugares2(String documento, String numica) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return DaoLugarProduccion2.buscarLugares2(documento, numica);
    }
    
    public DefaultTableModel cargarTablaLugares(List<DatosLugar2> listaLugares) {
        // Nuevas columnas: Se elimina "Tipo" y se añaden "Usuario" y "Clave"
        String[] titulos = {"REGISTRO ICA", "PREDIAL", "NOMBRE", "EMPRESA", "TELEFONO EMPRESA", "DEPARTAMENTO", "MUNICIPIO", "VEREDA"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
             // Esto hace que las celdas no sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };

        for (DatosLugar2 lugar : listaLugares) {
            
            Object[] fila = {
                lugar.getNumero_registro_ICA(),
                lugar.getNumero_predial(),
                lugar.getNombre(),
                lugar.getNombre_empresa(), 
                lugar.getTelefono_empresa(),         
                lugar.getDepartamento(),
                lugar.getMunicipio(),
                lugar.getVereda()
            };
            modelo.addRow(fila);
        }
        
        return modelo;
    }
    
}
