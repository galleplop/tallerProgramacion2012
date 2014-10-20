<%-- 
    Document   : ConfiguracionDeNotificaciones
    Created on : Nov 13, 2012, 9:50:50 AM
    Author     : tprog126
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TPlay Store</title>
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
    <script>
        function setCheckBoxNot(idCheckBox){
            idCheckBox.value = (idCheckBox.value == "ON")? "OFF":"ON";
        }
        function alertar(){
            alert('Se guardaron los cambios con exito');
        }
    </script>
        <jsp:include page="header.jsp" />
    </head>
    <div align="center" class="divConsulta"  style="font-size: 110%">
    <body style="height:600px; font-family: Arial;" >
         
        <div class="ContenedorPrincipal" align="center">

            Seleccione los eventos ante los desea ser notifiado a su dirección de correo electrónico:<br><br>
            <form id="form-Not" name="input" method="get" onsubmit="alertar()">
            <table align="center" width="500" >
            <tbody align="left">
            <td>
                <tr>Al actualizarse la versión de un juego que haya comprado. <input type="checkbox" name= "a" value="ON" id="1" onclick="setCheckBoxNot(1);"/> </tr><br><br>
                <br>
                 <tr>Al publicarse un nuevo comentario de un juego que haya comprado. <input type="checkbox" name= "b" value="ON" id="2" onclick="setCheckBoxNot(2);"/> </tr><br><br>
                <br>
                 <tr>Tras la atención de un reclamo que haya realizado a un Desarrollador. <input type="checkbox" name= "c" value="ON" id="3" onclick="setCheckBoxNot(3);"/></tr><br><br>
                <tr><input id="boton" type="submit" value="Guardar Configuraciones" /> <br></tr>
            </td>
            </tbody>
            </table>
                <input id="algo" name="VanDatos" type="hidden" value=""/>
            </form>

           
        </div>
           
    </body>
     <jsp:include page="/footer.html" />
        </div>
</html>
