<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>计算器</title>
    </head>

    <body>
        <h2>计算器</h2>
        <form action="/tool/calculator" method="post">
            <table border="1">
                <tr>
                    <td>
                        <input type="text" name="number1" value="${calculator.number1}"/>
                    </td>

                    <td>
                        <select name="operator">
                            <option value="+"  ${calculator.operator=="+" ? "selected":""}>+</option>
                            <option value="-"  ${calculator.operator=="-" ? "selected":""}>-</option>
                            <option value="*"  ${calculator.operator=="*" ? "selected":""}>*</option>
                            <option value="/"  ${calculator.operator=="/" ? "selected":""}>/</option>
                        </select>
                    </td>

                    <td>
                        <input type="text" name="number2" value="${calculator.number2}"/>
                    </td>

                    <td>
                        <input type="submit" value="=">
                    </td>

                    <td>
                        <input type="input" name="result" value="${calculator.result}"/>
                    </td>
                </tr>
            </table>
        </form>

        历史记录1：<br>
        <ul>
            <c:forEach items="${recordList}" var="record">
                <li>${record}</li>
                <%--            <br>换行--%>
            </c:forEach>
        </ul>


        历史记录2：<br>
        <ul>
            <c:forEach items="${recordList2}" var="record2">
                <li>${record2.number1} ${record2.operator} ${record2.number2} = ${record2.result}</li>
            </c:forEach>
        </ul>

        <a href="/tool/clearHistory">清空历史记录</a>
    </body>
</html>