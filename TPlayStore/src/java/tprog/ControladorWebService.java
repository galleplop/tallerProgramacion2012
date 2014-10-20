
package tprog;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "ControladorWebService", targetNamespace = "http://tprog/", wsdlLocation = "http://pcunix46:9999/ws/Service?wsdl")
public class ControladorWebService
    extends Service
{

    private final static URL CONTROLADORWEBSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(tprog.ControladorWebService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = tprog.ControladorWebService.class.getResource(".");
            url = new URL(baseUrl, "http://pcunix46:9999/ws/Service?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://pcunix46:9999/ws/Service?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CONTROLADORWEBSERVICE_WSDL_LOCATION = url;
    }

    public ControladorWebService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ControladorWebService() {
        super(CONTROLADORWEBSERVICE_WSDL_LOCATION, new QName("http://tprog/", "ControladorWebService"));
    }

    /**
     * 
     * @return
     *     returns InterfazWeb
     */
    @WebEndpoint(name = "ControladorWebPort")
    public InterfazWeb getControladorWebPort() {
        return super.getPort(new QName("http://tprog/", "ControladorWebPort"), InterfazWeb.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns InterfazWeb
     */
    @WebEndpoint(name = "ControladorWebPort")
    public InterfazWeb getControladorWebPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tprog/", "ControladorWebPort"), InterfazWeb.class, features);
    }

}
