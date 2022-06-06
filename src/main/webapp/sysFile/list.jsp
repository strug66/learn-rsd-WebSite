<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>文件上传管理</title>
        <script>
            function toAddPage() {
                window.location.href = "/sysFile/add.jsp";
            }

            function toUpdatePage(id) {
                window.location.href = "/sysFile/toUpdatePage?id=" + id;

            }

            function doDelete(id) {
                if (confirm("您确定删除吗？")) {
                    window.location.href = "/sysFile/doDelete?id=" + id;
                }
            }

            function download(id) {
                window.location.href = "/sysFile/download?id=" + id;
            }
        </script>
    </head>
    <body>
        <h2>文件上传管理</h2>
        <a href="javascript:toAddPage()">添加</a>

        <table border="1" width="100%">
            <tr>
                <th>序号</th>
                <th>文件名称</th>
                <th>路径</th>
                <th>文件大小</th>
                <th>上传时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${sysFiles}" var="sysFile">
                <tr>
                    <td>${sysFile.id}</td>
                    <td>${sysFile.name}</td>
                    <td>${sysFile.path}</td>
                    <td>${sysFile.fileSize}K</td>
                    <td>${sysFile.createTime}</td>
                    <td>
                        <input type="button" value="下载" onclick="download(${sysFile.id})">

                        <input type="button" value="修改" onclick="toUpdatePage(${sysFile.id})">
                        <input type="button" value="删除" onclick="doDelete(${sysFile.id})">
                    </td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>