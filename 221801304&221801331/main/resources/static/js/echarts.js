function BarLoad(beginYear,endYear,conference)
{
  bar.showLoading();
  $.post('/getTop10Keywords/',{beginYear:beginYear,
    endYear:endYear,conference:conference},function(data)
  {
    bar.hideLoading();
    data=JSON.parse(data);

    //加载词云
    CloudLoad(data);
    bar.setOption
    ({
      title: {
        text: beginYear==endYear?beginYear:(beginYear+'~'+endYear)+'年'+conference+'热词TOP10'
      },
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


function LineLoad(beginYear,endYear,keyword)
{
  line.showLoading();
  $.post('/getKeywordCount/',{beginYear:beginYear,endYear:endYear,keyword:keyword},function(data)
  {
    line.hideLoading();
    data=JSON.parse(data);
    line.setOption
    ({
      title: {
        text: keyword+'历年热度走势图'
      },
      xAxis: {
        data: data.x
      },
      series: [
        {
          name: 'CVPR',
          type: 'line',
          data: data.CVPR
        },
        {
          name: 'ICCV',
          type: 'line',
          data: data.ICCV
        },{
          name: 'ECCV',
          type: 'line',
          data: data.ECCV
        }]
    });
  });
}


