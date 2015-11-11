package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class location extends blueharvest.geocaching.concepts.location {

    private final static String url = "https://blueharvestgeo.com/WebServices/LocationService.asmx";

    /**
     * <h3>location</h3>
     * represents the latitude and longitude coordinates on globe
     *
     * @param id        id
     * @param name      name
     * @param latitude  latitude coordinate
     * @param longitude longitude coordinate
     * @param altitude  altitude
     * @param address   address
     * @see blueharvest.geocaching.concepts.location.coordinate
     * @since 2015-11
     */
    public location(java.util.UUID id,
                    @SuppressWarnings("null") /*@com.sun.istack.internal.Nullable*/ String name,
                    double latitude, double longitude, int altitude,
                    @SuppressWarnings("null") /*@com.sun.istack.internal.Nullable*/
                            blueharvest.geocaching.concepts.address address) {
        super(id, name, latitude, longitude, altitude, address);
    }

    /**
     * <h3>gets a location</h3>
     *
     * @param id id
     * @return location
     * @see <a href="https://blueharvestgeo.com/WebServices/LocationService.asmx?op=GetLocation">
     * GetLocation</a>
     * @since 2015-11-09
     */
    public static location get(java.util.UUID id) {
        location l;
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("GetLocation");
        // parameters
        request.addProperty("id", id.toString());
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/GetLocation", envelope);
            org.ksoap2.serialization.SoapObject response
                    = (org.ksoap2.serialization.SoapObject) envelope.getResponse();
            l = new location(java.util.UUID.fromString(response.getProperty("id").toString()), null,
                    Double.parseDouble(response.getProperty("latitude").toString()),
                    Double.parseDouble(response.getProperty("longitude").toString()),
                    Integer.parseInt(response.getProperty("altitude").toString()), null);
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return l;
    }

    /**
     * <h3>gets a location</h3>
     * todo: address
     *
     * @param latitude  in decimal degrees
     * @param longitude in decimal degrees
     * @return location
     * @see <a href="https://blueharvestgeo.com/WebServices/LocationService.asmx?op=GetLocationByCoordinates">
     * GetLocationByCoordinates</a>
     * @since 2015-11-09
     */
    public static location get(double latitude, double longitude) {
        location l;
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("GetLocationByCoordinates");
        // parameters
        request.addProperty("latitude", latitude);
        request.addProperty("longitude", longitude);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        // marshal double
        // http://seesharpgears.blogspot.com/2010/11/implementing-ksoap-marshal-interface.html
        blueharvest.geocaching.soap.objects.marshals.MarshalDouble md
                = new blueharvest.geocaching.soap.objects.marshals.MarshalDouble();
        md.register(envelope);
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/GetLocationByCoordinates", envelope);
            org.ksoap2.serialization.SoapObject response
                    = (org.ksoap2.serialization.SoapObject) envelope.getResponse();
            l = new location(java.util.UUID.fromString(response.getProperty("id").toString()), null,
                    Double.parseDouble(response.getProperty("latitude").toString()),
                    Double.parseDouble(response.getProperty("longitude").toString()),
                    Integer.parseInt(response.getProperty("altitude").toString()), null);
            // todo: address
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return l;
    }

    /**
     * <h3>inserts a location</h3>
     * <a href="http://www.latlong.net/place/statue-of-liberty-national-monument-new-york-usa-2122.html">
     * Statue of Liberty National Monument</a><br>
     * coordinates: 40.689247, -74.044502, altitude/elevation: 3 meters or 10 feet
     * todo: address
     *
     * @param l (l)ocation
     * @return true/false depending on whether the location was inserted
     * @see <a href="https://blueharvestgeo.com/WebServices/LocationService.asmx?op=InsertLocation">
     * InsertLocation</a>
     * @since 2015-11-09
     */
    public static boolean insert(location l) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("InsertLocation");
        final String ns = "http://blueharvestgeo.com/webservices/";
        org.ksoap2.serialization.SoapObject location
                = new org.ksoap2.serialization.SoapObject(ns, "l");
        location.addProperty("latitude", l.getLatitude().getDecimalDegrees());
        location.addProperty("longitude", l.getLongitude().getDecimalDegrees());
        location.addProperty("altitude", l.getAltitude());
        // todo: address
        request.addSoapObject(location);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        // marshal double
        // http://seesharpgears.blogspot.com/2010/11/implementing-ksoap-marshal-interface.html
        blueharvest.geocaching.soap.objects.marshals.MarshalDouble md
                = new blueharvest.geocaching.soap.objects.marshals.MarshalDouble();
        md.register(envelope);
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false); // prefixing
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        //transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/InsertLocation", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            //System.out.println(transport.requestDump); // testing
            //System.out.println(transport.responseDump); // testing
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

    /**
     * <h3>updates a location</h3>
     * todo: implementation (web service ready and available)
     *
     * @param l (l)ocation
     * @return true/false depending on whether the update was successful
     * @throws java.lang.UnsupportedOperationException - not implemented
     * @see <a href="https://blueharvestgeo.com/WebServices/LocationService.asmx?op=UpdateLocation">
     * UpdateLocation</a>
     * @since 2015-11-09
     */
    public static boolean update(location l) {
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
        public String name;
        public double latitude;
        public double longitude;
        public int altitude;
        public blueharvest.geocaching.soap.objects.address.serialized address;

        public serialized() {
        }

        @Override
        public Object getProperty(int i) {
            switch (i) {
                case 0:
                    return id;
                case 1:
                    return name;
                case 2:
                    return latitude;
                case 3:
                    return longitude;
                case 4:
                    return altitude;
                case 5:
                    return address;
            }
            return null;
        }

        @Override
        public int getPropertyCount() {
            return 6;
        }

        @Override
        public void setProperty(int i, Object o) {
            switch (i) {
                case 0:
                    id = java.util.UUID.fromString(o.toString());
                    break;
                case 1:
                    name = o.toString();
                    break;
                case 2:
                    latitude = (double) o;
                    break;
                case 3:
                    longitude = (double) o;
                    break;
                case 4:
                    altitude = (int) o;
                    break;
                case 5:
                    address = (blueharvest.geocaching.soap.objects.address.serialized) o;
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
                    propertyInfo.name = "name";
                    break;
                case 2:
                    propertyInfo.type = double.class; //org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "latitude";
                    break;
                case 3:
                    propertyInfo.type = double.class; //org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "longitude";
                    break;
                case 4:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "altitude";
                    break;
                case 5:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "address";
                    break;
            }
        }

    }

}
