import Vue from 'vue';
import ElementUI from 'element-ui';
import './theme/index.css'
import App from './App.vue';
import router from './router'
import store from './store'


import * as echarts from 'echarts';
// 或
// const echarts = require('echarts');
Vue.prototype.$echarts = echarts


Vue.use(ElementUI);

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
});

// App.component('v-chart', ECharts)
