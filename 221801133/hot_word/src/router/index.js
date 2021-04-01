import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home'
import Signin from '../components/Signin'
import Analy from '../components/Analy'
import Manage from '../components/Manage'
import Test from '../components/test'
import Tourist from '../components/Tourist'
import Link from '../components/Link'
import Click from '../components/Click'
import Sk from '../components/Sk'

Vue.use(Router)

export default new Router({
  routes: [
    { path: '/', redirect: '/tour' },
    { path: '/tour', component: Tourist },
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    { path: '/', redirect: '/home' },
    { path: '/home', component: Home },
    { path: '/', redirect: '/signin' },
    { path: '/signin', component: Signin },
    { path: '/', redirect: '/analy' },
    { path: '/analy', component: Analy },
    { path: '/', redirect: '/manage' },
    { path: '/manage', component: Manage },
    { path: '/', redirect: '/test' },
    { path: '/test', component: Test },
    { path: '/', redirect: '/link' },
    { path: '/link', component: Link },
    { path: '/', redirect: '/click' },
    { path: '/click', component: Click },
    { path: '/', redirect: '/sk' },
    { path: '/sk', component: Sk }
  ],
})
