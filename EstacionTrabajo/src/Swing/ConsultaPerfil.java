/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConsultaPerfil.java
 *
 * Created on 17-ago-2012, 18:48:29
 */

package Swing;

import DataBeans.ListaDataUsuario;
import Datatypes.DataDesarrollador;
import Datatypes.DataFecha;
import Datatypes.DataPerfil;
import Datatypes.DataUsuario;
import java.awt.Image;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import tprog.FabricaSistema;
import tprog.InterfazUsuario;

/**
 *
 * @author alexander
 */
public class ConsultaPerfil extends javax.swing.JInternalFrame {

      private String img;

      public class MyTableModel extends DefaultTableModel {
           public MyTableModel() {
                super();
            }
            public boolean isCellEditable(int row, int col) {
                return false;
             }  
     }


    private ListaDataUsuario coleccionUsuarios;
    private MyTableModel modelo = new MyTableModel();
    private InterfazUsuario iu = FabricaSistema.getInstance().getInterfazUsuario();

    /** Creates new form ConsultaPerfil */
    public ConsultaPerfil() {
        initComponents();        
        coleccionUsuarios  = iu.consultarPerfil();
        TablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TablaDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelo.addColumn("Nick");
        modelo.addColumn("Email");
        List<DataUsuario> ldu = coleccionUsuarios.getLista();
        Iterator it = ldu.iterator();

        while (it.hasNext()){
            DataUsuario du = (DataUsuario) it.next();
            String Nick = du.getNick();
            String Mail = du.getMail();

            Object [] fila = new Object[2];
            fila[0] = Nick;
            fila[1] = Mail;
            modelo.addRow ( fila ); // Añade una fila al final
        }

        TablaDatos.setValueAt("", 6, 0);
        TablaDatos.setValueAt("", 6, 1);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new panel("/Imagenes/Fondo.jpg");
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaUsuarios = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        TablaDatos = new javax.swing.JTable();
        LabelImagen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consultar perfil");

        TablaUsuarios.setModel(this.modelo);
        TablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaUsuarios);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), null), "Información de usuario"));
        jPanel1.setOpaque(false);

        TablaDatos.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));
        TablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"     Nick:", null},
                {"     Correo Electrónico:", null},
                {"     Nombre:", null},
                {"     Apellido:", null},
                {"     Edad:", null},
                {"     Fecha de Nacimiento:", null},
                {"     Web:", null}
            },
            new String [] {
                "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaDatos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TablaDatos.setFocusable(false);
        TablaDatos.setRequestFocusEnabled(false);
        TablaDatos.setRowHeight(35);
        TablaDatos.setRowSelectionAllowed(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TablaDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TablaDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 795, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(92, 92, 92)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 519, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(jScrollPane1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1)
                            .addGap(6, 6, 6)))
                    .addContainerGap(22, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaUsuariosMouseClicked
        int F = TablaUsuarios.rowAtPoint(evt.getPoint());
        int C = TablaUsuarios.columnAtPoint(evt.getPoint());

        if (C == 1)
            C--;

        String Nick = (String)TablaUsuarios.getValueAt(F, C);

        DataPerfil dp = iu.verPerfil(Nick);
       TablaDatos.setValueAt(dp.getNick(), 0, 1);
       TablaDatos.setValueAt(dp.getMail(), 1, 1);
       TablaDatos.setValueAt(dp.getNombre(), 2, 1);
       TablaDatos.setValueAt(dp.getApellido(), 3, 1);
       String e = Integer.toString(dp.getEdad());
       TablaDatos.setValueAt(e, 4, 1);
       DataFecha df = dp.getFecha();
       String fn = Integer.toString(df.getDia())+"/"+Integer.toString(df.getMes())+"/"+Integer.toString(df.getAnio());
       TablaDatos.setValueAt(fn, 5, 1);

       ImageIcon imageIcon = new ImageIcon(dp.getImagenData());
       int i = imageIcon.getIconHeight();
       int j = imageIcon.getIconWidth();
       LabelImagen.setHorizontalAlignment(SwingConstants.CENTER);
       if (i>j){
            ImageIcon nuevoIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(-1, LabelImagen.getHeight(), Image.SCALE_AREA_AVERAGING));            
            LabelImagen.setIcon(nuevoIcon);
        }
        else {
            ImageIcon nuevoIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(LabelImagen.getWidth()*2/3, -1, Image.SCALE_AREA_AVERAGING));
            LabelImagen.setIcon(nuevoIcon);
        }

        
        if(dp instanceof DataDesarrollador){
            DataDesarrollador dd = (DataDesarrollador) dp;
            TablaDatos.setValueAt("     Web:", 6, 0);
            TablaDatos.setValueAt(dd.getWeb(), 6, 1);
        }
        else{
             TablaDatos.setValueAt("", 6, 0);
             TablaDatos.setValueAt("", 6, 1);
        }
       
    }//GEN-LAST:event_TablaUsuariosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelImagen;
    private javax.swing.JTable TablaDatos;
    private javax.swing.JTable TablaUsuarios;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
