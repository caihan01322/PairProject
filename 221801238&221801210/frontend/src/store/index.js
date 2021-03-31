import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      name: '',
      email: '',
      token: ''
    }
  },
  mutations: {
    setUserData(state,payload) {
      localStorage.set('user', JSON.stringify(state,payload))
      state.user = payload
    },
    getUserData(state) {
      let user = JSON.parse(localStorage.getItem('user'))
      state.user = user || {}
    }
  },
  actions: {
  },
  modules: {
  }
})
