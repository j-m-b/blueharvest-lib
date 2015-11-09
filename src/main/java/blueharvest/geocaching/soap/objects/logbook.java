package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class logbook extends blueharvest.geocaching.concepts.logbook {

    public logbook(java.util.UUID id, java.util.ArrayList<entry> entries) {
        super(id, entries);
    }

    public static logbook get(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean insert(logbook l) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean update(logbook l) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

}
