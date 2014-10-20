<%--
    Document   : index
    Created on : Sep 4, 2012, 7:01:46 PM
    Author     : tprog129
--%>

<%@page import="tprog.DataJuego"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script src="js/jquery-1.7.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <script src="js/jquery-ui-1.8.23.custom.min.js" type="text/javascript" charset="iso-8859-1"></script>
        <link rel="stylesheet" href="css/jPages.css">
        <script type="text/javascript" src="jPages.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <title>TPlay Store</title>

        <jsp:include page="header.jsp" />
    </head>
    <body>
        <div class="contenedorPrincipal">
        NICE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
<span id="rateStatus">Seleccione un puntaje...</span>
<span id="ratingSaved">Rating Saved!</span>
<div id="rateMe" title="Seleccione un puntaje...">
    <a onclick="rateIt(this)" id="_1" title="1" onmouseover="rating(this)" onmouseout="off(this)"></a>
    <a onclick="rateIt(this)" id="_2" title="2" onmouseover="rating(this)" onmouseout="off(this)"></a>
    <a onclick="rateIt(this)" id="_3" title="3" onmouseover="rating(this)" onmouseout="off(this)"></a>
    <a onclick="rateIt(this)" id="_4" title="4" onmouseover="rating(this)" onmouseout="off(this)"></a>
    <a onclick="rateIt(this)" id="_5" title="5" onmouseover="rating(this)" onmouseout="off(this)"></a>
    <input type="submit" value="Borrar" autocomplete="off" onclick="borrar();"/>
</div>
<style type="text/css">
    #rateStatus{float:left; clear:both; width:100%; height:20px;}
    #rateMe{float:left; clear:both; width:100%; height:auto; padding:0px; margin:0px;}
    #rateMe li{float:left;list-style:none;}
    #rateMe li a:hover,
    #rateMe .on{background-image:url('images/buy.png');width:50px; height:50px;}
    #rateMe a{float:left;background-image:url('images/logo.png');width:50px; height:50px}
    #ratingSaved{display:none;}
    .saved{color:red; }
</style>

<script type="text/javascript" language="javascript" src="/javaScript/Puntaje.js"></script>

             
        <jsp:include page="footer.html" />
        </div>
    </body>
</html>

