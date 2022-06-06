package com.rsd.day1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class test2 {

    public static void main(String[] args) {
        Car car = new Car();

        try {
            Class<?> clazz = Class.forName("com.rsd.day1.Car");

            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method method : declaredMethods) {
                String mods = Modifier.toString(method.getModifiers());
                System.out.println(mods + "\t" + method.getReturnType() + "\t" + method.getName() + "\t" + method.getParameterCount());

                Parameter[] parameters = method.getParameters();//方法的参数
                for (Parameter parameter : parameters) {
                    System.out.println(parameter.getType() + "\t" + parameter.getName());
                }
            }
            System.out.println("-----------------");
            Method method_run = clazz.getDeclaredMethod("run");
            Object o1 = method_run.invoke(car);//执行方法  返回值  参数【Object... args】
            System.out.println(o1);

            System.out.println("-----------------");
            Method method_sale2 = clazz.getDeclaredMethod("sale", Float.class);
            Object o2 = method_sale2.invoke(car,66f);//执行方法  返回值
            System.out.println(o2);

            System.out.println("-----------------");
            Method method_sale = clazz.getDeclaredMethod("sale", Float.class,Float.class);

            int modifiers = method_sale.getModifiers(); //修饰符（数字）
            String modifiers_str = Modifier.toString(modifiers); //修饰符（字符串）
            Class<?> returnType = method_sale.getReturnType(); //返回值类型
            String method_name = method_sale.getName();//方法名字
            Class<?>[] parameterTypes = method_sale.getParameterTypes();//参数列表
            Object o3 = method_sale.invoke(car,66f,20f);//执行方法【传参数】  得到返回值
            System.out.println(o3);


//-----------------------
            clazz.getMethods(); //得到公共的方法
            Method run = clazz.getMethod("run");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
