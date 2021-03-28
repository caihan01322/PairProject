<%@ page import="java.util.List" %>
<%@ page import="com.example.thesisSearch.pojo.Thesis" %>
<%@ page import="com.example.thesisSearch.javabean.PageBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
</html>
<html lang="zh-CN">
<head>
    <title>Title</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>搜索结果</title>
        <!-- 引入jQuery -->
        <script type="text/javascript" src="static/js/jquery-3.6.0.min.js"></script>
        <!-- 引入样式 -->
        <link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
        <link href="static/css/myEdit.css" rel="stylesheet">
        <!-- 引入js -->
        <script src="static/js/star.js"></script>
    </head>

<body>

<div  class='main'>

    <div class="row search-bar">
        <div class="col-lg-4 col-lg-offset-4">
            <form class="form-horizontal" action="Like" method="GET">
                <div class="form-group">
                    <input type="text" class="col-sm-4 form-control thesis-input " aria-label="..." name="input">
                    <button type="submit" class="btn btn-default col-sm-2">搜索</button>
                </div>
            </form>
        </div>

    <div class="container-fluid">
        <ul class="list-group col-lg-8 col-lg-offset-2" >
            <%
             List<Thesis> result= (List<Thesis>) request.getAttribute("result");
            if(result.size()!=0)
                {
                for(Thesis i:result)
                    {%>
                    <li class="list-group-item">
                        <h3><a  href="<%=i.getLink()%>"><%=i.getTitle()%></a></h3>
                        <div class='thesis-content'><%=i.getAbstractContent()%>
                        </div>
                        <div class='thesis-content'><%=i.getKeyword()%>
                        </div>
                        <%if(i.isIsliked()){%>
                        <a class="btn btn-default thesis-star star-y" ThesisId="<%=i.getId()%>"  ole="button">已收藏</a>
                        <%}else{ %>
                        <a class="btn btn-default thesis-star star-n" ThesisId="<%=i.getId()%>" role="button">收藏</a>
                        <%} %>
                    </li>
                    <%}
                }%>
        </ul>
    </div>
    <nav class="navbar navbar-default ">
        <div class="container">
            <p class="navbar-text navbar-right"> <a href="#" class="navbar-link">copyright@aaagx</a></p>
        </div>
    </nav>
</body>
</html>
