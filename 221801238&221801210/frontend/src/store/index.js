import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      name: '',
      email: ''
    },
    paperList: []
  },
  mutations: {
    setUserData(state,payload) {
      localStorage.setItem('user', JSON.stringify(payload))
      console.log(payload)
      state.user = payload
    },
    getUserData(state) {
      let user = JSON.parse(localStorage.getItem('user'))
      console.log(localStorage.getItem('user'))
      state.user = user || {}
    },
    setPaperList(state,payload) {
      state.paperList = payload
    }
  },
  actions: {
  },
  modules: {
  }
})
