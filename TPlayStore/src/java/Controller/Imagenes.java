/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import tprog.DataInfoJuego;
import tprog.DataPerfil;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import tprog.ControladorWebService;
import tprog.DataDesarrollador;
import tprog.InterfazWeb;

/**
 *
 * @author guillote
 */
public class Imagenes extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //*********************** WEB SERVICE **************************
         URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
         QName qName = new QName("http://tprog/","ControladorWebService");
         InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

        try {
            /* TODO output your page here. You may use following sample code. */
            
            String tipo = request.getParameter("tipo");
            boolean publico = false;
            
            if (tipo.equals("icono")){
                
                publico = true;
                String idJ = request.getParameter("idJ");
                Integer integerId = new Integer(idJ);
                if (integerId != null){
                    DataInfoJuego infoJuego = iweb.seleccionarJuegoPorID(integerId.intValue());
                    this.cargarRespuesta(infoJuego.getIconoData(), infoJuego.getIconName(), response);
                }
            } else if (tipo.equals("juego")){
                
                publico = true;
                String idJ = request.getParameter("idJ");
                Integer integerId = new Integer(idJ);
                if (integerId != null){
                    DataInfoJuego infoJuego = iweb.seleccionarJuegoPorID(integerId.intValue());
                    this.cargarRespuesta(infoJuego.getImagenData(), infoJuego.getImageName(), response);
                }
            } else if (tipo.equals("QR")){
                publico=true;
                String nomDes = request.getParameter("nomDes");
                DataDesarrollador  infoUsuario = (DataDesarrollador) iweb.verPerfil(nomDes);
                String web = infoUsuario.getWeb();

                /*********************************** QR *******************************************/
                 ByteArrayOutputStream out = QRCode.from(web).to(ImageType.PNG).stream();
                 this.cargarRespuesta(out.toByteArray(), nomDes+"_QR.png", response);
                /**********************************************************************************/
            }
            
            if (!publico && sesion.estaLogeado(request, response) != null){
                
                if (tipo.equals("usuario")){
                
                    String nick = request.getParameter("nick");
                    DataPerfil infoUsuario = iweb.verPerfil(nick);
                    byte[] avatarFile = infoUsuario.getImagenData();
                    String filename = infoUsuario.getNick() + infoUsuario.getImageName();
                    this.cargarRespuesta(avatarFile, filename, response);
                } else if (tipo.equals("Jar") && sesion.esCliente(request)){
                    String nomjar = request.getParameter("idJ");
                    int  jar = Integer.parseInt(nomjar);
                    DataInfoJuego dij = iweb.seleccionarJuegoPorID(jar);
                    if (dij.getListaCompradores().contains(sesion.estaLogeado(request, response))){
                          this.cargarRespuesta(dij.getJarData(), dij.getJarName(), response);
                    }else{
                        request.getRequestDispatcher("/403.jsp").forward(request, response);
                    }
                }
                else if (tipo.equals("Jar") && sesion.esDesarrollador(request)){
                    request.getRequestDispatcher("/403.jsp").forward(request, response);
                }
            }
            
        } finally {            
            
        }
    }
    
    private void cargarRespuesta(byte[] data, String nameFile, HttpServletResponse res) throws IOException{
        
        String mimeType = getServletContext().getMimeType(nameFile);
        if (mimeType == null) {
            return;
        }
        // Set content type
        res.setContentType(mimeType);
        res.setHeader("Content-disposition","attachment;filename="+nameFile.replace(" ", "_"));
        // Set content size
        res.setContentLength((int)data.length);
        // Open the file and output streams
        OutputStream theOut = res.getOutputStream(); //new FileOutputStream(webFile);
        // Copy the contents of the file to the output stream
        theOut.write(data);
        theOut.flush();
        theOut.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
