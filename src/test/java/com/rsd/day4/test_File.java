package com.rsd.day4;

import com.rsd.bean.SysFiles;
import com.rsd.util.JDBCUtil;

import java.util.List;

public class test_File {
    public static void main(String[] args) {
        List<SysFiles> sysFiles = JDBCUtil.queryAllList(SysFiles.class, "select * from bu_sys_files");
        System.out.println(sysFiles);
    }
}
