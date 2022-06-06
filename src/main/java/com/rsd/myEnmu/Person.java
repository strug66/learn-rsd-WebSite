package com.rsd.myEnmu;

import com.rsd.anno.FirstAnno;

@FirstAnno("aaa")  //只给value赋值时，可以不写value
public class Person {

    private int id;
    private String name;

    public Person(){}

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public void tt(Sex sex) {
        if (sex.equals(Sex.man)) {
            System.out.println("同志，你好！");
        }
        if (sex.equals(Sex.woman)) {
            System.out.println("女生，你好！");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
