import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username:'',
    avatarUrl:'',
    isLogin:''
  },
  mutations: {
    setUser:function(state,loginUsername,loginAvatarUrl,isLogin){
      state.username=loginUsername;
      state.avatarUrl=loginAvatarUrl;
      state.isLogin=isLogin;
      sessionStorage.setItem("username", loginUsername);
      sessionStorage.setItem("avatarUrl", loginAvatarUrl);
      sessionStorage.setItem("isLogin", isLogin);
    },
  },
  actions: {
  },
  modules: {
  }
})
