/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariogafas;

import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author diego
 */
public class PanelControl extends javax.swing.JPanel {

    /**
     * Creates new form PanelControl
     */
    Gafas gafa = new Gafas();
    Gafas gafaSeleccionada = new Gafas();
    Conexion conexion = new Conexion();
    GestionGafas gestion = new GestionGafas();
    DefaultTableModel modeloTabla;
    Administracion ventana = new Administracion(Frame.getFrames()[0], true);

    void CargarDatosJTable() {
        gestion.selecGafas();
        ArrayList<Gafas> listaGafas = new ArrayList();
        modeloTabla = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        jTable1.setModel(modeloTabla);

        //Rellenar las cabeceras de las columnas
        String[] cabecera = {"IdGafas", "Modelo", "Marca", "Precio", "Color", "Genero", "Material", "Forma", "Tipo"};
        modeloTabla.setColumnIdentifiers(cabecera);

        listaGafas = gestion.selecGafas();
        //Recorrer la lista de contactos para añadir algunos datos en el JTable
        for (Gafas nueva : listaGafas) {
            //Se va mostrar sólo el nombre y los apellidos
            String[] datosFilaContacto = {
                String.valueOf(nueva.getIdGafas()),
                nueva.getModelo(),
                nueva.getMarca(),
                String.valueOf(nueva.getPrecio()),
                nueva.getColor(),
                nueva.getGenero(),
                nueva.getMaterial(),
                nueva.getForma(),
                nueva.getTipo()};
            modeloTabla.addRow(datosFilaContacto);
        }

        //Establecer que sólo se pueda seleccionar una fila
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Ocultar columna de idContacto
        TableColumn tc = jTable1.getColumn("IdGafas");
        jTable1.removeColumn(tc);

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(7).setPreferredWidth(200);
    }

    void mostrarDatosContacto() {
        //Obtener número de fila seleccionada en el JTable
        int numFilaSelec = jTable1.getSelectedRow();
        //Comprobar que el usuario ha seleccionado alguna fila
        if (numFilaSelec != -1) {
            //Obtener el contacto correspondiente a la fila seleccionada
            Gafas gafa = gestion.selecGafaById(
                    Integer.valueOf((String) modeloTabla.getValueAt(numFilaSelec, 0)));
            jLabelModelo.setText(gafa.getModelo());
            jLabelMarca.setText(gafa.getMarca());
            jLabelPrecio.setText("" + gafa.getPrecio());
            jLabelColor.setText(gafa.getColor());
            jLabelGenero.setText(gafa.genero);
            jLabelMaterial.setText(gafa.material);
            jLabelForma.setText(gafa.forma);
            jLabelTipo.setText(gafa.getTipo());
        } else {
            jLabelModelo.setText("");
            jLabelMarca.setText("");
            jLabelPrecio.setText("");
            jLabelColor.setText("");
            jLabelGenero.setText("");
            jLabelMaterial.setText("");
            jLabelForma.setText("");
            jLabelTipo.setText("");
        }
    }

    void Insertar() {
        Administracion ventana = new Administracion(Frame.getFrames()[0], true);
        //Crear un nuevo objeto contacto
        Gafas gafa = new Gafas();
        //Asignar el contacto obtenido a la ventana de diálogo
        ventana.setGafa(gafa);
        //Mostar la ventana con los campos de edición activados
        ventana.activarCampos(true);
        ventana.setVisible(true);
        //Liberar la memoria de pantalla ocupada por la ventana de detalle
        //Comprobar si se ha pulsado Aceptar o Cancelar 
        if (ventana.isAceptado()) {
            //Añadir el contacto en la BD
            gestion.insert(gafa);
            //Actualiza los datos en la tabla de la ventana
            CargarDatosJTable();
        }
    }

