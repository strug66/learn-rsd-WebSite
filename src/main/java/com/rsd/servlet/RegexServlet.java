package com.rsd.servlet;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.rsd.bean.Regex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/tool/regex")
public class RegexServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("str");
        String reg = request.getParameter("reg");
        System.out.println(reg + "\t" + str);


        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);//Pattern.matches("^[1-9]\\d{5}$", "154555")

        List<Regex> list = new ArrayList<>();
        int count = 0;
        while (matcher.find()) {
            Regex regex = new Regex();
            count+=1;
            regex.setId(count);
            regex.setGroup(matcher.group());
            list.add(regex);
        }
        System.out.println(list);

        JsonMapper jsonMapper = new JsonMapper();
        String json = jsonMapper.writeValueAsString(list);
        response.getWriter().print(json);
    }
}
