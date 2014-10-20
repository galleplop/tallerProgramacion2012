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

/**
 *
 * @author tprog129
 */
public class ListadoJuegos extends HttpServlet {

    private void Ordenar(List<DataJuego> lst, String Orden){
            DataJuego temp;

            if (Orden.equals("Ascendente")){
                for (int i = 1; i < lst.size(); i++)
                {

                    for (int x = lst.size() - 1; x >=i ; x--)
                    {
                        if (lst.get(x).getNombre().compareTo(lst.get(x-1).getNombre())<0)
                        {
                            temp = lst.get(x);
                            lst.set(x, lst.get(x-1));
                            lst.set(x-1, temp);
                        }
                    }
                }
           } else
               for (int i = 1; i < lst.size(); i++)
                    {

                        for (int x = lst.size() - 1; x >=i ; x--)
                        {
                            if (lst.get(x).getNombre().compareTo(lst.get(x-1).getNombre())>0)
                            {
                                temp = lst.get(x);
                                lst.set(x, lst.get(x-1));
                                lst.set(x-1, temp);
                            }
                        }
                    }
        }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            //*********************** WEB SERVICE **************************
            URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

            String nom = (String)request.getParameter("nom");

            ListaDataJuego lstG = iweb.listarJuegosAprobadosPorCat(nom);
            List<DataJuego> lstGames = lstG.getLista();

            /**********************************************************************/
            /************************** Ordenado y filtrado ***********************/
            /**********************************************************************/


            String Ord = (String)request.getParameter("ord");

            if(Ord!=null){
               if (Ord.equals("Ascendente"))
                    Ordenar(lstGames, "Ascendente");
               else
                    Ordenar(lstGames, "Descendente");
            }

            String fil = (String)request.getParameter("Precio");

            if(fil!=null){
              double min=0, max=0;

              if (fil.equals("Mas de U$S 3")){
                 char a = fil.charAt(11);
                 min = a - 48;
                 max = Double.MAX_VALUE;
              }
              else{
                 char a = fil.charAt(4);
                 min = a - 48;
                 a = fil.charAt(12);
                 max = a - 48;
             }

              int i = 0;
              while (i<lstGames.size()){
                    if ((lstGames.get(i).getPrecio()<min)||(lstGames.get(i).getPrecio()>max))
                        lstGames.remove(i);
                    else
                        i++;
              }
            }


            request.setAttribute("nomGamel", nom);
            request.setAttribute("JuegosPorCategoria", lstGames);

            request.getRequestDispatcher("/ListadoJuegos.jsp").forward(request, response);
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
