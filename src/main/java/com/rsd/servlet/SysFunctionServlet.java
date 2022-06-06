package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/sysFunction/list", "/sysFunction/toAddPage",
        "/sysFunction/doAdd", "/sysFunction/toUpdatePage",
        "/sysFunction/doUpdate", "/sysFunction/doDelete", "/sysFunction/doAdd2", "/sysFunction/doDelete2",
        "/sysFunction/toUpdatePage2", "/sysFunction/doUpdate2"})
public class SysFunctionServlet extends HttpServlet {

    private SysFunctionService sysFunctionService = new SysFunctionService();
    private SysRoleService sysRoleService = new SysRoleService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.equals("/sysFunction/list")) {
            list(request, response);
        } else if (uri.equals("/sysFunction/toAddPage")) {
            toAddPage(request, response);
        } else if (uri.equals("/sysFunction/doAdd")) {
            doAdd(request, response);
        } else if (uri.equals("/sysFunction/toUpdatePage")) {
            toUpdatePage(request, response);
        } else if (uri.equals("/sysFunction/doUpdate")) {
            doUpdate(request, response);
        } else if (uri.equals("/sysFunction/doDelete")) {
            doDel(request, response);
        } else if (uri.equals("/sysFunction/doAdd2")) {
            doAdd2(request, response);
        } else if (uri.equals("/sysFunction/doDelete2")) {
            doDel2(request, response);
        } else if (uri.equals("/sysFunction/toUpdatePage2")) {
            toUpdatePage2(request, response);
        } else if (uri.equals("/sysFunction/doUpdate2")) {
            doUpdate2(request, response);
        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 8;
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        int count = sysFunctionService.count();
        int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

        List<SysFunction> list = sysFunctionService.queryAllList(Integer.parseInt(page), pageSize);

        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("pageCount", pageCount);

//        request.setAttribute("list", list);
//        request.getRequestDispatcher("/sysFunction/list.jsp").forward(request, response);

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(list);

        PrintWriter out = response.getWriter();
        out.print(json);
    }

    private void toAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int count = sysRoleService.count();
        List<SysRole> sysRoleList = sysRoleService.queryAllList(1, count);

        request.setAttribute("sysRoleList", sysRoleList);

        request.getRequestDispatcher("/sysFunction/add.jsp").forward(request, response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        SysFunction sysFunction = new SysFunction();
        sysFunction.setName(name);

        sysFunctionService.insert(sysFunction);

        response.sendRedirect("/sysFunction/list");
    }

    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysFunction sysFunction = sysFunctionService.getObjectById(Integer.parseInt(id));

        request.setAttribute("sysFunction", sysFunction);

        request.getRequestDispatcher("/sysFunction/update.jsp").forward(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        SysFunction sysFunction = new SysFunction();
        sysFunction.setId(Integer.valueOf(id));  //需要修改的 对象id
        sysFunction.setName(name);

        sysFunctionService.update(sysFunction);


        response.sendRedirect("/sysFunction/list");
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        sysFunctionService.delete(Integer.parseInt(id));

        response.sendRedirect("/sysFunction/list");
    }

    private void doAdd2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        SysFunction sysFunction = new SysFunction();
        sysFunction.setName(name);

        boolean b = sysFunctionService.insert2(sysFunction);

        PrintWriter out = response.getWriter();
        out.print(b);
    }

    private void doDel2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        boolean b = sysFunctionService.delete2(Integer.parseInt(id));

        PrintWriter out = response.getWriter();
        out.print(b);
    }

    private void toUpdatePage2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        SysFunction sysFunction = sysFunctionService.getObjectById(Integer.parseInt(id));

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(sysFunction);
        System.out.println("----------" + json);
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    private void doUpdate2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        SysFunction sysFunction = new SysFunction();
        sysFunction.setId(Integer.valueOf(id));
        sysFunction.setName(name);

        boolean b = sysFunctionService.update2(sysFunction);

        PrintWriter out = response.getWriter();
        out.print(b);
    }


}
