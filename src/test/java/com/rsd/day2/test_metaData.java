package com.rsd.day2;

import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;
import com.rsd.util.JDBCUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class test_metaData {
    public static void main(String[] args) {

        List<SysUser> list1 = JDBCUtil.queryAllList(SysUser.class, "select * from bu_sys_user where id < 10");
        System.out.println(list1);

        List<SysRole> list = JDBCUtil.queryAllList(SysRole.class, "select * from bu_sys_role");
        for (SysRole sysRole : list) {
            System.out.println(sysRole.getId() + "\t" + sysRole.getName());
        }

    }

}
