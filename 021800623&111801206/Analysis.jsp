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
        <a class="btn_head" href="DoUserSelcet?">首页</a>
        <a class="btn_manage" href="Delete.jsp">论文管理</a>
        <a class="btn_analysis_active" href="DoICCVSelect?">论文分析</a>
        </div>
    </div>

    <div class="tab">
        <a class="btn_word_active btn">热词走势</a>
        <a class="btn_key btn" href="DoTopSelect?">关键词图谱</a>
        <a class="btn_ICCV mt" href="DoICCVSelect?">ICCV</a>
	    <a class="btn_ECCV mt" href="DoECCVSelect?">ECCV</a>
	    <a class="btn_CVPR mt" href="DoCVPRSelect?">CVPR</a>
        <div class="box" id="main"></div>
    </div>
    
    <script type="text/javascript">
    	var myChart = echarts.init(document.getElementById('main'));
    	var mt = document.querySelectorAll('.mt')
    	var arr=[];
    	var names=[];
    	var obj={
    		name:'',
    		type:'',
    		data:[],
    	};
    	<%
    	ArrayList<HotWord> list = (ArrayList<HotWord>)request.getAttribute("dataList");
    	String title = (String)request.getAttribute("title");
    	if(title != null){
    	if(title.equals("ICCV")){
    	%>
    		mt[0].style.backgroundColor = '#599AEF';
        	mt[0].style.color = '#fff'
    	<%
    	}
    	if(title.equals("ECCV")){
    	%>
    		mt[1].style.backgroundColor = '#599AEF';
        	mt[1].style.color = '#fff'
    	<%
    	}
    	if(title.equals("CVPR")){
    	%>
    		mt[2].style.backgroundColor = '#599AEF';
        	mt[2].style.color = '#fff'
    	<%	
    	}
    	}
    	if(list != null){
    	for(HotWord hw : list)
    	{
    	%>
    		console.log("<%=hw.getWordString()%>");
    		names.push("<%=hw.getWordString()%>");
    		obj.name= ("<%=hw.getWordString()%>");
    		obj.type='line';
    		obj.data= <%=hw.getWordList()%>;
    		arr.push(Object.assign({},obj));
    	<%
    	}
    	}
    	%>
        var option;
        option = {
		    title: {
		        text: '2017~2020<%=title%>热词变化趋势',
		        x:"center"
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		    	type:'scroll',
		        orient:"horizontal",
		        x:"right",
		        y:"bottom",
		        padding: 0,
		        data:names,
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
		        data: ['2017', '2018', '2019','2020']
		    },
		    yAxis: {
		        type: 'value',
		    },
		    series:arr,
		};
	myChart.setOption(option);
    </script>
    <script>
        var btn = document.querySelectorAll(".btn")
        var box = document.querySelectorAll('.box')
        var mt = document.querySelectorAll('.mt')
      
        for(var i = 0;i < mt.length;i++){
        	mt[i].index = i;
        	mt[i].addEventListener('click',function(e){
        		for(let j = 0;j < mt.length;j++){
        			mt[index].style.display = 'inline-block'
                	mt[j].style.backgroundColor = '#fff';
                    mt[j].style.color = '#BEBEBE';
            	}
        		var index = e.target.index;
        		mt[index].style.backgroundColor = '#599AEF';
                mt[index].style.color = '#fff'
                
        	})
        }
    </script>
</body>
</html>