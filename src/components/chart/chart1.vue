<template>
  <div class="chart1">
    <div :id="this.id1" :style="{width: '800px', height: '500px'}"></div>
  </div>
</template>

<script>
// eslint-disable-next-line @typescript-eslint/no-var-requires
const echarts = require('echarts')
export default {
  name: 'chart1.vue',
  props: ['text', 'id1', 'color'],
  data () {
    return {
      data1: ['2015', '2016', '2017', '2018', '2019', '2020', '2021'],
      data2: [120, 200, 150, 80, 70, 110, 130]
    }
  },
  methods: {
    drawLine () {
      this.chartLine = echarts.init(document.getElementById(this.id1))
      const option = {
        tooltip: { // 设置tip提示
          trigger: 'axis'
        },

        legend: { // 设置区分（哪条线属于什么）
          data: ['关键词出现频数']
        },
        color: [this.color], // 设置区分（每条线是什么颜色，和 legend 一一对应）
        xAxis: { // 设置x轴
          type: 'category',
          boundaryGap: false, // 坐标轴两边不留白
          data: this.data1,
          name: '年份', // X轴 name
          nameTextStyle: { // 坐标轴名称的文字样式
            color: '#aeaeae',
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
          axisLine: { // 坐标轴轴线相关设置。
            lineStyle: {
              color: '#5d5d5d'
            }
          }
        },
        yAxis: {
          name: '出现次数',
          nameTextStyle: {
            color: '#aeaeae',
            fontSize: 16,
            padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: '#5d5d5d'
            }
          },
          type: 'value'
        },
        series: [
          {
            name: '出现次数',
            data: this.data2,
            type: 'line',
            lineStyle: {
              normal: {
                color: this.color
              }
            }
          }
        ]
      }

      // 使用刚指定的配置项和数据显示图表。
      this.chartLine.setOption(option)
    },
    getInfo () {
      this.$axios.post('http://localhost:8081/KeywordTrendController/getTrend', {
        keyword: this.text
      })
        .then(res => {
          this.data1 = res.data.year
          this.data2 = res.data.frequency
          this.drawLine()
        })
        .catch(err => {
          console.log(err)
        })
        .finally({
        })
    }
  },
  mounted () {
    // console.log('fdfdsfds')
    // this.$nextTick(this.drawLine())
    this.getInfo()
    // this.drawLine()
  }
}
</script>

<style scoped lang="less">

</style>
