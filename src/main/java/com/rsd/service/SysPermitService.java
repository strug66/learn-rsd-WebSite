package com.rsd.service;

import com.rsd.bean.SysPermit;
import com.rsd.util.JDBCUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SysPermitService {

    private final Logger logger = Logger.getLogger(SysPermitService.class);

    public int count() {
        int count = 0;

        Connection connection = JDBCUtil.connection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from bu_sys_permit");

            rs.next();
            count = rs.getInt(1);

        } catch (SQLException e) {
            logger.error("SQL系统权限count异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return count;
    }

    public List<SysPermit> queryAllList(int page, int pageSize) {
        int m = (page - 1) * pageSize;
        List<SysPermit> list = new ArrayList<>();

        Connection connection = JDBCUtil.connection();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT t1.*,t2.name role_name from (select t1.*,t2.name function_name from bu_sys_permit t1 LEFT join bu_sys_function t2 on t1.function_id = t2.id) t1 left join bu_sys_role t2 on t1.role_id = t2.id limit " + m + "," + pageSize + "";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int roleId = rs.getInt("role_id");
                int functionId = rs.getInt("function_id");
                String roleName = rs.getString("role_name");
                String functionName = rs.getString("function_name");

                SysPermit sysPermit = new SysPermit();
                sysPermit.setId(id);
                sysPermit.setRoleId(roleId);
                sysPermit.setFunctionId(functionId);
                sysPermit.setRoleName(roleName);
                sysPermit.setFunctionName(functionName);

                list.add(sysPermit);
            }
        } catch (SQLException e) {
            logger.error("SQL系统权限查询异常！" + e.getMessage());
        } finally {
            JDBCUtil.close(connection);
        }

        return list;
    }

    public void delete(Integer id) {
        String sql = "delete from bu_sys_permit where id = " + id;
        JDBCUtil.execute(sql);
    }
}
