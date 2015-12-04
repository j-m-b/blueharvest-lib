package blueharvest.geocaching.soap.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jmb
 * @since 2015-12-04
 */
public class imageTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * todo
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testGet() throws Exception {
        image.get(null);
    }

    /**
     * insert an image test
     * p.s. There is an AWS S3 bucket setup for our images ... The AWS SDK code has been
     * written to upload a file and permissions have been set up to restrict the upload
     * using an IAM account. Only jpgs and pngs are permitted and the files are
     * publicly available immediately afterwards. As for now, we must manually
     * upload the images to the bucket and make sure it is public for use in the app.
     * <filename>.[jpg|png]
     *
     * @throws Exception
     */
    @Test(expected = NullPointerException.class)
    public void testInsert() throws Exception {
        image.insert(new image(null, null, null, null));
        // java.net.URI.create("https://s3-us-west-2.amazonaws.com/blueharvest/images/pluto.png")
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdate() throws Exception {
        image.update(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() throws Exception {
        image.delete(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpload() throws Exception {
        image.upload(null);
    }

}