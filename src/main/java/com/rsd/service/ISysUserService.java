package com.rsd.service;

import com.rsd.bean.SysUser;

import java.util.List;

public interface ISysUserService {

    public SysUser getObiectByLoginName(String loginName);

    int count();

    List<SysUser> queryAllList(int page, int pageSize);

    SysUser getObjectById(int id);

    void insert(SysUser user);

    void update(SysUser user);

    void delete(int id);

    void resetPwd(SysUser sysUser);

    List<SysUser> queryListBySearch(SysUser searchSysUser);
}
