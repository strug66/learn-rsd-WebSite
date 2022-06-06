package com.rsd.servlet;

import com.rsd.bean.SysRole;
import com.rsd.bean.SysUser;
import com.rsd.service.ISysUserService;
import com.rsd.service.SysRoleService;
import com.rsd.service.impl.SysUserServiceImpl;
import com.rsd.util.JDBCUtil;
import com.rsd.util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/sysUser/list", "/sysUser/toAddPage", "/sysUser/doAdd", "/sysUser/toUpdatePage", "/sysUser/doUpdate", "/sysUser/doDelete", "/loginResetPwd"})
public class SysUserServlet extends HttpServlet {

    private ISysUserService sysUserService = new SysUserServiceImpl();
    private SysRoleService sysRoleService = new SysRoleService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.equals("/sysUser/list")) {
            list(request, response);
        } else if (uri.equals("/sysUser/toAddPage")) {
            toAddPage(request, response);
        } else if (uri.equals("/sysUser/doAdd")) {
            doAdd(request, response);
        } else if (uri.equals("/sysUser/toUpdatePage")) {
            toUpdatePage(request, response);
        } else if (uri.equals("/sysUser/doUpdate")) {
            doUpdate(request, response);
        } else if (uri.equals("/sysUser/doDelete")) {
            doDel(request, response);
        } else if (uri.equals("/loginResetPwd")) {
            resetPwd(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageSize = 5;
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        Integer count = sysUserService.count();
        Integer pageCount = count / pageSize + (count % pageSize != 0 ? 1 : 0);

        List<SysUser> list = sysUserService.queryAllList(Integer.parseInt(page), pageSize);  //多表联查  角色id、角色名字

        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("pageCount", pageCount);

        request.setAttribute("list", list);

        request.getRequestDispatcher("/sysUser/list.jsp").forward(request, response);
    }

    private void toAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int count = sysRoleService.count();
        List<SysRole> list = sysRoleService.queryAllList(1, count);

        request.setAttribute("list", list);

        request.getRequestDispatcher("/sysUser/add.jsp").forward(request, response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String s = JDBCUtil.encryptPassword(password);
        String realName = request.getParameter("realName");
        Part headPicPath = request.getPart("headPicPath");
        String roleId = request.getParameter("roleId");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");

        String path = null;  //没有选择文件，数据库的路径为null
        String fileName = headPicPath.getSubmittedFileName();
        if (!fileName.equals("")) {
            InputStream in = headPicPath.getInputStream();
            String newFileName = UploadUtil.getNewFileName(fileName);
            path = UploadUtil.baseDir + newFileName;
//            boolean b = UploadUtil.upload(in, this.getServletContext().getRealPath("/") + path);
            headPicPath.write( this.getServletContext().getRealPath("/") + path);
        }

        SysUser user = new SysUser();
        user.setLoginName(loginName);
        user.setPassword(s);
        user.setRealName(realName);
        user.setHeadPicPath(path);
        user.setRoleId(Integer.valueOf(roleId)); //角色编号
        user.setSex(sex);
        user.setTel(tel);
        user.setCreateTime(new Date());

        sysUserService.insert(user);

        response.sendRedirect("/sysUser/list");
    }

    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        SysUser user = sysUserService.getObjectById(Integer.parseInt(id));

        request.setAttribute("user", user);

//将角色名给jsp页面
        int count = sysRoleService.count();
        List<SysRole> sysRoleList = sysRoleService.queryAllList(1, count);

        request.setAttribute("roleList", sysRoleList);

        request.getRequestDispatcher("/sysUser/update.jsp").forward(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String s = JDBCUtil.encryptPassword(password);
        String realName = request.getParameter("realName");
        Part part = request.getPart("headPicPath");
        String roleId = request.getParameter("roleId");
        String sex = request.getParameter("sex");
        String tel = request.getParameter("tel");

        String fileName = part.getSubmittedFileName();
        String path = null;
        if (!fileName.equals("")) {
            InputStream in = part.getInputStream();
            String newFileName = UploadUtil.getNewFileName(fileName);

            path = UploadUtil.baseDir + newFileName;
//            boolean b = UploadUtil.upload(in, this.getServletContext().getRealPath("/") + path);
            part.write(this.getServletContext().getRealPath("/") + path);
        }

        SysUser user = new SysUser();
        user.setId(Integer.valueOf(id));
        user.setLoginName(loginName);
        user.setPassword(s);
        user.setRealName(realName);
        user.setHeadPicPath(path);
        user.setRoleId(Integer.valueOf(roleId)); //角色编号
        user.setSex(sex);
        user.setTel(tel);
        //创建时间--------
        user.setCreateTime(new Date());

        sysUserService.update(user);

        response.sendRedirect("/sysUser/list");
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        sysUserService.delete(Integer.parseInt(id));

        response.sendRedirect("/sysUser/list");
    }

    private void resetPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");

        String encryptPassword = JDBCUtil.encryptPassword(password);
        String encryptNewPassword = JDBCUtil.encryptPassword(newPassword);

        SysUser sysUserId = sysUserService.getObjectById(Integer.parseInt(id));
        String passwordId = sysUserId.getPassword();

        if (encryptPassword.equals(passwordId)) { //原密码正确
            SysUser sysUser = new SysUser();

            sysUser.setId(Integer.valueOf(id));
            sysUser.setPassword(encryptNewPassword);

            sysUserService.resetPwd(sysUser);

            request.setAttribute("resetPwdResult", "重置成功");
        } else {
            request.setAttribute("resetPwdResult", "重置失败");
        }

        request.getRequestDispatcher("/sysUser/list").forward(request, response);

    }

}
