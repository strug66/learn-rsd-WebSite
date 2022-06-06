package com.rsd.day3;

import com.rsd.anno.FirstAnno;
import com.rsd.bean.SysFunction;
import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;
import com.rsd.myEnmu.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.security.PublicKey;

public class test_anno {
    public static void main(String[] args) {
        SysRole sysRole = new SysRole();

        Class<? extends SysRole> clazz = sysRole.getClass();
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
//            System.out.println(annotation);
//            System.out.println(annotation.toString());
//            System.out.println(annotation.annotationType());
            FirstAnno anno = (FirstAnno) annotation;
            System.out.println(anno.name());
            System.out.println(anno.aaa());
            System.out.println(anno.value());
        }

        SysUser sysUser = new SysUser();

        Class<? extends SysUser> clazz2 = sysUser.getClass();
        Method[] methods = clazz2.getMethods();
        for (Method method : methods) {
            Annotation[] annotations1 = method.getAnnotations();
            for (Annotation annotation : annotations1) {
                System.out.println(method.getName()+"\t"+annotation.toString());
            }
        }

    }
}
