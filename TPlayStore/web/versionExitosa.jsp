<%-- 
    Document   : VersionExitosa
    Created on : Sep 23, 2012, 5:29:13 PM
    Author     : tprog125
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/jPages.css">
        <jsp:include page="header.jsp" />
    </head>
    <body>
        <div align="center">
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

                                <h1 align="center" style="color: #8c83ff"> ¡Se ha agregado la versión con &eacute;xito!</h1>

                                <img alt=""  src="images/mensajes/exito.jpg" width="500"/> <br>

                                <input align="right" type="image" height="50px" src="images/mensajes/volver.jpeg" onclick="parent.location='/Home'">


                            </td>
                        </tr>
                    </tbody>

                </table>


            </div>

            <img alt=""  src="images/entry-bottom.png"/>
        </div>
        <div class="divConsulta">
             <jsp:include page="/footer.html" />
         </div>
    </body>
</html>
