<template>
  <div id="indexWordTrend">
    <div id="wordTrendTitle">
      <div>各大热词走势对比</div>
    </div>
    <div id="wordTrendBody">
      <Tabs type="card">
        <TabPane v-for="(item, index) in color" :label="item" :key="item">
          <chart1 :text="text" :id1="index" :color="color[index]"></chart1>
        </TabPane>
      </Tabs>
    </div>
  </div>
</template>

<script>
import chart1 from '../../chart/chart1.vue'
export default {
  name: 'wordTrend',
  components: {
    chart1
  },
  data () {
    return {
      text: '111',
      data: [120, 200, 150, 80, 70, 110, 130],
      data1: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
      color: ['#349dff', '#fbd438', '#33c45e', '#f2637b', '#6dd48c', '#fbd437', '#4ecb73', '#eaa674', '#88d1ea', '#36cbcb']
    }
  }
}
</script>

<style scoped lang="less">
  #indexWordTrend {
    width: 100%;
    height: 600px;
    top: 0;
  }
  #wordTrendTitle {
    width: 100%;
    height: 50px;
    background-color: #bdbdbd;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    >div{
      color: white;
      font-size: 20px;
      font-weight: 700;
      margin: 0 20px;
    }
  }
  #wordTrendBody {
    margin: 10px 0;
  }
</style>
