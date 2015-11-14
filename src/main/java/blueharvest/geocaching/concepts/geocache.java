package blueharvest.geocaching.concepts;

/**
 * @author jmb
 * @since 2015-10-13
 */
public abstract class geocache {

    private final java.util.UUID id;
    private final java.util.Date anniversary;
    private final String name;
    private final String description;
    private final int difficulty;
    private final int terrain;
    private final int size;
    private final int status;
    private final int type;
    private final user creator;
    private final java.util.ArrayList<image> images;
    private final location location;
    private final logbook logbook;

    /**
     * <h3>constructor</h3>
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
     * @since 2015-11
     */
    public geocache(java.util.UUID id, java.util.Date anniversary, String name,
                    String description, int difficulty, int size, int terrain, int status,
                    int type, user creator, java.util.ArrayList<image> images,
                    location location, logbook logbook) {
        this.id = id;
        this.anniversary = anniversary;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.terrain = terrain;
        this.size = size;
        this.status = status;
        this.type = type;
        this.creator = creator;
        this.images = images;
        this.logbook = logbook;
        this.location = location;
    }

    public java.util.UUID getId() {
        return id;
    }

    public java.util.Date getAnniversary() {
        return anniversary;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getTerrain() {
        return terrain;
    }

    public int getSize() {
        return size;
    }

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    public user getCreator() {
        return creator;
    }

    public java.util.ArrayList<image> getImages() {
        return images;
    }

    public location getLocation() {
        return location;
    }

    public logbook getLogbook() {
        return logbook;
    }

}
