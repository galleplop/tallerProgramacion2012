/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.DataInfoJuego;
import tprog.InterfazWeb;
import tprog.ListaNombreJuegoConVersionesAprobadas;

/**
 *
 * @author tprog129
 */
public class Estadisticas extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            HashMap<String,Double> JuegosComprados = new HashMap<String,Double>();

             //*********************** WEB SERVICE **************************
             URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

            HttpSession sesion = request.getSession(true);
            String nomDesarrollador = (String) sesion.getAttribute("nickLogueado");

            /****************************************** GRAFICA TORTA *********************************************/
            ListaNombreJuegoConVersionesAprobadas lst = iweb.listarJuegosEstadista(nomDesarrollador);
            List<String> listaJuegos = lst.getLista();

            Iterator it = listaJuegos.iterator();

            while (it.hasNext()){
              String n = (String)it.next();
              DataInfoJuego dij = iweb.seleccionarJuego(n);

              if(dij.getNickDesarrollador().equals(nomDesarrollador)){
                  JuegosComprados.put(n,dij.getListaCompradores().size()*dij.getPrecio());
              }
            }

            /****************************************** GRAFICA LINEA *********************************************/
            double[] linea = new double[13];

            for(int i = 1; i<=12;i++){
                Iterator it2 = JuegosComprados.entrySet().iterator();
                Double res = 0.0;
                while (it2.hasNext()){
                  Map.Entry e = (Map.Entry) it2.next();
                  if (i<10){
                    DataInfoJuego dij = iweb.seleccionarJuego((String)e.getKey());
                    int cant = iweb.cantidadComprasJuegoMes((String)e.getKey(),"0"+Integer.toString(i));
                    res = cant*(Double)dij.getPrecio();
                  }
                  else{
                    DataInfoJuego dij = iweb.seleccionarJuego((String)e.getKey());
                    int cant = iweb.cantidadComprasJuegoMes((String)e.getKey(),Integer.toString(i));
                    res = cant*(Double)dij.getPrecio();
                  }
                  linea[i] = linea[i]+res;
                }

            }
            
            request.setAttribute("graficaLinea", linea);
            request.setAttribute("ganancias", JuegosComprados);
            request.getRequestDispatcher("/Estadisticas.jsp").forward(request, response);

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
