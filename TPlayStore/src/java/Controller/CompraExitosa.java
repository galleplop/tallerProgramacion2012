/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import tprog.DataCompra;
import tprog.DataInfoJuego;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.ExcAccesoUsuario_Exception;
import tprog.InterfazWeb;

/**
 *
 * @author abuela
 */
public class CompraExitosa extends HttpServlet {

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
        response.setContentType("text/html;charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try {
            
            //*********************** WEB SERVICE **************************
            URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
            String idSession = request.getSession().getId() + "CompraExitosa";

            String idJ = (String)request.getParameter("idJ");
            int id = Integer.parseInt(idJ);
            DataInfoJuego dij = iweb.seleccionarJuegoPorID(id);
            request.setAttribute("InfoJuego", dij);
            
            iweb.ingresarNombreJuegoPorID(id, idSession);
            
            //DATOS DEL COMPRADOR
            
            HttpSession sesion = request.getSession(true);
            String nick = (String) sesion.getAttribute("nickLogueado");
            
            
            DateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
            Date date = new Date();
            String fecha = dateFormat.format(date);
            System.out.println(date);
            
            try {
                //DATOS DE LA COMPRA
                DataCompra dc = iweb.ingresarDatosCliente(nick, fecha, idSession);
                iweb.registrarCompra(idSession);
                iweb.terminar(idSession);
                request.getRequestDispatcher("/compraExitosa.jsp").forward(request, response);
            } catch (ExcAccesoUsuario_Exception ex) {
                System.out.println("hay un error en compra" + ex);
                iweb.terminar(idSession);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
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
