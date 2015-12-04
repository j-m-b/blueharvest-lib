package blueharvest.geocaching.concepts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test the coordinate class
 * //@Test(expected = IllegalArgumentException.class)
 *
 * @author jmb
 * @since 2015-12-03
 */
public class coordinateTest {

    private static double[] latitudes = {-90.0000001, -90, 0, 90, 90.0000001};
    private static double[] longitudes = {-180.0000001, -180, 0, 180, 180.0000001};

    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsValid() throws Exception {
        assertFalse(location.coordinate.isValid(latitudes[0], location.coordinate.type.latitude));
        assertTrue(location.coordinate.isValid(latitudes[1], location.coordinate.type.latitude));
        assertTrue(location.coordinate.isValid(latitudes[2], location.coordinate.type.latitude));
        assertTrue(location.coordinate.isValid(latitudes[3], location.coordinate.type.latitude));
        assertFalse(location.coordinate.isValid(latitudes[4], location.coordinate.type.latitude));
        /* loop testing
        for (double d = -90; d <= 90; d += 9.13) {
            assertTrue(location.coordinate.isValid(d, location.coordinate.type.latitude));
        }*/
        assertFalse(location.coordinate.isValid(longitudes[0], location.coordinate.type.longitude));
        assertTrue(location.coordinate.isValid(longitudes[1], location.coordinate.type.longitude));
        assertTrue(location.coordinate.isValid(longitudes[2], location.coordinate.type.longitude));
        assertTrue(location.coordinate.isValid(longitudes[3], location.coordinate.type.longitude));
        assertFalse(location.coordinate.isValid(longitudes[4], location.coordinate.type.longitude));
        /* loop testing
        for (double d = -90; d <= 90; d += 9.13) {
            assertTrue(location.coordinate.isValid(d, location.coordinate.type.longitude));
        }*/
    }

    @Test
    public void testToSexigesimal() throws Exception {

    }
}