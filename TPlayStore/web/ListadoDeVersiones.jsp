<%-- 
    Document   : ListadoDeVersiones
    Created on : Sep 20, 2012, 5:56:02 PM
    Author     : tprog126
--%>

<%@page import="java.io.File"%>
<%@page import="tprog.DataRechazada"%>
<%@page import="tprog.DataPendiente"%>
<%@page import="tprog.DataJuego"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href="css/jPages.css">
        <title>TPlay Store</title>
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <script type="text/javascript" src="jPages.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" />


        <script>
          $(function(){
          $("div.holder").jPages({
             containerID : "itemContainer",
             perPage : 1,
             startPage : 1,
             startRange : 1,
             midRange : 5,
             endRange : 1

         });

        });
        </script>

        <jsp:include page="header.jsp" />
    </head>
    <body style="font-family: Arial">

      <div style=" margin: 0 auto; width: 400px" align="center">

      <% List<DataPendiente> vp= (List<DataPendiente>) request.getAttribute("versionesPendientes");
      int cont;
      if((vp != null) && (!vp.isEmpty())){
      %>
        <b style="font-size: 25px">Versiones Pendientes </b><br><br>

            <%cont = 0;
            while( cont < vp.size()){
             DataPendiente dp = vp.get(cont);
             String n = dp.getNomJuego();
            %>
             <div style=" float: left; height: 80px">
                <img width="80" height="80" alt="" src="/Imagenes?tipo=icono&idJ=<%=dp.getIdJuego() %>" /><br><br>
             </div>

             <div style="width:200px; text-align: left; height: 80px" >
               <br>
             <b><%=dp.getNomJuego()%></b>
             <br>
             Version: <%=dp.getNumVersion()%>
             <br>
             </div>

             <br>
            <%cont++;
            }
        }%>
        <br><br>
        
        <% List<DataRechazada> vr= (List<DataRechazada>) request.getAttribute("versionesRechazadas");
        if((vr != null) && (!vr.isEmpty())){
        %>
            <b style="font-size: 25px">Versiones Rechazadas </b><br><br>
            <%cont = 0;
            while( cont < vr.size()){
             DataRechazada dr = vr.get(cont);
            %>
             <div style="float: left; height: 80px">
             <img width="80" height="80" alt=""  src="/Imagenes?tipo=icono&idJ=<%=dr.getIdJuego() %>" />
             </div>
             <div style="height: 80px; width:200px; text-align: left" >
             <b><%=dr.getNomJuego()%></b>
             <br>
             Version: <%=dr.getNumVersion()%>
             <br>

             Motivo: <%=dr.getMotivo()%>
             </div>
             <br>
            <%cont++;
            }
        }%>
        <%if((vp != null) && (vr != null) && vp.isEmpty() && vr.isEmpty()){
        %> No hay versiones Pendientes ni Rechazadas
        <%
        }%>

      </div>
      
      <div class="divConsulta">
          <jsp:include page="/footer.html" />
      </div>
    </body>
    
</html>
