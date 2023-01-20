<template>
  <a style="color: #b8b6b4;position: absolute;left: 200px;top: 500px">{{route.params.gameName}}</a>
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {onMounted} from "vue";

const route=useRoute()

onMounted(()=>{
  console.log(route.params.gameName)
})
</script>

<style scoped>

</style>
