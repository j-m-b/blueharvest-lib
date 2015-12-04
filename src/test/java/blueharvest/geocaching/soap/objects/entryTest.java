package blueharvest.geocaching.soap.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jmb
 * @since 2015-12-04
 */
public class entryTest {

    private static final java.util.UUID id
            = java.util.UUID.fromString("3A702A3D-367D-40F0-976F-65D3479786BA");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * <h3>get an entry from a logbook</h3>
     *
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        assertNotNull(logbook.entry.get(id));
    }

    /**
     * <h3>do not get an entry from a logbook</h3>
     *
     * @throws Exception
     */
    @Test
    public void testGet1() throws Exception {
        assertNull(logbook.get(java.util.UUID.fromString("00000000-0000-0000-0000-000000000000")));
    }

    /**
     * <h3>expected insert fail</h3>
     *
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testInsert() throws Exception {
        logbook.entry.insert(null, null);
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

}