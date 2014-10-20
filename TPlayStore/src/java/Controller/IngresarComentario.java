/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tprog.ControladorWebService;
import tprog.InterfazWeb;

public class IngresarComentario extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
           String comentario = request.getParameter("Comentario");
            //String idComentario = request.getParameter("idCom");

           String id = request.getParameter("idComment");
           
           System.out.println(comentario);
           //System.out.print(",");
           //System.out.println(idComentario);
            
            /*String comentario = request.getParameter("com");
            String idComentario = request.getParameter("idCom");
            String nickUsuario = sesion.estaLogeado(request, response);
            if(sesion.esCliente(request)){
            //*********************** WEB SERVICE **************************
            InterfazWeb iweb = new ControladorWebService().getControladorWebPort();
                //DataInfoJuego iweb = iCatJuego.seleccionarJuego(nomjuego);
                        
            }else{
            
            }*/

        } finally { 
            out.close();
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
