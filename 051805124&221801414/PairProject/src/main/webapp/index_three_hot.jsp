<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>热词走势</title>
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
                 <li class="icon"><a href="dopapersearch?cp=1"><i><img src="./ImageResources/database.png"></i>论文管理</a></li>
                 <li class="icon">
                 	<div><a href="index.jsp"><i><img src="./ImageResources/fund.png"></i>论文分析</a></div>
                 	<div class="titleright"><a href="DoPaperPie?">关键词TOP10</a></div>
                 	<div class="titleright"><a href="DoPaperLine?year=2017">热词走势</a></div>
                 </li>
                 <li class="icon"><a href="index_four.jsp"><i><img src="./ImageResources/read.png"></i>背景知识</a></li>
            </ul>
        </div>
    </div>
    <div>	
    	<%
        	String[] keyword = (String[])request.getAttribute("keyword");
        	int[] CVPR = (int[])request.getAttribute("CVPR");
        	int[] ICCV = (int[])request.getAttribute("ICCV");
        	int[] ECCV = (int[])request.getAttribute("ECCV");
        %>
    	
    <div class="contentright">
        <div>
    		<form action="DoPaperLine" method="get">
    			<select name="year">
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
        <div id="myline" style="width: 1100px;height:450px; float:left; margin-top: 20px;"></div>
        <script type="text/javascript">
        	var myChart = echarts.init(document.getElementById('myline'));
        	option = {
        		    title: {
        		        text: '近五年年度关键词TOP10各顶会占比'
        		    },
        		    tooltip: {
        		        trigger: 'axis'
        		    },
        		    legend: {
        		        data: ['CVPR', 'ICCV', 'ECCV']
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
                        axisLabel : {
                            interval:0,                    
                            formatter : function(params){
                               var newParamsName = "";// 最终拼接成的字符串
                               var paramsNameNumber = params.length;// 实际标签的个数
                               var provideNumber = 16;// 每行能显示的字的个数
                               var rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
                                            
                               if (paramsNameNumber > provideNumber) {
                                    /** 循环每一行,p表示行 */
                                   for (var p = 0; p < rowNumber; p++) {
                                       var tempStr = "";// 表示每一次截取的字符串
                                       var start = p * provideNumber;// 开始截取的位置
                                       var end = start + provideNumber;// 结束截取的位置
                                       // 此处特殊处理最后一行的索引值
                                       if (p == rowNumber - 1) {
                                           // 最后一次不换行
                                           tempStr = params.substring(start, paramsNameNumber);
                                       } else {
                                            // 每一次拼接字符串并换行
                                           tempStr = params.substring(start, end) + "-" +"\n";
                                       }
                                       newParamsName += tempStr;// 最终拼成的字符串
                                   }

                               } else {
                                     // 将旧标签的值赋给新标签
                                    newParamsName = params;
                               }
                               //将最终的字符串返回
                               return newParamsName
                             }
                         }

        		    },
        		    yAxis: {
        		        type: 'value'
        		    },
        		    series: [
        		        {
        		            name: 'CVPR',
        		            type: 'line',
        		            stack: '总量',
        		            data: [<%="\""+CVPR[0]+"\""%>,<%="\""+CVPR[1]+"\""%>,<%="\""+CVPR[2]+"\""%>,<%="\""+CVPR[3]+"\""%>,<%="\""+CVPR[4]+"\""%>,
        		            <%="\""+CVPR[5]+"\""%>,<%="\""+CVPR[6]+"\""%>,<%="\""+CVPR[7]+"\""%>,<%="\""+CVPR[8]+"\""%>,<%="\""+CVPR[9]+"\""%>]
        		        },
        		        {
        		            name: 'ICCV',
        		            type: 'line',
        		            stack: '总量',
        		            data: [<%="\""+ICCV[0]+"\""%>,<%="\""+ICCV[1]+"\""%>,<%="\""+ICCV[2]+"\""%>,<%="\""+ICCV[3]+"\""%>,<%="\""+ICCV[4]+"\""%>,
        		            <%="\""+ICCV[5]+"\""%>,<%="\""+ICCV[6]+"\""%>,<%="\""+ICCV[7]+"\""%>,<%="\""+ICCV[8]+"\""%>,<%="\""+ICCV[9]+"\""%>]
        		        },
        		        {
        		            name: 'ECCV',
        		            type: 'line',
        		            stack: '总量',
        		            data: [<%="\""+ECCV[0]+"\""%>,<%="\""+ECCV[1]+"\""%>,<%="\""+ECCV[2]+"\""%>,<%="\""+ECCV[3]+"\""%>,<%="\""+ECCV[4]+"\""%>,
        		            <%="\""+ECCV[5]+"\""%>,<%="\""+ECCV[6]+"\""%>,<%="\""+ECCV[7]+"\""%>,<%="\""+ECCV[8]+"\""%>,<%="\""+ECCV[9]+"\""%>]
        		        }
        		    ]
        		};
        	myChart.setOption(option);
        </script>
    	</div>
    </div>
</div>
</body>
</html>