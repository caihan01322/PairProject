import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import * as echart from 'echarts';
import { RequestService } from 'src/app/services/request.service';
import { NzMessageService } from 'ng-zorro-antd/message';
@Component({
  selector: 'app-tendency',
  templateUrl: './tendency.component.html',
  styleUrls: ['./tendency.component.css']
})
export class TendencyComponent implements OnInit {
  public  echarts: any;
  public Eccv_first:any=[];
  public Cvpr_first:any=[];
  public Iccv_first:any=[];

  public Pie_key:any=[];
  public Pie_value:any=[];
  public pie_object:any=[];
  public pie_num:any;

  public childrens:any=[];
  public GRAPH1:any = {
    title: {
      text: '最近几年顶会论文数量',
      textStyle:{
        fontSize:13   
    }
  },
  tooltip: {
      trigger: 'axis'
  },
  legend: {
      data: ['cvpr', 'iccv', 'eccv']
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
      data: [2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021]
  },
  yAxis: {
      type: 'value'
  },
  series: [
      {
          name: 'cvpr',
          type: 'line',
          stack: '总量1',
          data: this.Cvpr_first
      },
      {
          name: 'iccv',
          type: 'line',
          stack: '总量2',
          data: this.Iccv_first
      },
      {
          name: 'eccv',
          type: 'line',
          stack: '总量3',
          data:this.Eccv_first
      }
  ]
  };

  public GRAPH2:any = {
    title:{ text:'近三年top3研究领域论文数量'
        ,textStyle:{
            fontSize:13   
        }
    },
    legend: {},
    tooltip: {},
    dataset: {
        dimensions: ['paper', '2018', '2019', '2020'],
        source: [
            {paper: 'image_segment', '2018': 143, '2019': 163, '2020': 230},
            {paper: 'object_dection', '2018': 120, '2019': 170, '2020': 220},
            {paper: 'object_tracking', '2018': 111, '2019': 173, '2020': 210},
        ]
    },
    xAxis: { name:'研究方向',type: 'category'},
    yAxis: {name: '论文数量'},
    // Declare several bar series, each will be mapped
    // to a column of dataset.source by default.
    series: [
        {type: 'bar'},
        {type: 'bar'},
        {type: 'bar'}
    ]
  };

  public GRAPH3:any = {
    title:{
      text:'2020年论文方向占比'  ,
      textStyle:{
          fontSize:13
      }
    },
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)'
    },
    legend: {
        top: 'bottom'
    },
    toolbox: {
        show: true,
        feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    series: [
        {
            name: '面积模式',
            type: 'pie',
            radius: [20, 200],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
                borderRadius: 8
            },
            data: [
                {value: 40, name: '图像分割'},
                {value: 38, name: '图像分类'},
                {value: 32, name: '目标检测'},
                {value: 30, name: '目标跟踪'},
                {value: 28, name: '语义识别'},
                {value: 26, name: '判别分析'},
                {value: 22, name: '高清处理'},
                {value: 18, name: '人脸识别'}
            ]
        }
    ]
  };    

  public GRAPH4:any = {
      title:{
        text:'论文方向旭日图',
        textStyle:{
            fontSize:14
        }
      },
      series: {
        type: 'sunburst',
        // emphasis: {
        //     focus: 'ancestor'
        // },
        data:
        [{
            name: '',
            children: [{
                name: 'CVPR',
                value: 11,
                children: [
                    {
                    name: 'Division',
                    value: 2
                    },
                    {
                        name: 'Networks',
                        value: 5,
                    },
                    {
                    name: 'MultiModel',
                    value: 4
                    }
                ]
            }, {
                name: 'ICCV',
                value: 11,
                children: [
                    {
                        name: 'TransferLearning',
                        value: 2
                    },
                    {
                        name: 'Recognition',
                        value: 5,
                    },
                    {
                        name: 'GAN',
                        value: 4
                    }
                ]
            },{
                name: 'ICCV',
                value: 25,
                children: [
                    {
                        name: 'Point Cloud',
                        value: 10
                    },
                    {
                        name: 'GAN',
                        value: 5
                    },
                    {
                        name: 'BLSTM',
                        value: 10
                    }
                ]
            }
            
            ]
        }]
        ,
        radius: [0, '90%'],
        label: {
            rotate: 'radial'
        }
    },
    tooltip: {
    headerFormat: "",
    pointFormat: '<b>{point.name}</b>收录论文共计：<b>{point.value}篇</b>'
  }
  };    

  constructor(public request:RequestService, private message: NzMessageService) { 
    
  }

  getLineChart(){
    let Data=this.request.getData("http://47.96.231.121/getLineChart",{});
    Data.then((data:any)=>{
      this.Cvpr_first=data.data[0]['CVPR']
      this.Eccv_first=data.data[0]['ECCV']
      this.Iccv_first=data.data[0]['ICCV']
      console.log(data.data[0])
    }).then(()=>{
        this.GRAPH1.series[0].data=this.Cvpr_first;
        this.GRAPH1.series[1].data=this.Iccv_first;
        this.GRAPH1.series[2].data=this.Eccv_first;
        // console.log(this.GRAPH1.series[0].data)
        const myDemo = echart.init(document.getElementById('echarts1') as HTMLDivElement);
        myDemo.setOption(this.GRAPH1);
    })
}

   getPieChart(){
       let Data=this.request.getData("http://47.96.231.121/getTop10",{});
       Data.then((data:any)=>{
        //    console.log(data)
           this.Pie_key=data.data[0]['key'];
           this.Pie_value=data.data[0]['value'];
           this.pie_num=data.data[0]['num'];
           for (let index = 0; index < 8; index++) {
            this.pie_object.push({value:this.Pie_value[index],name:this.Pie_key[index]})
               
           }
        //    console.log(this.pie_object)

       }).then(()=>{
        const myDemo3 = echart.init(document.getElementById('echarts3') as HTMLDivElement);
        this.GRAPH3.series[0].data=this.pie_object;
        myDemo3.setOption(this.GRAPH3);
       })
   }
   getRisingSun(){
       let Data=this.request.getData("http://47.96.231.121/getRisingSun",{});
       Data.then((data:any)=>{
           console.log(Data)
           this.childrens=data.data
           console.log(this.childrens)
       }).then(()=>{
        const myDemo4 = echart.init(document.getElementById('echarts4') as HTMLDivElement);
        this.GRAPH4.series.data=this.childrens;
        myDemo4.setOption(this.GRAPH4);
        this.message.remove();
       })
   }




  ngOnInit(): void {
    // const myDemo = echart.init(document.getElementById('echarts1') as HTMLDivElement);
    // myDemo.setOption(this.GRAPH1);
    const myDemo2 = echart.init(document.getElementById('echarts2') as HTMLDivElement);
    myDemo2.setOption(this.GRAPH2);
    
    const id = this.message.loading('正在加载中');
    this.getLineChart();
    this.getPieChart();
    this.getRisingSun();
    

  }
  
//   ngAfterViewInit():void{
//     const myDemo = echart.init(document.getElementById('echarts1') as HTMLDivElement);
//     myDemo.setOption(this.GRAPH1);
//     const myDemo2 = echart.init(document.getElementById('echarts2') as HTMLDivElement);
//     myDemo2.setOption(this.GRAPH2);
//     const myDemo3 = echart.init(document.getElementById('echarts3') as HTMLDivElement);
//     myDemo3.setOption(this.GRAPH3);
//     const myDemo4 = echart.init(document.getElementById('echarts4') as HTMLDivElement);
//     myDemo4.setOption(this.GRAPH4);
//   }

}

