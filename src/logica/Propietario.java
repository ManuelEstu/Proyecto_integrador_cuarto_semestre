/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class Propietario extends Funcionario_ICA {

    // Constructor que llama al constructor de la clase padre
    public Propietario(long nroDeDocumento, String nombreDeUsuario, String clave, String nombre, String apellido, long telefono, String email) {
        // Llama al constructor de la superclase para inicializar los atributos heredados
        super(nroDeDocumento, nombreDeUsuario, clave, nombre, apellido, telefono, email);
    }
}
