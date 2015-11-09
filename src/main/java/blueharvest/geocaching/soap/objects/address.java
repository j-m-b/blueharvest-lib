package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class address extends blueharvest.geocaching.concepts.address {

    /**
     * <h3>address constructor</h3>
     *
     * @param street     the street of the address
     * @param city       the city of the address
     * @param region     the region of the address
     * @param postalcode the postal code of the address
     * @since 2015-11-08
     */
    public address(String street, String city,
                   blueharvest.geocaching.concepts.address.region region,
                   String postalcode) {
        super(street, city, region, postalcode);
    }

    public static address get(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean insert(address a) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean update(address a) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

}
