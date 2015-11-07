package blueharvest.geocaching.concepts;

/**
 * @author jmb
 * @since 2015-11-07
 */
public abstract class role {

    private final java.util.UUID id;
    private final String name;

    /**
     * <h3>constructor</h3>
     * @param id id of this
     * @param name name of this
     * @since 2015-11-07
     */
    public role(java.util.UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public java.util.UUID getId() { return id; }

    public String getName() { return name; }

}
