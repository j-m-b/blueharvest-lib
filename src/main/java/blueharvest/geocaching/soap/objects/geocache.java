package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class geocache extends blueharvest.geocaching.concepts.geocache {

    /**
     * <h3>constructor</h3>
     * instantiates this
     *
     * @param id          identifier
     * @param anniversary date which this was created
     * @param name        the name of this
     * @param description the description of this
     * @param difficulty  how difficult this is
     * @param size        the size of this
     * @param terrain     the terrain of this
     * @param status      the status of this
     * @param type        the type this is
     * @param creator     the user that created this
     * @param images      the images associated with this
     * @param location    the location of this
     * @param logbook     the logbook associated with this
     * @see blueharvest.geocaching.concepts.user
     * @see blueharvest.geocaching.concepts.image
     * @see blueharvest.geocaching.concepts.location
     * @see blueharvest.geocaching.concepts.logbook
     * @since 2015-11-08
     */
    public geocache(java.util.UUID id, java.util.Date anniversary,
                    String name, String description, int difficulty,
                    int size, int terrain, int status, int type, user creator,
                    java.util.ArrayList<blueharvest.geocaching.concepts.image> images,
                    blueharvest.geocaching.concepts.location location,
                    blueharvest.geocaching.concepts.logbook logbook) {
        super(id, anniversary, name, description, difficulty, size,
                terrain, status, type, creator, images, location, logbook);
    }

    public static geocache get(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean insert(geocache g) {
        return false;
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean update(geocache g) {
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
        public java.util.Date anniversary; // this could be a problem, too
        public String name;
        public String description;
        public int difficulty;
        public int terrain;
        public int size;
        public int status;
        public int type;
        public blueharvest.geocaching.soap.objects.user.serialized creator;
        public java.util.ArrayList<blueharvest.geocaching.soap.objects.image.serialized> images;
        public blueharvest.geocaching.soap.objects.location.serialized location;
        public blueharvest.geocaching.soap.objects.logbook.serialized logbook;

        public serialized() {
        }

        @Override
        public Object getProperty(int i) {
            switch (i) {
                case 0:
                    return id;
                case 1:
                    return anniversary;
                case 2:
                    return name;
                case 3:
                    return description;
                case 4:
                    return difficulty;
                case 5:
                    return terrain;
                case 6:
                    return size;
                case 7:
                    return status;
                case 8:
                    return type;
                case 9:
                    return creator;
                case 10:
                    return images;
                case 11:
                    return location;
                case 12:
                    return logbook;
            }
            return null;
        }

        @Override
        public int getPropertyCount() {
            return 13;
        }

        @Override
        public void setProperty(int i, Object o) {
            switch (i) {
                case 0:
                    id = java.util.UUID.fromString(o.toString());
                    break;
                case 1:
                    try {
                        anniversary = new java.text.SimpleDateFormat(
                                "yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.US).parse(
                                o.toString());
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    name = o.toString();
                    break;
                case 3:
                    description = o.toString();
                    break;
                case 4:
                    difficulty = (int) o;
                    break;
                case 5:
                    terrain = (int) o;
                    break;
                case 6:
                    size = (int) o;
                    break;
                case 7:
                    status = (int) o;
                    break;
                case 8:
                    type = (int) o;
                    break;
                case 9:
                    creator = (blueharvest.geocaching.soap.objects.user.serialized) o;
                    break;
                case 10:
                    images = (java.util.ArrayList<blueharvest.geocaching.soap.objects.image.serialized>) o;
                    break;
                case 11:
                    location = (blueharvest.geocaching.soap.objects.location.serialized) o;
                    break;
                case 12:
                    logbook = (blueharvest.geocaching.soap.objects.logbook.serialized) o;
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
                    propertyInfo.name = "anniversary";
                    break;
                case 2:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "name";
                    break;
                case 3:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "description";
                    break;
                case 4:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "difficulty";
                    break;
                case 5:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "terrain";
                    break;
                case 6:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "size";
                    break;
                case 7:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "status";
                    break;
                case 8:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.INTEGER_CLASS;
                    propertyInfo.name = "type";
                    break;
                case 9:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "user";
                    break;
                case 10:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.VECTOR_CLASS;
                    propertyInfo.name = "images";
                    break;
                case 11:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "location";
                    break;
                case 12:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                    propertyInfo.name = "logbook";
                    break;
            }
        }

    }

}
