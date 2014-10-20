<%-- 
    Document   : logginerror
    Created on : Sep 10, 2012, 7:26:45 PM
    Author     : tprog127
--%>

<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>TPlay Store</title>
        <link  rel="shortcut icon" href="images/otrosjuegos.png">        
        <jsp:include page="headerError.jsp" />
        <script type="text/javascript" >
        


        </script>
    </head>

    <body>
        <link  rel="shortcut icon" href="images/otrosjuegos.png">

        
        <br><br>

        <div align="center">


            <!-- margin-left: 290px; -->
            <img alt=""  src="images/entry-top.png" width="996px" />
            <div style=" background-image: url('images/entry-content.png'); width:996px;">
            <form method="get" action="/sesion" onsubmit="return validarIS(); " >
                
                <table >
                    <tbody>
                        <tr>
                            <td>
                                <img src="images/mensajes/alto.jpg">
                            </td>
                            
                            <td>
                                <h3 style="color: #8c83ff"> ¿Nos conocemos? <br> Por favor autentifíquese.</h3>
                                
                                <input name="nickLog" id="nickMail2" type="text" value="nick o mail..." onfocus="value=''" /><br>
                                <input name="passLog" id="pass2" type="text" value="contraseña..." onfocus="cambiarTipo('pass2');" /><br>
                                <input type="submit" value="Ingresar" onclick="submit();"/> <br><br>
                            </td>
                        </tr>
                       
                    </tbody>
                </table>


            </form>

        </div>
            
        <img alt=""  src="images/entry-bottom.png"/>    
        </div>
        <div class="divConsulta">
             <jsp:include page="/footer.html" />
         </div>
    </body>
</html>
