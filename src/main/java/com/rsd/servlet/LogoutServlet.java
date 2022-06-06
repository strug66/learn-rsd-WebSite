package com.rsd.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(3*60); //单位：秒
        session.invalidate();

        //重定向
//        response.sendRedirect("/login.jsp");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<script>");
        out.println("window.open('/login.jsp','_top')");
        out.println("</script>");
        out.println("</html>");

    }
}
