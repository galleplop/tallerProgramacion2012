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
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.DataJuego;
import tprog.InterfazWeb;
import tprog.ListaDataJuego;
import tprog.ListaNombreCategoria;

/**
 *
 * @author tprog129
 */
public class Home extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try {
//            ICargarDatos iCDatos = FabricaSistema.getInstance().getICargarDatos();
//            iCDatos.cargarDatos();




            //*********************** WEB SERVICE **************************
            URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
/*
            DataRegistro dr = new DataRegistro();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = new Date();
            String fecha = dateFormat.format(date);

            dr.setFecha(fecha);




            dr.setBrowser(request.getHeader("User-Agent"));
            dr.setIP(request.getRemoteAddr());
            dr.setSO(System.getProperty("os.name"));
            dr.setURL(request.getRequestURI());


            iu.agregarRegistro(dr);*/
            ListaNombreCategoria lst = iweb.listarCategorias();
            List <String> categorias = lst.getLista();

            request.setAttribute("categorias", categorias);

            ListaDataJuego games = iweb.listarJuegosAprobadosSistema();
            List <DataJuego> juegos = games.getLista();

            request.setAttribute("juegos", juegos);

            request.getRequestDispatcher("/Home.jsp").forward(request, response);
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
