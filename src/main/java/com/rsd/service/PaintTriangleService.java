package com.rsd.service;

import java.util.ArrayList;
import java.util.List;

public class PaintTriangleService {

    /**
     * 金字塔
     * 行数      1 2 3 4 5    【n  5行】
     * 星号的个数 1 3 5 7 9（2*i-1） 【i  i=1 i>=9  i+=2】
     * 空格的个数 4 3 2 1 0（n-i）  【m  m=4;m--】
     *
     * @param n 行数
     */
    public String jinzita(int n) {
        String str = "";
        for (int i = 1, m = n - 1; i <= 2 * n - 1; i += 2, m--) {
            for (int j = 1; j <= m; j++) {
                str += "&nbsp;";
            }
            for (int k = 1; k <= i; k++) {
                str += "*";
            }
            str += "<br>";
        }
        return str;
    }

    public String sanjiaxing(int n) {
        String str = "";

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                str += "*";
            }
            str += "<br>";
        }

        return str;
    }

    public List<String> starList(int n) {
        List<String> starList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String str = ""; //声明到外层for循环 里面
            for (int j = 0; j < i; j++) {
                str += "*";
            }
            starList.add(str);
        }

        return starList;
    }

    public List<String> jinzita2(int n) {
        List<String> starList = new ArrayList<>();

        for (int i = 1, m = n - 1; i <= 2 * n - 1; i += 2, m--) {
            String str = ""; //声明到外层for循环 里面
            for (int k = 0; k < m; k++) {
                str += "&nbsp;";
            }
            for (int j = 0; j < i; j++) {
                str += "*";
            }
            starList.add(str);
        }

        return starList;
    }

}
