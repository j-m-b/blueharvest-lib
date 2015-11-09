package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-07
 */
public class database {

    private final static String url = "https://blueharvestgeo.com/WebServices/DatabaseService.asmx";

    public static boolean delete(String username, String password) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("DeleteAll");
        // parameters
        request.addProperty("username", username);
        request.addProperty("password", password); // wrong auth crashes app
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/DeleteAll", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (java.io.IOException ex) {
            // todo: do something
            return false;
        } catch (org.xmlpull.v1.XmlPullParserException ex) {
            // todo: do something
            return false;
        }
    }

}