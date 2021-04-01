import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
// 用户部分
import Consumer from '../views/consumer.vue'
import SignIn from '../components/consumer/signin.vue'
import Register from '../components/consumer/register.vue'
import Retrieve from '../components/consumer/retrieve.vue'

// 论文主体页
import Index from '../views/index.vue'
import Search from '../components/index/search.vue'
import Record from '../components/index/record.vue'
import WordCloud from '../components/index/hotWord/wordCloud.vue'
import WordTrend from '../components/index/hotWord/wordTrend.vue'
import WordContrast from '../components/index/hotWord/wordContrast.vue'

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
    component: Index,
    redirect: '/index/search',
    children: [
      {
        path: 'search',
        name: 'search',
        component: Search
      },
      {
        path: 'record',
        name: 'record',
        component: Record
      },
      {
        path: 'wordcloud',
        name: 'wordcloud',
        component: WordCloud
      },
      {
        path: 'wordtrend',
        name: 'wordtrend',
        component: WordTrend
      },
      {
        path: 'wordcontrast',
        name: 'wordcontrast',
        component: WordContrast
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
