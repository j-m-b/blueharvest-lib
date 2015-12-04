package blueharvest.geocaching.soap.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by JamieMarie on 12/4/2015.
 */
public class logbookTest {

    private static final java.util.UUID id
            = java.util.UUID.fromString("4d08be88-655f-4d6d-a79a-e20565c4c40a");

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * <h3>get a logbook (with entries)</h3>
     *
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        assertNotNull(logbook.get(id));
    }

    /**
     * <h3>insert a logbook</h3>
     * this has been deprecated and throws an exception
     *
     * @throws Exception
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testInsert() throws Exception {
        logbook.insert();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        logbook.update(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws Exception {
        logbook.delete(null);
    }

    @Test
    public void testEntries() throws Exception {
        assertTrue(new logbook.entries(id).size() > 0);
    }


}