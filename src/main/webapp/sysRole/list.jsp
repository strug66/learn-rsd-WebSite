<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>系统角色列表</title>
    </head>
    <body>
        <h2>系统角色列表</h2>
        <a href="/sysRole/toAddPage">添加</a>
        <table border="1" width="100%">
            <tr>
                <th>序号</th>
                <th>名字</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${list}" var="role" varStatus="i">
                <tr align="center">
                    <td>${i.count + (page-1)*pageSize}</td>
                    <td>${role.name}</td>
                    <td><fmt:formatDate value="${role.createTime}" pattern="YYYY-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a href="/sysRole/toUpdatePage?id=${role.id}">修改</a>
                        <a href="/sysRole/doDelete?id=${role.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>

            <tr align="center">
                <td colspan="4">
                    <a href="/sysRole/list?page=1">首页</a>
                    <a href="/sysRole/list?page=${page-1}">&lt;&lt;</a>
                    <c:forEach var="i" begin="1" end="${pageCount}">
                        <a href="/sysRole/list?page=${i}">${i}</a>
                    </c:forEach>
                    <a href="/sysRole/list?page=${page+1}">&gt;&gt;</a>
                    <a href="/sysRole/list?page=${pageCount}">末页</a>
                </td>
            </tr>

        </table>
    </body>
</html>