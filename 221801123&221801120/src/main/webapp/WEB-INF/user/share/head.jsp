<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("basePath", basePath);
%>

<link rel="stylesheet" href="css/head.css">

<div class="header">
    <div class="logo">
        <font class="logofont">Crawler</font>
    </div>
    <!-- 导航栏 -->
    <div class="navbar">
        <ul>
            <li><a href="#">首页</a></li>
            <li><a href="#">论文列表</a></li>
            <li><a href="#">热门分析</a></li>
            <li><a href="#">我的收藏</a></li>
            <li><a href="#">帮助</a></li>
        </ul>
    </div>
    <!-- 搜索 -->
    <div class="search">
        <input type="text" placeholder="请输入关键词">
        <button>🔍</button>
    </div>
    <!-- 用户 -->
    <div class="user">
        <img src="" alt="">用户信息</img>
    </div>
</div>
