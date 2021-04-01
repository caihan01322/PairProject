<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>注册界面</title>
	<link rel="stylesheet" type="text/css" href="css/login.css"/>
</head>
<body>
<div id="one">
	<img src="./ImageResources/login.png"  alt="登录" />
</div>
<div id="two">
    <h1>欢迎使用，</h1>
    <h1>论文信息平台</h1>
    <div>
        <div>
            <form action="douseradd" method="post">
                
                <div id="divTop">
                	<label for="user">账号：</label>
                	<input type="text" name="username" placeholder="请输入账号" size="40" />
                </div>
            
            	<div id="divTop">
            		<label for="pwd">密码：</label>
                	<input type="password" name="pwd" placeholder="请输入密码" size="40" autocomplete />
            	</div>
            	
            	<div id="divTop">
            		<label for="pwd">密码：</label>
                	<input type="password" name="repwd" placeholder="请输入再次密码" size="40" autocomplete />
            	</div>
            
                <input id="button1" type="submit" value="立即注册"/>
                
            </form>
        </div>
        <div class="label">
        	<label>已有账号？去</label><label><a href="login.jsp">登录</a></label>
        </div>
    </div>
</div>
</body>
</html>