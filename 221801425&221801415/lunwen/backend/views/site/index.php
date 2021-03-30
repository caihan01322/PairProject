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

        var a=<?php echo json_encode($cvrp) ?>;
        var cvrp_arr = a.map(item => {
            return Object.values(item)
        })
        var b=<?php echo json_encode($eccv) ?>;
        var eccv_arr = b.map(item => {
           return Object.values(item)
        })
        var c=<?php echo json_encode($iccv) ?>;
        var iccv_arr = c.map(item => {
            return Object.values(item)
        })
        var p=<?php echo json_encode($pdata) ?>;

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
            data: [
            {value:cvrp_arr[0][2]},
            {value:cvrp_arr[1][2]},
            {value:cvrp_arr[2][2]},
            {value:cvrp_arr[3][2]},
            {value:cvrp_arr[4][2]},
            {value:cvrp_arr[5][2]},
            {value:cvrp_arr[6][2]},
            {value:cvrp_arr[7][2]},
            {value:cvrp_arr[8][2]},
            {value:cvrp_arr[9][2]},
            ]
        },
        {
            name: 'ECCV',
            type: 'line',
            stack: '总量',
            data: [
            {value:eccv_arr[0][2]},
            {value:eccv_arr[1][2]},
            {value:eccv_arr[2][2]},
            {value:eccv_arr[3][2]},
            {value:eccv_arr[4][2]},
            {value:eccv_arr[5][2]},
            {value:eccv_arr[6][2]},
            {value:eccv_arr[7][2]},
            {value:eccv_arr[8][2]},
            {value:eccv_arr[9][2]},
            ]
        },
        {
            name: 'ICCV',
            type: 'line',
            stack: '总量',
            data:  [
            {value:iccv_arr[0][2],name:iccv_arr[0][1]},
            {value:iccv_arr[1][2]},
            {value:iccv_arr[2][2]},
            {value:iccv_arr[3][2]},
            {value:iccv_arr[4][2]},
            {value:iccv_arr[5][2]},
            {value:iccv_arr[6][2]},
            {value:iccv_arr[7][2]},
            {value:iccv_arr[8][2]},
            {value:iccv_arr[9][2]},
            ]
        },
      
    ]
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