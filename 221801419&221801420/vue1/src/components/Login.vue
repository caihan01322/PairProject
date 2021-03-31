<template>
 <div style="margin-top:80px" style="note" >
     <el-row  style="background-color:transparent" type="flex" class="row-bg" justify="center">
  <el-col :span="8"></el-col>
  <el-col :span="8"><el-avatar :size="80" :src='iconurl'></el-avatar></el-col>
  <el-col :span="8"></el-col>
</el-row>
<el-row style="background-color:transparent" type="flex" class="row-bg" justify="center">
  <el-col :span="6"></el-col>
  <el-col :span="6">
      <el-input class = "userinput"
      prefix-icon = "el-icon-user-solid"
  placeholder="用户名"
  v-model="user"
  clearable>
</el-input>
</el-col>
  <el-col :span="6"></el-col>
</el-row>

<el-row style="background-color:transparent" type="flex" class="row-bg" justify="center">
  <el-col :span="6"></el-col>
  <el-col :span="6">
      <el-input placeholder="密码" @keydown.enter.native="login()" prefix-icon="el-icon-s-goods" v-model="password" show-password></el-input>
  </el-col>
  <el-col :span="6"></el-col>
</el-row>

<el-row style="background-color:transparent" type="flex" class="row-bg" justify="center">
  <el-col :span="6"></el-col>
  <el-col :span="6">  <el-button type="primary" v-on:click="login" round>登录</el-button></el-col>
  <el-col :span="6"></el-col>
</el-row>


<el-row style="background-color:transparent" type="flex" class="row-bg" justify="center">
  <el-col :span="6"></el-col>
  <el-col :span="3"><el-switch active-text="记住密码" v-model="remember"></el-switch></el-col>
    <el-col :span="3"><el-link type="info">注册账号</el-link></el-col>
  <el-col :span="6"></el-col>
</el-row>

</div>
</template>

<script>
import icon from "@/assets/vcicon.jpg";
import axios from 'axios';
  export default {
    data() {
      return {
        user: '',
        password:'',
        remember:false,
        iconurl:icon,
        note:{

        }
      }
    },
    methods: {
  
      login( ){
        axios.get('/login'+'?username='+this.user+'&pwd='+this.password).
        then(successResponse =>{
          console.log(successResponse);
         if (successResponse.data.loginStatus === 2){
            this.$message({
            type: 'success',
            message: '登录成功'
          });
            this.$router.replace({path:'/singlesearch'})
          }
          if (successResponse.data.loginStatus === 1){
            this.$message.error('密码错误');
            this.password='';
          }
          if (successResponse.data.loginStatus === 0){
            this.$message.error('用户名不存在');
            this.user='';
            this.password='';
          }
        }).catch(failResponse =>{

        });
      }

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