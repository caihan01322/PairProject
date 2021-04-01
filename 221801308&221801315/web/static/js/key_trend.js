window.onload = function () {
    var key;
    $.ajax({
        url: '/get_trend',
        type: 'get',
        cache: false,
        dataType: 'json',
        success: function (data) {
            //console.log(data.data);
            var myselect = $("#myselect");
            var mydata = data.data
            var str = '';
            $.each(mydata, function (index, item) {
                console.log(item.keyword);
                console.log(index);
                str += '<option value="' + index + '">' + item.keyword + '</option>';
                //console.log(str);
            })
            myselect.append(str);
            $("#myselect").change(function () {
                var options = $("#myselect option:selected");
                console.log(options.val());
                console.log(options.text());
                key = options.text();
                //console.log(key);
            });
        }
    });
    $('#key').click(function () {
        //console.log(key);
        var myChart = echarts.init(document.getElementById('main'));
        // 显示标题，图例和空的坐标轴
        function ajaxNew() {
            myChart.setOption({
                title: {
                    text: '热词十年走势'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['CVPR', 'ECCV', 'ICCV']
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: { show: true },
                        dataView: { show: true, readOnly: false },
                        magicType: { show: true, type: ['line', 'bar'] },
                        restore: { show: true },
                        saveAsImage: { show: true }
                    }
                },
                calculable: true,
                xAxis: {
                    type: 'category',
                    boundaryGap: false, //取消左侧的间距
                    data: []
                },
                yAxis: {
                    type: 'value',
                    splitLine: { show: false },//去除网格线
                    name: ''
                },
                series: [{
                    name: 'CVPR',
                    type: 'line',
                    symbol: 'emptydiamond',    //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
                    data: []
                },
                {
                    name: 'ECCV',
                    type: 'line',
                    symbol: 'emptydiamond',    //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
                    data: []
                },
                {
                    name: 'ICCV',
                    type: 'line',
                    symbol: 'emptydiamond',    //设置折线图中表示每个坐标点的符号 emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
                    data: []
                }]
            });
            var year = [];    //类别数组（实际用来盛放X轴坐标值）    
            var series1 = [];
            var series2 = [];
            var series3 = [];
            $.ajax({
                type: 'get',
                url: '/get_trend',//请求数据的地址
                dataType: "json",        //返回数据形式为json
                cache: false,
                success: function (data) {
                    $.each(data.data, function (index, item) {
                        console.log(item.keyword);
                        console.log(key);
                        if (item.keyword == key) {
                            console.log(item.CVPR)
                            $.each(item.CVPR, function (index, i) {
                                console.log(index + 2011)
                                console.log(i);
                                year.push(index + 2011);
                                series1.push(i);
                            });
                            $.each(item.ECCV, function (index, i) {
                                console.log(i);
                                series2.push(i);
                            });
                            $.each(item.ICCV, function (index, i) {
                                console.log(i);
                                series3.push(i);
                            })

                        }
                    });
                    myChart.setOption({        //加载数据图表
                        xAxis: {
                            data: year
                        },
                        series: [{
                            data: series1
                        },
                        {
                            data: series2
                        },
                        {
                            data: series3
                        }]
                    });
                },
                error: function (errorMsg) {
                    //请求失败时执行该函数
                    alert("图表请求数据失败!");
                }
            });
        }
        ajaxNew();
        setInterval(() => {
            ajaxNew();
        }, 15000);
    })
}