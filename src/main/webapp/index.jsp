<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>我的项目</title>
    </head>

    <frameSet rows="75px,*,24px" frameborder="no" framespacing="0" border="0">
        <frame src="top.jsp" scrolling="no">

        <frameSet cols="160px,*">
            <frame src="/servlet/menu">
            <frame src="main.jsp" name="view">
        </frameSet>

        <frame src="bottom.jsp" scrolling="no">
    </frameSet>

    <body>

    </body>
</html>