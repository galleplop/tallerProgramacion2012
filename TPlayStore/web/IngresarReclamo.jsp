<%-- 
    Document   : IngresarReclamo
    Created on : Oct 24, 2012, 9:13:14 PM
    Author     : tprog129
--%>

<%@page import="tprog.DataInfoJuego"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="css/style.css">
        <title>TPlay Store</title>
         <jsp:include page="header.jsp" />
    </head>
    <body style="font-family: Arial">
        <div class="contenedorPrincipal" align="center">
          <img alt=""  src="images/entry-top.png"/>
          <div style="background-image: url('images/entry-content.png')">
            <% DataInfoJuego dij = (DataInfoJuego) request.getAttribute("InfoJuego");%>
            <br><b style="font-size: 25px">Ingresa el reclamo para el juego<br></b>
            <p style="font-size: 20px; color: #0088cc;"><u><%=dij.getNombre()%></u></p>
             <br>
             <form id="form-Reclamo" name="input" method="get" action="/FinalizarReclamo">
                 <input type="hidden" name="nom" value="<%=dij.getNombre()%>" /><br>
                 <select name="motivo">
                     <option>Mal funcionamiento</option>
                     <option>Contenido inapropiado</option>
                     <option>facturaci&oacute;n err&oacute;nea</option>
                 </select><br><br>
                 <textarea name="Contenido" rows="10" cols="50">
                 </textarea><br><br>
                 <input type="submit" value="Reclamar" />
            </form>       
           </div>
            <img alt=""  src="images/entry-bottom.png"/>
           <jsp:include page="/footer.html" />
       </div>       
    </body>
</html>
