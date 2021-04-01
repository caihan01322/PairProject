<template>
    <div>
<el-container>
  <el-header style="background-color:black">
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
  <el-menu-item class="menu" index="4" >论文信息</el-menu-item>
</el-menu>
        </el-col>
       <el-col :span="5">
         
         
         <div class="login">
           <el-link :underline="false">登录/注册</el-link>
         </div>
         
         </el-col>
        
 </el-row>
  </el-header>
  <el-main style="background-color:transparent;height:678px">
    <el-input
    style="width:800px;margin-top:250px;"
    placeholder="请输入搜索内容"
    v-model="searchinfo"
    clearable>
 <i slot="suffix" class="el-input__icon el-icon-close" style="font-size:16px"></i>
 <i slot="suffix" class="el-input__icon el-icon-upload" style="font-size:16px;color:blue"></i>
  <i slot="suffix" class="el-input__icon el-icon-search" style="font-size:16px;color:blue"></i>
  </el-input>
      </el-main>
    </el-container>
    </div>
</template>

<script>
import icon from "@/assets/vcicon.jpg";

  export default {
    data() {
      return {
        iconurl:icon,
        activeIndex:'1',
        searchinfo:'',
      }
    },
    methods: {
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
    }
  }

</script>

<style>
    .menu:hover{
      background-color:mediumseagreen !important;
  }
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
    .el-avatar{
      float:left;
      margin-left:20px;
      margin-top:10px;
      margin-right: 15px;
  }
    .el-link{
    color:white !important;
    float: right;
    margin-right: 15px;
    font-size:17px;
  }
</style>