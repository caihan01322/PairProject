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
                     @click="logout">退出登录
          </el-button>

  </div>

</template>

<script>

export default {
  name:"ManageAccountPop",
  model:{
    prop:"manageAccountVisible",
    event:"visible-event"
  },
  props:{
    manageAccountVisible:{
      type:Boolean
    }
  },
  data(){
    return {
    }
  },
  methods:{
    manageAccount(){
      this.$router.push("/manageAccount")
    },
    logout(){
      this.$message.success("已退出登录")
      this.manageAccountVisible=false
      this.$emit('visible-event',this.manageAccountVisible)
      this.$store.commit('setUsername',"")
      this.$store.commit('setAvatarUrl',"")
      this.$store.commit('setAccount',"")
      this.$router.push("/")
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

#logout_button{
  background-color: #9e4d4d;
}

.el-button+.el-button {
  margin-left: 0;
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
  position: relative;
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
