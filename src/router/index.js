import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/HelloWorld'
import Login from '@/components/Login'
import Register from '@/components/Register'
import ForgetPassword from '@/components/ForgetPassword.vue'
import UpdatePassword from '@/components/UpdatePassword.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
    //   chidren:[
    //     {
    //         name: 'Login',
    //         path: 'login',
    //         component: Login
    //     },
    //     {
    //         name: 'Register',
    //         path: 'register',
    //         component: Register
    //     },
    // ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path:'/register',
      name:'Register',
      component:Register,
    },
    {
      path:'/forgetPassword',
      name:'ForgetPassword',
      component:ForgetPassword,
    },
    {
      path:'/updatePassword',
      name:'UpdatePassword',
      component:UpdatePassword,
    }
  ]
})
