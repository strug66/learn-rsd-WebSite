package com.rsd.util;

import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String beanToJson(Object obj) {
        Class<?> clazz = obj.getClass();
        String json = "{";
        String filedAndValue = "";
        try {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !methodName.equals("getClass")) {
                    Object value = method.invoke(obj);

                    String temp = "";
                    methodName = methodName.replaceFirst("get", "");
                    methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

                    if (value == null) {
                        temp = "\"" + methodName + "\"=" + value;//字符串中引入\:("\\")   引入":("\"")
                    }

                    if (value != null) {
                        if (method.getReturnType().getName().equals(String.class.getName())) {
                            temp = "\"" + methodName + "\"=\"" + value + "\"";
                        }
                        if (method.getReturnType().getName().equals(Integer.class.getName())) {
                            temp = "\"" + methodName + "\"=" + value;
                        }
                        if (method.getReturnType().getName().equals(Date.class.getName())) {
                            temp = "\"" + methodName + "\"=\"" + sdf.format(value) + "\"";
                        }
                    }
                    filedAndValue += "," + temp;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return json + filedAndValue.substring(1) + "}";
    }

    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
//        map.put("id",1);
        try {
            Class clazz = obj.getClass();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !methodName.equals("getClass")) {
                    methodName = methodName.replaceFirst("get", "");
                    methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

                    Object value = method.invoke(obj);
                    //赋值
                    map.put(methodName, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Object mapToBean(Map map, Class clazz) {
        Object bean = null;
        try {
            bean = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("set")) {
                    methodName = methodName.replaceFirst("set", "");
                    methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
                    //赋值
                    method.invoke(bean, map.get(methodName));
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Object cloneBean(Object sourceBean) {
        Object bean = null;
        try {
            Class<?> clazz = sourceBean.getClass();
            bean = clazz.newInstance();

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("set")) {
                    Method sourceMethod = clazz.getMethod("g" + methodName.substring(1));
                    Object value = sourceMethod.invoke(sourceBean);
                    method.invoke(bean, value);
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
