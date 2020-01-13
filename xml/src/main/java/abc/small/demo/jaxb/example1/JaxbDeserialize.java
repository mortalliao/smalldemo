package abc.small.demo.jaxb.example1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;

/**
 * @author Jim
 */
public class JaxbDeserialize {

    public static void main(String[] args) throws JAXBException {
        // 1. 通过映射的类创建XMLComtext上下文对象，其中参数为映射的类。
        JAXBContext context = JAXBContext.newInstance(Customer.class);
        // 2. 通过JAXBContext上下文对象创建createUnmarshaller()方法，创建XML转换成JAVA对象的格式。
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // 3. 最后，将XML转换成对映的类，转换后需要强制性转换成映射的类
        Customer customer = (Customer) unmarshaller.unmarshal(new File("D:\\Java\\smalldemo\\target\\classes\\jaxb_test_file.xml"));

        System.out.println(customer);

        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<customer id=\"100\">\n" +
                "    <age>29</age>\n" +
                "    <userName>this is username!</userName>\n" +
                "</customer>\n";
        StringReader stringReader = new StringReader(xmlStr);
        Customer customer1 = (Customer) unmarshaller.unmarshal(stringReader);
        System.out.println(customer1);
    }
}
