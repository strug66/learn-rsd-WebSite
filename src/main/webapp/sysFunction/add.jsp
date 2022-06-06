<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>添加系统功能</title>
    </head>

    <body>
        <h2>添加系统功能</h2>
        <form action="/sysFunction/doAdd" method="post">
            <table border="1">
                <tr>
                    <td>系统功能:</td>
                    <td><input type="text" name="name"/></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit"/>
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>