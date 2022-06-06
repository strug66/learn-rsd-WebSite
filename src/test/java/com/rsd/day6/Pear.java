package com.rsd.day6;

/**
 * 1、无参构造器私有化
 * 2、提供一个私有的、静态的类的变量
 * 3、提供一个静态方法，返回类型是本类，在方法体中编写代码，保证只有一个对象。
 */
public class Pear {
    private static Pear pear;

    private Pear() {

    }

    public static Pear getInstance() {
        if (pear == null) {
            pear = new Pear();
        }
        return pear;
    }

    public void eat(){
        System.out.println("aaaaa");
    }


}
