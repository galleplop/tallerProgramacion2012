<%--
    Document   : compraExitosa
    Created on : Sep 15, 2012, 1:24:31 PM
    Author     : tprog127
--%>

<%@page import="java.util.List"%>
<%@page import="tprog.DataInfoJuego"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>TPlay Store</title>
        <jsp:include page="header.jsp" />

    </head>
    <body >
        <script src="javaScript/ValidarAltaJuego.js" type="text/javascript" charset="iso-8859-1"></script>
        <div align="center" class="divConsulta">
            <img alt=""  src="images/entry-top.png"/>
            <!-- margin-left: 290px; -->
            <div style=" background-image: url('images/entry-content.png'); width:996px">


                <table align="center" width="900" >

                    <tbody align="center" >
                        <tr>
                            <td>
                                <br><br>
                            </td>

                            <td>
            <form method="POST"  enctype="multipart/form-data" name="datosJuego" action="altaJuego" onsubmit="return checkSumit();">

            <div align="center" >
                <table border="0" cellspacing="5" style="border-style: solid">
                    <tbody>
                        <tr>
                            <td>Nombre * <span id="JuegoSpan"></span> </td>
                            <td>Version * <span id="VersionSpan"></span></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="nombreJuego" id="nombreJuego" onkeyup="sendAjaxName()" value="" size="30" />
                            </td>
                            <td>
                                <input type="text" name="versionJuego" id="versionJuego" value="" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td>Juego (.jar) * <span id="jarSpan"></span> </td>
                            <td>Precio (U$S) * <span id="precioSpan"></span></td>
                        </tr>
                        <tr>
                            <td>
                                <input type="file" name="fileJuego" id="fileJuego" accept="application/java-archive" value="" size="19" />
                            </td>
                            <td>
                                <input type="text" name="precioJuego" id ="precioJuego" value="" size="30" />
                            </td>
                        </tr>
                        <tr>
                            <td>Icono</td>
                            <td>Imagen</td>
                        </tr>
                        <tr>
                            <td>
                                <input type="file" name="icono" id="icono" accept="image/jpg" value="" size="19" />
                            </td>
                            <td>
                                <input type="file" name="imagen" id="imagen" accept="image/jpg" value="" size="19" />
                            </td>
                        </tr>
                   </tbody>
                </table>
            </div>

            <div align="center" >
                <table border="0" cellspacing="10" style="border-style: solid" >
                    <thead>
                        <span id="catSpan"></span>
                    </thead>
                    <tbody>

                        <%
                            List <String> catList = (List <String>) request.getAttribute("categorias");
                            int cant = catList.size();
                            int iterativerRows = cant/3;

                            for (int i = 0; i<iterativerRows; i++){
                                String id0 = "cat" + Integer.toString(i*3);
                                String id1 = "cat" + Integer.toString(i*3+1);
                                String id2 = "cat" + Integer.toString(i*3+2);
                        %>

                        <tr>
                            <td>
                                <input type="checkbox" name= "<%= i*3 %>" value="OFF" id="<%=id0%>" onclick="setCheckBox(<%= id0%>);"/>
                                <%= catList.get(i*3) %>
                            </td>
                            <td>
                                <input type= "checkbox" name= "<%= i*3+1 %>" value="OFF" id="<%=id1%>" onclick="setCheckBox(<%= id1%>);"/>
                                <%= catList.get(i*3+1) %>
                            </td>
                            <td>
                                <input type="checkbox" name= "<%= i*3+2 %>" value="OFF" id="<%=id2%>" onclick="setCheckBox(<%= id2%>);"/>
                                <%= catList.get(i*3+2) %>
                            </td>
                        </tr>
                        <% } %>
                        <tr> <!-- Si cant%3==0 le agrego una fila al pedo, pero creo que no afecta -->
                        <% for (int i = 0; i<(cant%3); i++){ 
                            String id0 = "cat" + Integer.toString(iterativerRows*3+i);
                        %>

                            <td>
                                <input type="checkbox" name= "<%= iterativerRows*3+i %>" value="OFF" id="<%= id0 %>" onclick="setCheckBox(<%= id0%>);"/>
                                <%= catList.get(iterativerRows*3+i) %>
                            </td>
                        <% } %>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div align="center">
                <table border="0" cellspacing="10" style="border-style: solid ">
                    <tbody>
                        <tr>
                            Descripcion de juego:<br />

                        <textarea name="descripcion"  style="width: 450px; height: 50px;-moz-box-sizing: border-box; text-align: left" >
                            </textarea>

                    <!--  rows="6" cols="80"-->

                        </tr>
                    </tbody>

                </table>
            </div>
            <br>
            <div align="center">
            <input id="boton" type="submit" value="Confirmar Alta"/>
            </div>
        </form>
                                

                            </td>
                        </tr>
                    </tbody>

                </table>


            </div>

            <img alt=""  src="images/entry-bottom.png"/>
            
            <jsp:include page="/footer.html" />
        </div>

    </body>
</html>
