package com.rsd.day4;

import com.rsd.bean.SysFiles;

import java.util.List;

public class test3_sysFiles {
    public static void main(String[] args) {
        SysFiles sysFiles = new SysFiles();

        ISysFilesService sysFilesService = new SysFilesServiceImpl();
        List<SysFiles> sysFiles1 = sysFilesService.queryList();
        System.out.println(sysFiles1);
    }

}
