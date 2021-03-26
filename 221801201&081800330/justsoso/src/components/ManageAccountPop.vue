<template>
  <div class="frame">
          <el-button id="manage_account_button"
                     class="button_custom"
                     type="primary" round
                     @click="manageAccount">修改信息
          </el-button>

          <el-button id="logout_button"
                     class="button_custom"
                     type="primary" round
                     @click="register">退出登录
          </el-button>

  </div>

</template>

<script>
import axios from "axios";

export default {
  name:"Login",
  model:{
    prop:"manageAccountVisible",
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
              this.$store.commit('setUser'
                  ,response.data.data.account
                  ,response.data.data.username
                  ,'https://i.loli.net/2021/03/17/gIm31pPLdirouRc.jpg'
                  ,true
              )
              // response.data.data.avatarUrl
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
