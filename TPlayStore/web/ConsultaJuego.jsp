<%--
    Document   : newjsp
    Created on : Sep 11, 2012, 8:56:53 PM
    Author     : tprog129
--%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.Button"%>
<%@page import="Controller.Juego"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="tprog.DataComentario"%>
<%@page import="tprog.DataNodoComentario"%>
<%@page import="java.util.List"%>
<%@page import="tprog.Categoria"%>
<%@page import="tprog.DataInfoJuego"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>TPlay Store</title>
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <script src="js/jquery-ui-1.8.23.custom.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <script type="text/javascript" language="javascript" src="/javaScript/Puntaje.js"></script>
        <script src="js/jquery.treeview.js"  charset="iso-8859-1"></script>
        <link type="text/css" href="js/css/smoothness/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
        <link type="text/css" href="css/jquery.treeview.css" rel="stylesheet" />
        <jsp:include page="header.jsp" />
    </head>

    <body>
    <style type="text/css">
        b.titulo {
            padding-top: 20px;
            padding-right: 20px;
            font-family:Arial;
            font-size:30px;
         }
         
    </style>

    <script>
        $(document).ready(function(){

	$("#browser").treeview({
            animated: "fast",
            collapsed: true,
        });

        });

        var idComment;
        var ajaxCom;
        
      

        function setearIdCom(id){          
           document.getElementById("algo").value= id;

        }

        function responseAjax(){

            if (ajaxNick.readyState == 4){

                if( ajaxNick.status == 200 ){
                    //esta todo bien
                    opener.location.reload()
                } else {
                    //error

                }
            }
        }

	// increase the default animation speed to exaggerate the effect
	$.fx.speeds._default = 1000;
	$(function() {
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

        function hayPuntaje(){
            if (document.getElementById('rate') != null && document.getElementById('rate').value == '') {
                    alert('Debe puntuar el juego');
                    return false;
            }
            else
                return true;
       }
	</script>


        <%! private void listarComentarios(List<DataNodoComentario> comment, StringBuffer salida, boolean ok){

            salida.append("<ul class=\"filetree treeview-famfamfam treeview\">");

            for (int i = 0; i <comment.size(); i++){
                DataComentario dc = (DataComentario) comment.get(i).getContenido();
                salida.append("<li><div style=\"font-size:8px; float:left; color:#808080; padding:15px; padding-top:0px \">" + dc.getId() + "<img src=\"/Imagenes?tipo=usuario&nick="+dc.getNick()+"\" width=\"30\"/></div><div style=\"padding-bottom:15px\"><b>" + dc.getNick()+ "</b> " + dc.getFecha());
                for (int j= 0; j<dc.getPuntaje();j++){
                    salida.append("<img src=\"css/images/sbstar.png\"/>");
                }
                for (int j= 0; j<(5-dc.getPuntaje());j++){
                    salida.append("<img alt=\"e\" src=\"css/images/sgstar.png\"/>");
                }
                salida.append("<br><i>"+dc.getTexto()+"  </i>");
                if (ok){
                    salida.append("<a class=\"opener\" style=\" font-size:11px; color:#808080\" value=\""+dc.getId()+"\" onclick=\"setearIdCom(\'"+dc.getId()+"\')\"><u>Responder</u></a><br><br>");
                }
                salida.append("</div>");
                if(!comment.get(i).getRespuestas().isEmpty()) {
                    listarComentarios(comment.get(i).getRespuestas(),salida, ok);
                }
                salida.append("</li>");
            }
                salida.append("</ul>");
       } %>


        <% DataInfoJuego dij = (DataInfoJuego) request.getAttribute("InfoJuego");%>
        <div class="divConsulta">
            <div style="float: left ; width:250px; padding-top: 10px;" >
                <b class="titulo"><%= dij.getNombre()%></b><br>
                <%= dij.getNickDesarrollador()%><br>
                <br>
                <img width="120" height="120" alt="" src="/Imagenes?tipo=icono&idJ=<%=dij.getId()%>" /><br><br>

                <b>Version</b> <%= dij.getNumUltimaVersion()%><br>
                <b>Precio</b> U$S  <%= dij.getPrecio()%><br>
                <b>Tamaño</b> <%= dij.getTamanio()%> Kb<br><br>

                <%HttpSession Session = request.getSession(true);%>
                <% String tipo = (String) Session.getAttribute("tipoUsuario");
                if(tipo!=null && !tipo.equals("Desarrollador")){
                    if (dij.getListaCompradores().contains(Session.getAttribute("nickLogueado"))){
                        int idJuego = dij.getId();

                %>
                <a href="/Descarga?tipo=Jar&idJ=<%= idJuego%>"> <img src="images/boton_descargar.jpg" width="120"/></a>
                    <%}else{%>
                <%
                 int idJuego = dij.getId();%>

                <div id="dialog" class="mensaje" title="<%=dij.getNombre()%>" align="center" >
                    <table>
                        <tr>
                            <td>
                                <img width="120" height="120" alt=""  src="/Imagenes?tipo=icono&idJ=<%= dij.getId() %>" />
                            </td>
                            <td width="10px"></td>
                            <td>
                                <p style="font-size: small"> ¿Est&aacute; seguro que desea comprar este juego? <br>
                               Desarrollado por <%= dij.getNickDesarrollador() %> <br>
                               Precio U$S <%= dij.getPrecio() %> <br>
                               Tama&ntilde;o <%= dij.getTamanio() %>  Kb<br>
                               Versi&oacute;n <%= dij.getNumUltimaVersion() %></p>
                            </td>
                       </tr>
                    </table>
                    <input type="button" value="Confirmar" onclick="parent.location='CompraExitosa?idJ=<%= dij.getId() %>'" />
                    <input type="button" value="Cancelar" onclick="parent.location='ConsultaJuego?id=<%= dij.getId() %>'">
                    <%
                    StringBuffer strBuffer = request.getRequestURL();
                    String callBackAtHome = strBuffer.substring(0, strBuffer.lastIndexOf("/"));
                    %>
                    
                    <form name="paypal" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
                     <input type="hidden" name="cmd" value="_xclick" />
                     <input type="hidden" name="business" value="TPlayS_1353251750_biz@gmail.com" />
                     <input type="hidden" name="password" value="1353251784" />
                     <input type="hidden" name="custom" value="1123" />
                     <input type="hidden" name="item_name" value="<%=dij.getNombre()%>" />
                     <input type="hidden" name="amount" value=<%=dij.getPrecio()%> />
                     <input type="hidden" name="rm" value="1" />
                     <input type="hidden" name="return" value="<%= callBackAtHome + "/CompraExitosa?idJ=" + dij.getId()%>" />
                     <input type="hidden" name="cancel_return" value="<%= callBackAtHome %>" />
                     <input type="hidden" name="cert_id" value="AsMgE8DshyYmbBSxkvILfA7XDscnAykrrqHqcQf75yCfFBdVw7wX8x-B" />
                     <input type="image" src="images/payBtn.png"/>
                     <!--<input type="submit" src="images/payBtn.png"/> -->
                    </form>
                </div>
                <input id="opener" type="image" src="images/buy.png"/>

                <!--<input type="image" src="images/buy.png" onclick="parent.location='ComprarJuego?idJ=<%=idJuego%>'"/>-->
               <%}}else{
                    if (tipo == null){%>
                    <div id="dialog" class="mensaje" title="Advertencia" align="center" >
                       <p>Para comprar un juego necesitas estar registrado y logueado</p>
                       <a href="/logginerror.jsp" > <b>Iniciar Sesi&oacute;n</b> </a><br>
                       <a href="/altaPerfil.jsp" > <b>Registrar</b> </a>
                </div>
                <input id="opener" type="image" src="images/buy.png"/>
              <%}else{
              if (dij.getNickDesarrollador().equals(Session.getAttribute("nickLogueado"))){

          %>

              <button onclick="parent.location='/altaVersion?id=<%= dij.getId() %>'"> Alta versión </button>
              <%}}



                }%>
            </div>            
                  <div><img alt=""  src="/Imagenes?tipo=juego&idJ=<%=dij.getId()%>" width="705" height="345"/> </div>
            <br><br><br>
            <img alt=""  src="images/entry-top-desc.png"/>
            <div  style="background-image: url('images/entry-content-desc.png'); float: left">
               <b class="titulo" style="padding-top: 30px; padding-left: 30px;">Descripci&oacute;n</b><br>
                <p style="padding-left: 30px; padding-right: 30px; width: 930px;"> <%= dij.getDescripcion()%> </p><br>
                 <b class="titulo" style="padding-top: 30px; padding-left: 30px;">Categor&iacute;as</b><br>
             <div style="float:left">
                 <p style="padding-left: 30px; padding-right: 30px">
                 <% List<String> lstCat = dij.getListaCategorias();
                    int cant = lstCat.size();
                    for (int i = 0; i < cant; i++) {%>
                            <%= lstCat.get(i)+"  "%> <br>
                    <%}%>
                </p>

                <iframe src="https://www.facebook.com/plugins/like.php?href=http://localhost:10026/ConsultaJuego?id=<%=dij.getId()%>"
        scrolling="no" frameborder="0"
        style="border:none; width:380px; height:60px; padding-left: 30px;"></iframe>
        </div>
        <%!
             private int contarPuntajes(List<DataNodoComentario> comment, int est, int res){

                 for (int i = 0; i <comment.size(); i++){

                      if(comment.get(i).getContenido().getPuntaje()==est)
                            res++;
                       if(!comment.get(i).getRespuestas().isEmpty()) {
                            res= contarPuntajes(comment.get(i).getRespuestas(),est, res);
                       }
                 }
                 return res;
           }         
      %>
      <%  
          List<DataNodoComentario> comments = dij.getComentarios();
          int cant5= 0;
          int cant4= 0;
          int cant3= 0;
          int cant2= 0;
          int cant1= 0;
          
          cant5 = contarPuntajes(comments, 5, cant5);
          cant4 = contarPuntajes(comments, 4, cant4);
          cant3 = contarPuntajes(comments, 3, cant3);
          cant2 = contarPuntajes(comments, 2, cant2);
          cant1 = contarPuntajes(comments, 1, cant1);

          
          double max = Math.max(Math.max(Math.max(Math.max(cant1,cant2),cant3),cant4),cant5);
          double suma = cant1+cant2*2+cant3*3+cant4*4+cant5*5;
          double cantidad = cant1+cant2+cant3+cant4+cant5;
          double total=suma/cantidad;
          total = Math.round(total*100.0)/100.0;
          %>
          <table style="float: left; padding-top: 15px; padding-right: 50px; font-family: Arial" >
                <tr style="height: 20px;">
                    <td>5 estrellas</td>
                    <td><div style="width:<%=cant5*(80/max)+1%>px; background-color: #88B131; float: left; margin-right:5px ">&nbsp;</div> <%=cant5%></td>
                    <td rowspan="5" style="padding-left: 30px; text-align: center">Calificaci&oacute;n promedio<br><h1><%=total%></h1> </td>
                </tr>
                <tr style="height: 20px">
                    <td>4 estrellas</td>
                    <td><div style="width:<%=cant4*(80/max)+1%>px; background-color: #A4CC02; float: left; margin-right:5px ">&nbsp;</div> <%=cant4%></td>
                </tr>
                <tr style="height: 20px">
                    <td>3 estrellas</td>
                    <td><div style="width:<%=cant3*(80/max)+1%>px; background-color: #FFCF02; float: left; margin-right:5px ">&nbsp;</div> <%=cant3%></td>
                </tr>
                <tr style="height: 20px">
                    <td>2 estrellas</td>
                    <td><div style="width:<%=cant2*(80/max)+1%>px; background-color: #FF9F02; float: left; margin-right:5px ">&nbsp;</div> <%=cant2%></td>
                </tr>
                <tr style="height: 20px">
                    <td>1 estrella</td>
                    <td><div style="width:<%=cant1*(80/max)+1%>px; background-color: #FF6F31; float: left; margin-right:5px ">&nbsp;</div> <%=cant1%></td>
                </tr>
          
        </table>
        <img alt=""  src="/Imagenes?tipo=QR&nomDes=<%= dij.getNickDesarrollador() %>" width="150" height="150"/>        
            </div>
            <img alt=""  src="images/entry-bottom-desc.png"/>

            <div style="width: 900px; margin-left: 50px">
                <div style=" background-image: url('css/images/comment-top.png') ">
                    <br><br><br><br><b class="titulo" style="padding-top: 30px; padding-left: 170px;">Comentarios</b>
                </div>
                <% 
                    String usuarioid = (String) Session.getAttribute("nickLogueado");
                  if (comments.isEmpty()){
                    out.print("<div style=\"background-image: url(\'css/images/comment-content.png\'); padding-left:170px\"><br>El juego no posee comentarios");
                    if(tipo!=null && !tipo.equals("Desarrollador")&&usuarioid!=null){
                        if (dij.getListaCompradores().contains(Session.getAttribute("nickLogueado"))){
                            out.print("<a class=\"opener\" style=\" font-size:20px; color:#808080; padding-left:170px; text-align=\"center\"\" value=\""+comments.size()+"\" onclick=\"setearIdCom(\'\')\"> <u>Comentar</u> </a><br><br> ");
                  
                        }
                    }
                    out.print("</div>");

                  }
                  else{
                  StringBuffer salida = new StringBuffer("");                  
                  
                  
                  if(tipo!=null && !tipo.equals("Desarrollador")&&usuarioid!=null){
                    if (dij.getListaCompradores().contains(Session.getAttribute("nickLogueado"))){
                        listarComentarios(comments, salida, true);
                        salida.append("<a class=\"opener\" style=\" font-size:20px; color:#808080\"; text-align=\"center\" value=\""+comments.size()+"\" onclick=\"setearIdCom(\'\')\"> <u>Comentar</u> </a><br><br>");
                    }else
                        listarComentarios(comments, salida, false);
                  }else
                        listarComentarios(comments, salida, false);
                  
                  out.print("<div style=\"background-image: url(\'css/images/comment-content.png\'); padding-left:150px\"><ul id=\"browser\">"+salida.toString()+"</ul></div>");
                  }
                 %>
                 <img alt="" src="css/images/comment-bottom.png" />
                 <div id="dialogComment" title="Comentar">
                     <div style="float: left">Calificaci&oacute;n: </div>
                     <div id="rateMe">
                            <a onclick="rateIt(this)" id="_1" title="1" onmouseover="rating(this)" onmouseout="off(this)"></a>
                            <a onclick="rateIt(this)" id="_2" title="2" onmouseover="rating(this)" onmouseout="off(this)"></a>
                            <a onclick="rateIt(this)" id="_3" title="3" onmouseover="rating(this)" onmouseout="off(this)"></a>
                            <a onclick="rateIt(this)" id="_4" title="4" onmouseover="rating(this)" onmouseout="off(this)"></a>
                            <a onclick="rateIt(this)" id="_5" title="5" onmouseover="rating(this)" onmouseout="off(this)"></a>
                        </div>
                     <!--************************************************************************** -->
                      <p>Ingrese Comentario</p>
                      <form id="form-comentar" name="input" method="get" action="/ConsultaJuego" onsubmit="return hayPuntaje();">
                          <input type="hidden" id="algo" name="idC" value=""/>
                          <input type="hidden" id="algo" name="id" value="<%=dij.getId()%>"/>
                          <input type="hidden" id="rate" name="pts" value=""/>
                           <textarea id="texto" name="Comentario" rows="4" cols="48">
                           </textarea>
                           <input type="submit" value="Comentar" /><br>
                     </form>
                     <span  style="visibility: hidden" id="rateStatus" ></span>
                     <span style="visibility: hidden"id="ratingSaved"></span>
                 </div>
            </div>
            <jsp:include page="/footer.html" />
        </div>
    </body>
</html>
