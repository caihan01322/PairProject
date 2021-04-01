import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router/router'
import './assets/icon/iconfont.css'
import echarts from 'echarts'

Vue.config.productionTip = false;

// axios.defaults.withCredentials = true;
axios.defaults.baseURL = 'http://118.31.56.126/api/pages/'

Vue.prototype.$axios = axios
Vue.prototype.$echarts = echarts

Vue.use(ElementUI)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
