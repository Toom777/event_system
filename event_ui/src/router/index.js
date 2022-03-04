import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    /*登录页*/
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    /*注册页面*/
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register')
  },
  {
    /*布局页*/
    path: '/index',
    name: 'Index',
    component: () => import('../views/home/LayoutIndex'),
    redirect: 'home',
    children: [
      {
        path: '/home',
        name: 'Home',
        component:() => import('../views/home/index/home')
      },
      {
        path: '/user',
        name: 'User',
        component:() => import('../views/home/user/user')
      },
      {
        path: '/community',
        name: 'Community',
        component:() => import('../views/home/community/Community')
      },
      {
        path: '/activity',
        name: 'Activity,',
        component:() => import('../views/home/activity/index'),
        children: [
          {
            path: '/pageone',
            name: 'Pageone',
            component:() => import('../views/home/activity/PageOne')
          },
          {
            path: '/pagetwo',
            name: 'Pagetwo',
            component:() => import('../views/home/activity/PageTwo')
          },
          {
            path: '/pagethree',
            name: 'Pagethree',
            component:() => import('../views/home/activity/PageThree')
          }
        ]
      }

    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
