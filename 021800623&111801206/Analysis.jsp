<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <a class="btn_head" href="Head.jsp">首页</a>
        <a class="btn_manage" href="Delete.jsp">论文管理</a>
        <a class="btn_analysis_active" href="Analysis.jsp">论文分析</a>
        </div>
    </div>

    <div class="tab">
        <a class="btn_word_active btn">热词走势</a>
        <a class="btn_key btn">关键词图谱</a>
        <div class="box" id="main"></div>
        <div class="box2 box">456</div>
    </div>
    <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main'));
        var option;
        option = {
    title: {
        text: '热词变化趋势',
        x:"center"
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        orient:"horizontal",
        x:"right",
        y:"bottom",
        padding: 0,
        data: ['word1', 'word2', 'word3', 'word4', 'word5', 'word6', 'word7', 'word8', 'word9', 'word10']
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
        data: ['2013', '2014', '2015', '2016', '2017', '2018', '2019', '2020']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: 'word1',
            type: 'line',
            stack: '总量',
            data: [120, 132, 101, 134, 90, 230, 210,200]
        },
        {
            name: 'word2',
            type: 'line',
            stack: '总量',
            data: [220, 182, 191, 234, 290, 330, 310,200]
        },
        {
            name: 'word3',
            type: 'line',
            stack: '总量',
            data: [150, 232, 201, 154, 190, 330, 410,200]
        },
        {
            name: 'word4',
            type: 'line',
            stack: '总量',
            data: [320, 332, 301, 334, 390, 330, 320,200]
        },
        {
            name: 'word5',
            type: 'line',
            stack: '总量',
            data: [820, 932, 901, 934, 1290, 1330, 1320,200]
        },
        {
            name: 'word6',
            type: 'line',
            stack: '总量',
            data: [820, 932, 901, 934, 1290, 1330, 1320,200]
        },
        {
            name: 'word7',
            type: 'line',
            stack: '总量',
            data: [820, 932, 901, 934, 1290, 1330, 1320,200]
        },
        {
            name: 'word8',
            type: 'line',
            stack: '总量',
            data: [820, 932, 901, 934, 1290, 1330, 1320,200]
        },
        {
            name: 'word9',
            type: 'line',
            stack: '总量',
            data: [820, 932, 901, 934, 1290, 1330, 1320,200]
        },
        {
            name: 'word10',
            type: 'line',
            stack: '总量',
            data: [820, 932, 901, 934, 1290, 1330, 1320,200]
        },
    ]
};
        
        myChart.setOption(option);
    </script>
    <script>
        var btn = document.querySelectorAll(".btn")
        var box = document.querySelectorAll('.box')
        for(var i = 0; i < btn.length;i++){
            btn[i].index = i
            btn[i].addEventListener('click',function(e){
                
                for(let j = 0; j <box.length;j++){
                    box[j].style.display = 'none';
                }
                for(let j = 0; j <btn.length;j++){
                    btn[j].style.backgroundColor = '#fff';
                    btn[j].style.color = '#BEBEBE';
                }

                var index = e.target.index;
                btn[index].style.backgroundColor = '#599AEF';
                btn[index].style.color = '#fff'
                box[index].style.display = 'block'
            })
        }
        
    </script>
</body>
</html>