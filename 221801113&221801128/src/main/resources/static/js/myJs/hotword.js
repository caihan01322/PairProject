$(function(){




    var str1 = [];
    var str2 = [];
    var data2 = [];
    var cnt = 0;
    var cnt2 = 0;
    var cnt3 = 0;

    $.ajax({
        type: "GET",
        contentType: "application/json;charset=UTF-8",
        url: "/hot",
        success: function(res) {
            for(var key in res){
                str2[cnt2] = key;
                data2[cnt2] = res[key];
                cnt3+=data2[cnt2];
                cnt2++;
                for(var n = 0;n<res[key];n++) {
                    str1[cnt] = key;
                    cnt++;
                }
            }

            var text = str1.toString();
            var data = text.split(/[,\. ]+/g)
                .reduce(function (arr, word) {
                    var obj = arr.find(function (obj) {
                        return obj.name === word;
                    });
                    if (obj) {
                        obj.weight += 1;
                    } else {
                        obj = {
                            name: word,
                            weight: 1
                        };
                        arr.push(obj);
                    }
                    return arr;
                }, []);
            //第一个容器
            Highcharts.chart('container', {
                series: [{
                    type: 'wordcloud',
                    data: data
                }],
                title: {
                    text: ''
                }
            });
            //第二个容器
            var chart = Highcharts.chart('container2',{
                chart: {
                    type: 'column'
                },
                title: {
                    text: '顶会热词统计'
                },
                subtitle: {
                    text: ''
                },
                xAxis: {
                    categories: str2,
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '数量'
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                        '<td style="padding:0"><b>{point.y:.0f} 个</b></td></tr>',
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
                    name: '',
                    data: data2
                }]
            });

            //第三个容器
            Highcharts.getOptions().plotOptions.pie.colors = (function () {
                var colors = [],
                    base = Highcharts.getOptions().colors[0],
                    i;
                for (i = 0; i < 10; i += 1) {
                    colors.push(Highcharts.Color(base).brighten((i - 3) / 14).get());
                }
                return colors;
            }());
            // 初始化图表
            var chart = Highcharts.chart('container3', {
                title: {
                    text: ''
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
                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '',
                    data: [
                        [str2[0], (data2[0]/cnt3)*100],
                        [str2[1], (data2[1]/cnt3)*100],
                        [str2[2], (data2[2]/cnt3)*100],
                        [str2[3], (data2[3]/cnt3)*100],
                        [str2[4], (data2[4]/cnt3)*100],
                        [str2[5], (data2[5]/cnt3)*100],
                        [str2[6], (data2[6]/cnt3)*100],
                        [str2[7], (data2[7]/cnt3)*100],
                        [str2[8], (data2[8]/cnt3)*100],
                        [str2[9], (data2[9]/cnt3)*100]
                    ]
                }]
            });

            //第四个容器
            var chart = Highcharts.chart('container4', {
                chart: {
                    type: 'spline'
                },
                title: {
                    text: ''
                },
                subtitle: {
                    text: ''
                },
                xAxis: {
                    categories: str2
                },
                yAxis: {
                    title: {
                        text: '数量'
                    },
                    labels: {
                        formatter: function () {
                            return this.value;
                        }
                    }
                },
                tooltip: {
                    crosshairs: true,
                    shared: true
                },
                plotOptions: {
                    spline: {
                        marker: {
                            radius: 4,
                            lineColor: '#666666',
                            lineWidth: 1
                        }
                    }
                },
                series: [{
                    name: '',
                    data: data2
                }]
            });
            $("text").click(function () {
                console.log($(this).text().trim())
                localStorage.setItem("hot", $(this).text().trim());
                window.location = 'http://118.25.6.96:8080/main'
            })
        },
        //请求失败，包含具体的错误信息
        error: function(res){
            console.log(res.status);
            console.log(res.responseText);
        }
    });
})