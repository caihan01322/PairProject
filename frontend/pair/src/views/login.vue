<template>
<div>
  <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
    <h3 class="login-title">登录</h3>
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
      <router-link  to="/register">立即注册</router-link>
  <div>
    <br>
  </div>
    <el-form-item>
      <el-button id="loginbutton" type="primary" v-on:click="login">登录</el-button>
    </el-form-item>
    <br>

  </el-form>

  <el-dialog
    title="温馨提示"
    :visible.sync="dialogVisible"
    width="30%"
    >
    <span>请输入账号和密码</span>
    <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
    </span>
  </el-dialog>
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
        },
        // 对话框显示和隐藏
        dialogVisible: false
      }
    },
    methods: {
      onSubmit (formName) {
        // 为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
            this.$router.push('/main/' + this.form.username)
          } else {
            this.dialogVisible = true
            return false
          }
        })
      },
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
