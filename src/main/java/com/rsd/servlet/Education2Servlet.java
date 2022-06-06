package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.Education;
import com.rsd.service.IEducationService;
import com.rsd.service.impl.EducationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/education/test2")
public class Education2Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IEducationService educationService = new EducationServiceImpl();
        List<Education> list = educationService.queryList();

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(list);

        response.getWriter().print(json);
    }
}
