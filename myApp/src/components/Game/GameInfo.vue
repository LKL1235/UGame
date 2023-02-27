<template>
  <div>
    <img alt="sss" :src="game.imgs[0]" style="width: 50vw;height: 30vh">
  </div>
</template>

<script setup lang="ts">
import {onMounted,ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import axios from "axios";


const route=useRoute()
const game=ref()

onMounted(()=>{
  axios.get("/game/info/"+route.params.gameId).then((respon)=>{
    game.value=respon.data.data
    console.log(respon.data.data)
  }).catch(error=>{console.log(error)})
})
</script>

<style scoped>

</style>
