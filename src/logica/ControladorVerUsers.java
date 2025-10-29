/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import persistencia.UsuarioDao2;
import logica.DatosUsuario2;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ControladorVerUsers {
    
    private final UsuarioDao2 UsuarioDao2;

    public ControladorVerUsers() {
        this.UsuarioDao2 = new UsuarioDao2();
    }
    
    /**
     * Llama al DAO para buscar la lista de usuarios.
     * @param tipo El tipo de usuario seleccionado (ej: "Funcionario ICA").
     * @param documento El documento a buscar (puede ser null o vacío).
     * @return Lista de objetos Usuario.
     */
    public List<DatosUsuario2> buscarUsuarios(String tipo, String documento) {
        // La lógica de negocio aquí es mínima, solo delega al DAO
        return UsuarioDao2.buscarUsuarios(tipo, documento);
    }
    
    /**
     * Carga el DefaultTableModel con los datos de la lista de usuarios.
     * @param listaUsuarios Lista de objetos Usuario.
     * @return Un DefaultTableModel listo para ser asignado a la JTable.
     */
    public DefaultTableModel cargarTablaUsuarios(List<DatosUsuario2> listaUsuarios) {
        // Nuevas columnas: Se elimina "Tipo" y se añaden "Usuario" y "Clave"
        String[] titulos = {"Documento", "Nombre", "Apellido", "Usuario", "Clave", "Email", "Info Adicional"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
             // Esto hace que las celdas no sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };

        for (DatosUsuario2 user : listaUsuarios) {
            String infoAdicional = user.getInfoAdicional() != null ? user.getInfoAdicional() : "";
            
            Object[] fila = {
                user.getNroDocumento(),
                user.getNombre(),
                user.getApellido(),
                user.getNombreUsuario(), // ¡Nombre de usuario agregado!
                user.getClave(),         // ¡Clave agregada!
                user.getEmail(),
                // Lógica para mostrar N/A si es Técnico y no hay dato (o vacío si no aplica)
                user.getTipo().equals("Técnico") && infoAdicional.isEmpty() ? 
                    "N/A" : infoAdicional
            };
            modelo.addRow(fila);
        }
        
        return modelo;
    }
}
