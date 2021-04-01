import { Component, OnInit } from '@angular/core';
import * as echart from 'echarts';
import { timer } from 'rxjs';
import { RequestService } from 'src/app/services/request.service';
import { NzMessageService } from 'ng-zorro-antd/message';
@Component({
  selector: 'app-top-trend',
  templateUrl: './top-trend.component.html',
  styleUrls: ['./top-trend.component.css']
})
export class TopTrendComponent implements OnInit {
  public data:any = [];
  public datanum:any=[]
  public index:any = 0;
  public disable:any = false;
  public GRAPH1:any = {
    title: {
      text: 'top10领域论文数量',
      textStyle:{
        fontSize:14  
      },
      subtext: '据不完全统计'
  },
  tooltip: {
      trigger: 'axis',
      axisPointer: {
          type: 'shadow'
      }
  },
  legend: {
      data: ['2020年']
  },
  grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
  },
  xAxis: {
      type: 'value',
      boundaryGap: [0, 0.01],
  },
  yAxis: {
      type: 'category',
      data: this.data
  },
  series: [
      {
          name: '2020年',
          type: 'bar',
          data: this.datanum
      }
  ]
  };

  constructor(public request:RequestService,public message:NzMessageService) { }
  getTop10(){
    let Data=this.request.getData("http://47.96.231.121/getTop10",{});
    Data.then((data:any)=>{
      console.log(data.data[0]['key'])
      this.datanum=data.data[0]['value']
      this.data=data.data[0]['key']
    }).then((data)=>{
      console.log(data)
      const myDemo = echart.init(document.getElementById('echarts5') as HTMLDivElement);
      this.GRAPH1.yAxis.data=this.data
      this.GRAPH1.series[0].data=this.datanum
      myDemo.setOption(this.GRAPH1);
      this.message.remove();
    })


}

ngAfterContentInit(){
  this.message.loading('正在加载中');
  this.getTop10()
}

  ngOnInit(): void {
    

    
  }

  ngAfterViewInit():void{
  }

  
}
