import Vue from 'vue'
import VueRouter from 'vue-router'
import Search from "@/views/Search";
import Home from "@/views/Home";
import ManageAccount from "@/views/ManageAccount";

Vue.use(VueRouter)

const routes = [
  {
   path: '/',
   redirect:'/search'
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/manageAccount',
    name: 'ManageAccount',
    component: ManageAccount
  }
]

const router = new VueRouter({
  routes
})

export default router
