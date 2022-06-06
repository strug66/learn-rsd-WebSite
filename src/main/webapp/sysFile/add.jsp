<%@page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <title>新增文件上传</title>
    </head>
    <body>
        <h2>新增文件上传</h2>
        <form action="/sysFile/doAdd" method="post" enctype="multipart/form-data">

            <input type="file" name="myFile">
            <input type="submit">

        </form>
    </body>
</html>