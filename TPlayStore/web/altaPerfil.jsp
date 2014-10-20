<%-- 
    Document   : altaPerfil
    Created on : Sep 5, 2012, 6:19:35 PM
    Author     : tprog127
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>TPlay Store</title>
        <link  rel="shortcut icon" href="images/otrosjuegos.png">
        <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.23.custom.min.js"></script>
        <link type="text/css" href="js/css/smoothness/jquery-ui-1.8.23.custom.css" rel="stylesheet" />
        <jsp:include page="header.jsp" />
    </head>
    <body>
        <script type = "text/javascript" src="javaScript/ValidarAltaPerfil.js"></script>

        
        
        <div align="center" class="divConsulta">
            <img alt=""  src="images/entry-top.png"/>
            <form method="POST" enctype="multipart/form-data" action="altaPerfil"  onsubmit="return validar();" style=" background-image: url('images/entry-content.png'); width:996px">

                <div align="center">

                    <input type="radio" name="group" value="Desarrollador" checked onclick="habilitarWeb('fila1');"/> Desarrollador  <input type="radio" name="group" value="Cliente" onclick="desHabilitarWeb('fila1');"/> Cliente <br>
                </div>
                <table border="0" cellspacing="10" style="border-style: solid">
                    <tbody>
                        <tr>
                            <td>* Nick</td>
                            <td><input id="nic" type="text" name="nick" maxlength="35" value="" size="40" onkeyup= "sendAjaxNick()"/> 
                                <div style="float: right; width:150px;">
                                    <span id="checkName"></span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>* Mail</td>
                            <td><input id="mail" type="text" name="mail" maxlength="35" value="" size="40" onkeydown="sendAjaxMail()"/>
                                <div style="float: right; width:150px;">
                                    <span id="checkMail"></span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>* Contraseña</td>
                            <td><input id="contra" type="password" name="contrasenia" maxlength="35" value="" size="40" /></td>
                        </tr>
                        <tr>
                            <td>* Confirmar contraseña</td>
                            <td><input id="verifContra" type="password" name="verficarContrasenia" maxlength="35" value="" size="40" /></td>
                        </tr>
                        <tr>
                            <td>* Nombre</td>
                            <td><input id="nombre" type="text" name="nombre" maxlength="35" value="" size="40" /></td>
                        </tr>
                        <tr>
                            <td>* Apellido</td>
                            <td><input id="apellido" type="text" name="apellido" maxlength="35" value="" size="40" /></td>
                        </tr>
                        <tr>
                            <td>* Fecha de nacimiento</td>
                            <td><input id="datepicker" name="fNac" type="text"/></td>
                        </tr>
                        <tr>
                            <td>Imagen</td>
                            <td><input id="imagen" type="file" accept="image/*" name="imagen" maxlength="35" value="" size="29" /></td>
                        </tr>
                        <tr>

                            <td><label id="labelWeb"  >Web</label></td>
                            <td><input id="fila1" type="text" name="web" maxlength="35" value="" size="40" /></td>

                        </tr>
                     
                    </tbody>
                </table>
                <input type="submit" value="Registrar">
            </form>

            <img alt=""  src="images/entry-bottom.png"/>
            <jsp:include page="footer.html" />
        </div>
    </body>
</html>
