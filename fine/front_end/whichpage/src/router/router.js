import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

// 引入组件
// const home = () => import('../components/Home/Home')

const router = new VueRouter({
    mode: 'history',
    base: '',
    routes: [
        // {
        //     path: '/',
        //     component: home,
        //     name: 'home',
        // }
    ]
})

export default router