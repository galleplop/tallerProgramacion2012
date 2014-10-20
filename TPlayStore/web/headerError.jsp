<%--
    Document   : header
    Created on : Sep 4, 2012, 6:49:46 PM
    Author     : tprog129
--%>

<%@page import="javax.xml.namespace.QName"%>
<%@page import="java.net.URL"%>
<%@page import="tprog.ControladorWebService"%>
<%@page import="tprog.InterfazWeb"%>
<%@page import="tprog.ListaNombreCategoria"%>
<%@page import="tprog.ListaNickDesarrollador"%>
<%@page import="tprog.ListaDataJuego"%>
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

                            <form id="form-filtrar" name="input" method="get" action="/Busqueda">
                                <% out.print(sal.toString()); %>
                               <input type="submit" value="Buscar" onclick="submit();"/>
                            </form>
                            <div id="apuestas">                                
                               
                            </div>
                        </td>
                        
                        <td width="100">
                        </td>
                        <td>
                            
                                <a class="main" id="registrarse" href="altaPerfil.jsp" type="text">  Registrarse <br></a>
                            


                                
                                                   
                        </td>  
                        <td width="160">
                        </td>
                    </tr>              
                </table>
            </div>
            <img alt=""  src="images/entry-bottom.png"/>
        </div>
    </body>
</html>
