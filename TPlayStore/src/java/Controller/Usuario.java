package Controller;



import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import tprog.ControladorWebService;
import tprog.DataArchivo;
import tprog.DataFecha;
import tprog.InterfazWeb;


public class Usuario extends HttpServlet{


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, FileUploadException, Exception {
        response.setContentType("text/html;charset=iso-8859-1");
        HttpSession Session = request.getSession(true);
        //PrintWriter out = response.getWriter();
        System.out.println(request);
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            
            List items = upload.parseRequest(request);
            Iterator iterator = items.iterator();
            String nick = "";
            String mail = null;
            String pass = null;
            String nombre = null;
            String apellido = null;
            String fechaStr = "";
            //String encryptedPassword = null;
            boolean esDesarrollador = false;
            String webStr = null;
            byte[] imageFile = null;
            String imageExt = null;
            while (iterator.hasNext()){
                FileItem item = (FileItem) iterator.next();
                
                if (!item.isFormField()){
                    String fileName = item.getName();
                    int index = fileName.lastIndexOf(".");
                    if (index != -1){
                        
                        imageFile = item.get();
                        imageExt = fileName.substring(index);
                    }
                } else {
                    if (item.getFieldName().equals("nick")){
                        
                        nick = item.getString();
                    } else if (item.getFieldName().equals("mail")){
                        
                        mail = item.getString();
                    } else if (item.getFieldName().equals("contrasenia")){
                        
                        pass = item.getString();
//                        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
//                        encryptedPassword = passwordEncryptor.encryptPassword(pass);
                    } else if (item.getFieldName().equals("nombre")){
                        
                        nombre = item.getString();
                    } else if (item.getFieldName().equals("apellido")){
                        
                        apellido = item.getString();
                    } else if (item.getFieldName().equals("fNac")){
                        
                        fechaStr = item.getString();
                    } else if (item.getFieldName().equals("group")){
                        
                        esDesarrollador = item.getString().equals("Desarrollador");
                    } else if (item.getFieldName().equals("web")){
                        
                        webStr = item.getString();
                    }
                }
            }
            
            
            String [] parser = fechaStr.split("/");
            Integer dia  = new Integer(parser[0]);
            Integer mes  = new Integer(parser[1]);
            Integer anio = new Integer(parser[2]);
            DataFecha fecha = new DataFecha();
            fecha.setDia(dia);
            fecha.setMes(mes);
            fecha.setAnio(anio);

            Date fechaActual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String hoy = formato.format(fechaActual);
            String[] dat2 = hoy.split("/");
            int anos1 = Integer.parseInt(dat2[2]) - anio;
            int mes1 = Integer.parseInt(dat2[1]) - mes;
            if (mes1 < 0) {
                anos1 = anos1 - 1;
            } else if (mes == 0) {
                int dia1 = Integer.parseInt(dat2[0]) - dia;
                if (dia1 > 0) {
                    anos1 = anos1 - 1;
                }
            }
            int edad = anos1;
            DataArchivo archivo =new DataArchivo();
            if (imageFile != null && imageExt != null){
                archivo.setArchivo(imageFile);
                archivo.setExtencion(imageExt);
            }else{
                archivo.setExtencion("");
                archivo.setArchivo(null);
            }
            
             URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
            String idSession = request.getSession().getId() + "altaPerfil";
            
            boolean ok = iweb.ingresarPerfil(nick, mail, idSession);

            if(ok){
                if(esDesarrollador){
                    iweb.altaDesarrollador(nombre, apellido, fecha, edad, archivo, webStr, pass, idSession);
                    Session.setAttribute("tipoUsuario", "Desarrollador");
                }else{
                    iweb.altaCliente(nombre, apellido, fecha, edad, archivo, pass, idSession);
                    Session.setAttribute("tipoUsuario", "Cliente" );
                }
                Session.setAttribute("nickLogueado", nick);
                request.setAttribute("nickLogueado", nick);
                request.getRequestDispatcher("/Home").forward(request, response);
            }else{
                request.setAttribute("existe","Existe");
                request.getRequestDispatcher("/altaPerfil.jsp").forward(request, response);
            }

        } catch (Exception ex) {
            System.out.println("WTF\n" + ex);
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        //    out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
            
        } catch (FileUploadException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        try {
            processRequest(req, resp);
            
        } catch (FileUploadException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
