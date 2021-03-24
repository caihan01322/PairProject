import Vue from 'vue'
import VueRouter from 'vue-router'

import List from '../views/List'
import Edit from '../views/Edit'
import Analyze from '../views/Analyze'
import Detail from '../views/Detail'
import Related from '../views/Related'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'index',
    component: List,
    meta: {
      title: '论文站首页'
    }
  },
  {
    path: '/index',
    name: 'index',
    component: List,
    meta: {
      title: '论文站首页'
    }
  },
  {
    path: '/detail',
    name: 'detail',
    component: Detail,
    meta: {
      title: '论文详情'
    }
  },
  {
    path: '/analyze',
    name: 'analyze',
    component: Analyze,
    meta: {
      title: '论文数据分析'
    }
  },
  {
    path: '/edit',
    name: 'edit',
    component: Edit,
    meta: {
      title: '论文信息编辑'
    }
  },
  {
    path: '/related',
    name: 'related',
    component: Related,
    meta: {
      title: '相关论文'
    }
  },
]

const router = new VueRouter({
  routes
})

export default router
