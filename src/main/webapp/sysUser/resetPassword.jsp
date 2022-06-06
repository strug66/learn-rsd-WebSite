<%@page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <title>重置密码</title>
    </head>

    <body>

        <form action="/loginResetPwd" method="post">
            <input type="hidden" name="id" value="${param.id}"/>

            <table border="1">
                <tr>
                    <td>原密码：</td>
                    <td>
                        <input type="password" name="password"/>
                    </td>
                </tr>

                <tr>
                    <td>新密码：</td>
                    <td>
                        <input type="password" name="newPassword"/>
                    </td>
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

