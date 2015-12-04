package blueharvest.geocaching.concepts;

/**
 * @author jmb
 * @since 2015-10-13
 */
public abstract class location {

    private final java.util.UUID id;
    private final String name;
    private final coordinate latitude;
    private final coordinate longitude;
    private final int altitude;
    private final address address;

    /**
     * <h3>location</h3>
     * represents the latitude and longitude coordinates on globe
     *
     * @param id        id
     * @param name      name
     * @param latitude  latitude coordinate
     * @param longitude longitude coordinate
     * @param altitude  altitude
     * @param address   address
     * @see blueharvest.geocaching.concepts.location.coordinate
     */
    public location(java.util.UUID id, String name, double latitude,
                    double longitude, int altitude, address address) {
        this.id = id;
        this.name = name;
        this.latitude = new coordinate(latitude, coordinate.type.latitude);
        this.longitude = new coordinate(longitude, coordinate.type.longitude);
        this.altitude = altitude;
        this.address = address;
    }

    public java.util.UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * <h3>latitude φ coordinate</h3>
     * e.g., 40.7981905
     *
     * @return latitude coordinate; positive (+) is in the northern hemisphere
     * or N, negative (-) is in the southern hemisphere or S
     * @see blueharvest.geocaching.concepts.location.coordinate
     * @since 2015-10-23
     */
    public coordinate getLatitude() {
        return latitude;
    }

    /**
     * <h3>longitude λ coordinate</h3>
     * e.g., -77.8600217
     *
     * @return longitude coordinate; positive (+) is in the eastern hemisphere
     * or E, negative (-) is in the western hemisphere or W
     * @see blueharvest.geocaching.concepts.location.coordinate
     * @since 2015-10-23
     */
    public coordinate getLongitude() {
        return longitude;
    }

    public int getAltitude() {
        return altitude;
    }

    public address getAddress() {
        return address;
    }

    /**
     * <h3>distance from this location to that location</h3>
     *
     * @param that the location to determine distance from this location
     * @return statute miles from this location to that location
     * @see <a href="http://introcs.cs.princeton.edu/java/44st/Location.java.html">
     * Location.java</a>
     */
    public double distanceTo(location that) {
        double knot = 1.5077945;
        double lat1 = java.lang.Math.toRadians(this.latitude.getDecimalDegrees());
        double lat2 = java.lang.Math.toRadians(that.latitude.getDecimalDegrees());
        double lng1 = java.lang.Math.toRadians(this.longitude.getDecimalDegrees());
        double lng2 = java.lang.Math.toRadians(that.longitude.getDecimalDegrees());
        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng1 - lng2));
        // each degree on a great circle of Earth is 60 nautical miles
        double nm = 60 * Math.toDegrees(angle);
        //double mi = knot * nm;
        return knot * nm;
    }

    /**
     * wrapper class for a latitude or longitude coordinate
     */
    public static class coordinate {

        public enum type {latitude, longitude}

        private final double dd; // (d)ecimal (d)egrees
        private final type t; // type (latitude or longitude)
        private final int d; // (d)egree
        private final int m; // (m)inute
        private final double s; // (s)econd

        /**
         * <h3>constructor</h3>
         *
         * @param l coordinate in decimal degrees
         * @param t type of coordinate (latitude φ or longitude λ)
         * @throws java.lang.IllegalArgumentException if the value of <code>l</code>
         *                                            is not within range
         * @see <a href="https://en.wikipedia.org/wiki/Geographic_coordinate_conversion">
         * Geographic Coordinate Conversion</a>
         * @see #isValid(double, blueharvest.geocaching.concepts.location.coordinate.type)
         * @since 2015-10-23
         */
        public coordinate(double l, type t) {
            dd = l;
            this.t = t;
            if (!isValid(l, t)) throw new java.lang.IllegalArgumentException(
                    String.valueOf(l) + " is not within range.");
            d = (int) Math.floor(Math.abs(l));
            m = (int) Math.floor((Math.abs(l) - d) * 60d);
            s = (Math.abs(l) - d - (double) m / 60d) * 3600;
        }

        /**
         * <h3>validates coordinate</h3>
         * latitude φ {-90 < φ < 90}, longitude λ {-180 < λ < 180}
         *
         * @param l latitude φ or longitude λ value
         * @param t type of coordinate (latitude φ or longitude λ)
         * @return false if the coordinate is out of range, otherwise true
         * @since 2015-12-03
         */
        public static boolean isValid(double l, type t) {
            if (t == type.latitude && (-90 > l || l > 90)) return false;
            if (t == type.longitude && (-180 > l || l > 180)) return false;
            return true;
        }

        /**
         * <h3>gets the coordinate in decimal degree format</h3>
         * e.g., 40.7981905 or -77.8600217;
         * <ul>
         * <li>a positive (+) latitude coordinate indicates the northern
         * hemisphere or N</li>
         * <li>a negative (-) latitude coordinate indicates the southern
         * hemisphere or S</li>
         * <li>a positive (+) longitude coordinate indicates the eastern
         * hemisphere or E</li>
         * <li>a negative (-) longitude coordinate indicates the western
         * hemisphere or W</li>
         * </ul>
         *
         * @return coordinate in decimal degrees
         */
        public double getDecimalDegrees() {
            return dd;
        }

        /**
         * <h3>gets the degree ° for sexigesimal coordinate formatting</h3>
         * e.g., <b>40°</b>47'53.5", <b>77°</b>51'36.1" (<b>degrees</b> minutes
         * seconds or dms); to determine hemisphere, see
         * {@link blueharvest.geocaching.concepts.location.coordinate#getDecimalDegrees()}
         * for sign
         *
         * @return degrees ° of the coordinate
         */
        public int getDegree() {
            return d;
        }

        /**
         * <h3>gets the minute ' for coordinate formatting</h3>
         * e.g., 40°<b>47'</b>53.5", 77°<b>51'</b>36.1"(degrees <b>minutes</b>
         * seconds or dms)
         *
         * @return minutes ' of the coordinate
         */
        public int getMinute() {
            return m;
        }

        /**
         * <h3>gets the second " for coordinate formatting</h3>
         * e.g., 40°47'<b>53.5"</b>, 77°51'<b>36.1"</b> (degrees minutes
         * <b>seconds</b> or dms)
         *
         * @return seconds " of the coordinate
         */
        public double getSecond() {
            return s;
        }

        /**
         * <h3>degree minute second representation</h3>
         *
         * @return string representation of the coordinate
         * (i.e., 40° 26′ 46″ N, 79° 58′ 56″ W, or 0° 0′ 0″)
         * @since 2015-11-13 Friday the 13th and <a href="http://www.oddday.net/">Odd Day</a>
         * (last one of this century)
         */
        public String toSexigesimal() {
            String r = String.valueOf(d) + "\u00b0 "
                    + String.valueOf(m) + "\' "
                    + String.valueOf(new java.text.DecimalFormat("#.###").format(s)) + "\"";
            if (dd != 0) {
                if (t == coordinate.type.latitude) r += (dd > 0) ? " N" : " S";
                if (t == coordinate.type.longitude) r += (dd > 0) ? " E" : " W";
            }
            return r;
        }

    }

}
