// router/index.ts
import {
  createRouter,
  createWebHistory,
  type RouteRecordRaw,
  type NavigationGuardNext,
  type RouteLocationNormalized
} from 'vue-router'

// 扩展 Vue Router 的元信息类型，添加 requiresAuth 字段
declare module 'vue-router' {
  interface RouteMeta {
    // 是否需要登录权限，默认 false
    requiresAuth?: boolean
  }
}

// 路由规则配置（添加完整的 TypeScript 类型约束）
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/Login',
    meta: {requiresAuth: false} // 根路由重定向到登录页，无需权限
  },
  {
    path: '/Home',
    name: 'Home',
    component: () => import('@/views/Home/index.vue'),
    meta: {requiresAuth: true} // 需要登录才能访问
  },
  {
    path: '/Login',
    name: 'Login',
    component: () => import('@/views/Login/index.vue'),
    meta: {requiresAuth: false} // 登录页无需权限
  },
  {
    path: '/Register',
    name: 'Register',
    component: () => import('@/views/Register/index.vue'),
    meta: {requiresAuth: false} // 注册页无需权限
  },
  {
    path: '/Search',
    name: 'Search',
    component: () => import('@/views/Search/index.vue'),
    meta: {requiresAuth: true} // 需要登录才能访问
  },
  {
    path: '/Sort',
    name: 'Sort',
    component: () => import('@/views/Sort/index.vue'),
    meta: {requiresAuth: true} // 需要登录才能访问
  },
  {
    path: '/User',
    name: 'User',
    component: () => import('@/views/User/index.vue'),
    meta: {requiresAuth: true} // 需要登录才能访问
  },
]

// 创建路由实例
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

// 全局路由前置守卫（拦截器核心）
router.beforeEach((
  to: RouteLocationNormalized,
  from: RouteLocationNormalized,
  next: NavigationGuardNext
) => {
  // 1. 获取登录状态（从localStorage读取token，实际项目可替换为pinia/vuex）
  const token = localStorage.getItem('token') || 'will-token'
  const isLoggedIn = !!token

  // 2. 判断当前路由是否需要登录权限（默认需要，除非显式设置为false）
  const requiresAuth = to.meta.requiresAuth ?? true

  if (requiresAuth) {
    // 3. 需要登录：已登录则放行，未登录跳登录页（携带原路径）
    if (isLoggedIn) {
      next()
    } else {
      next({
        path: '/Login',
        query: {redirect: to.fullPath} // 记录原访问路径，登录后可返回
      })
    }
  } else {
    // 4. 无需登录：如果是登录/注册页且已登录，自动跳首页
    if (isLoggedIn && (to.path === '/Login' || to.path === '/Register')) {
      next('/Home')
    } else {
      next()
    }
  }
})

export default router
