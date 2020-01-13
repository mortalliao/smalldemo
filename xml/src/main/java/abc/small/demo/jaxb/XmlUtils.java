package abc.small.demo.jaxb;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;

/**
 * @author Jim
 */
@Slf4j
public class XmlUtils {

    public static String serialize(Object obj) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(obj, outputStream);
        } catch (JAXBException e) {
            log.error("toXmlString error:", e);
        }
        return outputStream.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String xml, Class<T> clazz) {
        T obj = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader stringReader = new StringReader(xml);
            obj = (T) unmarshaller.unmarshal(stringReader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
