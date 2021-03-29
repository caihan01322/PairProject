<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>论文列表</title>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/list_main.css">
    <link rel="stylesheet" href="css/thesis_list.css">

</head>
<%-- 头部 --%>
<jsp:include page="share/head.jsp"/>

<body>
    <div class="list">
        <!-- 论文列表 -->
        <div>
            <table>
                <thead>
                    <tr>
                        <th>题名</th>
                        <th>关键词</th>
                        <th>原文链接</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pageBean.records}" var="thesis">
                        <tr>
                            <td><div class="articleDiv"><p class="articleFont">${thesis.id}</p></div></td>
                            <td>${thesis.keyword}</td>
                            <td>${thesis.link}</td>
                            <td>
                                <a href="list.html">查看</a><a href="list.html">编辑</a><a href="list.html">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!--分页-->
        <div class="page">
            <script type="text/javascript">
                function page(pageCode) {
                    window.location.href = "${pageContext.request.contextPath}/user/thesis/list?pn=" + pageCode;
                }
            </script>
            <jsp:include page="../share/page.jsp"></jsp:include>
        </div>
    </div>
<jsp:include page="share/footer.jsp"/>
</body>
<script type="text/javascript" src="script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</html>
