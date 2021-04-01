<template>
  <div id="main_frame">
    <el-row class="row" type="flex" justify="space-around">
      <el-col class="frame" :span="10">
        <wordcloud
            id="wordcloud"
            :data="top40Data"
            nameKey="name"
            :color="wordCloudColor"
            valueKey="value"
            :fontsize='[0,100]'
            fontScale="n"
            :showTooltip="false"
            :rotate="{from: 0, to: 0, numOfOrientation: 1 }"
            :wordClick="wordClickHandler">
        </wordcloud>
      </el-col>
      <el-col class="frame1" :span="10">
        <div id="top10Chart"></div>
      </el-col>
    </el-row>

    <div class="frame2">
      <div id="hotTrendWordChart" ref="hotTrendWordChart"></div>

    </div>

    <div id="hot_trend_paper_title">{{ hotTrendWord+'相关论文' }}</div>
    <SearchList ref="searchList2" v-if="hotTrendWord!==''"
                :searchWord="this.hotTrendWord" :searchInput="searchInput">
    </SearchList>
  </div>
</template>

<script>

import wordcloud from 'vue-wordcloud'
import axios from "axios";
import * as echarts from 'echarts'
import SearchList from "@/components/SearchList";

export default {
  name:"statisticList",
  components:{
    wordcloud,
    SearchList
  },
  props:{
    searchInput:{},
  },
  data(){
    return {
      wordCloudColor:[
        "#c12e34",
        "#e6b600",
        "#0098d9",
        "#2b821d",
        "#005eaa",
        "#339ca8",
        "#cda819",
        "#32a487",
        "#c1232b",
        "#27727b",
        "#fcce10",
        "#e87c25",
        "#b5c334",
        "#fe8463",
        "#9bca63",
        "#fad860",
        "#f3a43b",
        "#60c0dd",
        "#d7504b",
        "#c6e579",
        "#f4e001",
        "#f0805a",
        "#26c0c0"
      ],
      top40Data:[],
      top10LegendData:[],
      top10Data:[],
      hotTrendWord:'training',
      CVPRData:[],
      ICCVData:[],
      ECCVData:[]
    }
  },
  watch:{
    top10Data(){
      this.drawLine()
    },
    hotTrendWord(){
      this.$message.success('当前热词为'+this.hotTrendWord)
      this.fillHotWordChart()
      this.$refs.searchList2.$props.searchWord=this.hotTrendWord
      Object.assign(this.$refs.searchList2.$data,this.$refs.searchList2.$options.data())
      this.$refs.searchList2.search()
    },
    CVPRData(){
      this.drawLineChart()
    }
  },
  methods:{
    wordClickHandler(name){
      this.hotTrendWord=name
    },
    fillTop40Chart(){
      const loading=this.$loading({
        lock:true,//lock的修改符--默认是false
        text:'加载中...',//显示在加载图标下方的加载文案
        spinner:'el-icon-loading',//自定义加载图标类名
        background:'rgba(226,226,226,0.7)',//遮罩层颜色
        target:document.querySelector('.frame')//loading覆盖的dom元素节点
      });
      axios
          .get("http://121.5.100.116:8080/api/Top40")
          .then(response=>{
            this.top40Data=response.data.data
            loading.close()
          });
    },
    fillTop10Chart(){
      const loading1=this.$loading({
        lock:true,//lock的修改符--默认是false
        text:'加载中...',//显示在加载图标下方的加载文案
        spinner:'el-icon-loading',//自定义加载图标类名
        background:'rgba(226,226,226,0.7)',//遮罩层颜色
        target:document.querySelector('.frame1')//loading覆盖的dom元素节点
      });
      axios
          .get("http://121.5.100.116:8080/api/Top10")
          .then(response=>{
            this.top10Data=response.data.data
            for(let i=0; i<response.data.data.length; i++){
              this.top10LegendData.push(response.data.data[i].name)
            }
            loading1.close()
          })
    },
    fillHotWordChart(){
      axios
          .get("http://121.5.100.116:8080/api/hotTrend?keyword="+this.hotTrendWord.toString())
          .then(response=>{
            this.CVPRData=response.data.data.cvpr
            this.ICCVData=response.data.data.iccv
            this.ECCVData=response.data.data.eccv
          })
      this.drawLineChart()
    },
    drawLine(){
      // 基于准备好的dom，初始化echarts实例
      let myChart=this.$echarts.init(document.getElementById('top10Chart'))
      var option={
        //title图的标题，副标题，以及位置设置
        title:{
          text:'TOP10热词图',
          x:'center'
        },
        tooltip:{
          trigger:'item',
          //提示框的数据样式显示
          formatter:"{a}<br/>{b}：{c}篇文章 ({d}%) "
        },
        //图例相关内容的说明
        legend:{
          show:false,
          x:'left',
          y:'center',
          //图例的排列默认属性是：水平或垂直,默认是：水平
          orient:'vertical',
          data:this.top10LegendData
        },
        /*
         * toolbox为工具栏；提供导出图片，数据转化，动态数据等信息
         * 保存图片
         */
        toolbox:{
          show:true,//是否显示工具栏组件
          feature:{
            //mark : {show: true},
            //显示图像所提供的数据
            dataView:{show:true,readOnly:false},
            //启用的动态数据类型
            magicType:{
              show:true,
              type:['pie','funnel']
            },
            //配置项还原
            restore:{show:true},
            //保存为图片
            saveAsImage:{show:true}
          }
        },
        calculable:true,//手柄拖拽调整选中的范围
        series:[{
          name:'TOP10热词',
          type:'pie',
          radius:[50,140],
          top:'40px',
          // center:[250,180],
          roseType:'radius',
          width:'100%',// for funnel
          max:40,            // for funnel
          itemStyle:{
            //普通样式设置
            normal:{
              borderRadius:8,
              label:{
                show:true,
                autosize:false
              },
              labelLine:{
                show:true
              },
              shadowBlur:30,
              shadowColor:'rgba(94,94,94,0.3)'
            },
            //高亮样式设置
            emphasis:{
              borderRadius:8,
              label:{
                show:true
              },
              labelLine:{
                show:true
              }
            },

          },
          data:this.top10Data,
          color:[
            "#c1232b",
            "#27727b",
            "#fcce10",
            "#e87c25",
            "#b5c334",
            "#fe8463",
            "#9bca63",
            "#fad860",
            "#f3a43b",
            "#60c0dd",
            "#d7504b",
            "#c6e579",
            "#f4e001",
            "#f0805a",
            "#26c0c0"
          ],
          label:{
            normal:{
              textStyle:{
                fontSize:10
              },
              formatter:function(param){
                return param.name+':\n'+Math.round(param.percent)+'%';
              }
            }
          },
          labelLine:{
            normal:{
              smooth:true,
              lineStyle:{
                width:2
              }
            }
          },

          // animationType:'scale',
          // animationEasing:'elasticOut',
          animationDelay:500
        },
        ],

      };
      myChart.setOption(option)
      let _this=this
      myChart.on('click',function(params){
        _this.hotTrendWord=params.name
      });
    },
    drawLineChart(){
      let chart=this.$echarts.init(document.getElementById('hotTrendWordChart'),'shine');// 基于准备好的dom，初始化echarts实例
      var option={
        title:{
          show:true,
          text:this.hotTrendWord+"热度趋势图",
          x:'center'
        },
        tooltip:{
          trigger:'axis',
          axisPointer:{            // 坐标轴指示器，坐标轴触发有效
            type:'shadow'        // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend:{
          top:50,
          bottom:50,
          data:['CVPR','ICCV','ECCV']
        },
        grid:{
          left:'3%',
          right:'4%',
          bottom:'3%',
          containLabel:true
        },
        xAxis:[
          {
            type:'category',
            data:['2000','2001','2002','2003','2004','2005','2006','2007','2008','2009','2010','2011','2012','2013','2014','2015','2016','2017','2018','2019','2020']
          }
        ],
        yAxis:[
          {
            type:'value'
          }
        ],
        series:[
          {
            name:'CVPR',
            type:'bar',
            // stack: '总量',
            emphasis:{
              focus:'series'
            },
            label:{
              show:true,
              rotate:90,
              align:'left',
              verticalAlign:'middle',
              position:'insideBottom',
              distance:15,
              formatter:'{c}  {name|{a}}',
              fontSize:16,
              color:'#000000',
              rich:{
                name:{}
              }
            },
            color:new echarts.graphic.LinearGradient(0,0,0,1,[{
              offset:0,
              color:'rgba(255, 191, 0)'
            },{
              offset:1,
              color:'rgba(224, 62, 76)'
            }]),
            data:this.CVPRData
          },
          {
            name:'ICCV',
            type:'bar',
            // stack: '总量',
            emphasis:{
              focus:'series'
            },
            label:{
              show:true,
              rotate:90,
              align:'left',
              verticalAlign:'middle',
              position:'insideBottom',
              distance:15,
              formatter:'{c}  {name|{a}}',
              fontSize:16,
              color:'#000000',
              rich:{
                name:{}
              }
            },
            color:new echarts.graphic.LinearGradient(0,0,0,1,[{
              offset:0,
              color:'rgb(0,247,255)'
            },{
              offset:1,
              color:'rgb(62,154,224)'
            }]),
            data:this.ICCVData
          },
          {
            name:'ECCV',
            type:'bar',
            // stack: '总量',
            emphasis:{
              focus:'series'
            },
            label:{
              show:true,
              rotate:90,
              align:'left',
              verticalAlign:'middle',
              position:'insideBottom',
              distance:15,
              formatter:'{c}  {name|{a}}',
              fontSize:16,
              color:'#000000',
              rich:{
                name:{}
              }
            },
            color:new echarts.graphic.LinearGradient(0,0,0,1,[{
              offset:0,
              color:'rgb(217,0,255)'
            },{
              offset:1,
              color:'rgb(129,62,224)'
            }]),
            data:this.ECCVData
          },
        ]
      };
      chart.setOption(option)
    }
  },
  mounted(){
    this.fillTop40Chart()
    this.fillTop10Chart()
    this.fillHotWordChart()
    window.addEventListener("resize",()=>{  // 通过resize方法来重设图表宽度
      let myChart=this.$echarts.init(document.getElementById('top10Chart'))
      let myChart1=this.$echarts.init(document.getElementById('hotTrendWordChart'))
      myChart.resize();
      myChart1.resize();
    });
    this.$refs.searchList2.search()
  }

}

</script>

<style scoped lang="less">
#main_frame {
  /*width: 100%;*/
  /*position: relative;*/
  margin-top: 50px;
  margin-bottom: 50px;
}

#hot_trend_paper_title {
  font-size: 25px;
  color: #e7e7e7;
  font-weight: bold;
  text-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
}

#wordcloud {
  width: 600px;
  height: 400px;
}

#wordcloud /deep/ .text {
  cursor: pointer;
}

#top10Chart {
  width: 90%;
  height: 100%;
  margin-left: 30px;
}

#hotTrendWordChart {
  width: 100%;
  height: 600px;
}

.frame {
  background-color: #f7f7f7;
  border-radius: 15px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  height: auto;
  margin-bottom: 50px;
  padding-top: 20px;
  padding-bottom: 20px;
  position: relative;
}

.frame1 {
  background-color: #f7f7f7;
  border-radius: 15px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  height: auto;
  margin-bottom: 50px;
  padding-top: 20px;
  padding-bottom: 20px;
  position: relative;
}

.frame2 {
  width: 92%;
  left: 4%;
  background-color: #f7f7f7;
  border-radius: 15px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  height: 600px;
  margin-bottom: 50px;
  padding-top: 20px;
  padding-bottom: 20px;
  position: relative;
}

</style>
