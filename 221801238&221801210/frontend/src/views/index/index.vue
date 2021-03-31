<template>
  <div class="full_height" style="width: 100%">
    <div class="top_bar_contianer">
      <!--登录按钮-->
      <el-button class="login_button"  @click="openLoginDialog()">登录</el-button>
    </div>
    <!--主体-->
    <el-row align="middle" justify="center" type="flex" class="full_height middle">
      <p class="title">论文直达</p>
      <el-row class="full_width" align="middle" justify="center" type="flex">
        <searchInput @searchEvent="searchKeyword($event)" />
      </el-row>
      <div class="batch_input_button">
        <i class="el-icon-upload2 icon" />
        <p class="text">批量导入</p>
      </div>
    </el-row>

    <!--登录对话框-->
    <el-dialog title="登录" :visible.sync="showLogin" width="600px">
      <div class="login_dialog">
        <div class="avatar" />
        <div class="dialog_input_contianer">
          <div class="dialog_input">
            <span>邮箱：</span>
            <el-input v-model="user.email" class="input_area"/>
          </div>
          <div class="dialog_input">
            <span>密码：</span>
            <el-input v-model="user.password" show-password class="input_area"/>
          </div>
          <p class="forget">忘记密码？</p>
        </div>
        <div class="dialog_input_contianer">
          <el-button type="primary" class="dialog_button" @click="login()">登录</el-button>
          <el-button  class="dialog_button" @click="openRegisterDialog()">注册</el-button>
        </div>
      </div>

    </el-dialog>

    <!--注册对话框-->
    <el-dialog title="注册" :visible.sync="showRegister" width="600px">
      <div class="login_dialog">
        <div class="dialog_input_contianer">
          <div class="dialog_input">
            <span>用户名：</span>
            <el-input v-model="user.name" placeholder="请设置你的用户名" class="input_area"/>
          </div>
          <div class="dialog_input">
            <span>邮箱：</span>
            <el-input v-model="user.email" placeholder="请输入你的邮箱" class="input_area"/>
          </div>
          <div class="dialog_input">
            <span>新密码：</span>
            <el-input v-model="user.password" placeholder="请设置密码" show-password class="input_area"/>
          </div>
          <div class="dialog_input">
            <span>确认密码：</span>
            <el-input v-model="user.confirmPassword" placeholder="请再次输入密码" show-password class="input_area"/>
          </div>
        </div>
        <el-button class="dialog_register_button" @click="register()">注册</el-button>
      </div>

    </el-dialog>

  </div>
</template>

<script>
import searchInput from '@/components/search-input'
export default {
  name: "index.vue",
  components: {
    searchInput
  },
  data() {
    return {
      showLogin: false,
      showRegister: false,
      user: {
        name: '',
        email: '',
        password: '',
        confirmPassword: ''
      }
    }
  },
  methods: {
    openLoginDialog() {
      console.log('hello')
      this.showLogin = true
    },
    openRegisterDialog(){
      this.showLogin = false
      this.showRegister = true
    },
    login() {
      this.$api.User.login(this.user.email, this.user.password).then(res => {
        this.$message.success('登陆成功')
        this.$router.push('/center')
      }).catch(err => {
        this.$message.error('注册失败')
      })
    },
    register() {
      this.$api.User.register(this.user.email, this.user.password).then(res => {
        this.$message.success('注册成功')
        this.$router.push('/center')
      }).catch(err => {
        this.$message.error('注册失败')
      })
    },
    keyword() {

    }
  }
}
</script>

<style scoped lang="scss">
.login_button {
  width: 172px;
  background-color: rgba(28, 92, 157, 100);
  font-size: 20px;
  color: white;
  letter-spacing: 10px;
  z-index: 1;
}



.middle {
  flex-direction: column;
  font-size: 72px;
  color: rgba(28,92,157,100);
  margin-top: -100px;

  .title {
    margin-bottom: 37px;
    margin-top: 0;
  }
}


.batch_input_button {

  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin-left: 860px;


  .text {
    font-size: 20px;
    font-weight: bold;
  }

  .icon {
    font-size: 24px;
    font-weight: bold;
  }
}


.center_contianer {
  display: flex;
  justify-content: center;
  align-items: center;
  width:  80%;
}

.top_bar_contianer {
  display: flex;
  justify-content: flex-end;
  padding: 10px;
}

.login_dialog {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .dialog_input_contianer {
    width: 400px;
    margin-top: 47px;

    .dialog_input {
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 400px;
    }

    .dialog_button {
      margin-top: 25px;
      width: 172px;
    }

    .dialog_button:nth-child(1) {
      margin-right: 40px;
    }
  }
}

.avatar {
  width: 135px;
  height: 135px;
  border-radius: 100%;
  border: 4px solid rgba(223, 223, 223, 100);
}

.input_area {
  margin-top: 10px;
  width: 300px;
}

.forget {
  color: rgba(25, 137, 250, 100);
  font-size: 14px;
  position: absolute;
  right: 100px;
  z-index: 1;
}

.dialog_register_button {
  width: 172px;
  margin-top: 50px;
}
</style>