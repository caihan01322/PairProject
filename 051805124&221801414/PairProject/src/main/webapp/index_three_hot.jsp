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
        	int[] CVPR = (int[])request.getAttribute("CVPR");
        	int[] ICCV = (int[])request.getAttribute("ICCV");
        	int[] ECCV = (int[])request.getAttribute("ECCV");
        %>
    	
        <div class="contentright">
        <div id="myline" style="width: 1100px;height:450px; float:left"></div>
        <script type="text/javascript">
        	var myChart = echarts.init(document.getElementById('myline'));
        	option = {
        		    title: {
        		        text: '多年论文关键词对比'
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
        		            data: [<%="\""+CVPR[0]+"\""%>,<%="\""+CVPR[1]+"\""%>,<%="\""+CVPR[2]+"\""%>]
        		        },
        		        {
        		            name: 'ICCV',
        		            type: 'line',
        		            stack: '总量',
        		            data: [<%="\""+ICCV[0]+"\""%>,<%="\""+ICCV[1]+"\""%>,<%="\""+ICCV[2]+"\""%>]
        		        },
        		        {
        		            name: 'ECCV',
        		            type: 'line',
        		            stack: '总量',
        		            data: [<%="\""+ECCV[0]+"\""%>,<%="\""+ECCV[1]+"\""%>,<%="\""+ECCV[2]+"\""%>]
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