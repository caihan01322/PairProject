import Vue from 'vue'
import Router from 'vue-router'
import ShowList from '@/components/ShowList'
import PaperDetail from '@/components/PaperDetail'
import BuzzWord from '@/components/BuzzWord'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name:'ShowList',
      component: ShowList
    },
    {
      path:'/PaperDetail',
      name:'PaperDetail',
      component: PaperDetail
    },
    {
      path:'/BuzzWord',
      name:'BuzzWord',
      component:BuzzWord
    }
  ]
})
