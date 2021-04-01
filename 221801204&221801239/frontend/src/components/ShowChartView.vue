<template>
  <div id="container">
    <div id="panel">
      <div id="myChart" :style="{width: '1200px', height: '600px'}"></div>
    </div>

    <div id="controller">
      <button id="button0" >CVPR</button>
      <button class="btn" >&gt;ICCV</button>
      <button class="btn" >&gt;ECCV</button>
    </div>
  </div>

</template>

<script>
const echarts = require('echarts');
export default {
  name: "Chart",
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      data1: ['人工智能', '机器学习', '算法', '大数据', '仿生学', '嵌入式'],
      data2: [5, 20, 36, 10, 10, 20],
      data3:[],
      data4:[]
    }
  },
  methods: {
    drawLine () {
      // 基于准备好的dom，初始化echarts实例
      const myChart = echarts.init(document.getElementById('myChart'))
      // 绘制图表
      myChart.setOption({
        title: {
          text: '',
           //textAlign: 'center',
           //padding: 0
        },
        xAxis: {
          data: this.data1,
          inverse: true,
          animationDuration: 600,
          animationDurationUpdate: 600,
        },
        yAxis: {max:'dataMax'},
        series: [
          {
            name: '',
            type: 'bar',
            data: this.data2,
            realtimeSort: true,
            label: {
              show: true,
              position: 'top',
              valueAnimation: true
            }
        }
        ],
        legend: {
          show:true,
          data: ['ECCV']
        },
        color: ['#1685ff'],
        animationDuration: 0,
        animationDurationUpdate: 3000,
        animationEasing: 'linear',
        animationEasingUpdate: 'linear'

      })
    },
    getInfo () {
      this.$axios.get('http://localhost:8081/getMeetingInfo', {
      })
        .then(res => {
          this.data1=res.data;

        }).catch(error => {

      })
    }
  },created() {
    this.getInfo();
  },
  mounted () {
    this.$nextTick(this.drawLine())
  },
  updated () {
    this.drawLine()
  }

}
</script>

<style lang="less" scoped>
#container{
  height: 100%;
  width: 100%;
  position:fixed;
  background-color: rgb(214, 234, 234);
  #panel{
    height: 600px;
    width: 100%;
    margin-left: 20%;
  }
  #controller{
     height: 100%;
     width: 100%;
     #button0{
       height: 60px;
       width: 80px;
       margin-left: 34%;
      color: black;
     }
    .btn{
        height: 60px;
        width: 80px;
        margin-left: 10%;
        color: black;
      }
   }
}


</style>
