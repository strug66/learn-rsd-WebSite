package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.Log4j;
import com.rsd.service.Log4jService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/log4j/list"})
public class Log4jServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/log4j/list")) {
            list(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Log4jService log4jService = new Log4jService();
        List<Log4j> log4jList = log4jService.queryAllList();

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(log4jList);

        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
