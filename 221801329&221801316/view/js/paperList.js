let instance = axios.create({
    baseURL: 'http://120.77.84.235:8080',
    headers: {
        'X-Requested-With': 'XMLHttpRequest'
    },
})
let searchStmt = '';
let searchType = '';

let isLogin = window.sessionStorage.getItem('isLogin');
if (!isLogin) window.location.href = 'login.html';

Highcharts.setOptions({
    lang: {
        thousandsSep: ','
    }
});
Highcharts.getOptions().colors.splice(0, 0, 'transparent');

function initPaperList() {
    document.getElementById("search-text").value = "";
    getPaperList(0);
}

function getPaperList(pageNum) {
    searchStmt = document.getElementById("search-text").value;
    let offset = document.getElementById('checklist').value;
    let router = '';
    if (offset === 'author') router = '/queryByAuthor'
    else if (offset === 'title') router = '/queryByTitle'
    else if (offset === 'keyword') router = '/queryByKeyword'
    instance.get(router, {
            params: {
                start: pageNum * 10,
                rows: 10,
                word: searchStmt,
                userId: sessionStorage.getItem('userId')
            }
        })
        .then(function(response) {
            searchType = "list";
            panel = document.getElementById('main-panel');
            panel.innerHTML = "";
            let data = response.data;
            setList(data, pageNum, searchType);

        })
        .then(function(error) {
            console.log(error);
        })
}

function getLikeList(pageNum) {
    instance.get('/queryLike', {
            params: {
                start: pageNum * 10,
                rows: 10,
                userId: sessionStorage.getItem('userId')
            }
        })
        .then(function(response) {
            searchType = "like";
            panel = document.getElementById('main-panel');
            panel.innerHTML = "";
            let data = response.data;
            setList(data, pageNum, searchType);
        })
        .then(function(error) {
            console.log(error);
        })
}

function setList(data, pageNum, type) {
    if (data.length === 0) {
        panel.innerHTML = panel.innerHTML + "<p style=\"text-align:center;color: rgb(127, 127, 127);\">No result</p>";
    } else {
        let list = data.paper;
        for (let k in list) {
            let element = list[k].data;
            let abstractStr = element['abstractContent'].slice(0, 100) + "...";
            let authorStr = "";
            let keywordStr = "";
            for (let t in element.author) {
                authorStr += element.author[t] + ';';
                if (t >= 3) {
                    break;
                }
            }
            authorStr = authorStr.slice(0, -1);
            for (let t in element.keywords) {
                keywordStr += element.keywords[t] + ';';
                if (t >= 3) {
                    break;
                }
            }
            keywordStr = keywordStr.slice(0, -1);
            let sytle = "like";
            let src = '../img/gary-star.svg'
            if (list[k].isLike === 1) {
                src = '../img/orange-star.svg'
            }
            panel.innerHTML = panel.innerHTML +
                "<div class=\"paper-list\" id=" +
                element.id +
                "><a href=" +
                element.link +
                " class=\"paper-title\">" +
                element.title +
                "</a>" +
                "<p class=\"paper-author\">" +
                authorStr +
                "</p> <p> <span class=\"paper-abstract-title\">[Abstract]</span>" +
                "<span class=\"paper-abstract-detial\">" +
                abstractStr +
                "</span></p>" +
                "<p><span class=\"paper-keyword\">[Keyword]</span>" +
                "<span class=\"paper-keyword-list\">" +
                keywordStr +
                "</span></p>" +
                "<img src=" + src + ' onclick=like(' +
                element.id + ')' + ' id=Like' +
                element.id + ' class=' + sytle + '>' +
                '</div>'

        }
        initPagination(pageNum, Math.floor(data.total / 10) + 1, type);
    }

}

function like(data) {
    let ID = 'Like' + data;
    let star = document.getElementById(ID);
    let src = star.getAttribute('src');
    let router = '';
    if (src === '../img/gary-star.svg') {
        router = '/addLike'
    } else {
        router = '/deleteLike'
    }
    instance.get(router, { params: { userId: sessionStorage.getItem('userId'), paperId: data } })
        .then(res => {
            if (router == '/addLike') {
                swal("收藏成功！", "点击继续", 'success')
            } else {
                swal("取消收藏成功！", "点击继续", 'success')
            }
            star.setAttribute('src', (src == '../img/gary-star.svg') ? '../img/orange-star.svg' : '../img/gary-star.svg');
        })
}

