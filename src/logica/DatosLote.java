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

public class DatosLote {
    
    // ⚠️ Se recomienda cambiar los modificadores de acceso a private
    private String ID_LOTE;
    private String NRO_LOTE;
    private String ID_PLANTA;
    private String NRO_REGISTRO_ICA_LUGARP;
    private String AREA_DE_LOTE;
    private String FECHA_DE_SIEMBRA;
    private String FECHA_DE_ELIMINACION;
    private String ESTADO;
    private String CANTIDAD_DE_PLANTAS;
    private String FECHA_PROYEC_RECOLEC;
    private String CANT_PROYEC_RECOLEC;
    private String FECHA_RECOLECCION;
    private String CANT_RECOLECTADA;
    private List<Planta> listaPlantasDisponibles;
    
    // Constructor vacío (opcional, pero buena práctica)
    public DatosLote() {
    }

    // Constructor con todos los campos (como lo tenías)
    public DatosLote(String ID_LOTE, String NRO_LOTE, String ID_PLANTA, String NRO_REGISTRO_ICA_LUGARP, String AREA_DE_LOTE, 
                             String FECHA_DE_SIEMBRA, String FECHA_DE_ELIMINACION, String ESTADO, String CANTIDAD_DE_PLANTAS, 
                             String FECHA_PROYEC_RECOLEC, String CANT_PROYEC_RECOLEC, String FECHA_RECOLECCION, String CANT_RECOLECTADA) {
        this.ID_LOTE = ID_LOTE;
        this.NRO_LOTE = NRO_LOTE;
        this.ID_PLANTA = ID_PLANTA;
        this.NRO_REGISTRO_ICA_LUGARP = NRO_REGISTRO_ICA_LUGARP;
        this.AREA_DE_LOTE = AREA_DE_LOTE;
        this.FECHA_DE_SIEMBRA = FECHA_DE_SIEMBRA;
        this.FECHA_DE_ELIMINACION = FECHA_DE_ELIMINACION;
        this.ESTADO = ESTADO;
        this.CANTIDAD_DE_PLANTAS = CANTIDAD_DE_PLANTAS;
        this.FECHA_PROYEC_RECOLEC = FECHA_PROYEC_RECOLEC;
        this.CANT_PROYEC_RECOLEC = CANT_PROYEC_RECOLEC;
        this.FECHA_RECOLECCION = FECHA_RECOLECCION;
        this.CANT_RECOLECTADA = CANT_RECOLECTADA;
    }

    // --- MÉTODOS GETTERS ---

    public String getID_LOTE() {
        return ID_LOTE;
    }

    public String getNRO_LOTE() {
        return NRO_LOTE;
    }

    public String getID_PLANTA() {
        return ID_PLANTA;
    }

    public String getNRO_REGISTRO_ICA_LUGARP() {
        return NRO_REGISTRO_ICA_LUGARP;
    }

    public String getAREA_DE_LOTE() {
        return AREA_DE_LOTE;
    }

    public String getFECHA_DE_SIEMBRA() {
        return FECHA_DE_SIEMBRA;
    }

    public String getFECHA_DE_ELIMINACION() {
        return FECHA_DE_ELIMINACION;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public String getCANTIDAD_DE_PLANTAS() {
        return CANTIDAD_DE_PLANTAS;
    }

    public String getFECHA_PROYEC_RECOLEC() {
        return FECHA_PROYEC_RECOLEC;
    }

    public String getCANT_PROYEC_RECOLEC() {
        return CANT_PROYEC_RECOLEC;
    }

    public String getFECHA_RECOLECCION() {
        return FECHA_RECOLECCION;
    }

    public String getCANT_RECOLECTADA() {
        return CANT_RECOLECTADA;
    }
    
    // --- MÉTODOS SETTERS ---

    public void setID_LOTE(String ID_LOTE) {
        this.ID_LOTE = ID_LOTE;
    }

    public void setNRO_LOTE(String NRO_LOTE) {
        this.NRO_LOTE = NRO_LOTE;
    }

    public void setID_PLANTA(String ID_PLANTA) {
        this.ID_PLANTA = ID_PLANTA;
    }

    public void setNRO_REGISTRO_ICA_LUGARP(String NRO_REGISTRO_ICA_LUGARP) {
        this.NRO_REGISTRO_ICA_LUGARP = NRO_REGISTRO_ICA_LUGARP;
    }

    public void setAREA_DE_LOTE(String AREA_DE_LOTE) {
        this.AREA_DE_LOTE = AREA_DE_LOTE;
    }

    public void setFECHA_DE_SIEMBRA(String FECHA_DE_SIEMBRA) {
        this.FECHA_DE_SIEMBRA = FECHA_DE_SIEMBRA;
    }

    public void setFECHA_DE_ELIMINACION(String FECHA_DE_ELIMINACION) {
        this.FECHA_DE_ELIMINACION = FECHA_DE_ELIMINACION;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public void setCANTIDAD_DE_PLANTAS(String CANTIDAD_DE_PLANTAS) {
        this.CANTIDAD_DE_PLANTAS = CANTIDAD_DE_PLANTAS;
    }

    public void setFECHA_PROYEC_RECOLEC(String FECHA_PROYEC_RECOLEC) {
        this.FECHA_PROYEC_RECOLEC = FECHA_PROYEC_RECOLEC;
    }

    public void setCANT_PROYEC_RECOLEC(String CANT_PROYEC_RECOLEC) {
        this.CANT_PROYEC_RECOLEC = CANT_PROYEC_RECOLEC;
    }

    public void setFECHA_RECOLECCION(String FECHA_RECOLECCION) {
        this.FECHA_RECOLECCION = FECHA_RECOLECCION;
    }

    public void setCANT_RECOLECTADA(String CANT_RECOLECTADA) {
        this.CANT_RECOLECTADA = CANT_RECOLECTADA;
    }
    
    public List<Planta> getListaPlantasDisponibles() {
        return listaPlantasDisponibles;
    }
    
    public void setListaPlantasDisponibles(List<Planta> listaPlantasDisponibles) {
        this.listaPlantasDisponibles = listaPlantasDisponibles;
    }
}
