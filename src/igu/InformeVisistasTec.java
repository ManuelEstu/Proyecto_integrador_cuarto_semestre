/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package igu;

/**
 *
 * @author CASA
 */
import logica.InformeVisTec;

public class InformeVisistasTec extends javax.swing.JFrame {
    private String documento;
    private final InformeVisTec informe;
    /**
     * Creates new form InformeVisistasTec
     */
     public InformeVisistasTec() {
        initComponents();
        Informe_vis_tec.setEditable(false);
        this.informe = null; // No hay datos
    }
    
    public InformeVisistasTec(String documento, InformeVisTec informe) {
        initComponents();
        this.documento = documento;
        this.informe = informe; 
        Informe_vis_tec.setEditable(false);
        cargarInforme(); // Llamamos al método para llenar el JTextArea
        // El ICA se extrae directamente del objeto informe
        this.setTitle("Informe de Visita Técnica - ICA: " + informe.getIca());
    }
    
    private void cargarInforme() {
        if (informe == null) {
            Informe_vis_tec.setText("ERROR: No se proporcionaron datos de informe.");
            return;
        }

        // --- Extracción de datos ---
        
        String ica = informe.getIca();
        String fechaUltimaInspeccion = informe.getFechaUltimaInspeccion(); 
        String nombreLugar = informe.getNombreLugar();
        String nombreTecnico = informe.getNombreTecnico();
        String apellidoTecnico = informe.getApellidoTecnico();
        String comentarios = informe.getComentarios();
        
        // Resultados de inspección (String "S" o "N")
        String estadoAcopio = informe.getAreaAcopio();
        String estadoResiduos = informe.getAreaManResiduosVegetales();
        String estadoAlmacenamiento = informe.getAreaAlmacInsumosAgricolas();
        String estadoDosificacion = informe.getAreaDosifPrepMezclas();
        String estadoResiduosLavado = informe.getAreaResiduosMezLavado();
        String estadoSanitaria = informe.getAreaSanitariaLavamanos();
        String estadoHerramientas = informe.getAreaHerramientas();
        
        // --- Construcción del Contenido del Informe ---
        
        StringBuilder sb = new StringBuilder();
        sb.append("============================================================\n");
        sb.append("                 INFORME DE INSPECCIÓN TÉCNICA (Nro ICA: ").append(ica).append(")\n");
        sb.append("============================================================\n\n");
        
        sb.append(String.format("Lugar de producción:          %s\n", nombreLugar));
        sb.append(String.format("Técnico que realizó la visita: %s %s\n", nombreTecnico, apellidoTecnico));
        sb.append(String.format("Fecha de realización:          %s\n", fechaUltimaInspeccion));
        
        sb.append("\n------------------------------------------------------------\n");
        sb.append("Área                                | ¿En buen estado? \n");
        sb.append("------------------------------------------------------------\n");
        sb.append("                                    |   Sí  |   No \n");
        sb.append("------------------------------------------------------------\n");
        
        // Lógica CORREGIDA: Comprobamos 'S' para Sí y 'N' para No
        final String YES_MARK = "  X  ";
        final String NO_MARK = "     ";
        
        sb.append(String.format("Acopio:                             | %s | %s \n", estadoAcopio.equalsIgnoreCase("S") ? YES_MARK : NO_MARK, estadoAcopio.equalsIgnoreCase("N") ? YES_MARK : NO_MARK));
        sb.append(String.format("Manejo de residuos vegetales:       | %s | %s \n", estadoResiduos.equalsIgnoreCase("S") ? YES_MARK : NO_MARK, estadoResiduos.equalsIgnoreCase("N") ? YES_MARK : NO_MARK));
        sb.append(String.format("Almacenamiento de insumos agrícolas:| %s | %s \n", estadoAlmacenamiento.equalsIgnoreCase("S") ? YES_MARK : NO_MARK, estadoAlmacenamiento.equalsIgnoreCase("N") ? YES_MARK : NO_MARK));
        sb.append(String.format("Dosificación/preparación mezclas:   | %s | %s \n", estadoDosificacion.equalsIgnoreCase("S") ? YES_MARK : NO_MARK, estadoDosificacion.equalsIgnoreCase("N") ? YES_MARK : NO_MARK));
        sb.append(String.format("Residuos mezclas/lavado:            | %s | %s \n", estadoResiduosLavado.equalsIgnoreCase("S") ? YES_MARK : NO_MARK, estadoResiduosLavado.equalsIgnoreCase("N") ? YES_MARK : NO_MARK));
        sb.append(String.format("Sanitaria/lavamanos:                | %s | %s \n", estadoSanitaria.equalsIgnoreCase("S") ? YES_MARK : NO_MARK, estadoSanitaria.equalsIgnoreCase("N") ? YES_MARK : NO_MARK));
        sb.append(String.format("Herramientas:                       | %s | %s \n", estadoHerramientas.equalsIgnoreCase("S") ? YES_MARK : NO_MARK, estadoHerramientas.equalsIgnoreCase("N") ? YES_MARK : NO_MARK));
        
        sb.append("------------------------------------------------------------\n\n");
        
        sb.append("El técnico realizó las siguientes observaciones/comentarios:\n");
        sb.append("------------------------------------------------------------\n");
        // Aseguramos que el texto fluya correctamente
        sb.append(comentarios != null && !comentarios.isEmpty() ? comentarios : "No se registraron comentarios adicionales.");
        sb.append("\n------------------------------------------------------------\n");

        Informe_vis_tec.setText(sb.toString());
        Informe_vis_tec.setCaretPosition(0); // Scroll al inicio
    }
    
    /**
     * Método auxiliar para convertir boolean a String "Sí" o "No".
     */
    private String getEstado(boolean estado) {
        return estado ? "Sí" : "No";
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
        jScrollPane1 = new javax.swing.JScrollPane();
        Informe_vis_tec = new javax.swing.JTextArea();
        Btn_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Informe_vis_tec.setColumns(20);
        Informe_vis_tec.setRows(5);
        jScrollPane1.setViewportView(Informe_vis_tec);

        Btn_cancelar.setBackground(new java.awt.Color(51, 153, 0));
        Btn_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        Btn_cancelar.setText("Cancelar");
        Btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(Btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(241, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(Btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_cancelarActionPerformed
        this.dispose();
        BuscarInformeVisitaTec bInfVisTec = new BuscarInformeVisitaTec(this.documento);
        bInfVisTec.setVisible(true);
        bInfVisTec.setLocationRelativeTo(null);
    }//GEN-LAST:event_Btn_cancelarActionPerformed

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
            java.util.logging.Logger.getLogger(InformeVisistasTec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformeVisistasTec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformeVisistasTec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformeVisistasTec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformeVisistasTec().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_cancelar;
    private javax.swing.JTextArea Informe_vis_tec;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
