<%@ page pageEncoding="utf-8" %>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,minimal-ui">
    <title>动态分析</title>
    <link rel='stylesheet' href='css/myCss.css' type='text/css'/>
    <link rel='stylesheet' href='css/myCss2.css' type='text/css'/>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/modules/variable-pie.js"></script>
</head>

<body style="background-color:#F8F8F8 ;">
<div id="app" class="surface--content">
    <el-backtop></el-backtop>
    <header class="metabar">
        <div class="container u-flex">
            <!--
            <a href="/" class="u-flex u-relative"><img class="logo" src=".jpg" alt="图标"></a>
            -->
            <nav class="header-nav">
                <ul class="subnav-ul">
                    <li class="menu-item"><a href="">首页</a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="">论文导入</a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="">论文管理</a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="">动态分析</a></li>
                </ul>
            </nav>
            <div class="metabar__right u-flex">
                <div class="metabarItem u-flex"><a href="" class="u-flex" title="上传" >
                    <svg t="1616220697148" class="icon" width="25" height="24"  viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2435" width="200" height="200"><path d="M522.24 760.32c-15.36 0-25.6-10.24-25.6-25.6V448c0-15.36 10.24-25.6 25.6-25.6s25.6 10.24 25.6 25.6v289.28c0 12.8-12.8 23.04-25.6 23.04z" fill="#333333" p-id="2436"></path><path d="M601.6 545.28c-7.68 0-12.8-2.56-17.92-7.68l-61.44-61.44L460.8 537.6c-10.24 10.24-25.6 10.24-35.84 0s-10.24-25.6 0-35.84l79.36-79.36c10.24-10.24 25.6-10.24 35.84 0l79.36 79.36c10.24 10.24 10.24 25.6 0 35.84-5.12 5.12-10.24 7.68-17.92 7.68z" fill="#333333" p-id="2437"></path><path d="M755.2 780.8h-151.04c-15.36 0-25.6-10.24-25.6-25.6s10.24-25.6 25.6-25.6h151.04c79.36 0 143.36-64 143.36-143.36s-64-143.36-143.36-143.36c-23.04 0-48.64 5.12-69.12 17.92-7.68 5.12-17.92 5.12-25.6 0-7.68-5.12-12.8-12.8-12.8-23.04 0-71.68-56.32-128-128-128-58.88 0-110.08 40.96-125.44 97.28-2.56 7.68-5.12 12.8-12.8 15.36-5.12 2.56-12.8 5.12-20.48 2.56-10.24-2.56-20.48-5.12-28.16-5.12-61.44 0-110.08 48.64-110.08 110.08 0 10.24-7.68 20.48-17.92 23.04-35.84 12.8-58.88 46.08-58.88 81.92 0 48.64 38.4 87.04 87.04 87.04h168.96c15.36 0 25.6 10.24 25.6 25.6s-10.24 25.6-25.6 25.6H243.2h-2.56c-79.36 5.12-145.92-58.88-145.92-138.24 0-53.76 30.72-102.4 76.8-125.44 7.68-81.92 76.8-145.92 161.28-145.92h17.92c25.6-69.12 92.16-115.2 166.4-115.2 87.04 0 158.72 61.44 176.64 143.36 17.92-5.12 38.4-10.24 58.88-10.24 107.52 0 194.56 87.04 194.56 194.56 0 117.76-87.04 204.8-192 204.8z" fill="#333333" p-id="2438"></path></svg>
                    上传论文</a>
                </div>
                <div class="metabarItem u-flex u-cursorPointer" v-on:click="switchSearch"><a href="" class="u-flex" title="上传" >
                    <svg t="1616220263438" class="svgIcon-use" width="18" height="17" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1653" width="200" height="200"><path d="M890.233 419.484c-1.895 0-3.81 0-5.71 0l-28.555 0L855.968 310.982c0-43.782-34.26-43.782-45.68-43.782L524.759 267.2l-36.167-78.042c-5.712-11.42-15.227-17.132-26.65-17.132L153.57 172.026c-34.262 0-59.007 47.587-59.007 72.332l0 586.286c0 28.555 28.55 64.715 59.007 64.715l727.143 0c30.46 0 53.305-34.26 53.305-64.715L934.018 459.456C932.108 440.424 920.688 419.484 890.233 419.484zM798.868 324.304l0 95.18L593.283 419.484l-43.785-95.18L798.868 324.304zM875.008 830.643c0 1.9 0 5.71-1.91 7.615L157.38 838.258c-1.907-1.905-5.712-5.715-5.712-7.615L151.668 244.357c0-3.805 3.805-11.42 7.612-15.225l283.627 0 104.697 230.324c3.81 9.517 15.23 17.135 26.65 17.135l300.754 0L875.008 830.643z" p-id="1654"></path></svg>
                    收藏夹</a>
                </div>
                <metabar-notice v-bind:noticenum="noticeNum" v-bind:islogin="islogin"></metabar-notice>
                <div class="metabarItem u-flex u-paddingLeft20" ><a href="" class="u-flex" title="上传" >
                    <span class="" >注册 | 登录</span></a>
                </div>
            </div>
        </div>
    </header>
