import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import MainView from '@/views/MainView.vue'
import PostView from '@/views/PostView.vue'
import PersonView from '@/views/PersonView.vue'
import CollenView from '@/views/CollecView.vue'
import SignView from '@/views/SignView.vue'
import PostDetailView from '@/views/PostDetailView.vue'

const routes = [
  {
    path: '/',
    redirect: '/main',
  },
  {
    path: '/main',
    name: 'main',
    redirect: '/home',
    component: MainView,
    children:[
      {
        path: '/home',
        name: 'home',
        component: HomeView
      },
      {
        path: '/post',
        name: 'post',
        component: PostView
      },
      {
        path: '/collect',
        name: 'collect',
        component: CollenView
      },
      {
        path: '/person',
        name: 'person',
        component: PersonView
      },
      {
        path: '/detail',
        name: 'detail',
        component: PostDetailView
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/sign',
    name: 'sign',
    component: SignView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to,from,next) => {
  //已登录
  if (localStorage.getItem("user") != null) {
    if(to.name == 'login') {
      next('/');
    }else {
      next();
    }
  }else {
    next();
  }
})
export default router
