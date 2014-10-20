/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.DataCliente;
import tprog.DataDesarrollador;
import tprog.DataFecha;
import tprog.DataPerfil;
import tprog.InterfazWeb;

/**
 *
 * @author tprog129
 */
public class ConsultaPerfil extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        try {
             HttpSession session = request.getSession();

            if (session.getAttribute("nickLogueado")!=null){
                //falta saber si es nick o es mail, y si es mail no se que hacer jiji
                String nickMail = (String) session.getAttribute("nickLogueado");

                //*********************** WEB SERVICE **************************
                 URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
                QName qName = new QName("http://tprog/","ControladorWebService");
                InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

                DataPerfil dp = iweb.verPerfil(nickMail);
                request.setAttribute("nickUsuario", dp.getNick());
                request.setAttribute("mailUsuario", dp.getMail());
                request.setAttribute("nomUsuario", dp.getNombre());
                request.setAttribute("apellidoUsuario", dp.getApellido());
                request.setAttribute("edadUsuario", dp.getEdad());
                DataFecha df = dp.getFechaNacimiento();
                String fnac = df.getDia()+"/"+df.getMes()+"/"+df.getAnio();
                request.setAttribute("fNacUsuario", fnac);
                String tipo = "";
                if(dp instanceof DataCliente){
                    tipo = "Cliente";
                } else {
                    tipo = "Desarrollador";
                    DataDesarrollador dd = (DataDesarrollador) dp;
                    String web = dd.getWeb();
                    request.setAttribute("web", web);
                }
                request.setAttribute("tipoUsuario", tipo);
                
                request.setAttribute("path", getServletConfig().getInitParameter("webPath"));
                
                
//                String filename = avatarFile.getAbsolutePath();
//                int index = filename.lastIndexOf("/");
//                String imageName = filename.substring(index+1);
//                String thePath = getServletConfig().getInitParameter("webPath");
//                File webFile = new File(thePath);
//                webFile.mkdirs();
//                File theFile = new File(webFile, imageName);
//                // Open the file and output streams
//                FileInputStream theIn = new FileInputStream(avatarFile);
//                OutputStream theOut = new FileOutputStream(theFile); //response.getOutputStream();
//                
//                // Copy the contents of the file to the output stream
//                byte[] buf = new byte[1024];
//                int count = 0;
//                while ((count = theIn.read(buf)) >= 0) {
//                    theOut.write(buf, 0, count);
//                }
//                theIn.close();
//                theOut.close();
//                request.setAttribute("avatar", theFile);
            }
            else 
               request.setAttribute("NombreUsuario", "el pibito no esta logueado");

            request.getRequestDispatcher("/ConsultaPerfil.jsp").forward(request, response);
        } catch (Exception e){
            System.out.println("ERRRO ConsultaPerfil\n" + e);
        }
        
        finally { 
            //out.close();
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
