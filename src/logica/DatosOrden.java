/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class DatosOrden {
    private int ID;
    private String TIPO;
    private String DOCUMENTO_FUNCIONARIO_ORDENA;
    private String NUMERO_ICA_LUGAR_PRODUCCION;
    private String NUMERO_DOCUMENTO_TECNICO;
    private String FECHA_ESTIMADA;
    private String ESTADO;
    private String COMENTARIOS;
    
    public DatosOrden(){
    
    }
    
    public DatosOrden(int ID, String TIPO, String DOCUMENTO_FUNCIONARIO_ORDENA, String NUMERO_ICA_LUGAR_PRODUCCION, String NUMERO_DOCUMENTO_TECNICO, String FECHA_ESTIMADA, String ESTADO, String COMENTARIOS){
        this.ID = ID;
        this.TIPO = TIPO;
        this.DOCUMENTO_FUNCIONARIO_ORDENA = DOCUMENTO_FUNCIONARIO_ORDENA;
        this.NUMERO_ICA_LUGAR_PRODUCCION = NUMERO_ICA_LUGAR_PRODUCCION;
        this.NUMERO_DOCUMENTO_TECNICO = NUMERO_DOCUMENTO_TECNICO;
        this.FECHA_ESTIMADA = FECHA_ESTIMADA;
        this.ESTADO = ESTADO;
        this.COMENTARIOS = COMENTARIOS;
    } 
    
    // --- Getters y Setters ---

    // ID_PLANTA
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    // NOMBRE_CIENTIFICO
    public String getTIPO() {
        return TIPO;
    }

    public void setTIPO(String TIPO) {
        this.TIPO = TIPO;
    }

    // NOMBRE
    public String getDOCUMENTO_FUNCIONARIO_ORDENA() {
        return DOCUMENTO_FUNCIONARIO_ORDENA;
    }

    public void setDOCUMENTO_FUNCIONARIO_ORDENA(String DOCUMENTO_FUNCIONARIO_ORDENA) {
        this.DOCUMENTO_FUNCIONARIO_ORDENA = DOCUMENTO_FUNCIONARIO_ORDENA;
    }

    // CICLO_DE_CULTIVO
    public String getNUMERO_ICA_LUGAR_PRODUCCION() {
        return NUMERO_ICA_LUGAR_PRODUCCION;
    }

    public void setNUMERO_ICA_LUGAR_PRODUCCION(String NUMERO_ICA_LUGAR_PRODUCCION) {
        this.NUMERO_ICA_LUGAR_PRODUCCION = NUMERO_ICA_LUGAR_PRODUCCION;
    }

    // NOMBRE_DE_VARIEDAD
    public String getNUMERO_DOCUMENTO_TECNICO() {
        return NUMERO_DOCUMENTO_TECNICO;
    }

    public void setNUMERO_DOCUMENTO_TECNICO(String NUMERO_DOCUMENTO_TECNICO) {
        this.NUMERO_DOCUMENTO_TECNICO = NUMERO_DOCUMENTO_TECNICO;
    }

    // DESCRIPCION
    public String getFECHA_ESTIMADA() {
        return FECHA_ESTIMADA;
    }

    public void setFECHA_ESTIMADA(String FECHA_ESTIMADA) {
        this.FECHA_ESTIMADA = FECHA_ESTIMADA;
    }
    
    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    
    public String getCOMENTARIOS() {
        return COMENTARIOS;
    }

    public void setCOMENTARIOS(String COMENTARIOS) {
        this.COMENTARIOS = COMENTARIOS;
    }
    
}
