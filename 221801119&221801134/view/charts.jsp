<%@ page pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Charts</title>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
</head>
<body>
<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>
<script>
	var chart = Highcharts.chart('container', {
		chart: {
			type: 'bar'
		},
		colors: ['#FE4365', '#FC9D9A', '#F9CDAD'],
		title: {
			text: '三 大 顶 会 历 年 热 词'
		},
		subtitle: {
			text: '数据来源: Wikipedia.org'
		},
		xAxis: {
			categories: ['热词1', '热词2', '热词3', '热词4', '热词5'],
			title: {
				text: '热词'
			}
		},
		yAxis: {
			min: 0,
			title: {
				text: '出现次数 (次)',
				align: 'high'
			},
			labels: {
				overflow: 'justify'
			}
		},
		tooltip: {
			valueSuffix: ' 次'
		},
		plotOptions: {
			bar: {
				dataLabels: {
					enabled: true,
					allowOverlap: true // 允许数据标签重叠
				}
			}
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'top',
			x: -40,
			y: 100,
			floating: true,
			borderWidth: 1,
			backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
			shadow: true
		},
		credits: {
			enabled:false  //去水印
		},
		series: [{
			name: 'CVPR',
			data: [107, 31, 635, 203, 2]
		}, {
			name: 'ICCV',
			data: [133, 156, 947, 408, 6]
		}, {
			name: 'ECCV',
			data: [973, 914, 4054, 732, 34]
		}]
	});
	$(document).ready(function(){
		var chart = new Highcharts.Chart(chart);

		/*$("button.change").click(function(){
			chart.xAxis[0].setCategories(['1', '2', '3', '4', '5']);
			chart.series[0].setData([673, 214, 404, 752, 440]);
		});*/
	});

</script>
</body>
</html>