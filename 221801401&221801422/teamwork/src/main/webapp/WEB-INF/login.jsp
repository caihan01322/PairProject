<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="jquery/jquery-3.3.1.js">

</script> 
<title>登录表单</title>
</head>
<script type="text/javascript"> 
$(function() {
    $("#button_submit").click(function() {
        if($("#username").val() == ""){
            $("#user").val("用户名不能为空");
        }
    })
})
</script> 
<body>
  <form id="userForm">
    <input id="user" type="text" value="请输入用户名"/><input id="username" type="text"/>
    <br/>
    <input id="password" type="password"/>
    <br/>
  </form>
    <button id="button_submit">提交</button>
    <button type="reset" >重置</button>
</body>
</html>

