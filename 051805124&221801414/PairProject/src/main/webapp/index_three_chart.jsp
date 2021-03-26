<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>图表分析</title>
    <link rel="stylesheet" type="text/css" href="css/indexmain.css"/>
    <script src="./echarts.min.js"></script>
</head>
<body>
<div>
    <div class="head">
        <div class="head1">
            <h1>论文信息平台</h1>
        </div>
        <div class="head2">
        	<a><label>用户名</label></a>
        	<a><img src="./ImageResources/user.png"/></a>
        	<a><img src="./ImageResources/question.png"/></a>
        	<a><img src="./ImageResources/bell.png" /></a>
        	<a><img src="./ImageResources/link.png" /></a>
            <a><img src="./ImageResources/redo.png" /></a>
        </div>
    </div>
</div>
<div class="content">
    <div>
        <div class="contentleft">
            <ul>
                 <li class="icon"><a href="index_one_add.jsp"><i><img src="./ImageResources/folderadd.png"></i>论文爬取</a></li>
                 <li class="icon"><a href="index_two_edit.jsp"><i><img src="./ImageResources/database.png"></i>论文管理</a></li>
                 <li class="icon">
                 	<div><a href="index.jsp"><i><img src="./ImageResources/fund.png"></i>论文分析</a></div>
                 	<div class="titleright"><a href="index_three_chart.jsp">图表分析</a></div>
                 	<div class="titleright"><a href="index_three_hot.jsp">热门领域</a></div>
                 </li>
                 <li class="icon"><a href="index_four.jsp"><i><img src="./ImageResources/read.png"></i>背景知识</a></li>
            </ul>
        </div>
    </div>
    <div>
    	
        	
        	<%
        	String[] keyword = (String[])request.getAttribute("keyword");
        	int[] occur = (int[])request.getAttribute("occur");
        	if(keyword == null){ %>
        		<jsp:forward page="DoPaperPie"></jsp:forward>
        		<%
        		}%>
        	<p><%=keyword[0] %></p>
        <div class="contentright">
        <div id="main" style="width: 600px;height:400px; float:left"></div>
        <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main'));
        option = {
        	    title: {
        	        text: '某站点用户访问来源',
        	        subtext: '纯属虚构',
        	        left: 'center'
        	    },
        	    tooltip: {
        	        trigger: 'item'
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        left: 'left',
        	        data: [<%=keyword[0]%>,<%=keyword[1]%>,<%=keyword[2]%>,<%=keyword[3]%>,<%=keyword[4]%>]
        	    },
        	    series: [
        	        {
        	            name: '访问来源',
        	            type: 'pie',
        	            radius: '50%',
        	            data: [
        	                <%=occur[0]%>,<%=occur[1]%>,<%=occur[2]%>,<%=occur[3]%>,<%=occur[4]%>
        	            ],
        	            emphasis: {
        	                itemStyle: {
        	                    shadowBlur: 10,
        	                    shadowOffsetX: 0,
        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
        	                }
        	            }
        	        }
        	    ]
        	};
        	myChart.setOption(option);
        </script>
        
    	</div>
    </div>
</div>
<div>
	<div class="footer">
		<label>Copyright © 2021 </label>
	</div>
	<div class="footer">
		<label>Powered by .NET 5.0 on Kubernetes & Theme Silence v3.0.0</label>
	</div>
</div>
</body>
</html>