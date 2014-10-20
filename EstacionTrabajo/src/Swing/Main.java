package Swing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import Endpoint.Publicador;
//import tprog.Publicador;
//import tprog.Publicador;

public class Main {

    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                }catch (Exception e) {
                    System.out.println(e);
                }
                /*WebService*/
                Publicador.getInstance().publicar();

                VentanaPrincipal v = new VentanaPrincipal();
                v.setVisible(true);
                v.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }

        });
    }

}
