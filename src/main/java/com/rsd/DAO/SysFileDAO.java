package com.rsd.DAO;

import com.rsd.bean.SysFiles;
import com.rsd.util.JDBCUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SysFileDAO {
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SysFiles getById(Integer id) {
        return JDBCUtil.queryById(SysFiles.class, "select * from bu_sys_files where id=" + id);

    }

    public List<SysFiles> queryAllList() {
        List<SysFiles> sysFiles = JDBCUtil.queryAllList(SysFiles.class, "select * from bu_sys_files");
        return sysFiles;
    }

    public void insert(SysFiles sysFile) {
        String time = sdf.format(sysFile.getCreateTime());
        String sql = "insert into bu_sys_files values (null,'" + sysFile.getName() + "','" + sysFile.getPath() + "'," + sysFile.getFileSize() + ",'" + time + "')";
        JDBCUtil.execute(sql);
    }

    public void update(SysFiles sysFile) {
        String time = sdf.format(sysFile.getCreateTime());
        String sql = "update bu_sys_files set name='" + sysFile.getName() + "',path='" + sysFile.getPath() + "',file_size=" + sysFile.getFileSize() + ",create_time='" + time + "' where id=" + sysFile.getId();
        JDBCUtil.execute(sql);
    }

    public void delete(Integer id) {
        String sql = "delete from bu_sys_files where id=" + id;
        JDBCUtil.execute(sql);
    }

}
