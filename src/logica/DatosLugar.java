/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class DatosLugar {
    public String numero_registro_ICA;
    public String numero_predial;
    public String nombre;
    public String nombre_empresa;
    public String telefono_empresa;
    public String departamento;
    public String municipio;
    public String vereda;
    public String documento_productor;
    
     public DatosLugar(String numero_registro_ICA, String numero_predial, String nombre, String nombre_empresa, String telefono_empresa, 
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
}
