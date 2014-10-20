/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dispositivomovil;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author guillote
 */
public class MostrarSecuencia extends Thread{
    
    JButton btnRed;
    JButton btnBlue;
    JButton btnGreen;
    JButton btnYellow;
    JLabel block;
    List<Integer> secuencia;

    public MostrarSecuencia(JButton btnRed, JButton btnBlue, JButton btnGreen, JButton btnYellow, JLabel block, List<Integer> secuencia) {
        this.btnRed = btnRed;
        this.btnBlue = btnBlue;
        this.btnGreen = btnGreen;
        this.btnYellow = btnYellow;
        this.block = block;
        this.secuencia = secuencia;
    }
    
//Thread Implementation
    @Override
    public void run() {
        super.run();
        
        jugar.ignorarUsuario = true;
        block.setVisible(true);
        for (Iterator<Integer> it = secuencia.iterator(); it.hasNext();) {
            try {
                Integer integer = it.next();
                switch (integer){
                    case 0:{
                        btnGreen.doClick(300);
                        break;
                    } case 1:{
                        btnRed.doClick(300);
                        break;
                    } case 2:{
                        btnBlue.doClick(300);
                        break;
                    } case 3:{
                        btnYellow.doClick(300);
                        break;
                    }
                }
                
                this.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(MostrarSecuencia.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        jugar.ignorarUsuario = false;
        block.setVisible(false);
    }
    
    
}
