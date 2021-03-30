let globalBaseURL = 'http://120.77.84.235:8080';
let instance = axios.create({
    baseURL: 'http://120.77.84.235:8080',
    headers: {
        'X-Requested-With': 'XMLHttpRequest'
    },
});


function getSunBurst() {
    panel = document.getElementById("")
    panel = document.getElementById('main-panel');
    panel.innerHTML = '<div id="container"></div>';

    Highcharts.setOptions({
        lang: {
            thousandsSep: ','
        }
    });
    instance.post('/queryTop10ByYear', {}).then(res => {
        list = res.data
        console.log(Highcharts.getOptions().colors)
        Highcharts.getOptions().colors.splice(0, 0, 'transparent');
        Highcharts.chart('container', {
            chart: {
                height: '100%'
            },
            title: {
                text: '2017-2020 CVPR ECCV ICCV热点论文收录情况统计'
            },
            subtitle: {
                text: '数据来源： <href="">不完全统计</a>'
            },
            series: [{
                type: "sunburst",
                data: res.data,
                allowDrillToNode: true,
                cursor: 'pointer',
                dataLabels: {
                    formatter: function() {
                        let shape = this.point.node.shapeArgs;
                        let innerArcFraction = (shape.end - shape.start) / (2 * Math.PI);
                        let perimeter = 2 * Math.PI * shape.innerR;
                        let innerArcPixels = innerArcFraction * perimeter;
                        if (innerArcPixels > 16) {
                            return this.point.name;
                        }
                    }
                },
                levels: [{
                    level: 2,
                    colorByPoint: true,
                    dataLabels: {
                        rotationMode: 'parallel'
                    }
                }, {
                    level: 3,
                    colorletiation: {
                        key: 'brightness',
                        to: -0.5
                    }
                }, {
                    level: 4,
                    colorletiation: {
                        key: 'brightness',
                        to: 0.5
                    }
                }]
            }],
            tooltip: {
                headerFormat: "",
                pointFormat: '<b>{point.name}</b>收录论文共计：<b>{point.value}篇</b>'
            }
        });
    })
}