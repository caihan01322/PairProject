<%--
  Created by IntelliJ IDEA.
  User: 武雍易
  Date: 2021/3/31
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>">
    <!-- 引入 ECharts 文件 -->
    <script src="script/echarts.min.js"></script>
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/footer.css">
    <%--<link rel="stylesheet" type="text/css" href="mainCSS.css">
    <link rel="stylesheet" type="text/css" href="cvCSS.css">--%>
</head>
<jsp:include page="share/head.jsp"/>

</div>

<div id="body">
    <table>
        <tr>
            <div id="main" style="width:70%;height:80%;margin: auto"></div>
        </tr>
    </table>
</div>

<script type="text/javascript" charset="UTF-8">
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var yearlist = ['2011','2013','2015','2017','2019'];
    var countryList = ['image','computer','analysis','vision','layout','recognize','detection'];
    var flagIcons = {
        'usa': 'usa.png',
        'chn': 'chn.png',
        'jpn': 'jpn.png',
        'deu': 'deu.png',
        'gbr': 'gbr.png',
        'fra': 'fra.png',
        'ita': 'ita.png',
        'ind': 'ind.png'
    };
    var option = {
        timeline:{
            axisType: 'category',
            autoPlay: true,
            playInterval: 1500,
            data: yearlist,
            label:{
                fontSize: 12
            }
        },

        baseOption:{
            dataset:{
                source:[
                    ['year','image','computer','analysis','vision','layout','recognize','detection'],
                    ['2011',272,150,106,41,32,20,36],
                    ['2013',158,126,101,24,87,32,39],
                    ['2011',273,120,136,61,82,10,56],
                    ['2017',102,80,59,59,48,20,30],
                    ['2019',200,78,63,42,46,48,35],
                ]
            },

            title: {
                text: '2000年ICCV走势图',
                left: 'center',
                textStyle:{
                    fontSize:12,
                    color: '#faf8ff'
                }
            },
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            legend: {
            },
            grid: {
                left: '10%',
                bottom: '15%',
                containLabel: true
            },
            xAxis: [
                {
                    type:'category',
                    data: countryList,
                    axisPointer: {
                        type: 'shadow'
                    },

                    axisLabel: {
                        formatter: function (value) {
                            return '{value|' + value + '}';
                        },
                        margin: 20,
                        rich: {
                            value: {
                                lineHeight: 30,
                                fontSize: 18,
                                align: 'center'
                            },
                            image: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.usa
                                }
                            },
                            computer: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.chn
                                }
                            },
                            analysis: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.jpn
                                }
                            },
                            vision: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.deu
                                }
                            },
                            layout: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.gbr
                                }
                            },
                            recognize: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.fra
                                }
                            },
                            detection: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.ita
                                }
                            },
                            tomography: {
                                height: 20,
                                align: 'center',
                                backgroundColor: {
                                    image: flagIcons.ind
                                }
                            },
                        }
                    }

                },
            ],
            yAxis:[
                {
                    name:'热度',
                    type:'value',
                    nameTextStyle:{
                        fontSize:18
                    },
                    axisLabel:{
                        fontSize:18
                    }
                },
            ],
            series: [
                {
                    //name: 'GDP',
                    type: 'bar',
                    seriesLayoutBy: 'row',
                    encode:{
                        x:'year',
                        y:'2001'
                    },
                    itemStyle:{
                        normal:{
                            color:function(params){
                                var colorlist = ['#21c2ff','#5c9fff','#936dfb','#cf8cfb','#49FBF4','#00ff77','#F774FB','#7D1FFB'];
                                return colorlist[params.dataIndex];
                            }
                        }
                    }
                },
            ]
        },
        options:[]
    };

    for (var n = 0; n<yearlist.length; n++){
        option.options.push({
            title:{
                show:true,
                text:yearlist[n]+'年ICCV热度走势',
                left: 'center',
                textStyle:{
                    fontSize:24
                }
            },
            series:[
                {
                    type: 'bar',
                    seriesLayoutBy: 'row',
                    encode:{
                        x:'year',
                        y:yearlist[n]
                    }
                },
            ]
        });
    }
    myChart.setOption(option);
</script>
<jsp:include page="share/footer.jsp"/>
</body>
</html>
