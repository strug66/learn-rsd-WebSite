<%@page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <title>后台登录</title>

        <script>
            function checkForm() {
                var loginName = document.getElementById("loginName");
                var password = window.document.getElementById("password");

                if (loginName.value == "") {
                    alert("登录名不能为空！");
                    return;
                }
                if (password.value == "") {
                    alert("密码不能为空！");
                    return;
                }

                var loginForm = document.getElementById("loginForm");
                loginForm.submit();
            }

        </script>

    </head>

    <body>
        <div style="margin-top: 100px;text-align: center">
            <h3>登录</h3>

            <form id="loginForm" action="/login" method="post">
                <table border="1" align="center">
                    <tr>
                        <td>用户名：</td>
                        <td><input type="text" name="loginName" id="loginName"/></td>
                    </tr>

                    <tr>
                        <td>密&emsp;码：</td>
                        <td><input type="password" name="password" id="password"/></td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <input type="button" value="登录" onclick="checkForm();"/>
                            <input type="reset"/>
                        </td>
                    </tr>

                </table>
                <div style="color: red;">${loginResult}</div>
            </form>

        </div>
    </body>
</html>