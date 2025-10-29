/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class DatosLugar2 {
    public String numero_registro_ICA;
    public String numero_predial;
    public String nombre;
    public String nombre_empresa;
    public String telefono_empresa;
    public String departamento;
    public String municipio;
    public String vereda;
    public String documento_productor;
    
    public DatosLugar2(){
    
    }
    
    public DatosLugar2(String numero_registro_ICA, String numero_predial, String nombre, String nombre_empresa, String telefono_empresa, 
                            String departamento, String municipio, String vereda, String documento_productor) {
            this.numero_registro_ICA = numero_registro_ICA;
            this.numero_predial = numero_predial;
            this.nombre = nombre;
            this.nombre_empresa = nombre_empresa;
            this.telefono_empresa = telefono_empresa;
            this.departamento = departamento;
            this.municipio = municipio;
            this.vereda = vereda;
            this.documento_productor = documento_productor;
    }
    
    // --- Getters y Setters ---

    /**
     * Obtiene el número de registro ICA.
     */
    public String getNumero_registro_ICA() {
        return numero_registro_ICA;
    }

    /**
     * Establece el número de registro ICA.
     */
    public void setNumero_registro_ICA(String numero_registro_ICA) {
        this.numero_registro_ICA = numero_registro_ICA;
    }

    /**
     * Obtiene el número predial.
     */
    public String getNumero_predial() {
        return numero_predial;
    }

    /**
     * Establece el número predial.
     */
    public void setNumero_predial(String numero_predial) {
        this.numero_predial = numero_predial;
    }

    /**
     * Obtiene el nombre del lugar.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del lugar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre de la empresa asociada.
     */
    public String getNombre_empresa() {
        return nombre_empresa;
    }

    /**
     * Establece el nombre de la empresa asociada.
     */
    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    /**
     * Obtiene el teléfono de la empresa.
     */
    public String getTelefono_empresa() {
        return telefono_empresa;
    }

    /**
     * Establece el teléfono de la empresa.
     */
    public void setTelefono_empresa(String telefono_empresa) {
        this.telefono_empresa = telefono_empresa;
    }

    /**
     * Obtiene el departamento.
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Establece el departamento.
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Obtiene el municipio.
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Establece el municipio.
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * Obtiene la vereda.
     */
    public String getVereda() {
        return vereda;
    }

    /**
     * Establece la vereda.
     */
    public void setVereda(String vereda) {
        this.vereda = vereda;
    }

    /**
     * Obtiene el documento del productor.
     */
    public String getDocumento_productor() {
        return documento_productor;
    }

    /**
     * Establece el documento del productor.
     */
    public void setDocumento_productor(String documento_productor) {
        this.documento_productor = documento_productor;
    }
    
}
