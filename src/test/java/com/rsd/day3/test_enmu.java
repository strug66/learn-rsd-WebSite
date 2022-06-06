package com.rsd.day3;

import com.rsd.myEnmu.Person;
import com.rsd.myEnmu.Sex;

public class test_enmu {
    public static void main(String[] args) {

        Person person = new Person(1, "小明");
//        person.setId(2);
//        System.out.println(person.getId());

        person.tt(Sex.man);

        System.out.println("------------");
        Sex[] values = Sex.values();
        for (Sex sex : values) {
            System.out.println(sex.name() + "\t" + sex.getCode() + "\t" + sex.getVal());
        }
    }

}
