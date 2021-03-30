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
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/list_main.css">
    <link rel="stylesheet" href="css/thesis_list.css">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/footer.css">

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
                            <td>${thesis.title}</td>
                            <td>${thesis.link}</td>
                            <td>
                                <button class="btn-info" onclick="LookCard()">查看</button>
                                <button class="btn-info" onclick="EditCard()">编辑</button>
                                <button class="btn-danger" onclick="DeleteCard()">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- 查看 -->
            <div class="lookCard card-show col-lg-6">
                <div class="card">
                    <div class="card-header">
                        论文信息
                        <span class="close">&times;</span>
                    </div>
                    <div class="card-body pre-scrollable">
                        <dl>
                            <dt>论文题目</dt>
                            <dd>这是论文题目</dd>
                            <dt>关键词</dt>
                            <dd>这是关键词</dd>
                            <dt>论文摘要</dt>
                            <dd>这是论文摘要</dd>
                            <dt>论文链接</dt>
                            <dd><a href="#">这是论文链接</a></dd>
                        </dl>
                    </div>
                    <div class="card-footer"></div>
                </div>
            </div>
            <!-- 编辑 -->
            <div class="editCard card-show col-lg-6">
                <div class="card">
                    <div class="card-header">
                        编辑论文
                        <span class="close">&times;</span>
                    </div>
                    <div class="card-body pre-scrollable">
                        <form>
                            <dl>
                                <dt>论文题目</dt>
                                <dd><input class="eidtText" type="text" placeholder="这是论文题目"></dd>
                                <dt>关键词</dt>
                                <dd><input class="eidtText" type="text" placeholder="这是关键词"></dd>
                                <dt>论文摘要</dt>
                                <dd><input class="eidtText" type="text" placeholder="这是论文摘要"></dd>
                                <dt>论文链接</dt>
                                <dd><input class="eidtText" type="text" placeholder="这是论文链接"></dd>
                            </dl>
                            <input class="btn-danger middleBtn" type="submit" value="确认修改">
                        </form>
                    </div>
                    <div class="card-footer"></div>
                </div>
            </div>
            <!-- 删除 -->
            <div class="deleteCard card-show col-lg-4">
                <div class="card">
                    <div class="card-header">
                        删除论文
                        <span class="close">&times;</span>
                    </div>
                    <div class="card-body pre-scrollable">
                        <div>您确认删除这篇论文？</div>
                        <div class="middleDiv">
                            <form>
                                <input class="btn-info" type="submit" value="确认">
                                <input class="btn-danger" type="submit" value="取消">
                            </form>
                        </div>
                    </div>
                    <div class="card-footer"></div>
                </div>
            </div>
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
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script src="<%=basePath%>script/thesis_list.js"></script>
</html>
