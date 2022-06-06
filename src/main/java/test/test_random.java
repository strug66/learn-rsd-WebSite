package test;


import java.util.ArrayList;
import java.util.List;

public class test_random {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("樊书琪");
        list.add("杨治宇");
        list.add("毛晓睿");
        list.add("许文韬");
        list.add("杨争鸣");
        list.add("高超");

        double random = Math.random();
        double floor = Math.floor(random * 6);
        Integer i = (int) floor;

        System.out.println(list.get(i));
    }
}
