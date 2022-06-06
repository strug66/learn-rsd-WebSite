<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>系统功能列表</title>
    </head>

    <body>
        <h2>系统功能列表</h2>

        <a href="/sysFunction/toAddPage">添加</a>

        <form action="/sysFunction/list" method="post">
            <table border="1" width="100%">
                <tr>
                    <th>序号</th>
                    <th>系统功能</th>
                    <th>操作</th>
                </tr>

                <jstl:forEach items="${list}" var="sysFunction" varStatus="i">
                    <tr align="center">
                        <td>${i.count + (page-1)*pageSize}</td>
                        <td>${sysFunction.name}</td>
                        <td>
                            <a href="/sysFunction/toUpdatePage?id=${sysFunction.id}">修改</a>
                            <a href="/sysFunction/doDelete?id=${sysFunction.id}">删除</a>
                        </td>
                    </tr>

                </jstl:forEach>

                <tr align="center">
                    <td colspan="3">
                        <a href="/sysFunction/list?page=1">首页</a>
                        <a href="/sysFunction/list?page=${page-1}">&lt;&lt;</a>
                        <jstl:forEach var="i" begin="1" end="${pageCount}">
                            <a href="/sysFunction/list?page=${i}">${i}</a>
                        </jstl:forEach>
                        <a href="/sysFunction/list?page=${page+1}">&gt;&gt;</a>
                        <a href="/sysFunction/list?page=${pageCount}">末页</a>
                    </td>
                </tr>
            </table>

        </form>
    </body>
</html>