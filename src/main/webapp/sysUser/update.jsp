<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>修改系统用户</title>
    </head>

    <body>
        <h2>修改系统用户</h2>

        <form action="/sysUser/doUpdate" method="post" enctype="multipart/form-data">

            <input type="hidden" name="id" value="${user.id}"/>

            <table border="1">
                <tr>
                    <td>登录名</td>
                    <td>
                        <input type="text" name="loginName" value="${user.loginName}"/>
                    </td>
                </tr>

                <tr>
                    <td>登录密码</td>
                    <td>
                        <input type="password" name="password" value="${user.password}"/>
                    </td>
                </tr>

                <tr>
                    <td>真实姓名</td>
                    <td>
                        <input type="text" name="realName" value="${user.realName}"/>
                    </td>
                </tr>

                <tr>
                    <td>头像</td>
                    <td>
                        <img src="${user.headPicPath}" width="100px" height="100px"/>
                        <input type="file" name="headPicPath" id="headPicPath"/>
                    </td>
                </tr>

                <tr>
                    <td>系统角色</td>
                    <td>
                        <select name="roleId">
                            <jstl:forEach items="${roleList}" var="role">
                                <option value="${role.id}" ${user.roleId == role.id ? "selected" : ""}>${role.name}</option>
                            </jstl:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>性别</td>
                    <td>
                        <input type="radio" name="sex" value="1" ${user.sex == "男" ? "checked" : ""}/>男
                        <input type="radio" name="sex" value="0" ${user.sex == "女" ? "checked" : ""}/>女
                    </td>
                </tr>

                <tr>
                    <td>电话</td>
                    <td>
                        <input type="text" name="tel" value="${user.tel}"/>
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