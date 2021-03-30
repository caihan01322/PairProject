<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<html>
<head>
	<title>主页</title>
	<meta charset="UTF-8">
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		pageContext.setAttribute("basePath", basePath);
	%>
	<base href="<%=basePath%>">
	<link rel="stylesheet" href="css/head.css">
	<link rel="stylesheet" href="css/footer.css">

</head>
<body>
<!--头部-->
<jsp:include page="share/head.jsp"/>
<!--中间主体部分-->
<div class="main">
	<div class="welcome">主体</div>
	<%--整点文字图片啥的，可以先放着--%>
</div>
<jsp:include page="share/footer.jsp"/>
</body>
</html>