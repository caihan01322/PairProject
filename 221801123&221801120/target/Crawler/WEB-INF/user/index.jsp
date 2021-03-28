<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
	<title>主页</title>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
</head>
<body>
<!--头部-->

<!--中间主体部分-->
<div class="main">
	<div class="welcome">主体</div>
</div>
</body>
</html>