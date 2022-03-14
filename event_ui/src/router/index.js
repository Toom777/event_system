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
        /*主页面*/
      {
        path: '/home',
        name: 'Home',
        component:() => import('../views/home/index/home'),
        children: [
          {
            /*首页内容*/
            path: '/main',
            name: 'main',
            component: () => import('../views/home/index/main')
          }
        ]
      },
      /*用户*/
      {
        path: '/user',
        name: 'User',
        component:() => import('../views/home/user/user')
      },
        /*社区*/
      {
        path: '/community',
        name: 'Community',
        component:() => import('../views/home/community/Community')
      },
        /*活动*/
      {
        path: '/activity',
        name: 'Activity,',
        component:() => import('../views/home/activity/index'),
        children: [
          {
            path: '/release',
            name: 'Release',
            component:() => import('../views/home/activity/release')
          },
          {
            path: '/list',
            name: 'List',
            component:() => import('../views/home/activity/list')
          }
        ]
      },
        /*内容*/
      {
        path: '/contents',
        name: 'Contents,',
        component:() => import('../views/home/contents/index'),
        children: [
          {
            path: '/carousel',
            name: 'Carousel',
            component:() => import('../views/home/contents/carousel')
          },
          {
            path: '/news',
            name: 'News',
            component:() => import('../views/home/contents/news')
          },
          {
            path: '/notice',
            name: 'Notice',
            component:() => import('../views/home/contents/notice')
          }
        ]
      }

    ]
  }
]

/*解决路由重复报错*/
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
