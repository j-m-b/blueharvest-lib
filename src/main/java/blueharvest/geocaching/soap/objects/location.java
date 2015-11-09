package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class location extends blueharvest.geocaching.concepts.location {

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
     * @see coordinate
     */
    public location(java.util.UUID id, String name, double latitude, double longitude,
                    int altitude, blueharvest.geocaching.concepts.address address) {
        super(id, name, latitude, longitude, altitude, address);
    }

    public static location get(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean insert(location l) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean update(location l) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

}
