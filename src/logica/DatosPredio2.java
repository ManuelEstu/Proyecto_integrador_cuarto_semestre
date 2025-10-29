/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class DatosPredio2 {
    private String NUMERO_REGISTRO_ICA;
    private String NUMERO_PREDIAL;
    private String NOMBRE;
    private String DEPARTAMENTO;
    private String MUNICIPIO;
    private String VEREDA;
    private String NUMERO_ICA_LUGAR_PRODUCCION;
    private String EXTENSION;
    private String DOCUMENTO_PROPIETARIO;
    
    public DatosPredio2(){
    
    }
    
    public DatosPredio2(String NUMERO_REGISTRO_ICA, String NUMERO_PREDIAL, String NOMBRE, String DEPARTAMENTO, String MUNICIPIO, String VEREDA, String NUMERO_ICA_LUGAR_PRODUCCION, String EXTENSION, String DOCUMENTO_PROPIETARIO){
        this.NUMERO_REGISTRO_ICA = NUMERO_REGISTRO_ICA;
        this.NUMERO_PREDIAL = NUMERO_PREDIAL;
        this.NOMBRE = NOMBRE;
        this.DEPARTAMENTO = DEPARTAMENTO;
        this.MUNICIPIO = MUNICIPIO;
        this.VEREDA = VEREDA;
        this.NUMERO_ICA_LUGAR_PRODUCCION = NUMERO_ICA_LUGAR_PRODUCCION;
        this.EXTENSION = EXTENSION;
        this.DOCUMENTO_PROPIETARIO = DOCUMENTO_PROPIETARIO;
        
    } 
    
    // --- Getters y Setters ---

    // NUMERO_REGISTRO_ICA
    public String getNUMERO_REGISTRO_ICA() {
        return NUMERO_REGISTRO_ICA;
    }

    public void setNUMERO_REGISTRO_ICA(String NUMERO_REGISTRO_ICA) {
        this.NUMERO_REGISTRO_ICA = NUMERO_REGISTRO_ICA;
    }

    // NUMERO_PREDIAL
    public String getNUMERO_PREDIAL() {
        return NUMERO_PREDIAL;
    }

    public void setNUMERO_PREDIAL(String NUMERO_PREDIAL) {
        this.NUMERO_PREDIAL = NUMERO_PREDIAL;
    }

    // NOMBRE
    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    // DEPARTAMENTO
    public String getDEPARTAMENTO() {
        return DEPARTAMENTO;
    }

    public void setDEPARTAMENTO(String DEPARTAMENTO) {
        this.DEPARTAMENTO = DEPARTAMENTO;
    }

    // MUNICIPIO
    public String getMUNICIPIO() {
        return MUNICIPIO;
    }

    public void setMUNICIPIO(String MUNICIPIO) {
        this.MUNICIPIO = MUNICIPIO;
    }

    // VEREDA
    public String getVEREDA() {
        return VEREDA;
    }

    public void setVEREDA(String VEREDA) {
        this.VEREDA = VEREDA;
    }

    // NUMERO_ICA_LUGAR_PRODUCCION
    public String getNUMERO_ICA_LUGAR_PRODUCCION() {
        return NUMERO_ICA_LUGAR_PRODUCCION;
    }

    public void setNUMERO_ICA_LUGAR_PRODUCCION(String NUMERO_ICA_LUGAR_PRODUCCION) {
        this.NUMERO_ICA_LUGAR_PRODUCCION = NUMERO_ICA_LUGAR_PRODUCCION;
    }

    // EXTENSION
    public String getEXTENSION() {
        return EXTENSION;
    }

    public void setEXTENSION(String EXTENSION) {
        this.EXTENSION = EXTENSION;
    }

    // DOCUMENTO_PROPIETARIO
    public String getDOCUMENTO_PROPIETARIO() {
        return DOCUMENTO_PROPIETARIO;
    }

    public void setDOCUMENTO_PROPIETARIO(String DOCUMENTO_PROPIETARIO) {
        this.DOCUMENTO_PROPIETARIO = DOCUMENTO_PROPIETARIO;
    }
    
}
