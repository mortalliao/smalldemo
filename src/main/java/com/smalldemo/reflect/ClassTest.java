package com.smalldemo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jim
 */
public class ClassTest {

    public static void main(String[] args) throws Exception {
//        reflectNewInstance();
//        reflectPrivateConstructor();
//        reflectPrivateField();
        reflectPrivateMethod();
    }

    /**
     * 创建对象
     */
    public static void reflectNewInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.smalldemo.reflect.ReflectTest");
        Object obj = aClass.newInstance();
        ReflectTest reflectTest = (ReflectTest) obj;
        reflectTest.callTest("Hello Ha");
    }

    /**
     * 反射私有的构造方法
     */
    public static void reflectPrivateConstructor() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = Class.forName("com.smalldemo.reflect.ReflectTest");
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, Integer.class);
        declaredConstructor.setAccessible(true);
        Object instance = declaredConstructor.newInstance("Hello again", 100);
        ReflectTest reflectTest = (ReflectTest) instance;
        reflectTest.callTest("This is reflect test");
    }

    /**
     * 反射私有属性
     */
    public static void reflectPrivateField() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> aClass = Class.forName("com.smalldemo.reflect.ReflectTest");
        Object obj = aClass.newInstance();
        ReflectTest reflectTest = (ReflectTest) obj;
        ((ReflectTest) obj).setName("My name is Jim");
        Field field = aClass.getDeclaredField("name");
        field.setAccessible(true);
        String name = (String) field.get(obj);
        System.out.println(name);
    }

    /**
     * 反射私有方法
     */
    public static void reflectPrivateMethod() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> aClass = Class.forName("com.smalldemo.reflect.ReflectTest");
        Method method = aClass.getDeclaredMethod("callTest", String.class);
        method.setAccessible(true);
        // invoke method
        Object obj = aClass.newInstance();
        String returnFromMethod = (String) method.invoke(obj, "easy, right?");
        System.out.println(returnFromMethod);
        // get annotation
        TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
    }

}
