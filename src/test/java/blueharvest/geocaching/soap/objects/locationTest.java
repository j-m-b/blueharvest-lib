package blueharvest.geocaching.soap.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jmb
 * @since 2015-12-04
 */
public class locationTest {

    private final static java.util.UUID id
            = java.util.UUID.fromString("d341791d-8672-4646-9f5a-5618ada9d343");
    private final static double latitude = 40.689247, longitude = -74.044502;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * <h3>get a location by id</h3>
     */
    @Test
    public void testGet() throws Exception {
        assertNotNull(location.get(id));
    }

    /**
     * <h3>get a location by coordinates</h3>
     *
     * @throws Exception
     */
    @Test
    public void testGet1() throws Exception {
        assertNotNull(location.get(latitude, longitude));
    }

    /**
     * <h3>insert a location</h3>
     * Generally, a Location is inserted with the Geocache. If not, we can have an orphan
     * Location by inserting the Location all by its lonesome.
     * The latitude and longitude must be unique (in combination).
     * Tough call on using altitude and it's required. Hind sight is 20/20.
     * Google hooks us up with altitude information, so it's more information if
     * we decide to add more.<br/>
     * to not dirty the database, the test is not done
     *
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception {
        //new location(null, "Somewhere in the Atlantic", 0, 0, 0, null);
        //location.insert(l);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        location.update(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws Exception {
        location.delete(null);
    }

}