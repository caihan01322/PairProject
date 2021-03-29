import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/Login'
import Main from '@/components/Main'
// import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    {
      path: '/',
      name: 'login',
      components: login,
      meta: {
        keepalive: false
      }
    },
    {
      path: '/Main',
      name: 'Main',
      compnent: Main,
      meta: {
        keepalive: true
      }
    }
  ]
})
