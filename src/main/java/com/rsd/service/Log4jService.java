package com.rsd.service;

import com.rsd.bean.Log4j;
import com.rsd.util.JDBCUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Log4jService {
    private Logger logger = Logger.getLogger(Log4jService.class);

    public List<Log4j> queryAllList() {
        List<Log4j> list = new ArrayList<>();

        Connection connection = null;
        try {
            connection = JDBCUtil.connection();
            Statement statement = connection.createStatement();

            String sql = "select * from bu_log4j";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String clazz = rs.getString("clazz");
                String level = rs.getString("level");
                String content = rs.getString("content");
                Timestamp createTime = rs.getTimestamp("create_time");

                Log4j log4j = new Log4j();
                log4j.setId(id);
                log4j.setClazz(clazz);
                log4j.setLevel(level);
                log4j.setContent(content);
                log4j.setCreateTime(createTime);

                list.add(log4j);
            }

        } catch (SQLException e) {
            logger.debug("log4j查询异常！" + e.getMessage());
        } finally {
            if (connection != null) {
                JDBCUtil.close(connection);
            }
        }

        return list;
    }

}
