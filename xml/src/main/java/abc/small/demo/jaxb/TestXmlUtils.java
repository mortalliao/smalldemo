package com.smalldemo.jaxb;

import com.smalldemo.jaxb.example1.Customer;
import org.junit.Test;

/**
 * @author Jim
 */
public class TestXmlUtils {

    @Test
    public void test1() {
        Customer customer = new Customer();
        customer.setId(100);
        customer.setName("this is username!");
        customer.setAge(29);

        System.out.println(XmlUtils.serialize(customer));
    }

    @Test
    public void test2() {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<customer id=\"100\">\n" +
                "    <age>29</age>\n" +
                "    <userName>this is username!</userName>\n" +
                "</customer>\n";
        System.out.println(XmlUtils.deserialize(xmlStr, Customer.class));
    }
}
