import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/global.css'

import ViewUI from 'view-design'
import 'view-design/dist/styles/iview.css'

import vuescroll from 'vuescroll'

Vue.use(ViewUI)

Vue.use(vuescroll, {
  ops: {
    bar: {
      showDelay: 500,
      onlyShowBarOnScroll: true,
      keepShow: false,
      background: '#cecece',
      opacity: 0.5,
      hoverStyle: false,
      specifyBorderRadius: false,
      minSize: 0.2,
      size: '5px'
    }
  }, // 在这里设置全局默认配置
  name: 'myScroll' // 在这里自定义组件名字，默认是vueScroll
})

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
