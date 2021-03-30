<%@ page import="com.example.thesisSearch.javabean.PageBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>Title</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>统计页面</title>
        <!-- 引入jQuery -->
        <script type="text/javascript" src="static/js/jquery-3.6.0.min.js"></script>
        <!-- 引入样式 -->
        <link href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
        <link href="static/css/myEdit.css" rel="stylesheet">
        <script src="static/echart/echarts.min.js"></script>
        <script type="text/javascript" src="static/echart/echarts-wordcloud.min.js"></script>
        <script src="static/js/charts.js"></script>
    </head>

<body>
<div id="info"></div>
    <div  class='main row'>
        <div class="col-md-4 col-lg-offset-0 ">
        <div id="chart1" style="width: 800px;height:600px">
        </div>
        </div>
    <div class="col-md-4 col-lg-offset-2 ">
        <div id="chart2" style="width: 800px;height:600px">
        </div>
    </div>
    <div class="col-md-10 col-lg-offset-0 ">
        <div class="col-lg-4 col-lg-offset-2">
            <form class="form-horizontal" action="Search" method="GET">
                <div class="form-group">
                    <select class="form-control  thesis-select col-sm-4" id="Year-Select" type="text">
                        <option>2007</option>
                        <option >2009</option>
                        <option >2011</option>
                        <option >2013</option>
                        <option >2015</option>
                        <option >2017</option>
                        <option >2019</option>
                    </select>
                    <select class="form-control  thesis-select col-sm-4" id="meeting-Select" type="text">
                        <option >ICCV</option>
                        <option >ECCV</option>
                        <option >CVPR</option>
                    </select>
                    <button type="button" class="btn btn-default col-sm-2 chart-button">确定</button>
                </div>
            </form>
    </div>
    </div>


    <nav class="navbar navbar-default navbar-fixed-bottom">
        <div class="container">
            <p class="navbar-text navbar-right"> <a href="#" class="navbar-link">copyright@aaagx</a></p>
        </div>
    </nav>
</body>
</html>
