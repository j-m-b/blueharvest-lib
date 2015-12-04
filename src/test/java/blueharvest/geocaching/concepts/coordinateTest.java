package blueharvest.geocaching.concepts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * test the coordinate class
 *
 * @author jmb
 * @since 2015-12-03
 */
public class coordinateTest {

    /* each array contains values for testing, do not modify */
    private static final double[] latitudes = {-90.0000001, -90, 0, 90, 90.0000001};
    private static final double[] longitudes = {-180.0000001, -180, 0, 180, 180.0000001};

    /** latitude out of bounds test */
    @Test(expected = IllegalArgumentException.class)
    public void newLatitudeTest1() {
        new location.coordinate(latitudes[0], location.coordinate.type.latitude);
    }

    /** latitude out of bounds test */
    @Test(expected = IllegalArgumentException.class)
    public void newLatitudeTest2() {
        new location.coordinate(latitudes[4], location.coordinate.type.latitude);
    }

    /** latitude in range test */
    @Test
    public void newLatitudeTest3() {
        new location.coordinate(latitudes[1], location.coordinate.type.latitude);
        new location.coordinate(latitudes[2], location.coordinate.type.latitude);
        new location.coordinate(latitudes[3], location.coordinate.type.latitude);
    }

    /** longitude out of bounds test */
    @Test(expected = IllegalArgumentException.class)
    public void newLongitudeTest1() {
        new location.coordinate(longitudes[0], location.coordinate.type.longitude);
    }

    /** longitude out of bounds test */
    @Test(expected = IllegalArgumentException.class)
    public void newLongitudeTest2() {
        new location.coordinate(longitudes[4], location.coordinate.type.longitude);
    }

    /** longitude in range test */
    @Test
    public void newLongitudeTest3() {
        new location.coordinate(longitudes[1], location.coordinate.type.longitude);
        new location.coordinate(longitudes[2], location.coordinate.type.longitude);
        new location.coordinate(longitudes[3], location.coordinate.type.longitude);
    }

    @Before
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
        assertTrue(new location.coordinate(latitudes[1], location.coordinate.type.latitude)
                .toSexigesimal().contains("S"));
        assertTrue(new location.coordinate(latitudes[3], location.coordinate.type.latitude)
                .toSexigesimal().contains("N"));
        assertTrue(new location.coordinate(longitudes[1], location.coordinate.type.longitude)
                .toSexigesimal().contains("W"));
        assertTrue(new location.coordinate(longitudes[3], location.coordinate.type.longitude)
                .toSexigesimal().contains("E"));
    }

}