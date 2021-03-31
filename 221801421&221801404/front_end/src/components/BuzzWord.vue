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
          @row-click="searchKeyword"
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
import axios from 'axios'
import * as echarts from 'echarts'

export default {
data () {
  return {
    tableData:'',
    chartData:'',
    myChart:'',
    TYPE:['ECCV','ICCV','CVPR'],
    selectedObj:''
  }
},
methods: {
  getTotalRank(){
    axios.get('http://localhost:5000/totalrank').then((res) => {
      this.tableData = res.data
    })
  },
  getChartInfo () {
    axios.get('http://localhost:5000/chartInfo').then((res) => {
      for(var i=0;i<res.data.length;i++){
        this.chartData = res.data[i]
        this.draw(this.TYPE[i])
      }
    })
  },
  getyears(){
    let arrnew = [];
    this.chartData.forEach(e => {
        arrnew.push(e.year)
    })
    return arrnew
  },
  getCount(){
    let arrnew = this.chartData.map((item,index) => {
        return Object.assign(this.chartData[index],{'value':item.count})
    })
    return arrnew
  },
  searchKeyword(row, column, event){
    this.selectedObj=row
    let str=JSON.stringify(this.selectedObj.keyword)
    this.$router.push({path:'/',query:{keyword:str}})
  },
  draw(type){
    this.myChart = echarts.init(document.getElementById(type))
    let option = null
    option = {
        title: {
            text: type
        },
        tooltip: {
            show: true,
            trigger: 'axis',
            backgroundColor: '#fff',  //背景色
              padding: [5, 15, 5, 15],   //边距
              borderColor: '#DDDDDF',  //边框颜色
              borderWidth: 1,    //边框线宽度
              textStyle: {     //文字样式
                color: '#6A6A6A',
                decoration: 'none',
                fontFamily: 'Verdana, sans-serif',
              },
              extraCssText:'text-align: left;',  //文字对齐方式
            formatter:function(params){
              return "关键字: "+params[0].data.keyword+"<br/>引用次数: "+params[0].data.count
            },
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: this.getyears()
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: type,
                type: 'line',
                data: this.getCount()
            },
        ]
    }
    this.myChart.setOption(option)
  }
},

mounted() {
  this.getTotalRank()
  this.getChartInfo()
}
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
