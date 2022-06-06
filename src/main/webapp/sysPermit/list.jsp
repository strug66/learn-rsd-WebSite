<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <heaad>
        <title>系统权限列表</title>
    </heaad>

    <body>
        <h2>系统权限列表</h2>
        <table border="1" width="100%">
            <tr>
                <th>序号</th>
                <th>角色</th>
                <th>功能</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${list}" var="sysPermit" varStatus="i">
                <tr>
                    <td>${i.count+(page-1)*pageSize}</td>
                    <td>${sysPermit.roleName}</td>
                    <td>${sysPermit.functionName}</td>
                    <td>
                        <a href="/sysPermit/doDelete?id=${sysPermit.id}">删除</a>
                    </td>
                </tr>

            </c:forEach>

            <tr align="center">
                <td colspan="4">
                    <a href="/sysPermit/list?page=1">首页</a>
                    <a href="/sysPermit/list?page=${page-1}">&lt;&lt;</a>
                    <c:forEach begin="1" end="${pageCount}" var="i">
                        <a href="/sysPermit/list?page=${i}">${i}</a>
                    </c:forEach>
                    <a href="/sysPermit/list?page=${page+1}">&gt;&gt;</a>
                    <a href="/sysPermit/list?page=${pageCount}">末页</a>
                </td>
            </tr>

        </table>
    </body>
</html>