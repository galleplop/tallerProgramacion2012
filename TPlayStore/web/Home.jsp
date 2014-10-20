<%--
    Document   : index
    Created on : Sep 4, 2012, 7:01:46 PM
    Author     : tprog129
--%>

<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URI"%>
<%@page import="tprog.DataJuego"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <script src="js/jquery-ui-1.8.23.custom.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <link rel="stylesheet" href="css/jPages.css">
        <script type="text/javascript" src="jPages.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>TPlay Store</title>

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
        <style>
            a.grey:link{
                color: #333333;
            }
        </style>
        <jsp:include page="slider.jsp" /> 
    
      
      <div class="contenedorPrincipal">
          
          
          <div class="izquierda">
              <img alt="" src="css/images/postit-top.png" />
              <div style="background-image: url('css/images/postit-content.png'); width:240px; padding-left: 60px;">
                  <div style="font-family: fantasy; font-size: 18px; color:#333333;"> Categor&iacute;as<br></div><br>
              <%
                        List<String> catList = (List<String>) request.getAttribute("categorias");
                        List<DataJuego> res = (List<DataJuego>) request.getAttribute("juegos");

                        int cant = catList.size();
                        for (int i = 0; i < cant; i++) {
            %>

            <a class="grey" href="/ListadoJuegos?nom=<%= catList.get(i)%>"> <%= catList.get(i)%> <br></a>
            
            <%}%>
            </div>
          <img alt="" src="css/images/postit-bottom.png" />
          </div>

          <div class="wrapper" style="padding-bottom: 50px; ">            

        <ul id="itemContainer">

               <% if (res.size()==0) {%>
                  No hay juegos que coincidan con la búsqueda

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
                                <br>
                                <a href="/ConsultaJuego?id=<%= res.get(cont).getId() %>"><img width="78" src="/Imagenes?tipo=icono&idJ=<%=res.get(cont).getId() %>" /></a><br>

                              <!--***************************************************************************** -->
                              <!--***************************************************************************** -->
                                <%
                                    String n = res.get(cont).getNombre();
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
          <div class="holder" align="center"></div>
         </div>
         <jsp:include page="footer.html" />
      </div>
    </body>
</html>
