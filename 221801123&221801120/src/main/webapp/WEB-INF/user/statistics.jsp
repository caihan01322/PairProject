<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>热门分析</title>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" href="css/echart.css">
    <script src="https://cdn.jsdelivr.net/npm/echarts@5/dist/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/script/echarts-wordcloud.js"></script>

</head>
<body>
<!--头部-->
<jsp:include page="share/head.jsp"/>
<!--中间主体部分-->

<div id="wordcloudDiv"></div>
<script>
    var chart = echarts.init(document.getElementById("wordcloudDiv"));
    var option = {
        title: {
            text: 'Top10',
            x: 'center',
            textStyle: {
                fontSize: 35,
                color:'#a9aaac'
            }
        },
        tooltip: {
            show: true
        },
        series: [ {
            type: 'wordCloud',
            gridSize: 1,
            sizeRange: [15, 35],
            rotationRange: [-45, 90],
            shape: 'triangle',
            width: 400,
            height: 300,
            drawOutOfBound: false,
            textStyle: {
                color: function () {
                    return 'rgb(' + [
                        Math.round(Math.random() * 166),
                        Math.round(Math.random() * 166),
                        Math.round(Math.random() * 166)
                    ].join(',') + ')';
                }
            },
            emphasis: {
                textStyle: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                },

                //阴影距离
                shadowBlur:1,

                //阴影颜色
                shadowColor:'#111'
            },
            data: [
                {
                    name: 'Computer vision',
                    value: 3887,
                    textStyle: {
                        color: 'black'
                    },
                    emphasis: {
                        textStyle: {
                            color: 'red'
                        }
                    }
                },
                {
                    name: 'feature extraction',
                    value: 3439
                },
                {
                    name: 'Cameras',
                    value: 2860
                },
                {
                    name: 'image segmentation',
                    value: 2856
                },
                {
                    name: 'learning (artificial intelligence)',
                    value: 2493
                },
                {
                    name: 'Object detection',
                    value: 2487
                },
                {
                    name: 'training',
                    value: 2028
                },
                {
                    name: 'image reconstruction',
                    value: 1951
                },
                {
                    name: 'image classification',
                    value: 1623
                },
                {
                    name: 'face recognition',
                    value: 1485
                }
            ]
        } ]
    };

    chart.setOption(option);

    chart.on('click',function(params){
        var name = params.data.name;
        window.location.href="<%=basePath%>user/thesis/list?keyword="+name;
    });
    window.onresize = chart.resize;
</script>
<%-- 尾部 --%>
<jsp:include page="share/footer.jsp"/>
</body>
</html>
