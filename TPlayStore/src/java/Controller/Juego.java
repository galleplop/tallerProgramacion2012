/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import tprog.DataArchivo;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tprog.ControladorWebService;
import tprog.ExcAltaJuego_Exception;
import tprog.InterfazWeb;
import tprog.ListaNombreCategoria;


/**
 *
 * @author tprog128
 */
public class Juego extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletExce==============='l'l[===============['''''''''''''''''''''''''''''''''''''''''''''''''''''''']]]]]]]]]]]]]]]]]]]]]]]]]]ption if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        String urlStr = request.getRequestURL().toString();
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            if (soloParaDesarrolladores(request, response)){

                //*********************** WEB SERVICE **************************
                URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
                QName qName = new QName("http://tprog/","ControladorWebService");
                InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
                String idSession = request.getSession().getId() + "altaJuego";
                
                System.out.println("ID" + idSession);

                ListaNombreCategoria lstCat = iweb.listarCategorias();
                List <String> categorias = lstCat.getLista();

                if (urlStr.contains("altaJuego")) {

                    List <String> lCat = new ArrayList<String>();
                    String juego = request.getParameter("nombreJuego");
                    String precioStr = request.getParameter("precioJuego");
                    String versionStr = request.getParameter("versionJuego");
                    String desc = request.getParameter("descripcion");
                    List items = upload.parseRequest(request);
                    Iterator iterator = items.iterator();
                    byte[] jarFile = null;
                    String jarExt = null;
                    byte[] iconoFile = null;
                    String iconoExt = null;
                    byte[] imageFile = null;
                    String imageExt = null;
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
                            } else if (item.getFieldName().equals("icono")){
                                String fileNameIcono = item.getName();
                                int index = fileNameIcono.lastIndexOf(".");
                                if (index != -1){

                                    iconoFile = item.get();
                                    iconoExt = fileNameIcono.substring(index);
                                }
                            } else if (item.getFieldName().equals("imagen")){
                                String fileNameImage = item.getName();
                                int index = fileNameImage.lastIndexOf(".");
                                if (index != -1){

                                    imageFile = item.get();
                                    imageExt = fileNameImage.substring(index);
                                }
                            }
                            
                        } else {
                            if (item.getFieldName().equals("nombreJuego")){

                                juego = item.getString();
                            } else if (item.getFieldName().equals("precioJuego")){

                                precioStr = item.getString();
                            } else if (item.getFieldName().equals("versionJuego")){

                                versionStr = item.getString();
                            } else if (item.getFieldName().equals("descripcion")){

                                desc = item.getString();
                            } else if ("ON".equals(item.getString())){
                                
                                lCat.add(categorias.get(Integer.parseInt(item.getFieldName())));
                            }
                        }
                    }
                    Double precio = new Double(precioStr);
                    
                    DataArchivo dArchivo = new DataArchivo();
                    dArchivo.setArchivo(jarFile);
                    dArchivo.setExtencion(jarExt);

                    DataArchivo dArchivoIcono = new DataArchivo();
                    if (iconoFile != null && iconoExt != null){

                        dArchivoIcono.setArchivo(iconoFile);
                        dArchivoIcono.setExtencion(iconoExt);

                    }else{
                        dArchivoIcono.setArchivo(null);
                        dArchivoIcono.setExtencion("");

                    }
                    
                    DataArchivo dArchivoImage = new DataArchivo();
                    if (imageFile != null && imageExt != null){
                        dArchivoImage.setArchivo(imageFile);
                        dArchivoImage.setExtencion(imageExt);
                    }else{
                        dArchivoImage.setArchivo(null);
                        dArchivoImage.setExtencion("");

                    }
                    iweb.altaJuego(juego, versionStr, dArchivo, dArchivoImage, dArchivoIcono, precio.longValue(), desc, idSession);
                    for (Iterator<String> it = lCat.iterator(); it.hasNext();) {
                        String catStr = it.next();
                        //if ("ON".equals(request.getParameter(catStr))){
                            iweb.seleccionarCategoria(catStr, idSession);
                        //}
                    }
                    iweb.seleccionarDesarrollador(sesion.estaLogeado(request, response), idSession);
                    iweb.confirmar(idSession);
                    request.getRequestDispatcher("/altaJuegoCompletado.jsp").forward(request, response);
                    
                } else {
                    request.setAttribute("categorias", categorias);
                    request.getRequestDispatcher("/altaJuego.jsp").forward(request, response);
                }
            }

        } catch (ServletException e) {
            System.out.println(e);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (IOException ex){
            System.out.println(ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (ExcAltaJuego_Exception eJ){
            System.out.println("Error Alta Juego => " + eJ);
            request.getRequestDispatcher("/altaJuego.jsp").forward(request, response);
        }
        finally { 
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
     * Handles the HTTP <code>GET</code> method.
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
        } catch (Exception ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
        } catch (Exception ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
