<?php

/* @var $this yii\web\View */



use common\models\overall;
use common\models\taganalysis;

$pdata=overall::getOverall();

$cvrp=taganalysis::getDataPagercvrp();
$eccv=taganalysis::getDataPagereccv();
$iccv=taganalysis::getDataPagericcv();

$this->title = 'My Yii Application';
?>

<div class="site-index">

    <div class="chart0">
        <?php

            /* @var $this yii\web\View */

            use yii\helpers\Html;

            $this->title = '图表分析';
            $this->params['breadcrumbs'][] = $this->title;
        ?>
        <script type="text/javascript" src="https://cdn.bootcss.com/echarts/3.7.0/echarts.min.js"></script>

        <div class="site-about">
            <h1><?= Html::encode($this->title) ?></h1>
            <div id="main" style="width: 800px;height:400px;float:left;"></div>
        </div>
        <script type="text/javascript" >
            // 基于准备好的dom，初始化echarts实例
        var chartDom = document.getElementById('main');
        var myChartz = echarts.init(chartDom);
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

option && myChartz.setOption(option);
</script>

<div id="main2" style="width: 550px;height:400px;float:left;"></div>

<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main2'));
var p=<?php echo json_encode($pdata) ?>;
var js_arr = p.map(item => {
  return Object.values(item)
})
// 指定图表的配置项和数据
var option2 = {
        series : [
            {
                name: '评论详情',
                type: 'pie',
                radius: 120,
                data:[
                    {value:js_arr[0][2], name:js_arr[0][1],url:"https://doi.org/10.1007/978-3-030-01228-1_48"},
                    {value:js_arr[1][2], name:js_arr[1][1],url:"https://doi.org/10.1007/978-3-030-58568-6_33"},
                    {value:js_arr[2][2], name:js_arr[2][1],url:"https://doi.org/10.1007/978-3-030-01231-1_4"},
                    {value:js_arr[3][2], name:js_arr[3][1],url:"https://doi.org/10.1109/CVPR.2000.854723"},
                    {value:js_arr[4][2], name:js_arr[4][1],url:"https://doi.org/10.1007/978-3-030-01231-1_27"},
                    {value:js_arr[5][2], name:js_arr[5][1],url:"https://doi.org/10.1007/978-3-030-01216-8_6"},
                    {value:js_arr[6][2], name:js_arr[6][1],url:"https://doi.org/10.1007/978-3-030-11018-5_6"},
                    {value:js_arr[7][2], name:js_arr[7][1],url:"https://doi.org/10.1007/978-3-030-01225-0_36"},
                    {value:js_arr[8][2], name:js_arr[8][1],url:"https://doi.org/10.1007/978-3-030-01231-1_10"},
                    {value:js_arr[9][2], name:js_arr[9][1],url:"https://doi.org/10.1007/978-3-030-01216-8_39"},
                ],
                //roseType: 'angle',
                itemStyle: {
                    normal: {
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)',
                        label:{ 
                                      show: true, 
                                      formatter: '{b} : {c} ({d}%)' 
                                      },
                    }
                }
            }
        ]
    };

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option2);
myChart.on('click',function(params){
    var url=params.data.url;
    window.location.href=url;
})
</script>
          </div>
          </div>