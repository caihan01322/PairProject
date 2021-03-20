import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router/router'
import './assets/icon/iconfont.css'

Vue.config.productionTip = false;

axios.defaults.withCredentials = true;
// axios.defaults.baseURL = ''

Vue.prototype.$axios = axios

Vue.use(ElementUI)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
