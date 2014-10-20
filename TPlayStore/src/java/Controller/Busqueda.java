/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import tprog.DataTreeMap.Lista;
import tprog.ListaDataJuego;
import tprog.DataJuego;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.DataTreeMap;
import tprog.DataTreeMap.Lista.Entry;
import tprog.InterfazWeb;
import tprog.ListaNickDesarrollador;

/**
 *
 * @author tprog129
 */
public class Busqueda extends HttpServlet {
   
    private boolean perteneceLista(List<DataJuego> lista, String nom){
        boolean pertenece = false;
        int i = 0;
        while(!pertenece && i < lista.size()){
            if(lista.get(i).getNombre().equals(nom)){
                pertenece = true;                
            }
            i++;
        }      
        return pertenece;
    }

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
        response.setContentType("text/html;charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try {
            String StringBusqueda = (String)request.getParameter("Buscador");          

            //*********************** WEB SERVICE **************************
            URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
            
           
            
            ListaDataJuego ldj = iweb.listarJuegosAprobadosSistema(); //iCatJuego.listarJuegosAprobadosSistema();

            List<DataJuego> lstGames = ldj.getLista();
            List<DataJuego> resultado = new ArrayList<DataJuego>();
            DataTreeMap dtm = iweb.getListaCategoria();
            Lista lstCat = dtm.getLista();
            List<Entry> l = lstCat.getEntry();

            Iterator it = l.iterator();
            boolean found=false;

            while (it.hasNext()&&!found){
               Entry e = (Entry) it.next();
               String key = e.getKey();
              if (key.equals(StringBusqueda)){
                   found=true;
              }
            }
            /**********************************************************************/
            /************************* Chequeo Categorias *************************/
            /**********************************************************************/

            if (found){
                ListaDataJuego ldaj = iweb.listarJuegosAprobadosPorCat(StringBusqueda);
                List<DataJuego> lj = ldaj.getLista();

                for(int i=0; i<lj.size();i++){
                    resultado.add(lj.get(i));
                }
            }

            /**********************************************************************/
            /********************** Chequeo Desarrolladores ***********************/
            /**********************************************************************/

            String str2 = StringBusqueda.toLowerCase();
            boolean encontre= false;
            int c = 0;

            ListaNickDesarrollador lUser = iweb.listarDesarrolladores();
            List<String> lstUser = lUser.getLista();

            while ((c < lstUser.size())&&(!encontre)){
                String str = lstUser.get(c).toLowerCase();
                if (str.equals(str2))
                    encontre = true;
                c++;
            }
            if (encontre){
                String nomUsuario = lstUser.get(c-1);

                ListaDataJuego lalgoj= iweb.listarJuegosAprobadosSistema();
                List<DataJuego> lj = lalgoj.getLista();

                for(int i = 0; i < lj.size(); i++){
                    int idJuego = lj.get(i).getId();
                    tprog.DataInfoJuego dij = iweb.seleccionarJuegoPorID(idJuego);

                    if (dij.getNickDesarrollador().equals(nomUsuario)){
                        //DataJuego dj = new DataJuego(dij.getNombre(), dij.getDescripcion(),dij.getPrecio(), dij.getImagen(), dij.getIcono());
                        DataJuego dj = new DataJuego();
                        dj.setId(dij.getId());
                        dj.setNombre(dij.getNombre());
                        dj.setDsc(dij.getDescripcion());
                        dj.setImageName(dij.getImageName());
                        dj.setIconName(dij.getIconName());
                        dj.setPrecio(dij.getPrecio());
                        dj.setImagenData(dij.getImagenData());

                        resultado.add(dj);
                    }
                }
            }


            /**********************************************************************/
            /********************** Chequeo Juegos y Descripcion*******************/
            /**********************************************************************/

            if (!encontre){
                for(int i=0; i<lstGames.size();i++){
                    String str1 = lstGames.get(i).getNombre().toLowerCase();
                    if(!perteneceLista(resultado, lstGames.get(i).getNombre())){
                        if (str1.contains(str2))
                            resultado.add(lstGames.get(i));
                        else{
                            String str3 = lstGames.get(i).getDsc().toLowerCase();
                            if (str3.contains(str2))
                                resultado.add(lstGames.get(i));
                        }
                    }
                }
             }

            /**********************************************************************/
            /************************** Ordenado y filtrado ***********************/
            /**********************************************************************/


            String Ord = (String)request.getParameter("ord");

            if(Ord!=null){
               if (Ord.equals("Ascendente"))
                    Ordenar(resultado, "Ascendente");
               else
                    Ordenar(resultado, "Descendente");
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
              while (i<resultado.size()){
                    if ((resultado.get(i).getPrecio()<min)||(resultado.get(i).getPrecio()>max))
                        resultado.remove(i);
                    else
                        i++;                   
              }
            }


            request.setAttribute("nomGame", StringBusqueda);
            request.setAttribute("ResultadoBusqueda", resultado);
            request.getRequestDispatcher("/Busqueda.jsp").forward(request, response);

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
