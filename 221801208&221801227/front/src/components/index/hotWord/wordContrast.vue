<template>
  <div id="indexWordContrast">
    <div id="wordContrastTitle">
      <div>热词热度对比</div>
    </div>
    <div id="chartBody">
      <chart2></chart2>
    </div>
  </div>
</template>

<script>
import chart2 from '../../chart/chart2.vue'

export default {

  name: 'wordContrast',
  components: {
    chart2
  }
}
</script>

<style scoped lang="less">
  #indexWordContrast {
    width: 100%;
    height: 600px;
    top: 0;
  }
  #wordContrastTitle {
    width: 100%;
    height: 50px;
    background-color: #03a7f0;
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
  #wordContrastBody {
    margin: 10px 0;
  }
  #chartBody {
    width: 90%;
    height: 100%;
  }
</style>
