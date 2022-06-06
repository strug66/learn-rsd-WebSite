package com.rsd.day1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 创建类对象的5种方式：
 */
public class test6_class {
    public static void main(String[] args) {

        Apple apple1 = new Apple();  //new
        System.out.println("第1种创建对象的方式：" + apple1);

        try {
            Class<?> clazz = Class.forName("com.rsd.day1.Apple");  //字符串引入

            Apple apple2 = (Apple) clazz.newInstance();
            System.out.println("第2种创建对象的方式：" + apple2);

            Constructor<?> constructor = clazz.getConstructor();
            Apple apple3 = (Apple) constructor.newInstance();
            System.out.println("第3种创建对象的方式：" + apple3);


            Class clazz2 = Apple.class;  //类的结构
            Apple apple4 = (Apple) clazz2.newInstance();
            System.out.println("第4种创建对象的方式：" + apple4);

            Class clazz3 = apple1.getClass();//是可以得到类的所有元素
            Apple apple5 = (Apple) clazz3.newInstance();
            System.out.println("第5种创建对象的方式：" + apple5);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
