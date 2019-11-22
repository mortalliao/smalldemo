package com.smalldemo.proxy;

import java.lang.reflect.Proxy;

/**
 * @author Jim
 */
public class JavaDynamicProxy {

    public static void main(String[] arg) {
        JavaDeveloper zack = new JavaDeveloper("zack");

        Developer zackProxy = (Developer) Proxy.newProxyInstance(zack.getClass().getClassLoader(),
                zack.getClass().getInterfaces(), (proxy, method, args) -> {
                    if (method.getName().equals("code")) {
                        System.out.println("Zack is praying for the code");
                        return method.invoke(zack, args);
                    }
                    if (method.getName().equals("debug")) {
                        System.out.println("Zack's have no bug! No need to debug!");
                    }
                    return null;
                });

        zackProxy.code();
        zackProxy.debug();
    }
}
