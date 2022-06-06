package com.rsd.day1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class test3 {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.rsd.day1.Car");

            Constructor<?> constructor = clazz.getConstructor(String.class);
            Car car = (Car) constructor.newInstance("奥迪");
            System.out.println(car.name);


            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(String.class, Integer.class);
            declaredConstructor.setAccessible(true); //访问权限
            Car car1 = (Car) declaredConstructor.newInstance("AAAA",45);
            System.out.println(car1.name);


            System.out.println("-------------");
            Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
            for (Constructor constructor1 : declaredConstructors) {
                System.out.println(constructor1.getModifiers() + "\t" + constructor1.getName() + "\t" + constructor1.getParameterCount());
            }
            System.out.println("-------------");
            Constructor<?>[] constructors = clazz.getConstructors();
            for (Constructor constructor1 : constructors) {
                System.out.println(constructor1.getModifiers() + "\t" + constructor1.getName() + "\t" + constructor1.getParameterCount());
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
