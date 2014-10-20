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
                                <h3 style="color: #8c83ff"> Error ya existe la versión. <br> Por favor, int&eacute;ntelo nuevamente.</h3>
                                
                                
                            </td>
                        </tr>
                       
                    </tbody>
                </table>


            </form>

        </div>
            
        <img alt=""  src="images/entry-bottom.png"/>    
        </div>
        
    </body>
</html>
