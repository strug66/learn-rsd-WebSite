package com.rsd.servlet;

import com.rsd.bean.Calculator;
import com.rsd.service.CalculatorService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/tool/calculator", "/tool/clearHistory"})
public class CalculatorServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(CalculatorServlet.class);
    private CalculatorService calculatorService = new CalculatorService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/tool/calculator")) {
            list(request, response);
        }
        if (uri.equals("/tool/clearHistory")) {
            clear(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Calculator calculator = null;

        if (request.getParameter("number1") != null) {  //第一次访问，没有从表单接值

            Map<String, String[]> parameterMap = request.getParameterMap();

            calculator = new Calculator();

            try {
                BeanUtils.copyProperties(calculator, parameterMap);
            } catch (IllegalAccessException e) {
                logger.error("表单beanutil异常！" + e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error("表单beanutil异常！" + e.getMessage());
            }

            Float result = calculatorService.oper(calculator);
            String stringResult = NumberFormat.getInstance().format(result);  //判断Float的小数点 为0取整
            calculator.setResult(stringResult);

            //增加记录
            calculatorService.insert(calculator);
            calculatorService.insert2(calculator);

        }

        //历史记录列表
        List<String> recordList = calculatorService.querryAllRecord();
        List<Calculator> recordList2 = calculatorService.querryAllRecord2();

        request.setAttribute("calculator", calculator);
        request.setAttribute("recordList", recordList);
        request.setAttribute("recordList2", recordList2);

        request.getRequestDispatcher("/tool/calculator.jsp").forward(request, response);
    }

    private void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        calculatorService.clear();
        calculatorService.clear2();

        //重定向
        response.sendRedirect("/tool/calculator.jsp");
    }

}
