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
      url: "Count?count="+count,
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
              "Search?searchtype=title&input=" +
              item.keyword,
          });
        });
      },
    });
    return {
      legendData: legendData,
      seriesData: seriesData,
    };
  }
    function ymData(year,meeting)
    {
      var legendData = [];
      var seriesData = [];
      $.ajax({
        url: "Count?year=" + year + "&meeting=" + meeting,
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
                  "Search?searchtype=title&input=" +
                  item.keyword,
            });
          });
        },
      });
      var myChart = echarts.init(document.getElementById("chart1"));
      myChart.setOption({
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
                data: seriesData,
                emphasis: {
                  itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: "rgba(0, 0, 0, 0.5)",
                  },
                },
              },
            ],
          }
      )
    }
  myChart.setOption(option);
  myChart.on("click", function (param) {
    //console.log(param);
    var url = param.data.url;
    window.location.href = url;
  });
  cloud();
  $('.chart-button').click(function (){
  year=$('#Year-Select option:selected').text();
  meeting=$('#meeting-Select option:selected').text();
  ymData(year,meeting);
})

  $("#meeting-Select").change(function(){
    meeting=$('#meeting-Select option:selected').text();
    if(meeting=="ECCV")
    {
      optionArr=[{value:1,name:2016},{value:1,name:2018},{value:1,name:2020}];
    }
    else
    {
      optionArr=[{value:1,name:2009},{value:1,name:2011},{value:1,name:2013},{value:1,name:2015},{value:1,name:2017},{value:1,name:2019}];
    }
      select=$('#Year-Select');
      select.empty();
      for(i=0;i<optionArr.length;i++)
      {
      select.append(`<option value=${optionArr[i].value}>${optionArr[i].name}</option>`);
      }




  });
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

});


