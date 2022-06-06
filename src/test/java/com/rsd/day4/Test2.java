package com.rsd.day4;

import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        Apple apple1 = new Apple();
        Apple apple = apple1;
        apple.setId(2);
        apple.setName("红富士2222");
        apple.setPrice(15);

        IAppleService appleService = new AppleServiceImpl();
//        appleService.insert(apple);
//        appleService.update(apple);

//        Apple apple2 = appleService.querryById(2);
//        System.out.println(apple2);

        List<Apple> apples = appleService.queryList();
        System.out.println(apples);

        Apple apple2 = appleService.querryById(2);
        System.out.println(apple2);

        appleService.delete(2);

        List<Apple> apples2 = appleService.queryList();
        System.out.println(apples2);
    }
}
