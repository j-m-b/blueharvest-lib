package blueharvest.geocaching.soap.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JamieMarie on 12/4/2015.
 */
public class geocachesTest {

    private final static double latitude = 40.689247, longitude = -74.044502, distance = 16d;

    private static final java.util.UUID uid
            = java.util.UUID.fromString("bd357e34-0de5-4ec9-bda3-58b7d86c4bc9");

    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * list favorite geocaches
     * this user should return at least one favorite
     *
     * @throws Exception
     */
    @Test
    public void testFavorites() throws Exception {
        geocache.geocaches g = new geocache.geocaches(uid, geocache.geocaches.type.favorite);
        assertNotNull(g);
        assertTrue(g.size() > 0);
    }

    /**
     * list of found geocaches
     * this user has none found
     *
     * @throws Exception
     */
    @Test
    public void testFound() throws Exception {
        geocache.geocaches g = new geocache.geocaches(uid, geocache.geocaches.type.found);
        assertNotNull(g);
        assertTrue(g.size() == 0);
    }

    /**
     * <h3>get geocaches within a radius</h3>
     * there are two ways, one deprecated since it was a pita;
     * we know these parameters will produce at least one geocache in the area
     *
     * @throws Exception
     * @see blueharvest.geocaching.soap.objects.geocache.geocaches
     * constructor with like 6 or 7 parameters;
     * this is the easier way ...
     */
    @Test
    public void testByRadius() throws Exception {
        geocache.geocaches g = new geocache.geocaches(latitude, longitude, distance);
        assertNotNull(g);
        assertTrue(g.size() > 0);
    }

}