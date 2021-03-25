<template>
  <div class="frame">
    <div style="margin-bottom: 30px;margin-top: 15px">
      <span id="login_text" class="text_tab"
            :class="{active : isLogin}" @click="isLogin=true;isRegister=false">登录</span>
      <span id="register_text" class="text_tab"
            :class="{active : isRegister}" @click="isLogin=false;isRegister=true">注册</span>
    </div>
    <el-input id="name_input"
              class="input_custom"
              placeholder="请输入昵称"
              v-model="input"
              size="small"/>
    <!--    登录组件-->
    <div v-if="isLogin">
      <el-input id="password_input"
                class="input_custom"
                placeholder="请输入密码"
                v-model="input"
                show-password
                size="small"/>
      <el-button id="login_button"
                 class="button_custom"
                 type="primary" round
                 @click="routeToHome">登 录
      </el-button>
    </div>

    <!--    注册组件-->
    <div v-if="isRegister">
      <el-input id="password_input_register"
                class="input_custom"
                placeholder="请输入密码"
                v-model="input"
                show-password
                size="small"/>
      <el-input id="password_confirm_input"
                class="input_custom"
                placeholder="请再次输入密码"
                v-model="input"
                show-password
                size="small"/>
      <el-button id="register_button"
                 class="button_custom"
                 type="primary" round>注 册
      </el-button>
    </div>
  </div>

</template>

<script>
import axios from "axios";

export default {
  name:"Login",
  data(){
    return {
      inputNickName: '',
      inputPassword: '',
      inputConfirmPassword: '',
      isLogin:true,
      isRegister:false,
    }
  },
  methods:{
    routeToHome:function(){
      this.$router.push("/home");
    },
    login(){

    },
    register(){
      axios
      .post('http://121.5.100.116:8080/api/register?Account='+this.inputNickName+'&password='+this.inputPassword)
          .then(response=>{
            this.papers=this.papers.concat(response.data.data.list);
            for(var paper of response.data.data.list){
              this.paperIds.push(paper.artical.academicNum)
            }
            this.totalPageNum=response.data.data.totalNum;
            if(this.pageNum===0){
              if(this.totalPageNum!==0){
                this.isEmpty=false
                this.searchSuccess(this.totalPageNum)
              }else{
                this.$message({
                  message:'未搜索到文章',
                  type:'warning'
                });
                this.isEmpty=true
              }
            }else{
              this.$message({
                message:'请求第'+(this.pageNum+1)+'页',
                type:'success'
              });
            }
          })
    }

  }
}
</script>

<style scoped lang="less">

.frame {
  background-color: white;
  border-radius: 15px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  padding: 40px;
}


.input_custom /deep/ .el-input__inner {
  background-color: #efefef;
  border-radius: 20px;
  margin-bottom: 10px;
  margin-top: 10px;
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
