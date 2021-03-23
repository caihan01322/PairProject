<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./index.css">
</head>
<body>
    <div class="nav">
        <div class="logo">
            Paper管理平台
        </div>
        <div class="items">
        <a class="btn_head_active" href="Head.jsp">首页</a>
        <a class="btn_manage" href="Delete.jsp">论文管理</a>
        <a class="btn_analysis" href="Analysis.jsp">论文分析</a>
        </div>
    </div>
    <div class="header">
        <div class="title">
            论文列表
        </div>
        <div class="search">
        <input type="text" class="text_search" placeholder="请输入要查询的论文"/>
        <a class="btn_search" href="Search.jsp">搜索</a>
        </div>
    </div>
    <div class="content">
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
    </div>
    <div class="title_recommend">
        为您推荐以下论文
    </div>
    <div class="line"></div>
    <div class="content">
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
        <div class="block">标题<br>摘要<br>关键词</div>
    </div>
</body>
</html>