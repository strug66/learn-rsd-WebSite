package com.rsd.servlet;

import com.rsd.bean.SysFunction;
import com.rsd.bean.SysRole;
import com.rsd.service.SysFunctionService;
import com.rsd.service.SysRoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/sysRole/list", "/sysRole/toAddPage", "/sysRole/doAdd", "/sysRole/toUpdatePage", "/sysRole/doUpdate", "/sysRole/doDelete"})
public class SysRoleServlet extends HttpServlet {

    private SysRoleService sysRoleService = new SysRoleService();
    private SysFunctionService sysFunctionService = new SysFunctionService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.equals("/sysRole/list")) {
            list(request, response);
        } else if (uri.equals("/sysRole/toAddPage")) {
            toAddPage(request, response);
        } else if (uri.equals("/sysRole/doAdd")) {
            doAdd(request, response);
        } else if (uri.equals("/sysRole/toUpdatePage")) {
            toUpdatePage(request, response);
        } else if (uri.equals("/sysRole/doUpdate")) {
            doUpdate(request, response);
        } else if (uri.equals("/sysRole/doDelete")) {
            doDel(request, response);
        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 6;
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        int count = sysRoleService.count();
        int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

        List<SysRole> list = sysRoleService.queryAllList(Integer.parseInt(page), pageSize);

        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("pageCount", pageCount);

        request.setAttribute("list", list);

        request.getRequestDispatcher("/sysRole/list.jsp").forward(request, response);
    }

    private void toAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int count = sysFunctionService.count();
        List<SysFunction> sysFunctionList = sysFunctionService.queryAllList(1, count);

        request.setAttribute("sysFunctionList", sysFunctionList);

        request.getRequestDispatcher("/sysRole/add.jsp").forward(request, response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String[] sysFunctionIds = request.getParameterValues("sysFunctionId");

        SysRole role = new SysRole();
        role.setName(name);
        role.setCreateTime(new Date());
        role.setSysFunctionIds(sysFunctionIds);  //javabean新增 数组功能id

        sysRoleService.insert(role);

        response.sendRedirect("/sysRole/list");
    }

    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysRole sysRole = sysRoleService.getObjectById(Integer.parseInt(id));
        String[] functionIds = sysRoleService.getSysFunctionIdsByRoleId(Integer.valueOf(id));
        sysRole.setSysFunctionIds(functionIds); //功能ids

        int count = sysFunctionService.count();
        List<SysFunction> sysFunctionList = sysFunctionService.queryAllList(1, count);
//        List<SysFunction> sysFunctionList = sysFunctionService.queryAllListForChecked(Integer.valueOf(id));

        request.setAttribute("sysRole", sysRole);
        request.setAttribute("sysFunctionList", sysFunctionList);

        request.getRequestDispatcher("/sysRole/update.jsp").forward(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String id = request.getParameter("id");  //接取 指定修改的角色id
        String[] sysFunctionIds = request.getParameterValues("sysFunctionId");

        SysRole role = new SysRole();
        role.setName(name);
        role.setId(Integer.valueOf(id));
        role.setCreateTime(new Date()); //当前时间
        role.setSysFunctionIds(sysFunctionIds);

        sysRoleService.update(role);

        response.sendRedirect("/sysRole/list");
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        sysRoleService.delete(Integer.parseInt(id));

        response.sendRedirect("/sysRole/list");
    }


}
