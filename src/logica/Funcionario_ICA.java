/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class Funcionario_ICA {

    // Atributos
    private long nroDeDocumento;
    private String nombreDeUsuario;
    private String clave;
    private String nombre;
    private String apellido;
    private long telefono;
    private String email;

    // Constructor para inicializar los atributos
    public Funcionario_ICA(long nroDeDocumento, String nombreDeUsuario, String clave, String nombre, String apellido, long telefono, String email) {
        this.nroDeDocumento = nroDeDocumento;
        this.nombreDeUsuario = nombreDeUsuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    // Métodos getters para acceder a los atributos
    public long getNroDeDocumento() {
        return nroDeDocumento;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    // Métodos setters para modificar los atributos
    public void setNroDeDocumento(int nroDeDocumento) {
        this.nroDeDocumento = nroDeDocumento;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
