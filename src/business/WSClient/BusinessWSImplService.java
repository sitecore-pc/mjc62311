
package business.WSClient;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 * Modified 24/09/2015 by patrickc to support multiple instances of businesses
 * 
 */
@WebServiceClient(name = "", targetNamespace = "", wsdlLocation = "")
public class BusinessWSImplService extends Service
{
    /**
     * This is the only constructor supported for the Business implementation. You must specify a stock 
     * symbol when creating your interface object.      
     * @param stockSymbol
     * @throws MalformedURLException 
     */
    public BusinessWSImplService(String stockSymbol, String port) throws MalformedURLException {

    	super(new URL("http://localhost:"+port+"/WS/" + stockSymbol + "?wsdl"),
    			new QName("http://business/", "BusinessWSImplService"));
    }

    /**
     * 
     * @return
     *     returns IBusiness
     */
    @WebEndpoint(name = "BusinessWSImplPort")
    public IBusiness getBusinessWSImplPort() {
        return super.getPort(new QName("http://business/", "BusinessWSImplPort"), IBusiness.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IBusiness
     */
    @WebEndpoint(name = "BusinessWSImplPort")
    public IBusiness getBusinessWSImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://business/", "BusinessWSImplPort"), IBusiness.class, features);
    }
}
