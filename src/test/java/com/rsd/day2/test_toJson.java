package com.rsd.day2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.SysUser;
import com.rsd.util.BeanUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test_toJson {
    public static void main(String[] args) {
        //{"id":30,"loginName":"星星xx","roleId":2}  //名字和值 都有"" ，字符串引入双引号：（\"）

        SysUser sysUser = new SysUser();
        sysUser.setLoginName("星星xx");
        sysUser.setPassword("123456");
        sysUser.setRealName("肖肖同学");
        sysUser.setRoleId(2);
        sysUser.setCreateTime(new Date());
        sysUser.setId(30);

        String json = BeanUtil.beanToJson(sysUser);
        System.out.println(json);
//
//        Date date = new Date();
//        System.out.println(date.getTime());

//        JsonMapper jsonMapper = new JsonMapper();
//        try {
//            String json = jsonMapper.writeValueAsString(sysUser);
//            System.out.println(json);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }
}
