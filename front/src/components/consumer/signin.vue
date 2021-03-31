<template>
  <div id = "signin">
    <div class="viceTitle">登录</div>
    <div class="signinForm">
      <Form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="0">
        <FormItem prop="account">
          <Input v-model="formValidate.account" placeholder="请输入用户名"></Input>
        </FormItem>
        <FormItem prop="password">
          <Input v-model="formValidate.password" type="password" password placeholder="请输入密码"></Input>
        </FormItem>
        <FormItem>
          <Button class="subBtn" type="primary" @click="handleSubmit('formValidate')">登录</Button>
          <div id="signinOpe">
            <div @click="toRetrieve()" id="retriveBtn" class="opeBtn">忘记密码</div>
            <div @click="toRegister()" id="loginBtn" class="opeBtn">点我注册</div>
          </div>
        </FormItem>

      </Form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'signin',
  data () {
    return {
      formValidate: {
        account: '',
        password: ''
      },
      ruleValidate: {
        account: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { pattern: /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/, message: '用户名以字母开头，允许5-16字节，允许字母数字下划线', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { pattern: /^[a-zA-Z]\w{5,17}$/, message: '密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$Message.success('Success!')
          this.toIndex()
        } else {
          this.$Message.error('Fail!')
        }
      })
    },
    toRetrieve () {
      this.$router.push({ path: '/retrieve' })
    },
    toRegister () {
      this.$router.push({ path: '/register' })
    },
    toIndex () {
      this.$router.push({ path: '/index' })
    }
  }

}
</script>

<style scoped lang="less">
#signin{
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}
.mytry{
  width: 100px;
  height: 100px;
  background-color: gray;
}
.viceTitle {
  font-size: 28px;
  font-weight: 700;
  color: #000080;
  margin: 10px 0 30px 0;
}
.signinForm {
  width: 60%;

  .subBtn {
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    width: 150px;
    background-color: #428BCA;
    margin: 20px 0 0px 0;
  }
}
#signinOpe {
  width: 100%;
  padding: 5px 5px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  .opeBtn {
    font-size: 12px;
    color: #428BCA;
    cursor: pointer;
    margin: 0 30px;
  }
  .opeBtn:hover {
    text-decoration: underline;
    color: #015478;
  }
}
</style>
