<template>
  <div class="register">
    <div class="reg">
      <div id="head">
        <button id="bt" disabled="">注册</button>
      </div>
      <el-form
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="账号" prop="user">
          <el-input
            type="text"
            v-model="ruleForm.user"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input
            type="password"
            v-model="ruleForm.pass"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item label="确认密码" prop="checkPass">
          <el-input
            type="password"
            v-model="ruleForm.checkPass"
            autocomplete="off"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
            id="rgb"
            type="primary"
            @click.native="submitForm('ruleForm')"
          >提交
          </el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    const validateUser = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入账号"));
      } else if (value.length < 6) {
        callback(new Error("账号位数不能小于6位"));
      } else {
        callback();
      }
    };
    const validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else if (value.length < 6) {
        callback(new Error("密码位数不能小于6位"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        user: "",
        pass: "",
        checkPass: "",
      },
      rules: {
        user: [{validator: validateUser, trigger: "blur"}],
        pass: [{validator: validatePass, trigger: "blur"}],
        checkPass: [{validator: validatePass2, trigger: "blur"}],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .get('http://localhost:8081/register', {
              params: {
                username: this.ruleForm.user,
                password: this.ruleForm.pass
              }
            }, {headers: {'Content-Type': 'application/x-www-form-urlencoded format'}})
            .then(res => {
              if (res.data === 1) {
                this.$message.success("注册成功")
                this.$router.replace({path: '/Login'})
              } else {
                this.$message.error("注册失败，用户名已被注册！")
              }

            }).catch(failResponse => {
          })

        } else {
          this.$message.error("信息填写有误！请重新填写")
          return false;
        }
      });
    },
  },
};
</script>

<style lang="less">
.register {
  background-color: rgba(53, 64, 68, 1);
  height: 835px;
  width: 100%;
  padding-top: 85px;
}

.reg {
  height: 592px;
  width: 446px;
  color: white;
  background-color: #233b47;
  margin-left: 750px;


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
}

.el-form-item__label {
  text-align: left !important;
  font-weight: bold !important;
  padding: 0 0 0 0 !important;
  float: none !important;
  font-size: 20px !important;
  color: #f0f2f6 !important;
  margin-left: 40px;
  line-height: 40px;
  display: block;
}

.el-form-item__content {
  line-height: 40px;
  margin-left: 40px !important;
  width: 360px;
  font-size: 14px;
}

.el-button#rgb {
  display: inline;
  border: none;
  width: 260px !important;
  font-size: 20px !important;
  float: none;
  margin-top: 30px !important;
  margin-left: 45px !important;
  border-radius: 30px !important;
}
</style>
