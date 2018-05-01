import Vue from 'vue'
import Router from 'vue-router'
import Homepage from '@/components/homepage'
import PostsManager from '@/components/PostsManager'
import Employees from '@/components/admin/employees'
import Departments from '@/components/admin/departments'
import Auth from '@okta/okta-vue'

Vue.use(Router)

Vue.use(Auth, {
  issuer: 'https://dev-487888.oktapreview.com/oauth2/default',
  client_id: '0oaethn5zrHz7JNpw0h7',
  redirect_uri: 'http://localhost:8080/implicit/callback',
  scope: 'openid profile email'
})

var router = new Router({
  routes: [
    {
      path: '/',
      name: 'homepage',
      component: Homepage
    },
    {
      path: '/implicit/callback',
      component: Auth.handleCallback()
    },
    {
      path: '/employees',
      component: Employees,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/departments',
      component: Departments,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/posts-manager',
      name: 'PostsManager',
      component: PostsManager,
      meta: {
        requiresAuth: true
      }
    }
  ],
  mode: 'history'
})

router.beforeEach(Vue.prototype.$auth.authRedirectGuard())

export default router
