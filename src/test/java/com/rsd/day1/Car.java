package com.rsd.day1;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@MultipartConfig
@WebServlet
public class Car {
    private Integer id = 5;
    public String name = "宝马";
    public Float price = 3.1f;
    public Float money;
    protected static final String ss = null;

    public Car() {
        System.out.println("我是汽车类的无参构造器。");
    }

    public Car(String name) {
        System.out.println("我是汽车类的一个参数的构造器");
        this.name = name;
    }

    private Car(String name, Integer a) {
        System.out.println("我是汽车类的2个参数的构造器");
    }

    public void run() {
        System.out.println("我是小汽车，我在运行中。");
    }

    /**
     * 找零
     *
     * @param money
     * @param price
     * @return
     */
    public Float sale(Float money, Float price) {
        System.out.println("我是出售苹果的方法。");
        return money - price;
    }

    public Float sale(Float ss) {
        System.out.println("我是好人。");
        return ss;
    }

    @SuppressWarnings("ss")
    @Override
    public String toString() {
        return "{Car}";
    }
}
