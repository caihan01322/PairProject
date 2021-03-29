<?php

            /* @var $this yii\web\View */

            use yii\helpers\Html;

            $this->title = '图表分析';
            $this->params['breadcrumbs'][] = $this->title;
?>

<div class="site-index">

    <div class="chart0">
       
        <script type="text/javascript" src="https://cdn.bootcss.com/echarts/3.7.0/echarts.min.js"></script>

        <div class="site-about">
            <h1><?= Html::encode($this->title) ?></h1>
            <div id="main" style="width: 800px;height:400px;float:left;"></div>
        </div>
        <script type="text/javascript" >
            // 基于准备好的dom，初始化echarts实例
            var chartDom = document.getElementById('main');
             var myChart = echarts.init(chartDom);
            var option;

option = {
    title: {
        text: '上一届三大会议热词走势对比图'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['CVPR', 'ICCV', 'ECCV' ]
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
   
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['TOP1', 'TOP2', 'TOP3', 'TOP4', 'TOP5', 'TOP6', 'TOP7','TOP8', 'TOP9', 'TOP10']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: 'CVPR',
            type: 'line',
            stack: '总量',
            data: [230,210,204,198,184,154,144,122,90,87]
        },
        {
            name: 'ECCV',
            type: 'line',
            stack: '总量',
            data: [284,192,152,143,140,127,113,111,88,86]
        },
        {
            name: 'ICCV',
            type: 'line',
            stack: '总量',
            data: [263,261,201,168,162,158,142,119,111,91]
        },
      
    ]
};

option && myChart.setOption(option);

        </script>
          </div>
        <div id="main2" style="width: 800px;height:400px;float:left;"></div>

<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例

    // 基于准备好的dom，初始化echarts实例
    var chartDom1 = document.getElementById('main2');
var myChart1 = echarts.init(chartDom1);
var option1;

option1 = {
    title: {
        text: '上俩届三大会议热词走势对比图'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['CVPR', 'ICCV', 'ECCV' ]
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
   
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['TOP1', 'TOP2', 'TOP3', 'TOP4', 'TOP5', 'TOP6', 'TOP7','TOP8', 'TOP9', 'TOP10']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: 'CVPR',
            type: 'line',
            stack: '总量',
            data: [198,185,170,150,120,98,70,55,43,21]
        },
        {
            name: 'ECCV',
            type: 'line',
            stack: '总量',
            data: [184,172,152,143,133,110,93,88,86,54]
        },
        {
            name: 'ICCV',
            type: 'line',
            stack: '总量',
            data: [173,161,150,130,110,95,73,54,32,10]
        },
      
    ]
};

option1 && myChart1.setOption(option1);

</script>
</div>

<div id="main3" style="width: 800px;height:400px;float:left;"></div>

<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例

    // 基于准备好的dom，初始化echarts实例
    var chartDom2 = document.getElementById('main3');
var myChart2 = echarts.init(chartDom2);
var option2;

option2 = {
    title: {
        text: '上三届三大会议热词走势对比图'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['CVPR', 'ICCV', 'ECCV' ]
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
   
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['TOP1', 'TOP2', 'TOP3', 'TOP4', 'TOP5', 'TOP6', 'TOP7','TOP8', 'TOP9', 'TOP10']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: 'CVPR',
            type: 'line',
            stack: '总量',
            data: [158,145,99,94,94,89,88,82,69,59]
        },
        {
            name: 'ECCV',
            type: 'line',
            stack: '总量',
            data: [238,208,203,146,127,111,110,110,100,92]
        },
        {
            name: 'ICCV',
            type: 'line',
            stack: '总量',
            data: [175,147,98,97,95,95,86,76,67,64]
        },
      
    ]
};

option2 && myChart2.setOption(option2);

</script>
</div>