<%@page contentType="text/html; charset=utf-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>添加系统角色</title>

        <script>
            function checkForm() {
                var sysFunctionId = document.getElementsByName("sysFunctionId");
                var flag = false;
                for (var i = 0; i < sysFunctionId.length; i++) {
                    if (sysFunctionId[i].checked) {
                        flag = true;
                    }
                }

                if (flag == false) {
                    alert("请选择功能！")
                    return;
                }

                var sysRoleForm = document.getElementById("sysRoleForm");
                sysRoleForm.submit();

            }
        </script>
    </head>

    <body>
        <h2>添加系统角色</h2>
        <form id="sysRoleForm" action="/sysRole/doAdd" method="post">

            <table border="1">
                <tr>
                    <td>角色名称：</td>
                    <td><input type="text" name="name"/></td>
                </tr>

                <tr>
                    <td>选择功能：</td>
                    <td>
                        <c:forEach items="${sysFunctionList}" var="sysFunction">
                            <input type="checkbox" name="sysFunctionId" value="${sysFunction.id}"/>${sysFunction.name}
                        </c:forEach>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="button" value="提交" onclick="checkForm()"/>
                    </td>
                </tr>

            </table>
        </form>

    </body>
</html>