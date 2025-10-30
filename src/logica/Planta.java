/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class Planta {
    private int idPlanta;
    private String nombreComun;
    private String nombreCientifico;
    // Puedes incluir más campos si los necesitas, pero estos son esenciales por ahora.

    public Planta(int idPlanta, String nombreComun, String nombreCientifico) {
        this.idPlanta = idPlanta;
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
    }

    // Getters
    public int getIdPlanta() {
        return idPlanta;
    }

    public String getNombreComun() {
        return nombreComun;
    }
    
    // El JComboBox llama a este método para saber qué mostrar en la lista.
    @Override
    public String toString() {
        return nombreComun;
    }
}
