package com.rsd.day1;

import com.rsd.bean.SysFiles;
import com.rsd.bean.SysFunction;
import com.rsd.bean.SysUser;
import com.rsd.util.JDBCUtil;
import jdk.internal.org.objectweb.asm.tree.IincInsnNode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class test8_insert {
    public static void main(String[] args) {
        //insert into 表名(id,name) values(5,'')

        SysUser sysUser = new SysUser();
        sysUser.setLoginName("星星xx");
        sysUser.setPassword("123456");
        sysUser.setRealName("肖肖同学");
        sysUser.setRoleId(2);
        sysUser.setCreateTime(new Date());
        sysUser.setId(30);

        SysFunction sysFunction = new SysFunction();
        sysFunction.setName("日志文件22");
        sysFunction.setId(29);

        String sql = JDBCUtil.insertSQL(sysFunction);
        System.out.println(sql);
    }

}
