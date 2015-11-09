package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class logbook extends blueharvest.geocaching.concepts.logbook {

    public logbook(java.util.UUID id,
                   java.util.ArrayList<blueharvest.geocaching.concepts.logbook.entry> entries) {
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

    public static class entry {

        /**
         * serialized representation to use in soap
         */
        public static class serialized implements org.ksoap2.serialization.KvmSerializable {

            public java.util.UUID id; // this could be a problem
            public java.util.Date date; // this could be a problem, too
            public String title;
            public String text;
            public blueharvest.geocaching.soap.objects.user.serialized user;

            public serialized() {
            }

            @Override
            public Object getProperty(int i) {
                switch (i) {
                    case 0:
                        return id;
                    case 1:
                        return date;
                    case 2:
                        return title;
                    case 3:
                        return text;
                    case 4:
                        return user;
                }
                return null;
            }

            @Override
            public int getPropertyCount() {
                return 5;
            }

            @Override
            public void setProperty(int i, Object o) {
                switch (i) {
                    case 0:
                        id = java.util.UUID.fromString(o.toString());
                        break;
                    case 1:
                        try {
                            date = new java.text.SimpleDateFormat(
                                    "yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.US).parse(
                                    o.toString());
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        title = o.toString();
                        break;
                    case 3:
                        text = o.toString();
                        break;
                    case 4:
                        user = (blueharvest.geocaching.soap.objects.user.serialized) o;
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
                        propertyInfo.name = "date";
                        break;
                    case 2:
                        propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                        propertyInfo.name = "title";
                        break;
                    case 3:
                        propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                        propertyInfo.name = "text";
                        break;
                    case 4:
                        propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                        propertyInfo.name = "user";
                        break;
                }
            }

        }

    }

}
