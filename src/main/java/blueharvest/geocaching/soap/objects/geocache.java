package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class geocache extends blueharvest.geocaching.concepts.geocache {

    private final static String url = "https://blueharvestgeo.com/WebServices/GeocacheService.asmx";

    private String request;
    private String response;

    /**
     * <h3>constructor</h3>
     * instantiates this
     *
     * @param id          identifier
     * @param anniversary date which this was created
     * @param name        the name of this
     * @param description the description of this
     * @param difficulty  how difficult this is
     * @param size        the size of this
     * @param terrain     the terrain of this
     * @param status      the status of this
     * @param type        the type this is
     * @param creator     the user that created this
     * @param images      the images associated with this
     * @param location    the location of this
     * @param logbook     the logbook associated with this
     * @see blueharvest.geocaching.concepts.user
     * @see blueharvest.geocaching.concepts.image
     * @see blueharvest.geocaching.concepts.location
     * @see blueharvest.geocaching.concepts.logbook
     * @since 2015-11-08
     */
    public geocache(java.util.UUID id, java.util.Date anniversary,
                    String name, String description, int difficulty,
                    int size, int terrain, int status, int type, user creator,
                    java.util.ArrayList<blueharvest.geocaching.concepts.image> images,
                    blueharvest.geocaching.concepts.location location,
                    blueharvest.geocaching.concepts.logbook logbook) {
        super(id, anniversary, name, description, difficulty, size,
                terrain, status, type, creator, images, location, logbook);
        this.request = "";
        this.response = "";
    }

    public void setRequest(String value) {
        request = value;
    }

    public void setResponse(String value) {
        response = value;
    }

    public String getResponse() {
        return response;
    }

    public String getRequest() {
        return request;
    }

    public static geocache get(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * <h3>inserts a geocache</h3>
     * required: name, description, difficulty, terrain, size, status, type, user.id,
     * location.latitude, location.longitude, location.altitude req'd;
     * if the location exists by coordinates, then the location will be set to the existing
     * location; otherwise, the location is, too, inserted; the logbook is inserted as well
     *
     * @param g (g)eocache
     * @return true/false depending on success of insert
     * @see <a href="https://blueharvestgeo.com/WebServices/GeocacheService.asmx?op=InsertGeocache">
     * InsertGeocache</a>
     * @since 2015-11-09
     */
    public static boolean insert(geocache g) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("InsertGeocache");
        final String ns = "http://blueharvestgeo.com/webservices/";
        // parameters
        org.ksoap2.serialization.SoapObject geocache
                = new org.ksoap2.serialization.SoapObject(ns, "g"); // <g>
        //geocache.addProperty("id", null); // <id>guid</id> (default)
        //geocache.addProperty("anniversary", null); // <anniversary>dateTime</anniversary> (default)
        geocache.addProperty("name", g.getName()); // <name>string</name>
        geocache.addProperty("description", g.getDescription()); // <description>string</description>
        geocache.addProperty("difficulty", g.getDifficulty()); // <difficulty>int</difficulty>
        geocache.addProperty("terrain", g.getTerrain()); // <terrain>int</terrain>
        geocache.addProperty("size", g.getSize()); // <size>int</size>
        geocache.addProperty("status", g.getStatus()); // <status>int</status>
        geocache.addProperty("type", g.getType()); // <type>int</type>
        //geocache.addProperty("userid", null); // <userid>guid</userid> (n/a)
        /*****************user******************/
        org.ksoap2.serialization.SoapObject user
                = new org.ksoap2.serialization.SoapObject(ns, "user"); // <user>
        user.addProperty("id", g.getCreator().getId().toString()); // <id>guid</id>
        //user.addProperty("anniversary", g.getCreator().getAnniversary()); // <anniversary>dateTime</anniversary> (n/a)
        //user.addProperty("username", g.getCreator().getUsername()); // <username>string</username> (n/a)
        //user.addProperty("password", g.getCreator().getPassword()); // <password>string</password> (n/a)
        //user.addProperty("salt", g.getCreator().getSalt().toString()); // <salt>guid</salt> (n/a)
        //user.addProperty("email", g.getCreator().getEmail()); // <email>string</email> (n/a)
        //user.addProperty("active", g.getCreator().isActive()); // <active>boolean</active> (n/a)
        //user.addProperty("locked", g.getCreator().isLocked()); // <locked>boolean</locked> (n/a)
        /*****************role******************/
        /*org.ksoap2.serialization.SoapObject role
                = new org.ksoap2.serialization.SoapObject(ns, "role"); // <role>
        role.addProperty("id", g.getCreator().getRole().getId().toString()); // <id>guid</id>
        role.addProperty("name", g.getCreator().getRole().getName()); // <name>string</name>
        user.addSoapObject(role); // </role> (n/a)*/
        //user.addProperty("empty", null); // <empty>boolean</empty> (n/a)
        geocache.addSoapObject(user); // </user>
        //geocache.addProperty("locationid", null); // <locationid>guid</locationid> (n/a)
        /***************location****************/
        org.ksoap2.serialization.SoapObject location
                = new org.ksoap2.serialization.SoapObject(ns, "location"); // <location>
        //location.addProperty("id", g.getLocation().getId()); // <id>guid</id> (n/a)
        location.addProperty("latitude", g.getLocation().getLatitude().getDecimalDegrees()); // <latitude>double</latitude>
        location.addProperty("longitude", g.getLocation().getLongitude().getDecimalDegrees()); // <longitude>double</longitude>
        location.addProperty("altitude", g.getLocation().getAltitude()); // <altitude>int</altitude>
        geocache.addSoapObject(location); // </location>
        //geocache.addProperty("logbookid", null); // <logbookid>guid</logbookid> (n/a)
        /***************location****************/
        /*org.ksoap2.serialization.SoapObject logbook
                = new org.ksoap2.serialization.SoapObject(ns, "logbook"); // <logbook>
        logbook.addProperty("id", g.getLogbook().getId()); // <id>guid</id>
        logbook.addProperty("datetime", g.getLogbook().getDate()); // <datetime>dateTime</datetime>
        geocache.addSoapObject(logbook); // </logbook> (n/a)*/
        request.addSoapObject(geocache); // </g>
        // end parameters
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        // marshal double
        // http://seesharpgears.blogspot.com/2010/11/implementing-ksoap-marshal-interface.html
        blueharvest.geocaching.soap.objects.marshals.MarshalDouble mdbl
                = new blueharvest.geocaching.soap.objects.marshals.MarshalDouble();
        mdbl.register(envelope);
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false); // prefixing
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/InsertGeocache", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            g.setRequest(transport.requestDump); // testing
            g.setResponse(transport.responseDump); // testing
            return Boolean.parseBoolean(response.toString());
        } catch (org.ksoap2.SoapFault ex) {
            throw new RuntimeException("soap fault:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (org.ksoap2.transport.HttpResponseException ex) {
            throw new RuntimeException("http response exception:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (java.io.IOException ex) {
            throw new RuntimeException("io exception:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException("xml pull parser exception:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (java.lang.Exception ex) {
            throw new RuntimeException("exception:" + ex.getLocalizedMessage() + " ... " + transport.requestDump);
        }
    }

    public static boolean update(geocache g) {
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
        public java.util.Date anniversary; // this could be a problem, too
        public String name;
        public String description;
        public int difficulty;
        public int terrain;
        public int size;
        public int status;
        public int type;
        public blueharvest.geocaching.soap.objects.user.serialized creator;
        public java.util.ArrayList<blueharvest.geocaching.soap.objects.image.serialized> images;
        public blueharvest.geocaching.soap.objects.location.serialized location;
        public blueharvest.geocaching.soap.objects.logbook.serialized logbook;

        public serialized() {
        }

        @Override
        public Object getProperty(int i) {
            switch (i) {
                case 0:
                    return id;
                case 1:
                    return anniversary;
                case 2:
                    return name;
                case 3:
                    return description;
                case 4:
                    return difficulty;
                case 5:
                    return terrain;
                case 6:
                    return size;
                case 7:
                    return status;
                case 8:
                    return type;
                case 9:
                    return creator;
                case 10:
                    return images;
                case 11:
                    return location;
                case 12:
                    return logbook;
            }
            return null;
        }

        @Override
        public int getPropertyCount() {
            return 13;
        }

        @Override
        public void setProperty(int i, Object o) {
            switch (i) {
                case 0:
                    id = java.util.UUID.fromString(o.toString());
                    break;
                case 1:
                    try {
                        anniversary = new java.text.SimpleDateFormat(
                                "yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.US).parse(
                                o.toString());
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    name = o.toString();
                    break;
                case 3:
                    description = o.toString();
                    break;
                case 4:
                    difficulty = (int) o;
                    break;
                case 5:
                    terrain = (int) o;
                    break;
                case 6:
                    size = (int) o;
                    break;
                case 7:
                    status = (int) o;
                    break;
                case 8:
                    type = (int) o;
                    break;
                case 9:
                    creator = (blueharvest.geocaching.soap.objects.user.serialized) o;
                    break;
                case 10:
                    images = (java.util.ArrayList<blueharvest.geocaching.soap.objects.image.serialized>) o;
                    break;
                case 11:
                    location = (blueharvest.geocaching.soap.objects.location.serialized) o;
                    break;
                case 12:
                    logbook = (blueharvest.geocaching.soap.objects.logbook.serialized) o;
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
                    propertyInfo.name = "anniversary";
                    break;
                case 2:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "name";
                    break;
                case 3:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "description";
                    break;
                case 4:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "difficulty";
                    break;
                case 5:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "terrain";
                    break;
                case 6:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "size";
                    break;
                case 7:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "status";
                    break;
                case 8:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "type";
                    break;
                case 9:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "user";
                    break;
                case 10:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.VECTOR_CLASS;
                    propertyInfo.name = "images";
                    break;
                case 11:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "location";
                    break;
                case 12:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "logbook";
                    break;
            }
        }

    }

}
