<?php session_start();?>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,minimal-ui">
    <title>动态分析</title>
    <link rel='stylesheet' href='../css/myCss.css' type='text/css'/>
    <link rel='stylesheet' href='../css/myCss2.css' type='text/css'/>
    <link rel='stylesheet' href='../css/myCss3.css' type='text/css'/>
    <link rel='stylesheet' href='../css/myCss4.css' type='text/css'/>
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
    <script src="https://code.highcharts.com.cn/highcharts/modules/variable-pie.js"></script>
</head>

<body style="background-color:#F8F8F8 ;">
<div id="app" class="surface--content">

    <header class="metabar">
        <div class="container u-flex">
            <!--
            <a href="/" class="u-flex u-relative"><img class="logo" src=".jpg" alt="图标"></a>
            -->
            <nav class="header-nav">
                <ul class="subnav-ul">
                    <li class="menu-item"><a href="../index.php">首页</a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="import.php">论文导入</a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="manage.php">论文管理</a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="analysis.php"><b style="font-size: larger">动态分析</b></a></li>
                </ul>
            </nav>
            <div class="metabar__right u-flex">
                <div class="metabarItem u-flex"><a href="" class="u-flex" title="上传" >
                    <svg t="1616220697148" class="icon" width="25" height="24"  viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2435" width="200" height="200"><path d="M522.24 760.32c-15.36 0-25.6-10.24-25.6-25.6V448c0-15.36 10.24-25.6 25.6-25.6s25.6 10.24 25.6 25.6v289.28c0 12.8-12.8 23.04-25.6 23.04z" fill="#333333" p-id="2436"></path><path d="M601.6 545.28c-7.68 0-12.8-2.56-17.92-7.68l-61.44-61.44L460.8 537.6c-10.24 10.24-25.6 10.24-35.84 0s-10.24-25.6 0-35.84l79.36-79.36c10.24-10.24 25.6-10.24 35.84 0l79.36 79.36c10.24 10.24 10.24 25.6 0 35.84-5.12 5.12-10.24 7.68-17.92 7.68z" fill="#333333" p-id="2437"></path><path d="M755.2 780.8h-151.04c-15.36 0-25.6-10.24-25.6-25.6s10.24-25.6 25.6-25.6h151.04c79.36 0 143.36-64 143.36-143.36s-64-143.36-143.36-143.36c-23.04 0-48.64 5.12-69.12 17.92-7.68 5.12-17.92 5.12-25.6 0-7.68-5.12-12.8-12.8-12.8-23.04 0-71.68-56.32-128-128-128-58.88 0-110.08 40.96-125.44 97.28-2.56 7.68-5.12 12.8-12.8 15.36-5.12 2.56-12.8 5.12-20.48 2.56-10.24-2.56-20.48-5.12-28.16-5.12-61.44 0-110.08 48.64-110.08 110.08 0 10.24-7.68 20.48-17.92 23.04-35.84 12.8-58.88 46.08-58.88 81.92 0 48.64 38.4 87.04 87.04 87.04h168.96c15.36 0 25.6 10.24 25.6 25.6s-10.24 25.6-25.6 25.6H243.2h-2.56c-79.36 5.12-145.92-58.88-145.92-138.24 0-53.76 30.72-102.4 76.8-125.44 7.68-81.92 76.8-145.92 161.28-145.92h17.92c25.6-69.12 92.16-115.2 166.4-115.2 87.04 0 158.72 61.44 176.64 143.36 17.92-5.12 38.4-10.24 58.88-10.24 107.52 0 194.56 87.04 194.56 194.56 0 117.76-87.04 204.8-192 204.8z" fill="#333333" p-id="2438"></path></svg>
                    上传论文</a>
                </div>
                <div class="metabarItem u-flex u-cursorPointer" v-on:click="switchSearch"><a href="collect.php" class="u-flex" title="收藏" >
                    <svg t="1616220263438" class="svgIcon-use" width="18" height="17" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1653" width="200" height="200"><path d="M890.233 419.484c-1.895 0-3.81 0-5.71 0l-28.555 0L855.968 310.982c0-43.782-34.26-43.782-45.68-43.782L524.759 267.2l-36.167-78.042c-5.712-11.42-15.227-17.132-26.65-17.132L153.57 172.026c-34.262 0-59.007 47.587-59.007 72.332l0 586.286c0 28.555 28.55 64.715 59.007 64.715l727.143 0c30.46 0 53.305-34.26 53.305-64.715L934.018 459.456C932.108 440.424 920.688 419.484 890.233 419.484zM798.868 324.304l0 95.18L593.283 419.484l-43.785-95.18L798.868 324.304zM875.008 830.643c0 1.9 0 5.71-1.91 7.615L157.38 838.258c-1.907-1.905-5.712-5.715-5.712-7.615L151.668 244.357c0-3.805 3.805-11.42 7.612-15.225l283.627 0 104.697 230.324c3.81 9.517 15.23 17.135 26.65 17.135l300.754 0L875.008 830.643z" p-id="1654"></path></svg>
                    收藏夹</a>
                </div>
                <metabar-notice v-bind:noticenum="noticeNum" v-bind:islogin="islogin"></metabar-notice>
                                <div class="metabarItem u-flex u-paddingLeft20 dropdown" >
                    <a href="" class="u-flex" title="用户" >
                    <svg t="1616238407449" class="icon" width="40" height="39" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2253" width="200" height="200"><path d="M512 0a512 512 0 1 0 512 512A512 512 0 0 0 512 0zM213.333 832A298.667 298.667 0 0 1 512 533.333a170.667 170.667 0 1 1 170.667-170.666A170.667 170.667 0 0 1 512 533.333 298.667 298.667 0 0 1 810.667 832z" p-id="2254" fill="#e6e6e6"></path></svg>
                    </a>
                    <?php 
                    $conn = new mysqli('localhost','root','','paperdb');
                    if(!isset($_SESSION["userid"])){
                        echo '<script>alert("请先登录！");window.location.href="../view/login.php";</script>';
                    }
                    $userid=$_SESSION["userid"];
                    $sql = "select * from user where userid = '$userid' ";
                    $result = $conn->query($sql);
                    $number = mysqli_num_rows($result);
                    $row=$result ->fetch_assoc();
                    echo $row["username"];
                    ?>
                    <div class="dropdown-content">
    				<a href="">个人中心</a><br/>
    				<a onclick="exitLogin()">退出登录</a>
  					</div>
                </div>
            </div>
        </div>
    </header>
 </div>

