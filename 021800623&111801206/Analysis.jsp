<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="test.HotWord" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="echarts.min.js"></script>
    <link rel="stylesheet" href="./index.css">
</head>
<body>
    <div class="nav">
        <div class="logo">
            Paper管理平台
        </div>
        <div class="items">
        <a class="btn_head" href="DoUserSelect">首页</a>
        <a class="btn_manage" href="Delete.jsp">论文管理</a>
        <a class="btn_analysis_active" href="DoHotWordSelect">论文分析</a>
        </div>
    </div>

    <div class="tab">
        <a class="btn_word_active btn">热词走势</a>
        <a class="btn_key btn" href="DoTopSelect?">关键词图谱</a>
        <div class="div_select">
	    	<form action="DoHotWordSelect" method="post">
		        <select class="select" name="year">
		          <option value ="">请选择年份</option>
				  <option value ="2017">2017</option>
				  <option value ="2018">2018</option>
				  <option value ="2019">2019</option>
				  <option value ="2020">2020</option>
				</select>
				<input type="submit" value="切换" class="btn_change"/>
			</form>
		</div>
        <div class="box" id="main"></div>
    </div>
    
    <script type="text/javascript">
    	var myChart = echarts.init(document.getElementById('main'));
    	var arr=[];
    	var detail=[];
    	var obj={
    		name:'',
    		type:'',
    		data:[],
    	};
    	var obj1={
        		name:'',
        		type:'',
        		data:[],
        	};
    	<%
    	ArrayList<HotWord> list = (ArrayList<HotWord>)request.getAttribute("dataList");
    	String year = (String)request.getAttribute("year");
    	if(list != null){
    	for(HotWord hw : list)
    	{
    	%>
    		obj.name = ("<%=hw.getMeetingName()%>");
    		obj.type = 'line';
    		obj.data = <%=hw.getNumList()%>;
    		arr.push(Object.assign({},obj));
    		obj1.name = ("<%=hw.getMeetingName()%>_");
    		obj1.type = 'line'; 
    		<% 
    		for(String string : hw.getWordList())
    		{
    		%>
    			detail.push('<%=string%>');
    		<%
    		}
    		%>
    		console.log("detail", detail); 
    		obj1.data = detail.concat();
    		arr.push(Object.assign({},obj1));
    		detail.splice(0);
    	<%
    	}
    	}
    	%>
        var option;
        option = {
		    title: {
		        text: '<%=year%>三大顶会热词变化趋势',
		        x:"center"
		    },
		    tooltip: {
		        trigger: 'axis',
		        formatter: function (params, ticket, callback) {
		        	var htmlStr = '';
		            for(var i=0;i<params.length;i++){
		              var param = params[i];
		              var xName = param.name;//x轴的名称
		              var seriesName = param.seriesName;//图例名称
		              var value = param.value;//y轴值
		              var color = param.color;//图例颜色
		              
		              if(i===0){
		                htmlStr += xName + '<br/>';//x轴的名称
		              }
		              htmlStr +='<div>';
		              
		              // 具体显示的数据【字段名称：seriesName，值：value】
		              // 这里判断一下name，如果是我们需要特殊处理的，就处理
		              if(seriesName === 'ICCV_'){
		                  htmlStr += 'ICCV: ' + value;
		              }
		              else if(seriesName === 'ECCV_'){
		                  htmlStr += 'ECCV: ' + value;
		              }
		              else if(seriesName === 'CVPR_'){
		                  htmlStr += 'CVPR: ' + value;
		              }else{
		                  // 正常显示的数据，走默认
		                  htmlStr += '<span style="margin-right:5px;display:inline-block;width:10px;height:10px;border-radius:5px;background-color:'+color+';"></span>';
		                  htmlStr += seriesName + ':' + value ;
		              }
		              
		              htmlStr += '</div>';
		            }
		            return htmlStr;
		        }
		    },
		    legend: {
		    	type:'scroll',
		        orient:"horizontal",
		        x:"right",
		        y:"bottom",
		        padding: 0,
		        data:['ICCV','ECCV','CVPR']
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
		        data: ['TOP1','TOP2','TOP3','TOP4','TOP5','TOP6','TOP7','TOP8','TOP9','TOP10']
		    },
		    yAxis: {
		        type: 'value',
		    },
		    series:arr,
		};
	myChart.setOption(option);
    </script>
    <script>
        
    </script>
</body>
</html>