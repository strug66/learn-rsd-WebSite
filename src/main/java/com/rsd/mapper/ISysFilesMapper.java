package com.rsd.mapper;

import com.rsd.DAO.SysFileDAO;
import com.rsd.bean.SysFiles;

import java.util.List;

public interface ISysFilesMapper {
    List<SysFiles> queryList();

    SysFiles getById(Integer id);
}
