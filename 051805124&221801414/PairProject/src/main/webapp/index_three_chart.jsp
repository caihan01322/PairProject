<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>关键词TOP10</title>
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
                 <li class="icon"><a href="dopapersearch?cp=1"><i><img src="./ImageResources/database.png"></i>论文管理</a></li>
                 <li class="icon">
                 	<div><a href="index.jsp"><i><img src="./ImageResources/fund.png"></i>论文分析</a></div>
                 	<div class="titleright"><a href="DoPaperPie?year=total">关键词TOP10</a></div>
                 	<div class="titleright"><a href="DoPaperLine?year=2017">热词走势</a></div>
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
        <div id="mypie" style="width: 900px;height:450px; float:left"></div>
        <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('mypie'));
        option = {
                title : {
                    text: '近五年关键词Top10',       //大标题
                    x:'center'                 //标题位置   居中
                },
                tooltip : {
                    trigger: 'item',           //数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
                    formatter: "{a} <br/>{b} : {c} ({d}%)"   //{a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）用于鼠标悬浮时对应的显示格式和内容
                },
                legend: {                           //图例组件。
                    orient: 'vertical',             //图例列表的布局朝向
                    left: 'left',
                    data: [<%="\""+keyword[0]+"\""%>,<%="\""+keyword[1]+"\""%>,<%="\""+keyword[2]+"\""%>,<%="\""+keyword[3]+"\""%>,<%="\""+keyword[4]+"\""%>,
                    	<%="\""+keyword[5]+"\""%>,<%="\""+keyword[6]+"\""%>,<%="\""+keyword[7]+"\""%>,<%="\""+keyword[8]+"\""%>,<%="\""+keyword[9]+"\""%>],
                },
                series : [              //系列列表。每个系列通过 type 决定自己的图表类型
                    {
                        name: '关键词占比',
                        type: 'pie',
                        radius : '55%',
                        center: ['60%', '60%'],
                        data:[
                            {value:<%="\""+occur[0]+"\""%>, name:<%="\""+keyword[0]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[0]+"\""%>},
                            {value:<%="\""+occur[1]+"\""%>, name:<%="\""+keyword[1]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[1]+"\""%>},
                            {value:<%="\""+occur[2]+"\""%>, name:<%="\""+keyword[2]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[2]+"\""%>},
                            {value:<%="\""+occur[3]+"\""%>, name:<%="\""+keyword[3]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[3]+"\""%>},
                            {value:<%="\""+occur[4]+"\""%>, name:<%="\""+keyword[4]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[4]+"\""%>},
                            {value:<%="\""+occur[5]+"\""%>, name:<%="\""+keyword[5]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[5]+"\""%>},
                            {value:<%="\""+occur[6]+"\""%>, name:<%="\""+keyword[6]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[6]+"\""%>},
                            {value:<%="\""+occur[7]+"\""%>, name:<%="\""+keyword[7]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[7]+"\""%>},
                            {value:<%="\""+occur[8]+"\""%>, name:<%="\""+keyword[8]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[8]+"\""%>},
                            {value:<%="\""+occur[9]+"\""%>, name:<%="\""+keyword[9]+"\""%>,url: "dopapersearch?plugin=keyword&searchContent="+<%="\""+keyword[9]+"\""%>},
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(1, 2, 3, 4)'
                            }
                        }
                    }
                ]
            };
        	myChart.setOption(option);
        	myChart.on('click', function(param) {
        	    var url = param.data.url;
        	    window.location.href = url;
        	});
        </script>
        <div class="pieselect">
    		<form action="DoPaperPie" method="get">
    			<select name="year">
    				<c:if test="${year=='total'}">
						<option value ="total" selected="selected" >total</option>
					</c:if>
  					<c:if test="${year!='total'}">
						<option value ="total" >total</option>
					</c:if>
    				<c:if test="${year=='2016'}">
						<option value ="2016" selected="selected" >2016</option>
					</c:if>
  					<c:if test="${year!='2016'}">
						<option value ="2016" >2016</option>
					</c:if>
					<c:if test="${year=='2017'}">
						<option value ="2017" selected="selected" >2017</option>
					</c:if>
  					<c:if test="${year!='2017'}">
						<option value ="2017" >2017</option>
					</c:if>
					<c:if test="${year=='2018'}">
						<option value ="2018" selected="selected" >2018</option>
					</c:if>
  					<c:if test="${year!='2018'}">
						<option value ="2018" >2018</option>
					</c:if>
					<c:if test="${year=='2019'}">
						<option value ="2019" selected="selected" >2019</option>
					</c:if>
  					<c:if test="${year!='2019'}">
						<option value ="2019" >2019</option>
					</c:if>
  					<c:if test="${year=='2020'}">
						<option value ="2020" selected="selected" >2020</option>
					</c:if>
  					<c:if test="${year!='2020'}">
						<option value ="2020" >2020</option>
					</c:if>
				</select>
				<input class="searchBtn" id="button" type="submit" value="切换"/>
            </form>
    	</div>
    	</div>
    </div>
</div>
</body>
</html>