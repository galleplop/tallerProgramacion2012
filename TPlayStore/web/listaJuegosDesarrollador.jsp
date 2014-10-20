<%-- 
    Document   : ListaJuegosDesarrollador
    Created on : Sep 23, 2012, 5:26:37 PM
    Author     : tprog125
--%>

<%@page import="javax.xml.namespace.QName"%>
<%@page import="java.net.URL"%>
<%@page import="tprog.ControladorWebService"%>
<%@page import="tprog.InterfazWeb"%>
<%@page import="tprog.DataInfoJuego"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <script src="js/jquery-ui-1.8.23.custom.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <link rel="stylesheet" href="css/style.css">
        <title>TPlay Store</title>
        <script type="text/javascript" src="jPages.js"></script>
        <link rel="stylesheet" href="css/jPages.css">
        <link type="text/css" href="js/css/smoothness/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
        <script>
            $.fx.speeds._default = 1000;
          $(function(){
          $("div.holder").jPages({
             containerID : "itemContainer",
             perPage : 1,
             startPage : 1,
             startRange : 1,
             midRange : 5,
             endRange : 1

         });

         $( "#dialog, #dialogComment" ).dialog({
			autoOpen: false,
			show: "blind",
			hide: "explode",
                        resizable: false
		});

		$( "#opener").click(function() {
			$( "#dialog" ).dialog( "open" );
			return false;
		});

                $( ".opener").click(function() {
			$( "#dialogComment" ).dialog( "open" );
			return false;
		});

        });
        </script>
        <jsp:include page="header.jsp" />
    </head>
    <body>
        <div class="contenedorPrincipal" align="center">

            <div style="padding-left: 120px" class="wrapper">
            <b align="center" style="font-size: 25px">Selecciona el juego </b>            
            <div class="holder" align="center"></div>

        <ul id="itemContainer">
            <% List<String> res= (List<String>) request.getAttribute("listaJuego");
               String accion = (String) request.getAttribute("accion");
               if (accion.equals("av"))
                   accion="/altaVersion";
               else
                   accion="/ConsultaReclamo";

               if (res.size()==0) {%>
                  No posee Juegos.
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
                                    String n = res.get(cont);
                                    
                                    URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
                                    QName qName = new QName("http://tprog/","ControladorWebService");
                                    InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

                                    int id = iweb.seleccionarJuego(res.get(cont)).getId();

                                %>
                              <!--***************************************************************************** -->
                              <!--***************************************************************************** -->
                                <br>
                                <a href="<%= accion %>?id=<%= id %>"><img width="78" src="/Imagenes?tipo=icono&idJ=<%=id %>" /></a><br>

                              <!--***************************************************************************** -->
                              <!--***************************************************************************** -->
                                <%
                                    if (n.length() > 12) {
                                        n = res.get(cont).substring(0, 12) + "...";
                                    } else {
                                        n = res.get(cont);
                                    }
                                %>
                              <!--***************************************************************************** -->
                              <!--***************************************************************************** -->
                                <a  href="<%= accion %>?id=<%= id %>"><%= n %> </a><br>

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
        </div>

         <div class="divConsulta">
             <jsp:include page="/footer.html" />
         </div>
    </body>
</html>
