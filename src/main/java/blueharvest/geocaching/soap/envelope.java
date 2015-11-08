package blueharvest.geocaching.soap;

/**
 * @author jmb
 * @since 2015-11-07
 */
public class envelope extends org.ksoap2.serialization.SoapSerializationEnvelope {

    public envelope() {
        super(org.ksoap2.SoapEnvelope.VER11);;
        dotNet = true;
        headerOut = new org.kxml2.kdom.Element[1];
        headerOut[0] = blueharvest.geocaching.soap.header.getElement();
    }
}
