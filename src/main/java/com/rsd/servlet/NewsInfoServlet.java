package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.NewsInfo;
import com.rsd.service.INewsInfoService;
import com.rsd.service.impl.NewsInfoServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@MultipartConfig
@WebServlet(urlPatterns = {"/newsInfo/list", "/newsInfo/doAdd", "/newsInfo/doDelete", "/newsInfo/toUpdatePage", "/newsInfo/doUpdate"})
public class NewsInfoServlet extends HttpServlet {
    private INewsInfoService newsInfoService = new NewsInfoServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/newsInfo/list")) {
            list(request, response);
        } else if (uri.equals("/newsInfo/doAdd")) {
            doAdd(request, response);
        } else if (uri.equals("/newsInfo/doDelete")) {
            doDel(request, response);
        } else if (uri.equals("/newsInfo/toUpdatePage")) {
            toUpdatePage(request, response);
        } else if (uri.equals("/newsInfo/doUpdate")) {
            doUpdate(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<NewsInfo> list = newsInfoService.queryList();

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(list);

        response.getWriter().print(json);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String title = request.getParameter("title");
//        String typeId = request.getParameter("typeId");
//        String shortDesc = request.getParameter("shortDesc");
//        String content = request.getParameter("content");
//        String showTime = request.getParameter("showTime");
//        String pubUserId = request.getParameter("pubUserId");
        Map<String, String[]> map = request.getParameterMap();

        NewsInfo newsInfo = new NewsInfo();
//        newsInfo.setTitle(title);
//        newsInfo.setTypeId(Integer.valueOf(typeId));
//        newsInfo.setShortDesc(shortDesc);
//        newsInfo.setContent(content);
//        newsInfo.setShowTime(showTime);
//        newsInfo.setPubUserId(Integer.valueOf(pubUserId));
        newsInfo.setCreateTime(new Date());
        newsInfo.setUpdateTime(new Date());
        try {
            BeanUtils.populate(newsInfo, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        newsInfoService.insert(newsInfo);
        response.getWriter().print("true");

    }


    private void toUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        NewsInfo newsInfo = newsInfoService.getById(Integer.valueOf(id));

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(newsInfo);

        response.getWriter().print(json);
    }


    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        NewsInfo newsInfo = new NewsInfo();
        newsInfo.setUpdateTime(new Date());
        try {
            BeanUtils.populate(newsInfo, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("修改的-------------" + newsInfo);

        newsInfoService.update(newsInfo);
        response.getWriter().print("true");

    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        newsInfoService.delete(Integer.valueOf(id));

        response.getWriter().print("true");
    }
}
