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
        <a class="btn_head_active" href="DoUserSelect?">首页</a>
        <a class="btn_manage" href="Delete.jsp">论文管理</a>
        <a class="btn_analysis" href="DoICCVSelect.jsp">论文分析</a>
        </div>
    </div>
    <div class="header">
        <div class="title">
            论文列表
        </div>
        <div class="search">
        <form action="DoSearchSelect" method="post">
        <input type="text" name="condition" class="text_search" placeholder="请输入要查询的论文"/>
        <input type="submit" value="搜索" class="btn_search"/>
        </form>
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
		        ${p.keywords.size()>0?p.keywords.get(0):null},
		        ${p.keywords.size()>1?p.keywords.get(1):null},
		        ${p.keywords.size()>2?p.keywords.get(2):null}
	        </div>
    	</div>
    </c:forEach>
    </div>
    <div class="page">
    	共${tsum}条记录，当前在${cpage}/${tpage}页
    	<a class="btn_page" href="DoUserSelect?cp=1">首页</a>
    	<a class="btn_page" href="DoUserSelect?cp=${cpage>1?cpage-1:1}">上一页</a>
    	<a class="btn_page" href="DoUserSelect?cp=${cpage>tpage-1?tpage:cpage+1}">下一页</a>
    	<a class="btn_page" href="DoUserSelect?cp=${tpage}">尾页</a>
    </div>
    <div>
    </div>
</body>
</html>