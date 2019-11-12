package com.smalldemo.jaxb.example1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author Jim
 */
public class JAXBExample {

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("this is username!");
        customer.setAge(29);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            // 是否格式化输出xml文件
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //写到文件中
            File file = getClasspathFile("com/smalldemo/jaxb/example1/file.xml");
            jaxbMarshaller.marshal(customer, file);

            //写到控制台
            jaxbMarshaller.marshal(customer, System.out);

            //保存到字符串中
            OutputStream out = new ByteArrayOutputStream();
            jaxbMarshaller.marshal(customer, out);
            String xml = out.toString();
            System.out.println(xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private static File getClasspathFile(String filename) {
        ClassLoader classLoader = JAXBExample.class.getClassLoader();
        URL url = classLoader.getResource(filename);
        assert url != null;
        return new File(url.getFile());
    }
}
