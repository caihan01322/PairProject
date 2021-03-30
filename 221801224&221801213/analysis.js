var searchBar = new Vue({
    el: "#searchBar",
    data: {
        joke: "一个笑话",

    },
    methods: {
        testfunc: function() {
            var that = this;
            axios.get('https://api.gugudata.com/news/joke/demo')
                .then(function(response) {
                    console.log(response);
                    that.joke = response.data.Data[1].Title;
                })
                .catch(function(err) {


                })

        }
    }

})

var rightsideBar = new Vue({
    el: "#rightsideBar",
    data: {
        hotWordArr: ["机器学习", "机器学习", "机器学习", "机器学习", "机器学习",
            "机器学习", "机器学习", "机器学习", "机器学习", "机器学习"
        ],

    },
    methods: {
        func: function() {}
    }

})

// 基于准备好的dom，初始化echarts实例
var myChart1 = echarts.init(document.getElementById('main1'));
// 指定图表的配置项和数据
var option1 = {
    title: {
        text: 'xxx峰会热词频率变化'
    },
    tooltip: {},
    legend: {
        data: ['热词1', '热词2']
    },
    xAxis: {
        data: ["2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"]
    },
    yAxis: {},
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
        text: '第一个 ECharts 实例'
    },
    tooltip: {},
    legend: {
        data: ['销量']
    },
    xAxis: {
        data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
    },
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
    }]
};
// 使用刚指定的配置项和数据显示图表。
myChart2.setOption(option2);

// 基于准备好的dom，初始化echarts实例
var myChart3 = echarts.init(document.getElementById('main3'));
// 指定图表的配置项和数据
var option3 = {
    title: {
        text: '第一个 ECharts 实例'
    },
    tooltip: {},
    legend: {
        data: ['销量']
    },
    xAxis: {
        data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
    },
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
    }]
};
// 使用刚指定的配置项和数据显示图表。
myChart3.setOption(option3);