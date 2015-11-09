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

    /**
     * serialized representation to use in soap
     */
    public static class serialized implements org.ksoap2.serialization.KvmSerializable {

        public String street;
        public String city;
        public String region;
        public String postalcode;

        public serialized() {
        }

        @Override
        public Object getProperty(int i) {
            switch (i) {
                case 0:
                    return street;
                case 1:
                    return city;
                case 2:
                    return region;
                case 3:
                    return postalcode;
            }
            return null;
        }

        @Override
        public int getPropertyCount() {
            return 4;
        }

        @Override
        public void setProperty(int i, Object o) {
            switch (i) {
                case 0:
                    street = o.toString();
                    break;
                case 1:
                    city = o.toString();
                    break;
                case 2:
                    region = o.toString();
                    break;
                case 3:
                    postalcode = o.toString();
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
                    propertyInfo.name = "street";
                    break;
                case 1:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "city";
                    break;
                case 2:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "region";
                    break;
                case 3:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "postalcode";
                    break;
            }
        }

    }

}
