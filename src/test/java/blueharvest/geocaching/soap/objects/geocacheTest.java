package blueharvest.geocaching.soap.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jmb
 * @since 2015-12-03
 */
public class geocacheTest {

    private static final java.util.UUID id
            = java.util.UUID.fromString("ce69040b-fb5e-4914-b1ca-6bd94a428be5");
    private static final String code = "BH13GC7";
    private static final String username = "username";
    private static final String password = "password";
    private static final double latitude = 40.689247;
    private static final double longitude = -74.044502;
    private static final boolean favorite = true;
    private static final boolean found = false;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * get a geocache object from a known id and make sure it's not null
     */
    @Test
    public void testGet() throws Exception {
        assertNotNull(geocache.get(id));
    }

    /**
     * get a geocache object from a known code and make sure it's not null
     */
    @Test
    public void testGet1() throws Exception {
        assertNotNull(geocache.get(code));
    }

    /**
     * get a null geocache object from an unknown id and make sure it's null
     */
    @Test
    public void testGet2() throws Exception {
        assertNull(geocache.get(java.util.UUID.fromString("00000000-0000-0000-0000-000000000000")));
    }

    /**
     * get a null geocache object from an unknown code and make sure it's null
     */
    @Test
    public void testGet3() throws Exception {
        assertNull(geocache.get(""));
    }

    /**
     * test of expected insert failure, covers both insert functions
     */
    @Test
    public void testInsert() throws Exception {
        assertFalse(geocache.insert(new blueharvest.geocaching.soap.objects.geocache(
                null, null, code, "", "", 1, 1, 1, 1, 1,
                blueharvest.geocaching.soap.objects.user.get(username, password), null,
                new blueharvest.geocaching.soap.objects.location(
                        null, "", latitude, longitude, 1, null), null)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        geocache.update(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws Exception {
        geocache.delete(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testExists() throws Exception {
        geocache.exists("");
    }

    @Test
    public void testIsFavorite() throws Exception {
        boolean f = geocache.isFavorite(id,
                blueharvest.geocaching.soap.objects.user.get(
                        username, password).getId());
        if (favorite) assertTrue(f);
        if (!favorite) assertFalse(f);
    }

    @Test
    public void testIsFound() throws Exception {
        boolean f = geocache.isFound(id,
                blueharvest.geocaching.soap.objects.user.get(
                        username, password).getId());
        if (found) assertTrue(f);
        if (!found) assertFalse(f);
    }

}