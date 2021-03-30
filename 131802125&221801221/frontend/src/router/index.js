import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'login',
        component: Login,
        meta: {
        title: '登录'
        }
    },
    {
        path: '/home',
        name: 'home',
        component: Home,
        meta: {
        title: '搜索'
        }
    },
]

const router = new VueRouter({
    routes
  })
  
export default router