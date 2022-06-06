package test;

import com.rsd.bean.SysUser;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test_beanutil {
    public static void main(String[] args) {
//        ConvertUtils.register(new DateLocaleConverter(), java.util.Date.class); //时间-格式化
        DateConverter converter = new DateConverter();
        converter.setPattern(new String("yyyy-MM-dd"));
        ConvertUtils.register(converter, Date.class);

        Map<String, String> map = new HashMap<>();

        map.put("id", "1");
        map.put("loginName", "管理员");
        map.put("password", "123456");
        map.put("realName", "圆圆");
        map.put("roleId", "3");
        map.put("roleName", "");
        map.put("sex", "男");
        map.put("tel", "12323333456");
        map.put("createTime", "2010-4-05");

        try {
            Object s = Class.forName("com.rsd.bean.SysUser").newInstance();
            BeanUtils.copyProperties(s, map);

            System.out.println(s);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        t1();

    }

    private static void t1() {
        SysUser sysUser = new SysUser();

        sysUser.setId(2);
        sysUser.setLoginName("admin");
        sysUser.setPassword("123456");
        sysUser.setRealName("飞飞");
        sysUser.setRoleId(3);
        sysUser.setRoleName("超级管理员");
        sysUser.setSex("女");
        sysUser.setTel("12345859541");
        sysUser.setCreateTime(new Date());

        try {
            Object s2 = Class.forName("com.rsd.bean.SysUser").newInstance();

            BeanUtils.copyProperty(s2, "id", sysUser.getId());
            BeanUtils.copyProperty(s2, "loginName", sysUser.getLoginName());
            BeanUtils.copyProperty(s2, "password", sysUser.getPassword());
            BeanUtils.copyProperty(s2, "realName", sysUser.getRealName());
            BeanUtils.copyProperty(s2, "roleId", sysUser.getRoleId());
            BeanUtils.copyProperty(s2, "roleName", sysUser.getRoleId());
            BeanUtils.copyProperty(s2, "sex", sysUser.getSex());
            BeanUtils.copyProperty(s2, "tel", sysUser.getTel());
            BeanUtils.copyProperty(s2, "createTime", sysUser.getCreateTime());

            System.out.println(s2);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
