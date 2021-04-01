import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Axios from 'axios'
import echarts from 'echarts'
/* import global from '../Global'
Vue.prototype.GLOBAL=global
axios.defaults.baseURL=global_Base_URL */




Vue.use(ElementUI)
Vue.prototype.$echarts = echarts

Vue.prototype.$axios = Axios

Vue.config.productionTip = false


new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
