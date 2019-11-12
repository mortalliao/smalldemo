package com.smalldemo.jaxb.example2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jim
 */
public class JaxbTest {

    private final static String XML_CODE = "UTF-8";

    public static void main(String[] args) throws JAXBException {

        Teacher teacher = new Teacher(33, "teacherA");
        List<Student> students = new ArrayList<Student>();

        for (int i = 1; i < 3; i++) {
            Student student = new Student(i, i, "sName_" + i);
            students.add(student);
        }

        teacher.setStudents(students);

        //存到字符串中并打印出来
        System.out.println(objectToXmlStr(teacher));

        //写入到文件中
        objectToXmlToFile(teacher, "d:\\teacher.xml");

        //打印到控制台
        objectToXmlToConsle(teacher);

    }

    /**
     * 打印xml到控制台
     *
     * @param object object
     * @throws JAXBException JAXBException
     */
    public static void objectToXmlToConsle(Object object) throws JAXBException {
        if (object == null) {
            return;
        }

        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller jaxbMarshaller = context.createMarshaller();

        //是否格式化输出xml文件
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //设置编码
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, XML_CODE);

        //控制台打印输出
        jaxbMarshaller.marshal(object, System.out);

    }

    /**
     * xml已写入到文件
     *
     * @param object   object
     * @param filePath filePath
     * @throws JAXBException JAXBException
     */
    public static void objectToXmlToFile(Object object, String filePath) throws JAXBException {
        if (object == null || filePath == null || "".equals(filePath)) {
            return;
        }
        File file = new File(filePath);

        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller jaxbMarshaller = context.createMarshaller();

        //是否格式化输出xml文件
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //设置编码
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, XML_CODE);

        //写入到文件中
        jaxbMarshaller.marshal(object, file);
    }

    /**
     * 把对象转换为xml字符串
     *
     * @param object object
     * @return String
     * @throws JAXBException JAXBException
     */
    public static String objectToXmlStr(Object object) throws JAXBException {
        if (object == null) {
            return "";
        }
        OutputStream out = new ByteArrayOutputStream();

        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller jaxbMarshaller = context.createMarshaller();

        //是否格式化输出xml文件
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //设置编码
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, XML_CODE);

        //打印到输出流中
        jaxbMarshaller.marshal(object, out);

        return out.toString();
    }

}
