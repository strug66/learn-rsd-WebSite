<%@page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <title>修改系统功能</title>
    </head>

    <body>

        <h2>修改系统功能</h2>

        <form action="/sysFunction/doUpdate" method="post">
            <input type="hidden" name="id" value="${sysFunction.id}"/>
<%--            修改  根据id查找修改的对象--%>
            <table border="1">
                <tr>
                    <td>系统功能</td>
                    <td>
                        <input type="text" name="name" value="${sysFunction.name}"/>
                    </td>
                </tr>

                <tr>
                    <td  colspan="2">
                        <input type="submit" />
                    </td>
                </tr>

            </table>
        </form>

    </body>
</html>