package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-07
 */
public class user extends blueharvest.geocaching.concepts.user {

    private final static String url = "https://blueharvestgeo.com/WebServices/UserService.asmx";

    private String request;
    private String response;

    /**
     * <h3>constructor</h3>
     *
     * @param id          id
     * @param anniversary the date the user was created
     * @param username    username
     * @param password    hashed password
     * @param salt        salt for hash
     * @param email       email
     * @param active      true/false
     * @param locked      true/false
     * @param location    location
     * @param image       avatar/profile picture
     * @param role        personalization and membership role
     * @see blueharvest.geocaching.concepts.location
     * @see blueharvest.geocaching.concepts.image
     * @see blueharvest.geocaching.concepts.role
     * @since 2015-11-07
     */
    public user(java.util.UUID id, java.util.Date anniversary, String username,
                String password, java.util.UUID salt, String email, boolean active,
                boolean locked, blueharvest.geocaching.concepts.location location,
                blueharvest.geocaching.concepts.image image,
                blueharvest.geocaching.concepts.role role) {
        super(id, anniversary, username, password, salt, email, active, locked,
                location, image, role);
        request = "";
        response = "";
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

    /**
     * <h3>gets a user</h3>
     * via web services
     *
     * @param username username
     * @return a user or null
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=GetUser">GetUser</a>
     * @since 2015-11-07
     * todo: location and image
     */
    public static user get(String username) {
        user u = null;
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("GetUser");
        // parameters
        request.addProperty("username", username);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/GetUser", envelope);
            org.ksoap2.serialization.SoapObject response
                    = (org.ksoap2.serialization.SoapObject) envelope.getResponse();
            u = new user(java.util.UUID.fromString(response.getProperty("id").toString()),
                    new java.text.SimpleDateFormat(
                            "yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.US).parse(
                            response.getProperty("anniversary").toString()),
                    username, response.getProperty("password").toString(),
                    java.util.UUID.fromString(response.getProperty("salt").toString()),
                    response.getProperty("email").toString(),
                    Boolean.valueOf(response.getProperty("active").toString()),
                    Boolean.valueOf(response.getProperty("locked").toString()),
                    null, null,  // todo: location, image
                    new role(java.util.UUID.fromString(
                            ((org.ksoap2.serialization.SoapObject) response.getProperty("role"))
                                    .getProperty("id").toString()),
                            ((org.ksoap2.serialization.SoapObject) response.getProperty("role"))
                                    .getProperty("name").toString()));
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        } catch (java.text.ParseException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return u;
    }

    /**
     * <h3>inserts a user</h3>
     * inserts a user through the web service into storage; username,
     * password (clear text), email, active, locked, and role.name are req'd; id and
     * anniversary default in storage; salt and password (hashed) computed
     * by the web service
     * todo: complexType implementation
     *
     * @param u (u)ser
     * @return true/false dependent on whether the user was inserted
     * @throws java.lang.UnsupportedOperationException not supported yet
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=InsertUser">InsertUser</a>
     * @since 2015-11-07
     * @deprecated use {@link blueharvest.geocaching.soap.objects.user#insert(String, String, String, String)}
     */
    public static boolean insert(user u) {
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("InsertUser");
        // todo: complexType
        /*String xml = "<u>" +
                "<username>" + u.getUsername() + "</username>" +
                "<password>" + u.getPassword() + "</password>" +
                "<email>" + u.getEmail() + "</email>" +
                "<active>" + String.valueOf(u.isActive()) + "</active>" +
                "<locked>" + String.valueOf(u.isLocked()) + "</locked>" +
                "<role><name>" + u.getRole().getName() + "</name></role>" +
                "</u>";
        request.setInnerText(xml); // outputs cdata
        */

        // never returns anything but false
        final String ns = "http://blueharvestgeo.com/webservices/";
        org.ksoap2.serialization.SoapObject user
                = new org.ksoap2.serialization.SoapObject(ns, "user");
        //user.addProperty("id", null);
        //user.addProperty("anniversary", null);
        user.addProperty("username", u.getUsername());
        user.addProperty("password", u.getPassword());
        //user.addProperty("salt", null);
        user.addProperty("email", u.getEmail());
        user.addProperty("active", u.isActive());
        user.addProperty("locked", u.isLocked());
        org.ksoap2.serialization.SoapObject role
                = new org.ksoap2.serialization.SoapObject(ns, "role");
        //role.addProperty("id", null);
        role.addProperty("name", u.getRole().getName());
        user.addSoapObject(role);
        //user.addProperty("empty", null);
        request.addSoapObject(user);

        // some things aren't serializable
        /*blueharvest.geocaching.soap.objects.user.serialized v
                = new blueharvest.geocaching.soap.objects.user.serialized();
        v.username = u.getUsername();
        v.password = u.getPassword();
        v.email = u.getEmail();
        v.active = u.isActive();
        v.locked = u.isLocked();
        v.role = new blueharvest.geocaching.soap.objects.role.serialized();
        org.ksoap2.serialization.PropertyInfo pi = new org.ksoap2.serialization.PropertyInfo();
        pi.setName("u");
        pi.setValue(v);
        pi.setType("User");
        request.addProperty(pi);
        */

        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false); // prefixing
        envelope.setOutputSoapObject(request);
        // for **returning** a complexType
        //envelope.addMapping("http://blueharvestgeo.com/webservices/", "User", u.getClass());
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url); // ?wsdl
        transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/InsertUser", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            u.setRequest(transport.requestDump); // testing
            u.setResponse(transport.responseDump); // testing
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
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * <h3>inserts a user</h3>
     * inserts a user through the web service into storage; parameters req'd; id and anniversary
     * default in storage; salt and password (hashed) computed by the web service;
     * active is true and locked is false; specify "Basic" user.role.name for general users
     * use instead of {@link blueharvest.geocaching.soap.objects.user#insert(user)}
     *
     * @param username unique username
     * @param password clear text password
     * @param email    email (ex. handle@domain.com)
     * @param rolename role for the user (ex. Basic, Premium, etc.)
     * @return true/false dependent on whether the user was inserted
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=InsertSimpleUser">InsertSimpleUser</a>
     * @since 2015-11-08
     */
    public static boolean insert(String username, String password, String email, String rolename) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("InsertSimpleUser");
        // parameters
        request.addProperty("username", username);
        request.addProperty("password", password);
        request.addProperty("email", email);
        request.addProperty("rolename", rolename);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/InsertSimpleUser", envelope);
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

    /**
     * <h3>updates a user</h3>
     * updates the user through the web service into storage; id, active,
     * and locked the only update-able attributes at this time and therefore
     * req'd (all others may be null)
     * todo: implementation
     *
     * @param u (u)ser
     * @return true/false dependent on whether the user was updated
     * @throws java.lang.UnsupportedOperationException not supported yet
     * @since 2015-11-07
     */
    public static boolean update(user u) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
        //return false;
    }

