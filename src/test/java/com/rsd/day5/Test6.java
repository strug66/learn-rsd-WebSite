package com.rsd.day5;

import com.rsd.service.IEducationService;
import com.rsd.service.impl.EducationServiceImpl;

public class Test6 {
    public static void main(String[] args) {
        IEducationService educationService = new EducationServiceImpl();
        educationService.queryList();
        educationService.queryList();
        educationService.queryList();

        IEducationService educationService2 = new EducationServiceImpl();
        educationService2.queryList();
        educationService2.queryList();
        educationService2.queryList();
    }
}
