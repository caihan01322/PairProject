import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 引入组件
const home = () => import('../components/home.vue')
const hotpage = () => import('../components/hotpage.vue')
const insertpage = () => import('../components/insertpage.vue')
const listpage = () => import('../components/home_detail/listpage.vue')
const detailpage = () => import('../components/home_detail/detailpage.vue')
const editpage = () => import('../components/home_detail/editpage.vue')

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
                },
                {
                    path: 'detailpage/:isbn',
                    component: detailpage,
                    name: 'detailpage',
                },
                {
                    path: 'editpage/:isbn',
                    component: editpage,
                    name: 'editpage',
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