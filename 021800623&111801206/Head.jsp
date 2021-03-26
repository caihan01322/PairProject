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
    <script type="text/javascript">  
			function a(){
				$.ajax({  
            		url:"DoUserServlet",//servlet文件的名称
            		type:"GET",
            		success:function(e){
            			alert("servlet调用成功！");
            		}
            	});
				
			}
        </script>
</head>
<body onload = "a()">
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
    <c:forEach var="p" items="${paperList}">
        <div class="block">
	        <a class="title_a">标题:
	        </a>${p.title}<br>
	        <a class="conclude">摘要:</a>
	        <div class=conclude_content>${p.abstractString}</div>
	        <a class="keyword">关键词:</a>
	        <div class="word">
		        ${p.keywords.get(0)},
		        ${p.keywords.get(1)},
		        ${p.keywords.get(2)}
	        </div>
    	</div>
    </c:forEach>
    </div>
</body>
</html>