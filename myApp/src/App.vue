
<template>
  <!--使用Message-->
  <n-message-provider>
    <!--  使用暗色主题-->
  <n-config-provider :theme="darkTheme" :theme-overrides="themeOverrides">
    <RouterView/>
  </n-config-provider>
</n-message-provider>
</template>

<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import {darkTheme} from 'naive-ui'
import { NConfigProvider} from 'naive-ui'
import {onMounted, ref} from "vue";
import axios from "axios";
import {useUserStore} from "@/stores/User";
import GlobalView from "@/views/GlobalView.vue"
// import { defineComponent } from 'vue'
const store=useUserStore()
//主题变量
const themeOverrides = {
  common: {
    primaryColor: '#4C41CBFF'
  },
  Input: {
    caretColor: '#4C41CBFF',
    borderFocus: "1px solid #4C41CBFF",
    colorFocus: "rgba(57, 45, 96, 0.1)",
    boxShadowFocus: "0 0 8px 0 rgba(174,15,18,0.1)",
    borderHover: "1px solid #4C41CBFF",
  },
  // ...
}
//判断是否已登录
onMounted(()=>{
  axios.post("/isLogin",).then((respon)=>{
      if(respon.data.code===200){
        store.$state.user={...respon.data.data}
        store.$state.user.isLogin=true
      }
  }).catch(error=>console.log(error))
})


</script>

<style >

</style>
