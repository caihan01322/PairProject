<template>
  <div id="main_frame">
    <el-row class="row" type="flex" justify="space-around">
      <el-col class="frame" :span="10">
        <wordcloud
            id="wordcloud"
            :data="top40Data"
            nameKey="name"
            valueKey="value"
            :fontsize='[0,100]'
            fontScale="n"
            :showTooltip="false"
            :rotate="{from: 0, to: 0, numOfOrientation: 1 }"
            :wordClick="wordClickHandler">
        </wordcloud>
      </el-col>
      <el-col class="frame" :span="10">
        <div id="top10Chart"></div>
      </el-col>
    </el-row>

    <div class="frame2">
      <div id="hotTrendWordChart"></div>

    </div>

      <SearchList ref="searchList"
          v-model="hotTrendWord" :searchInput="searchInput"/>
  </div>
</template>

<script>

import wordcloud from 'vue-wordcloud'
import axios from "axios";
import SearchList from "@/components/SearchList";

export default {
  name:"statisticList",
  components:{
    wordcloud,
    SearchList
  },
  data(){
    return {
      searchInput:'',
      top40Data:[],
      top10LegendData:[],
      top10Data:[],
      hotTrendWord:'',
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
      this.$message.success('!!!!!!!!!!!!!!!!!!!!')
      this.fillHotWordChart()
      this.$refs.searchList.search()
    },
    CVPRData(){
      this.drawLineChart()
    }
  },
  methods:{
    wordClickHandler(name){
      this.hotTrendWord=name
      this.$message.success('当前热词为'+this.hotTrendWord)
    },
    fillTop40Chart(){
      axios
          .get("http://121.5.100.116:8080/api/Top40")
          .then(response=>{
            this.top40Data=response.data.data
          })
    },
    fillTop10Chart(){
      axios
          .get("http://121.5.100.116:8080/api/Top10")
          .then(response=>{
            this.top10Data=response.data.data
            for(let i=0;i<response.data.data.length;i++){
              this.top10LegendData.push(response.data.data[i].name)
            }
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
          // formatter:"{a}<br/>占比名是{b}：{c} ({d}%) "
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
          radius:[10,110],
          top:'40px',
          // center:[250,180],
          roseType:'radius',
          width:'100%',// for funnel
          max:40,            // for funnel
          itemStyle:{
            //普通样式设置
            normal:{
              label:{
                show:true,
                autosize:false
              },
              labelLine:{
                show:true
              },
              shadowBlur:30,
              shadowColor:'rgba(0, 0, 0, 0.3)'
            },
            //高亮样式设置
            emphasis:{
              label:{
                show:true
              },
              labelLine:{
                show:true
              }
            }
          },
          data:this.top10Data,
          color:['#7EC0EE','#FF9F7F','#FFD700','#C9C9C9','#E066FF','#C0FF3E','#BDA29A','#C23531'],
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
          animationDelay: 500
        },
        ],

      };
      myChart.setOption(option)
      myChart.on('click', function (params) {
        this.hotTrendWord=params.name
        this.$message.success('当前热词为'+this.hotTrendWord)
      });
    },
    drawLineChart() {
      let chartLine = this.$echarts.init(document.getElementById('hotTrendWordChart'),'shine');// 基于准备好的dom，初始化echarts实例
      let option = {
        title:{
          show:true,
          text:this.hotTrendWord+"热度趋势图",
          x:'center'
        },
        tooltip : {
          trigger: 'axis'
        },
        legend: {
          top:50,
          data:['CVPR','ICCV','ECCV']
        },
        calculable : true,
        xAxis : [
          {
            type : 'category',
            boundaryGap : false,
            axisTick: {
              show: false
            },
            data : ['2014','2015','2016','2017','2018','2019','2020']
          }
        ],
        yAxis : [
          {
            type : 'value',
            axisTick: {
              show: false
            },
            name: '（论文数）'
          }
        ],
        series : [
          {
            name:'CVPR',
            type:'line',
            stack: '总量',
            data:this.CVPRData
          },
          {
            name:'ICCV',
            type:'line',
            stack: '总量',
            data:this.ICCVData
          },
          {
            name:'ECCV',
            type:'line',
            stack: '总量',
            data:this.ECCVData
          },
        ]
      };
      // 使用刚指定的配置项和数据显示图表
      chartLine.setOption(option);
    }
  },
  mounted(){
    this.fillTop40Chart()
    this.fillTop10Chart()
    this.fillHotWordChart()
    window.addEventListener("resize", () => {  // 通过resize方法来重设图表宽度
      let myChart=this.$echarts.init(document.getElementById('top10Chart'))
      let myChart1=this.$echarts.init(document.getElementById('hotTrendWordChart'))
      myChart.resize();
      myChart1.resize();
    });
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

.row {
}

#wordcloud {
  width: 600px;
  height: 400px;
}

#wordcloud /deep/ .text{
  cursor: pointer;
}

#top10Chart {
  width: 90%;
  height: 100%;
  margin-left: 30px;
}

#hotTrendWordChart{
  width: 100%;
  height: 400px;
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

.frame2{
  width: 92%;
  left: 4%;
  background-color: #f7f7f7;
  border-radius: 15px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  height: auto;
  margin-bottom: 50px;
  padding-top: 20px;
  padding-bottom: 20px;
  position: relative;
}

</style>
