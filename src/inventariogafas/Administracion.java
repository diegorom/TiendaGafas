/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariogafas;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author diego
 */
public class Administracion extends javax.swing.JDialog {

    /**
     * Creates new form Administracion
     */
    JPanel PanelControl = new JPanel();
    GestionGafas gestion = new GestionGafas();
    Gafas gafa = new Gafas();
    Tipo tipo = new Tipo();
    GestionTipo gestionTipo = new GestionTipo();
    Conexion conexion = new Conexion();
    private boolean hayCambios = false;
    private boolean aceptado = false;
    String tipoSelecionado;

    public Gafas getGafa() {
        return gafa;
    }

    public void activarCampos(boolean activar) {
        jLabelModelo.setEnabled(activar);
        jLabelMarca.setEnabled(activar);
        jLabelPrecio.setEnabled(activar);
        jLabelColor.setEnabled(activar);
        jLabelGenero.setEnabled(activar);
        jLabelMaterial.setEnabled(activar);
        jLabelForma.setEnabled(activar);
        //jLabelTipo.setEnabled(activar);
        if (activar) {
            hayCambios = true;
        }
    }

    private void aceptar() {
        aceptado = true;
        //Si todos los datos que ha introducido el usuario cumplen con
        //  los tamaños establecidos, se cierra la ventana. En caso contrario,
        //  se avisa al usuario del problema y se mantiene en la ventana
        gafa.getIdGafas();
        gafa.setModelo(jLabelModelo.getText());
        gafa.setMarca(jLabelMarca.getText());
        gafa.setPrecio(Integer.valueOf(jLabelPrecio.getText()));
        gafa.setColor(jLabelColor.getText());
        gafa.setGenero(jLabelGenero.getText());
        gafa.setMaterial(jLabelMaterial.getText());
        gafa.setForma(jLabelForma.getText());
        gafa.setTipo(Integer.valueOf(tipoSelecionado));
        this.setVisible(false);

    }

    public void setGafa(Gafas gafa) {
        this.gafa = gafa;
        jLabelModelo.setText(gafa.getModelo());
        jLabelMarca.setText(gafa.getMarca());
        jLabelPrecio.setText("" + gafa.getPrecio());
        jLabelColor.setText(gafa.getColor());
        jLabelGenero.setText(gafa.genero);
        jLabelMaterial.setText(gafa.material);
        jLabelForma.setText(gafa.forma);
        //jLabelTipo.setText(gafa.getTipo());
        this.listaTipo();
    }

    public boolean isAceptado() {
        return aceptado;
    }

    private void cancelar() {
        this.setVisible(false);
    }
    
    public void listaTipo(){
        int numElementos = gestionTipo.selecTipo().size();
        Tipo[] arrayParaJComboBox = new Tipo[numElementos];
        gestionTipo.selecTipo().toArray(arrayParaJComboBox);
        ComboBoxModel c = new DefaultComboBoxModel(arrayParaJComboBox);
        jComboBox1.setModel(c);
        for (int i = 0; i < gestionTipo.selecTipo().size(); i++) {

            int id_tipo = gestionTipo.selecTipo().get(i).getIdTipo();
            int id_TipoGafa = gafa.getIdGafas();
            if (id_tipo == id_TipoGafa) {
                jComboBox1.setSelectedIndex(i);
            }

            tipoSelecionado = jComboBox1.getSelectedItem().toString();
            /*this.mostrarDatosContacto();*/
        }
    }

    public Administracion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        conexion.conectar("localhost", "root", "");
    /**
     *
     */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelModelo = new javax.swing.JTextField();
        jLabelMarca = new javax.swing.JTextField();
        jLabelPrecio = new javax.swing.JTextField();
        jLabelColor = new javax.swing.JTextField();
        jLabelGenero = new javax.swing.JTextField();
        jLabelMaterial = new javax.swing.JTextField();
        jLabelForma = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("Tipo:");

        jLabel7.setText("Forma:");

        jLabel6.setText("Material:");

        jLabel5.setText("Genero:");

        jLabel4.setText("Color:");

        jLabel1.setText("Modelo:");

        jLabel2.setText("Marca:");

        jLabel3.setText("Precio:");

        jLabelModelo.setEnabled(false);
        jLabelModelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelModeloMouseClicked(evt);
            }
        });
        jLabelModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelModeloActionPerformed(evt);
            }
        });
        jLabelModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabelModeloKeyPressed(evt);
            }
        });

        jLabelMarca.setEnabled(false);
        jLabelMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMarcaMouseClicked(evt);
            }
        });

        jLabelPrecio.setEnabled(false);
        jLabelPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPrecioMouseClicked(evt);
            }
        });

        jLabelColor.setEnabled(false);
        jLabelColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelColorMouseClicked(evt);
            }
        });

        jLabelGenero.setEnabled(false);
        jLabelGenero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelGeneroMouseClicked(evt);
            }
        });

        jLabelMaterial.setEnabled(false);
        jLabelMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMaterialMouseClicked(evt);
            }
        });

        jLabelForma.setEnabled(false);
        jLabelForma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFormaMouseClicked(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPrecio))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMarca))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelGenero))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelColor))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMaterial))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelForma)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(3, 3, 3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabelForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cancelar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        aceptar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabelModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelModeloActionPerformed

    private void jLabelModeloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabelModeloKeyPressed
        // TODO add your handling code here:
        activarCampos(true);
    }//GEN-LAST:event_jLabelModeloKeyPressed

    private void jLabelModeloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelModeloMouseClicked
        // TODO add your handling code here:
        activarCampos(true);
    }//GEN-LAST:event_jLabelModeloMouseClicked

    private void jLabelMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMarcaMouseClicked
        // TODO add your handling code here:
        activarCampos(true);
    }//GEN-LAST:event_jLabelMarcaMouseClicked

    private void jLabelPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPrecioMouseClicked
        // TODO add your handling code here:
        activarCampos(true);
    }//GEN-LAST:event_jLabelPrecioMouseClicked

    private void jLabelColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelColorMouseClicked
        // TODO add your handling code here:
        activarCampos(true);
    }//GEN-LAST:event_jLabelColorMouseClicked

    private void jLabelGeneroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelGeneroMouseClicked
        // TODO add your handling code here:
        activarCampos(true);
    }//GEN-LAST:event_jLabelGeneroMouseClicked

    private void jLabelMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMaterialMouseClicked
        // TODO add your handling code here:
        activarCampos(true);
    }//GEN-LAST:event_jLabelMaterialMouseClicked

    private void jLabelFormaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFormaMouseClicked
        // TODO add your handling code here:
        activarCampos(true);
    }//GEN-LAST:event_jLabelFormaMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (hayCambios) {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Desea guardar los cambios?",
                    "Atención", JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            switch (respuesta) {
                case JOptionPane.YES_OPTION:
                    aceptar();
                    break;
                case JOptionPane.NO_OPTION:
                    cancelar();
                    break;
                case JOptionPane.CANCEL_OPTION:
                    break;
            }
        } else {
            cancelar();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Administracion dialog = new Administracion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jLabelColor;
    private javax.swing.JTextField jLabelForma;
    private javax.swing.JTextField jLabelGenero;
    private javax.swing.JTextField jLabelMarca;
    private javax.swing.JTextField jLabelMaterial;
    private javax.swing.JTextField jLabelModelo;
    private javax.swing.JTextField jLabelPrecio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
