package blueharvest.geocaching.soap.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jmb
 * @since 2015-12-04
 */
public class userTest {

    private final static String username = "username";
    private final static String password = "password";
    private final static String email = "email@email.com";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * <h3>get a user</h3>
     * In order to get a user, the username and password must authenticate.
     * This protects the user from outside access and the ball is in their
     * court. Additionally, the user must be active and not locked.
     *
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        assertNotNull(user.get(username, password));
    }

    /**
     * <h3>do not get a user</h3>
     *
     * @throws Exception
     */
    @Test
    public void testGet2() throws Exception {
        assertNull(user.get("", ""));
    }

    /**
     * <h3>test a duplicate user cannot be inserted</h3>
     * There are two manners of inserting a new user.
     * One way only requires four parameters and specifies the
     * username, password, email, and role name.
     * All users are inserted in the "Basic" role until
     * a manner is in place to prevent anonymous users creating
     * Admin accts.
     */
    @Test
    public void testInsert() throws Exception {
        assertFalse(user.insert(username, password, email, ""));
    }

    /**
     * <h3>test a duplicate user cannot be inserted</h3>
     * The second way is a little more complicated and requires
     * only one parameter; however, the parameter is an
     * instantiation of a user object with username, password, email,
     * and role.name specified. Either should work.
     */
    @Test
    public void testInsert1() throws Exception {
        assertFalse(blueharvest.geocaching.soap.objects.user.insert(
                new blueharvest.geocaching.soap.objects.user(
                        null, null, username, username, null,
                        email, true, false, null, null,
                        new blueharvest.geocaching.soap.objects.role(
                                null, "Basic"))));
    }

    /**
     * <h3>update a user</h3>
     * a user is only updatable if the username and password
     * authenticate; furthermore, the user must be active and not locked *prior to update*
     * and this function will fail if the user is specified as locked or inactive;
     * this is useful for locking out or setting users; we're not changing any values here,
     * just making sure we can
     *
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception {
        assertTrue(user.update(user.get(username, password)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws Exception {
        user.delete(null);
    }

    /**
     * <h3>auth a user</h3>
     * successful result (correct credentials, active, not locked)
     *
     * @throws Exception
     */
    @Test
    public void testAuth() throws Exception {
        assertTrue(user.auth(username, password));
    }

    /**
     * <h3>auth a user</h3>
     * failure (wrong credentials, inactive, locked)
     * any combination will do; we don't care which is wrong and wouldn't tell
     * the user anyway.
     *
     * @throws Exception
     */
    @Test
    public void testAuth1() throws Exception {
        assertFalse(user.auth("", ""));
    }

    @Test
    public void testExists() throws Exception {
        assertTrue(user.exists(username));
    }

    @Test
    public void testExists1() throws Exception {
        assertFalse(user.exists(null));
    }

    /**
     * todo
     */
    @Test
    public void testRelateFoundGeocache() throws Exception {
        //user.relateFoundGeocache(u.getId(), g.getId(), true/false);
    }

    /**
     * todo
     */
    @Test
    public void testRelateFavoriteGeocache() throws Exception {
        //user.relateFavoriteGeocache(u.getId(), g.getId(), true/false);
    }
}