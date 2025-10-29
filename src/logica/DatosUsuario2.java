/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
public class DatosUsuario2 {
    private String tipo; // Para saber de qué tabla proviene
    private String nroDocumento;
    private String nombreUsuario;
    private String clave;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String infoAdicional; // Para el 'Nro_tarjeta_profesional' del Técnico

    // Constructor vacío
    public DatosUsuario2() {
    }

    // Constructor completo (puedes adaptarlo según necesites)
    public DatosUsuario2(String tipo, String nroDocumento, String nombreUsuario, String clave, String nombre, String apellido, String telefono, String email, String infoAdicional) {
        this.tipo = tipo;
        this.nroDocumento = nroDocumento;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.infoAdicional = infoAdicional;
    }

    // --- Getters y Setters ---

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
}
