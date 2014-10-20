package Controller;

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
import tprog.DataCliente;
import tprog.DataPerfil;
import tprog.InterfazWeb;


//@WebServiceClient //(name = "PublicadorService", /*targetNamespace = "http://publicar.complejoservidor/",*/ wsdlLocation = "http://localhost:8080/publicador?wsdl")
public class sesion extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        HttpSession Session = request.getSession(true);
        PrintWriter out = response.getWriter();
        
        try {
            String nickMail = request.getParameter("nickLog");//nickMail
            String password = request.getParameter("passLog");//password

            //*********************** WEB SERVICE **************************
            URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

            DataPerfil user;
            String nickLogueado = iweb.iniciarSesion(nickMail, password);
            if (!nickLogueado.equals("")){
                Session.setAttribute("nickLogueado", nickLogueado);
                if (iweb.perteneceUsuario(nickMail)||(iweb.perteneceUsuarioMail(nickMail))){
                    user = iweb.verPerfil(nickLogueado);
                    String tipo = "";
                    if(user instanceof DataCliente){
                        tipo = "Cliente";
//                        Hilo runLoop = new Hilo();
//                        runLoop.setUserName(nickLogueado);
//                        runLoop.setPort(request.getSession().getServletContext().getInitParameter("puertoServidor"));
//                        runLoop.setUrlServlet(request.getSession().getServletContext().getInitParameter("servidorCentral"));
//                        runLoop.start();
                    }else{
                        tipo = "Desarrollador";
                    }
                    Session.setAttribute("tipoUsuario", tipo);
                    request.setAttribute("nickLogueado", nickLogueado);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                   
                } else{
                    Session.setAttribute("nickLogueado", "");
                    request.setAttribute("nickLogueado", "");
                    request.getRequestDispatcher("/logginerror.jsp").forward(request, response);
                }
            }else{
                Session.setAttribute("nickLogueado", "");
                request.setAttribute("nickLogueado", "");
                
                request.getRequestDispatcher("/logginerror.jsp").forward(request, response);
                

                //request.getRequestDispatcher("/header.jsp").forward(request, response);
            }

        } finally { 
            out.close();
        }
    }
    
    static public String estaLogeado (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        HttpSession session = request.getSession();
        String nick =  (String) session.getAttribute("nickLogueado");
        if ((nick != null) && (!nick.equals(""))){
            return nick;
        } else {
            request.getRequestDispatcher("/logginerror.jsp").forward(request, response);
        }
        return null;
    }
    
    static public boolean esDesarrollador (HttpServletRequest request){
        
        String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario");
        return (tipoUsuario != null) && "Desarrollador".equals(tipoUsuario);
    }
    
    static public boolean esCliente (HttpServletRequest request){
        
        String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario");
        return (tipoUsuario != null) && "Cliente".equals(tipoUsuario);
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
