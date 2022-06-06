package com.rsd.DAO;

import com.rsd.bean.SysUser;
import com.rsd.service.impl.SysUserServiceImpl;
import com.rsd.util.JDBCUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysUserDAO {
    private final Logger logger = Logger.getLogger(SysUserServiceImpl.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    public SysUser getObiectByLoginName(String loginName) {
        SysUser sysUser = null;

        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();

            String sql = "select * from bu_sys_user where login_name = '" + loginName + "'";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                logger.debug("数据库的用户密码-----------" + password);

                String realName = rs.getString("real_name");
                int roleId = rs.getInt("role_id");
                String sex = rs.getString("sex");
                String tel = rs.getString("tel");
                Date createTime = rs.getTimestamp("create_time");

                sysUser = new SysUser();
                sysUser.setId(id);
                sysUser.setLoginName(loginName);
                sysUser.setPassword(password);
                sysUser.setRealName(realName);

                sysUser.setRoleId(roleId);
                sysUser.setSex(sex);
                sysUser.setTel(tel);
                sysUser.setCreateTime(createTime);
            }
        } catch (SQLException e) {
            logger.error("SQL系统用户查询ByLoginName异常！" + e.getMessage());
        } finally {
            if (connection != null) {
                JDBCUtil.close(connection);
            }
        }

        //判断性别
        if (sysUser != null) {
            sysUser.setSex(sysUser.getSex().equals("1") ? "男" : "女");
        }

        return sysUser;
    }


    public int count() {
        int count = 0;

        Connection connection = JDBCUtil.connection();
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select count(*) from bu_sys_user");
            rs.next();
            count = rs.getInt(1);

        } catch (SQLException e) {
            logger.error("SQL系统用户count异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return count;
    }

    public List<SysUser> queryAllList(int page, int pageSize) {
//        return JDBCUtil.queryAllList(SysUser.class, "select * from bu_sys_user");
        int p = (page - 1) * pageSize;
        List<SysUser> list = new ArrayList<>();

        Connection connection = JDBCUtil.connection();
        try {
            Statement statement = connection.createStatement();

            String sql = "select t1.*,t2.name role_name from bu_sys_user t1 left join bu_sys_role t2 on t1.role_id = t2.id order by t1.id desc limit " + p + "," + pageSize + "";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String loginName = rs.getString("login_name");
                String password = rs.getString("password");
                String realName = rs.getString("real_name");
                int roleId = rs.getInt("role_id");
                String sex = rs.getString("sex");
                String tel = rs.getString("tel");
                Date createTime = rs.getTimestamp("create_time");
                String roleName = rs.getString("role_name");

                SysUser user = new SysUser();
                user.setId(id);
                user.setLoginName(loginName);
                user.setPassword(password);
                user.setRealName(realName);
                user.setRoleId(roleId);
                user.setSex(sex);
                user.setTel(tel);
                user.setCreateTime(createTime);
                user.setRoleName(roleName);

                list.add(user);
            }

        } catch (SQLException e) {
            logger.error("SQL系统用户查询异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        //判断性别
        for (SysUser sysUser : list) {
            sysUser.setSex(sysUser.getSex().equals("1") ? "男" : "女");
        }

        return list;
    }

    public SysUser getObjectById(int id) {
        SysUser sysUser = null;

        String sql = "select * from bu_sys_user where id = " + id;

        Connection connection = JDBCUtil.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                String loginName = rs.getString("login_name");
                String password = rs.getString("password");
                String realName = rs.getString("real_name");
                String headPicPath = rs.getString("head_pic_path");
                int roleId = rs.getInt("role_id");
                String sex = rs.getString("sex");
                String tel = rs.getString("tel");
                Date createTime = rs.getDate("create_time");

                sysUser = new SysUser();
                sysUser.setId(id);
                sysUser.setLoginName(loginName);
                sysUser.setPassword(password);
                sysUser.setRealName(realName);
                sysUser.setHeadPicPath(headPicPath);
                sysUser.setRoleId(roleId);
                sysUser.setSex(sex);
                sysUser.setTel(tel);
                sysUser.setCreateTime(createTime);
            }

        } catch (SQLException e) {
            logger.error("SQL系统用户查询对象异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        //判断性别
        if (sysUser != null) {
            sysUser.setSex(sysUser.getSex().equals("1") ? "男" : "女");
        }

        return sysUser;
    }

    public void insert(SysUser user) {
        String currentTime = sdf.format(user.getCreateTime());

        logger.debug("----" + user.getSex());
        String sql = "insert into bu_sys_user values (null,'" + user.getLoginName() + "','" + user.getPassword() + "','" + user.getRealName() + "','" + user.getHeadPicPath() + "'," + user.getRoleId() + ",'" + user.getSex() + "','" + user.getTel() + "','" + currentTime + "')";
        JDBCUtil.execute(sql);
    }

    public void update(SysUser user) {
        String currentTime = sdf.format(user.getCreateTime());

        String sql = "update bu_sys_user set login_name = '" + user.getLoginName() + "',password = '" + user.getPassword() + "',real_name = '" + user.getRealName() + "'";

        if (user.getHeadPicPath() != null) {
            sql += ",head_pic_path='" + user.getHeadPicPath() + "'";
        }

        sql += ",role_id=" + user.getRoleId() + ",sex = '" + user.getSex() + "',tel='" + user.getTel() + "',create_time = '" + currentTime + "'  where id = " + user.getId() + "";
        JDBCUtil.execute(sql);
    }

    public void delete(int id) {
        String sql = "delete from bu_sys_user where id = " + id;
        JDBCUtil.execute(sql);
    }

    public void resetPwd(SysUser sysUser) {
        String sql = "update bu_sys_user set password = '" + sysUser.getPassword() + "' where id = " + sysUser.getId() + "";
        JDBCUtil.execute(sql);
    }

    public List<SysUser> queryListBySearch(SysUser searchSysUser) {
//        int m = (page - 1) * pageSize;  查询之前，不知道结果集合的count
        List<SysUser> searchSysUserList = new ArrayList<>();

        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();

            String sql = "select t1.*,t2.name role_name from bu_sys_user t1 left join bu_sys_role t2 on t1.role_id = t2.id";

            sql += " where 1=1"; //where 真，下面开始拼接查询条件

            if (searchSysUser.getLoginName() != null && !searchSysUser.getLoginName().equals("")) {
                sql += " and t1.login_name like '%" + searchSysUser.getLoginName() + "%'";
            }
            if (searchSysUser.getRealName() != null && !searchSysUser.getRealName().equals("")) {
                sql += " and t1.real_name like '%" + searchSysUser.getRealName() + "%'";
            }
            if (searchSysUser.getTel() != null && !searchSysUser.getTel().equals("")) {
                sql += " and t1.tel like '%" + searchSysUser.getTel() + "%'";
            }
            if (searchSysUser.getRoleId() != null && !searchSysUser.getRoleId().equals("")) {
                sql += " and t1.role_id = " + searchSysUser.getRoleId();
            }
            if (searchSysUser.getSex() != null && !searchSysUser.getSex().equals("")) {
                sql += " and t1.sex = " + searchSysUser.getSex();
            }
            if (searchSysUser.getSearchStartTime() != null && !searchSysUser.getSearchStartTime().equals("")) {
                sql += " and t1.create_time >= '" + searchSysUser.getSearchStartTime() + " 00:00:00'";
            }
            if (searchSysUser.getSearchEndTime() != null && !searchSysUser.getSearchEndTime().equals("")) {
                sql += " and t1.create_time < '" + searchSysUser.getSearchEndTime() + " 23:59:59'";
            }

            logger.debug(sql);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String loginName = rs.getString("login_name");
                String password = rs.getString("password");
                String realName = rs.getString("real_name");   //全部的真实名字
                int roleId = rs.getInt("role_id");
                String sex = rs.getString("sex");
                String tel = rs.getString("tel");
                Date createTime = rs.getTimestamp("create_time");
                String roleName = rs.getString("role_name");

                SysUser user = new SysUser();
                user.setId(id);
                user.setLoginName(loginName);
                user.setPassword(password);
                user.setRealName(realName);
                user.setRoleId(roleId);
                user.setSex(sex);
                user.setTel(tel);
                user.setCreateTime(createTime);
                user.setRoleName(roleName);

                searchSysUserList.add(user);
            }

        } catch (SQLException e) {
            logger.error("SQL系统用户查询By条件异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        for (SysUser sysUser : searchSysUserList) {
            sysUser.setSex(sysUser.getSex().equals("1") ? "男" : "女");
        }

        return searchSysUserList;
    }

}
