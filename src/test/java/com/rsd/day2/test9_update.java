package com.rsd.day2;

import com.rsd.bean.SysFunction;
import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;
import com.rsd.util.JDBCUtil;

import java.util.Date;

public class test9_update {
    public static void main(String[] args) {
        //update 表名 name='',age=2 where id = 9
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


        String sql = JDBCUtil.updateSQL(sysUser);
        System.out.println(sql);
    }
}
