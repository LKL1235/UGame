<template>
  <div style="text-align: center;width: 100vw;overflow: hidden">
    <h1 style="color: #FFFFFF">个人中心</h1>
    <h2 style="color: #FFFFFF">上传头像</h2>
    <n-upload
        action="http://127.0.0.1:9000/avatarUpload"
        :default-file-list="fileList"
        list-type="image-card"
        :data="{'userName':store.user.name}"
        style="margin-left: 47.5vw"
    >
      点击上传
    </n-upload>
    <h2 style="color: #FFFFFF">用户名</h2>
    <h3 style="color: #FFFFFF">{{store.user.name}}</h3>

    <n-button @click="gotoDeveloper">
      {{isDeveloper?"开发者中心":"成为开发者"}}
    </n-button>
  </div>

</template>

<script setup lang="ts">
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {useUserStore} from "@/stores/User";
import axios from "axios";

const router=useRouter()
const store = useUserStore()

const fileList = ref([])

const isDeveloper = ref(false)

const gotoDeveloper = () => {
  if(isDeveloper.value) {
    router.push({name: 'developer'});
  }else{
    router.push({name:'developerPay'})
  }
}


onMounted(()=>{
  axios.get(`/user/isDeveloper?name=${store.$state.user.name}`).then((respon)=>{
    if(respon.data.code==200){
      isDeveloper.value = true
    }
  }).catch(error=>{console.log(error)})
})
</script>

<style scoped>

</style>
