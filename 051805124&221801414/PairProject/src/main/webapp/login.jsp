<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>登录界面</title>
	<style type="text/css">
		#one{
	         float: left;
	         width: 55%;
	         height: 100%;
	         word-wrap: break-word;
	         margin-top: 45px;
	   }
       #one img{
   			width: 100%;
   			vertical-align: middle;
       }
	  #two{
            float: right;
            position: relative;
            width: 45%;
            height: 100%;
            margin-top: 140px;
	  }
	  h1{
            width: 50%;
            height: 100%;
            word-wrap: break-word;
            font-size: 200%;
            color:#3A62D7;
	  }
	  #button1{
	  		width: 60%;	
	  		margin-top : 30px;
	  		background: #3A62D7;
	  		color:#FFFFFF;
	  		border-radius: 30px;
	  		padding: 8px;
	  }
	  input{
	  		width: 50%;	
	  		border-color: #DCDCDC;
	  		border-radius: 30px;
	  		padding: 8px;
	  }
	  #divTop{
	  		height:50px;
	  		width: 100%;	
	  		margin-top : 30px;
	  }
	  
	</style>
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
            <form action="douserlogin" method="post">
                
                <div id="divTop">
                	<label for="user">账号：</label>
                	<input type="text" name="username" placeholder="请输入账号" size="40" />
                </div>
            
            	<div id="divTop">
            		<label for="pwd">密码：</label>
                	<input type="password" name="pwd" placeholder="请输入密码" size="40" />
            	</div>
            
                <input id="button1" type="submit" value="立即登录"/>
                
            </form>
        </div>
        <div>
        	<label>没有账号？去</label><label><a href="register.jsp">注册</a></label>
        </div>
    </div>
</div>
</body>
</html>