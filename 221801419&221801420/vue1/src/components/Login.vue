<template>
 <div style="margin-top:80px">
     <el-row type="flex" class="row-bg" justify="center">
  <el-col :span="8"></el-col>
  <el-col :span="8"><el-avatar :size="80" :src='iconurl'></el-avatar></el-col>
  <el-col :span="8"></el-col>
</el-row>
<el-row type="flex" class="row-bg" justify="center">
  <el-col :span="6"></el-col>
  <el-col :span="6">
      <el-input class = "userinput"
      prefix-icon = "el-icon-user-solid"
  placeholder="用户名"
  v-model="username"
  clearable>
</el-input>
</el-col>
  <el-col :span="6"></el-col>
</el-row>

<el-row type="flex" class="row-bg" justify="center">
  <el-col :span="6"></el-col>
  <el-col :span="6">
      <el-input placeholder="密码" prefix-icon="el-icon-s-goods" v-model="password" show-password></el-input>
  </el-col>
  <el-col :span="6"></el-col>
</el-row>

<el-row type="flex" class="row-bg" justify="center">
  <el-col :span="6"></el-col>
  <el-col :span="6">  <el-button type="primary"  round>登录</el-button></el-col>
  <el-col :span="6"></el-col>
</el-row>


<el-row type="flex" class="row-bg" justify="center">
  <el-col :span="6"></el-col>
  <el-col :span="3"><el-switch active-text="记住密码" v-model="remember"></el-switch></el-col>
    <el-col :span="3"><el-link type="info">注册账号</el-link></el-col>
  <el-col :span="6"></el-col>
</el-row>

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
        iconurl:icon
      }
    },
    methods: {

    }
  }
</script>
<style>
    input.el-input__inner{

        background: transparent im !important;
        border-radius: 30px  !important;
    }
    .el-button--primary{
        width:100%;
    }
  .el-row {
    margin-bottom: 20px;

  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
 
  }
  .bg-purple {
   
  }
  .bg-purple-light {

  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
  }
</style>