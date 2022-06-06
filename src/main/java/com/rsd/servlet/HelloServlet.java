package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.SysUser;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(HelloServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("MySQL加载驱动异常！" + e.getMessage());
        }
        Connection connection = null;
        SysUser sysUser = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsd_webSite", "root", "123456");
            Statement statement = connection.createStatement();
            String sql = "select * from bu_sys_user where id=1";
            ResultSet rs = statement.executeQuery(sql);

            rs.next();
            String loginName = rs.getString("login_name");
            String password = rs.getString("password");
            String realName = rs.getString("real_name");
            int roleId = rs.getInt("role_id");
            String sex = rs.getString("sex");
            String tel = rs.getString("tel");
            Timestamp createTime = rs.getTimestamp("create_time");

            sysUser = new SysUser();
            sysUser.setLoginName(loginName);
            sysUser.setPassword(password);
            sysUser.setRealName(realName);
            sysUser.setRoleId(roleId);
            sysUser.setSex(sex);
            sysUser.setTel(tel);
            sysUser.setCreateTime(createTime);

        } catch (SQLException e) {
            logger.error("MySQL连接数据库异常！" + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("MySQL关闭数据库异常！" + e.getMessage());
            }
        }

//json
        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(sysUser);

        PrintWriter out = response.getWriter();
        out.println(json);
    }
}
