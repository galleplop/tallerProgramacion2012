/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.DataInfoJuego;
import tprog.InterfazWeb;

/**
 *
 * @author guillote
 */
public class VerificacionAjax extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String urlStr = request.getRequestURL().toString();
        try {
            
            URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
            if (urlStr.contains("checkPerfil")) {

                String validar = request.getParameter("nick");
                if (validar != null) {
                    if (iweb.perteneceUsuario(validar)){
                        out.print("<a style='color: red; font-size: large'> No Valido. </a>");
                    } else {
                        out.print("<a style='color: green; font-size: large'> Valido. </a>");
                    }
                } else {
                    validar = request.getParameter("mail");
                    if (iweb.perteneceUsuarioMail(validar)){
                        out.print("<a style='color: red; font-size: large'> No Valido. </a>");
                    } else {
                        out.print("<a style='color: green; font-size: large'> Valido. </a>");
                    }
                }
            } else { //check Juego

                String juego = request.getParameter("juego");
                if (juego!=null){
                    DataInfoJuego dataJuego = iweb.seleccionarJuego(juego);
                    if (!dataJuego.getNombre().equals("")){
                        out.print("<a style='color: red; font-size:16px;'> No Valido. </a>");
                    } else {
                        out.print("<a style='color: green; font-size:16px;'> Valido. </a>");
                    }
                }
            }
            
        } finally {            
            out.close();
        }
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
