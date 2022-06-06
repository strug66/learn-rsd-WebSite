package com.rsd.day5;

import com.rsd.bean.Apple;
import com.rsd.service.IAppleService;
import com.rsd.service.impl.AppleServiceImpl;

import java.util.List;

public class Test1_fz {
    public static void main(String[] args) {
        IAppleService appleService = new AppleServiceImpl();
        List<Apple> list = appleService.queryListByIdOrPrice(1, 22);
        System.out.println(list);
    }
}
