<template>
  <div class="frame">
    <div style="margin-bottom: 30px;margin-top: 15px">
      <span id="login_text" class="text_tab"
            :class="{active : isLogin}" @click="isLogin=true;isRegister=false">登录</span>
      <span id="register_text" class="text_tab"
            :class="{active : isRegister}" @click="isLogin=false;isRegister=true">注册</span>
    </div>

    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm">
      <el-form-item prop="inputNickName">
        <el-input id="name_input"
                  class="input_custom"
                  placeholder="请输入昵称"
                  v-model="ruleForm.inputNickName"
                  size="small"/>
      </el-form-item>
      <el-form-item prop="inputPassword">
        <el-input id="password_input"
                  class="input_custom"
                  placeholder="请输入密码"
                  v-model="ruleForm.inputPassword"
                  show-password
                  size="small"/>
      </el-form-item>
      <el-form-item prop="inputConfirmPassword" v-if="isRegister">
        <el-input id="password_confirm_input"
                  class="input_custom"
                  placeholder="请再次输入密码"
                  v-model="ruleForm.inputConfirmPassword"
                  show-password
                  size="small"/>
      </el-form-item>
      <el-form-item>
        <!--        登录按钮-->
        <div v-if="isLogin">
          <el-button id="login_button"
                     class="button_custom"
                     type="primary" round
                     @click="login">登 录
          </el-button>
        </div>

        <!--    注册按钮-->
        <div v-if="isRegister">
          <el-button id="register_button"
                     class="button_custom"
                     type="primary" round
                     @click="register">注 册
          </el-button>
        </div>
      </el-form-item>
    </el-form>

  </div>

</template>

<script>
import axios from "axios";

export default {
  name:"Login",
  model:{
    prop:"loginVisible",
    event:"visible-event"
  },
  props:{
    loginVisible:{
      type:Boolean
    }
  },
  data(){
    var checkNickName=(rule,value,callback)=>{
      if(!value){
        return callback(new Error('昵称不能为空'));
      }
      setTimeout(()=>{
        if(value.length<6){
          callback(new Error('昵称必须大于6位'));
        }else{
          callback();
        }
      },1000);
    };
    var validatePass=(rule,value,callback)=>{
      if(value===''){
        callback(new Error('请输入密码'));
      }else if(value.length<6){
        callback(new Error('密码需大于6位'))
      }else{
        if(this.ruleForm.inputPassword!==''){
          this.$refs.ruleForm.validateField('validatePass2');
        }
        callback();
      }
    };
    var validatePass2=(rule,value,callback)=>{
      if(value===''){
        callback(new Error('请再次输入密码'));
      }else if(value!==this.ruleForm.inputPassword){
        callback(new Error('两次输入密码不一致!'));
      }else{
        callback();
      }
    };
    return {
      ruleForm:{
        inputNickName:'',
        inputPassword:'',
        inputConfirmPassword:'',
      },
      isLogin:true,
      isRegister:false,
      rules:{
        inputPassword:[
          {validator:validatePass,trigger:'blur'}
        ],
        inputConfirmPassword:[
          {validator:validatePass2,trigger:'blur'}
        ],
        inputNickName:[
          {validator:checkNickName,trigger:'blur'}
        ]
      }
    }
  },
  methods:{
    routeToHome:function(){
      this.$router.push("/home");
    },
    login(){
      axios
          .post('http://121.5.100.116:8080/api/login?Account='+this.ruleForm.inputNickName+'&password='+this.ruleForm.inputPassword)
          .then(response=>{
            if(response.data.code!==200){
              this.$message.error(response.data.message+"，登录失败！")
            }else{
              this.$message.success("登录成功！")
              this.loginVisible=false
              this.$emit('visible-event',this.loginVisible)
              this.$store.commit('setUsername',response.data.data.username)
              this.$store.commit('setAvatarUrl',response.data.data.imgUrl)
              this.$store.commit('setAccount',response.data.data.Account)
            }
          })
    },
    register(){
      this.$refs['ruleForm'].validate((valid)=>{
        if(valid){
          axios
              .post('http://121.5.100.116:8080/api/register?Account='+this.ruleForm.inputNickName+'&password='+this.ruleForm.inputPassword)
              .then(response=>{
                if(response.data.code!==200){
                  this.$message.error(response.data.message+"，注册失败！")
                }else{
                  this.$message.success("注册成功！")
                }
              })
        }else{
          this.$message.error("请确认注册填写正确")
        }
      });
    },
  }
}
</script>

<style scoped lang="less">

.frame {
  background-color: white;
  border-radius: 15px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  padding: 40px;
  z-index: 50;
}


.input_custom /deep/ .el-input__inner {
  background-color: #efefef;
  border-radius: 20px;
  margin-bottom: 10px;
  margin-top: 10px;
  z-index: 4;
}

.button_custom {
  background-color: #405869;
  width: 100%;
  height: 28px;
  line-height: 5px;
  margin-top: 10px;
  margin-bottom: 10px;
  font-size: 12px;
  font-weight: bold;
  cursor: pointer;
}

.text_tab {
  color: #999999;
  font-size: 15px;
  font-weight: bold;
  margin: 10px;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
}

.text_tab:hover {
  color: #007aff;
}

.active {
  color: #007aff;
}

</style>
