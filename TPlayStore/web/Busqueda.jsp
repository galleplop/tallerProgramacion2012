<%-- 
    Document   : Busqueda
    Created on : Sep 12, 2012, 11:25:49 AM
    Author     : tprog129
--%>

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

      <div class="contenedorPrincipal" align="center">

          <% List<DataJuego> res= (List<DataJuego>) request.getAttribute("ResultadoBusqueda");

            if (!res.isEmpty()){ %>

          <div class="izquierda" style="font-family: Arial;">
            <img alt="" src="css/images/postit-top.png" />
            <div style="background-image: url('css/images/postit-content.png'); width:240px; padding-left: 60px;">
                Ordenar alfabéticamente<br><br>

                <form id="form-ordenar" name="input" method="get" action="/Busqueda">
                    <input type="hidden" name="Buscador" value="<%=(String) request.getAttribute("nomGame")%>" />
                    <select name="ord">
                        <option> Ascendente</option>
                        <option> Descendente</option>
                    </select><br>
                    <input type="submit" value="ordenar" onclick="submit();"/>
                </form>
                <br><br>Filtrar por precio<br><br>
                <form id="form-filtrado" name="input" method="get" action="/Busqueda">
                    <input type="hidden" name="Buscador" value="<%=(String) request.getAttribute("nomGame")%>" />
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
            <div class="izquierda" style="width:10px;"></div>
         <%}%>
          <div class="wrapper" style="padding-bottom: 50px; width: 760px; ">
              <ul id="itemContainer" style="width:770px ">
            <% 
               if (res.size()==0) {%>
                  No hay juegos que coincidan con la búsqueda

               <%
               }else{
                int cont = 0;
                while( cont <res.size()){ 
                  int i = 0; %>
                 <li style="list-style:none">
                 <% while( i<8 && cont < res.size()){ %>
                 <div style="float: left; height: 120px">

                        <br>
                        <a href="/ConsultaJuego?id=<%= res.get(cont).getId() %>"><img width="78" src="/Imagenes?tipo=icono&idJ=<%=res.get(cont).getId()%>" /></a>
                    </div>
                    <div style="float: left; padding: 20px; height: 80px; width:100px; text-align: left" >
                        <br><b><%= res.get(cont).getNombre() %></b><br>
                        U$S <%= res.get(cont).getPrecio() %>
                    </div>
                    <div style="float: left; height: 120px; width: 480px; text-align: justify; padding-right: 10px " >
                        <%
                            String Desc = null;
                             if (res.get(cont).getDsc().length()>200){
                                  Desc =  res.get(cont).getDsc().substring(0,200)+"...";
                             }
                             else
                                Desc =  res.get(cont).getDsc();

                        %>
                        <br><%= Desc %>                        
                    </div>
                    <div style="float: left"><img alt="" src="images/barra.png" height="100" width="710" /></div>
                   <%cont++;
                     i++;%>
                <%}%>
                </li>
              <%}%>
              <%}%>  
         </ul>
         <%if (res.size()!=0) {%>
         <div class="holder" align="center"></div>
         <%}%>
         </div>
         <jsp:include page="footer.html" />
      </div>      
    </body>            
</html>
