/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author CASA
 */
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase auxiliar para añadir funcionalidad de filtro/autocompletado a un JComboBox.
 */
public class FiltroDepartamento {

    public static void habilitarFiltro(JComboBox<String> comboBox) {
        // Asegura que el JComboBox sea editable
        comboBox.setEditable(true);

        // Obtiene el modelo original y el editor (JTextComponent)
        final DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox.getModel();
        final JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();

        // Almacena la lista completa de ítems para poder filtrar
        final List<String> itemsOriginales = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            itemsOriginales.add(model.getElementAt(i));
        }

        // Listener para capturar lo que el usuario escribe
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                SwingUtilities.invokeLater(() -> {
                    String textoIngresado = editor.getText().toLowerCase();
                    List<String> itemsFiltrados = new ArrayList<>();

                    // Filtra los elementos que comienzan con el texto ingresado
                    for (String item : itemsOriginales) {
                        if (item.toLowerCase().startsWith(textoIngresado)) {
                            itemsFiltrados.add(item);
                        }
                    }

                    // Reconstruye el modelo con los elementos filtrados
                    DefaultComboBoxModel<String> nuevoModel = new DefaultComboBoxModel<>();
                    for (String item : itemsFiltrados) {
                        nuevoModel.addElement(item);
                    }
                    
                    // Solo actualiza si hay un cambio y previene el reseteo
                    if (!nuevoModel.equals(comboBox.getModel())) {
                        // Guarda la posición actual antes de actualizar el modelo
                        int caretPos = editor.getCaretPosition();
                        
                        comboBox.setModel(nuevoModel);
                        
                        // Reajusta el texto ingresado y el cursor
                        editor.setText(textoIngresado);
                        editor.setCaretPosition(Math.min(caretPos, editor.getText().length()));
                        
                        // Si hay texto, abre el desplegable
                        if (textoIngresado.length() > 0) {
                            comboBox.showPopup();
                        } else {
                            // Si el texto se borra, vuelve a cargar todos los items y cierra
                            comboBox.setModel((DefaultComboBoxModel<String>) model);
                            comboBox.hidePopup();
                        }
                    }
                });
            }
        });
    }
}
