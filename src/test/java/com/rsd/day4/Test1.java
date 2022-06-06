package com.rsd.day4;

import com.rsd.bean.SysFiles;

public class Test1 {
    public static void main(String[] args) {
//        Car<Boolean> car = new Car();
//        car.run(true);

        Car car = new Car();
        SysFiles sysFiles = Car.queryById(SysFiles.class, "", 3);

    }
}
