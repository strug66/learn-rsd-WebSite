package com.rsd.day2;

import com.rsd.bean.SysUser;
import com.rsd.util.BeanUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class test_toMap {
    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setLoginName("星星xx");
        sysUser.setPassword("123456");
        sysUser.setRealName("肖肖同学");
        sysUser.setRoleId(2);
        sysUser.setCreateTime(new Date());
        sysUser.setId(30);

        Map<String, Object> map = BeanUtil.beanToMap(sysUser);

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            System.out.println(next.getKey() + ":" + next.getValue());
        }


    }
}
