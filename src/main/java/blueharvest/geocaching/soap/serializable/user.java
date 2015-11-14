package blueharvest.geocaching.soap.serializable;

/**
 * @author jmb
 * @since 2015-11-08
 * @deprecated use {@link blueharvest.geocaching.soap.objects.user.serialized}
 */
@SuppressWarnings("ALL")
public class user extends blueharvest.geocaching.concepts.user
        implements org.ksoap2.serialization.KvmSerializable {

    public user(java.util.UUID id, java.util.Date anniversary, String username,
                String password, java.util.UUID salt, String email, boolean active,
                boolean locked, blueharvest.geocaching.concepts.location location,
                blueharvest.geocaching.concepts.image image,
                blueharvest.geocaching.concepts.role role) {
        super(id, anniversary, username, password, salt, email, active, locked,
                location, image, role);
    }

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {
    }

    @Override
    public void getPropertyInfo(int i, java.util.Hashtable hashtable,
                                org.ksoap2.serialization.PropertyInfo propertyInfo) {
    }

}
