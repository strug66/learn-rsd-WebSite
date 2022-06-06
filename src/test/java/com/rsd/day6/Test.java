package com.rsd.day6;

public class Test {
    public static void main(String[] args) {
//        Pear pear = new Pear();
        Pear pear1 = Pear.getInstance();
        System.out.println(pear1);
        Pear pear2 = Pear.getInstance();
        System.out.println(pear2);

        pear1.eat();
    }
}
