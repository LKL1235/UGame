<template>
  <div style="text-align: center">
    <h1 style="color: #FFFFFF">个人中心</h1>
    <n-upload
        action="http://127.0.0.1:9000/avatarUpload"
        :default-file-list="fileList"
        list-type="image-card"
        :data="{'userName':store.user.name}"
    >
      点击上传
    </n-upload>


    <n-button @click="gotoDeveloper">
      开发者中心
    </n-button>
  </div>

</template>

<script setup lang="ts">
import {useRouter} from "vue-router";
import {ref} from "vue";
import {useUserStore} from "@/stores/User";

const router=useRouter()
const store = useUserStore()

const fileList = ref([])


const gotoDeveloper = () => {
  router.push({name:'developer'});
}
</script>

<style scoped>

</style>
