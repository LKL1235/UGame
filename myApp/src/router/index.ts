import { createRouter, createWebHistory } from 'vue-router'
import GlobalView from "@/views/GlobalView.vue"
import Store from "@/views/StoreView.vue"
import StoreMain from "@/components/Store/StoreMain.vue"
import Login from "@/components/User/Login.vue"
import Chat from "@/components/Chat/Chat.vue"
import UserInfo from "@/components/User/UserInfo.vue"
import Community from "@/components/Community/Community.vue"
import Developer from "@/views/DeveloperView.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/global',
      name: 'global',
      component: GlobalView,
      children:[
        //  商店
        {
          path:'/store',
          name:'store',
          component:Store,
          children:[
            {
              path:'/store/main',
              name:'storeMain',
              component:StoreMain,
            },
          ]
        },
        //  登录
        {
          path:'/login',
          name:'login',
          component:Login,
        },
        //  个人资料
        {
          path:'/userInfo',
          name:'userInfo',
          component:UserInfo,
        },
        //  社区
        {
          path:'/community',
          name:'community',
          component:Community,
        },
        //  聊天
        {
          path:'/chat',
          name:'chat',
          component:Chat,
        },
        {
          path:'/developer',
          name:'developer',
          component:Developer,
        },
      ]
    },

    {
      path: "/",
      redirect: "/store/main",
    },

  ]
})

export default router
