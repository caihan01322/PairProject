<template>
  <div class="app">
    <div id="wrap">
      <div id="picture">
        <img alt="图片" src="../assets/css/7.jpg" />
      </div>
      <div id="userLogin">
        <h1>登录</h1>
        <p>登录账号</p>
        <i class="el-icon-user-solid"></i>
        <el-input placeholder="请输入内容" v-model="userForm.username" clearable></el-input>
        <p>登录密码</p>
        <i class="el-icon-lock"></i>
        <el-input placeholder="请输入密码" v-model="userForm.password" show-password></el-input>
        <input id="submitButton" type="submit" value="登录" @click="postMes()" />
        <input id="goSignup" type="button" value="注册新账号→" @click="gotolink()" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import qs from 'qs';
export default {
  data() {
    return {
      userForm: {
        username: "",
        password: "",
      },
      data:{},
    };
  },
  methods: {
    gotolink() {
      this.$router.replace("/signin");
    },
    postMes() {
      axios.post("http://120.77.179.111:3300/data/login", this.userForm, function(
        data,
        status
      ) {
      }).then((res)=>{
          this.data=res.data;
          //this.data.msg = mes.replace( /^\s+|\s+$/g, "" );
          if(this.data.msg==""){
             this.$message.success("Login Successfully!");
             this.$router.push('/manage');
          }
          else this.$message.error(this.data.msg);
          //msgBox(this.data.msg);
          //console.log(this.data.status);
        });
    },
     test() {
      console.log(JSON.stringify(this.tes));
    } 
  }
};
</script>

<style scoped>
#picture {
  display: inline-block;
  width: 57%;
  height: 100%;
  vertical-align: top;
}

#userLogin {
  display: inline-block;
  width: 43%;
  height: 100%;
  vertical-align: top;
}

#picture img {
  margin-left: 114px;
  margin-top: 83px;

  width: 600px;
  height: 600px;
}

#userLogin h1 {
  padding-left: 9px;
  padding-top: 120px;
  color: aliceblue;
}

#userLogin p {
  margin-top: 48px;
  margin-left: 9px;
  font-size: 14px;
  color: aliceblue;
}

.el-icon-user-solid {
  color: aliceblue;
  font-size: 30px;
}

.el-icon-lock {
  color: aliceblue;
  font-size: 30px;
}

.el-input {
  padding-left: 20px;
  width: 357px;
  height: 50px;
}

#submitButton {
  background-color: #2d8ed0;
  color: #fcfcfc;
  font-size: 16px;

  width: 405px;
  height: 59px;
  margin-top: 51px;
  margin-left: 9px;
}

#goSignup {
  background-color: #0e151d;
  color: #eae9e9;
  border: none;

  width: 383px;
  height: 57px;
  padding-left: 19px;
}
</style>