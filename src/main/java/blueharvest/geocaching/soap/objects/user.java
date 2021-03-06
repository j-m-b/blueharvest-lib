package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-07
 */
public class user extends blueharvest.geocaching.concepts.user {

    private final static String url = "https://blueharvestgeo.com/WebServices/UserService.asmx";

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
    public user(java.util.UUID id, java.util.Date anniversary, String username, String password,
                java.util.UUID salt, String email, boolean active, boolean locked,
                @SuppressWarnings({"null", "SameParameterValue"}) /*@com.sun.istack.internal.Nullable*/
                        blueharvest.geocaching.concepts.location location,
                @SuppressWarnings({"null", "SameParameterValue"}) /*@com.sun.istack.internal.Nullable*/
                        blueharvest.geocaching.concepts.image image,
                blueharvest.geocaching.concepts.role role) {
        super(id, anniversary, username, password, salt, email, active, locked,
                location, image, role);
    }

    /**
     * <h3>gets a user</h3>
     * todo: location and image
     *
     * @param username username credentials to get user information
     * @param password password credentials to get user information
     * @return a user or null
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=GetUser">GetUser</a>
     * @since 2015-11-07
     */
    public static user get(String username, String password) {
        user u = null;
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("GetUser");
        // parameters
        request.addProperty("username", username);
        request.addProperty("password", password);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/GetUser", envelope);
            org.ksoap2.serialization.SoapObject response
                    = (org.ksoap2.serialization.SoapObject) envelope.getResponse();
            if (response != null)
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
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException |
                java.text.ParseException | java.lang.NullPointerException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return u;
    }

