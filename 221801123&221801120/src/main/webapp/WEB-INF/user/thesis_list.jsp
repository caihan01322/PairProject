<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>论文列表</title>
    <link rel="stylesheet" href="/css/thesis_list.css">
</head>
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
                    <c:forEach items="${pageBean}" var="thesis">
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
    </div>
<jsp:include page="share/footer.jsp"/>
</body>
</html>
