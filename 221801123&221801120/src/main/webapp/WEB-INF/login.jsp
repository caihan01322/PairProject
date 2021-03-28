<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title>登录</title>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
				request.getServerPort() + path + "/";
	%>
	<base href="<%=basePath%>">
	<link rel="stylesheet" href="<%=basePath%>css/login.css">
	<script src="<%=basePath%>script/login.js"></script>
</head>
<body>
	<div class="header">
		<div class="logo">
			<font class="logofont">Crawler</font>
		</div>
	</div>
	<div class="middle">
		<h2>登录</h2>
		<form action="login/do" method="post" onsubmit="return check(this);">
			<div class="form-group">
				<label for="user">账号：</label>
				<input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
			</div>

			<div class="form-group">
				<label for="password">密码：</label>
				<input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
			</div>

			<div class="form-inline">
				<label for="vcode">验证码：</label>
				<input type="text" name="verify" class="verify_input" id="vcode">
				<img src="<%=basePath%>varify_code.jsp" onclick="image(this);" class="verify_image">
			</div>

			<div class="form-group" style="text-align: center;">
				<input class="btn btn-primary" type="submit" value="登录">
			</div>
		</form>

		<!-- 出错显示的信息框 -->
		<div id="login_error">
			<c:if test="${! empty error}" >
				<strong>${error}</strong>
				<button >
					<span>x</span>
				</button>
			</c:if>
		</div>
	</div>
</body>
</html>
