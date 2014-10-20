<%-- 
    Document   : ConsultaReclamos
    Created on : Oct 25, 2012, 5:35:12 PM
    Author     : tprog129
--%>

<%@page import="tprog.Reclamo"%>
<%@page import="tprog.Reclamo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>        
        <title>TPlay Store</title>
         <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
         <link type="text/css" href="js/css/smoothness/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
        <link rel="stylesheet" href="css/jPages.css">
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <script type="text/javascript" src="jPages.js"></script>
        <script src="js/jquery-ui-1.8.23.custom.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        

    <style type="text/css">
        b.titulo {
            padding-top: 20px;
            padding-right: 20px;
            font-family:Arial;
            font-size:30px;
         }

    </style>

        <script>

        $.fx.speeds._default = 1000;
	$(function() {

        $("div.holder").jPages({
                 containerID : "itemContainer",
                 perPage : 1,
                 startPage : 1,
                 startRange : 1,
                 midRange : 5,
                 endRange : 1

             });

                $( "#dialog" ).dialog({
                    autoOpen: false,
                    show: "blind",
                    hide: "explode",
                    resizable: false
                });

                $( ".opener" ).click(function() {
                    $( "#dialog" ).dialog( "open" );
                    return false;
                });

                $( "#dialogo" ).dialogo({
                    autoOpen: false,
                    show: "blind",
                    hide: "explode",
                    resizable: false
                });

                $( ".abrir" ).click(function() {
                    $( "#dialogo" ).dialogo( "abrir" );
                    return false;
                });

                
	});

        function borrar(){
            document.getElementById("texto").value= "";
        }

        function setIdReclamo(id){
           document.getElementById("algo").value= id;

        }
        
        </script>
        

        <jsp:include page="header.jsp" />
    </head>

    <style>
            a:hover{
                text-decoration: none;
            }
        </style>
    <body style="font-family: Arial">
      <div class="contenedorPrincipal" align="center">
        <div class="izquierda" style="width:70px; height: 400px"></div>
        <div class="wrapper" style="padding-bottom: 50px; width: 760px; ">
              <ul id="itemContainer" style="width:770px ">
            <% List<Reclamo> res= (List<Reclamo>) request.getAttribute("listaReclamo");
                
               if (res.size()==0) {%>
               <div style="height: 150px"></div>
                  No hay reclamos para este juego.
               <%
               }else{
                int cont = 0;

                while( cont <res.size()){
                  int i = 0; %>
                  
                 <li style="list-style:none">
                     <div id="dialog" title="Atender reclamo" align="center" >
                         <form action="atencionReclamo" method="get">
                             <textarea type="submit" id="texto" cols="48" rows="4" name="comentarioDesarrollador" >hola</textarea>
                             <input type="hidden" id="algo" name="idReclamo" value=""/>
                             <input type="hidden" id="otro" name="nombreJuego" value="<%=res.get(cont).getIdJuego() %>"/>
                             <input type="submit" id="icono" value="Enviar" size="19"/>
                             
                         </form>                        
                    </div>
                     <div id="dialogo" title="Reclamo atendido" align="center" >
                         

                    </div>

                 <% while( i<8 && cont < res.size()){ %>
                 
                 <div style="float: left; height: 120px">

                        <br>
                        
                        <img src="/Imagenes?tipo=usuario&nick=<%=res.get(i).getCliente() %>" width="70"/>
                        
                    </div>
                    <div style="float: left; margin-top:10px; padding-right:10px;  padding-left: 20px; height: 80px; width:250px; text-align: left; border-right:2px solid #cccccc; " >
                        <b style="font-size: 20px"><%=res.get(cont).getCliente()%> </b><br>
                        <a style="font-size:10px">Fecha del reclamo:</a> <tt><%= res.get(cont).getFecha() %></tt><br>
                        <a style="font-size:10px">Version del juego:&nbsp;&nbsp;</a> <tt><%= res.get(cont).getVersion() %></tt><br>
                        <a style="font-size:10px">Reclamo:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;</a><tt><%=res.get(cont).getCategoria().toString()%></tt> <br> <br>
                        
                    </div>                        
                    <div style="float: left; height: 70px; width: 380px; text-align: justify; padding-top:20px; padding-left:30px;">
                        <b>Comentario:</b>
                        <%
                            String Desc = null;
                                Desc =  res.get(cont).getTexto();
                        %>
                        
                        <br><%= Desc %>
                        <%
                        if (res.get(cont).isAtendido()){%>
                        <input class="abrir" style="height: 25px; padding-left: inherit" type="image" src="images/Ok.png"/> <br><br>
                        Respuesta del desarrollador:
                            
                            <%=res.get(cont).getRespuesta() 
                            %>
                        
                        <%
                        }else{

            %>
            
            
                          <br>  <br> <input class="opener" onclick="setIdReclamo('<%=cont%>'); borrar();" style="height: 25px; padding-left: inherit" type="image" src="images/BotonResponder.png"/>
                        <%}%>
                    </div>
                    
                    <div style="float: left"><img alt="" src="images/barra.png" height="100" width="710" /></div>
                    
                   <%cont++;
                     i++;%>
                <%}%>

                </li>
                
              <%}%>
              <%}%>

         </ul>         
         </div>
         <%if (res.size()!=0) {%>
         <div class="holder" align="center"></div><br>
         <%}%>
         
         <jsp:include page="footer.html" />
      </div>
    </body>
</html>