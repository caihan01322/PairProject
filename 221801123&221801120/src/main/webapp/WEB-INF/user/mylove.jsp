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
<style>
    .main{
        font-size:80px;
        /*background-image: -webkit-linear-gradient(bottom, rgb(201, 115, 255), rgb(20, 11, 255));*/
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        font-family: Arial, Helvetica, sans-serif;
        color: #02F0FF;
        text-shadow:
                1px -1px 0 #0BF8FF,
                -1px 2px 1px #17EAFF,
                -2px 4px 1px #2BDDFF,
                -3px 6px 1px #3ECDFF,
                -4px 8px 1px #4DCAFF,
                -5px 10px 1px #5ACAFF,
                -6px 12px 1px #74CBFF,
                -7px 14px 1px #7BC3FF,
                -8px 16px 1px #8AC9FF,
                -9px 18px 1px #9BCEFF,
                -10px 20px 1px #AACBFF,
                -11px 22px 1px #B6C6FF,
                -12px 24px 1px #C1CDFF,
                -13px 26px 1px #CFCFFF,
                -14px 28px 1px #E3DDFF,
                -15px 30px 1px #F5E0FF,
                -16px 32px 1px #FFE6F6,
                -17px 34px 1px #FDFFFB
    }
</style>
<div class="main">
    <span>敬请期待</span>
</div>
<jsp:include page="share/footer.jsp"/>
</body>
</html>
