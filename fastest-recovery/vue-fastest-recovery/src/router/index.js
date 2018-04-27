import Vue from 'vue'
import Router from 'vue-router'
import Homepage from '@/components/homepage'
import Profile from '@/components/homepage'
import PostsManager from '@/components/PostsManager'
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
      path: '/profile',
      component: Profile,
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
