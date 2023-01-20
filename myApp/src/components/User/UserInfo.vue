<template>
<h1>个人中心</h1>
  <n-button @click="gotoDeveloper">
    开发者中心
  </n-button>
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";

const router=useRouter()
const gotoDeveloper = () => {
  router.push({name:'developer'});
}
</script>

<style scoped>

</style>
