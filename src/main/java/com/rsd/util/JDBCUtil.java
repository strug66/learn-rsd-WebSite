package com.rsd.util;

import com.rsd.anno.NoParameters;
import com.rsd.bean.SysFiles;
import com.rsd.bean.SysUser;
import com.rsd.servlet.LoginServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JDBCUtil {

    private static final Logger logger = Logger.getLogger(LoginServlet.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("MySQL的Jar没有找到。");
        }
    }

    public static <T> T queryById(Class<T> clazz, String sql) {
        T t = null;
        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();

            if (rs.next()) {
                t = clazz.newInstance();//SysUser sysUser = new SysUser();

                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);  //数据库中的字段名
                    String columnTypeName = metaData.getColumnTypeName(i);

                    Object value = null;
                    if (columnTypeName.equals("VARCHAR")) {
                        value = rs.getString(columnName);
                    }
                    if (columnTypeName.equals("INT")) {
                        value = rs.getInt(columnName);
                    }
                    if (columnTypeName.equals("DATETIME")) {
                        value = rs.getTimestamp(columnName);
                    }
                    if (columnTypeName.equals("DATE")) {
                        value = rs.getDate(columnName);
                    }

                    String temp = "";
                    String[] strings = columnName.split("_");
                    for (String s : strings) {
                        temp += s.substring(0, 1).toUpperCase() + s.substring(1);
                    }
                    columnName = temp.substring(0, 1).toLowerCase() + temp.substring(1);

//                    System.out.println(columnName + "\t" + columnTypeName + "\t" + value);
                    BeanUtils.setProperty(t, columnName, value);

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                JDBCUtil.close(connection);
            }
        }

        return t;
    }


    public static <T> List<T> queryAllList(Class<T> clazz, String sql) {
        List<T> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            ResultSetMetaData metaData = rs.getMetaData();

            while (rs.next()) {
                T t = clazz.newInstance();//SysUser sysUser = new SysUser();

                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);  //数据库中的字段名
                    String columnTypeName = metaData.getColumnTypeName(i);

                    Object value = null;
                    if (columnTypeName.equals("VARCHAR")) {
                        value = rs.getString(columnName);
                    }
                    if (columnTypeName.equals("INT")) {
                        value = rs.getInt(columnName);
                    }
                    if (columnTypeName.equals("DATETIME")) {
                        value = rs.getTimestamp(columnName);
                    }
                    if (columnTypeName.equals("DATE")) {
                        value = rs.getDate(columnName);
                    }
                    if (columnTypeName.equals("TIMESTAMP")) {
                        value = rs.getTimestamp(columnName);
                    }

                    String temp = "";
                    String[] strings = columnName.split("_");
                    for (String s : strings) {
                        temp += s.substring(0, 1).toUpperCase() + s.substring(1);
                    }
                    columnName = temp.substring(0, 1).toLowerCase() + temp.substring(1);

//                    System.out.println(columnName + "\t" + columnTypeName + "\t" + value);
                    BeanUtils.setProperty(t, columnName, value);

                }

                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                JDBCUtil.close(connection);
            }
        }

        return list;
    }

    public static String selectSQL(Class cla) {
//        Class clazz = obj.getClass();
        Class clazz = cla;
        String name = clazz.getSimpleName();//SysFiles

//        String[] splits = name.split("F");//Sys 和 iles

        String tableName = "bu";
        String[] strings = name.split("(?=[A-Z])"); //Sys 和 Files
        for (String s : strings) {
            tableName += "_" + s.toLowerCase();
        }
//        System.out.println(tableName); //bu_sys_files

        return "select * from " + tableName;

    }

    public static String insertSQL(Object obj) {
        Class<?> clazz = obj.getClass();
        String name = clazz.getSimpleName();

        String tableName = "bu";
        String[] strings = name.split("(?=[A-Z])");
        for (String s : strings) {
            tableName = tableName + "_" + s.toLowerCase();
        }

        String fileds = ""; // 多个字段名称，用“,”进行连接
        String values = "";// 多个字段对应的值，用“,”进行连接

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") && !methodName.equals("getClass") && !methodName.equals("getId")) { //1、方法筛选，get方法，去掉getId和getClass
                try {
                    Object value = method.invoke(obj);
                    NoParameters annotation = method.getAnnotation(NoParameters.class);
                    if (annotation == null || annotation.supportSQL()) {
//                    if (value != null) {// 2、【值不为null 再进行拼接】去掉getSearchStartTime等
                        //值的拼接
                        //System.out.println(method.getReturnType());
                        if (method.getReturnType().getName().equals(String.class.getName())) {
                            values += ",'" + value + "'";
                        }
                        if (method.getReturnType().getName().equals(Integer.class.getName())) {
                            values += "," + value;
                        }
                        if (method.getReturnType().getName().equals(Date.class.getName())) {
                            values += ",'" + sdf.format(value) + "'";
                        }

                        //字段名称的拼接
                        methodName = methodName.replaceFirst("get", "");//去掉方法名的“get” 【RealName】

                        String filed = ""; //每一个字段
                        String[] strings1 = methodName.split("(?=[A-Z])");
                        for (String s : strings1) {
                            filed += "_" + s.toLowerCase();
                        }
                        filed = filed.substring(1);

                        fileds += "," + filed;
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }


            }
        }

        values = values.substring(1);
        fileds = fileds.substring(1);

        return "insert into " + tableName + "(" + fileds + ") values(" + values + ")";
    }

    public static String updateSQL(Object obj) {
        Class clazz = obj.getClass();

        String tableName = "bu";
        String filedAndValues = "";
        Integer id = null;

        //得到表名
        String name = clazz.getSimpleName();
        String[] strings = name.split("(?=[A-Z])");
        for (String s : strings) {
            tableName += "_" + s.toLowerCase();
        }
        tableName.substring(1);

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") && !methodName.equals("getClass") && !methodName.equals("getId")) {
                try {
                    Object value = method.invoke(obj);
                    NoParameters annotation = method.getAnnotation(NoParameters.class);
                    if (annotation == null || annotation.supportSQL()) {//if (value != null) {
//                        System.out.println(methodName+"="+value);

                        //处理字段
                        methodName = methodName.replaceFirst("get", "");
                        String[] strings1 = methodName.split("(?=[A-Z])");
                        String filed = "";
                        for (String s : strings1) {
                            filed += "_" + s.toLowerCase();
                        }
                        filed = filed.substring(1);

                        //处理值
                        if (method.getReturnType().getName().equals(String.class.getName())) {
                            filedAndValues += "," + filed + "='" + value + "'";
                        }
                        if (method.getReturnType().getName().equals(Integer.class.getName())) {
                            filedAndValues += "," + filed + "=" + value;
                        }
                        if (method.getReturnType().getName().equals(Date.class.getName())) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            filedAndValues += "," + filed + "='" + sdf.format(value) + "'";
                        }

                        //得到id
                        Method method_id = clazz.getMethod("getId");
                        id = (Integer) method_id.invoke(obj);

                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        filedAndValues = filedAndValues.substring(1);
        return "update " + tableName + " set " + filedAndValues + " where id = " + id;
    }

    public static String deleteSQL(Object obj) {
        Class clazz = obj.getClass();
        String name = clazz.getSimpleName();
        String[] strings = name.split("(?=[A-Z])");

        String tableName = "bu";
        for (String s : strings) {
            tableName += "_" + s.toLowerCase();
        }

        Integer id = null;
        try {
            Method method = clazz.getMethod("getId");
            id = (Integer) method.invoke(obj);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return "delete from " + tableName + " where id = " + id;
    }

    public static void execute(String sql) {
        Connection connection = connection();

        try {
            Statement statement = connection.createStatement();

            statement.execute(sql);

        } catch (SQLException e) {
            logger.error("SQL执行异常！" + e.getMessage());
        } finally {
            close(connection);
        }
    }


    public static Integer executeInsertForId(String sql) {
        Integer i = null;

        Connection connection = connection();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql, Statement.RETURN_GENERATED_KEYS);

            //获取插入数据的ID（角色ID）
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            i = rs.getInt(1);

        } catch (SQLException e) {
            logger.error("SQL执行ForId异常！" + e.getMessage());
        }

        return i;
    }

    public static String encryptPassword(String password) {
        String newPwd = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] passwordBytes = password.getBytes();

            byte[] encryptPassword = md.digest(passwordBytes);


            for (byte b : encryptPassword) {
                String hex = Integer.toHexString((b + 4 - 3 - 3 / 8 - 4 / 8 & 2 / 9 - 3 + 2) & 0xff);

                if (hex.length() < 2) {// 判断字符串的长度

                    hex += "0";

                }

                newPwd += hex;
            }

        } catch (NoSuchAlgorithmException e) {
            logger.error("SQL加密异常！" + e.getMessage());
        }

        return newPwd;
    }

    public static Connection connection() {
        Connection collection = null;
        try {
            collection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_website", "root", "123456");
        } catch (SQLException e) {
            logger.error("SQL连接异常！" + e.getMessage());
        }
        return collection;
    }

    public static void close(Connection collection) {
        if (collection != null) {
            try {
                collection.close();
            } catch (SQLException e) {
                logger.error("SQL关闭连接异常！" + e.getMessage());
            }
        }
    }


}
