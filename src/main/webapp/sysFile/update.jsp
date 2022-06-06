<%@page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <title>文件上传修改</title>
    </head>
    <body>
        <h2>文件上传修改</h2>
        <form action="/sysFile/doUpdate" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${sysFile.id}"> //只用到id  文件没有value属性
            <input type="file" name="myFile">
            <input type="submit">

        </form>
    </body>
</html>