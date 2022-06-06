package com.rsd.filter;

import com.rsd.service.SysRoleService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = "/*")
public class PermitFilter implements Filter {

    private final Logger logger = Logger.getLogger(PermitFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        logger.debug("-----------------" + request.getRequestURL());

        String uri = request.getRequestURI();

        if (uri.equals("/login.jsp") || uri.equals("/login")) {
            filterChain.doFilter(servletRequest, servletResponse); //放行
        } else {
            HttpSession session = request.getSession();
            Object sysUser = session.getAttribute("sysUser");

            if (sysUser != null) {
                filterChain.doFilter(servletRequest, servletResponse); //放行
            } else {
                PrintWriter out = servletResponse.getWriter();
                out.println("<html>");
                out.println("<script>");
                out.println("window.open('/login.jsp','_top')");
                out.println("</script>");
                out.println("</html>");
            }
        }

    }
}
