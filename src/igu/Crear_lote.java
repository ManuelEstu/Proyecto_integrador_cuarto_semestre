/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package igu;

/**
 *
 * @author CASA
 */
import logica.ControladorCrearLote;
import logica.Planta;
// Asegúrate de que esta clase exista y esté correctamente importada
import logica.LugarCajita; 
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.MutableComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

public class Crear_lote extends javax.swing.JFrame {
    private String documento;
    private ControladorCrearLote control;
    private List<Planta> todasLasPlantas;
    private List<LugarCajita> todosLosLugares;
    /**
     * Creates new form Crear_lote
     */
   public Crear_lote() {
        this.control = new ControladorCrearLote();
        initComponents();
        Fech_recoleccion.setEditable(false);
        Txt_cantidadR.setEditable(false);
        Fech_recoleccion.setEnabled(false);
        Txt_cantidadR.setEnabled(false);
        
        cargarPlantasEnComboBox(); // Carga inicial de datos
        configurarFiltradoComboBox(); // 👈 Nuevo: Configura el filtrado
    }
    
    public Crear_lote(String documento) {
        this.documento = documento;
        this.control = new ControladorCrearLote();
        initComponents();
        Fech_recoleccion.setEditable(false);
        Txt_cantidadR.setEditable(false);
        Fech_recoleccion.setEnabled(false);
        Txt_cantidadR.setEnabled(false);

        cargarPlantasEnComboBox(); // Carga inicial de datos
        configurarFiltradoComboBox(); // 👈 Nuevo: Configura el filtrado
        cargarLugaresEnComboBox(documento); 
        configurarFiltradoLugarComboBox();
    }
    
    private void cargarLugaresEnComboBox(String documentoProductor) {
        if (documentoProductor == null || documentoProductor.trim().isEmpty()) {
             JOptionPane.showMessageDialog(this, 
                     "No se puede cargar la lista de lugares sin el documento del productor.", 
                     "Error de Datos", 
                     JOptionPane.ERROR_MESSAGE);
             return;
        }
        
        try {
            this.todosLosLugares = control.cargarLugares(documentoProductor);
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            model.addElement("--- Escriba para filtrar ---"); 

            for (LugarCajita lugar : todosLosLugares) {
                model.addElement(lugar); 
            }

            Lugar_pertenece.setModel((javax.swing.DefaultComboBoxModel) model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al cargar los lugares de producción: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarFiltradoLugarComboBox() {
        // 1. Hacemos el JComboBox editable
        Lugar_pertenece.setEditable(true);

        // 2. Obtenemos el componente de texto
        final JTextComponent editor = (JTextComponent) Lugar_pertenece.getEditor().getEditorComponent();

        // 3. Agregamos el KeyListener
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Filtra la lista cada vez que se presiona una tecla
                filtrarLugares(editor.getText());
            }
        });
        
        // 4. El JComboBox debe estar inicialmente cerrado al cargarse
        Lugar_pertenece.hidePopup();
    }
    
    private void filtrarLugares(String texto) {
        String filtro = texto.toLowerCase();

        if (this.todosLosLugares == null) {
            return; 
        }

        MutableComboBoxModel model = new DefaultComboBoxModel();

        // 1. Filtrar los lugares por el número de registro ICA (o el campo que uses en toString())
        for (LugarCajita lugar : this.todosLosLugares) {
            // Se asume que LugarCajita tiene un método para obtener su identificador (ej: getNumRegistroICA())
            if (lugar.getNombre().toLowerCase().startsWith(filtro)) { 
                model.addElement(lugar);
            }
        }

        // 2. Asignar el nuevo modelo al JComboBox.
        Lugar_pertenece.setModel((javax.swing.DefaultComboBoxModel) model); 

        // 3. Manejo del editor y el popup
        JTextComponent editor = (JTextComponent) Lugar_pertenece.getEditor().getEditorComponent();

        if (!filtro.isEmpty()) {
            editor.setText(texto);
            editor.setCaretPosition(texto.length());
            Lugar_pertenece.setPopupVisible(model.getSize() > 0);
        } else {
            editor.setText("");
            Lugar_pertenece.setPopupVisible(true);
        }
    }
    
