package dispositivomovil;
import Entidades.Test;
import logica.Conexion;
import logica.ControladorDispositivo;

public class Main {

    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
                Movil v = new Movil();
                v.setVisible(true);
               // new Test().test();

            }
        });
    }

}
