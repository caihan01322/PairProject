window.onload = function () {
    $("#key").click(function () {
        var myChart = echarts.init(document.getElementById('main'));
        // 显示标题，图例和空的坐标轴
        function ajaxCake() {
            myChart.setOption({
                tooltip: {
                    trigger: 'item',
                    formatter: "{b}:{c}({d}%)"
                },
                series: [{
                    name: '次数',
                    type: 'pie',
                    radius: '55%',
                    label: {
                        normal: {
                            show: true,
                            formatter: "{b}:{c}({d}%)"
                        }
                    },
                    data: []
                }]
            });
            // 异步加载数据
            $.get('/get_cake').done(function (data) {
                // 填入数据
                var words = [];
                var times = [];
                var content = [];
                console.log(data)
                console.log(data.data)
                $.each(data.data, function (index, item) {
                    console.log(item.keyword);
                    console.log(item.total);
                    content.push({
                        value: item.total,
                        name: item.keyword
                    })
                    // words.push(item.keyword);
                    // times.push(item.total);
                })
                myChart.setOption({
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '次数',
                        data: content
                    }]
                });
                myChart.on('click', function (param) {
                    //这个params可以获取你要的图中的当前点击的项的参数
                    var keyword = param.name;
                    console.log(keyword);
                    $.ajax({
                        data: { 'keyword': keyword },
                        url: '/get_cake',
                        type: 'get',
                        cache: false,
                        dataType: 'json',
                        success: function (data) {
                            console.log(data);
                            console.log(data.data.url);
                            $.each(data.data, function (index, item) {
                                console.log(item.keyword);
                                console.log(item.url);
                                if (item.keyword == keyword) {
                                    location.href = item.url;
                                }
                            })
                            //location.href="URL"
                        }
                    });
                });

            });
        }
        ajaxCake();
        setInterval(() => {
            ajaxCake();
        }, 10000);
    });
}