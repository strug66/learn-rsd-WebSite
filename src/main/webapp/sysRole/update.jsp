<%@page contentType="text/html; charset=utf-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>修改系统角色</title>
    </head>

    <body>
        <h2>修改系统角色</h2>

        <form action="/sysRole/doUpdate" method="post">
            <input type="hidden" name="id" value="${sysRole.id}"/>
            <table border="1">
                <tr>
                    <td>角色名称：</td>
                    <td><input type="text" name="name" value="${sysRole.name}"/></td>
                </tr>

                <tr>
                    <th>选择功能：</th>
                    <th>
                        <c:forEach items="${sysFunctionList}" var="sysFunction">
                            <c:set var="ischecked" value="false"/>
                            <c:forEach items="${sysRole.sysFunctionIds}" var="sysFunctionId">
                                <c:if test="${sysFunctionId == sysFunction.id}">
                                    <c:set var="ischecked" value="true"/>
                                </c:if>
                            </c:forEach>
                            <input type="checkbox" name="sysFunctionId"
                                   value="${sysFunction.id}" ${ischecked?"checked":""} />${sysFunction.name}

                            <%--                            <input type="checkbox" name="sysFunctionId" value="${sysFunction.id}" ${sysFunction.ischecked?"checked":""}>${sysFunction.name}--%>

                        </c:forEach>
                    </th>
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