   private void cargarPlantasEnComboBox() {
        try {
            // 1. Obtener y guardar la lista completa de plantas (CACHÉ)
            this.todasLasPlantas = control.cargarPlantas();

            // 2. Crear un modelo para el ComboBox
            DefaultComboBoxModel model = new DefaultComboBoxModel();

            // Opcional: añade un elemento por defecto o instrucción
            model.addElement("--- Escriba para filtrar ---"); 

            // 3. Llenar el modelo con todas las plantas
            for (Planta planta : todasLasPlantas) {
                model.addElement(planta);
            }

            // 4. Asignar el modelo al JComboBox (usando el cast compatible)
            Planta.setModel((javax.swing.DefaultComboBoxModel) model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error al cargar las plantas: " + e.getMessage(), 
                    "Error de Base de Datos", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void configurarFiltradoComboBox() {
        // Hacemos el JComboBox editable para que el usuario pueda escribir
        Planta.setEditable(true);

        // Obtenemos el componente de texto dentro del JComboBox
        final JTextComponent editor = (JTextComponent) Planta.getEditor().getEditorComponent();

        // Agregamos un KeyListener al componente de texto
        editor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Filtra la lista cada vez que se presiona una tecla
                filtrarPlantas(editor.getText());
            }
        });
        
        // El JComboBox debe estar inicialmente cerrado al cargarse
        Planta.hidePopup();
    }
    
    private void filtrarPlantas(String texto) {
        String filtro = texto.toLowerCase();

        // Si la lista de caché no existe, salimos.
        if (this.todasLasPlantas == null) {
            return; 
        }

        // Usamos el tipo sin genéricos para mayor compatibilidad con el JComboBox de NetBeans.
        MutableComboBoxModel model = new DefaultComboBoxModel();

        // 1. Filtrar las plantas usando la lista precargada (Filtro 'Comienza con')
        for (Planta planta : this.todasLasPlantas) { 
            if (planta.getNombreComun().toLowerCase().startsWith(filtro)) { // 👈 CORRECCIÓN A startsWith()
                model.addElement(planta);
            }
        }

        // 2. Asignar el nuevo modelo al JComboBox.
        // CAST NECESARIO: Resuelve el error de tipos.
        Planta.setModel((javax.swing.DefaultComboBoxModel) model); 

        // 3. Manejo del editor y el popup
        JTextComponent editor = (JTextComponent) Planta.getEditor().getEditorComponent();

        if (!filtro.isEmpty()) {
            // Mantiene el texto escrito y selecciona lo que coincida.
            editor.setText(texto);
            editor.setCaretPosition(texto.length()); // Coloca el cursor al final

            // Muestra el popup solo si hay elementos filtrados
            Planta.setPopupVisible(model.getSize() > 0);

        } else {
            // Si el texto está vacío, muestra el popup con todos los elementos.
            editor.setText("");
            Planta.setPopupVisible(true);
        }
    }
    