</div>

<div id="analysis_container">
<div id="analysis_left_container"></div>
<script>
	var chart = Highcharts.chart('analysis_left_container', {
		chart: {
			type: 'bar'
		},
		colors: ['#FE4365', '#FC9D9A', '#F9CDAD'],
		title: {
			text: '三 大 顶 会 年 度 热 词', style: {
				fontSize: '27px'
			}
		},
		subtitle: {
			text: '数据来源: Wikipedia.org'
		},
		xAxis: {
			categories: ['热词1', '热词2', '热词3', '热词4', '热词5'],
			title: {
				text: '热词', style: {
					fontSize: '20px'
				}
			},
			labels: {
				style: {
					fontSize: '17px'
				}
			}
		},
		yAxis: {
			min: 0,
			title: {
				text: '出现次数 (次)',
				align: 'high'
			},
			labels: {
				overflow: 'justify',
				style: {
					fontSize: '15px'
				}
			}
		},
		tooltip: {
			valueSuffix: ' 次'
		},
		plotOptions: {
			bar: {
				dataLabels: {
					enabled: true,
					allowOverlap: true, // 允许数据标签重叠
					style: {
		                'fontSize' : '13px'
		            }
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
			shadow: true,
			itemStyle: {
		        'fontSize' : '15px'
		    }
		},
		credits: {
			enabled:false  //去水印
		},
		series: [{
			name: 'CVPR',
			data: [107, 31, 635, 203, 2],
		}, {
			name: 'ICCV',
			data: [133, 156, 947, 408, 6]
		}, {
			name: 'ECCV',
			data: [973, 914, 4054, 732, 34]
		}]
	});
	/*$(document).ready(function(){
		var chart = new Highcharts.Chart(chart);
		$("button.change").click(function(){
			chart.xAxis[0].setCategories(['1', '2', '3', '4', '5']);
			chart.series[0].setData([673, 214, 404, 752, 440]);
		});
	});*/
</script>

<div id="analysis_right_container"></div>
<script>
    Highcharts.chart('analysis_right_container', {
        chart: {
            type: 'variablepie'
        },
        colors: ['#FE4365', '#FC9D9A', '#F9CDAD', '#C8C8A9', '#83AF9B'],
        title: {
            text: '热 门 论 文 关 键 词', style: {
				fontSize: '27px'
			}
        },
        subtitle: {
            text: '本站前十热门关键词，点击可查看相关论文'  
        },
        tooltip: {
            headerFormat: '',
            pointFormat: '<span style="color:{point.color}">\u25CF</span> <b> {point.name}</b><br/>' +
                '相关论文数 (篇): <b>{point.y}</b><br/>' /*+
                '人口密度 (每平方千米人数): <b>{point.z}</b><br/>'*/
        },
        plotOptions: {
        	variablepie: {
				dataLabels: {
					//enabled: true,
					//allowOverlap: true, // 允许数据标签重叠
					style: {
		                'fontSize' : '15px'
		            }
				}
			}
		},
        credits: {
			enabled:false  //去水印
		},
        series: [{
            minPointSize: 10,
            innerSize: '20%',
            zMin: 0,
            name: 'countries',
            data: [{
                name: 'Seven',
                y: 505370,
                z: 92.9
            }, {
                name: 'August',
                y: 551500,
                z: 118.7
            }, {
                name: 'Cardigan',
                y: 312685,
                z: 124.6
            }, {
                name: 'The 1',
                y: 78867,
                z: 137.5
            }, {
                name: 'Mirrorball',
                y: 301340,
                z: 171.8
            }, {
                name: 'Evermore',
                y: 41277,
                z: 214.5
            }, {
                name: 'Exile',
                y: 357022,
                z: 205.6
            }, {
                name: 'Illicit affairs',
                y: 390166,
                z: 157.4
            }, {
                name: 'Ivy',
                y: 290166,
                z: 197.4
            }, {
                name: 'Hoax',
                y: 650166,
                z: 227.9
            }]
        }]
    });
</script>
</div>

</body>