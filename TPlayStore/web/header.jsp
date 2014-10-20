<%--
    Document   : header
    Created on : Sep 4, 2012, 6:49:46 PM
    Author     : tprog129
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.net.URL"%>
<%@page import="javax.xml.namespace.QName"%>
<%@page import="tprog.ControladorWebService"%>
<%@page import="tprog.InterfazWeb"%>
<%@page import="tprog.ListaDataJuego"%>
<%@page import="tprog.ListaNickDesarrollador"%>
<%@page import="tprog.ListaNombreCategoria"%>
<%@page import="tprog.DataJuego"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script type="text/javascript" src="javaScript/validarSesion.js"></script>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
         <script src="bootstrap/js/bootstrap-typeahead.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <link  rel="shortcut icon" href="images/otrosjuegos.png">
    </head>
    <%!
        public class Hilo extends Thread{

            private String urlServlet, port, userName;

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public void setPort(String port) {
                this.port = port;
            }

            public void setUrlServlet(String urlServlet) {
                this.urlServlet = urlServlet;
            }

            @Override
            public void run() {
                super.run();
                URL url;
                try {
                    while (true){
                        url = new URL("http://"+ this.urlServlet +":"+ this.port+"/ws/Service?wsdl");
                        QName qName = new QName("http://tprog/","ControladorWebService");
                        InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
                        int id = iweb.checkNuevaVersion(this.userName);
                        if (id != -1){
                         String nom = iweb.seleccionarJuegoPorID(id).getNombre();
                         JOptionPane.showMessageDialog(null, "hay una nueva versión\ndel juego"+nom);
                        }
                        Hilo.sleep(3000);
                    }

                } catch (InterruptedException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
  %>
    <body>

        <script>
            $('.typeahead').typeahead()
        </script>

        <div align="center">
            <img alt=""  src="images/entry-top.png"/>
            <!-- margin-left: 290px; -->
            <div style=" background-image: url('images/entry-content.png'); width:996px">
                <table width="996" style="margin-left: 10px;">
                    <tr>
                        <td width="150">
                            <a href="index.jsp"><img src="images/logo.png" height="80" alt="logo"/></a>

                        </td>

                        <td align="center">
                            <%
                                String ListaCompleta [] = new String [256];

                                URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
                                QName qName = new QName("http://tprog/","ControladorWebService");
                                InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

                                ListaNombreCategoria l = iweb.listarCategorias();
                                List<String> lstCat= l.getLista();

                                int j = 0;
                                for(int i=0; i<lstCat.size();i++){
                                   ListaCompleta[j]= lstCat.get(i);
                                   j++;
                                }

                                ListaNickDesarrollador li = iweb.listarDesarrolladores();
                                List<String> lstDes =  li.getLista();

                                for(int i=0; i<lstDes.size();i++){
                                   ListaCompleta[j]= lstDes.get(i);
                                   j++;
                                }

                                ListaDataJuego lis =  iweb.listarJuegosAprobadosSistema();
                                List<DataJuego> lstJuegos = lis.getLista();

                                for(int i=0; i<lstJuegos.size();i++){
                                   ListaCompleta[j]= lstJuegos.get(i).getNombre();
                                   j++;
                                }

                                StringBuffer sal = new StringBuffer("");

                                sal.append( "<input class=\"span3\" type=\"text\" name=\"Buscador\" value=\"Buscar Juegos..\" onclick=\"this.value=\'\'\" data-source=\'[");
                                for (int i=0; i<j;i++){
                                    if (ListaCompleta[i].equals("Where's My Water?")){
                                        sal.append("");
                                    }
                                    else{
                                        sal.append("\""+ListaCompleta[i] + "\"");
                                        if (i+1!=j)
                                            sal.append(",");
                                    }
                                }
                                sal.append( "]\' data-items=\"10\" data-provide=\"typeahead\" style=\"margin: 0 auto;\">");


                            %>

                            <form id="form-filtrar" name="input" autocomplete="off" method="get" action="/Busqueda">
                                <% out.print(sal.toString()); %>
                               <input type="submit" value="Buscar" onclick="submit();"/>
                            </form>
                        </td>
                        <td id="iniciarRegistrar" width="180" align="right">
                            <% HttpSession Session = request.getSession(true);%>

                            <form method="get" action="/sesion" onsubmit="return validarIS();">
                                <%

                                    String usuarioid = (String) Session.getAttribute("nickLogueado");

                                   if (usuarioid==null){
                                     usuarioid="";
                                   }
                                %>

                                <% if(usuarioid.equals("")){%>
                                    <input name="nickLog" id="nickMail" type="text" value="nick o correo..." onfocus="value=''" />
                                    <input name="passLog" id="pass" type="text" value="contraseña..." onfocus="cambiarTipo('pass');" />

                                    <input id="boton" type="submit" value="Ingresar"/> <br>
                                <%}else{%>
                                    <p> Bienvenido <a href="/ConsultaPerfil"> <%=usuarioid%> </a></p>
                                <%}%>
                            </form>
                        </td>
                        <td width="10">
                        </td>
                        <td>
                            <% if(usuarioid.equals("")){%>
                                <a class="main" id="registrarse" href="altaPerfil.jsp" type="text"> Registrarse <br></a>
                            <%}else{%>

                                <% String tipo = (String) Session.getAttribute("tipoUsuario");
                                if(tipo.equals("Desarrollador")){%>
                                    <a id="altaJuego" href="/Juego" >  Alta Juego </a> <br>
                                    <a id="altaVersion" href="/juegosDesarrollador?acc=av" > Alta Versi&oacute;n</a><br>

                                <%}%>
                                <a id="cerrar" href="invalidar.jsp" >  Cerrar sesi&oacute;n </a>
                            <%}%>
                        </td>
                    </tr>
                </table>
            </div>
            <img alt=""  src="images/entry-bottom.png"/>
        </div>
    </body>
</html>
