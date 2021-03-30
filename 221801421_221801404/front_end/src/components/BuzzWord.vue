<template>
<div>
<el-row>
  <el-col :span="9">
    <el-row>
      <div class="grid-chart">
        <div id="ECCV" class="myChart"></div>
      </div>
    </el-row>
    <el-row>
      <div class="grid-chart">
        <div id="ICCV" class="myChart"></div>
      </div>
    </el-row>
  </el-col>
  <el-col :span="9">
    <el-row>
      <div class="grid-chart">
        <div id="CVPR" class="myChart"></div>
      </div>
    </el-row>
    <el-row><div class="grid-chart"></div></el-row>
  </el-col>
  <el-col :span="6">
    <div class="grid-table">
      <div id="rank-header">热词排行</div>
      <el-table
          :data="tableData"
          stripe
          style="width: 100%">
          <el-table-column
            prop="keyword"
            label="关键词"
            min-width="70%">
          </el-table-column>
          <el-table-column
            prop="count"
            label="引用次数"
            min-width="30%">
          </el-table-column>
        </el-table>
    </div>
  </el-col>
</el-row>
</div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  data () {
    return {
    }
  },
}
</script>

<style>
#rank-header{
  min-width: 100%;
  text-align: center;
  margin: 20px 0;
  font-size:24px;
  font-weight:bold;
  color:#607D8B;
}
.charts{
  float: left;
  width: 100%;
  height: 800px;
}
.chart{
  width: 500px;
  height: 300px;
  background-color: #0000FF;
  float: left;
}

.el-row {
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}

.grid-chart {
  border-radius: 4px;
  min-height: 400px;
}

.grid-table{
    min-height: 800px;
}

.myChart {
   width: 95%;
   height: 350px;
   margin: 20px auto;
   border: 1px solid #CCC;
}

</style>
