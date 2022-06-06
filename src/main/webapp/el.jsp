<%@page contentType="text/html; charset=utf-8" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>jsp学习</title>
    </head>

    <body>
        <jstl:set var="display" value="111"></jstl:set>
        ${display}

        <jstl:if test="true">
            222
        </jstl:if>

    </body>
</html>