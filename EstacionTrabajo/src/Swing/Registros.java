/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Registros.java
 *
 * Created on Oct 19, 2012, 6:17:16 PM
 */

package Swing;

import DataBeans.ListaDataRegistro;
import Datatypes.DataRegistro;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import tprog.FabricaSistema;
import tprog.InterfazUsuario;
import tprog.RegistrosObserver;

/**
 *
 * @author tprog129
 */
public class Registros extends javax.swing.JInternalFrame {

    public class MyTableModel extends DefaultTableModel {
           public MyTableModel() {
                super();
            }
            public boolean isCellEditable(int row, int col) {
                return false;
             }
     }


    public class tablaObservador implements Observer{

        @Override
        public void update(Observable o, Object arg){
            MyTableModel mod = (MyTableModel) TablaRegistros.getModel();
            for (int i=mod.getRowCount()-1; i>-1;i--){
                    mod.removeRow(i);
                }
            cargarTabla(mod);
        }
    }

    private InterfazUsuario iu = FabricaSistema.getInstance().getInterfazUsuario();
    private MyTableModel modelo = new MyTableModel();

    private void cargarTabla(MyTableModel m){
    ListaDataRegistro ldr= iu.getListaRegistro();
       List<DataRegistro> lista= ldr.getDr();
       if (lista!=null){
           Iterator it = lista.iterator();

             while (it.hasNext()){
                DataRegistro s = (DataRegistro) it.next();
                Object [] fila = new Object[5];
                fila[0] = s.getFecha();
                fila[1] = s.getURL();
                fila[2] = s.getIP();
                fila[3] = s.getSO();
                fila[4] = s.getBrowser();

                m.addRow (fila); // Añade una fila al final
            }
            TablaRegistros.setModel(m);
       }
    }

    /** Creates new form Registros */
    public Registros() {
       initComponents();
       tablaObservador observer = new tablaObservador();

       iu.registarObserverRegistro(observer);
       //RegistrosObserver observable = new RegistrosObserver();

       //observable.addObserver(observer);
     //  observable.notifyObservers();

       TableColumn column = null;
       TablaRegistros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       modelo.addColumn("Fecha");
       column = TablaRegistros.getColumnModel().getColumn(0);
       column.setPreferredWidth(50);

       modelo.addColumn("URL");
       column = TablaRegistros.getColumnModel().getColumn(1);
       column.setPreferredWidth(200);

       modelo.addColumn("IP");
       column = TablaRegistros.getColumnModel().getColumn(2);
       column.setPreferredWidth(50);


       modelo.addColumn("Sistema Operativo");
       column = TablaRegistros.getColumnModel().getColumn(3);
       column.setPreferredWidth(70);

       modelo.addColumn("Navegador");
       column = TablaRegistros.getColumnModel().getColumn(4);
       column.setPreferredWidth(50);
       
       cargarTabla(modelo);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do;.add(d) NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new panel("/Imagenes/Fondo.jpg");
        jScrollPane4 = new javax.swing.JScrollPane();
        TablaRegistros = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registo de acceso al sitio");

        TablaRegistros.setModel(this.modelo);
        TablaRegistros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablaRegistros.setOpaque(false);
        TablaRegistros.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(TablaRegistros);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane4)
                .addGap(34, 34, 34))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaRegistros;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables

}