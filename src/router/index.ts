import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
// 用户部分
import Consumer from '../views/consumer.vue'
import SignIn from '../components/consumer/signin.vue'
import Register from '../components/consumer/register.vue'
import Retrieve from '../components/consumer/retrieve.vue'

//
import Index from '../views/index.vue'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    redirect: '/signIn',
    name: 'consumer',
    component: Consumer,
    children: [
      {
        path: 'signIn',
        name: 'signIn',
        component: SignIn
      },
      {
        path: 'register',
        name: 'register',
        component: Register
      },
      {
        path: 'retrieve',
        name: 'retrieve',
        component: Retrieve
      }
    ]
  },
  {
    path: '/index',
    name: 'index',
    component: Index
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
