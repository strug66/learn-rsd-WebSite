package com.rsd.day2;

import com.rsd.bean.SysFunction;
import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class test_beanUtils {
    public static void main(String[] args) {
        SysRole sysRole = new SysRole();
        sysRole.setId(6);
        sysRole.setName("管理员");
        sysRole.setCreateTime(new Date());

        try {
//            BeanUtils.copyProperty(sysRole,"name","china");
//            System.out.println(sysRole.getName());
//
//            BeanUtils.setProperty(sysRole,"name","中国");
//            System.out.println(sysRole.getName());
//
//            String id = BeanUtils.getProperty(sysRole, "id");
//            System.out.println(id);

//            SysRole sysRole1 = (SysRole) BeanUtils.cloneBean(sysRole); //创建出来的新对象和被clone的对象一定是 同一种类型
//            System.out.println(sysRole1.getName());


//            SysRole sysRole1 = new SysRole();
//            BeanUtils.copyProperties(sysRole1,sysRole);//两个参数 可以是同一种类型
//            System.out.println(sysRole1.getId());
//
//            SysFunction sysFunction = new SysFunction();
//            BeanUtils.copyProperties(sysFunction,sysRole);//也可以是不同的类型
//            System.out.println(sysFunction.getName()+sysFunction.getId());

//            Map<String, String> describe = BeanUtils.describe(sysRole); //beanToMap
//            Set<Map.Entry<String, String>> entries = describe.entrySet();
//            Iterator<Map.Entry<String, String>> iterator = entries.iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, String> next = iterator.next();
//                System.out.println(next.getKey() + "\t" + next.getValue());
//            }

            Map<String, Object> map = new HashMap<>();
            map.put("realName", "战战");
            map.put("password", "123456");
            map.put("roleId", 2);
            map.put("sex", "2");
            map.put("id", 30);

            SysUser sysUser= new SysUser();

            BeanUtils.populate(sysUser,map);  //mapToBean
            System.out.println(sysUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
