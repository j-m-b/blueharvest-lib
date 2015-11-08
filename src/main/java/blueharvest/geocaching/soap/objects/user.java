package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-07
 */
public class user extends blueharvest.geocaching.concepts.user { // implements skeleton<user>

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
    private user(java.util.UUID id, java.util.Date anniversary, String username,
                 String password, java.util.UUID salt, String email, boolean active,
                 boolean locked, blueharvest.geocaching.concepts.location location,
                 blueharvest.geocaching.concepts.image image,
                 blueharvest.geocaching.concepts.role role) {
        super(id, anniversary, username, password, salt, email, active, locked,
                location, image, role);
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
                    response.getProperty("anniversary").toString(), // username
                    response.getProperty("password").toString(),
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
            // todo: do something
        } catch (java.text.ParseException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return u;
    }

    /**
     * <h3>insert</h3>
     * inserts a user through the web service into storage; username,
     * password (clear text), email, active, and locked are req'd; id and
     * anniversary computed by the database; salt and password (hashed) computed
     * by the web service
     *
     * @param u (u)ser
     * @return true/false dependent on whether the user was inserted
     * @throws java.lang.UnsupportedOperationException not supported yet
     * @since 2015-11-07
     */
    public static boolean insert(user u) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
        //return false;
    }

    /**
     * <h3>update</h3>
     * updates the user through the web service into storage; id, active,
     * and locked the only update-able attributes at this time and therefore
     * req'd (all others may be null)
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

}
