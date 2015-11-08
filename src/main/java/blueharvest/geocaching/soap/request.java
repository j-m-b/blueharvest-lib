package blueharvest.geocaching.soap;

/**
 * @author jmb
 * @since 2015-11-07
 */
public class request extends org.ksoap2.serialization.SoapObject {

    public request(String op) {
        super("http://blueharvestgeo.com/webservices/", op);
    }

}
