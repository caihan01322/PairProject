$(document).ready(function () {
  var myChart = echarts.init(document.getElementById("chart1"));
  var data = genData();

  option = {
    title: {
      text: "热度前20关键词统计",
      left: "center",
    },
    tooltip: {
      trigger: "item",
      formatter: "{a} <br/>{b} : {c} ({d}%)",
    },
    legend: {
      type: "scroll",
      orient: "vertical",
      right: 10,
      top: 20,
      bottom: 20,
      data: data.legendData,

      selected: data.selected,
    },
    series: [
      {
        name: "热词",
        type: "pie",
        radius: "55%",
        center: ["40%", "50%"],
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

  function genData() {
    var legendData = [];
    var seriesData = [];
    $.ajax({
      url: "http://localhost:8080/thesisSearch_war_exploded/Count", //某大佬提供的api希望一直有效
      dataType: "json", //数据格式
      type: "get", //请求方式
      async: false, //是否异步请求
      success: function (data) {
        //如果请求成功，返回数据。
        $.each(data, function (i, item) {
          legendData.push(item.keyword);
          seriesData.push({ name: item.keyword, value: item.nums });
          console.log(seriesData[i]);
        });
      },
    });

    return {
      legendData: legendData,
      seriesData: seriesData,
    };
  }

  myChart.setOption(option);
});