    /**
     * <h3>deletes a user</h3>
     * todo: implementation
     *
     * @param id id of the user
     * @return true/false dependent on whether the user was deleted from storage
     * @throws java.lang.UnsupportedOperationException not supported yet
     * @since 2015-11-07
     */
    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
        //return false;
    }

    /**
     * <h3>authorizes a user</h3>
     * authorization for a user by username and password
     * todo: implementation
     *
     * @param username unique username of the user
     * @param password password (plain text) of the user
     * @return true/false dependent on credentials and inner exceptions
     * @throws java.lang.UnsupportedOperationException not supported yet
     * @since 2015-11-07
     */
    public static boolean auth(String username, String password) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
        //return false;
    }

    /**
     * nested class for serialized object for soap
     *
     * @see <a href="http://seesharpgears.blogspot.com/2010/10/ksoap-android-web-service-tutorial-with.html">
     * KSoap Android Web Service Tutorial With Sample Code</a>
     * @since 2015-11-08
     */
    public static class serialized implements org.ksoap2.serialization.KvmSerializable {

        public java.util.UUID id;
        public java.util.Date anniversary;
        public String username;
        public String password;
        public java.util.UUID salt;
        public String email;
        public boolean active;
        public boolean locked;
        public Object location; // todo
        public Object image; // todo
        public blueharvest.geocaching.soap.objects.role.serialized role;

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
                    return username;
                case 3:
                    return password;
                case 4:
                    return salt;
                case 5:
                    return email;
                case 6:
                    return active;
                case 7:
                    return locked;
                case 8:
                    return location;
                case 9:
                    return image;
                case 10:
                    return role;
            }
            return null;
        }

        @Override
        public int getPropertyCount() {
            return 11;
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
                    username = o.toString();
                    break;
                case 3:
                    password = o.toString();
                    break;
                case 4:
                    salt = java.util.UUID.fromString(o.toString());
                    break;
                case 5:
                    email = o.toString();
                    break;
                case 6:
                    active = Boolean.valueOf(o.toString());
                    break;
                case 7:
                    locked = Boolean.valueOf(o.toString());
                    break;
                case 8:
                    location = o;
                    break;
                case 9:
                    image = o;
                    break;
                case 10:
                    role = (blueharvest.geocaching.soap.objects.role.serialized) o;
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
                    propertyInfo.name = "username";
                    break;
                case 3:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "password";
                    break;
                case 4: // salt
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "salt";
                    break;
                case 5: // email
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "email";
                    break;
                case 6: // active
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.BOOLEAN_CLASS;
                    propertyInfo.name = "active";
                    break;
                case 7: // locked
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.BOOLEAN_CLASS;
                    propertyInfo.name = "locked";
                    break;
                case 8: // location
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "location";
                    break;
                case 9: // image
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "image";
                    break;
                case 10: // role
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "role";
                    break;
            }
        }

    }

}
