<%-- 
    Document   : AltaVersion
    Created on : Sep 23, 2012, 5:17:30 PM
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
        <script src="javaScript/ValidarAltaJuego.js" type="text/javascript" charset="iso-8859-1"></script>
        <script type="text/javascript">
            function valido(){
                if (document.getElementById('versionJuego') != null && (document.getElementById('versionJuego').value == '')) {
                    alert('No ha ingresado versión');
                    return false;
                }else{
                    if (document.getElementById('fileJuego') != null && (document.getElementById('fileJuego').value == '')) {
                        alert('Debe ingresar un archivo');
                        return false;
                    }else{
                        return true;
                    }
                }


            }
        </script>

    </head>
    <body>
        <div style="height: 150px"></div>
        <div align="center">
        <% String nombre = (String) request.getAttribute("nombreVersion");%>
        <form action="finalizarAltaVersion" method="POST"  enctype="multipart/form-data" onsubmit="return valido();">
            <table>
                <tr>
                    <td>
                        Version * <span id="VersionSpan"></span><br>
                        Juego (.jar) * <span id="jarSpan"></span>

                        <input name="id" value="<%=nombre%>" type="hidden">
                    </td>

                    <td>
                        <input type="text" name="versionJuego" id="versionJuego" value="" size="30" /><br>
                        <input type="file" name="fileJuego" id="fileJuego" accept="application/java-archive" value="" size="19" />
                    </td>
                </tr>
            </table>
                    <button type="submit" value="Aceptar" > Aceptar </button>
        </form>
        </div>
                    <div style="height: 150px"></div>
         <div class="divConsulta">
             <jsp:include page="/footer.html" />
         </div>
    </body>
</html>
