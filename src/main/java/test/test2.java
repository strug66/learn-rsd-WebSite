package test;

import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;
import com.rsd.service.SysRoleService;

import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        List<SysUser> list = new ArrayList<>();

        SysUser sysUser1 = new SysUser();
        sysUser1.setRoleId(1);
        sysUser1.setSex("1");

        SysUser sysUser2 = new SysUser();
        sysUser2.setRoleId(2);
        sysUser2.setSex("2");

        SysUser sysUser3 = new SysUser();
        sysUser3.setRoleId(3);
        sysUser3.setSex("1");

        list.add(sysUser1);
        list.add(sysUser2);
        list.add(sysUser3);


        SysRoleService roleService = new SysRoleService();
        for (SysUser sysUser : list) {
            SysRole sysRole = roleService.getObjectById(sysUser.getRoleId());
            sysUser.setRoleName(sysRole.getName());  //赋值 角色名字

            sysUser.setSex(sysUser.getSex().equals("1") ? "男" : "女");
        }


        for (SysUser sysUser : list) {
            System.out.println(sysUser.getRoleId() + "\t" + sysUser.getSex() + "\t" + sysUser.getRoleName());
        }

    }
}
