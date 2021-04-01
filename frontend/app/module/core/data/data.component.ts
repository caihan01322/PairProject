import { Component, OnInit, ViewChild } from '@angular/core';
import * as Highcharts from 'highcharts';
import { DataService } from '../../service/data.service';


@Component({
    selector: 'app-data',
    templateUrl: './data.component.html',
    styleUrls: ['./data.component.css']
})
export class DataComponent implements OnInit {

    constructor(private dataService: DataService) { }

    isSpinning = true;
    public ECCVyears: any = [];
    public ECCVdata: any = [];
    public IEEEyears: any = [];
    public IEEEdata: any = [];
    public KwdData : any = [];
    paperNum = 0;
    @ViewChild('piechart') pieChart: any;
    @ViewChild('ieeechart') ieeeChart: any;
    @ViewChild('kwdchart') kwdchart: any;
    

    getFromLocalStorage(key: string) {
        let d: any = localStorage.getItem(key);
        return JSON.parse(d);
    }

    updateChartSetting(anyChart: any, showData: any, years: any) {
        let ops: any = {
            name: "引用次数",
            type: 'line',
            data: showData
        };
        anyChart.chart.addSeries(ops);
        anyChart.chart.xAxis[0].setCategories([...years]);
    }


    updatePieSData(kwd : any){
        kwd.forEach((element:any) => {
            this.KwdData.push({
                name : element.key,
                y:element.value
            })
        });;

        this.kwdchart.chart.addSeries({
            type: "pie",
            // 数据含义
            name: '总引用占比',
            colorByPoint: true,
            data: this.KwdData
        })
    }

    ngOnInit(): void {
        this.dataService.getStatisticsData()
            .then(res => {
                this.dataService.getYears(res.lineData.eccvYears, this.ECCVyears);
                this.dataService.getYears(res.lineData.ieeeYears, this.IEEEyears);
                this.dataService.getData(res.lineData.ECCV, this.ECCVdata);
                this.dataService.getData(res.lineData.IEEE, this.IEEEdata);
                this.updateChartSetting(this.pieChart, this.ECCVdata, this.ECCVyears);
                this.updateChartSetting(this.ieeeChart, this.IEEEdata, this.IEEEyears);
                this.updatePieSData(res.keywords);
                this.paperNum = res.count;
                this.isSpinning = false;
            });
    }


    Highcharts = Highcharts;
    // ECCV折线图
    chartOptions: Highcharts.Options = {
        title: {
            text: 'ECCV热词走势'
        },
        subtitle: {
            text: '数据来源：www.informatik.uni-trier.de'
        },
        xAxis: {
            categories: []
        },
        credits: {
            enabled: false
        },
        yAxis: {
            title: {
                text: '引用次数'
            }
        },
        plotOptions: {
            series: {
                label: {
                    connectorAllowed: false,
                },
            }
        },
        series: [],
        responsive: {
            rules: [{
                condition: {
                    maxWidth: 500
                },
                chartOptions: {
                    legend: {
                        layout: 'horizontal',
                        align: 'center',
                        verticalAlign: 'bottom'
                    }
                }
            }]
        }
    }



    // IEEE折线图
    ieeeOptions: Highcharts.Options = {
        title: {
            text: 'IEEE热词走势'
        },
        subtitle: {
            text: '数据来源：www.informatik.uni-trier.de'
        },
        xAxis: {
            categories: []
        },
        credits: {
            enabled: false
        },
        yAxis: {
            title: {
                text: '引用次数'
            }
        },
        plotOptions: {
            series: {
                label: {
                    connectorAllowed: false,
                },
            }
        },
        series: [],
        responsive: {
            rules: [{
                condition: {
                    maxWidth: 500
                },
                chartOptions: {
                    legend: {
                        layout: 'horizontal',
                        align: 'center',
                        verticalAlign: 'bottom'
                    }
                }
            }]
        }
    }

    // 饼图
    keywordOptions: Highcharts.Options = {
        chart: {
            plotShadow: false,
            type: 'pie'
          },
          // 图标题
          title: {
            text: 'Top10热词占比'
          },
          tooltip: {
            // 鼠标放到每一块显示的文字，.1f表示小数点后一位
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
          },
          // 去除版权信息
          credits: {
            enabled: false
          },
          plotOptions: {
            pie: {
              allowPointSelect: true,
              cursor: 'pointer',
              dataLabels: {
                enabled: true,
                // 初始状态下每一块的tip
                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                style: {
                  color: 'black'
                }
              }
            }
          },
          // 数据配置
          series: []
        }
}
