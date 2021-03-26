function refresh(beginYear,endYear,conference)
{
  myChart.showLoading();
  $.post('/getTop10Keywords/',{beginYear:beginYear,
    endYear:endYear,conference:conference},function(data)
  {
    myChart.hideLoading();
    data=JSON.parse(data);
    myChart.setOption
    ({
      xAxis: {
        data: data.x
      },
      series: [
        {
          type: 'bar',
          data: data.y
        }]
    });
  });
}

