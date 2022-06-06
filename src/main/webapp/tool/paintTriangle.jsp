<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>画星星</title>
    </head>

    <body>
        <h2>画星星</h2>

        <form action="/tool/paintTriangle" method="post">
            <table border="1">
                <tr>
                    <td>选择图案：</td>
                    <td colspan="2">
                        <input type="radio" name="graph" value="sanjiaoxing" ${graph=="sanjiaoxing"?"checked":""}/>直角三角形
                        <input type="radio" name="graph" value="jinzita" ${graph=="jinzita"?"checked":""}/>金字塔
                    </td>
                </tr>

                <tr>
                    <td>行数</td>
                    <td><input type="number" name="line" value="${line}"/></td>
                    <td><input type="submit"/></td>
                </tr>

                <tr>
                    <td colspan="3">
                        第1种实现方法：<br>
                        ${s}
                    </td>
                </tr>

                <tr>
                    <td colspan="3">
                        第2种实现方法：<br>
                        <c:forEach items="${starList}" var="star">
                            ${star}
                            <br>
                        </c:forEach>
                    </td>
                </tr>
            </table>

        </form>



    </body>
</html>