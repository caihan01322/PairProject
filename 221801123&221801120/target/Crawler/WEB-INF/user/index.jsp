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
	<select id="search_type" class="form-select-button">
		<option value="title">论文题目</option>
		<option value="abstractContent">论文摘要</option>
		<option value="keyword">关键词</option>
	</select>
	<input id="search_message" type="text" placeholder="请输入">
	<button onclick="index_search(this);">🔍</button>
	<script>
		function index_search(btn){
			var searchType = document.getElementById("search_type");
			var index = searchType.selectedIndex;
			var type = searchType.options[index].value;
			var message = document.getElementById("search_message").value;
			if (type=="title"){
				window.location.href = "user/thesis/list?title="+message;
			}
			else if (type=="abstractContent"){
				window.location.href = "user/thesis/list?abstractContent="+message;
			}
			else {
				window.location.href = "user/thesis/list?keyword="+message;
			}
		}
	</script>
</div>
<jsp:include page="share/footer.jsp"/>
</body>
</html>
