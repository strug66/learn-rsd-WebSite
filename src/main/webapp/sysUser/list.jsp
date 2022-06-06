<%@page contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
    <head>
        <title>系统用户列表</title>

        <script>
            function toAddPage() {
                window.location.href = "/sysUser/toAddPage";
            }

            function doDel(id) {
                if (confirm("确定删除吗？")) {
                    window.location.href = "/sysUser/doDelete?id=" + id;
                }
            }

        </script>
    </head>
    <body>
        <h2>系统用户列表</h2>
        <a href="javascript:toAddPage()">添加</a>

        <table>
            <tr>
                <td>
                    <form action="/sysUser/toList" method="post">
                        <input type="search" name="searchLoginName" title="请输入账号" placeholder="账号"
                               value="${searchSysUser.loginName}"/>
                        <input type="search" name="searchRealName" title="请输入姓名" placeholder="姓名"
                               value="${searchSysUser.realName}"/>
                        <input type="search" name="searchTel" title="请输入电话" placeholder="电话"
                               value="${searchSysUser.tel}"/>

                        <select name="searchRoleId">
                            <option value="">所有角色</option>
                            <c:forEach items="${sysRoleList}" var="sysRole">
                                <option value="${sysRole.id}"  ${sysRole.id==searchSysUser.roleId ? "selected":""}>${sysRole.name}</option>
                            </c:forEach>
                        </select>

                        <select name="searchSex">
                            <option value="">所有性别</option>
                            <option value="1"  ${searchSysUser.sex=="1" ? "selected":""}>男</option>
                            <option value="0"  ${searchSysUser.sex=="0" ? "selected":""}>女</option>
                        </select>


                        开始时间：<input type="date" name="searchStartTime" value="${searchSysUser.searchStartTime}">
                        结束时间：<input type="date" name="searchEndTime" value="${searchSysUser.searchEndTime}">

                        <input type="submit" value="搜索">
                    </form>
                </td>
            </tr>
        </table>


        <table border="1" width="100%">
            <tr>
                <th>序号</th>
                <th>账号</th>
                <th>姓名</th>
                <th>系统角色</th>
                <th>性别</th>
                <th>电话</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${list}" var="sysUser" varStatus="i">
                <tr>
                    <td>${i.count + (page-1)*pageSize}</td>
                    <td>${sysUser.loginName}</td>
                    <td>${sysUser.realName}</td>
                    <td>${sysUser.roleName}</td>
                    <td>${sysUser.sex}</td>
                    <td>${sysUser.tel}</td>
                    <td><fmt:formatDate value="${sysUser.createTime}" pattern="YYYY-MM-dd HH:mm:ss"/></td>
                    <td>
                        <a href="/sysUser/toUpdatePage?id=${sysUser.id}">修改</a>

                            <%--                        <a href="/sysUser/doDelete?id=${sysUser.id}">删除</a>--%>
                        <a href="javascript:doDel(${sysUser.id})">删除</a>

                        <a href="/sysUser/resetPassword.jsp?id=${sysUser.id}">重置密码</a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${!(pageCount eq null)}">
                <tr align="center">
                    <td colspan="8">
                        <a href="/sysUser/list?page=1">首页</a>
                        <a href="/sysUser/list?page=${page-1}">&lt;&lt;</a>
                        <c:forEach var="i" begin="1" end="${pageCount}">
                            <a href="/sysUser/list?page=${i}">${i}</a>
                        </c:forEach>
                        <a href="/sysUser/list?page=${page+1}">&gt;&gt;</a>
                        <a href="/sysUser/list?page=${pageCount}">末页</a>
                    </td>
                </tr>
            </c:if>


        </table>

        <div style="color: #a85ea5">${resetPwdResult}</div>
    </body>
</html>