<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body style="background-color: #ecbeea">

    <ul>

        <c:forEach items="${sysFunctionList}" var="sysFunction">
            <li><a href="${sysFunction.url}" target="view">${sysFunction.name}</a></li>
        </c:forEach>
<%--        <li><a href="/sysFile/list" target="view">文件上传</a></li>--%>
        <li><a href="/sysFile/list2.html" target="view">文件上传</a></li>
        <li><a href="/tool/random.jsp" target="view">随机点名</a></li>
        <li><a href="/tool/regex.html" target="view">正则验证</a></li>
        <li><a href="/newsInfo/list2.html" target="view">新闻信息</a></li>
    </ul>

</body>