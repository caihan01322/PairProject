<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>热门领域</title>
    <link rel="stylesheet" type="text/css" href="css/indexmain.css"/>
</head>
<body>
<div>
    <div class="head">
        <div class="head1">
            <h1>论文信息平台</h1>
        </div>
        <div class="head2">
        	<a><label>用户名</label></a>
        	<a><img src="./ImageResources/user.png"/></a>
        	<a><img src="./ImageResources/question.png"/></a>
        	<a><img src="./ImageResources/bell.png" /></a>
        	<a><img src="./ImageResources/link.png" /></a>
            <a><img src="./ImageResources/redo.png" /></a>
        </div>
    </div>
</div>
<div class="content">
    <div>
        <div class="contentleft">
            <ul>
                 <li class="icon"><a href="index_one_add.jsp"><i><img src="./ImageResources/folderadd.png"></i>论文爬取</a></li>
                 <li class="icon"><a href="index_two_edit.jsp"><i><img src="./ImageResources/database.png"></i>论文管理</a></li>
                 <li class="icon">
                 	<div><a href="index.jsp"><i><img src="./ImageResources/fund.png"></i>论文分析</a></div>
                 	<div class="titleright"><a href="index_three_chart.jsp">图表分析</a></div>
                 	<div class="titleright"><a href="index_three_hot.jsp">热门领域</a></div>
                 </li>
                 <li class="icon"><a href="index_four.jsp"><i><img src="./ImageResources/read.png"></i>背景知识</a></li>
            </ul>
        </div>
    </div>
</div>
<div>
	<div class="footer">
		<label>Copyright © 2021 </label>
	</div>
	<div class="footer">
		<label>Powered by .NET 5.0 on Kubernetes & Theme Silence v3.0.0</label>
	</div>
</div>
</body>
</html>