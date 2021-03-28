<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>热门领域</title>
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
                 <li class="icon"><a href="index_two_search.jsp"><i><img src="./ImageResources/database.png"></i>论文管理</a></li>
                 <li class="icon">
                 	<div><a href="index.jsp"><i><img src="./ImageResources/fund.png"></i>论文分析</a></div>
                 	<div class="titleright"><a href="DoPaperPie?">图表分析</a></div>
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
        %>
    	
        <div class="contentright">
        <div id="myline" style="width: 900px;height:450px; float:left"></div>
        <script type="text/javascript">
        	var myChart = echarts.init(document.getElementById('myline'));
        	option = {
        		    title: {
        		        text: '折线图堆叠'
        		    },
        		    tooltip: {
        		        trigger: 'axis'
        		    },
        		    legend: {
        		        data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
        		    },
        		    grid: {
        		        left: '3%',
        		        right: '4%',
        		        bottom: '3%',
        		        containLabel: true
        		    },
        		    toolbox: {
        		        feature: {
        		            saveAsImage: {}
        		        }
        		    },
        		    xAxis: {
        		        type: 'category',
        		        boundaryGap: false,
        		        data: [<%="\""+keyword[0]+"\""%>,<%="\""+keyword[1]+"\""%>,<%="\""+keyword[2]+"\""%>,<%="\""+keyword[3]+"\""%>,<%="\""+keyword[4]+"\""%>,
                        	<%="\""+keyword[5]+"\""%>,<%="\""+keyword[6]+"\""%>,<%="\""+keyword[7]+"\""%>,<%="\""+keyword[8]+"\""%>,<%="\""+keyword[9]+"\""%>],
        		    },
        		    yAxis: {
        		        type: 'value'
        		    },
        		    series: [
        		        {
        		            name: '邮件营销',
        		            type: 'line',
        		            stack: '总量',
        		            data: [120, 132, 101, 134, 90, 230, 210]
        		        },
        		        {
        		            name: '联盟广告',
        		            type: 'line',
        		            stack: '总量',
        		            data: [220, 182, 191, 234, 290, 330, 310]
        		        },
        		        {
        		            name: '视频广告',
        		            type: 'line',
        		            stack: '总量',
        		            data: [150, 232, 201, 154, 190, 330, 410]
        		        },
        		        {
        		            name: '直接访问',
        		            type: 'line',
        		            stack: '总量',
        		            data: [320, 332, 301, 334, 390, 330, 320]
        		        },
        		        {
        		            name: '搜索引擎',
        		            type: 'line',
        		            stack: '总量',
        		            data: [820, 932, 901, 934, 1290, 1330, 1320]
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