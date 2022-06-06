package com.rsd.day3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * /w 字符和下划线，指【a-z】【A-Z】【0-9】
 * /d 指数字【0-9】
 * /s 指空格
 * /b 【边界 字母、数字、汉字、下划线】    特殊符号不匹配边界
 * . 指任意字符  //. 只匹配“.”，用\\.来精确匹配
 * {n} 精确到n次
 * {n,} 至少n次
 * {n,m} n到m次
 * *  0到多次匹配 前面的字符或子表达式
 * +  1到多次匹配 前面的字符或子表达式
 * ?  0到1次匹配 前面的字符或子表达式
 * [xyz]  [^xyz]  [x-z]  (x|y|z)
 * (pattern)：优先级   (?=pattern)  (?!pattern)  (?:pattern)
 * \n换行  \f 换页   \r 回车
 * 案例
 */
public class test3_reg {
    public static void main(String[] args) {
//        String str = "a";
//        boolean b = str.matches("^$");
//        System.out.println(b); //"^$"空字符串

//        String str = "a果8$";
//        boolean b = str.matches("^\\w\\W\\d\\D$");
//        System.out.println(b);

//        String str = "I m a stude#.";
////        boolean b = str.matches("^\\w\\s\\w\\w\\s\\w\\s\\w\\w\\w\\w\\w\\w\\w\\.$");
//        boolean b = str.matches("^\\w\\s\\w+\\s\\w\\s\\w{5,}.\\.$");
//        System.out.println(b);

//        String str2 = "_1 a. aaaaaaa";
//        boolean b2 = str2.matches("^\\b\\w\\d\\b\\s\\w\\.\\s\\b\\w*\\b$"); //边界\b
//        System.out.println(b2);
//
//        String str3 = ",国1 a aaaaaaa";
//        boolean b3 = str3.matches("^\\b\\W\\W\\d\\b\\s\\w\\b\\s\\b\\w*\\b$"); //边界\b 遇到特殊符号，无效
//        System.out.println(b3);


//        String str = "z";
//        boolean b = str.matches("^(x|y|z)$"); //[xyz]  [x-z]
//        System.out.println(b);


//        String sss = "as-df-fg";
////        String[] splits = sss.split("-"); //去掉"-"
//        String[] splits = sss.split("(?=-)"); //?= 留下"-"
//        for (String s:splits){
//            System.out.println(s);
//        }
//
//        String ss = "abcfe";
////        boolean b1 = ss.matches("^abc(?:(d|f))e$");
//        boolean b1 = ss.matches("^abc[df]e$");
//        System.out.println(b1);


//        //邮箱
//        String str1 = "dsaj@qjw.cn";
//        boolean b1 = str1.matches("^\\w+@\\w{1,}\\.(com|cn)$");
//        System.out.println("邮箱验证："+b1);
//
//        //邮编
//        String str2 = "154555";
//        boolean b2 = str2.matches("^[1-9]\\d{5}$");
//        System.out.println("邮编验证："+b2);
//
//        //身份证
//        String str3 = "12345619970902720X";
//        boolean b3 = str3.matches("^\\d{6}(1|2)\\d{3}(0[1-9]|10|11|12)[0123][0-9]\\d{3}(\\d|X)$");
//        System.out.println("身份证验证："+b3);

//        boolean matches = Pattern.matches("^[1-9]\\d{5}$", "154555");
//        System.out.println(matches);

        String reg = "\\w";
        String str = "s2@.com";
        System.out.println(reg + "\t" + str);


        boolean matches = Pattern.matches(reg, str);
        System.out.println("=========" + matches);

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println("+++++++++" + matcher.group());
        }

    }
}
