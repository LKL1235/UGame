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
import GameUpdate from "@/components/Developer/GameUpdate.vue"
import Library from "@/components/User/Library.vue"
import ManagerView from "@/views/ManagerView.vue"
import UserManager from "@/components/Manager/UserManager.vue"
import GameManager from "@/components/Manager/GameManager.vue"
import PostManager from "@/components/Manager/PostManager.vue"
import Forget from "@/components/User/Forget.vue"
import ManagerLogin from "@/components/Manager/ManagerLogin.vue"
import BoardManager from "@/components/Manager/BoardManager.vue"

import {useUserStore} from "@/stores/User";

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
              path:'main',
              name:'storeMain',
              component:StoreMain,
            },
            {
              path:'search',
              name:'search',
              component:Search,
            },
            {
              path:'searchList',
              name:'searchList',
              component:SearchList,
            },
            {
              path:':gameId',
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
        {
          path:'/forget',
          name:'forget',
          component: Forget,
        },
        //  个人资料
        {
          path:'/userInfo',
          name:'userInfo',
          component:UserInfo,
        },
        {
          path:'/library',
          name:'library',
          component:Library,
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
          children:[
            {
              path: 'Message',
              name: 'Message',
              component: ()=>import('@/components/Chat/ChatMessage.vue')
            }
          ]
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
          path:'/gameUpdate',
          name:'gameUpdate',
          component:GameUpdate,
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
    {
      path:'/manager',
      name:'manager',
      component:ManagerView,
      children:[
        {
          path:'userManager',
          name:'userManager',
          component:UserManager,
        },
        {
          path:'gameManager',
          name:'gameManager',
          component:GameManager,
        },
        {
          path:'postManager',
          name:'postManager',
          component:PostManager,
        },
        {
          path:'boardManager',
          name:'boardManager',
          component:BoardManager,
        }
      ]
    },
    {
      path:'/managerLogin',
      name:'managerLogin',
      component: ManagerLogin
    }
  ]
})

router.beforeEach((to, from, next) => {
  let store = useUserStore();
  if(to.fullPath?.includes("manager") && to.name!="managerLogin"){
      if(store.$state.user.isRoot){
        next()
      }else{
        next({name:'managerLogin'})
      }
  }else{
    next()
  }
})

export default router
