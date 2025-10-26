/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class DatosPredio {
    public String numero_registro_ICA;
    public String numero_predial;
    public String nombre;
    public String departamento;
    public String municipio;
    public String vereda;
    public String numero_ica_lugar_produccion;
    public String extension;
    public String documento_propietario;
    
    public DatosPredio(String numero_registro_ICA, String numero_predial, String nombre, 
                            String departamento, String municipio, String vereda, String numero_ica_lugar_produccion, String extension, String documento_propietario) {
            this.numero_registro_ICA = numero_registro_ICA;
            this.numero_predial = numero_predial;
            this.nombre = nombre;
            this.departamento = departamento;
            this.municipio = municipio;
            this.vereda = vereda;
            this.numero_ica_lugar_produccion = numero_ica_lugar_produccion;
            this.extension = extension;
            this.documento_propietario = documento_propietario;
    }
}
