<?php

/* @var $this yii\web\View */

use yii\helpers\Html;


$this->title = '论文分析';
$this->params['breadcrumbs'][] = $this->title;
?>

<div class="site-index">

    <div class="container">
        <script type="text/javascript" src="https://cdn.bootcss.com/echarts/3.7.0/echarts.min.js"></script>

        <div class="chart">
            <div id="chart1" style="width: 800px;height:400px;float: left">
            <script type="text/javascript">
                // 基于准备好的dom，初始化echarts实例
                var chartDom = document.getElementById('chart1');
                var myChart = echarts.init(chartDom);
                var option;
                var meeting = 3;
                var categoryCount = 18;

                var xAxisData = [];
                var customData = [];
                var legendData = [];
                var dataList = [];

                legendData.push('trend');
                var encodeY = [];
                for (var i = 0; i < meeting; i++) {
                    legendData.push(('ICCV') + '');
                    legendData.push(('ECCV') + '');
                    legendData.push(('CVPR') + '');
                    dataList.push([]);
                    encodeY.push(1 + i);
                }

                for (var i = 0; i < categoryCount; i++) {
                    var val = Math.random() * 1000;
                    xAxisData.push(i+2003);
                    var customVal = [i];
                    customData.push(customVal);

                    for (var j = 0; j < dataList.length; j++) {
                        var value = j === 0
                            ? echarts.number.round(val, 2)
                            : echarts.number.round(Math.max(0, dataList[j - 1][i] + (Math.random() - 0.5) * 200), 2);
                        dataList[j].push(value);
                        customVal.push(value);
                    }
                }

                function renderItem(params, api) {
                    var xValue = api.value(0);
                    var currentSeriesIndices = api.currentSeriesIndices();
                    var barLayout = api.barLayout({
                        barGap: '30%', barCategoryGap: '20%', count: currentSeriesIndices.length - 1
                    });

                    var points = [];
                    for (var i = 0; i < currentSeriesIndices.length; i++) {
                        var seriesIndex = currentSeriesIndices[i];
                        if (seriesIndex !== params.seriesIndex) {
                            var point = api.coord([xValue, api.value(seriesIndex)]);
                            point[0] += barLayout[i - 1].offsetCenter;
                            point[1] -= 20;
                            points.push(point);
                        }
                    }
                    var style = api.style({
                        stroke: api.visual('color'),
                        fill: null
                    });

                    return {
                        type: 'polyline',
                        shape: {
                            points: points
                        },
                        style: style
                    };
                }


                option = {
                    title: {
                        text: '论文数量对比',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: legendData
                    },
                    dataZoom: [{
                        type: 'slider',
                        start: 50,
                        end: 70
                    }, {
                        type: 'inside',
                        start: 50,
                        end: 70
                    }],
                    xAxis: {
                        data: xAxisData
                    },
                    yAxis: {},
                    series: [{
                        type: 'custom',
                        name: 'trend',
                        renderItem: renderItem,
                        itemStyle: {
                            borderWidth: 2
                        },
                        encode: {
                            x: 0,
                            y: encodeY
                        },
                        data: customData,
                        z: 100
                    }].concat(dataList.map(function (data, index) {
                        return {
                            type: 'bar',
                            animation: false,
                            name: legendData[index + 1],
                            itemStyle: {
                                opacity: 0.5
                            },
                            data: data
                        };
                    }))
                };
                option && myChart.setOption(option);
            </script>
            </div>
            <div id="chart2" style="width: 800px;height:400px;float: left">
                <script type="text/javascript">
                    // 基于准备好的dom，初始化echarts实例
                    var chartDom1 = document.getElementById('chart2');
                    var myChart1 = echarts.init(chartDom1);
                    var option1;
                    option1 = {
                        title: {
                            text: 'Top10热词展示',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                        },
                        series: [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius: '50%',
                                data: [
                                    {value: 3300, name: 'computer vision'},
                                    {value:2779, name: 'feature extraction'},
                                    {value: 2296, name: 'image segmentation'},
                                    {value: 2197, name: 'learning (artificial intelligence)'},
                                    {value: 2044, name: 'cameras'},
                                    {value: 2044, name: 'object detection'},
                                    {value: 1667, name: 'image reconstruction'},
                                    {value: 1568, name: 'trainings'},
                                    {value: 1383, name: 'image classification'},
                                    {value: 1169, name: 'face recognition'},
                                ],
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };

                    option1 && myChart1.setOption(option1);
                </script>
            </div>
        </div>

    </div>



    </div>
</div>
