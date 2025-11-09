/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class DatosPlanta {
    private int ID_PLANTA;
    private String NOMBRE_CIENTIFICO;
    private String NOMBRE_COMUN;
    private String CICLO_DE_CULTIVO;
    private String NOMBRE_DE_VARIEDAD;
    private String DESCRIPCION;
    
    public DatosPlanta(){
    
    }
    
    public DatosPlanta(int ID_PLANTA, String NOMBRE_CIENTIFICO, String NOMBRE_COMUN, String CICLO_DE_CULTIVO, String NOMBRE_DE_VARIEDAD, String DESCRIPCION){
        this.ID_PLANTA = ID_PLANTA;
        this.NOMBRE_CIENTIFICO = NOMBRE_CIENTIFICO;
        this.NOMBRE_COMUN = NOMBRE_COMUN;
        this.CICLO_DE_CULTIVO = CICLO_DE_CULTIVO;
        this.NOMBRE_DE_VARIEDAD = NOMBRE_DE_VARIEDAD;
        this.DESCRIPCION = DESCRIPCION;
    } 
    
    // --- Getters y Setters ---

    // ID_PLANTA
    public int getID_PLANTA() {
        return ID_PLANTA;
    }

    public void setID_PLANTA(int ID_PLANTA) {
        this.ID_PLANTA = ID_PLANTA;
    }

    // NOMBRE_CIENTIFICO
    public String getNOMBRE_CIENTIFICO() {
        return NOMBRE_CIENTIFICO;
    }

    public void setNOMBRE_CIENTIFICO(String NOMBRE_CIENTIFICO) {
        this.NOMBRE_CIENTIFICO = NOMBRE_CIENTIFICO;
    }

    // NOMBRE
    public String getNOMBRE_COMUN() {
        return NOMBRE_COMUN;
    }

    public void setNOMBRE_COMUN(String NOMBRE_COMUN) {
        this.NOMBRE_COMUN = NOMBRE_COMUN;
    }

    // CICLO_DE_CULTIVO
    public String getCICLO_DE_CULTIVO() {
        return CICLO_DE_CULTIVO;
    }

    public void setCICLO_DE_CULTIVO(String CICLO_DE_CULTIVO) {
        this.CICLO_DE_CULTIVO = CICLO_DE_CULTIVO;
    }

    // NOMBRE_DE_VARIEDAD
    public String getNOMBRE_DE_VARIEDAD() {
        return NOMBRE_DE_VARIEDAD;
    }

    public void setNOMBRE_DE_VARIEDAD(String NOMBRE_DE_VARIEDAD) {
        this.NOMBRE_DE_VARIEDAD = NOMBRE_DE_VARIEDAD;
    }

    // DESCRIPCION
    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }
    
}
