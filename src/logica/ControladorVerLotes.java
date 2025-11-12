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
import logica.DatosLote2;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorVerLotes {
    
    private final DaoLote DaoLote;
    
    public ControladorVerLotes() {
        this.DaoLote = new DaoLote();
    }
    
    public List<DatosLote2> buscarLotes(String numeroICALugar, String idPlanta) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return DaoLote.buscarLotes(numeroICALugar, idPlanta);
    }
    
    public List<DatosLote2> buscarLotes2(String numeroICALugar) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return DaoLote.buscarLotes2(numeroICALugar);
    }
    
    public DefaultTableModel cargarTablaLotes(List<DatosLote2> listaLotes) {
        // Nuevas columnas: Se elimina "Tipo" y se añaden "Usuario" y "Clave"
        String[] titulos = {"ID", "NRO", "ID planta", "Lugar producción", "Area", "Sembrado", "Eliminar", "Estado", "NRO Plantas", "Recolectar", "cantidad esperada", "Se recolectó", "Cantidad real"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
             // Esto hace que las celdas no sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };

        for (DatosLote2 lote : listaLotes) {
            
            Object[] fila = {
                lote.getID_LOTE(),
                lote.getNRO_LOTE(),
                lote.getID_PLANTA(),
                lote.getNRO_REGISTRO_ICA_LUGARP(), 
                lote.getAREA_DE_LOTE(),         
                lote.getFECHA_DE_SIEMBRA(),
                lote.getFECHA_DE_ELIMINACION(),
                lote.getESTADO(),
                lote.getCANTIDAD_DE_PLANTAS(),
                lote.getFECHA_PROYEC_RECOLEC(),
                lote.getCANT_PROYEC_RECOLEC(),
                lote.getFECHA_RECOLECCION(),
                lote.getCANT_RECOLECTADA()
            };
            modelo.addRow(fila);
        }
        
        return modelo;
    }
    
}