function initPagination(currentPage, totalPage, type) {
    console.log(totalPage)
    panel = document.getElementById('main-panel');
    let start;
    let end;
    if (totalPage < 8) {
        start = 1;
        end = totalPage;
    } else {
        start = currentPage - 4;
        end = currentPage + 3;
        if (start < 1) {
            start = 1;
            end = start + 7;
        }
        if (end > totalPage) {
            end = totalPage;
            start = end - 7;
        }
    }
    let str = '<nav aria-label="Page navigation">' +
        '<ul class="pagination">';

    if (type === 'like') {
        for (let i = start; i <= end; i++) {
            if (currentPage == i - 1) {
                var li = "<li class=\"active\"><a onclick=getLikeList(" + (i - 1) + ")>" + i + "</a></li>";
            } else {
                var li = "<li><a onclick=getLikeList(" + (i - 1) + ")>" + i + "</a></li>";
            }
            str += li;
        }
    } else if (type === 'list') {
        for (let i = start; i <= end; i++) {
            if (currentPage == i - 1) {
                var li = "<li class=\"active\"><a onclick=getPaperList(" + (i - 1) + ")>" + i + "</a></li>";
            } else {
                var li = "<li><a onclick=getPaperList(" + (i - 1) + ")>" + i + "</a></li>";
            }
            str += li;
        }
    }
    str += '</ul></nav>'
    panel.innerHTML = panel.innerHTML + str;
}

function getIndex() {
    panel = document.getElementById('main-panel');
    panel.innerHTML = '' +
        '<div class="img-panel">' +
        '<img src="../img/u98.png" class="img-box" alt="">' +
        '<span class="img-span">A batch/recursive algorithm for 3D scene reconstruction</span>' +
        '<span class="img-keyword">Camera Rotation</span>' +
        '</div>' +
        '<div class="img-panel">' +
        '<img src="../img/u98.png" class="img-box" alt="">' +
        '<span class="img-span">A batch/recursive algorithm for 3D scene reconstruction</span>' +
        '<span class="img-keyword">Camera Rotation</span>' +
        '</div>';
}


function getSunBurst() {
    panel = document.getElementById("")
    panel = document.getElementById('main-panel');
    panel.innerHTML = '<div id="container"></div>';

    instance.post('/queryTop10ByYear', {}).then(res => {
        list = res.data
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

function logout() {
    sessionStorage.clear();
    window.location.href = 'login.html';
}

function getRaceChart() {
    panel = document.getElementById("")
    panel = document.getElementById('main-panel');
    panel.innerHTML += '<div id="race"></div>';
    let dom = document.getElementById("race");
    let myChart = echarts.init(dom);
    let option;

    instance.get('/keywordTrends', {}).then(res => {
        setTimeout(function() {
            console.log(JSON.stringify(res.data))
            option = {
                legend: {},
                tooltip: {
                    trigger: 'axis',
                    showContent: false
                },
                dataset: {
                    source: res.data
                },
                xAxis: { type: 'category' },
                yAxis: { gridIndex: 0 },
                grid: { top: '55%' },
                series: [
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    { type: 'line', smooth: true, seriesLayoutBy: 'row', emphasis: { focus: 'series' } },
                    {
                        type: 'pie',
                        id: 'pie',
                        radius: '30%',
                        center: ['50%', '25%'],
                        emphasis: { focus: 'data' },
                        label: {
                            formatter: '{b}: {@2016} ({d}%)'
                        },
                        encode: {
                            itemName: 'product',
                            value: '2016',
                            tooltip: '2016'
                        }
                    }
                ]
            };

            myChart.on('updateAxisPointer', function(event) {
                let xAxisInfo = event.axesInfo[0];
                if (xAxisInfo) {
                    let dimension = xAxisInfo.value + 1;
                    myChart.setOption({
                        series: {
                            id: 'pie',
                            label: {
                                formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                            },
                            encode: {
                                value: dimension,
                                tooltip: dimension
                            }
                        }
                    });
                }
            });

            myChart.setOption(option);

        });

        if (option && typeof option === 'object') {
            myChart.setOption(option);
        }
    })

}

function initCharts() {
    getSunBurst()
    getRaceChart()
}