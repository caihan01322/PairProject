// 基于准备好的dom，初始化echarts实例
var myChart1 = echarts.init(document.getElementById('main1'));
// 指定图表的配置项和数据
var option1 = {
    title: {
        text: 'CVPR峰会热词频率变化',
        textStyle: {
            color: '#ffffff' //字体颜色
        },
    },
    tooltip: {},
    legend: {
        data: ['热词1', '热词2'],
        textStyle: {
            color: '#ffffff' //字体颜色
        },

    },
    xAxis: {
        data: ["2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"],
        axisLabel: {
            show: true,
            textStyle: {
                color: '#ffffff'
            }
        }

    },
    yAxis: {
        axisLabel: {
            show: true,
            textStyle: {
                color: '#ffffff'
            }
        }

    },
    series: [{
        name: '热词1',
        type: 'line',
        data: [5, 20, 36, 10, 10, 20]
    }, {
        name: '热词2',
        type: 'line',
        data: [15, 25, 36, 11, 13, 23]
    }]
};
// 显示图表。
myChart1.setOption(option1);


// 基于准备好的dom，初始化echarts实例
var myChart2 = echarts.init(document.getElementById('main2'));
// 指定图表的配置项和数据
var option2 = {
    title: {
        text: 'ICCV峰会热词频率变化',
        textStyle: {
            color: '#ffffff' //字体颜色
        },
    },
    tooltip: {},
    legend: {
        data: ['热词1', '热词2'],
        textStyle: {
            color: '#ffffff' //字体颜色
        },

    },
    xAxis: {
        data: ["2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"],
        axisLabel: {
            show: true,
            textStyle: {
                color: '#ffffff'
            }
        }

    },
    yAxis: {
        axisLabel: {
            show: true,
            textStyle: {
                color: '#ffffff'
            }
        }

    },
    series: [{
        name: '热词1',
        type: 'line',
        data: [10, 20, 55, 20, 36, 10]
    }, {
        name: '热词2',
        type: 'line',
        data: [12, 11, 25, 33, 11, 3]
    }]
};
// 使用刚指定的配置项和数据显示图表。
myChart2.setOption(option2);

// 基于准备好的dom，初始化echarts实例
var myChart3 = echarts.init(document.getElementById('main3'));
// 指定图表的配置项和数据
var option3 = {
    title: {
        text: 'ECCV峰会热词频率变化',
        textStyle: {
            color: '#ffffff' //字体颜色
        },
    },
    tooltip: {},
    legend: {
        data: ['热词1', '热词2'],
        textStyle: {
            color: '#ffffff' //字体颜色
        },

    },
    xAxis: {
        data: ["2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"],
        axisLabel: {
            show: true,
            textStyle: {
                color: '#ffffff'
            }
        }

    },
    yAxis: {
        axisLabel: {
            show: true,
            textStyle: {
                color: '#ffffff'
            }
        }

    },
    series: [{
        name: '热词1',
        type: 'line',
        data: [5, 36, 10, 23, 10, 20]
    }, {
        name: '热词2',
        type: 'line',
        data: [25, 15, 36, 23, 11, 13]
    }]
};
// 使用刚指定的配置项和数据显示图表。
myChart3.setOption(option3);