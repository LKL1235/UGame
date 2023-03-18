<template>
<!--  全局头-->
  <div id="globalHead">

<!--    logo-->
    <n-icon size="48" id="logo" class="pointerStyle">
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 600 600">
          <g>
            <title>Layer 1</title>
            <ellipse stroke-width="0" ry="300" rx="300" id="svg_3" cy="300" cx="300" stroke="#000" fill="#c5c3c0"/>
            <text transform="matrix(1.50923 0 0 1.63902 -119.735 -161.264)" stroke="null" font-weight="bold"  xml:space="preserve" text-anchor="start" font-family="Noto Sans JP" font-size="250" id="svg_6" y="381" x="95" stroke-width="0" fill="#171a21">UG</text>
          </g>
      </svg>
    </n-icon>
    <n-icon size="100" id="logoText" class="pointerStyle">
      <svg width="600" height="200" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 600 200">
        <g id="Layer_1">
          <title>Layer 1</title>
          <text transform="matrix(6.71318 0 0 6.16582 -22.8527 -16.3442)" stroke="#000" font-weight="bold" xml:space="preserve" text-anchor="start" font-family="Noto Sans JP" font-size="24" id="svg_2" y="28.5" x="4" stroke-width="0" fill="#b8b6b4">UGame</text>
        </g>
      </svg>
    </n-icon>
<!--    导航-->
  <!--  商店  -->

    <n-dropdown class="" trigger="hover" :options="storeOptions" @select="handleSelect">
      <a class="fontColor pointerStyle" style="position:absolute;left:660px;top:47px;" @click="router.push({name:'storeMain'})">商店</a>
    </n-dropdown>

    <!--  社区  -->
    <n-dropdown class="" trigger="hover" :options="communityOptions" @select="handleSelect">
      <a class="fontColor pointerStyle" style="  position:absolute;left:710px;top:47px;" @click="router.push({name:'postList'})">社区</a>
    </n-dropdown>

    <!--  个人中心  -->
    <n-dropdown class="" trigger="hover" :options="userOptions" @select="handleSelect">
      <a class="fontColor pointerStyle" style="  position:absolute;left:760px;top:47px;" @click="router.push({name:'userinfo'})">{{ nickName }}</a>
    </n-dropdown>

    <!--  聊天  -->
    <!--  距离左边基础为20px-->
      <a class="fontColor pointerStyle" :style="{position:'absolute',left:chatButtonLeft}" @click="()=>{router.push({name:'chat'})}" style="top: 47px">聊天</a>


<!--个人信息栏-->
    <div v-if="User.$state.user.isLogin" >

      <!--    通知图标-->
     <div id="mailBox">
        <n-icon size="20" color="#FFFFFF">
          <MailOutline class="pointerStyle" style="position:absolute;left: 3px;" />
        </n-icon>
      </div>
<!--名字-->
      <n-dropdown trigger="click" :options="topOptions" @select="handleSelect">
        <a class="fontColor pointerStyle" style="position: absolute;left: 1277px;top: 5px" >{{nickName}}
         <n-icon size="10" color="#FFFFFF">
           <CaretDown/>
         </n-icon>
        </a>
     </n-dropdown>
<!--    头像-->

<!--    :src="User.$state.user.isLogin?User.$state.user.avatar:'/src/assets/unLogin.png'"-->
      <n-avatar
        size="small"
        :src="User.$state.user.avatar!==null?User.$state.user.avatar:'/unLogin.png'"
        class="pointerStyle"
        style="top: 5px;border-style:outset;border-color: #52a2c2"
        :style="{position:'absolute',left:avatarLeft}"
      />
<!--    钱包-->
      <a class="fontColor pointerStyle" style="position: absolute;top: 23px" :style="{left:walletLeft}">
      ￥0.00{{}}
      </a>
    </div>

<!--未登录-->
    <div id="unLogin" v-if="!User.$state.user.isLogin" style="position: absolute;left: 70%;top: 5px;z-index: 5;" class="fontColor">
      <a class="fontColor pointerStyle" @click="gotoLogin">登录</a> |
      <a class="fontColor pointerStyle" @click="gotoLogin">注册</a>

    </div>

  </div>
  <router-view/>

</template>

<script setup lang="ts">
import {computed, onMounted, ref} from "vue";
import {Mail,MailOutline,CaretDown,Help} from "@vicons/ionicons5"
import { useUserStore} from "@/stores/User";
import {useRouter} from "vue-router"
import { storeToRefs } from 'pinia'
import axios from "axios";

const router=useRouter()

const User=useUserStore()
const {user}=storeToRefs(User)
const nickName=computed(()=>{
  if(User.$state.user.isLogin){
    if(User.$state.user.nickName!=null){
      return user.value.nickName
    }else{
      return user.value.name
    }
  }else{
    return ''
  }

})
//聊天按钮左侧距离
const chatButtonLeft: String=computed(()=>{
  return (770+10*nickName.value.length+'px')
})
//头像左侧
const avatarLeft:String=(1380+14.3*nickName.value.length)+'px'
//钱包左侧
const walletLeft:String=nickName.value.length>5?(1273+7*nickName.value.length)+'px':(1270)+'px'

//商店菜单
const storeOptions=[
  {
    label:"主页",
    key:"storeMain",
  },
]
//社区选单
const communityOptions=[
  {
    label:"主页",
    key:"postList"
  },
]
//用户选单
const userOptions=[
  {
    label:"个人资料",
    key:"userInfo"
  },
]
//头像旁边的选单
const topOptions=[
  {
    label:"查看个人资料",
    key:"userInfo"
  },
  {
    label: "注销",
    key:"logout"
  }
]
//选单触发
const handleSelect = (key:string) => {
  if(key!="logout") {
    router.push({name:key})
  }else{
    axios.get("/Logout").then((respon)=>{
      User.$state.user={
        isLogin:false,
        name:"",
        nickName:null,
        email:"",
        avatar:"",
        balance:0,
        regDate:"",
        latestDate:"",
      }
    }).catch(error=>{console.log(error)})
  }
}
const gotoLogin = () => {
  router.push("/login")
}

onMounted(()=>{

})

</script>

<style scoped>

/*全局头*/
#globalHead{
  margin: 0px;
  background: #171a21;
  /*width: 99vw;*/
  height: 104px;
}
/*logo*/
#logo{
  position:relative;
  left:470px;
  top:-20px;
}
#logoText{
  position:relative;
  left:485px;
  top:5px;
}
/*指针样式*/
.pointerStyle{
  cursor:pointer
}
/*字体*/
.fontColor{
  color:#b8b6b4;
}
a:hover
{
  color: #FFFFFF;
}
/*消息按钮*/
#mailBox{
  background: #262625;
  width: 26px;
  height: 23px;
  position:absolute;
  left:1243px;
  top:7px;
}
#mailBox:hover{
  background: #b8b6b4;
  width: 26px;
  height: 23px;
  position:absolute;
  left:1243px;
  top:7px;
}
/*未登录*/
#unLogin{
  width: 100px;
  height: 20px;
}
</style>
