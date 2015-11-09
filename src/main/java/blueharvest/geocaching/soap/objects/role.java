package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-07
 */
public class role extends blueharvest.geocaching.concepts.role {

    public role(java.util.UUID id, String name) {
        super(id, name);
    }

    /**
     * serialized representation to use in soap
     */
    public static class serialized implements org.ksoap2.serialization.KvmSerializable {

        public java.util.UUID id; // this could be a problem
        public String name;

        public serialized() {
        }

        @Override
        public Object getProperty(int i) {
            switch (i) {
                case 0:
                    return id;
                case 1:
                    return name;
            }
            return null;
        }

        @Override
        public int getPropertyCount() {
            return 2;
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
            }
        }

    }

}
