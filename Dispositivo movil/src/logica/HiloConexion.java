/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import Entidades.Comentario;
import Entidades.Puntaje;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceException;

/**
 *
 * @author tprog129
 */
public class HiloConexion extends Thread {
    private JLabel con;
    private JLabel des;
    private JLabel load;
    private Properties p;
    private boolean hayConexion = true;    

    public HiloConexion(JLabel con, JLabel des, JLabel load ) {
        this.con = con;
        this.des = des;
        this.load = load;

        FileInputStream propFile = null;
        try {

            String userHome = System.getProperty("user.home");
            propFile = new FileInputStream(userHome + "/TPlay/config.properties");
            try {
                p = new Properties(System.getProperties());
                p.load(propFile);

            } catch (IOException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
             finally {
                try {
                    propFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException e){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void run(){
      while(true){
            try {
                try {
                    Conexion c = new Conexion();
                    ControladorBase cb = ControladorBase.getInstance();

                    List<Puntaje> l = c.obtenerTopTen(1);
                    if (l != null) {   // ESTA CONECTADO
                        con.setVisible(true);
                        des.setVisible(false);

                        // HCER CONSULTAS NORMALES
                        
                          
                        

                        if (!hayConexion){
                            load.setVisible(true);
                           // MANDO PUNTAJES
                           List<Puntaje> lstPun = cb.MandarPuntajesAServidor();

                           for (Iterator<Puntaje> it = lstPun.iterator(); it.hasNext();) {
                                Puntaje pu = it.next();
                                c.newScoreAtGame(pu.getIdJuego(), pu.getPuntos(), pu.getNick());
                           }

                           // MANDO COMENTARIOS
                           List<Comentario> lstCom = cb.MandarComentariosAServidor();

                           for (Iterator<Comentario> it = lstCom.iterator(); it.hasNext();) {
                                Comentario com = it.next();
                                String t =  com.getIdCom();
                                int i = t.lastIndexOf ('.');
                                String id = null;

                                if (i > 0){
                                    id = com.getIdCom().substring(0,i);
                                }else{
                                    id ="";
                                }

                                c.ingresarComentario("Simón", com.getTexto(), com.getNickCliente(),
                                                     com.getFecha(), com.getCalificacion(), id);
                           }
                        }
                        load.setVisible(false);
                        hayConexion = true;
                        

                        
                    }
                } catch (WebServiceException ex) {
                    des.setVisible(true);
                    con.setVisible(false);
                    hayConexion = false;

                }

                int tiempo = Integer.parseInt(p.getProperty("TiempoDeChequeoConexion"));

                sleep(tiempo);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }

}
