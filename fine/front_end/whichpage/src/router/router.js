import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 引入组件
const home = () => import('../components/home.vue')
const listpage = () => import('../components/listpage.vue')
const hotpage = () => import('../components/hotpage.vue')
const insertpage = () => import('../components/insertpage.vue')

const router = new VueRouter({
    mode: 'history',
    base: '',
    routes: [
        {
            path: '/',
            redirect: { name: 'home' },
        },
        {
            path: '/home',
            component: home,
            name: 'home',
            redirect: { name: 'listpage' },
            children: [
                {
                    path: 'listpage',
                    component: listpage,
                    name: 'listpage',
                },
                {
                    path: 'insertpage',
                    component: insertpage,
                    name: 'insertpage',
                }
            ]
        },
        {
            path: '/hotpage',
            component: hotpage,
            name: 'hotpage',
        },
    ]
})

export default router