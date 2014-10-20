/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.DataRechazada;
import tprog.InterfazWeb;
import tprog.ListaDataRechazada;
import tprog.ListaNombreJuegoConVersionesAprobadas;

/**
 *
 * @author abuela
 */
public class juegosDesarrollador extends HttpServlet {

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
        try {
            //*********************** WEB SERVICE **************************
            URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

            HttpSession sesion = request.getSession(true);
            String nomDesarrollador = (String) sesion.getAttribute("nickLogueado");
           // iweb.seleccionarDesarrollador(nomDesarrollador);

            ListaNombreJuegoConVersionesAprobadas l = iweb.listarJuegosConVersionesAprobadas(nomDesarrollador);
            List <String> listarAprobados = l.getLista();
            
            ListaDataRechazada lst = iweb.listarVersionRechazadasPorDesarrollador(nomDesarrollador);
            List <DataRechazada> listarRechazados = lst.getLista();
            String nombRechazada;
            
            
            
            int i=0;
            int largo = listarRechazados.size();
            while (i<largo){                
                nombRechazada = listarRechazados.get(i).getNomJuego();
                
                if (!listarAprobados.contains(nombRechazada)){
                    
                    listarAprobados.add(nombRechazada);
                }
                i++;
            }
            String accion = (String) request.getParameter("acc");

            if (accion.equals("av"))
                request.setAttribute("accion", "av");
            else
                request.setAttribute("accion", "cr");

            request.setAttribute("listaJuego", listarAprobados);            
            request.getRequestDispatcher("/listaJuegosDesarrollador.jsp").forward(request, response);
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
