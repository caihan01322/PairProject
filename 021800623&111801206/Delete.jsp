<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <a class="btn_head" href="DoUserSelect?">首页</a>
        <a class="btn_manage_active" href="DoDeleteSelect?">论文管理</a>
        <a class="btn_analysis" href="DoICCVSelect">论文分析</a>
        </div>
    </div>
    <div class="header">
        <div class="title">
            论文删除
        </div>
        <div class="search">
        <form action="DoDeleteSelect" method="post">
        <input type="text" name="condition" class="text_search" placeholder="请输入要查询的论文"/>
        <input type="submit" value="搜索" class="btn_search"/>
        </form>
        </div>
    </div>
    <div class="line"></div>
    <div class="content_delete">
        <c:forEach var="p" items="${paperList}">
	        <div class="block_delete">
		        <a class="title_a">标题:
		        </a>${p.title}<br>
		        <a class="conclude">摘要:</a>
		        <div class=conclude_content>${p.abstractString}</div>
		        <a class="keyword">关键词:</a>
		        <div class="word">
			        ${p.keywords.size()>0?p.keywords.get(0):null},
			        ${p.keywords.size()>1?p.keywords.get(1):null},
			        ${p.keywords.size()>2?p.keywords.get(2):null}
		        </div>
		        <form action="DoDeletePaperSelect" method="post">
		        <input type="hidden" value= "${p.academicNum}" name="id"/>
		        <input type="submit" value="删除" class="btn_delete"/>
		        </form>
	    	</div>
    	</c:forEach>
    	<div class="page">
    	共${tsum}条记录，当前在${cpage}/${tpage}页
    	<a class="btn_page" href="DoDeleteSelect?cp=1">首页</a>
    	<a class="btn_page" href="DoDeleteSelect?cp=${cpage>1?cpage-1:1}">上一页</a>
    	<a class="btn_page" href="DoDeleteSelect?cp=${cpage>tpage-1?tpage:cpage+1}">下一页</a>
    	<a class="btn_page" href="DoDeleteSelect?cp=${tpage}">尾页</a>
    </div>
    </div>
</body>
<script>
    
</script>
</html>