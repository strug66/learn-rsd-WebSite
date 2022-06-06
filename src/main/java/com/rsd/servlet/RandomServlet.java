package com.rsd.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/tool/random1", "/tool/random2"})
public class RandomServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/tool/random1")) {
            random1(request, response);
        }
        if (uri.equals("/tool/random2")) {
            random2(request, response);
        }
    }

    private void random1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> list = new ArrayList<>();
        list.add("樊书琪");
        list.add("杨治宇");
        list.add("毛晓睿");
        list.add("许文韬");
        list.add("杨争鸣");
        list.add("高超");

        double random = Math.random();
        double floor = Math.floor(random * 6);
        Integer i = (int) floor;
        String studentName = list.get(i);

        request.setAttribute("studentName", studentName);
        request.getRequestDispatcher("/tool/random.jsp").forward(request, response);
    }

    private void random2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] names = {"樊书琪", "杨治宇", "毛晓睿", "许文韬", "杨争鸣", "高超"};

        double random = Math.random();
        double floor = Math.floor(random * 6);
        Integer i = (int) floor;
        String studentName = names[i];

        response.getWriter().print(studentName);

    }


}
