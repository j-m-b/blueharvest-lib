package blueharvest.geocaching.soap;

/**
 * @author jmb
 * @since 2015-11-07
 */
public class envelope extends org.ksoap2.serialization.SoapSerializationEnvelope {

    public envelope() {
        super(org.ksoap2.SoapEnvelope.VER11);
        this.dotNet = true;
        this.implicitTypes = true;
        this.setAddAdornments(false); // prefixing
        this.headerOut = new org.kxml2.kdom.Element[1];
        this.headerOut[0] = blueharvest.geocaching.soap.header.getElement();
    }

}
