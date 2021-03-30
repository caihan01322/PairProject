<template>
  <div class="hello">

    <transition name="fade">
      <!--    登录弹窗-->
      <Login v-model="loginVisible" id="login" v-if="loginVisible"/>

      <ManageAccountPop v-model="manageAccountVisible" id="manage_account" v-if="manageAccountVisible"/>
    </transition>

    <img id="logo" src="../assets/logo.png" alt="logo"/>
    <div id="title">论文搜索平台</div>

    <!--    搜索框-->
    <div id="search_div">
      <el-input id="search_input"
                placeholder="搜索论文题目 / 摘要 / 关键字 / 作者"
                v-model="searchWord"
                clearable="clearable"
                @keyup.enter.native="routeToHome">
        <el-button id="search_button" slot="suffix" icon="el-icon-search" circle @click="routeToHome"/>
      </el-input>
    </div>

    <div id="background"/>
    <img id="avatar"
         alt="avatar"
         :src="avatarUrl!=='' ? avatarUrl : defaultAvatar"
         @click="clickAvatar">
  </div>
</template>

<script>
import Login from "@/components/LoginPop";
import ManageAccountPop from "@/components/ManageAccountPop";

export default {
  name:'Search',
  components:{ManageAccountPop,Login},
  props:{
    msg:String
  },
  watch:{
    loginVisible(){
      this.avatarUrl= this.$store.state.avatarUrl
    },
    manageAccountVisible(){
      this.avatarUrl= this.$store.state.avatarUrl
    }
  },
  data(){
    return {
      searchWord:'',
      loginVisible:false,
      manageAccountVisible:false,
      avatarUrl: this.$store.state.avatarUrl,
      defaultAvatar:require('../assets/avatar.png')
    }
  },
  methods:{
    routeToHome:function(){
      this.$router.push({
        path:'/home',
        name:'Home',
        query:{
          searchWord:this.searchWord
        }
      })
    },
    clickAvatar(){
      if(this.$store.state.account===''){
        this.loginVisible=!this.loginVisible
      }else{
        this.manageAccountVisible=!this.manageAccountVisible
      }
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>

h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}


#login {
  width: 240px;
  position: absolute;
  top: 100px;
  right: 50px;
}

#manage_account {
  width: 240px;
  position: absolute;
  top: 100px;
  right: 50px;
}

#logo {
  margin-top: 8%;
  width: 30%;
}

#title {
  font-family: 微软雅黑, sans-serif;
  font-size: 25px;
  color: #ffffff;
  letter-spacing: 20px;
}

#avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  position: absolute;
  alignment: right;
  top: 50px;
  right: 50px;
  transition: transform .3s ease-in-out, box-shadow .3s cubic-bezier(.47, 0, .745, .715), border .3s linear .1s;
}

#avatar:hover {
  filter: drop-shadow(5px 5px 5px #000000);
  -webkit-transform: translateY(-5px);
  -moz-transform: translateY(-5px);
  transform: translateY(-5px)
}

#search_div {
  width: 50%;
  margin: 0 auto;
  margin-top: 50px;
}

#search_input {
  border-radius: 100px;
}

#search_button {
  border: none;
  background-color: rgba(0, 0, 0, 0);
}

#search_button:hover {
  background-color: rgba(0, 0, 0, 0);
}

#background {
  z-index: -2;
  background-image: url("../assets/background_search.jpg");
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: transparent;
  background-size: cover;
  background-position: center center;
  background-repeat: no-repeat
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s
}

.fade-enter, .fade-leave-active {
  opacity: 0
}
</style>
