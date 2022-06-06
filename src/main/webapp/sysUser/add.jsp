<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>新增系统用户</title>
        <script>
            function checkForm() {
                var loginName = document.getElementById("loginName");
                var password = document.getElementById("password");
                var realName = document.getElementById("realName");
                var roleId = document.getElementById("roleId");

                var sex = document.getElementsByName("sex");
                var flag = false;
                for (var i = 0; i < sex.length; i++) {
                    if (sex[i].checked) {  //单选框选中
                        flag = true;
                    }
                }

                if (loginName.value == "") {
                    alert("登录名不能为空！");
                    return;
                }
                if (password.value == "") {
                    alert("密码不能为空！");
                    return;
                }
                if (realName.value == "") {
                    alert("真实姓名不能为空！");
                    return;
                }

                if (roleId.value == "") {
                    alert("请选择角色！");
                    return;
                }

                if (flag == false) {
                    alert("请选择性别！");
                    return;
                }

                var sysUserForm = document.getElementById("sysUserForm");
                sysUserForm.submit();
            }
        </script>
    </head>

    <body>
        <h2>新增系统用户</h2>

        <form id="sysUserForm" action="/sysUser/doAdd" method="post" enctype="multipart/form-data">
            <table border="1">
                <tr>
                    <td>登录名</td>
                    <td>
                        <input type="text" name="loginName" id="loginName"/>
                    </td>
                </tr>

                <tr>
                    <td>登录密码</td>
                    <td>
                        <input type="password" name="password" id="password"/>
                    </td>
                </tr>

                <tr>
                    <td>真实姓名</td>
                    <td>
                        <input type="text" name="realName" id="realName"/>
                    </td>
                </tr>

                <tr>
                    <td>头像</td>
                    <td>
                        <input type="file" name="headPicPath" id="headPicPath"/>
                    </td>
                </tr>

                <tr>
                    <td>系统角色</td>
                    <td>
                        <select name="roleId" id="roleId">
                            <option value="">请选择角色</option>
                            <jstl:forEach items="${list}" var="role">
                                <option value="${role.id}">${role.name}</option>
                            </jstl:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>性别</td>
                    <td>
                        <input type="radio" name="sex" value="1"/>男
                        <input type="radio" name="sex" value="0"/>女
                    </td>
                </tr>

                <tr>
                    <td>电话</td>
                    <td>
                        <input type="text" name="tel"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="button" value="提交" onclick="checkForm();"/>
                    </td>
                </tr>

            </table>
        </form>

    </body>
</html>