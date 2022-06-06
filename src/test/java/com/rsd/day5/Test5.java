package com.rsd.day5;

import com.rsd.service.IEducationService;
import com.rsd.service.impl.EducationServiceImpl2;

public class Test5 {
    public static void main(String[] args) {
        IEducationService educationService = new EducationServiceImpl2();

        educationService.queryList(); //一级缓存在同一个session里，默认是开启的。
    }
}
