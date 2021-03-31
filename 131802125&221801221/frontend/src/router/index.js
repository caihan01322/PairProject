import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import Display from '../components/Display'
import Paper from '../components/Paper'
import Statistics from '../components/Statistics'
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
    {
        path: '/display',
        name: 'display',
        component: Display,
        children:[
            {
              path: 'paper',
              name: 'paper',
              component: Paper,
              meta: {
                title: '论文信息'
              }
            },
            {
                path: 'statistics',
                name: 'statistics',
                component: Statistics,
                meta: {
                  title: '统计数据'
                }
              },
          ]
    },
]

const router = new VueRouter({
    routes
})
  
export default router