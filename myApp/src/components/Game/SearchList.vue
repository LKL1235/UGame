<template>
  <div  v-for="game in props.games" @click="info(game.gameId)">
    <img :src="game.img[0]" alt="img" style="width: 125px;height: 50px;margin-left: 25vw">
    <a>{{game.gameName}}</a>
  </div>
</template>

<script setup lang="ts">
import {onMounted} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

const router=useRouter()

const props = defineProps<{
  games:object[]
}>()

const info=(gameId:number)=>{
  router.push({name:'gameInfo',params:{gameId}})
}

onMounted(()=>{

})
</script>

<style scoped>

</style>
