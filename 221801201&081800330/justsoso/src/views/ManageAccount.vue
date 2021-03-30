<template>
  <div class="manageAccount">
    <img id="logo_manage" src="../assets/logo.png" alt="logo" @click="routeToSearch"/>
    <div id="avatar_background">
      <el-upload
          class="avatar_uploader"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :action="'http://121.5.100.116:8080/api/imgUpload?Account='+this.$store.state.account">
        <img id="manage_avatar"
             v-if="avatarUrl!==''"
             :src="avatarUrl!=='' ? avatarUrl : defaultAvatar"
             alt="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </div>

    <div id="main_frame">
      <div style="margin-top: 100px;position: relative;">
        <input id="username" ref="username" :disabled="usernameDisable"
               :placeholder="this.$store.state.username.toString()">
        <img id="modify_username" src="../assets/modify.svg" alt="modify_username" @click="modifyUsername">
      </div>
      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" style="width: 406px;margin-top: 50px">
        <el-form-item prop="inputOldPassword">
          <el-input id="old_password"
                    class="input_custom"
                    placeholder="请输入旧密码"
                    v-model="ruleForm.inputOldPassword"
                    show-password
                    size="small"/>
        </el-form-item>
        <el-form-item prop="inputNewPassword">
          <el-input id="new_password"
                    class="input_custom"
                    placeholder="请输入新密码"
                    v-model="ruleForm.inputNewPassword"
                    show-password
                    size="small"/>
        </el-form-item>

        <el-form-item>
          <div>
            <el-button id="modify_button"
                       class="button_custom"
                       type="primary" round
                       @click="modifyPassword">确认修改密码
            </el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
    <div id="background_manage"/>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name:"ManageAccount",
  data(){
    var validatePass=(rule,value,callback)=>{
      if(value===''){
        callback(new Error('请输入密码'));
      }else if(value.length<6){
        callback(new Error('密码需大于6位'))
      }else{
        callback();
      }
    };
    return {
      usernameDisable:true,
      avatarUrl:this.$store.state.avatarUrl,
      defaultAvatar:require('../assets/avatar.png'),
      ruleForm:{
        inputOldPassword:'',
        inputNewPassword:'',
      },
      rules:{
        inputOldPassword:[
          {validator:validatePass,trigger:'blur'}
        ],
        inputNewPassword:[
          {validator:validatePass,trigger:'blur'}
        ],
      }
    }
  },
  methods:{
    routeToSearch:function(){
      this.$router.push("/")
    },
    modifyUsername(){
      if(!this.usernameDisable){
        axios
            .post('http://121.5.100.116:8080/api/updateInfo?Account='+this.$store.state.account
                +'&username='+document.getElementById('username').value)
            .then(response=>{
              if(response.data.code===200){
                this.$store.commit('setUsername',document.getElementById('username').value)
                this.$message.success('修改用户名成功！')
              }else{
                this.$message.error('修改用户名失败！')
                document.getElementById('username').value=this.$store.state.username
              }
            })
        this.usernameDisable=true
      }else{
        this.usernameDisable=false
      }
    },
    modifyPassword(){
      this.$refs['ruleForm'].validate((valid)=>{
        if(valid){
          axios
              .post('http://121.5.100.116:8080/api/updateInfo?Account='+this.$store.state.account
                  +'&username='+document.getElementById('username').value
                  +'&oldPassword='+this.ruleForm.inputOldPassword
                  +'&newPassword='+this.ruleForm.inputNewPassword)
              .then(response=>{
                if(response.data.code===200){
                  this.$message.success('修改密码成功！')
                }else{
                  this.$message.error('修改密码失败！')
                }
              })
        }else{
          this.$message.error("请确认注册填写正确")
        }
      });
    },
    handleAvatarSuccess(res){
      console.log(res)
      if(res.code===200){
      this.$message.success('上传头像成功！')
        this.$store.commit('setAvatarUrl',res.data.toString())
        this.avatarUrl=this.$store.state.avatarUrl
      }else{
        this.$message.error(res.message.toString()+'，上传头像失败！')
      }
    }
  }
}
</script>

<style scoped lang="less">
.manageAccount {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#logo_manage {
  position: absolute;
  top: 20px;
  left: 30px;
  width: auto;
  height: 90px;
  cursor: pointer;
  transition: transform .3s ease-in-out, box-shadow .3s cubic-bezier(.47, 0, .745, .715), border .3s linear .1s;
}

#logo_manage:hover {
  filter: drop-shadow(5px 5px 5px #000000);
  -webkit-transform: translateY(-5px);
  -moz-transform: translateY(-5px);
  transform: translateY(-5px)
}

#avatar_background {
  background-color: white;
  border-radius: 50%;
  width: 180px;
  height: 180px;
  margin-top: 110px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  z-index: 10;
}

.avatar_uploader {
  width: 180px;
  height: 180px;
  z-index: 900;
}

.avatar_uploader /deep/ .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar_uploader /deep/ .el-upload:hover {
  border-color: #409EFF;
}

.avatar_uploader /deep/ .el-upload--text {
  width: 92%;
  height: 92%;
  margin-top: 4%;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100%;
  height: 100%;
  line-height: 170px;
  text-align: center;
}

#manage_avatar {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

#modify_username {
  width: 15px;
  height: 15px;
  position: absolute;
  bottom: 3px;
  right: -25px;
  cursor: pointer;
}

#username {
  font-weight: bold;
  font-size: 30px;
  color: black;
  border-radius: 10px;
  outline: none;
  text-align: center;
}

#main_frame {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;

  position: absolute;
  height: 430px;
  margin-top: 200px;
  margin-bottom: 50px;
  width: 90%;
  background-color: #f7f7f7;
  border-radius: 15px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  padding-top: 20px;
  padding-bottom: 50px;
  z-index: 5;
}

#old_password {
  width: 100%;
}

#new_password {
  width: 100%;
}

#modify_button {
  width: 100%;
  border-radius: 10px;
}

.input_custom /deep/ .el-input__inner {
  background-color: #ffffff;
  border-radius: 10px;
  margin-bottom: 10px;
  z-index: 4;
}

.button_custom {
  background-color: #405869;
  height: 28px;
  line-height: 5px;
  margin-top: 10px;
  margin-bottom: 10px;
  font-size: 12px;
  font-weight: bold;
  cursor: pointer;
}

#background_manage {
  z-index: -2;
  background-image: url("../assets/background.png");
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: transparent;
  background-size: cover;
  background-position: center center;
  background-repeat: no-repeat
}
</style>
