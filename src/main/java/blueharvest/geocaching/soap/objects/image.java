package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class image extends blueharvest.geocaching.concepts.image {

    private final static String url = "https://blueharvestgeo.com/WebServices/ImageService.asmx";

    private String request;
    private String response;

    /**
     * <h3>constructor</h3>
     *
     * @param id      id of this
     * @param uri     where this is located
     * @param caption the caption for this
     * @param file    the file object of this
     * @see java.util.UUID
     * @see java.net.URI
     * @see java.io.File
     * @since 2015-11-08
     */
    public image(java.util.UUID id, java.net.URI uri, String caption, java.io.File file) {
        super(id, uri, caption, file);
        this.request = "";
        this.response = "";
    }

    public void setRequest(String value) {
        request = value;
    }

    public void setResponse(String value) {
        response = value;
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    /**
     * <h3>gets an image</h3>
     *
     * @param id id of the image
     * @return image
     * @see <a href="https://blueharvestgeo.com/WebServices/ImageService.asmx?op=GetImage">GetImage</a>
     * @since 2015-11-09
     */
    public static image get(java.util.UUID id) {
        image i;
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("GetImage");
        // parameters
        request.addProperty("id", id.toString());
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/GetImage", envelope);
            org.ksoap2.serialization.SoapObject response
                    = (org.ksoap2.serialization.SoapObject) envelope.getResponse();
            i = new image(java.util.UUID.fromString(response.getProperty("id").toString()),
                    java.net.URI.create(response.getProperty("uri").toString()),
                    response.getProperty("caption").toString(), null);
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return i;
    }

    /**
     * <h3>inserts an image</h3>
     *
     * @param i (i)mage
     * @return true/false depending on whether the image was inserted
     * @see <a href="https://blueharvestgeo.com/WebServices/ImageService.asmx?op=InsertImage">InsertImage</a>
     * @since 2015-11-09
     */
    public static boolean insert(image i) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("InsertImage");
        final String ns = "http://blueharvestgeo.com/webservices/";
        org.ksoap2.serialization.SoapObject image
                = new org.ksoap2.serialization.SoapObject(ns, "i");
        image.addProperty("uri", i.getUri().toString());
        image.addProperty("caption", i.getCaption());
        request.addSoapObject(image);

        // serializable way
        /* blueharvest.geocaching.soap.objects.image.serialized j
                = new blueharvest.geocaching.soap.objects.image.serialized();
        j.uri = i.getUri();
        j.caption = i.getCaption();
        org.ksoap2.serialization.PropertyInfo pi = new org.ksoap2.serialization.PropertyInfo();
        pi.setName("i");
        pi.setValue(i);
        pi.setType("image");
        request.addProperty(pi);*/

        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false); // prefixing
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url); // ?wsdl
        transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/InsertImage", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            i.setRequest(transport.requestDump); // testing
            i.setResponse(transport.responseDump); // testing
            return Boolean.parseBoolean(response.toString());
        } catch (org.ksoap2.SoapFault ex) {
            throw new RuntimeException(
                    "soap fault:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (org.ksoap2.transport.HttpResponseException ex) {
            throw new RuntimeException(
                    "http response exception:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (java.io.IOException ex) {
            throw new RuntimeException(
                    "io exception:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(
                    "xml pull parser exception:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (java.lang.Exception ex) {
            throw new RuntimeException(
                    "exception:" + ex.getLocalizedMessage() + " ... " + transport.requestDump);
        }
    }

    public static boolean update(image i) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * serialized representation to use in soap
     */
    public static class serialized implements org.ksoap2.serialization.KvmSerializable {

        public java.util.UUID id; // this could be a problem
        public String uri; // public java.net.URI uri;
        public String caption;

        public serialized() {
        }

        @Override
        public Object getProperty(int i) {
            switch (i) {
                case 0:
                    return id;
                case 1:
                    return uri;
                case 2:
                    return caption;
            }
            return null;
        }

        @Override
        public int getPropertyCount() {
            return 3;
        }

        @Override
        public void setProperty(int i, Object o) {
            switch (i) {
                case 0:
                    id = java.util.UUID.fromString(o.toString());
                    break;
                case 1:
                    uri = o.toString(); // java.net.URI.create(o.toString());
                    break;
                case 2:
                    caption = o.toString();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void getPropertyInfo(int i, java.util.Hashtable hashtable,
                                    org.ksoap2.serialization.PropertyInfo propertyInfo) {
            switch (i) {
                case 0:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "id";
                    break;
                case 1:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "uri";
                    break;
                case 2:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "caption";
                    break;
            }
        }

    }

}
