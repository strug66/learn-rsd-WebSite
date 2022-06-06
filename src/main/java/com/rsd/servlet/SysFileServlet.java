package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.SysFiles;
import com.rsd.service.ISysFileService;
import com.rsd.service.impl.SysFileServiceImpl;
import com.rsd.util.UploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = {"/sysFile/list", "/sysFile/doAdd", "/sysFile/toUpdatePage",
        "/sysFile/doUpdate", "/sysFile/doDelete", "/sysFile/download"
        , "/sysFile/list2", "/sysFile/doAdd2", "/sysFile/toUpdatePage2",
        "/sysFile/doUpdate2", "/sysFile/doDelete2"})
public class SysFileServlet extends HttpServlet {
    private ISysFileService sysFileService = new SysFileServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/sysFile/list")) {
            list(request, response);
        } else if (uri.equals("/sysFile/doAdd")) {
            doAdd(request, response);
        } else if (uri.equals("/sysFile/toUpdatePage")) {
            toUpdatePage(request, response);
        } else if (uri.equals("/sysFile/doUpdate")) {
            doUpdate(request, response);
        } else if (uri.equals("/sysFile/doDelete")) {
            doDel(request, response);
        } else if (uri.equals("/sysFile/download")) {
            download(request, response);
        } else if (uri.equals("/sysFile/list2")) {
            list2(request, response);
        } else if (uri.equals("/sysFile/doAdd2")) {
            doAdd2(request, response);
        } else if (uri.equals("/sysFile/toUpdatePage2")) {
            toUpdatePage2(request, response);
        } else if (uri.equals("/sysFile/doUpdate2")) {
            doUpdate2(request, response);
        } else if (uri.equals("/sysFile/doDelete2")) {
            doDel2(request, response);
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SysFiles> sysFiles = sysFileService.queryAllList();
        request.setAttribute("sysFiles", sysFiles);
        request.getRequestDispatcher("/sysFile/list.jsp").forward(request, response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("myFile");

        String name = part.getSubmittedFileName();

        if (!name.equals("")) {
            String path = UploadUtil.baseDir + UploadUtil.getNewFileName(name); //上传到数据库的path:"/uploadFiles/sdsjkdk.txt"
            long size = part.getSize();

            part.write(this.getServletContext().getRealPath("/") + path); // 上传文件

            //保存到数据库
            SysFiles sysFile = new SysFiles();
            sysFile.setName(name);
            sysFile.setPath(path);
            sysFile.setFileSize(size);
            sysFile.setCreateTime(new Date());

            sysFileService.insert(sysFile);
            response.sendRedirect("/sysFile/list");
        } else {
            response.sendRedirect("/sysFile/add.jsp");
        }
    }

    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        SysFiles sysFile = sysFileService.getById(Integer.valueOf(id));
        request.setAttribute("sysFile", sysFile);
        request.getRequestDispatcher("/sysFile/update.jsp").forward(request, response);
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String id = request.getParameter("id");
        Part part = request.getPart("myFile");

        String name = part.getSubmittedFileName();
        String path = null;
        if (!name.equals("")) {
            long size = part.getSize();
            //对原文件进行删除操作
            sysFileService.deleteFile(Integer.valueOf(id), this.getServletContext().getRealPath("/"));

            //上传新文件
            path = UploadUtil.baseDir + UploadUtil.getNewFileName(name);
            part.write(this.getServletContext().getRealPath("/") + path);

            //更新数据库
            SysFiles sysFile = new SysFiles();
            sysFile.setId(Integer.valueOf(id));
            sysFile.setName(name);
            sysFile.setPath(path);
            sysFile.setFileSize(size);
            sysFile.setCreateTime(new Date());

            sysFileService.update(sysFile);
            response.sendRedirect("/sysFile/list");

        } else {
            response.sendRedirect("/sysFile/toUpdatePage?id=" + id);
        }

    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String id = request.getParameter("id");

        //对真实文件做删除操作
        sysFileService.deleteFile(Integer.valueOf(id), this.getServletContext().getRealPath("/"));

        sysFileService.delete(Integer.valueOf(id));
        response.sendRedirect("/sysFile/list");
    }

    private void download(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String id = request.getParameter("id");
        sysFileService.downLoad(Integer.valueOf(id), this.getServletContext().getRealPath("/"), response);

        response.getWriter().print("true");
    }

    private void list2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SysFiles> sysFiles = sysFileService.queryAllList();

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(sysFiles);
        response.getWriter().print(json);
    }

    private void doAdd2(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Part file = request.getPart("file");

        String fileName = file.getSubmittedFileName();
        if (!fileName.equals("")) {
            long size = file.getSize();

            String path = UploadUtil.baseDir + UploadUtil.getNewFileName(fileName);
            file.write(this.getServletContext().getRealPath("/") + path);

            SysFiles sysFile = new SysFiles();
            sysFile.setName(fileName);
            sysFile.setPath(path);
            sysFile.setFileSize(size);
            sysFile.setCreateTime(new Date());
            sysFileService.insert(sysFile);

            response.getWriter().print("true");
        }
    }

    private void toUpdatePage2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Part file = request.getPart("file");
        System.out.println("去修改id:-----" + id);
        SysFiles sysFile = sysFileService.getById(Integer.valueOf(id));

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(sysFile);

        response.getWriter().print(json); //需修改的对象-回显-跳到update.html页面
    }

    private void doUpdate2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        Part part = request.getPart("file");

        String name = part.getSubmittedFileName();
        String path = null;
        if (!name.equals("")) {
            long size = part.getSize();
            //对原文件进行删除操作
            sysFileService.deleteFile(Integer.valueOf(id), this.getServletContext().getRealPath("/"));

            //上传新文件
            path = UploadUtil.baseDir + UploadUtil.getNewFileName(name);
            part.write(this.getServletContext().getRealPath("/") + path);

            //更新数据库
            SysFiles sysFile = new SysFiles();
            sysFile.setId(Integer.valueOf(id));
            sysFile.setName(name);
            sysFile.setPath(path);
            sysFile.setFileSize(size);
            sysFile.setCreateTime(new Date());

            sysFileService.update(sysFile);

            response.getWriter().print("true");
        }
    }


    private void doDel2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println("删除id------------" + id);
        //对真实文件做删除操作
        sysFileService.deleteFile(Integer.valueOf(id), this.getServletContext().getRealPath("/"));

        sysFileService.delete(Integer.valueOf(id));

        response.getWriter().print("true");

    }


}
