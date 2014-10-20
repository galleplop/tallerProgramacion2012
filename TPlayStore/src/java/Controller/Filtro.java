/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import tprog.ControladorWebService;
import tprog.DataRegistro;
import tprog.InterfazWeb;

/**
 *
 * @author tprog129
 */
public class Filtro implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public Filtro() {
    } 
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
	throws IOException, ServletException {

	if (debug) log("Filtro:doFilter()");

	Throwable problem = null;
	try {

         URL url = new URL("http://"+this.getFilterConfig().getServletContext().getInitParameter("servidorCentral")+":"+this.getFilterConfig().getServletContext().getInitParameter("puertoServidor")+"/ws/Service?wsdl");
         QName qName = new QName("http://tprog/","ControladorWebService");
         InterfazWeb iweb = new ControladorWebService(url, qName).getControladorWebPort();

         DataRegistro dr = new DataRegistro();
	 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         Date date = new Date();
         String fecha = dateFormat.format(date);

         dr.setFecha(fecha);

         String userAgent = ((HttpServletRequest) request).getHeader("User-Agent");
         if (userAgent.indexOf("Firefox") > 0)
            dr.setBrowser("Firefox");
         else
             if (userAgent.indexOf("Konqueror") > 0)
                 dr.setBrowser("Konqueror");
             else
                 if (userAgent.indexOf("Opera") > 0)
                    dr.setBrowser("Opera");
                 else
                     if (userAgent.indexOf("MSIE") > 0)
                         dr.setBrowser("Internet Explorer");

         dr.setIP(request.getRemoteAddr());
         dr.setSO(System.getProperty("os.name"));
         String path = ((HttpServletRequest)request).getRequestURL().toString();
         String queryString = ((HttpServletRequest)request).getQueryString();
         if(queryString==null)
            dr.setURL(path);
         else
             dr.setURL(path+"?"+queryString);

         iweb.agregarRegistro(dr);
	 chain.doFilter(request, response);
	}
	catch(Throwable t) {
	    // If an exception is thrown somewhere down the filter chain,
	    // we still want to execute our after processing, and then
	    // rethrow the problem after that.
	    problem = t;
            System.out.println("error filtro " + t);
	}

	// If there was a problem, we want to rethrow it if it is
	// a known type, otherwise log it.
	if (problem != null) {
	    if (problem instanceof ServletException) throw (ServletException)problem;
	    if (problem instanceof IOException) throw (IOException)problem;
	    sendProcessingError(problem, response);
	}
    }
    
    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
	return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
	this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     */
    public void destroy() { 
    }

    /**
     * Init method for this filter 
     */
    public void init(FilterConfig filterConfig) { 
	this.filterConfig = filterConfig;
	if (filterConfig != null) {
	    if (debug) { 
		log("Filtro:Initializing filter");
	    }
	}
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
	if (filterConfig == null) return ("Filtro()");
	StringBuffer sb = new StringBuffer("Filtro(");
	sb.append(filterConfig);
	sb.append(")");
	return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
	String stackTrace = getStackTrace(t); 

	if(stackTrace != null && !stackTrace.equals("")) {
	    try {
		response.setContentType("text/html");
		PrintStream ps = new PrintStream(response.getOutputStream());
		PrintWriter pw = new PrintWriter(ps); 
		pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
		    
		// PENDING! Localize this for next official release
		pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n"); 
		pw.print(stackTrace); 
		pw.print("</pre></body>\n</html>"); //NOI18N
		pw.close();
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
	else {
	    try {
		PrintStream ps = new PrintStream(response.getOutputStream());
		t.printStackTrace(ps);
		ps.close();
		response.getOutputStream().close();
	    }
	    catch(Exception ex) {}
	}
    }

    public static String getStackTrace(Throwable t) {
	String stackTrace = null;
	try {
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    t.printStackTrace(pw);
	    pw.close();
	    sw.close();
	    stackTrace = sw.getBuffer().toString();
	}
	catch(Exception ex) {}
	return stackTrace;
    }

    public void log(String msg) {
	filterConfig.getServletContext().log(msg); 
    }

}
