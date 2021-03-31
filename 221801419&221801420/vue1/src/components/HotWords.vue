<template>
    <div>
<el-container>
  <el-header style="          padding:0;
          background-color: black;">
    <el-row>
      <el-col :span="5"><el-avatar shape="square" :size="40" :src='iconurl'></el-avatar><div class="title">vc论文</div></el-col>
      <el-col :span="14">
       <el-menu id ="menu"
  :default-active="activeIndex"
  class="el-menu-demo"
  mode="horizontal"
  font-size="50px"
  @select="handleSelect"
  background-color="black"
  text-color="#fff"
  active-text-color="#ffd04b">
  <el-menu-item class="menu" index="1">论文搜索</el-menu-item>
  <el-menu-item class="menu" index="2">
    论文列表
  </el-menu-item>
  <el-menu-item class="menu" index="3">论文热词</el-menu-item>
</el-menu>
        </el-col>
       <el-col :span="5">
         
         
         <div class="login">
           <el-link :underline="false">登录/注册</el-link>
         </div>
         
         </el-col>
        
 </el-row>
  </el-header>
        <el-main style="background-color:transparent;margin-top:30px;">
              <div style="background-color:transparent;height:40px;line-height:20px;margin-bottom:0px">
                <el-row>
                  <el-col :span="6"><div  style="margin-top:5px"></div></el-col>
                  <el-col :span="10"><div class="grid-content bg-purple-light" style="background-color:transparent"></div></el-col>
                  <el-col :span="8"> 
                     <el-tag
                          class="label"
                          v-for="item in items"
                          :key="item.label"
                          :type="item.type"
                          effect="plain"
                          style="margin-left:10px">
                          {{ item.label }}
                     </el-tag></el-col>
                </el-row>
              </div>
              <div style="width: auto;height: 400px" id="main">
                </div>
      </el-main>
      <el-footer style="background-color:transparent;margin-top:60px;height:auto">
          <el-container >
              <el-main style="background-color:transparent">
                <div style="width:auto;height:500px" id="main2">1</div>
              </el-main>
            <el-aside style="width:550px;background-color:transparent">
              <div  style="wdith:auto;height:540px" id="main3"></div>
            </el-aside>                
          </el-container>
      </el-footer>
      </el-container>
      </div>
</template>
<script>
import icon from "@/assets/vcicon.jpg";
const echarts = require('echarts');
  export default {
    data() {
      return {
        iconurl:icon,
        items: [
          { type: 'success', label: 'CVPR' },
          { type: 'success', label: 'ECCV' },
          { type: 'success', label: 'ICCV' },
        ],
        activeIndex:'3',
      }
    },
    handleSelect(key,keyPath){

      if (key == 1){
        this.$router.replace({path:'/main'});
      }

      if (key == 2){
        if (this.username == '登录/注册'){
          this.$message.error('登录后才可访论文列表');
          this.activeIndex='1';
        }else{
          this.$router.push({path:'/essaylist',query:{username:this.username}});
        }

      }

      if (key == 3){
        this.$router.replace({path:'/hotwords'});
      }

      if (key == 4){
        this.$router.replace({path:'/infoview'});
      }
    },
    mounted() {
      this.echartsInit()
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      },
      echartsInit() {
        echarts.init(document.getElementById('main')).setOption({
    title: {
        text: '顶会热词趋势'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['2018', '2019', '2020']
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
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: '2018',
            type: 'line',
            stack: '总量',
            data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
            name: '2019',
            type: 'line',
            stack: '总量',
            data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
            name: '2020',
            type: 'line',
            stack: '总量',
            data: [150, 232, 201, 154, 190, 330, 410]
        },
    ]
        });


         echarts.init(document.getElementById('main2')).setOption({
          dataset: {
            source: [
            ['score', 'amount', 'product'],
            [89.3, 150, 'Matcha Latte'],
            [57.1, 98, 'Milk Tea'],
            [74.4, 65, 'Cheese Cocoa'],
            [50.1, 78, 'Cheese Brownie'],
            [89.7, 5, 'Matcha Cocoa'],
            [68.1, 35, 'Tea'],
            [19.6, 45, 'Orange Juice'],
            [10.6, 89, 'Lemon Juice'],
            [32.7, 45, 'Walnut Brownie']
            ]
        },
        grid: {containLabel: true},
        xAxis: {name: 'amount'},
        yAxis: {type: 'category'},
        visualMap: {
            orient: 'horizontal',
            left: 'center',
            min: 10,
            max: 100,
            text: ['High Score', 'Low Score'],
            // Map the score column to color
            dimension: 0,
            inRange: {
                color: ['#65B581', '#FFCE34', '#FD665F']
            }
        },
        series: [
            {
                type: 'bar',
                encode: {
                    // Map the "amount" column to X axis.
                    x: 'amount',
                    // Map the "product" column to Y axis
                    y: 'product'
                }
            }
        ]
        });
        
          echarts.init(document.getElementById('main3')).setOption({
            tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top: '5%',
                    left: 'center'
                },
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '40',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: [
                            {value: 1048, name: '搜索引擎'},
                            {value: 735, name: '直接访问'},
                            {value: 580, name: '邮件营销'},
                            {value: 484, name: '联盟广告'},
                            {value: 300, name: '视频广告'}
                        ]
                    }
                ]
              })


      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
<style>
 .el-col el-col-5{
      height:100%;
      margin-bottom:0px;
  }
  .el-col {
    border-radius: 0px;
    height:100%;
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
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .el-avatar{
      float:left;
      margin-left:20px;
      margin-top:10px;
      margin-right: 15px;
  }
    .menu{
    background-color: black;
    margin-left: 80px !important;
    text-align: center;
  }

  .el-menu-item{
    font-size: 17px;

  
  }
  li.el-menu-item{
    text-align: center;
  }
    .menu:hover{
      background-color:mediumseagreen !important;
  }
  .el-link{
    color:white !important;
    float: right;
    margin-right: 15px;
    font-size:17px;
  }
   .title{
      color: white;
      margin-left: 30px;
      text-align: left;
      font-size: 17px;
  }
  .label:hover{
background-color: #67c23a;
    border-color: #67c23a;
    color: #fff;
  }
</style>