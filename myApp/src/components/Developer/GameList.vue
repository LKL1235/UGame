<template>
  <!--  <div  v-for="game in props.games" @click="info(game.gameId)"  >-->
  <!--    <div><img :src="game.img[0]" alt="img" style="width: 125px;height: 50px;margin-left: 25vw"></div>-->
  <!--    <div >{{game.gameName}}</div>-->
  <!--    <div>{{game.price}}</div>-->
  <!--  </div>-->

  <div v-for="game in props.games" @click="info(game.gameId)"  >
    <div class="game-item"><img :src="game.img[0]" alt="img" style="width: 125px;height: 50px;margin-left: 25vw"></div>
    <div class="game-item" style="position: relative;top: -3vh;font-size: 18px;color: #FFFFFF">{{game.gameName}}</div>
    <div class="game-item" style="position: absolute;left: 65vw;color: #FFFFFF">{{"￥"+game.price}}</div>
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
  router.push({name:'gameUpdate',query:{'gameId':gameId}})
}

onMounted(()=>{

})
</script>

<style scoped>

.game-item {
  white-space: nowrap;
  display: inline-block;
  margin-right: 20px;
}
</style>
