package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class image extends blueharvest.geocaching.concepts.image {

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
    }
    public static image get(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean insert(image i) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean update(image i) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

}
