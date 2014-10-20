/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tprog.ControladorWebService;
import tprog.DataArchivo;
import tprog.InterfazWeb;

/**
 *
 * @author abuela
 */
public class finalizarAltaVersion extends HttpServlet {

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
            throws ServletException, IOException, FileUploadException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String urlStr = request.getRequestURL().toString();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            if (soloParaDesarrolladores(request, response)){

                if (urlStr.contains("finalizarAltaVersion")) {
                    String idJuegoS = request.getParameter("id");
                    int idJuego = 0;
                    if(idJuegoS != null)
                        idJuego = Integer.parseInt(idJuegoS);
                    String version = request.getParameter("versionJuego");
                    List items = upload.parseRequest(request);
                    Iterator iterator = items.iterator();
                    byte[] jarFile = null;
                    String jarExt = null;
                    while (iterator.hasNext()){
                        FileItem item = (FileItem) iterator.next();

                        if (!item.isFormField()){
                            if (item.getFieldName().equals("fileJuego")){
                                String fileName = item.getName();
                                int index = fileName.lastIndexOf(".");
                                if (index != -1){

                                    jarFile = item.get();
                                    jarExt = fileName.substring(index);
                                }
                            }
                        }else{
                            if (item.getFieldName().equals("id")){

                                idJuegoS = item.getString();
                                idJuego = Integer.parseInt(idJuegoS);
                            } else if (item.getFieldName().equals("versionJuego")){

                                version = item.getString();
                            }
                        }
                    }
                    DataArchivo dArchivo = new DataArchivo();
                    dArchivo.setArchivo(jarFile);
                    dArchivo.setExtencion(jarExt);

                    //*********************** WEB SERVICE **************************
                    URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
                    QName qName = new QName("http://tprog/","ControladorWebService");
                    InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
                    String idSession = request.getSession().getId() + "altaJuego";

                    iweb.seleccionarDesarrollador(sesion.estaLogeado(request, response), idSession);
                    iweb.ingresarNombreJuegoPorID(idJuego, idSession);


                    iweb.altaVersion(version, dArchivo, idSession);

                    //ACÁ TENGO QUE MANDAR LOS MAILS//

                    request.getRequestDispatcher("/versionExitosa.jsp").forward(request, response);
                }
            }
        } finally {            
            out.close();
        }

    }
    public boolean soloParaDesarrolladores(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

        if (sesion.estaLogeado(req, res) != null) {
            if (sesion.esDesarrollador(req)) {
                return true;
            }
            req.getRequestDispatcher("/Home").forward(req, res);
        }
        return false;
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(finalizarAltaVersion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (FileUploadException ex) {
            Logger.getLogger(finalizarAltaVersion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
