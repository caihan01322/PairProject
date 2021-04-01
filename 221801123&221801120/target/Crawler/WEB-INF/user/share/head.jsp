<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<div class="header">
    <div class="logo">
        <font class="logofont">Crawler</font>
    </div>
    <!-- 导航栏 -->
    <div class="navbar">
        <ul>
            <li><a href="user/index">首页</a></li>
            <li><a href="user/thesis/list">论文列表</a></li>
            <li><a href="user/statistics">热门分析</a></li>
            <li><a href="user/trend">趋势</a></li>
            <li><a href="user/mylove">我的收藏</a></li>
        </ul>
    </div>
    <!-- 搜索 -->
    <div class="search">
        <input id="input_search" type="text" placeholder="请输入关键词">
        <button onclick="search(this);">🔍</button>
    </div>
    <script>
        function search(btn) {
            var keyword = document.getElementById("input_search");
            window.location.href = "user/thesis/list?keyword="+(keyword.value);
        }
    </script>
    <!-- 用户 -->
    <div class="user">
        <a href="user/logout">注销</a>
    </div>
</div>
