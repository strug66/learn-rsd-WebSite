package com.rsd.servlet;

import com.rsd.bean.SysUser;
import com.rsd.service.ISysUserService;
import com.rsd.service.impl.SysUserServiceImpl;
import com.rsd.util.JDBCUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");

        ISysUserService sysUserService = new SysUserServiceImpl();
        SysUser sysUser = sysUserService.getObiectByLoginName(loginName);

        if (sysUser != null) {

            String encryptPwd = JDBCUtil.encryptPassword(password); //表单接到的密码，MD5加密

            if (sysUser.getPassword().equals(encryptPwd)) {
                logger.info("[" + sysUser.getLoginName() + "]用户登录成功！");

                HttpSession session = request.getSession();
                session.setAttribute("sysUser", sysUser);  //对象放到session里
                session.setMaxInactiveInterval(3 * 60);  //失效时间

                response.sendRedirect("/index.jsp");
            } else {
                logger.info("[" + sysUser.getLoginName() + "]密码错误！");

                request.setAttribute("loginResult", "密码错误！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            logger.info("用户名不存在！");

            request.setAttribute("loginResult", "用户名不存在！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }


}
