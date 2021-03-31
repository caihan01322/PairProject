import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    searchKey: '',
    isSearchKey: false
  },
  mutations: {
    setSerchKey (state, key) {
      state.searchKey = key
    },
    openSearchKey (state) {
      state.isSearchKey = true
    },
    closeSearchKey (state) {
      state.isSearchKey = false
    }
  },
  actions: {
  },
  modules: {
  }
})
