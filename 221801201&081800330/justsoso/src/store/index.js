import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username:'',
    avatarUrl:'',
    account:'',
  },
  mutations: {
    setUsername:function(state,loginUsername){
      state.username=loginUsername;
      sessionStorage.setItem("username", loginUsername);
    },
    setAvatarUrl:function(state,loginAvatarUrl){
      state.avatarUrl=loginAvatarUrl;
      sessionStorage.setItem("avatarUrl", loginAvatarUrl);
    },
    setAccount:function(state,loginAccount){
      state.account=loginAccount;
      sessionStorage.setItem("account", loginAccount);
    },
  },
  actions: {
  },
  modules: {
  }
})
