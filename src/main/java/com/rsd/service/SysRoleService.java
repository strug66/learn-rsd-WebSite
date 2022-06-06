package com.rsd.service;

import com.rsd.bean.SysRole;
import com.rsd.servlet.LoginServlet;
import com.rsd.util.JDBCUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SysRoleService {

    private final Logger logger = Logger.getLogger(SysRoleService.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    public int count() {
        int count = 0;
        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from bu_sys_role");

            rs.next();
            count = rs.getInt(1);

        } catch (SQLException e) {
            logger.error("SQL系统角色count异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return count;
    }

    public List<SysRole> queryAllList(int page, int pageSize) {
        int m = (page - 1) * pageSize;
        List<SysRole> list = new ArrayList<>();

        Connection connection = JDBCUtil.connection();

        try {
            Statement statement = connection.createStatement();
            String sql = "select * from bu_sys_role order by id desc limit " + m + "," + pageSize + ""; //降序

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date creatTime = rs.getTimestamp("create_time");

                SysRole role = new SysRole();
                role.setId(id);
                role.setName(name);
                role.setCreateTime(creatTime);

                list.add(role);
            }
        } catch (SQLException e) {
            logger.error("SQL系统角色查询异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return list;
    }

    public String[] getSysFunctionIdsByRoleId(Integer roleId) {
        String[] sysFunctionIds = null;
        List<String> list = new ArrayList<>();

        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from bu_sys_permit where role_id = " + roleId);
            while (rs.next()) {
                Integer functionId = rs.getInt("function_id");

                list.add(functionId.toString());
            }

            if (list.size() > 0) {
                sysFunctionIds = new String[list.size()];  //数组的长度
                for (int i = 0; i < list.size(); i++) {
                    sysFunctionIds[i] = list.get(i);
                }
            }
        } catch (SQLException e) {
            logger.error("SQL系统角色查询功能Ids异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return sysFunctionIds;
    }

    public SysRole getObjectById(int id) {
        SysRole role = null;

        String sql = "select * from bu_sys_role where id = " + id;

        Connection connection = JDBCUtil.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                String name = rs.getString("name");
                Date creatTime = rs.getTimestamp("create_time");

                role = new SysRole();
                role.setId(id);
                role.setName(name);
                role.setCreateTime(creatTime);

            }

        } catch (SQLException e) {
            logger.error("SQL系统角色查询对象异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return role;
    }

    public void insert(SysRole role) {
        String currentTime = sdf.format(role.getCreateTime());

        Connection connection = JDBCUtil.connection();
        try {

            Statement statement = connection.createStatement();

            connection.setAutoCommit(false);  //开启事务

            String sql1 = "insert into bu_sys_role values (null,' " + role.getName() + " ','" + currentTime + "')";
            logger.debug(sql1);

            statement.execute(sql1, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int roleId = rs.getInt(1);


            saveRelationship(roleId, role.getSysFunctionIds());//保存关系

            connection.commit(); //提交事务

        } catch (SQLException e) {

            try {
                connection.rollback();//事务回滚
            } catch (SQLException ex) {
                logger.error("SQL系统角色新增时,事务回滚异常！" + e.getMessage());
            }
        } finally {
            if (connection != null) {
                JDBCUtil.close(connection);
            }
        }


//        String sql1 = "insert into bu_sys_role values (null,' " + role.getName() + " ','" + currentTime + "')";
//        Integer roleId = JDBCUtil.executeInsertForId(sql1, connection);
//        System.out.println("roleId--------------" + roleId);
//        saveRelationship(roleId, role.getSysFunctionIds(), connection);//保存关系
    }

    public void update(SysRole role) {
        String currentTime = sdf.format(role.getCreateTime());

        //事务
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("SQL系统角色修改-类加载异常！" + e.getMessage());
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_webSite", "root", "123456");
            Statement statement = connection.createStatement();

            connection.setAutoCommit(false);  //开启事务

            String sql1 = "update bu_sys_role set name = '" + role.getName() + "' ,create_time = '" + currentTime + "' where id = " + role.getId() + "";
            statement.execute(sql1);

            statement.execute("delete from bu_sys_permit where  role_id =" + role.getId());

            String[] sysFunctionIds = role.getSysFunctionIds();
            if (sysFunctionIds != null && sysFunctionIds.length > 0) {
                for (String sysFunctionId : sysFunctionIds) {
                    statement.execute("insert into bu_sys_permit values (null," + role.getId() + "," + sysFunctionId + ")");
                }
            }

            connection.commit(); //事务提交

        } catch (SQLException e) {
            try {
                connection.rollback(); //事务回滚
            } catch (SQLException ex) {
                logger.error("SQL系统角色修改-事务回滚异常！" + e.getMessage());
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQL系统角色修改-连接关闭异常！" + e.getMessage());
            }
        }


//        String sql1 = "update bu_sys_role set name = '" + role.getName() + "' ,create_time = '" + currentTime + "' where id = " + role.getId() + "";
//        JDBCUtil.execute(sql1);
//
//        JDBCUtil.execute("delete from bu_sys_permit where  role_id =" + role.getId());
//
//        saveRelationship(role.getId(), role.getSysFunctionIds());//保存关系

    }

    public void delete(int id) {
        String sql1 = "delete from bu_sys_role where id=" + id;
        JDBCUtil.execute(sql1);
        String sql2 = "delete from bu_sys_permit where  role_id =" + id;
        JDBCUtil.execute(sql2);
    }

    private void saveRelationship(Integer roleId, String[] sysFunctionIds) {
        if (sysFunctionIds != null && sysFunctionIds.length > 0) {
            for (int i = 0; i < sysFunctionIds.length; i++) {
                int sysFunctionId = Integer.parseInt(sysFunctionIds[i]); //转型
                String sql2 = "insert into bu_sys_permit values (null," + roleId + "," + sysFunctionId + ")";
                JDBCUtil.execute(sql2);
            }
        }
    }


}
