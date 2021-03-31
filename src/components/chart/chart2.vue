<template>
  <div class="chart2">
    <div id="myChart2"></div>
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
      datat: [],
      color: ['#349dff', '#fbd438', '#33c45e', '#f2637b', '#6dd48c', '#fbd437', '#4ecb73', '#eaa674', '#88d1ea', '#36cbcb']
    }
  },
  methods: {
    setdata () {
      this.datat = []
      const num = this.data2.length
      for (let i = 0; i < num; i++) {
        const temp = {
          value: this.data1[i],
          name: this.data2[i]
        }
        this.datat.push(temp)
      }
      this.drawLine()
    },
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
        series: [
          {
            name: '出现次数',
            data: this.datat,
            type: 'pie'
            // radius: ['50%', '70%']
            // center: ['50%', '50%']
          }
        ]
      }

      // 使用刚指定的配置项和数据显示图表。
      this.chartLine.setOption(option)
    },
    getInfo () {
      this.$axios.post('http://localhost:8080/WordController/getHotWords')
        .then(res => {
          this.data1 = res.data.frequency
          this.data2 = res.data.hotWord
          this.setdata()
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
    // this.getInfo()
    this.setdata()
  }
}
</script>

<style scoped lang="less">
  .chart2 {
    width:100%;
  }
  #myChart2 {
    width: 800px;
    height: 600px;
  }

</style>
