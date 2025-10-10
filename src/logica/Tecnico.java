/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class Tecnico extends Funcionario_ICA {

    // Atributo específico de la clase tecnico
    private long nroTarjetaProfesional;

    // Constructor que llama al constructor de la clase padre y añade el nuevo atributo
    public Tecnico(long nroDeDocumento, String nombreDeUsuario, String clave, String nombre, String apellido, long telefono, String email, long nroTarjetaProfesional) {
        // Llama al constructor de la clase padre (funcionario_ICA)
        super(nroDeDocumento, nombreDeUsuario, clave, nombre, apellido, telefono, email);
        this.nroTarjetaProfesional = nroTarjetaProfesional;
    }

    // Método getter para el atributo específico
    public long getNroTarjetaProfesional() {
        return nroTarjetaProfesional;
    }

    // Método setter para el atributo específico
    public void setNroTarjetaProfesional(int nroTarjetaProfesional) {
        this.nroTarjetaProfesional = nroTarjetaProfesional;
    }
}
