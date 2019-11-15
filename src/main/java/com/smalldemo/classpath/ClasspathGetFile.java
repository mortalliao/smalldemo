package com.smalldemo.classpath;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author Jim
 * <p>
 * Class对象还有getResource() 的方法去获取文件资源，使用规则和上面的一样。
 * <p>
 * maven项目还要注意一点，maven 的compiler插件在编译时是不会将package下的文本文件给编译到target下的，
 * 下图是我在用mybatis框架的时候将xml的mapper给放到package编译后的效果：
 */
public class ClasspathGetFile {

    public static void main(String[] args) throws IOException {
//        ClasspathGetFile classpathGetFile = new ClasspathGetFile();
//        classpathGetFile.getFile("classpathGetFileTest.txt");
        // 注意要加'/'
        Properties properties = loadProperties(getInputStreamStatic("/classpathGetFileTest.properties"));
        System.out.println(properties.getProperty("classpathGetFile"));
    }

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        // getResource()方法会去classpath下找这个文件，获取到url resource
        URL url = classLoader.getResource(fileName);
        // 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
        assert url != null;
        System.out.println(url.getFile());
        File file = new File(url.getFile());
        System.out.println(file.exists());
        return file;
    }

    private static File getFileStatic(String fileName) {
        URL url = ClasspathGetFile.class.getClassLoader().getResource("classpathGetFileTest.txt");
        File file = new File(url.getFile());
        return file;
    }

    private InputStream getInputStream(String fileName) {
        InputStream in = null;
        //also can be this way:
        try {
            Class.forName(ClasspathGetFile.class.getName()).getResourceAsStream(fileName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        input = this.getClass().getResourceAsStream("resources/dbconfig.properties");    // 对应package下的文件
//        input = this.getClass().getResourceAsStream("/dbconfig.properties");    // 对应resources下的文件
        return in;
    }

    private static InputStream getInputStreamStatic(String fileName) {
        InputStream in = ClasspathGetFile.class.getResourceAsStream(fileName);
        return in;
    }

    private static Properties loadProperties(InputStream input) throws IOException {
        Properties properties = new Properties();
        properties.load(input);
        return properties;
    }
}
