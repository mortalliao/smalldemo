package com.smalldemo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jim
 * <p>
 * 获得类相关的方法
 * 方法   用途
 * asSubclass(Class<U> clazz)   把传递的类的对象转换成代表其子类的对象
 * Cast     把对象转换成代表类或是接口的对象
 * getClassLoader()     获得类的加载器
 * getClasses()     返回一个数组，数组中包含该类中所有公共类和接口类的对象
 * getDeclaredClasses()     返回一个数组，数组中包含该类中所有类和接口类的对象
 * forName(String className)    根据类名返回类的对象
 * getName()    获得类的完整路径名字
 * newInstance()    创建类的实例
 * getPackage()     获得类的包
 * getSimpleName()      获得类的名字
 * getSuperclass()      获得当前类继承的父类的名字
 * getInterfaces()      获得当前类实现的类或是接口
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

    }

}
