package dispositivomovil;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import logica.ControladorDispositivo;

public class Reloj extends Thread{
    JLabel lblHora;
    JLabel lblNick;
    public Reloj(JLabel lblHora, JLabel nick ) {
        this.lblHora=lblHora;
        this.lblNick=nick;
    }
    public void run(){
      while(true){
            try {
                Date fecha=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("hh:mm a");
                lblHora.setText(""+sdf.format(fecha));
                ControladorDispositivo c = ControladorDispositivo.getInstance();
                if (c.obtenerNickLogueado()==null){
                    lblNick.setText("");
                }else
                    lblNick.setText(c.obtenerNickLogueado());

                sleep(1000);
            } catch (Exception ex) {
                System.out.print(ex.toString());
            }
        }
    }
}