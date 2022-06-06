package com.rsd.service;

import com.rsd.bean.SysFunction;
import com.rsd.util.JDBCUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SysFunctionService {

    private final Logger logger = Logger.getLogger(SysFunctionService.class);


    public int count() {
        int count = 0;

        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select  count(*) from bu_sys_function");
            rs.next();
            count = rs.getInt(1);

        } catch (SQLException e) {
            logger.error("SQL系统功能count异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return count;
    }

    public List<SysFunction> queryAllList(int page, int pageSize) {
        int m = (page - 1) * pageSize;
        List<SysFunction> list = new ArrayList<>();

        Connection connection = JDBCUtil.connection();
        try {
            Statement statement = connection.createStatement();

            String sql = "select * from bu_sys_function order by id desc limit " + m + "," + pageSize + "";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");

                SysFunction function = new SysFunction();
                function.setId(id);
                function.setName(name);

                list.add(function);
            }
        } catch (SQLException e) {
            logger.error("SQL系统功能查询异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return list;
    }

    public List<SysFunction> queryAllListForChecked(Integer roleId) {
        String sql = "SELECT t1.*,if(t2.id,'true','false') ischecked  from bu_sys_function t1 LEFT join (SELECT * from bu_sys_permit where role_id = " + roleId + ") t2 on t1.id = t2.function_id";

        List<SysFunction> list = new ArrayList<>();

        Connection connection = JDBCUtil.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String ischecked = rs.getString("ischecked");

                SysFunction function = new SysFunction();
                function.setId(id);
                function.setName(name);
                function.setIschecked(ischecked);

                list.add(function);
            }
        } catch (SQLException e) {
            logger.error("SQL系统功能查询checked异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return list;
    }

    public SysFunction getObjectById(int id) {
        SysFunction function = null;

        String sql = "select * from bu_sys_function where id = " + id;

        Connection connection = JDBCUtil.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                String name = rs.getString("name");

                function = new SysFunction();
                function.setId(id);
                function.setName(name);
            }
        } catch (SQLException e) {
            logger.error("SQL系统功能查询对象异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return function;
    }

    public void insert(SysFunction function) {
        String sql = "insert into bu_sys_function values (null,'" + function.getName() + "')";
        JDBCUtil.execute(sql);
    }

    public void update(SysFunction function) {
        String sql = "update bu_sys_function set name = '" + function.getName() + "' where id = " + function.getId() + "";
        JDBCUtil.execute(sql);
    }

    public void delete(int id) {
        String sql1 = "delete from bu_sys_function where id = " + id;
        JDBCUtil.execute(sql1);
        String sql2 = "delete from bu_sys_permit where function_id =" + id;
        JDBCUtil.execute(sql2);
    }

    public List<SysFunction> queryFunctionListBySysUserId(Integer sysUserId) {
        List<SysFunction> list = new ArrayList<>();

        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();

            String sql = "select * from bu_sys_function where id in (select function_id from bu_sys_permit where role_id = (select role_id from bu_sys_user where id = " + sysUserId + ")) and is_used = 'Y'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");
                String isUsed = rs.getString("is_used");

                SysFunction sysFunction = new SysFunction();
                sysFunction.setId(id);
                sysFunction.setName(name);
                sysFunction.setUrl(url);
                sysFunction.setIsUsed(isUsed);

                list.add(sysFunction);
            }

        } catch (SQLException e) {
            logger.error("SQL系统功能查询ByUserId异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return list;
    }

    public boolean insert2(SysFunction function) {
        boolean result = true;
        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();

            String sql = "insert into bu_sys_function values (null,'" + function.getName() + "',null,null)";
            statement.execute(sql);
        } catch (SQLException e) {
            result = false;
            logger.error("在执行新增操作时发生异常！" + e.getMessage());
        } finally {
            if (connection != null) {
                JDBCUtil.close(connection);
            }
        }

        return result;
    }

    public boolean delete2(Integer id) {
        boolean result = true;
        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();

            String sql = "delete f22om bu_sys_function where id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            result = false;
            logger.error("在执行删除操作时发生异常！" + e.getMessage());
        } finally {
            if (connection != null) {
                JDBCUtil.close(connection);
            }
        }
        return result;
    }

    public boolean update2(SysFunction sysFunction) {
        boolean result = true;
        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();

            String sql = "update bu_sys_function set name ='" + sysFunction.getName() + "' where id = " + sysFunction.getId();
            statement.execute(sql);
        } catch (SQLException e) {
            result = false;
            logger.error("在执行更新操作时发生异常！" + e.getMessage());
        } finally {
            if (connection != null) {
                JDBCUtil.close(connection);
            }
        }
        return result;
    }
}
