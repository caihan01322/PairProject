<template>
  <div class="chart2">
    <div id="myChart2" :style="{width: '500px', height: '300px'}"></div>
  </div>
</template>

<script>
// eslint-disable-next-line @typescript-eslint/no-var-requires
const echarts = require('echarts')
export default {
  name: 'chart2.vue',
  // props: ['text', 'id1'],
  data () {
    return {
      data1: [5, 10, 12, 69, 25, 14, 18, 55, 47, 33],
      data2: ['key1', 'key2', 'key3', 'key4', 'key5', 'key6', 'key7', 'key8', 'key9', 'key10'],
      color: ['#349dff', '#fbd438', '#33c45e', '#f2637b', '#6dd48c', '#fbd437', '#4ecb73', '#eaa674', '#88d1ea', '#36cbcb']
    }
  },
  methods: {
    drawLine () {
      this.chartLine = echarts.init(document.getElementById('myChart2'))
      const option = {
        tooltip: { // 设置tip提示
          formatter: '{a}<br/>{b}:{c} ({d}%)'
        },

        legend: { // 设置区分（哪条线属于什么）
          y: 'bottom',
          x: 'center',
          data: ['key1', 'key2', 'key3', 'key4', 'key5', 'key6', 'key7', 'key8', 'key9', 'key10']
        },
        color: this.color, // 设置区分（每条线是什么颜色，和 legend 一一对应）
        xAxis: { // 设置x轴
        },
        yAxis: {
        },
        series: [
          {
            name: '出现次数',
            data: this.data1,
            type: 'pie'
          }
        ]
      }

      // 使用刚指定的配置项和数据显示图表。
      this.chartLine.setOption(option)
    },
    getInfo () {
      // this.$axios.
    }
  },
  mounted () {
    // console.log('fdfdsfds')
    // this.$nextTick(this.drawLine())
    // this.getInfo()
    this.drawLine()
  }
}
</script>

<style scoped lang="less">

</style>
