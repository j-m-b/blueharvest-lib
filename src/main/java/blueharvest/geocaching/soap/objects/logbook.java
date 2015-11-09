package blueharvest.geocaching.soap.objects;

/**
 * @author jmb
 * @since 2015-11-08
 */
public class logbook extends blueharvest.geocaching.concepts.logbook {

    private final static String url = "https://blueharvestgeo.com/WebServices/LogbookService.asmx";

    private String request;
    private String response;

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
        this.request = "";
        this.response = "";
    }

    public void setRequest(String value) {
        request = value;
    }

    public void setResponse(String value) {
        response = value;
    }

    public String getResponse() {
        return response;
    }

    public String getRequest() {
        return request;
    }

    public static logbook get(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
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
        org.ksoap2.serialization.SoapObject request
                = new blueharvest.geocaching.soap.request("InsertLogbook");
        final String ns = "http://blueharvestgeo.com/webservices/";
        org.ksoap2.serialization.SoapObject image
                = new org.ksoap2.serialization.SoapObject(ns, "i");
        request.addSoapObject(image);

        org.ksoap2.serialization.SoapSerializationEnvelope envelope
                = new blueharvest.geocaching.soap.envelope();
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false); // prefixing
        envelope.setOutputSoapObject(request);
        org.ksoap2.transport.HttpTransportSE transport
                = new org.ksoap2.transport.HttpTransportSE(url); // ?wsdl
        transport.debug = true; // testing
        try {
            transport.call("http://blueharvestgeo.com/webservices/InsertLogbook", envelope);
            org.ksoap2.serialization.SoapPrimitive response
                    = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
            //l.setRequest(transport.requestDump); // testing
            //l.setResponse(transport.responseDump); // testing
            return Boolean.parseBoolean(response.toString());
        } catch (org.ksoap2.SoapFault ex) {
            throw new RuntimeException(
                    "soap fault:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (org.ksoap2.transport.HttpResponseException ex) {
            throw new RuntimeException(
                    "http response exception:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (java.io.IOException ex) {
            throw new RuntimeException(
                    "io exception:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (org.xmlpull.v1.XmlPullParserException ex) {
            throw new RuntimeException(
                    "xml pull parser exception:" + ex.getMessage() + " ... " + transport.requestDump);
        } catch (java.lang.Exception ex) {
            throw new RuntimeException(
                    "exception:" + ex.getLocalizedMessage() + " ... " + transport.requestDump);
        }
    }

    /**
     * <h3>updates a logbook</h3>
     *
     * @param l (l)ogbook
     * @return true/false depending on whether the update was successful
     * @throws java.lang.UnsupportedOperationException
     * @throws java.lang.UnsupportedOperationException
     * @since 2015-11-09
     * @deprecated no replacement, there is nothing to update
     */
    public static boolean update(logbook l) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public static boolean delete(java.util.UUID id) {
        throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    /**
     * serialized representation to use in soap
     */
    public static class serialized implements org.ksoap2.serialization.KvmSerializable {

        public java.util.UUID id; // this could be a problem
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

        private String request;
        private String response;

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

        public void setRequest(String value) {
            request = value;
        }

        public void setResponse(String value) {
            response = value;
        }

        public String getRequest() {
            return request;
        }

        public String getResponse() {
            return response;
        }

        public static entry get(java.util.UUID id) {
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <h3>inserts a logbook entry</h3>
         * required: title, text, user.id, and logbookid<br />
         * this may not be necessary ...
         * todo: test with logbook
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
            envelope.implicitTypes = true;
            envelope.setAddAdornments(false); // prefixing
            envelope.setOutputSoapObject(request);
            org.ksoap2.transport.HttpTransportSE transport
                    = new org.ksoap2.transport.HttpTransportSE(url);
            transport.debug = true; // testing
            try {
                transport.call("http://blueharvestgeo.com/webservices/InsertLogbookEntry", envelope);
                org.ksoap2.serialization.SoapPrimitive response
                        = (org.ksoap2.serialization.SoapPrimitive) envelope.getResponse();
                e.setRequest(transport.requestDump); // testing
                e.setResponse(transport.responseDump); // testing
                return Boolean.parseBoolean(response.toString());
            } catch (org.ksoap2.SoapFault ex) {
                throw new RuntimeException(
                        "soap fault:" + ex.getMessage() + " ... " + transport.requestDump);
            } catch (org.ksoap2.transport.HttpResponseException ex) {
                throw new RuntimeException(
                        "http response exception:" + ex.getMessage() + " ... " + transport.requestDump);
            } catch (java.io.IOException ex) {
                throw new RuntimeException(
                        "io exception:" + ex.getMessage() + " ... " + transport.requestDump);
            } catch (org.xmlpull.v1.XmlPullParserException ex) {
                throw new RuntimeException(
                        "xml pull parser exception:" + ex.getMessage() + " ... " + transport.requestDump);
            } catch (java.lang.Exception ex) {
                throw new RuntimeException(
                        "exception:" + ex.getLocalizedMessage() + " ... " + transport.requestDump);
            }
        }

        public static boolean update(entry e) {
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }

        public static boolean delete(java.util.UUID id) {
            throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }

        /**
         * serialized representation to use in soap
         */
        public static class serialized implements org.ksoap2.serialization.KvmSerializable {

            public java.util.UUID id; // this could be a problem
            public java.util.Date date; // this could be a problem, too
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

}