    /**
     * <h3>inserts a user</h3>
     * inserts a user through the web service into storage; username,
     * password (clear text), email, active, locked, and role.name are req'd; id and
     * anniversary default in storage; salt and password (hashed) computed
     * by the web service<br>
     * for a simpler way, see
     * {@link blueharvest.geocaching.soap.objects.user#insert(String, String, String, String)}
     *
     * @param u (u)ser
     * @return true/false dependent on whether the user was inserted
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=InsertUser">InsertUser</a>
     * @since 2015-11-07
     */
    public static boolean insert(user u) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("InsertUser");
        final String ns = "http://blueharvestgeo.com/webservices/";
        // parameters
        org.ksoap2.serialization.SoapObject user
                = new org.ksoap2.serialization.SoapObject(ns, "u");
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
        // end parameters
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url); // ?wsdl
        //transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/InsertUser", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            //System.out.println(transport.requestDump); // testing
            //System.out.println(transport.responseDump); // testing
            return response != null && Boolean.parseBoolean(response.toString());
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        } // java.lang.Exception, org.ksoap2.transport.HttpResponseException, org.ksoap2.SoapFault
    }

    /**
     * <h3>inserts a user</h3>
     * inserts a user through the web service into storage; parameters req'd; id and anniversary
     * default in storage; salt and password (hashed) computed by the web service;
     * active is true and locked is false; specify "Basic" user.role.name for general users
     * for a more complex way, use {@link blueharvest.geocaching.soap.objects.user#insert(user)}
     *
     * @param username unique username
     * @param password clear text password
     * @param email    email (ex. handle@domain.com)
     * @param rolename role for the user (ex. Basic, Premium, etc.)
     * @return true/false dependent on whether the user was inserted
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=InsertSimpleUser">
     * InsertSimpleUser</a>
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
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * <h3>updates a user</h3>
     * updates the user through the web service into storage; id required; active
     * and locked the only update-able attributes at this time and therefore
     * req'd; username and password required for authentication (all others may be null);<br>
     * catch 22: a locked or inactive user is not permitted to update, meaning, unless
     * the user is active and not locked prior to update, the request is not authenticated
     * and the update fails
     *
     * @param u (u)ser
     * @return true/false dependent on whether the update was successful
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=UpdateUser">
     * UpdateUser</a>
     * @since 2015-11-07
     */
    public static boolean update(user u) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("UpdateUser");
        final String ns = "http://blueharvestgeo.com/webservices/";
        // parameters
        org.ksoap2.serialization.SoapObject user
                = new org.ksoap2.serialization.SoapObject(ns, "u");
        user.addProperty("id", u.getId().toString());
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
        // end parameters
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        //transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/UpdateUser", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            //System.out.println(transport.requestDump); // testing
            //System.out.println(transport.responseDump); // testing
            return response != null && Boolean.parseBoolean(response.toString());
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        } // org.ksoap2.transport.HttpResponseException, org.ksoap2.SoapFault, org.ksoap2.SoapFault
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
    @SuppressWarnings("UnusedParameters")
    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
        //return false;
    }

    /**
     * <h3>authorizes a user</h3>
     * authorization for a user by username and password
     *
     * @param username unique username of the user
     * @param password password (plain text) of the user
     * @return true/false dependent on credentials and inner exceptions
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=AuthUser">AuthUser</a>
     * @since 2015-11-07
     */
    public static boolean auth(String username, String password) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("AuthUser");
        // parameters
        request.addProperty("username", username);
        request.addProperty("password", password);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/AuthUser", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            return response != null && Boolean.parseBoolean(response.toString());
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * <h3>checks if the username already exists</h3>
     *
     * @param username username to check
     * @return true if the username exists, false if the username does
     * not exist
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=IsUsernameAvailable">
     * IsUserName</a>
     * @since 2015-11-10
     */
    public static boolean exists(String username) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("IsUsernameAvailable");
        // parameters
        request.addProperty("username", username);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/IsUsernameAvailable", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            if (response == null) throw new RuntimeException("Response is null.");
            return Boolean.parseBoolean(response.toString());
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * <h3>relates a geocache to a user</h3>
     * when found is true, the found relationship is established between a geocache and a user;
     * when found is false, the found relationship is severed between a geocache and a user;
     *
     * @param userid     user identifier
     * @param geocacheid geocache identifier
     * @param found      found/un-found flag
     * @return true/false depending on whether the user was related to the geocache
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=RelateFoundGeocache">
     * RelateFoundGeocache</a>
     * @since 0.0.4, 2015-11-26 Happy Thanksgiving!
     */
    public static boolean relateFoundGeocache(
            java.util.UUID userid, java.util.UUID geocacheid, boolean found) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("RelateFoundGeocache");
        // parameters
        request.addProperty("userid", userid.toString());
        request.addProperty("geocacheid", geocacheid.toString());
        request.addProperty("found", found);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/RelateFoundGeocache", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * <h3>relates a geocache to a user</h3>
     * when favorite is true, the favorite relationship is established between the geocache and
     * the user; when favorite is false, the favorite relationship is severed between a
     * geocache and a user.
     *
     * @param userid     user identifier
     * @param geocacheid geocache identifier
     * @param favorite   favorite/unfavorite flag
     * @return true/false depending on whether the user was related to the geocache
     * @see <a href="https://blueharvestgeo.com/WebServices/UserService.asmx?op=RelateFavoriteGeocache">
     * RelateFavoriteGeocache</a>
     * @since 0.0.4, 2015-11-26 Happy Thanksgiving!
     */
    public static boolean relateFavoriteGeocache(
            java.util.UUID userid, java.util.UUID geocacheid, boolean favorite) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("RelateFavoriteGeocache");
        // parameters
        request.addProperty("userid", userid.toString());
        request.addProperty("geocacheid", geocacheid.toString());
        request.addProperty("favorite", favorite);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/RelateFavoriteGeocache", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    /**
     * nested class for serialized object for soap
     *
     * @see <a href="http://seesharpgears.blogspot.com/2010/10/ksoap-android-web-service-tutorial-with.html">
     * KSoap Android Web Service Tutorial With Sample Code</a>
     * @since 2015-11-08
     */
    public static class serialized implements org.ksoap2.serialization.KvmSerializable {

        public java.util.UUID id; // this may be a problem ...
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
