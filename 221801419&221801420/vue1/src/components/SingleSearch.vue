<template>
    <div>
<el-container>
  <el-header>
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
  <el-menu-item class="menu" index="4">论文热词</el-menu-item>
</el-menu>
        </el-col>
       <el-col :span="5">
         
         
         <div class="login">
           <el-link :underline="false">登录/注册</el-link>
         </div>
         
         </el-col>
        
 </el-row>
  </el-header>
  <el-main>
        <el-container>
          
                    <el-main>
                      <el-container>
                        <el-header id="search">
                           <el-input
                             placeholder="请输入搜索内容"
                             v-model="searchinfo"
                             clearable>
                            <i slot="suffix" class="el-input__icon el-icon-close" style="font-size:16px"></i>
                             <i slot="suffix" class="el-input__icon el-icon-upload" style="font-size:16px;color:blue"></i>
                              <i slot="suffix" class="el-input__icon el-icon-search" style="font-size:16px;color:blue"></i>
                           </el-input>
                        </el-header>
                        <el-main>
                            <el-container>
                            
                              <el-main class="essay">
                                <el-container>

                                  <el-main style="background-color:transparent">
                                   <div> <p style="text-align:left;font-size:13px;width:100%">论文题目</p></div>
                                    <div><p style="text-align:left;font-size:13px;width:100%">原文网址</p></div>
                                    <div><p style="font-size:13px;width:100%;text-align:left">摘要</p> 
                                      </div>
                                    <div style="display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp: 5;overflow: hidden;  word-break: break-all;word-wrap: break-word;margin-top:0px">
                                      <p style="font-size:13px;width:100%;text-align:left;">摘要11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111</p>
                                    </div>
                                  </el-main>
                                  <el-footer style="background-color:transparent;height:20px">
                                    <div>
                                       <a  style="float:left;color:blue" href="">详细信息</a>
                                    </div>
                                  </el-footer>
                                  </el-container>
                              </el-main>
                               <el-main class="essay">main</el-main>
                                <el-main class="essay">main</el-main>
                                 <el-main class="essay">main</el-main>
                                 <el-footer>
                                   <el-pagination
                                   class="pager"
                                    background
                                     layout="prev, pager, next"
                                     :total="1000">
                                    </el-pagination>
                                 </el-footer>
                            </el-container>
                            
                        </el-main>
                      </el-container>
                    </el-main>
         <el-aside width="350px" class="sideraside">
           <el-container>
             <el-header class="header">                  
               <el-input
                  id ="resultsearch"
                  placeholder="请输入搜索内容"
                   prefix-icon="el-icon-search"
                  v-model="input2">
              </el-input></el-header>
              <el-main class="results">
          <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose">
              <el-menu-item class="menu2" index="1">
                 <el-button class="resultbutton" round>sss</el-button>
              </el-menu-item>
              <el-menu-item  class="menu2" index="2" >
                <el-button class="resultbutton" round>sss</el-button>
              </el-menu-item>
              <el-menu-item class="menu2" index="3">
                  <el-button class="resultbutton" round>sss</el-button>
              </el-menu-item>
              <el-menu-item class="menu2" index="4">
                  <el-button class="resultbutton" round>sss</el-button>
              </el-menu-item>
             <el-menu-item class="menu2" index="5">
                  <el-button class="resultbutton" round>sss</el-button>
              </el-menu-item>
                           <el-menu-item class="menu2" index="6">
                  <el-button class="resultbutton" round>sss</el-button>
              </el-menu-item>
                           <el-menu-item class="menu2" index="6">
                  <el-button class="resultbutton" round>sss</el-button>
              </el-menu-item>
                           <el-menu-item class="menu2" index="6">
                  <el-button class="resultbutton" round>sss</el-button>
              </el-menu-item>
                           <el-menu-item class="menu2" index="6">
                  <el-button class="resultbutton" round>sss</el-button>
              </el-menu-item>
            </el-menu>
          </el-main>
            </el-container>
        
     
         </el-aside>

        </el-container>
  </el-main>
</el-container>
    </div>
</template>
<script>
import icon from "@/assets/vcicon.jpg";

  export default {
    data() {
      return {
        username: '',
        password:'',
        remember:false,
        iconurl:icon,
        searchinfo:'',
        input2:'',
        activeIndex:'1'
      }
    },
    methods: {

    }
  }

</script>
<style>
 .el-row {
     height:100%;
        padding-bottom: 0;
  }
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
    background-color:transparent;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
    margin-top:86px;

  }
  
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: auto;
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
  .el-header{
          padding:0;
          background-color: black;


  }
  .el-avatar{
      float:left;
      margin-left:20px;
      margin-top:10px;
      margin-right: 15px;
  }
  .title{
      color: white;
      margin-left: 30px;
      text-align: left;
      font-size: 17px;
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
  #search{
    background:transparent;
    width:690px;
    margin-left:120px;
    margin-top:0px;
  }
  .el-pagination{
    float:inline-end;
  }
  .menu2:hover{
    background-color:transparent;
  }
  .menu2{
    margin-top: 3px !important;
    background-color: transparent;
    padding-top:0px !important;
  }
  .resultbutton{
    width:260px ;
    margin-left:0px;
  }
  .resultbutton span{
    font-size:1px !important;
    margin-bottom: 0px !important;
    padding-bottom: 12px !important;
    margin-top:0px !important
  }
  #resultsearch{
    height:35px !important;
    
  }
  #sider{
    background-color: white;
  }
  .el-menu-vertical-demo{
    height:300px;
    background-color: transparent;
    width:200px !important;

  }
  .sideraside{
    height:500px;
    background-color:transparent;
    margin-right: 200px;
    margin-top:90px;
  }
  .essay{
    background-color: rgba(64, 224, 208, 0.144) !important;
    margin-bottom: 16px;
    height:300px;
    box-shadow: 0 5px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    width:690px;
    margin-right:100px !important;
    margin-left:100px;
 }
 .pager{
   margin-top: 10px;
   margin-right: 80px;
 }
 .header{
   background-color: transparent;
 }
 .results{

 }
</style>
