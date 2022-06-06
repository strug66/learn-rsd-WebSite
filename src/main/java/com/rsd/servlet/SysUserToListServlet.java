package com.rsd.servlet;

import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;
import com.rsd.service.SysRoleService;
import com.rsd.service.impl.SysUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/sysUser/toList")
public class SysUserToListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchLoginName = request.getParameter("searchLoginName");
        String searchRealName = request.getParameter("searchRealName");
        String searchRoleId = request.getParameter("searchRoleId");
        String searchTel = request.getParameter("searchTel");
        String searchSex = request.getParameter("searchSex");
        String searchStartTime = request.getParameter("searchStartTime");
        String searchEndTime = request.getParameter("searchEndTime");

        SysUser searchSysUser = new SysUser();
        searchSysUser.setLoginName(searchLoginName);
        searchSysUser.setRealName(searchRealName);
        searchSysUser.setRoleId(searchRoleId != null && !searchRoleId.equals("") ? Integer.parseInt(searchRoleId) : null);
        searchSysUser.setTel(searchTel);
        searchSysUser.setSex(searchSex);
        searchSysUser.setSearchStartTime(searchStartTime);
        searchSysUser.setSearchEndTime(searchEndTime);

        SysUserServiceImpl sysUserService = new SysUserServiceImpl();
        List<SysUser> sysUserList = sysUserService.queryListBySearch(searchSysUser);

        //角色下拉框
        SysRoleService sysRoleService = new SysRoleService();
        int count = sysRoleService.count();
        List<SysRole> sysRoleList = sysRoleService.queryAllList(1, count); //支持分页查询


        request.setAttribute("searchSysUser",searchSysUser);
        request.setAttribute("sysRoleList", sysRoleList);

        request.setAttribute("list", sysUserList);//查询前，不知道count，不做分页

        request.getRequestDispatcher("/sysUser/list.jsp").forward(request, response);

    }
}
