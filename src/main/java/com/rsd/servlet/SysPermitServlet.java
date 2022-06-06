package com.rsd.servlet;

import com.rsd.bean.SysPermit;
import com.rsd.service.SysPermitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/sysPermit/list", "/sysPermit/doDelete"})
public class SysPermitServlet extends HttpServlet {

    private SysPermitService sysPermitService = new SysPermitService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.equals("/sysPermit/list")) {
            list(request, response);
        } else if (uri.equals("/sysPermit/doDelete")) {
            doDel(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 9;
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        int count = sysPermitService.count();
        int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);

        List<SysPermit> list = sysPermitService.queryAllList(Integer.parseInt(page), pageSize);

        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("list", list);

        request.getRequestDispatcher("/sysPermit/list.jsp").forward(request, response);
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        sysPermitService.delete(Integer.parseInt(id));

        response.sendRedirect("/sysPermit/list");
    }


}