<div id="analysis_container">
<div id="analysis_left_container"></div>

<?php
    $servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "paperdb";
    // 数据库连接
    $conn = new mysqli($servername, $username, $password, $dbname);
    // Check connection
    if ($conn->connect_error) {
        die("连接失败: " . $conn->connect_error);
    }

    function getTopKeys($sql, $conn, $nums) {
        $result = $conn->query($sql);
        $result_array = array();
        $key_array = array();
        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $result_array[] = $row["key1"]; 
                $result_array[] = $row["key2"];
                $result_array[] = $row["key3"];
                $result_array[] = $row["key4"];
                $result_array[] = $row["key5"];
            }
        }
        foreach($result_array as $value){
            if(!empty($value)){
                $value = ucfirst($value);
                //$value = str_replace(' ', '<br>', $value);  //词组遇空格则分行
                if(array_key_exists($value, $key_array)){
                    $key_array[$value] += 10;   //+=1
                }
                else{
                    $key_array[$value] = 10;   //=1
                }
            }
        }
        arsort($key_array);
        return array_slice($key_array, 0, $nums);
    }
    
    $year = array('2016', '2018', '2020');
    $arr = array();
    $arr['ECCV'] = array();
    $arr['ECCV'][$year[0]] = getTopKeys("SELECT key1, key2, key3, key4, key5 FROM paper WHERE meeting = 'ECCV' AND year = '".$year[0]."'", $conn, 5);
    $arr['ECCV'][$year[1]] = getTopKeys("SELECT key1, key2, key3, key4, key5 FROM paper WHERE meeting = 'ECCV' AND year = '".$year[1]."'", $conn, 5);
    $arr['ECCV'][$year[2]] = getTopKeys("SELECT key1, key2, key3, key4, key5 FROM paper WHERE meeting = 'ECCV' AND year = '".$year[2]."'", $conn, 5);
    echo 
