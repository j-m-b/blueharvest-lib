package blueharvest.geocaching.soap.objects.marshals;

import java.io.IOException;

import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/**
 * @author Vladimir
 *         Used to marshal Doubles - crucial to serialization for SOAP
 * @see <a href="http://seesharpgears.blogspot.com/2010/11/implementing-ksoap-marshal-interface.html">
 * Implementing KSOAP Marshal Interface</a>
 * @since 2015-11-09
 */
@SuppressWarnings("ALL")
public class MarshalDouble implements Marshal {

    public Object readInstance(XmlPullParser parser, String namespace, String name,
                               PropertyInfo expected) throws IOException, XmlPullParserException {
        return Double.parseDouble(parser.nextText());
    }

    public void register(SoapSerializationEnvelope cm) {
        cm.addMapping(cm.xsd, "double", Double.class, this);
    }

    public void writeInstance(XmlSerializer writer, Object obj) throws IOException {
        writer.text(obj.toString());
    }

}