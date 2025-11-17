/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
/**
 * Clase que mapea los resultados de la consulta SQL para el informe de plantas.
 */
public class InformePlantas {

    private String nombrePlanta;
    private int cantidadLotes;
    private int cantidadLugares;

    // Constructor vacío (necesario en muchos frameworks)
    public InformePlantas() {
    }

    // Constructor con todos los campos
    public InformePlantas(String nombrePlanta, int cantidadLotes, int cantidadLugares) {
        this.nombrePlanta = nombrePlanta;
        this.cantidadLotes = cantidadLotes;
        this.cantidadLugares = cantidadLugares;
    }

    // Getters y Setters

    public String getNombrePlanta() {
        return nombrePlanta;
    }

    public void setNombrePlanta(String nombrePlanta) {
        this.nombrePlanta = nombrePlanta;
    }

    public int getCantidadLotes() {
        return cantidadLotes;
    }

    public void setCantidadLotes(int cantidadLotes) {
        this.cantidadLotes = cantidadLotes;
    }

    public int getCantidadLugares() {
        return cantidadLugares;
    }

    public void setCantidadLugares(int cantidadLugares) {
        this.cantidadLugares = cantidadLugares;
    }

    @Override
    public String toString() {
        return "Planta: " + nombrePlanta +
               ", Lotes: " + cantidadLotes +
               ", Predios: " + cantidadLugares;
    }
}
