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
import axios from 'axios'
import * as echarts from 'echarts'

export default {
data () {
  return {
    tableData: [{
      keyword: '2016-05-02',
      count: '王小虎',
    }, {
      keyword: '2016-05-04',
      count: '王小虎',
    }, {
      keyword: '2016-05-01',
      count: '王小虎',
    }, {
      keyword: '2016-05-03',
      count: '王小虎',
    }],

    chartData:'',
    myChart:'',
    TYPE:['ECCV','ICCV','CVPR']
  }
},
methods: {
  getChartInfo (type) {
    //这里需要字符串拼接
    axios.get('../static/chart.json').then((res) => {
      console.log(res)
      this.chartData = res.data
      this.draw(type)
    })
  },
  getyears(){
    //log(this.chartData)
    /*let arrnew = this.chartData.map((item,index) => {
        return Object.assign({},{'year':item.year})
    })*/
    let arrnew = [];
    this.chartData.forEach(e => {
        arrnew.push(e.year)
    })
    console.log(arrnew)
    return arrnew

  },
  getCount(){
    let arrnew = this.chartData.map((item,index) => {
        return Object.assign(this.chartData[index],{'value':item.count})
    })
    return arrnew
  },
  draw(type){
    //console.log(document.getElementById('myChart'))
    // 实例化echarts对象
    this.myChart = echarts.init(document.getElementById(type))

    //console.log(this.myChart)
    // 绘制条形图
    let option = null
    option = {
        title: {
            text: type
        },
        tooltip: {
            show: true,
            trigger: 'axis',
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
    //console.log(this.myChart)
  }
},

mounted() {
  for (var i = 0; i < this.TYPE.length; i++) {
        this.getChartInfo(this.TYPE[i])
        console.log(this.TYPE[i])
  }
},
computed:{
},

components:{
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
.rank{
  float: left;
  width: 300px;
  height: 800px;
}


.el-row {
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-chart {
  border-radius: 4px;
  min-height: 400px;
}

.grid-table{
    min-height: 800px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.myChart {
   width: 95%;
   height: 350px;
   margin: 20px auto;
   border: 1px solid #CCC;
}

</style>
