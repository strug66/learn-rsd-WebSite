package com.rsd.servlet;

import com.rsd.service.PaintTriangleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/tool/paintTriangle")
public class PaintTriangleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PaintTriangleService paintTriangleService = new PaintTriangleService();

        String line = request.getParameter("line");
        String graph = request.getParameter("graph");

        Integer lineNumber = Integer.parseInt(line);


        String s = "";
        List<String> starList = new ArrayList<>();

        if (graph.equals("sanjiaoxing")) {
            //第1种实现方式：拼接字符串
            s = paintTriangleService.sanjiaxing(lineNumber);
            //第2种实现方式：list+拼接字符串
            starList = paintTriangleService.starList(lineNumber);

        }
        if (graph.equals("jinzita")) {
            s = paintTriangleService.jinzita(lineNumber);
            starList = paintTriangleService.jinzita2(lineNumber);
        }


        request.setAttribute("line", line);
        request.setAttribute("graph", graph);
        request.setAttribute("starList", starList);
        request.setAttribute("s", s);
        request.getRequestDispatcher("/tool/paintTriangle.jsp").forward(request, response);

    }
}
