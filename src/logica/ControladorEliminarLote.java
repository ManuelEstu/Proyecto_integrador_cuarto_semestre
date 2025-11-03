/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
// Archivo: ControladorEliminarLote.java
import persistencia.DaoLote;
import javax.swing.JOptionPane;

public class ControladorEliminarLote {

    private final DaoLote daoLote;

    public ControladorEliminarLote() {
        this.daoLote = new DaoLote();
    }

    /**
     * Valida que el texto sea un número (String con solo dígitos) y llama al DAO.
     * @param idLoteTexto El texto obtenido del Txt_id_lote (String).
     */
    public boolean procesarEliminacion(String idLoteTexto) {
        
        // 1. Validar que el campo no esté vacío
        if (idLoteTexto == null || idLoteTexto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el ID del lote.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return false; // Error de validación
        }

        String idLoteLimpio = idLoteTexto.trim();
        
        // 2. Validar que el String contenga SÓLO dígitos
        if (!idLoteLimpio.matches("\\d+")) {
             JOptionPane.showMessageDialog(null, "El ID del lote debe contener sólo números (como texto).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false; // Error de formato
        }

        // 3. Llamar al DAO para realizar la baja lógica
        boolean exito = daoLote.eliminarLote(idLoteLimpio);

        if (exito) {
            JOptionPane.showMessageDialog(null, "El Lote con ID " + idLoteLimpio + " ha sido marcado como Eliminado.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true; // Éxito
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la eliminación del lote. Revise si el ID existe o consulte el log de errores.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false; // Error en la base de datos o ID no encontrado
        }
    }
}
