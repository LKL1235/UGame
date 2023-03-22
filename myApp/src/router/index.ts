import { createRouter, createWebHistory } from 'vue-router'
import GlobalView from "@/views/GlobalView.vue"
import Store from "@/views/StoreView.vue"
import StoreMain from "@/components/Store/StoreMain.vue"
import Login from "@/components/User/Login.vue"
import Chat from "@/components/Chat/Chat.vue"
import UserInfo from "@/components/User/UserInfo.vue"
import Community from "@/components/Community/Community.vue"
import Developer from "@/views/DeveloperView.vue"
import GameUpLoad from "@/components/Developer/GameUpLoad.vue"
import Repository from "@/components/Developer/Repository.vue"
import DeveloperPay from "@/components/Pay/DeveloperPay.vue"
import Search from "@/components/Store/Search.vue"
import SearchList from "@/components/Game/SearchList.vue"
import GameInfo from "@/components/Game/GameInfo.vue"
import PostList from "@/components/Community/PostList.vue"
import AddBoard from "@/components/Community/AddBoard.vue";
import AddPost from "@/components/Community/AddPost.vue";
import PostInfo from "@/components/Community/PostInfo.vue";


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
            {
              path:'/store/search',
              name:'search',
              component:Search,
            },
            {
              path:'/store/searchList',
              name:'searchList',
              component:SearchList,
            },
            {
              path:'/store/:gameId',
              name:'gameInfo',
              component:GameInfo,
              props:true
            }
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
          children:[
            {
              path:'postList',
              name:'postList',
              component: PostList,
            },
            {
              path:'boardList',
              name:'boardList',
              component: () => import('@/components/Community/BoardList.vue')
            },
            {
              path:'addBoard',
              name:'addBoard',
              component:AddBoard
            },
            {
              path:'addPost',
              name:'addPost',
              component:AddPost
            },
            {
              path:'postInfo',
              name:'postInfo',
              component: PostInfo
            }
          ]
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
        {
          path:'/gameUpLoad',
          name:'gameUpLoad',
          component:GameUpLoad,
        },
        {
          path:'/repository',
          name:'repository',
          component:Repository,
        },
        {
          path:'/pay/developerPay',
          name:'developerPay',
          component:DeveloperPay,
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
