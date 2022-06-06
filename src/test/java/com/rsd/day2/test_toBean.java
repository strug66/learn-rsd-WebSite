package com.rsd.day2;

import com.rsd.bean.SysFunction;
import com.rsd.bean.SysUser;
import com.rsd.util.BeanUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class test_toBean {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
//        map.put("realName", "战战");
//        map.put("password", "123456");
//        map.put("roleId", 2);
//        map.put("sex", "2");
//        map.put("id", 30);

//
//        Class<SysUser> clazz = SysUser.class;
//        SysUser bean = (SysUser)BeanUtil.mapToBean(map, clazz);
//        System.out.println(bean.toString());

        map.put("name", "测试功能");
        map.put("id", 3);

        Class<SysFunction> clazz = SysFunction.class;
        SysFunction bean = (SysFunction) BeanUtil.mapToBean(map, clazz);
        System.out.println(bean.getName());
        System.out.println(bean.getId());

    }
}
