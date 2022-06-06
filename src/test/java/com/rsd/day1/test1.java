package com.rsd.day1;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class test1 {
    public static void main(String[] args) {
        Car car = new Car();

        try {
            Class<?> clazz = Class.forName("com.rsd.day1.Car");

            Field[] declaredFields = clazz.getDeclaredFields();//得到所有属性
            for (Field field : declaredFields) {
                String mod = Modifier.toString(field.getModifiers());
                field.setAccessible(true); // 取消访问权限
                System.out.println(field.getModifiers() + "\t" + mod + "\t" + field.getType() + "\t" + field.getName() + "\t" + field.get(car));
            }
            System.out.println("-----------------------");
            Field[] fields = clazz.getFields();//得到所有公共的属性
            for (Field field : fields) {
                String mod = Modifier.toString(field.getModifiers());
                field.setAccessible(true); // 取消访问权限
                System.out.println(field.getModifiers() + "\t" + mod + "\t" + field.getType() + "\t" + field.getName() + "\t" + field.get(car));
            }
            System.out.println("========================");//得到指定的单个 属性
            Field file1 = clazz.getDeclaredField("ss");
            System.out.println(file1.getName() + "\t" + file1.get(car));

            Field file2 = clazz.getField("price");//得到指定的单个 公共属性
            System.out.println(file2.getName() + "\t" + file2.get(car));


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
