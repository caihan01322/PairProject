<template>
<div>
  <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
    <h3 class="login-title">欢迎注册</h3>
    <el-form-item label="账号" prop="username">
      <el-input type="text" placeholder="请输入手机号/邮箱" v-model="account"/>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input type="password" placeholder="请输入密码" v-model="password"/>
    </el-form-item>
     <el-form-item>
     <!-- <el-checkbox-group v-model="checkList" id="checkbox">
    <el-checkbox label="1">分论坛1</el-checkbox>
    <el-checkbox label="2">分论坛2</el-checkbox>
    <el-checkbox label="3">分论坛3</el-checkbox>
    </el-checkbox-group> -->

      <el-button id="register" type="primary" v-on:click="register">注册</el-button>
    </el-form-item>
              <router-link to="/login">
              <br>
              <a id="bbs">已有账号请登录</a></router-link>

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
    name: 'login',
    data () {
      return {
        account: '',
        password: '',
        form: {
          username: '',
          password: ''
        },
     checkList: [],
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
      onSubmit () {
        // 为表单绑定验证功能
      },
      register () {
          for (let i = 0; i < this.checkList.length; i++) {
              this.checkList[i] = parseInt(this.checkList[i])
          }
          console.log(this.checkList)
          let obj = {
              account: this.account,
              password: this.password,
              forumid: this.checkList
          }
          axios.post('/api/v1/user/register', obj).then((res) => {
              console.log(res)
          })
      }
    }
  }
</script>

<style scoped>
  .login-box {
    border: 1px solid #DCDFE6;
    height: 400px;
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
  #register{
    margin-top: 30px;
     position:inherit;
    left:-45px;
    bottom: -10px;
    margin-bottom: 30px;
  }
  #checkbox{
    margin-top: 30px;
     position:inherit;
    left:-45px;
    bottom: -10px;
  }
</style>
