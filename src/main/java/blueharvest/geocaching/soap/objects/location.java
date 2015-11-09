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
