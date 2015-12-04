package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class logbook extends blueharvest.geocaching.concepts.logbook {

    private final static String url = "https://blueharvestgeo.com/WebServices/LogbookService.asmx";

    /**
     * <h3>constructor</h3>
     *
     * @param id      id
     * @param date    create date
     * @param entries entries in the logbook
     * @see blueharvest.geocaching.concepts.logbook.entry
     * @since 2015-11-09
     */
    public logbook(java.util.UUID id, java.util.Date date,
                   java.util.ArrayList<blueharvest.geocaching.concepts.logbook.entry> entries) {
        super(id, date, entries);
    }

    /**
     * <h3>gets a logbook</h3>
     *
     * @param id logbook identifier
     * @return a logbook with entries if there are any or null if none exists
     * @see <a href="https://blueharvestgeo.com/WebServices/LogbookService.asmx?op=GetLogbookWithEntries">
     * GetLogbookWithEntries</a>
     * @since 2015-11-11 Veteran's Day
     */
    public static logbook get(java.util.UUID id) {
        logbook l = null;
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("GetLogbookWithEntries");
        // parameters
        request.addProperty("id", id.toString());
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        //transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/GetLogbookWithEntries", envelope);
            org.ksoap2.serialization.SoapObject response
                    = (org.ksoap2.serialization.SoapObject) envelope.getResponse();
            //System.out.println(transport.requestDump); // testing
            //System.out.println(transport.responseDump); // testing
            if (response != null
                    && !response.getProperty("id").toString().equals(
                    "00000000-0000-0000-0000-000000000000")) {
                l = new blueharvest.geocaching.soap.objects.logbook(
                        java.util.UUID.fromString(response.getProperty("id").toString()),
                        new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                                java.util.Locale.US).parse(
                                response.getProperty("datetime").toString()),
                        new entries());
                org.ksoap2.serialization.SoapObject child = null;
                int n = 0; // number of entries
                try { // ksoap throws an exception when the element is absent
                    child = (org.ksoap2.serialization.SoapObject) response.getProperty("entries");
                    n = child.getPropertyCount();
                } catch (java.lang.Exception ex) {
                    if (!ex.getMessage().contains("illegal property: entries"))
                        ex.printStackTrace();
                }
                for (int i = 0; i < n; i++) {
                    l.getEntries().add(new blueharvest.geocaching.soap.objects.logbook.entry(
                            java.util.UUID.fromString(
                                    ((org.ksoap2.serialization.SoapObject) child.getProperty(i))
                                            .getProperty("id").toString()),
                            new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                                    java.util.Locale.US).parse(
                                    ((org.ksoap2.serialization.SoapObject) child.getProperty(i))
                                            .getProperty("datetime").toString()),
                            ((org.ksoap2.serialization.SoapObject) child.getProperty(i))
                                    .getProperty("title").toString(),
                            ((org.ksoap2.serialization.SoapObject) child.getProperty(i))
                                    .getProperty("text").toString(),
                            new blueharvest.geocaching.soap.objects.user(null, null,
                                    ((org.ksoap2.serialization.SoapObject) child.getProperty(i))
                                            .getProperty("username").toString(),
                                    null, null, null, false, false, null, null, null)));
                }
            }
            // ((org.ksoap2.serialization.SoapObject) child.getProperty(i))
            // .getProperty("url").toString(); // todo: either use or refactor
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException | java.text.ParseException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return l;
    }

    /**
     * <h3>inserts a logbook</h3>
     * id and date default
     *
     * @return true/false depending on whether the logbook was inserted
     * @see <a href="https://blueharvestgeo.com/WebServices/LogbookService.asmx?op=InsertLogbook">
     * InsertLogbook</a>
     * @since 2015-11-09
     * @deprecated no replacement, a logbook is inserted with a geocache
     */
    public static boolean insert() {
        throw new java.lang.UnsupportedOperationException("No longer supported");
        /*org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("InsertLogbook");
        final String ns = "http://blueharvestgeo.com/webservices/";
        org.ksoap2.serialization.SoapObject image
                = new org.ksoap2.serialization.SoapObject(ns, "i");
        request.addSoapObject(image);
        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url);
        //transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/InsertLogbook", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            //l.setRequest(transport.requestDump); // testing
            //l.setResponse(transport.responseDump); // testing
            return response != null && Boolean.parseBoolean(response.toString());
        } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(ex.getMessage());
        } // org.ksoap2.SoapFault, org.ksoap2.transport.HttpResponseException, java.lang.Exception*/
    }

    /**
     * <h3>updates a logbook</h3>
     * todo: implementation
     *
     * @param l (l)ogbook
     * @return true/false depending on whether the update was successful
     * @throws java.lang.UnsupportedOperationException not implemented
     * @since 2015-11-09
     * @deprecated no replacement, there is nothing to update
     */
    @SuppressWarnings("UnusedParameters")
    public static boolean update(logbook l) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * <h3>deletes a logbook</h3>
     * todo: implementation (complex cascading deletes, consider a placeholder logbook)
     *
     * @param id id
     * @return true/false depending on whether the delete was successful
     * @throws java.lang.UnsupportedOperationException not implemented
     * @since 2015-11
     */
    @SuppressWarnings("UnusedParameters")
    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * <h3>serialized representation to use in soap</h3>
     *
     * @since 2015-11
     */
    public static class serialized implements org.ksoap2.serialization.KvmSerializable {

        public java.util.UUID id;
        public String name;

        public serialized() {
        }

        @Override
        public Object getProperty(int i) {
            switch (i) {
                case 0:
                    return id;
                case 1:
                    return name;
            }
            return null;
        }

        @Override
        public int getPropertyCount() {
            return 2;
        }

        @Override
        public void setProperty(int i, Object o) {
            switch (i) {
                case 0:
                    id = java.util.UUID.fromString(o.toString());
                    break;
                case 1:
                    name = o.toString();
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
                    propertyInfo.name = "name";
                    break;
            }
        }

    }

    public static class entry extends blueharvest.geocaching.concepts.logbook.entry {

        /**
         * <h3>constructor</h3>
         *
         * @param id     id
         * @param date   create/entry date
         * @param title  title
         * @param text   text
         * @param author author of the logbook entry
         * @see blueharvest.geocaching.concepts.user
         * @since 2015-11-09
         */
        public entry(java.util.UUID id, java.util.Date date, String title, String text,
                     blueharvest.geocaching.concepts.user author) {
            super(id, date, title, text, author);
        }

        /**
         * <h3>gets an instance of a logbook entry</h3>
         *
         * @param id id of an entry
         * @return an entry in a logbook or null
         * @see <a href="https://blueharvestgeo.com/WebServices/LogbookService.asmx?op=GetLogbookEntry">
         * GetLogbookEntry</a>
         * @since 0.0.6, 2015-11-20
         */
        public static entry get(java.util.UUID id) {
            entry e = null;
            org.ksoap2.serialization.SoapObject request
                    = new blueharvest.geocaching.soap.request("GetLogbookEntry");
            // parameters
            request.addProperty("id", id.toString());
            org.ksoap2.serialization.SoapSerializationEnvelope envelope
                    = new blueharvest.geocaching.soap.envelope();
            envelope.setOutputSoapObject(request);
            org.ksoap2.transport.HttpTransportSE transport
                    = new org.ksoap2.transport.HttpTransportSE(url);
            //transport.debug = true; // testing
            try {
                transport.call("http://blueharvestgeo.com/webservices/GetLogbookEntry", envelope);
                org.ksoap2.serialization.SoapObject response
                        = (org.ksoap2.serialization.SoapObject) envelope.getResponse();
                //System.out.println(transport.requestDump); // testing
                //System.out.println(transport.responseDump); // testing
                if (response != null
                        && !response.getProperty("id").toString().equals(
                        "00000000-0000-0000-0000-000000000000")) {
                    e = new blueharvest.geocaching.soap.objects.logbook.entry(
                            java.util.UUID.fromString(response.getProperty("id").toString()),
                            new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                                    java.util.Locale.US).parse(
                                    response.getProperty("datetime").toString()),
                            response.getProperty("title").toString(),
                            response.getProperty("text").toString(),
                            new blueharvest.geocaching.soap.objects.user(null, null,
                                    response.getProperty("username").toString(),
                                    null, null, null, false, false, null, null, null));
                }
            } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException | java.text.ParseException ex) {
                throw new RuntimeException(ex.getMessage());
            }
            return e;
        }

        /**
         * <h3>inserts a logbook entry</h3>
         * required: title, text, user.id, and logbookid
         *
         * @param e         (e)ntry
         * @param logbookid id og the logbook for which this belongs to
         * @return true/false depending on success of insert
         * @see <a href="https://blueharvestgeo.com/WebServices/LogbookService.asmx?op=InsertLogbookEntry">
         * InsertLogbookEntry</a>
         * @since 2015-11-09
         */
        public static boolean insert(entry e, java.util.UUID logbookid) {
            org.ksoap2.serialization.SoapObject request
                    = new blueharvest.geocaching.soap.request("InsertLogbookEntry");
            final String ns = "http://blueharvestgeo.com/webservices/";
            org.ksoap2.serialization.SoapObject entry
                    = new org.ksoap2.serialization.SoapObject(ns, "entry");
            entry.addProperty("title", e.getTitle());
            entry.addProperty("text", e.getText());
            request.addSoapObject(entry);
            request.addProperty("userid", e.getAuthor().getId().toString());
            request.addProperty("logbookid", logbookid.toString());
            org.ksoap2.serialization.SoapSerializationEnvelope envelope
                    = new blueharvest.geocaching.soap.envelope();
            envelope.setOutputSoapObject(request);
            org.ksoap2.transport.HttpTransportSE transport
                    = new org.ksoap2.transport.HttpTransportSE(url);
            //transport.debug = true; // testing
            try {
                transport.call("http://blueharvestgeo.com/webservices/InsertLogbookEntry", envelope);
                org.ksoap2.serialization.SoapPrimitive response
                        = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
                return response != null && Boolean.parseBoolean(response.toString());
            } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException ex) {
                throw new RuntimeException(ex.getMessage());
            } // java.lang.Exception, org.ksoap2.SoapFault, org.ksoap2.transport.HttpResponseException
        }

        /**
         * <h3>updates an entry</h3>
         * todo: implementation ... this may be useful for typos, etc.
         *
         * @param e (e)ntry
         * @return true/false whether the update was successful
         * @throws java.lang.UnsupportedOperationException - not implemented
         * @since 2015-11
         */
        @SuppressWarnings("UnusedParameters")
        public static boolean update(entry e) {
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <h3>deletes an entry</h3>
         * todo: implementation
         *
         * @param id id
         * @return true/false depending on the success of the delete
         * @throws java.lang.UnsupportedOperationException - not implemented
         * @since 2015-11
         */
        @SuppressWarnings("UnusedParameters")
        public static boolean delete(java.util.UUID id) {
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <h3>serialized representation to use in soap</h3>
         *
         * @since 2015-11
         */
        public static class serialized implements org.ksoap2.serialization.KvmSerializable {

            public java.util.UUID id;
            public java.util.Date date; // needs marshalling
            public String title;
            public String text;
            public blueharvest.geocaching.soap.objects.user.serialized author;

            public serialized() {
            }

            @Override
            public Object getProperty(int i) {
                switch (i) {
                    case 0:
                        return id;
                    case 1:
                        return date;
                    case 2:
                        return title;
                    case 3:
                        return text;
                    case 4:
                        return author;
                }
                return null;
            }

            @Override
            public int getPropertyCount() {
                return 5;
            }

            @Override
            public void setProperty(int i, Object o) {
                switch (i) {
                    case 0:
                        id = java.util.UUID.fromString(o.toString());
                        break;
                    case 1:
                        try {
                            date = new java.text.SimpleDateFormat(
                                    "yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.US).parse(
                                    o.toString());
                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        title = o.toString();
                        break;
                    case 3:
                        text = o.toString();
                        break;
                    case 4:
                        author = (blueharvest.geocaching.soap.objects.user.serialized) o;
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
                        propertyInfo.name = "date";
                        break;
                    case 2:
                        propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                        propertyInfo.name = "title";
                        break;
                    case 3:
                        propertyInfo.type = org.ksoap2.serialization.PropertyInfo.STRING_CLASS;
                        propertyInfo.name = "text";
                        break;
                    case 4:
                        propertyInfo.type = org.ksoap2.serialization.PropertyInfo.OBJECT_CLASS;
                        propertyInfo.name = "author";
                        break;
                }
            }

        }

    }

    /**
     * <h3>entries in a logbook</h3>
     *
     * @see java.util.ArrayList
     * @since 2015-11-20
     */
    public static class entries
            extends java.util.ArrayList<blueharvest.geocaching.concepts.logbook.entry> {

        private entries() {
            super();
        }

        /**
         * constructs an array of all the entries for a given logbook
         *
         * @param logbookid logbook identifier
         * @see <a href="https://blueharvestgeo.com/WebServices/LogbookService.asmx?op=GetLogbookEntries">
         * GetLogbookEntries</a>
         * @since 2015-11-27
         */
        public entries(java.util.UUID logbookid) {
            super();
            org.ksoap2.serialization.SoapObject request
                    = new blueharvest.geocaching.soap.request("GetLogbookEntries");
            // parameters
            request.addProperty("logbookid", logbookid.toString());
            org.ksoap2.serialization.SoapSerializationEnvelope envelope
                    = new blueharvest.geocaching.soap.envelope();
            envelope.setOutputSoapObject(request);
            org.ksoap2.transport.HttpTransportSE transport
                    = new org.ksoap2.transport.HttpTransportSE(url);
            //transport.debug = true; // todo: testing
            try {
                transport.call("http://blueharvestgeo.com/webservices/GetLogbookEntries", envelope);
                org.ksoap2.serialization.SoapObject response
                        = (org.ksoap2.serialization.SoapObject) envelope.getResponse();
                //System.out.println(transport.requestDump); // testing
                //System.out.println(transport.responseDump); // testing
                if (response.getPropertyCount() > 0) {
                    for (int i = 0; i < response.getPropertyCount(); i++) {
                        org.ksoap2.serialization.SoapObject child
                                = (org.ksoap2.serialization.SoapObject) response.getProperty(i);
                        add(new blueharvest.geocaching.soap.objects.logbook.entry(
                                java.util.UUID.fromString(child.getProperty("id").toString()),
                                new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                                        java.util.Locale.US).parse(
                                        child.getProperty("datetime").toString()),
                                child.getProperty("title").toString(),
                                child.getProperty("text").toString(),
                                new blueharvest.geocaching.soap.objects.user(null, null,
                                        child.getProperty("username").toString(),
                                        null, null, null, false, false, null, null, null))); // todo: refactor
                    }
                }
                //child.getProperty("uri").toString(); // todo: either use or refactor
            } catch (java.io.IOException | org.xmlpull.v1.XmlPullParserException | java.text.ParseException ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }

    }

}
