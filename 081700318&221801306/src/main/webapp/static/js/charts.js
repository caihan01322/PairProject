$(document).ready(function () {
  var myChart = echarts.init(document.getElementById("chart1"));
  var data = genData(10);
  option = {
    title: {
      text: "TOP10关键词统计",
      left: "center",
    },
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b} : {c} ({d}%)",
    },
    series: [
      {
        name: "热词",
        type: 'pie',
        radius: ['20%', '80%'],
        avoidLabelOverlap: false,
        roseType: 'radius',
        data: data.seriesData,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
      },
    ],
  };

  function genData(count) {
    var legendData = [];
    var seriesData = [];
    $.ajax({
      url: "http://localhost:8080/thesisSearch_war_exploded/Count?count="+count,
      dataType: "json", //数据格式
      type: "get", //请求方式
      async: false, //是否异步请求
      success: function (data) {
        //如果请求成功，返回数据。
        $.each(data, function (i, item) {
          legendData.push(item.keyword);
          seriesData.push({
            name: item.keyword,
            value: item.nums,
            url:
              "http://localhost:8080/thesisSearch_war_exploded/Search?searchtype=title&input=" +
              item.keyword,
          });
        });
      },
    });
    function ymData(year,meeting)
    {
      var legendData = [];
      var seriesData = [];
      $.ajax({
        url: "http://localhost:8080/thesisSearch_war_exploded/Count?year=" + year + "&meeting=" + meeting,
        dataType: "json", //数据格式
        type: "get", //请求方式
        async: false, //是否异步请求
        success: function (data) {
          //如果请求成功，返回数据。
          $.each(data, function (i, item) {
            legendData.push(item.keyword);
            seriesData.push({
              name: item.keyword,
              value: item.nums,
              url:
                  "http://localhost:8080/thesisSearch_war_exploded/Search?searchtype=title&input=" +
                  item.keyword,
            });
          });
        },
      });
    }
    return {
      legendData: legendData,
      seriesData: seriesData,
    };
  }
  myChart.setOption(option);
  myChart.on("click", function (param) {
    //console.log(param);
    var url = param.data.url;
    window.location.href = url;
  });
  cloud();
  line();

function cloud () {
    var myChart = echarts.init(document.getElementById("chart2"));
    var data = genData(50);
    option = {
      title: {
        text: "TOP50关键词云",
        left: "center",
      },
      layoutAnimation: true,
      backgroundColor:'#fff',
      tooltip: {
        show: false
      },
      series: [{
        type: 'wordCloud',
        gridSize: 1,
        sizeRange: [12, 55],
        rotationRange: [-45, 0, 45, 90],
        textStyle: {
            color: function () {
              return 'rgb(' + [
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255),
                Math.round(Math.random() * 255)
              ].join(',') + ')';
            }

        },
        left: 'center',
        top: 'center',
        // width: '96%',
        // height: '100%',
        right: null,
        bottom: null,
        // width: 300,
        // height: 200,
        // top: 20,
        data: genData(50).seriesData
      }]
    };
    myChart.setOption(option);
    myChart.on("click", function (param) {
      //console.log(param);
      var url = param.data.url;
      window.location.href = url;
    });
  }


  function line () {
    var myChart = echarts.init(document.getElementById("chart3"));
    var data = genData(50);
    option = {
      title: {
        text: '折线图堆叠'
      },
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        data: ['ICCB', 'ECCV', 'CVPR']
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['2001', '2002', '2003', '2004', '2005', '2006', '2007']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: '邮件营销',
          type: 'line',
          stack: '总量',
          data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
          name: '联盟广告',
          type: 'line',
          stack: '总量',
          data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
          name: '视频广告',
          type: 'line',
          stack: '总量',
          data: [150, 232, 201, 154, 190, 330, 410]
        },
        {
          name: '直接访问',
          type: 'line',
          stack: '总量',
          data: [320, 332, 301, 334, 390, 330, 320]
        },
        {
          name: '搜索引擎',
          type: 'line',
          stack: '总量',
          data: [820, 932, 901, 934, 1290, 1330, 1320]
        }
      ]
    };
    myChart.setOption(option);
    myChart.on("click", function (param) {
      //console.log(param);
      var url = param.data.url;
      window.location.href = url;
    });
  }
});


