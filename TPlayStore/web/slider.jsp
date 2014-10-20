<%@page import="javax.xml.namespace.QName"%>
<%@page import="java.net.URL"%>
<%@page import="tprog.ControladorWebService"%>
<%@page import="tprog.InterfazWeb"%>
<%@page import="tprog.ListaDataJuego"%>
<%@page import="java.util.Random"%>
<%@page import="tprog.DataJuego"%>
<%@page import="java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <meta http-equiv="Content-type" content="text/html; charset=iso-8859-1" />
    <link rel="stylesheet" href="css/style.css" charset="iso-8859-1" />    
    <script src="js/jquery.featureCarousel.min.js" type="text/javascript" charset="iso-8859-1"></script>
    <script type="text/javascript">
      $(document).ready(function() {
        var carousel = $("#carousel").featureCarousel({           
          // include options like this:
          // (use quotes only for string values, and no trailing comma after last option)
          // option: value,
          // option: value
        });

        $("#but_prev").click(function () {
          carousel.prev();
        });
        $("#but_pause").click(function () {
          carousel.pause();
        });
        $("#but_start").click(function () {
          carousel.start();
        });
        $("#but_next").click(function () {
          carousel.next();
        });
      });
    </script>
  </head>
  <body>
  
    <div class="carousel-container">
    
      <div id="carousel">
        <% 
            URL url = new URL("http://"+request.getSession().getServletContext().getInitParameter("servidorCentral")+":"+request.getSession().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
            QName qName = new QName("http://tprog/","ControladorWebService");
            InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();
           ListaDataJuego l =  iweb.listarJuegosAprobadosSistema();

           List<DataJuego> lstGames = l.getLista();
           if (lstGames.size()>0){
               Random randomGenerator = new Random();
               int i = 0;
               for(int j=0;j<5;j++){
                  String Desc;
                  i = randomGenerator.nextInt(lstGames.size());
                  if (lstGames.get(i).getDsc().length()>200){
                       Desc =  lstGames.get(i).getDsc().substring(0,200)+"...";
                  }
                  else
                       Desc =  lstGames.get(i).getDsc();
                 int id = lstGames.get(i).getId();
        %>

        <div class="carousel-feature">
            <a href="/ConsultaJuego?id=<%=id%>"><img class="carousel-image" alt="Image Caption" src="/Imagenes?tipo=juego&idJ=<%=id%>"></a>
          <div class="carousel-caption">
            <p><%=Desc%></p>
          </div>
        </div>
        <%
           lstGames.remove(i);
        }
       }%>
      </div>          
    </div>
  
  </body>
</html>
