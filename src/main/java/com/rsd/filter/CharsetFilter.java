package com.rsd.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
//@WebFilter(urlPatterns = "/sysRole/*")
public class CharsetFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        System.out.println("----------------"+request.getRequestURI());

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=utf-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
