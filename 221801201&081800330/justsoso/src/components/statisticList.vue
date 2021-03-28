<template>
  <div id="main_frame">
      <el-row class="row" type="flex" justify="space-around" :gutter="20">
        <el-col class="frame" :span="10">

        </el-col>
        <el-col class="frame" :span="10">
          <div id="top10Chart"></div>
        </el-col>
      </el-row>
    </div>
</template>

<script>
export default {
  name:"statisticList",

  data(){
    return{

    }
  },
  methods:{
    fillTop10Chart(){

    },
    drawLine(){
      // 基于准备好的dom，初始化echarts实例
      let myChart=this.$echarts.init(document.getElementById('top10Chart'))
      // 绘制图表
      // myChart.setOption({
      //   title:{text:'在Vue中使用echarts'},
      //   tooltip:{},
      //   xAxis:{
      //     data:["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
      //   },
      //   yAxis:{},
      //   series:[{
      //     name:'销量',
      //     type:'bar',
      //     data:[5,20,36,10,10,20]
      //   }]
      // });
      var option = {
        //title图的标题，副标题，以及位置设置
        title : {
          text: 'TOP10热词图',
          x:'center'
        },
        tooltip : {
          trigger: 'item',
          //提示框的数据样式显示
          formatter:"{a}<br/>占比名是{b}：{c} ({d}%) "
        },
        //图例相关内容的说明
        legend: {
          x : 'left',
          y : 'center',
          //图例的排列默认属性是：水平或垂直,默认是：水平
          orient:'vertical',
          data:['北京','上海','广州','福建','厦门','武汉','青岛','南京']
        },
        /*
         * toolbox为工具栏；提供导出图片，数据转化，动态数据等信息
         * 保存图片
         */
        toolbox: {
          show : true,//是否显示工具栏组件
          feature : {
            //mark : {show: true},
            //显示图像所提供的数据
            dataView : {show: true, readOnly: false},
            //启用的动态数据类型
            magicType : {
              show: true,
              type: ['pie', 'funnel']
            },
            //配置项还原
            restore : {show: true},
            //保存为图片
            saveAsImage : {show: true}
          }
        },
        calculable : true,//手柄拖拽调整选中的范围
        series : [
          {
            name:'半径模式',
            type:'pie',
            radius : [60, 110],
            center : [300, 180],
            roseType : 'radius',
            width: '40%',// for funnel
            max: 40,            // for funnel
            itemStyle : {
              //普通样式设置
              normal : {
                label : {
                  show : false
                },
                labelLine : {
                  show : false
                }
              },
              //高亮样式设置
              emphasis : {
                label : {
                  show : true
                },
                labelLine : {
                  show : true
                }
              }
            },
            data:[
              {value:10, name:'北京'},
              {value:5, name:'上海'},
              {value:15, name:'广州'},
              {value:25, name:'福建'},
              {value:20, name:'厦门'},
              {value:35, name:'武汉'},
              {value:30, name:'青岛'},
              {value:40, name:'南京'}
            ],
            color: ['#7EC0EE', '#FF9F7F', '#FFD700', '#C9C9C9', '#E066FF', '#C0FF3E','#BDA29A','#C23531']
          }
        ]
      };
      myChart.setOption(option)
    }
  },
  mounted(){

    this.drawLine()
  }

}

</script>

<style scoped>
#main_frame {
  /*width: 100%;*/
  /*position: relative;*/
  margin-top: 50px;
  margin-bottom: 50px;
}

.row{
}

#top10Chart{
  width: 600px;
  height: 300px;
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

</style>
