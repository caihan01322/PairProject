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
                        <th class="col-lg-5">标题</th>
                        <th class="col-lg-1">来源</th>
                        <th class="col-lg-1">年份</th>
                        <th class="col-lg-2">操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pageBean.records}" var="thesis">
                        <tr>
                            <td hidden>${thesis.id}</td>
                            <td>${thesis.title}</td>
                            <td>${thesis.meet}</td>
                            <td>${thesis.year}</td>
                            <td hidden>${thesis.keyword}</td>
                            <td hidden>${thesis.abstractContent}</td>
                            <td hidden>${thesis.link}</td>
                            <td>
                                <button class="btn-info" onclick="LookCard(this)">查看</button>
                                <button class="btn-info" onclick="EditCard(this);">编辑</button>
                                <button class="btn-danger" onclick="DeleteCard(this);">删除</button>
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
                            <dt><span class="buleFont">*</span>论文题目</dt>
                            <dd><textarea id="thesis_view_title" class="eidtText" name="title" readonly></textarea></dd>
                            <dt><span class="buleFont">*</span>来源</dt>
                            <dd><textarea id="thesis_view_meet" class="eidtText" name="meet" readonly></textarea></dd>
                            <dt><span class="buleFont">*</span>时间</dt>
                            <dd><textarea id="thesis_view_year" class="eidtText" name="year"  readonly></textarea></dd>
                            <dt><span class="buleFont">*</span>关键词</dt>
                            <dd><textarea id="thesis_view_keyword" class="eidtText bigText" name="keyword" readonly></textarea></dd>
                            <dt><span class="buleFont">*</span>论文摘要</dt>
                            <dd><textarea id="thesis_view_abstractContent" class="eidtText bigText" name="abstract" readonly></textarea></dd>
                            <dt><span class="buleFont">*</span>论文链接</dt>
                            <dd><textarea id="thesis_view_link" class="eidtText" name="link" readonly></textarea></dd>
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
                        <form action="" method="post" onsubmit="return editThesis(this);">
                            <dl>
                                <dt hidden>论文编号</dt>
                                <dd hidden><input id="thesis_edit_id" class="eidtText" name="id" type="text" ></dd>
                                <dt><span class="redFont">*</span>论文题目</dt>
                                <dd><input id="thesis_edit_title" class="eidtText" name="title" type="text" ></dd>
                                <dt><span class="redFont">*</span>来源</dt>
                                <dd><input id="thesis_edit_meet" class="eidtText" type="text" name="meet" ></dd>
                                <dt><span class="redFont">*</span>时间</dt>
                                <dd><input id="thesis_edit_year" class="eidtText" type="text" name="year"  ></dd>
                                <dt><span class="redFont">*</span>关键词</dt>
                                <dd><input id="thesis_edit_keyword" class="eidtText" type="text" name="keyword"  ></dd>
                                <dt><span class="redFont">*</span>论文摘要</dt>
                                <dd><input id="thesis_edit_abstractContent" class="eidtText" type="text" name="abstract" ></dd>
                                <dt><span class="redFont">*</span>论文链接</dt>
                                <dd><input id="thesis_edit_link" class="eidtText" type="text" name="link" ></dd>
                            </dl>
                            <input class="btn-danger middleBtn btn-lg" type="submit" value="确认修改">
                            <span class="error" id="thesis_edit_error">&nbsp;</span>
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
                        <div class="alertFont">您确认要删除这篇论文吗？</div>
                        <div class="middleDiv">
                            <form>
                                <input class="btn-info btn-lg" type="submit" value="确认">
                                <input class="btn-danger btn-lg" type="submit" value="取消">
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
<script src="<%=basePath%>script/tips.js"></script>
</html>
