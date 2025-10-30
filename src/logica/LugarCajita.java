/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class LugarCajita {
    private String numeroRegistroIca;
    private String numeroPredial;
    private String nombre;
    private String nombreEmpresa;
    // Agrega los demás campos si los vas a usar
    
    public LugarCajita(String numeroRegistroIca, String nombre) {
        this.numeroRegistroIca = numeroRegistroIca;
        this.nombre = nombre;
    }

    // Getters
    public String getNumeroRegistroIca() {
        return numeroRegistroIca;
    }

    public String getNombre() {
        return nombre;
    }
    
    // Sobreescribe toString para que el JComboBox muestre el valor deseado.
    // Usaremos el NÚMERO DE REGISTRO ICA, ya que es el identificador único.
    @Override
    public String toString() {
        return nombre; // Solo retorna el nombre
    }
}
