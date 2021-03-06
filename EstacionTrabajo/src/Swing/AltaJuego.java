/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AltaJuego.java
 *
 * Created on Aug 21, 2012, 9:32:39 PM
 */

package Swing;

import DataBeans.DataTreeMap;
import DataBeans.ListaNickDesarrollador;
import Exceptions.ExcAltaJuego;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import tprog.Categoria;
import tprog.FabricaSistema;
import tprog.InterfazCategoriaJuego;

/**
 *
 * @author tprog129
 */
public class AltaJuego extends javax.swing.JInternalFrame {


    public class MyTableModel extends DefaultTableModel {
           public MyTableModel() {
                super();
            }
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
             }
     }

    private MyTableModel modeloC = new MyTableModel();
    private MyTableModel modeloD = new MyTableModel();
    private MyTableModel datos   = new MyTableModel();
    private boolean seleccionoDes = false;
    private InterfazCategoriaJuego icj = FabricaSistema.getInstance().getInterfazCategoriaJuego();
    int cont = 0;

    /** Creates new form AltaJuego */
    public AltaJuego() {
        initComponents();
        TablaDes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TablaCatSel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TablaCat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Des.setWrapStyleWord(true);
        DataTreeMap d = icj.getListaCategoria();
        TreeMap<String, Categoria> coleccionCat = d.getLista();
        ListaNickDesarrollador l = icj.listarDesarrolladores();
        List<String> coleccionDes = l.getLista();

        modeloC.addColumn("Categorías");
        modeloD.addColumn("Desarrolladores");

        Set<String> listaKey = coleccionCat.keySet();

        Iterator it = listaKey.iterator();
        Iterator itD = coleccionDes.iterator();

        while (it.hasNext()){
            String s = (String) it.next();
            Object [] fila = new Object[1];
            fila[0] = s;

            modeloC.addRow (fila); // Añade una fila al final
        }
        
        while (itD.hasNext()){
            String nick = (String) itD.next();
            Object [] fila1 = new Object[1];
            fila1[0] = nick;
            modeloD.addRow (fila1); // Añade una fila al final
        }

        datos.addColumn("Seleccionados");
        TablaCatSel.setModel(datos);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new panel("/Imagenes/Fondo.jpg");
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        Tamano = new javax.swing.JTextField();
        Precio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Des = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaCat = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaCatSel = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        NombreDes = new javax.swing.JLabel();
        Registrar = new javax.swing.JButton();
        Registrar1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaDes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Agregar Juego");

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 445));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos del juego"));
        jPanel2.setOpaque(false);

        jLabel1.setText("Nombre:");

        jLabel2.setText("Tamaño (KB):");

        jLabel3.setText("Precio (U$S):");

        jLabel4.setText("Descripción:");

        Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreActionPerformed(evt);
            }
        });

        Des.setColumns(20);
        Des.setLineWrap(true);
        Des.setRows(5);
        jScrollPane1.setViewportView(Des);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(Tamano, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tamano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 350, -1));

        TablaCat.setModel(this.modeloC);
        TablaCat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaCatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaCat);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 12, 180, 184));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel5.setText("<html><center>Haga click para<br>Agregar/Quitar categorías</center></html> ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(397, 202, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 10));
        jLabel6.setText("<html><center>Haga click para Agregar <br>Quitar Desarrolladores</center></html> ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 202, -1, -1));

        TablaCatSel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Seleccionadas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TablaCatSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaCatSelMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TablaCatSel);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 240, 180, 115));

        jLabel7.setText("  Desarrollador:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 240, 115, -1));

        NombreDes.setFont(new java.awt.Font("Dialog", 1, 14));
        NombreDes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NombreDes.setText("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nick<br>desarrollador</html>");
        jPanel1.add(NombreDes, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 273, -1, -1));

        Registrar.setText("Aceptar");
        Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        Registrar1.setText("Cancelar");
        Registrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Registrar1ActionPerformed(evt);
            }
        });
        jPanel1.add(Registrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, -1, -1));

        TablaDes.setModel(this.modeloD);
        TablaDes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaDesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaDes);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(583, 12, 178, 184));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreActionPerformed

    private void TablaCatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCatMouseClicked
        int F = TablaCat.rowAtPoint(evt.getPoint());
        String Cat = (String)TablaCat.getValueAt(F, 0);
        Object [] Nuevafila = new Object[1];
        Nuevafila[0] = Cat;
        datos.addRow(Nuevafila);
        modeloC.removeRow(F);
    }//GEN-LAST:event_TablaCatMouseClicked

    private void TablaCatSelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCatSelMouseClicked
        int F = TablaCatSel.rowAtPoint(evt.getPoint());
        String Cat = (String)TablaCatSel.getValueAt(F, 0);
        Object [] Nuevafila = new Object[1];
        Nuevafila[0] = Cat;
        modeloC.addRow(Nuevafila);
        datos.removeRow(F);
    }//GEN-LAST:event_TablaCatSelMouseClicked

    private void RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarActionPerformed
        String nom = Nombre.getText();
        if(nom.equals("")){
            JOptionPane.showMessageDialog(null, "Ingrese un nombre","ERROR",2);
        }
        else{
        try{
            
            double tam =  (double) Double.valueOf(Tamano.getText()).doubleValue();
            double pre =  (double) Double.valueOf(Precio.getText()).doubleValue();
            if((tam<0)||(pre<0)){
                JOptionPane.showMessageDialog(null, "Precio y Tamaño deben ser mayores que cero.","ERROR",2);
                Tamano.setText("");
                Precio.setText("");

            }else{

            String des = Des.getText();
            //icj.altaJuego(nom, tam, pre, des);

            int cant=TablaCatSel.getRowCount();
            int i = 0;
            while (i<cant){
                String catSel = (String) TablaCatSel.getValueAt(i,0);
                icj.seleccionarCategoria(catSel);
                i++;
            }
            if(!this.seleccionoDes){
                JOptionPane.showMessageDialog(null, "No ha seleccionado un Desarrollador","ERROR",2);
            }else{
                icj.seleccionarDesarrollador(NombreDes.getText());
                icj.confirmar();
                JOptionPane.showMessageDialog(null, "    El juego ha sido\ningresado con exito");
                this.dispose();
            }
            }
        }catch (ExcAltaJuego e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",2);
        }catch (ExceptionInInitializerError e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR",2);
            Nombre.setText("");
        }catch (IllegalArgumentException ilegal){
            JOptionPane.showMessageDialog(null, "Precio y Tamaño deben ser valores numéricos.","ERROR",2);
            Tamano.setText("");
            Precio.setText("");

        }
        }
    }//GEN-LAST:event_RegistrarActionPerformed

    private void Registrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Registrar1ActionPerformed
        icj.terminar();
        this.dispose();
    }//GEN-LAST:event_Registrar1ActionPerformed

    private void TablaDesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaDesMouseClicked
        int F = TablaDes.rowAtPoint(evt.getPoint());
        String des = (String)TablaDes.getValueAt(F, 0);
        NombreDes.setText(des);
        this.seleccionoDes = true;
    }//GEN-LAST:event_TablaDesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Des;
    private javax.swing.JTextField Nombre;
    private javax.swing.JLabel NombreDes;
    private javax.swing.JTextField Precio;
    private javax.swing.JButton Registrar;
    private javax.swing.JButton Registrar1;
    private javax.swing.JTable TablaCat;
    private javax.swing.JTable TablaCatSel;
    private javax.swing.JTable TablaDes;
    private javax.swing.JTextField Tamano;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables

}
