package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class image extends blueharvest.geocaching.concepts.image {

    private final static String url = "https://blueharvestgeo.com/WebServices/ImageService.asmx";

    /**
     * <h3>constructor</h3>
     *
     * @param id      id of this
     * @param uri     where this is located
     * @param caption the caption for this
     * @param file    the file object of this
     * @see java.util.UUID
     * @see java.net.URI
     * @see java.io.File
     * @since 2015-11-08
     */
    public image(java.util.UUID id, java.net.URI uri, String caption,
                 @SuppressWarnings({"null", "SameParameterValue"}) java.io.File file) {
        super(id, uri, caption, file);
    }

    /**
     * <h3>gets an image</h3>
     *
     * @param id id of the image
     * @return image or null
     * @see <a href="https://blueharvestgeo.com/WebServices/ImageService.asmx?op=GetImage">GetImage</a>
     * @since 2015-11-09
     */
    public static image get(java.util.UUID id) {
        image i = null;
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("GetImage");
        // parameters
        request.addProperty("id", id.toString());
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        try {
            transport.call("http://blueharvestgeo.com/webservices/GetImage", envelope);
            org.ksoap2.serialization.SoapObject response
                    = (org.ksoap2.serialization.SoapObject) envelope.getResponse();
            if (response != null) {
                i = new image(java.util.UUID.fromString(response.getProperty("id").toString()),
                        java.net.URI.create(response.getProperty("uri").toString()),
                        response.getProperty("caption").toString(), null);
            }
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return i;
    }

    /**
     * <h3>inserts an image</h3>
     *
     * @param i (i)mage
     * @return true/false depending on whether the insert was successful
     * @see <a href="https://blueharvestgeo.com/WebServices/ImageService.asmx?op=InsertImage">
     * InsertImage</a>
     * @since 2015-11-09
     */
    public static boolean insert(image i) {
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("InsertImage");
        final String ns = "http://blueharvestgeo.com/webservices/";
        org.ksoap2.serialization.SoapObject image
                = new org.ksoap2.serialization.SoapObject(ns, "i");
        image.addProperty("uri", i.getUri().toString());
        image.addProperty("caption", i.getCaption());
        request.addSoapObject(image);
        // serializable way
        /* blueharvest.geocaching.soap.objects.image.serialized j
                = new blueharvest.geocaching.soap.objects.image.serialized();
        j.uri = i.getUri();
        j.caption = i.getCaption();
        org.ksoap2.serialization.PropertyInfo pi = new org.ksoap2.serialization.PropertyInfo();
        pi.setName("i");
        pi.setValue(i);
        pi.setType("image");
        request.addProperty(pi);*/
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        //transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/InsertImage", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            //System.out.println(transport.requestDump); // testing
            //System.out.println(transport.responseDump); // testing
            return response != null && Boolean.parseBoolean(response.toString());
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        } // org.ksoap2.SoapFault and org.ksoap2.transport.HttpResponseException
    }

    /**
     * <h3>updates an image</h3>
     * todo: implementation (web service ready and available)
     *
     * @param i (i)mage
     * @return true/false depending on whether the image was updated
     * @throws java.lang.UnsupportedOperationException - not implemented
     * @since 2015-11-09
     */
    @SuppressWarnings("UnusedParameters")
    public static boolean update(image i) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * <h3>deletes an image</h3>
     * todo: implementation (stored procedure and web service available)
     *
     * @param id id of the image
     * @return true/false depending on whether the image was inserted
     * @throws java.lang.UnsupportedOperationException - not implemented
     * @since 2015-11-09
     */
    @SuppressWarnings("UnusedParameters")
    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * <h3>uploads an image</h3>
     * uploads an image to Amazon Web Services (AWS) Simple Storage Service (S3)
     * to a specific bucket in the 'images' directory; the filename is identical
     * to that of the file itself; only .jpg and .png permitted as per a policy
     * specified in the AWS Console
     * todo: implement and test
     *
     * @param f (f)ile to be uploaded
     * @return true/false whether the image was uploaded
     * @throws java.lang.UnsupportedOperationException - not implemented
     * @see <a href="http://docs.aws.amazon.com/AmazonS3/latest/dev/UploadObjSingleOpJava.html">
     * Upload an Object Using the AWS SDK for Java</a>
     * @see <a href="http://javatutorial.net/java-s3-example">Java S3 Example</a>
     * @since 2015-11-01
     */
    @SuppressWarnings("UnusedParameters")
    public static boolean upload(java.io.File f) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
        // todo: setup sdk for android
        // http://docs.aws.amazon.com/mobile/sdkforandroid/developerguide/setup.html
        /*
        try {
            java.util.Properties p = new java.util.Properties();
            // replace config.properties
            p.load(new Object().getClass().getResourceAsStream(
                    "/blueharvest/geocaching/resources/config.properties"));
            com.amazonaws.auth.AWSCredentials credentials
                    = new com.amazonaws.auth.BasicAWSCredentials(
                    p.getProperty("awskid"), p.getProperty("awssk")); // todo: fix
            com.amazonaws.services.s3.AmazonS3 s3client
                    = new com.amazonaws.services.s3.AmazonS3Client(credentials);
            s3client.putObject(new com.amazonaws.services.s3.model.PutObjectRequest(
                    p.getProperty("s3bucket"), "images/" + f.getName(), f)
                    .withCannedAcl(com.amazonaws.services.s3.model.CannedAccessControlList.PublicRead));
        } catch (com.amazonaws.services.s3.model.AmazonS3Exception ex) {
            // signature does not match (403), invalid access key (403),
            // access denied (403), no such bucket (404)
            // do something
            return false;
        } catch (com.amazonaws.AmazonClientException ex) { // file path not found
            // do something
            return false;
        } catch (java.io.IOException ex) { // config.properties
            // do something
            return false;
        }
        return true;
        */
    }

    /**
     * serialized representation to use in soap
     */
    public static class serialized implements org.ksoap2.serialization.KvmSerializable {

        public java.util.UUID id; // String?
        public String uri; // public java.net.URI uri;
        public String caption;

        public serialized() {
        }

        @Override
        public Object getProperty(int i) {
            switch (i) {
                case 0:
                    return id;
                case 1:
                    return uri;
                case 2:
                    return caption;
            }
            return null;
        }

        @Override
        public int getPropertyCount() {
            return 3;
        }

        @Override
        public void setProperty(int i, Object o) {
            switch (i) {
                case 0:
                    id = java.util.UUID.fromString(o.toString());
                    break;
                case 1:
                    uri = o.toString(); // java.net.URI.create(o.toString());
                    break;
                case 2:
                    caption = o.toString();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void getPropertyInfo(int i, java.util.Hashtable hashtable,
                                    org.ksoap2.serialization.PropertyInfo propertyInfo) {
            switch (i) {
                case 0:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "id";
                    break;
                case 1:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "uri";
                    break;
                case 2:
                    propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                    propertyInfo.name = "caption";
                    break;
            }
        }

    }

    /**
     * <h3>images</h3>
     *
     * @see java.util.ArrayList
     * @since 2015-11-25
     */
    public static class images extends java.util.ArrayList<image> {

        /**
         * <h3>gets an array list of images for a geocache</h3>
         * todo: implement database, web service and this
         *
         * @param geocacheid geocache identifier
         * @throws java.lang.UnsupportedOperationException not yet supported
         * @see <a href="https://blueharvestgeo.com/WebServices/ImageService.asmx">
         * todo: web service</a>
         * @since 2015-11-25
         */
        public images(java.util.UUID geocacheid) {
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }

    }
}
