/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import tprog.DataInfoJuego;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.DataJuego;
import tprog.InterfazWeb;
import tprog.ListaDataJuego;

/**
 *
 * @author tprog129
 */
public class ConsultaJuego extends HttpServlet {
   
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try {
            //*********************** WEB SERVICE **************************
             URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
            String idSession = request.getSession().getId() + "comentando";
            
            String idC = (String)request.getParameter("idC");
            String com = (String)request.getParameter("Comentario");
            String ide = (String)request.getParameter("id");
            String pts = (String)request.getParameter("pts");
            
            if (idC!=null){
               DataInfoJuego j = iweb.seleccionarJuegoPorID(Integer.parseInt(ide));
               iweb.setNombreJuego(j.getNombre(), idSession);
               DateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
               Date date = new Date();
               String fecha = dateFormat.format(date);

               HttpSession Session = request.getSession(true);
               String usuarioid = (String) Session.getAttribute("nickLogueado");

               int puntos = Integer.parseInt(pts);
               iweb.ingresarDatos(com,fecha, usuarioid,puntos, idSession);

               iweb.agregarComentario(idC, idSession);

               //ACÁ TENGO QUE MANDAR LOS MAILS//
            }
            
            int id = Integer.parseInt(ide);

            DataInfoJuego dij = iweb.seleccionarJuegoPorID(id);
            request.setAttribute("InfoJuego", dij);
            ListaDataJuego lista = iweb.listarJuegos();
            boolean encontre = false;
            int i = 0;
            List<DataJuego> lj = lista.getLista();

            while ((i<lj.size())&&(!encontre)){
                if(lj.get(i).getNombre().equals(dij.getNombre())){
                     encontre=true;
                }
                i++;
            }
            if (encontre)
                request.getRequestDispatcher("/ConsultaJuego.jsp").forward(request, response);
            else
                request.getRequestDispatcher("/404.jsp").forward(request, response);

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
