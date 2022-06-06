package com.rsd.service.impl;

import com.rsd.DAO.SysUserDAO;
import com.rsd.bean.SysUser;
import com.rsd.service.ISysUserService;

import java.util.List;

public class SysUserServiceImpl implements ISysUserService {
    SysUserDAO sysUserDAO = new SysUserDAO();

    public SysUser getObiectByLoginName(String loginName) {
        return sysUserDAO.getObiectByLoginName(loginName);
    }

    public int count() {
        return sysUserDAO.count();
    }

    public List<SysUser> queryAllList(int page, int pageSize) {
        return sysUserDAO.queryAllList(page, pageSize);
    }

    public SysUser getObjectById(int id) {
        return sysUserDAO.getObjectById(id);
    }

    public void insert(SysUser user) {
        sysUserDAO.insert(user);
    }

    public void update(SysUser user) {
        sysUserDAO.update(user);
    }

    public void delete(int id) {
        sysUserDAO.delete(id);
    }

    public void resetPwd(SysUser sysUser) {
        sysUserDAO.resetPwd(sysUser);
    }

    public List<SysUser> queryListBySearch(SysUser searchSysUser) {
        return sysUserDAO.queryListBySearch(searchSysUser);
    }

}
