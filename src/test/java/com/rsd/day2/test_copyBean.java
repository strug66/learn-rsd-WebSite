package com.rsd.day2;

import com.rsd.bean.SysRole;
import com.rsd.util.BeanUtil;

import java.beans.beancontext.BeanContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class test_copyBean {
    public static void main(String[] args) {
        SysRole sysRole = new SysRole();
        sysRole.setId(1);
        sysRole.setName("管理员");
        sysRole.setCreateTime(new Date());

        SysRole sysRole2 = (SysRole)BeanUtil.cloneBean(sysRole);

        System.out.println(sysRole.getName());

        sysRole2.setName("aaa");
        System.out.println(sysRole2.getName());
        System.out.println(sysRole.getName());
    }
}
