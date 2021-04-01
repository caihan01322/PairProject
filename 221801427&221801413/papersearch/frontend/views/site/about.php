<?php

/* @var $this yii\web\View */

use yii\helpers\Html;
use common\models\Paper;
use common\models\Keyword;

$this->title = '三大顶会论文数据';
$this->params['breadcrumbs'][] = $this->title;

$Eccv2016 = Paper::find()->andFilterWhere(['like', 'conference', 'ECCV 2016'])->count();
$Cvpr2016 = Paper::find()->andFilterWhere(['like', 'conference', 'CVPR2016'])->count();

$Eccv2020 = Paper::find()->andFilterWhere(['like', 'conference', 'ECCV 2020'])->count();
$Cvpr2020 = 486;

$Eccv2018 = Paper::find()->andFilterWhere(['like', 'conference', 'ECCV 2018'])->count();
$Cvpr2018 = Paper::find()->andFilterWhere(['like', 'conference', 'CVPR2018'])->count();


$total = (new \yii\db\Query())
		->select(['frequency'])
		->from('Keyword')
		->all();

$namelist = (new \yii\db\Query())
		->select(['name'])
		->from('Keyword')
		->all();

$name1 = $namelist[0];
$name2 = $namelist[1];	
$name3 = $namelist[2];	
$name4 = $namelist[3];	
$name5 = $namelist[4];	
$name6 = $namelist[5];	
$name7 = $namelist[6];	
$name8 = $namelist[7];	
$name9 = $namelist[8];	
$name10 = $namelist[9];		

$top1 = $total[0];
$top2 = $total[1];	
$top3 = $total[2];	
$top4 = $total[3];	
$top5 = $total[4];	
$top6 = $total[5];	
$top7 = $total[6];	
$top8 = $total[7];	
$top9 = $total[8];	
$top10 = $total[9];		
?>
<div class="site-about">
    <h1><?= Html::encode($this->title) ?></h1>

    <div class="chart0">
        <?php

            /* @var $this yii\web\View */

            // use yii\helpers\Html;

            $this->title = '图表样例';
            $this->params['breadcrumbs'][] = $this->title;
        ?>

         <script type='text/javascript' src="http://cdn.highcharts.com.cn/highcharts/9.0.1/highcharts.js"></script>



        <div class="site-about">
            <h1><?= Html::encode($this->title) ?></h1>
            <div id="chart01" style="margin: 0 auto;width: 900px;height:600px;"></div></br>
			<div id="chart02" style="margin: 0 auto;width: 900px;height:600px;"></div>

        </div>

        <script type="text/javascript">
		    var ECCV2016 = parseInt("<?php echo $Eccv2016; ?>");
			var CVPR2016 = parseInt("<?php echo $Cvpr2016; ?>");

			var ECCV2020 = parseInt("<?php echo $Eccv2020; ?>");
			var CVPR2020 = parseInt("<?php echo $Cvpr2020; ?>");

			var ECCV2018 = parseInt("<?php echo $Eccv2018; ?>");
			var CVPR2018 = parseInt("<?php echo $Cvpr2018; ?>");
            var chart = Highcharts.chart('chart01',{
				chart: {
					type: 'column'
				},
				title: {
					text: '2016、2018、2020ECCV与CVPR两大顶会历年论文篇数'
				},
				xAxis: {
					categories: [
					'2014','2016','2018'
					],
					crosshair: true
				},
				yAxis: {
					min: 0,
					title: {
						text: '论文篇数'
					}
				},
				tooltip: {
					// head + 每个 point + footer 拼接成完整的 table
					headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
					pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
					'<td style="padding:0"><b>{point.y} 篇数</b></td></tr>',
					footerFormat: '</table>',
					shared: true,
					useHTML: true
				},
				plotOptions: {
					column: {
						borderWidth: 0
					}
				},
				series: [{
					name: 'ECCV',
					data: [ECCV2016, ECCV2018,ECCV2020]
				}, 
				{
					name: 'CVPR',
					data: [CVPR2016,CVPR2018,CVPR2020]
				}]
			});
        </script>
        
		<script>
		var top1 = parseInt("<?php echo $top1['frequency']; ?>");
		var name1 ="<?php echo $name1['name']?>";

		var top2 = parseInt("<?php echo $top2['frequency']; ?>");
		var name2 ="<?php echo $name2['name']?>";

		var top3 = parseInt("<?php echo $top3['frequency']; ?>");
		var name3 ="<?php echo $name3['name']?>";

		var top4 = parseInt("<?php echo $top4['frequency']; ?>");
		var name4 ="<?php echo $name4['name']?>";

		var top5 = parseInt("<?php echo $top5['frequency']; ?>");
		var name5 ="<?php echo $name5['name']?>";

		var top6 = parseInt("<?php echo $top6['frequency']; ?>");
		var name6 ="<?php echo $name6['name']?>";

		var top7 = parseInt("<?php echo $top7['frequency']; ?>");
		var name7 ="<?php echo $name7['name']?>";

		var top8 = parseInt("<?php echo $top8['frequency']; ?>");
		var name8 ="<?php echo $name8['name']?>";

		var top9 = parseInt("<?php echo $top9['frequency']; ?>");
		var name9 ="<?php echo $name9['name']?>";

		var top10 = parseInt("<?php echo $top10['frequency']; ?>");
		var name10 ="<?php echo $name10['name']?>";
			// 创建渐变色
			Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
				return {
					radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
					stops: [
						[0, color],
						[1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
					]
				};
			});
		// 构建图表
		var chart = Highcharts.chart('chart02',{
			title: {
				text: '十大热词频率占比饼图'
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: true,
						format: '<b>{point.name}</b>: {point.percentage:.1f} %',
						style: {
							color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
						},
						connectorColor: 'silver'
					}
				}
			},
			series: [{
				type: 'pie',
				name: '热词占比',
				data: [
					[name1,   top1],
					[name2,   top2],
					{
						name: 'Chrome',
						y: 12.8,
						sliced: true,
						selected: true
					},
					[name3,   top3],
					[name4,   top4],
					[name5,   top5],
					[name6,   top6],
					[name7,   top7],
					[name8,   top8],
					[name9,   top9],
					[name10,   top10]
				]
			}]
		});
		</script>
    
       
    </div>

</div>