<%-- 
    Document   : ListadoJuegos
    Created on : Sep 12, 2012, 5:45:54 PM
    Author     : tprog129
--%>

<%@page import="tprog.DataJuego"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <script src="js/jquery-ui-1.8.23.custom.min.js" type="text/javascript" charset="iso-8859-1"></script> 
        <title>TPlay Store</title>
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
    <body style="font-family: Arial">
       <% List<DataJuego> res= (List<DataJuego>) request.getAttribute("JuegosPorCategoria"); %>

       <div class="contenedorPrincipal" align="center">
          <% if (res.size()!=0) {%>
          <div class="izquierda" style="font-family: Arial;">
            <img alt="" src="css/images/postit-top.png" />
            <div style="background-image: url('css/images/postit-content.png'); width:240px; padding-left: 60px;">
                Ordenar alfabéticamente<br><br>

                <form id="form-ordenar" name="input" method="get" action="/ListadoJuegos">
                    <input type="hidden" name="nom" value="<%=(String) request.getAttribute("nomGamel")%>" />
                    <select name="ord">
                        <option> Ascendente</option>
                        <option> Descendente</option>
                    </select><br>
                    <input type="submit" value="ordenar" onclick="submit();"/>
                </form>
                <br><br>Filtrar por precio<br><br>
                <form id="form-filtrado" name="input" method="get" action="/ListadoJuegos">
                    <input type="hidden" name="nom" value="<%=(String) request.getAttribute("nomGamel")%>" />
                    <select name="Precio" title="Precio" >
                        <option>U$S 0 - U$S 1</option>
                        <option>U$S 1 - U$S 3</option>
                        <option>Mas de U$S 3</option>
                    </select><br>
                    <input type="submit" value="Filtrar" onclick="submit();"/>
                </form>
            </div>
            <img alt="" src="css/images/postit-bottom.png" />
        </div>
        <%} else{%>
            <div class="izquierda" style="width:50px;"></div>
         <%}%>
        <div class="wrapper">

        <ul id="itemContainer">
            <%if (res.size()==0) {%>
                  No hay juegos para la categoría seleccionada
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
                                <a href="/ConsultaJuego?id=<%= res.get(cont).getId() %>"><img width="78" src="/Imagenes?tipo=icono&idJ=<%=res.get(cont).getId() %>" /></a><br>

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
                                Precio: U$S <%= res.get(cont).getPrecio() %>
                                <%cont++;%>
                             </div>
                            <% j++;
                            }%>
                       </div>
                     <% h++;
                     }%>
                     <img alt="" src="images/empty.png" height="20"/>
                    </li>
               <%}
                }%>
         </ul>         
         </div>
         <% if (res.size()!=0) {%>
         <div class="holder" style="padding-left:170px"></div>
         <%}%>
      </div>
         
         <div class="divConsulta">
             <jsp:include page="/footer.html" />
         </div>
    </body>
</html>
