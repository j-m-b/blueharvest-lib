package blueharvest.geocaching.resources;

/**
 * !warning! not for use in an Android environment
 * @author jmb
 */
public class properties extends java.util.Properties {

    public properties() {
        try { // use properties file for credentials
            load(this.getClass().getResourceAsStream(
                    "/blueharvest/geocaching/resources/config.properties"));
        } catch (java.io.IOException ex) {
            // subclass exception java.io.FileNotFoundException
            java.util.logging.Logger.getLogger(
                    this.getClass().getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
