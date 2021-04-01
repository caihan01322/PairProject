<template>
  <div>
    <div class="main">
      <IntroView/>

      <div id="login">
        <div id="head">
          <button id="bt" disabled="">登录</button>
        </div>
        <div id="lform">
          <label class="lab">账号</label>
          <el-input v-model="username" placeholder="请输入账号"></el-input>
          <label class="lab">密码</label>
          <el-input
            type="password"
            v-model="password"
            placeholder="请输入密码"
            @keydown.enter.native="doLogin"
          ></el-input>
          <el-button style="width: 270px" type="primary" @click="doLogin"
          >登录
          </el-button
          >
          <div id="bm">
            <label id="lb">还没有账号？</label>
            <el-button type="primary" @click="doRegister"
            >立即注册
            </el-button
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import bus from '@/assets/eventBus';
import IntroView from "@/components/IntroView";

export default {
  data() {
    return {
      username: "",
      password: "",
      state: {
        status: 0,
        index: 0
      }

    };
  },
  methods: {
    doLogin() {
      this.$axios.get('http://localhost:8081/login', {
        params: {
          username: this.username,
          password: this.password
        }
      }, {headers: {'Content-Type': 'application/x-www-form-urlencoded format'}})
        .then(res => {
          if (res.data === 1) {
            this.state.status = 1;
            this.state.index = 1;

            bus.$emit('sendStatus', this.state);
            this.$router.replace('/Search');
          } else if (res.data === -1) {
            alert("用户未注册，请注册后再登录")
          }
        })
        .catch(failResponse => {
        })
    },
    doRegister() {
      this.$router.replace("/register");
    },
  }, mounted() {
    bus.$emit('sendStatus', this.state);
  },
  components: {
    IntroView
  }
};
</script>
<style lang="less" scoped>
.main {
  height: 100%;
  width: 100%;
  background: rgba(53, 64, 68, 1);
  position: fixed;
}

#login {
  height: 592px;
  width: 446px;
  background-color: #233b47;
  margin-left: 35px;
  margin-top: 50px;
  float: left;
}

#head {
  height: 100px;
  width: 446px;

  #bt {
    height: 30px;
    width: 120px;
    font-size: 15pt;
    color: white;
    border-radius: 25px;
    background-color: #4b7902;
    margin-left: 160px;
    margin-top: 30px;
  }
}

#lform {
  height: 392px;
  width: 446px;

  .lab {
    font-size: 20px;
    font-weight: bold;
    width: 40px;
    margin-left: 40px;
    color: white;
    margin-top: 20px;
    display: block;
  }

  .el-input {
    font-size: 16px;
    margin-top: 10px;
    margin-left: 40px;
    width: 357px;
    height: 36px;
    display: block;
  }

  .el-button {
    font-size: 20px;
    font-weight: bold;
    margin-top: 45px;
    margin-left: 75px;
    border-radius: 25px;
    display: block;
  }
}

#bm {
  font-size: 18px;
  margin-top: 160px;

  #lb {
    font-size: 20px;
    margin-top: 10px;
    color: white;
    margin-left: 110px;
    float: left;
  }

  .el-button {
    background-color: #233b47;
    color: #02a7f0;
    font-weight: lighter;
    border: none;
    text-decoration: underline;
    float: none;
    font-size: 20px;
    margin-left: 180px;
  }
}
</style>
