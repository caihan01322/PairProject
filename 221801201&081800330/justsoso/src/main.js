import Vue from 'vue';
import ElementUI from 'element-ui';
import './theme/index.css'
import App from './App.vue';
import router from './router'
// import ECharts from 'vue-echarts'
import { use } from "echarts/core"

import {
  CanvasRenderer
} from 'echarts/renderers'
import {
  BarChart
} from 'echarts/charts'
import {
  GridComponent,
  TooltipComponent
} from 'echarts/components'

use([
  CanvasRenderer,
  BarChart,
  GridComponent,
  TooltipComponent
]);


Vue.use(ElementUI);

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});

// App.component('v-chart', ECharts)
