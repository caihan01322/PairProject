var dom = document.getElementsByClassName("chart");
var myChart = [];
for(var i = 0;i < 3;i++){
    myChart[i] = echarts.init(dom[i]);
}

var app = {};

var option = [];

var urlStr = "https://mock.mengxuegu.com/mock/60634842f2e38f3a2f6ba3ec/example_copy/trend";
var trendVal = {
    type:"trend"
};
PostHandle(urlStr, JSON.stringify(trendVal), function(data){
    var legend = [[],[],[]];
    var year = [[],[],[]];
    var series = [[],[],[]];
    var trend = data.data.trend;

    for(var i = 0 ; i < 3 ; i++){
        for(var j = 0 ; j < trend[i].years.length ; j++){
            year[i].push(trend[i].years[j]);
        }
    }
    for(var i = 0 ; i < 3 ; i++){
        for(var j = 0 ; j < trend[i].key_values.length ; j++){
            legend[i].push(trend[i].key_values[j].name);
            series[i][j] = {
                name: trend[i].key_values[j].name,
                type: 'line',
                stack: trend[i].key_values[j].name,
                data: trend[i].key_values[j].counts
            };
        }
    }
    for(var i = 0;i < 3;i++){
        option[i] = {
            /*title: {
                text: '折线图堆叠'
            },*/
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: legend[i]
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
                data: year[i]
            },
            yAxis: {
                type: 'value'
            },
            series: series[i]
        };
        if (option[i] && typeof option[i] === 'object') {
            myChart[i].setOption(option[i]);
        }
    }
});