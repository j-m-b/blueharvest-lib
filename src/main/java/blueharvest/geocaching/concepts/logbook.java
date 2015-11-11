package blueharvest.geocaching.concepts;

/**
 * @author jmb
 * @since 2015-10-13
 */
public abstract class logbook {

    private final java.util.UUID id;
    private final java.util.Date date;
    java.util.ArrayList<entry> entries = new java.util.ArrayList<>();

    /**
     * <h3>constructor</h3>
     *
     * @param id      id
     * @param date    create date
     * @param entries entries in the logbook
     * @see blueharvest.geocaching.concepts.logbook.entry
     * @since 2015-10
     */
    public logbook(java.util.UUID id, java.util.Date date, java.util.ArrayList<entry> entries) {
        this.id = id;
        this.date = date;
        this.entries = entries;
    }

    public java.util.UUID getId() {
        return id;
    }

    public java.util.Date getDate() {
        return date;
    }

    public java.util.ArrayList<entry> getEntries() {
        return entries;
    }

    /**
     * @author jmb
     * @since 2015-10-13
     */
    public static class entry {

        private final java.util.UUID id;
        private final java.util.Date date;
        private final String title;
        private final String text;
        private final user author;

        /**
         * <h3>constructor</h3>
         *
         * @param id     id
         * @param date   create/entry date
         * @param title  title
         * @param text   text
         * @param author author of the logbook entry
         * @see blueharvest.geocaching.concepts.user
         * @since 2015-11
         */
        public entry(java.util.UUID id, java.util.Date date, String title,
                     String text, user author) {
            this.id = id;
            this.date = date;
            this.title = title;
            this.text = text;
            this.author = author;
        }

        public java.util.UUID getId() {
            return id;
        }

        public java.util.Date getDate() {
            return date;
        }

        public String getTitle() {
            return title;
        }

        public String getText() {
            return text;
        }

        public blueharvest.geocaching.concepts.user getAuthor() {
            return author;
        }

    }

}
