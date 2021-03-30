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
        <a class="btn_word btn" href="DoHotWordSelect">热词走势</a>
        <a class="btn_key_active btn">关键词图谱</a>
        <div id="map"></div>
    </div>
    
    <script type="text/javascript">
    var myChart = echarts.init(document.getElementById('map'));
    <%
	ArrayList<String> list = (ArrayList<String>)request.getAttribute("dataList");
	%>
    myChart.setOption({
    	legend: {
	        orient:"vertical",
	        x:"left",
	        y:"top",
	        padding:[10,0,0,0],
	        data:['<%=list.get(0)%>','<%=list.get(1)%>','<%=list.get(2)%>','<%=list.get(3)%>','<%=list.get(4)%>',
	        	'<%=list.get(5)%>','<%=list.get(6)%>','<%=list.get(7)%>','<%=list.get(8)%>','<%=list.get(9)%>']
	    },
        series : [
            {
                name: '热词TOP10',
                type: 'pie',    // 设置图表类型为饼图
                radius: '65%',  // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                roseType: 'angle',
                data:[          // 数据数组，name 为数据项名称，value 为数据项值
                    {value:1200,name:("<%=list.get(0)%>")},
                    {value:1150,name:("<%=list.get(1)%>")},
                    {value:1050,name:("<%=list.get(2)%>")},
                    {value:1000,name:("<%=list.get(3)%>")},
                    {value:950,name:("<%=list.get(4)%>")},
                    {value:900,name:("<%=list.get(5)%>")},
                    {value:850,name:("<%=list.get(6)%>")},
                    {value:800,name:("<%=list.get(7)%>")},
                    {value:750,name:("<%=list.get(8)%>")},
                    {value:700,name:("<%=list.get(9)%>")},
                ],
                color: ['#37A2DA', '#32C5E9', '#67E0E3', '#9FE6B8', '#FFDB5C','#ff9f7f', '#fb7293', '#E062AE', '#E690D1', '#e7bcf3', '#9d96f5', '#8378EA', '#96BFFF'],
            }

        ],
        
    	
    });
    myChart.on("click", eConsole);
	function eConsole(param) {
		//alert(param.name)
		window.location.href = "DoSearchSelect?cp=1&condition="+param.name;
	}
    </script>
</body>
</html>