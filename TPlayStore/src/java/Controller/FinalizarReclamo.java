package Controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.EnumCategoriaReclamo;
import tprog.InterfazWeb;

/**
 *
 * @author tprog129
 */
public class FinalizarReclamo extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String nom = (String) request.getParameter("nom");
            String cat = (String) request.getParameter("motivo");
            String cont = (String) request.getParameter("Contenido");


            //*********************** WEB SERVICE **************************
             URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

            HttpSession Session = request.getSession(true);
            String usuarioid = (String) Session.getAttribute("nickLogueado");

            EnumCategoriaReclamo e;
            if (cat.equals("Mal funcionamiento"))
                e = EnumCategoriaReclamo.MAL_FUNCIONAMIENTO;
            else if (cat.equals("Contenido inapropiado"))
                    e = EnumCategoriaReclamo.CONTENIDO_INAPROPIADO;
                 else
                    e = EnumCategoriaReclamo.FACTURACION_ERRONEA;
            
            iweb.agregarReclamo(cont, usuarioid, e, nom);

            request.getRequestDispatcher("/FinalizarReclamo.jsp").forward(request, response);

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
