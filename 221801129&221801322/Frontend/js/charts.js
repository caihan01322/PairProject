var dom = document.getElementsByClassName("chart");

var myChart1 = echarts.init(dom[0]);
var myChart2 = echarts.init(dom[1]);
var myChart3 = echarts.init(dom[2]);

var app = {};

var option;

option = {
    /*title: {
        text: '折线图堆叠'
    },*/
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['热词1', '热词2', '热词3']
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
        data: ['2014', '2015', '2016', '2017', '2018', '2019', '2020']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: '热词1',
            type: 'line',
            stack: '热度1',
            data: [120/2, 132/2, 101/2, 134/2, 90/2, 230/2, 210/2]
        },
        {
            name: '热词2',
            type: 'line',
            stack: '热度2',
            data: [220/2, 182/2, 191/2, 234/2, 290/2, 330/2, 310/2]
        },
        {
            name: '热词3',
            type: 'line',
            stack: '热度3',
            data: [150/2, 232/2, 201/2, 154/2, 190/2, 330/2, 410/2]
        }
    ]
};

if (option && typeof option === 'object') {
    myChart1.setOption(option);
    myChart2.setOption(option);
    myChart3.setOption(option);
}