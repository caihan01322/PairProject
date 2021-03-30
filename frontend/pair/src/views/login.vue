<template>
<div>
  <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
    <h3 class="login-title">登录界面</h3>
    <el-form-item label="账号" prop="username">
      <el-input type="text" placeholder="请输入账号" v-model="account"/>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" placeholder="请输入密码" v-model="password"/>
    </el-form-item>
  <div>
    <br>
  </div>
    <el-radio v-model="radio" label="1" >记住我</el-radio>
  <div>
    <br>
  </div>
    <el-form-item style="margin-left:-90px">
      <el-button id="loginbutton" type="primary" v-on:click="login">登录</el-button>
    </el-form-item>
    <br>

  </el-form>

  <router-view></router-view>
</div>
</template>

<script>
import axios from 'axios'
  export default {
    name: 'Login',
    data () {
      return {
        account: '',
        password: '',
        form: {
          username: '',
          password: ''
        },
        radio: 3,
        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
        rules: {
          username: [
            {required: true, message: '账号不可为空', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '密码不可为空', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      login () {
        let obj = {
          account: this.account,
          password: this.password
        }
        axios.post('/api/v1/user/login', obj).then((res) => {
          let data = res.data
          if (data.error_code === 0) {
            this.$router.push('')
          }
        })
      }
    }
  }
</script>

<style lang="css" scoped>
  .login-box {
    border: 1px solid #DCDFE6;
    width: 500px;
    margin: 180px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
  }
  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }
  #loginbutton{
    left:-45px;
    bottom: -10px;
    margin-bottom: 20px;
  }
</style>
