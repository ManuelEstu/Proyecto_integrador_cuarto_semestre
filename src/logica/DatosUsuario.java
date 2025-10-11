/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
// Clase DatosUsuario (Modelo)
public class DatosUsuario {
    // Campos de identificación
        public String documento; // Usado solo en la cláusula WHERE (NO se actualiza)
        public String tipoUsuario; // Tipo de usuario (para determinar la tabla)

        // Campos que SÍ se pueden editar
        public String nombreUsuario;
        public String clave; // ⚠️ ¡Manejar la clave de forma segura en la aplicación!
        public String nombre;
        public String apellido;
        public String telefono;
        public String correo;

        // La Tarjeta Profesional se incluye solo en la consulta 'buscarPerfil', pero NO en el 'update'.
        // Se mantiene aquí para consistencia si se usa este DTO para pasar datos completos, 
        // pero NO será mapeado a la sentencia UPDATE.
        public String tarjetaProfesional; 

        // Constructor
        public DatosUsuario(String documento, String tipoUsuario, String nombreUsuario, String clave, String nombre, 
                            String apellido, String telefono, String correo, String tarjetaProfesional) {
            this.documento = documento;
            this.tipoUsuario = tipoUsuario;
            this.nombreUsuario = nombreUsuario;
            this.clave = clave;
            this.nombre = nombre;
            this.apellido = apellido;
            this.telefono = telefono;
            this.correo = correo;
            this.tarjetaProfesional = tarjetaProfesional;
    }
    
    // **Nota Importante:** Aunque el DAO solo usa 7 campos para el UPDATE (los que estás editando),
    // es buena práctica enviar todos los datos disponibles.
}
