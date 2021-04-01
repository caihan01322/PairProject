import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '@/components/LoginView'
import RegisterView from '@/components/RegisterView'
import SearchView from '@/components/SearchView'
import ViewArticleView from '@/components/ViewArticleView'
import ShowChartView from '@/components/ShowChartView'
import WordCloudView from '@/components/WordCloudView'
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: LoginView

  },
  {
    path: '/Login',
    component: LoginView
  },
  {
    path: '/Register',
    component: RegisterView
  },
  {
    path: '/Search',
    component: SearchView
  }, {
    path: '/ViewArticle',
    component: ViewArticleView
  }, {
    path: '/ChartView',
    component: ShowChartView
  },{
    path: '/WordCloudView',
    component: WordCloudView
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
