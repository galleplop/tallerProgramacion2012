<%-- 
    Document   : Estadisticas
    Created on : Oct 20, 2012, 8:52:58 PM
    Author     : tprog129
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>TPlay Store</title>
         <% HashMap<String,Double> g = (HashMap<String,Double>)request.getAttribute("ganancias");

        StringBuffer sal = new StringBuffer("");

        sal.append("<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>");
        sal.append("<script type=\"text/javascript\">");
        // Load the Visualization API and the piechart package.
        sal.append("google.load('visualization', '1.0', {'packages':['corechart']});");
        // Set a callback to run when the Google Visualization API is loaded.
        sal.append("google.setOnLoadCallback(drawChart);");
        // Callback that creates and populates a data table,
        // instantiates the pie chart, passes in the data and
        // draws it.
        sal.append("function drawChart() {");
        // Create the data table.
        sal.append("var data = new google.visualization.DataTable();");
        sal.append("data.addColumn('string', 'Topping');");
        sal.append("data.addColumn('number', 'Slices');");
        sal.append("data.addRows([");
         Iterator it = g.entrySet().iterator();
         while (it.hasNext()){
           Map.Entry e = (Map.Entry)it.next();
           Double ganancia = (Double)e.getValue();
           ganancia = Math.round(ganancia*100.0)/100.0;
           String nom = (String)e.getKey();
           sal.append("['"+nom+"',"+ganancia+"]");
           if (it.hasNext())
              sal.append(",");
         }
        sal.append("]);");
        // Set chart options
        sal.append("var options = {'title':'','width':700,'height':400};");
        // Instantiate and draw our chart, passing in some options.
        sal.append("var chart = new google.visualization.PieChart(document.getElementById('chart2_div'));");
        sal.append("chart.draw(data, options); }</script>");

       %>

       <%
       double[] linea = (double[])request.getAttribute("graficaLinea");
       sal.append("<script type=\"text/javascript\"> google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});");
       sal.append("google.setOnLoadCallback(drawChart);");

       sal.append("function drawChart() { var data = google.visualization.arrayToDataTable([['Meses', 'Ganancias'],");

          for (int i = 12; i<=12; i++){
          sal.append("['"+Integer.toString(i)+"',"+linea[i]+"]");
          sal.append(",");
          }
          for (int i = 1; i<=11; i++){
          sal.append("['"+Integer.toString(i)+"',"+linea[i]+"]");
          if (i != 11)
              sal.append(",");
          }
        sal.append("]); var options = {title: 'Ganancias por mes'}; var chart = new google.visualization.LineChart(document.getElementById('char_div'));");
        sal.append("chart.draw(data, options);  }   </script>");


        %>

         <% out.print(sal.toString()); %>
       <link rel="stylesheet" href="css/style.css" type="text/css" />
    </head>
    <body>
       <jsp:include page="header.jsp" />

       <div align="center" class="divConsulta">
           <img alt="" src="images/entry-top.png" />
           <div style=" background-image: url('images/entry-content.png'); padding-left:10px ">
                <div id="chart2_div"></div>
                <div id="char_div" style="width: 700px; height: 400px;"></div>

                <%
                %>
           </div>
           <img alt="" src="images/entry-bottom.png" />            
           <jsp:include page="/footer.html" />
       </div>
    </body>
</html>
