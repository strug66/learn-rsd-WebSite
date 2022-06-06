package com.rsd.day3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test4 {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d{2}");
        Matcher matcher = pattern.matcher("abc1539d52f69f");

//        //字符串替换
//        String s = matcher.replaceAll("A");
//        System.out.println(s);
//        String s1 = matcher.replaceFirst("A");
//        System.out.println(s1);

        //查找
//        System.out.println(matcher.find());
//        System.out.println("1-------------"+matcher.group());

        while (matcher.find()) {
            System.out.println(matcher.group() + "\t" + matcher.start() + "\t" + matcher.end());
        }

    }
}
