<%-- 
    Document   : invalidar
    Created on : Sep 15, 2012, 12:17:14 PM
    Author     : tprog126
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="refresh" content="0;URL=/Home">
    </head>
    <body>
        <%session.invalidate();%>
    </body>
</html>
