<%-- 
    Document   : compra
    Created on : Sep 15, 2012, 12:18:42 PM
    Author     : tprog127
--%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>TPlay Store</title>
        <link  rel="shortcut icon" href="images/otrosjuegos.png">
        <jsp:include page="header.jsp" />
        
    </head>
    <body >
        
        
        <div align="center" class="divConsulta">
            <img alt=""  src="images/entry-top.png"/>
            <!-- margin-left: 290px; -->
            <div style=" background-image: url('images/entry-content.png'); width:996px">
        
        
                <table align="center" width="900" >

                    <tbody align="center" >
                        <tr>
                            <td>

                                <br><br>
                                <div align="left">
                                    <%String n = (String) request.getAttribute("nomJuego");%>
                                    ¿Est&aacute; seguro que desea comprar
                                    <b class="titulo"><%=n%>?</b> <br><br>
                                <%
                                String id = (String) request.getAttribute("idJuego").toString();
                                %>
                                <img width="120" height="120" alt=""  src="/Imagenes?tipo=icono&idJ=<%=id%>" /><br><br>



                                Desarrollado por <%=request.getAttribute("desarrollador")%> <br>
                                Precio U$S <%=request.getAttribute("precio")%> <br>
                                Tama&ntilde;o <%=request.getAttribute("tamano")%>  Kb<br>
                                Versi&oacute;n <%=request.getAttribute("version")%>
                                 </div>
                                <br>

                                <div style="float: left">
                                    <input type="button" value="Confirmar" onclick="parent.location='CompraExitosa?idJ=<%=id%>'" />

                                    <input type="button" value="Cancelar" onclick="parent.location='ConsultaJuego?id=<%=id%>'">
                                </div>

                            </td>
                            
                            <td>
                                <img alt=""  src="/Imagenes?tipo=juego&idJ=<%=id%>" />

                            </td>
                        </tr>
                    </tbody>

                </table>
                       
                        
            </div>
            <img alt=""  src="images/entry-bottom.png"/>
            
            
            <jsp:include page="footer.html" />
        </div> 
                        
    </body>
    
</html>
