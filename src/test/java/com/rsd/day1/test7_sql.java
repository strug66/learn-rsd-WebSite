package com.rsd.day1;

import com.rsd.bean.SysFiles;
import com.rsd.util.JDBCUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class test7_sql {
    public static void main(String[] args) {
        //select * from 表名
        //delete from 表名 where id = 13
        SysFiles sysFiles = new SysFiles();
        sysFiles.setId(50);


        String ss = JDBCUtil.selectSQL(SysFiles.class);
        System.out.println(ss);

        String s = JDBCUtil.deleteSQL(sysFiles);
        System.out.println(s);


    }
}
