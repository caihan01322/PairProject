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
    </head>

<body>

<div  class='main'>

    <div class="row search-bar">
        <div class="col-lg-4 col-lg-offset-4">
            <form class="form-horizontal" action="Search" method="GET">
                <div class="form-group">
                    <select class="form-control  thesis-select col-sm-4" name="searchtype" type="text">
                        <option value ="all">模糊查询</option>
                        <option value ="title">题目</option>
                        <option value ="keyword">关键词</option>
                        <option value ="abstract">文章内容</option>
                    </select>
                    <input type="text" class="col-sm-4 form-control thesis-input " aria-label="..." name="input">
                    <button type="submit" class="btn btn-default col-sm-2">搜索</button>
                </div>
            </form>
        </div>

    <div class="container-fluid">
        <ul class="list-group col-lg-8 col-lg-offset-2" >
            <%PageBean Pb=(PageBean) request.getAttribute("result");
             List<Thesis> result=Pb.getList();
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
                        <a class="btn btn-default thesis-star" href="#" role="button">收藏</a>
                    </li>
                    <%}
                }%>
        </ul>
    </div>
        <%if(Pb.getTotalPage()>1)
        {%>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="Search?searchtype=<%=Pb.getSearchType()%>&input=<%=Pb.getInput()%>&pagenum=<%=Pb.getPageNum()-1<=0?0:Pb.getPageNum()-1%>" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <% for(int i=Pb.getPageNum();i<Pb.getTotalPage()&&i<Pb.getPageNum()+10;i++)
                {%>
                <li><a href="Search?searchtype=<%=Pb.getSearchType()%>&input=<%=Pb.getInput()%>&pagenum=<%=i%>"><%=i%></a></li>
                <%}%>
                <li>
                    <a href="Search?searchtype=<%=Pb.getSearchType()%>&input=<%=Pb.getInput()%>&pagenum=<%=Pb.getPageNum()+1%>" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a >
                </li>
            </ul>
        </nav>
        <%}%>
    <nav class="navbar navbar-default ">
        <div class="container">
            <p class="navbar-text navbar-right"> <a href="#" class="navbar-link">copyright@aaagx</a></p>
        </div>
    </nav>
</body>
</html>
