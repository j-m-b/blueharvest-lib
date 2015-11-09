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

    /**
     * serialized representation to use in soap
     */
    public static class serialized implements org.ksoap2.serialization.KvmSerializable {

        public java.util.UUID id; // this could be a problem
        public java.net.URI uri;  // this could be a problem, too
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
                    uri = java.net.URI.create(o.toString());
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