    private void limpiarCampos() {
        // Campos de texto (JTextField)
        Txt_num_lote.setText("");
        Txt_area.setText("");
        Txt_cantidad_plantas.setText("");
        Fech_siembra.setText("");
        Fech_eliminacion.setText("");
        Fech_p_recoleccion.setText("");
        Txt_cantidadP.setText("");
        Fech_recoleccion.setText(""); // Aunque está deshabilitado, es buena práctica
        Txt_cantidadR.setText("");   // Aunque está deshabilitado, es buena práctica

        // Campos de selección (JComboBox)
        // Se establece el primer ítem, que suele ser el mensaje de "Escriba para filtrar" o el primer elemento cargado.
        Planta.setSelectedIndex(0); 
        Lugar_pertenece.setSelectedIndex(0);
        Estado.setSelectedIndex(0); // Si 'Crecimiento' es el primer estado por defecto, si no, usa el índice correcto.

        // Si los JComboBox tienen autocompletado, esto limpia el texto del editor:
        ((javax.swing.text.JTextComponent) Planta.getEditor().getEditorComponent()).setText("");
        ((javax.swing.text.JTextComponent) Lugar_pertenece.getEditor().getEditorComponent()).setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Txt_area = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Planta = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Btn_cancelar = new javax.swing.JButton();
        Btn_crear = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Txt_num_lote = new javax.swing.JTextField();
        Lugar_pertenece = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        Estado = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Txt_cantidad_plantas = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Txt_cantidadP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Txt_cantidadR = new javax.swing.JTextField();
        Fech_siembra = new javax.swing.JTextField();
        Fech_eliminacion = new javax.swing.JTextField();
        Fech_p_recoleccion = new javax.swing.JTextField();
        Fech_recoleccion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 112));
        jLabel1.setText("Creación de lotes");

        jLabel2.setText("lugar producción pertenece:");

        jLabel3.setText("Área lote (hectáreas): ");

        jLabel4.setText("Planta cultivada: ");

        Planta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Fecha de siembra (DD/MM/AAAA): ");

        jLabel6.setText("Fecha eliminación (opcional):");

        Btn_cancelar.setBackground(new java.awt.Color(51, 153, 0));
        Btn_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_cancelar.setText("Cancelar");
        Btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_cancelarActionPerformed(evt);
            }
        });

        Btn_crear.setBackground(new java.awt.Color(51, 153, 0));
        Btn_crear.setForeground(new java.awt.Color(255, 255, 255));
        Btn_crear.setText("Crear");
        Btn_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_crearActionPerformed(evt);
            }
        });

        jLabel7.setText("Nro de lote:");

        Lugar_pertenece.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Estado:");

        Estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crecimiento", "Espera" }));

        jLabel9.setText("Número de plantas:");

        jLabel10.setText("Fecha proyectada recolección:");

        jLabel11.setText("Cantidad proyectada recolección:");

        jLabel12.setText("Fecha recolección:");

        jLabel13.setText("Cantidad recolectada:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(Btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Btn_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Txt_area)
                            .addComponent(Planta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Txt_num_lote)
                            .addComponent(Lugar_pertenece, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Estado, 0, 127, Short.MAX_VALUE)
                            .addComponent(Txt_cantidad_plantas)
                            .addComponent(Txt_cantidadP)
                            .addComponent(Txt_cantidadR)
                            .addComponent(Fech_siembra)
                            .addComponent(Fech_eliminacion)
                            .addComponent(Fech_p_recoleccion)
                            .addComponent(Fech_recoleccion))))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Txt_num_lote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Planta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Lugar_pertenece, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Txt_area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Fech_siembra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Fech_eliminacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Txt_cantidad_plantas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Fech_p_recoleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(Txt_cantidadP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(Fech_recoleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(Txt_cantidadR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_cancelar)
                    .addComponent(Btn_crear))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_cancelarActionPerformed
        // Cierra esta ventana
        this.dispose();
        // Abre la otra ventana
        Administrar_lotes adminL = new Administrar_lotes(this.documento);
        adminL.setVisible(true);
        adminL.setLocationRelativeTo(null);
    }//GEN-LAST:event_Btn_cancelarActionPerformed

    private void Btn_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_crearActionPerformed
        // 1. OBTENER Y VALIDAR OBJETOS SELECCIONADOS (Planta y Lugar)
        Object plantaSeleccionadaObj = Planta.getSelectedItem();
        Object lugarSeleccionadoObj = Lugar_pertenece.getSelectedItem(); 

        // **Validación 1.1: Planta Seleccionada**
        if (!(plantaSeleccionadaObj instanceof Planta)) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una planta válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // **Validación 1.2: Lugar Seleccionado**
        if (!(lugarSeleccionadoObj instanceof LugarCajita)) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un lugar de producción válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Casteo seguro y extracción de IDs clave
        Planta plantaSeleccionada = (Planta) plantaSeleccionadaObj;
        LugarCajita lugarSeleccionado = (LugarCajita) lugarSeleccionadoObj;

        // Datos identificadores (los que guardarás en el Lote)
        int idPlanta = plantaSeleccionada.getIdPlanta();
        String registroIcaLugar = lugarSeleccionado.getNumeroRegistroIca(); 

        // 2. OBTENER VALORES DE TEXTO
        String nroLote = Txt_num_lote.getText();
        String areaLote = Txt_area.getText();
        String cantidadPlantas = Txt_cantidad_plantas.getText();
        String fechaSiembra = Fech_siembra.getText();
        String fechaEliminacion = Fech_eliminacion.getText(); // Puede ser vacío
        String fechaProyRecoleccion = Fech_p_recoleccion.getText();
        String cantidadProyRecoleccion = Txt_cantidadP.getText();
        String estado = Estado.getSelectedItem().toString(); 

        // Nota: Fech_recoleccion y Txt_cantidadR (recolectada) se ignoran en la creación,
        // ya que el estado inicial es 'Crecimiento' o 'Espera'.

        // 3. VALIDAR DATOS USANDO EL CONTROLADOR (ControladorCrearLote)

        // **Validación 3.1: Números Enteros**
        if (!control.validarNumeroEntero(nroLote)) {
            JOptionPane.showMessageDialog(this, "El 'Nro de lote' es inválido. Debe ser un número entero positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!control.validarNumeroEntero(cantidadPlantas)) {
            JOptionPane.showMessageDialog(this, "La 'Cantidad de plantas' es inválida. Debe ser un número entero positivo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // **Validación 3.2: Números Decimales (usando PUNTO '.' para Oracle)**
        if (!control.validarNumeroDecimal(areaLote)) {
            JOptionPane.showMessageDialog(this, "El 'Área lote' es inválida. Debe ser un número decimal positivo con hasta 2 decimales, usando PUNTO (.).", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!control.validarNumeroDecimal(cantidadProyRecoleccion)) {
            JOptionPane.showMessageDialog(this, "La 'Cantidad proyectada recolección' es inválida. Debe ser un número decimal positivo con hasta 2 decimales, usando PUNTO (.).", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // **Validación 3.3: Fechas (Formato DD/MM/AAAA)**
        if (!control.validarFormatoFecha(fechaSiembra)) {
            JOptionPane.showMessageDialog(this, "La 'Fecha de siembra' es inválida. Use DD/MM/AAAA y revise los días por mes.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!control.validarFormatoFecha(fechaProyRecoleccion)) {
            JOptionPane.showMessageDialog(this, "La 'Fecha proyectada recolección' es inválida. Use DD/MM/AAAA.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Fecha de Eliminación es opcional
        if (!fechaEliminacion.trim().isEmpty() && !control.validarFormatoFecha(fechaEliminacion)) {
            JOptionPane.showMessageDialog(this, "La 'Fecha de eliminación' es inválida. Use DD/MM/AAAA o déjela vacía.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. LLAMADA AL CONTROLADOR DE NEGOCIO (Asumiendo que ya tienes este método implementado)
        try {
            // Llamada al método de negocio para guardar el lote en la DB
            control.crearLote(
                nroLote, 
                idPlanta, 
                registroIcaLugar, 
                areaLote, 
                cantidadPlantas, 
                fechaSiembra, 
                estado, 
                fechaProyRecoleccion, 
                cantidadProyRecoleccion, 
                fechaEliminacion // Puede ser una cadena vacía o nula si el campo estaba vacío
            );
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Lote creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Opcional: Limpiar campos o navegar a otra vista

        } catch (Exception e) {
            // Captura cualquier excepción lanzada por el controlador (ej. error de DB, lote duplicado)
            JOptionPane.showMessageDialog(this, "Error al crear el lote: " + e.getMessage(), "Error de Creación", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_Btn_crearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Crear_lote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Crear_lote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Crear_lote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Crear_lote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Crear_lote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_cancelar;
    private javax.swing.JButton Btn_crear;
    private javax.swing.JComboBox<String> Estado;
    private javax.swing.JTextField Fech_eliminacion;
    private javax.swing.JTextField Fech_p_recoleccion;
    private javax.swing.JTextField Fech_recoleccion;
    private javax.swing.JTextField Fech_siembra;
    private javax.swing.JComboBox<String> Lugar_pertenece;
    private javax.swing.JComboBox<String> Planta;
    private javax.swing.JTextField Txt_area;
    private javax.swing.JTextField Txt_cantidadP;
    private javax.swing.JTextField Txt_cantidadR;
    private javax.swing.JTextField Txt_cantidad_plantas;
    private javax.swing.JTextField Txt_num_lote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
