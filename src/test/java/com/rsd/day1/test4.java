package com.rsd.day1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 得到类的相关信息
 */
public class test4 {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.rsd.day1.Car");

            System.out.println(Modifier.toString(clazz.getModifiers()));
            System.out.println(clazz.getName());

            //注解
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation:annotations){
                System.out.println("---------"+annotation.toString());
            }

            //方法的注解
            Method toString = clazz.getMethod("toString");
            Annotation[] annotations1 = toString.getAnnotations();
            System.out.println("方法的注解个数："+annotations1.length);
            for (Annotation annotation:annotations){
                System.out.println("=========="+annotation.annotationType());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
