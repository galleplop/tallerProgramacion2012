<%--
    Document   : compraExitosa
    Created on : Sep 15, 2012, 1:24:31 PM
    Author     : tprog127
--%>

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

        <div align="center" class="divConsulta">
            <img alt=""  src="images/entry-top.png"/>
            <!-- margin-left: 290px; -->
            <div style=" background-image: url('images/entry-content.png'); width:996px">


                <table align="center" width="700" >

                    <tbody align="center" >
                        <tr>
                            <td>

                                <br><br>




                            </td>

                            <td>

                                <h1 align="center" style="color: #8c83ff"> ¡Su respuesta ha sido ingresada con &eacute;xito!</h1>

                                <img alt=""  src="images/mensajes/ok.png" width="300"/> <br>

                                <input align="right" type="image" height="50px" src="images/mensajes/volver.jpeg" onclick="parent.location='/Home'">

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