"<script>
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
		xAxis: {
			categories: ".json_encode(array_keys($arr['ECCV'][$year[2]])).",
			/*title: {
				text: '热词', style: {
					fontSize: '18px'
				}
			},*/
			labels: {
				style: {
					fontSize: '14px'
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
		exporting: {
			enabled: true,
		    buttons: {
		    	contextButton: {
		    		enabled: true
		    	},
		    	firstYearButton: {
		    		y: 25,
		    		x: -770,
		    		align: 'right',
		    		text: ".$year[0].",
		    		hoverSymbolFill: null,
		    		onclick: function() {
		    			chart.series[2].setData(".json_encode(array_values($arr['ECCV'][$year[0]])).");
		    		},
		    		theme: {
		    			style: {
							color: 'grey',
							fontSize: '15px',
							textDecoration: 'underline'
						},
		    			stroke: 'white',
		    			states: {
		    				hover: {
		    					fill: 'white',
		    					style: {
		    						color: 'black'
		    					}
		    				},
		    				select: {
		    					stroke: '#039',
		    					fill: 'white',
		    					style: {
		    						color: 'black'
		    					}
		    				}
		    			}
		    		}
		    	},
		    	secondYearButton: {
		    		y: 25,
		    		x: -700,
		    		align: 'right',
		    		text: ".$year[1].",
		    		hoverSymbolFill: null,
		    		onclick: function() {
		    			chart.series[2].setData(".json_encode(array_values($arr['ECCV'][$year[1]])).");
		    		},
		    		theme: {
		    			style: {
							color: 'grey',
							fontSize: '15px',
							textDecoration: 'underline'
						},
		    			stroke: 'white',
		    			states: {
		    				hover: {
		    					fill: 'white',
		    					style: {
		    						color: 'black'
		    					}
		    				},
		    				select: {
		    					stroke: '#039',
		    					fill: 'white',
		    					style: {
		    						color: 'black'
		    					}
		    				}
		    			}
		    		}
		    	},
		    	thirdYearButton: {
		    		y: 25,
		    		x: -630,
		    		align: 'right',
		    		text: ".$year[2].",
		    		hoverSymbolFill: null,
		    		onclick: function() {
		    			chart.series[2].setData(".json_encode(array_values($arr['ECCV'][$year[2]])).");
		    		},
		    		theme: {
		    			style: {
							color: 'grey',
							fontSize: '15px',
							textDecoration: 'underline'
						},
		    			stroke: 'white',
		    			states: {
		    				hover: {
		    					fill: 'white',
		    					style: {
		    						color: 'black'
		    					}
		    				},
		    				select: {
		    					stroke: '#039',
		    					fill: 'white',
		    					style: {
		    						color: 'black'
		    					}
		    				}
		    			}
		    		}
		    	},
		    }
		},
		navigation: {
			buttonOptions: {
				height: 40,
				width: 48,
				symbolSize: 24,
				symbolX: 23,
				symbolY: 21,
				symbolStrokeWidth: 2
			}
		},
		credits: {
			enabled:false  //去水印
		},
		series: [{
			name: 'CVPR',
			data: [507, 931, 2635, 203, 1522],
		}, {
			name: 'ICCV',
			data: [833, 156, 947, 408, 2006]
		}, {
			name: 'ECCV',
			//data: [973, 914, 2054, 732, 34]
            data: ".json_encode(array_values($arr['ECCV'][$year[2]]))."
		}]
	});
</script>"; 

    $top_ten_key = getTopKeys("SELECT key1, key2, key3, key4, key5 FROM paper", $conn, 10);
    $top_key_array = array_keys($top_ten_key);
    $top_value_array = array_values($top_ten_key);
    
    echo "
<div id=\"analysis_right_container\"></div>
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
            pointFormat: '<span style=\"color:{point.color}\">\u25CF</span> <b> {point.name}</b><br/>' +
                /*'相关论文数 (篇): <b>{point.y}</b><br/>' +*/
                '相关论文数 (篇): <b>{point.z}</b><br/>'
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
			},
            series: { 
                cursor: 'pointer', 
                events: { 
                    click: function(e) { 
                        var paperName = e.point.name;
                        window.location.href = \"search.php?searchName=\" + paperName + \"&searchSelect=3\"; 
                    } 
                } 
            }
		},
		navigation: {
			buttonOptions: {
				height: 40,
				width: 48,
				symbolSize: 24,
				symbolX: 23,
				symbolY: 21,
				symbolStrokeWidth: 2
			}
		},
        credits: {
			enabled:false  //去水印
		},
        series: [{
            minPointSize: 10,
            innerSize: '20%',
            zMin: 0,
            name: 'keys',
            data: [{
                name: '".$top_key_array[9]."',
                y: 200,
                z: ".$top_value_array[9]."
            }, {
                name: '".$top_key_array[8]."',
                y: 300,
                z: ".$top_value_array[8]."
            }, {
                name: '".$top_key_array[7]."',
                y: 400,
                z: ".$top_value_array[7]."
            }, {
                name: '".$top_key_array[6]."',
                y: 500,
                z: ".$top_value_array[6]."
            }, {
                name: '".$top_key_array[5]."',
                y: 300,
                z: ".$top_value_array[5]."
            }, {
                name: '".$top_key_array[4]."',
                y: 200,
                z: ".$top_value_array[4]."
            }, {
                name: '".$top_key_array[3]."',
                y: 400,
                z: ".$top_value_array[3]."
            }, {
                name: '".$top_key_array[2]."',
                y: 600,
                z: ".$top_value_array[2]."
            }, {
                name: '".$top_key_array[1]."',
                y: 400,
                z: ".$top_value_array[1]."
            }, {
                name: '".$top_key_array[0]."',
                y: 500,
                z: ".$top_value_array[0]."
            }]
        }]
    });
</script>";
    $conn->close();
?>
</div>

</body>

<script type="text/javascript">
function exitLogin(){
	window.event.returnValue=false;     
	window.location.href="../form/exit.php";
}
</script>
