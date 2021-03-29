<template>
  <div class="manageAccount">
    <img id="logo_manage" src="../assets/logo.png" alt="logo" @click="routeToSearch"/>
    <div id="avatar_background">
      <img id="manage_avatar" src="../assets/background_search.jpg" alt="avatar">
    </div>
    <div id="main_frame">
      <div style="margin-top: 100px;position: relative">
        <input id="username" ref="username" >
        <img id="modify_username" src="../assets/modify.svg" alt="modify_username" @click="modifyUsername">
      </div>
      
    </div>
    <div id="background_manage"/>
  </div>
</template>

<script>
export default {
  name:"ManageAccount",
  data(){
    return {
      usernameEditable:false
    }
  },
  methods:{
    routeToSearch:function(){
      this.$router.push("/")
    },
    modifyUsername(){
      if(!this.usernameEditable){
        this.usernameEditable=true
      }else{
        this.usernameEditable=false
      }
    }
  }
}
</script>

<style scoped>
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

#manage_avatar {
  border-radius: 50%;
  width: 92%;
  height: 92%;
  margin-top: 4%;
}

#modify_username {
  width: 15px;
  height: 15px;
  position: absolute;
  bottom: 3px;
  right: -20px;
  cursor: pointer;
}

#username {
  font-weight: bold;
  font-size: 30px;
  color: black;
  border-radius: 10px;
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
