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
	<link rel="stylesheet" href="css/index.css">
</head>
<body>
<!--头部-->
<jsp:include page="share/head.jsp"/>
<!--中间主体部分-->
<div class="main">
	<select class="form-select-button">
		<option value="thesis_title">论文题目</option>
		<option value="thesis_id">论文摘要</option>
		<option value="thesis_keyword">关键词</option>
	</select>
	<input type="text" placeholder="请输入">
	<button>🔍</button>
</div>
<jsp:include page="share/footer.jsp"/>
</body>
</html>