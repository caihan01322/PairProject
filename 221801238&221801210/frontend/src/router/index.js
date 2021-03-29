import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: () => import("@/views/index")
  },
  {
    path: '/search',
    name: 'Search',
    component: () => import("@/views/search")
  },
  {
    path: '/favourite',
    name: 'Favorite',
    component: () => import("@/views/favourite")
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
