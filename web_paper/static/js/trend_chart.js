var chart = Highcharts.chart('container', {
		title: {
				text: '2013 ~ 2020 各峰会论文发布走势图'
		},
		subtitle: {
				text: '数据来源：三峰会:ICCV,ECCV,,CVPR'
		},
		yAxis: {
				title: {
						text: '论文数量'
				}
		},
		legend: {
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle'
		},
		plotOptions: {
				series: {
						label: {
								connectorAllowed: false
						},
						pointStart: 2010
				}
		},
		series: [{
				name: 'ICCV',
				data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
		}, {
				name: 'ECCV',
				data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
		}, {
				name: 'CVPR',
				data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
		}],
		responsive: {
				rules: [{
						condition: {
								maxWidth: 500
						},
						chartOptions: {
								legend: {
										layout: 'horizontal',
										align: 'center',
										verticalAlign: 'bottom'
								}
						}
				}]
		}
});