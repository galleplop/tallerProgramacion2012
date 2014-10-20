<%-- 
    Document   : ConsultaPerfil
    Created on : Sep 12, 2012, 10:44:42 AM
    Author     : tprog129
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TPlay Store</title>
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <jsp:include page="header.jsp" />
    </head>
    <body style="height:600px; font-family: Arial;" >
        <div align="center" class="divConsulta"  style="font-size: 110%">
            <img alt="" src="images/entry-top-desc.png" />
            <div style=" background-image: url('images/entry-content-desc.png'); padding-left:50px ">
                <div style="float: left; padding-top: 15px">
                    <img width="200" alt="" src="/Imagenes?tipo=usuario&nick=<%=request.getAttribute("nickUsuario") %>" />
                </div>
                <div>
                    <table align="center" width="500" >

                        <tbody align="left" >
                            <tr>
                                <td>
                                    <b>Nick:</b> <%= request.getAttribute("nickUsuario")%>
                                    <br><br>
                                    <b>Correo electronico:</b> <%= request.getAttribute("mailUsuario")%>
                                    <br><br>
                                    <b>Tipo de perfil:</b> <%= request.getAttribute("tipoUsuario")%>
                                    <br><br>
                                    <b>Nombre:</b> <%= request.getAttribute("nomUsuario")%>
                                    <br><br>
                                    <b>Apellido:</b> <%= request.getAttribute("apellidoUsuario")%>
                                    <br><br>
                                    <b>Fecha nacimiento:</b> <%= request.getAttribute("fNacUsuario")%>
                                    <br><br>
                                    <b>Edad:</b> <%= request.getAttribute("edadUsuario")%>
                                    <br><br>
                                    <% String tipo = (String) request.getAttribute("tipoUsuario");
                                    if(tipo.equals("Desarrollador")){%>
                                    Web: <a href="<%= request.getAttribute("web") %>" target="_blank"><%= request.getAttribute("web")%> </a>
                                        <br><br>
                                        <a href="/ListadoDeVersiones" class="grey">Listado de versiones</a>
                                        <i style="padding-right:15px; padding-left:15px; color: #0088CC">|</i>
                                        <a id="ConsultaReclamo" href="/juegosDesarrollador?acc=cr" > Consulta Reclamos</a>
                                        <i style="padding-right:15px; padding-left:15px; color: #0088CC">|</i>
                                        <a id="Estadisticas" href="/Estadisticas" >  Estadisticas </a>
                                    <%}else{%>
                                        <a href="/ListadoDeCompras" class="grey">Listado de compras </a>
                                        <i style="padding-right:15px; padding-left:15px; color: #0088CC">|</i>
                                        <a href="/ConfiguracionDeNotificaciones" class="grey">Configuración de notificaciones </a>
                                    <%}%>
                                    <br>
                                </td>
                            </tr>
                        </tbody>

                    </table>
                </div>
            </div>
            <img alt="" src="images/entry-bottom-desc.png" />
            
            <jsp:include page="/footer.html" />
        </div>
    </body>
</html>
