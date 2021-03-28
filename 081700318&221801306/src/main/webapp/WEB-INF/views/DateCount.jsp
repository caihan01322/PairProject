<%@ page import="com.example.thesisSearch.javabean.PageBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>Title</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>测试使用bootstrap框架</title>
        <!-- 引入jQuery -->
        <script type="text/javascript" src="static/js/jquery-3.6.0.min.js"></script>
        <!-- 引入样式 -->
        <link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
        <link href="static/css/myEdit.css" rel="stylesheet">
        <script src="static/echart/echarts.min.js"></script>
        <script src="static/js/charts.js"></script>
    </head>

<body>
<div id="info"></div>
    <div  class='main row'>
        <div class="col-md-8 col-sm-offset-2">
        <div id="chart1" style="width: 1200px;height:800px">
        </div>
        </div>
        </div>
    </div>
    <nav class="navbar navbar-default navbar-fixed-bottom">
        <div class="container">
            <p class="navbar-text navbar-right"> <a href="#" class="navbar-link">copyright@aaagx</a></p>
        </div>
    </nav>
</body>
</html>
