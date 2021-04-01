<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>论文详情</title>
    <link rel="stylesheet" type="text/css" href="css/indexmain.css"/>
    <link rel="stylesheet" type="text/css" href="css/tableDetail.css"/>
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
                 <li class="icon">
                 	<div><a href="index.jsp"><i><img src="./ImageResources/database.png"></i>论文管理</a></div>
                 	<div class="titleright"><a href="dopapersearch?cp=1">论文查找</a></div>
                 </li>
                 <li class="icon"><a href="DoPaperPie?year=total"><i><img src="./ImageResources/fund.png"></i>论文分析</a></li>
                 <li class="icon"><a href="index_four.jsp"><i><img src="./ImageResources/read.png"></i>背景知识</a></li>
            </ul>
        </div>
    </div>
    <div class="contentright">
    	<p>
    		<a class="button" href="dopapersearch?cp=${curpage}">返回</a>
    	</p>
    	<form action="DoPaperEdit" method="post">
    	<input class="updateBtn" id="button" type="submit" value="修改"/>
    	<input type="hidden" name="id" value="${id}"/>
    	<input type="hidden" name="curPage" value="${curpage}"/>
    	<table style="display: ${paperlist.size()==0?none:block};">
    		<tr>
    			<th class="td">标题</th>
    			<td class="td"><input class="tdcontent" type="text" name="title" value="${title}"/></td>
    		</tr>
    		<tr>
    			<th class="td">发布年份</th>
    			<td class="td"><input class="tdcontent" type="text" name="year" value="${year}"/></td>
    		</tr>
    		<tr>
    			<th class="td">所属顶会</th>
    			<td class="td"><input class="tdcontent" type="text" name="magazine" value="${magazine}"/></td>
    		</tr>
    		<tr>
    			<th class="td">链接</th>
    			<td class="td"><input class="tdcontent" type="text" name="link" value="${link}"/></td>
    		</tr>
    		<tr>
    			<th class="td">摘要</th>
    			<td class="td"><textarea class="tdcontentlast" name="abst" rows="10" cols="1000">${abst}</textarea></td>
    		</tr>
    		<tr>
    			<th class="td">关键词</th>
    			<td class="td"><textarea class="tdcontentlast" rows="10" cols="1000">${keyword}</textarea></td>
    		</tr>
    	</table>
    	</form>
    </div>
</div>
</body>
</html>