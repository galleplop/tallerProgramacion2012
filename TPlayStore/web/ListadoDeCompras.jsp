<%-- 
    Document   : ListadoDeCompras
    Created on : Sep 18, 2012, 9:00:52 PM
    Author     : tprog126
--%>

<%@page import="tprog.DataJuego"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>TPlay Store</title>
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <script src="js/jquery-ui-1.8.23.custom.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="jPages.js"></script>
        <link rel="stylesheet" href="css/jPages.css">
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
    <body>

       <div class="contenedorPrincipal" align="center">

           <div class="wrapper" style="margin-left: 150px; margin-bottom:30px">
            <div class="holder" align="center"></div>

        <ul id="itemContainer">
            <% List<DataJuego> res= (List<DataJuego>) request.getAttribute("JuegosCliente");

               if (res.size()==0) {%>
                  No has realizado compras..
               <%
               }else{
                int cont = 0;
                while( cont <res.size()){ %>
                 <li style="list-style:none">
                  <% int h = 1;
                   while((h<=3)&&(cont<res.size())){
                        String cl = Integer.toString(h); %>
                        <div class=<%="wp" + cl%>>
                           <% int j=0;
                             while((j<4)&&(cont<res.size())){%>

                             <div class="cell" align="center">
                                <!--***************************************************************************** -->
                                <!--***************************************************************************** -->
                                <%
                                    String n = res.get(cont).getNombre();
                                    
                                %>
                              <!--***************************************************************************** -->
                              <!--***************************************************************************** -->
                                <br>
                                <a href="/ConsultaJuego?id=<%= res.get(cont).getId() %>"><img width="65" src="/Imagenes?tipo=icono&idJ=<%=res.get(cont).getId() %>" /></a><br>

                              <!--***************************************************************************** -->
                              <!--***************************************************************************** -->
                                <%
                                    if (n.length() > 12) {
                                        n = res.get(cont).getNombre().substring(0, 12) + "...";
                                    } else {
                                        n = res.get(cont).getNombre();
                                    }
                                %>
                              <!--***************************************************************************** -->
                              <!--***************************************************************************** -->
                                <a href="/ConsultaJuego?id=<%= res.get(cont).getId() %>"><%= n %> </a><br>
                                <div class="precio">Precio U$S <%= res.get(cont).getPrecio() %></div>
                                <a href="/Reclamos?id=<%= res.get(cont).getId() %>"> Reclamar </a><br>
                                <%cont++;%>
                             </div>
                            <% j++;
                            }%>
                       </div>
                     <% h++;
                     }%>
                    </li>
               <%}
                }%>
         </ul>
       
         </div>
         
         <jsp:include page="/footer.html" />
      </div>
    </body>
</html>
