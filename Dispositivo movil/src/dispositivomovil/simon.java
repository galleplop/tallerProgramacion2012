/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * simon.java
 *
 * Created on 08-nov-2012, 19:48:13
 */

package dispositivomovil;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import logica.ControladorDispositivo;
import logica.Motor;

/**
 *
 * @author alexander
 */
public class simon extends javax.swing.JInternalFrame {
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private void ocultarBarraTitulo(){
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0,0);
        Barra.setPreferredSize(new Dimension(0,0));
        repaint();
    }
    /** Creates new form simon */
    public simon() {
        initComponents();
        this.ocultarBarraTitulo();
        logoutButton.setVisible(false);
    }

    public simon(boolean estaLog){
        initComponents();
        this.ocultarBarraTitulo();

        loginButton.setVisible(false);
        logoutButton.setVisible(true);      
        
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new panel("/imagenes/Background.jpg");
        jLabel1 = new javax.swing.JLabel();
        JugarButton = new javax.swing.JButton();
        ComentariosButton = new javax.swing.JButton();
        RankingButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();

        setBorder(null);
        setFocusable(false);
        setName("simon"); // NOI18N
        setPreferredSize(new java.awt.Dimension(321, 480));

        jPanel1.setFocusable(false);
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/simon.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        JugarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Jugar.png"))); // NOI18N
        JugarButton.setToolTipText("");
        JugarButton.setBorderPainted(false);
        JugarButton.setContentAreaFilled(false);
        JugarButton.setFocusable(false);
        JugarButton.setName("JugarButton"); // NOI18N
        JugarButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Jugar_pressed.png"))); // NOI18N
        JugarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JugarButtonActionPerformed(evt);
            }
        });
        jPanel1.add(JugarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 254, 160, -1));

        ComentariosButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Comentarios.png"))); // NOI18N
        ComentariosButton.setBorderPainted(false);
        ComentariosButton.setContentAreaFilled(false);
        ComentariosButton.setFocusable(false);
        ComentariosButton.setName("ComentariosButton"); // NOI18N
        ComentariosButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Comentarios_pressed.png"))); // NOI18N
        ComentariosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComentariosButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ComentariosButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 360, 160, -1));

        RankingButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Ranking.png"))); // NOI18N
        RankingButton.setBorderPainted(false);
        RankingButton.setContentAreaFilled(false);
        RankingButton.setFocusable(false);
        RankingButton.setName("RankingButton"); // NOI18N
        RankingButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Ranking_pressed.png"))); // NOI18N
        RankingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RankingButtonActionPerformed(evt);
            }
        });
        jPanel1.add(RankingButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 411, 160, -1));

        loginButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Iniciar.png"))); // NOI18N
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusable(false);
        loginButton.setName("loginButton"); // NOI18N
        loginButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Iniciar_pressed.png"))); // NOI18N
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        jPanel1.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 307, 160, -1));

        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Cerrar.png"))); // NOI18N
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setFocusable(false);
        logoutButton.setName("logoutButton"); // NOI18N
        logoutButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Botones/Cerrar_pressed.png"))); // NOI18N
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        jPanel1.add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 307, 160, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        Login l = new Login();
        JDesktopPane Esc = this.getDesktopPane();
        Esc.add(l);
        l.show();
        l.setLocation(39, 126);
        this.dispose();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
       ControladorDispositivo c = ControladorDispositivo.getInstance();
       c.cerrarSesion();
       simon s = new simon();
       JDesktopPane Esc = this.getDesktopPane();
       Esc.add(s);
       s.show();
       s.setLocation(39, 126);
       JOptionPane.showMessageDialog(this, "Se ha cerrado sesión con éxito");
       this.dispose();
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void ComentariosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComentariosButtonActionPerformed
        
       MostrarComentarios vistaComentarios = new MostrarComentarios();
       JDesktopPane Esc = this.getDesktopPane();
        Esc.add(vistaComentarios);        
        vistaComentarios.ocultarBarraTitulo();
        vistaComentarios.show();
        vistaComentarios.setLocation(39, 126);
        this.dispose();
    }//GEN-LAST:event_ComentariosButtonActionPerformed

    private void JugarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JugarButtonActionPerformed
        jugar j = new jugar();
        JDesktopPane Esc = this.getDesktopPane();
        Esc.add(j);
        //j.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        j.ocultarBarraTitulo();
        j.show();
        j.setLocation(39, 126);
        this.dispose();
}//GEN-LAST:event_JugarButtonActionPerformed

    private void RankingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RankingButtonActionPerformed
        Ranking r = new Ranking();
        JDesktopPane Esc = this.getDesktopPane();
        Esc.add(r);
        r.show();
        this.dispose();
    }//GEN-LAST:event_RankingButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ComentariosButton;
    private javax.swing.JButton JugarButton;
    private javax.swing.JButton RankingButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton logoutButton;
    // End of variables declaration//GEN-END:variables

}
