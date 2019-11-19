package com.smalldemo.thirdpart.dubbo.consumer;

import com.smalldemo.thirdpart.dubbo.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jim
 */
public class Consumer {

    public static void main(String[] args) {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-consumer.xml"});
        context.start();
        // get remote service proxy
        DemoService demoService = (DemoService) context.getBean("demoService");

        while (true) {
            try {
                Thread.sleep(1000);
                // call remote method
                String hello = demoService.sayHello("world");
                // get result
                System.out.println(hello);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