    void editar() {
        //Obtener número de fila seleccionada en el JTable
        int numFilaSelec = jTable1.getSelectedRow();
        //Comprobar que el usuario ha seleccionado alguna fila
        if (numFilaSelec != -1) {
            Administracion ventana = new Administracion(Frame.getFrames()[0], true);
            //Obtener el contacto correspondiente a la fila seleccionada
            Gafas gafa = gestion.selecGafaById(
                    Integer.valueOf((String) modeloTabla.getValueAt(numFilaSelec, 0)));
            //Asignar el contacto obtenido a la ventana de diálogo
            ventana.setGafa(gafa);
            //Mostar la ventana con los detalles del contacto desactivados
            ventana.activarCampos(false);
            ventana.setVisible(true);
            //Liberar la memoria de pantalla ocupada por la ventana de detalle
            ventana.dispose();
            //Comprobar si se ha pulsado Aceptar o Cancelar 
            if (ventana.isAceptado()) {
                //Guardar el contacto en la BD
                gestion.update(gafa);
                //Actualiza los datos en la tabla de la ventana
                CargarDatosJTable();
            }
        } else {
            //Si no se ha seleccionado un contacto de la lista hay que notificarlo
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un contacto previamente",
                    "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }

    public PanelControl() {
        initComponents();
        if (!java.beans.Beans.isDesignTime()) {
            conexion.conectar("localhost", "root", "");
            this.CargarDatosJTable();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelTipo = new javax.swing.JLabel();
        jLabelForma = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelGenero = new javax.swing.JLabel();
        jLabelMaterial = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelPrecio = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelColor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelModelo = new javax.swing.JLabel();
        jLabelMarca = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Insertar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Suprimir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jButton1)
                .addGap(167, 167, 167)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(94, 94, 94))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabelTipo.setText("          ");

        jLabelForma.setText("          ");

        jLabel8.setText("Tipo:");

        jLabel7.setText("Forma:");

        jLabel6.setText("Material:");

        jLabel5.setText("Genero:");

        jLabel4.setText("Color:");

        jLabelGenero.setText("           ");

        jLabelMaterial.setText("           ");

        jLabel1.setText("Modelo:");

        jLabelPrecio.setText("          ");

        jLabel2.setText("Marca:");

        jLabelColor.setText("          ");

        jLabel3.setText("Precio:");

        jLabelModelo.setText("   ");

        jLabelMarca.setText("          ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelForma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelModelo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelMarca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelColor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelGenero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelMaterial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabelForma))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabelTipo))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabelTipo.getAccessibleContext().setAccessibleName("");
        jLabelForma.getAccessibleContext().setAccessibleName("");
        jLabelGenero.getAccessibleContext().setAccessibleName("");
        jLabelMaterial.getAccessibleContext().setAccessibleName("");
        jLabelPrecio.getAccessibleContext().setAccessibleName("");
        jLabelColor.getAccessibleContext().setAccessibleName("");
        jLabelModelo.getAccessibleContext().setAccessibleName("");
        jLabelModelo.getAccessibleContext().setAccessibleDescription("");
        jLabelMarca.getAccessibleContext().setAccessibleName("");

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        this.mostrarDatosContacto();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        this.mostrarDatosContacto();
    }//GEN-LAST:event_jTable1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Insertar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el articulo seleccionado?", "Atención", JOptionPane.OK_CANCEL_OPTION);
        if (respuesta == JOptionPane.OK_OPTION) {
            int numFilaSelec = jTable1.getSelectedRow();
            Gafas gafaSeleccionada = gestion.selecGafaById(
                    Integer.valueOf((String) modeloTabla.getValueAt(numFilaSelec, 0)));
            gestion.delete(gafaSeleccionada);
        }
        if (respuesta == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
        this.mostrarDatosContacto();
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelColor;
    private javax.swing.JLabel jLabelForma;
    private javax.swing.JLabel jLabelGenero;
    private javax.swing.JLabel jLabelMarca;
    private javax.swing.JLabel jLabelMaterial;
    private javax.swing.JLabel jLabelModelo;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
