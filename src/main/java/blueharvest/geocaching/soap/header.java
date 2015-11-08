package blueharvest.geocaching.soap;

/**
 * common credentials for web services to prevent external use
 * @author jmb
 * @since 2015-11-07
 */
public class header { // extends org.kxml2.kdom.Element

    private final static String ns = "http://blueharvestgeo.com/webservices/";

    public static org.kxml2.kdom.Element getElement() {
        org.kxml2.kdom.Element h =
                new org.kxml2.kdom.Element().createElement(ns, "ServiceCredentials");
        org.kxml2.kdom.Element u =
                new org.kxml2.kdom.Element().createElement(ns, "username");
        u.addChild(org.kxml2.kdom.Node.TEXT, "blueharvest-ws"); // todo: config file
        h.addChild(org.kxml2.kdom.Node.ELEMENT, u);
        org.kxml2.kdom.Element p =
                new org.kxml2.kdom.Element().createElement(ns, "password");
        p.addChild(org.kxml2.kdom.Node.TEXT, "tAt00in3!"); // todo: config file
        h.addChild(org.kxml2.kdom.Node.ELEMENT, p);
        return h;
    }

}
