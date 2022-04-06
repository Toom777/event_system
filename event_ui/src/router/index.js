import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
/*  {
    path: '/404',
    component: () => import('@/views/404')
  },*/
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
    redirect: '/home',
    children: [
        /*主页面*/
      {
        path: '/home',
        name: 'Home',
        component:() => import('../views/home/index/home'),
        redirect: '/mainContent',
        children: [
          {
            /*首页内容*/
            path: '/mainContent',
            name: 'MainContent',
            component: () => import('../views/home/index/MainContent')
          },
          {
            /*活动内容*/
            path: '/activityContent',
            name: 'ActivityContent',
            component: () => import('../views/home/index/ActivityContent')
          },
          {
            /*公告内容*/
            path: '/noticeContent',
            name: 'NoticeContent',
            component: () => import('../views/home/index/NoticeContent')
          },
          {
            /*个人信息*/
            path: '/information',
            name: 'Information',
            component: () => import('../views/home/index/Information')
          },
          {
            /*资讯内容*/
            path: '/newsContent',
            name: 'NewsContent',
            component: () => import('../views/home/index/newsContent')
          },
          {
            /*我的活动*/
            path: '/userActivity',
            name: 'UserActivity',
            component: () => import('../views/home/index/userActivity')
          },
          {
            /*我的收藏*/
            path: '/userCollection',
            name: 'UserCollection',
            component: () => import('../views/home/index/userCollection')
          },
          {
            /*数据统计*/
            path: '/charts',
            name: 'Charts,',
            component: () => import('../views/home/echarts/index')
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
            name: 'ActivityList',
            component:() => import('../views/home/activity/list')
          },
          {
            path: '/confirmation',
            name: 'Confirmation',
            component: () => import('../views/home/activity/confirmation')
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
      },
        /*留言*/
      {
        path: '/message',
        name: 'Message',
        component:() => import('../views/home/message/index')
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
