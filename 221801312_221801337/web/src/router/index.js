import { createRouter, createWebHistory } from 'vue-router'
// import Home from '../views/Home.vue'
import SearchPage from '../views/SearchPage.vue'
import HotAreas from '../views/HotAreas.vue'
import SearchDetail from '../views/SearchDetail.vue'
import HotWords from '@/components/HotWords.vue'
import Top10 from '@/components/Top10.vue'

const routes = [
  {
    path: '/',
    name: 'SearchPage',
    component:SearchPage,
    meta:{index:1,title: 'Paper Retrieval'},
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/HotAreas',
    name: 'HotAreas',
    component: HotAreas,
    meta:{index:1,title: '近年热词'},
    children:[
      {
        path:'/HotAreas',
        redirect:HotWords
      },
      {
        path:'/HotWords',
        component: HotWords
      },
      {
        path: '/Top10',
        component: Top10
      }
    ],
  },
  {
    path: '/SearchDetail',
    name: 'SearchDetail',
    component: SearchDetail,
    meta:{index:1,title: 'Paper Retrieval 搜索'},
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
