<template>
  <div style="margin-bottom: 10px">
    <span style="color: #FFFFFF;margin-left: 25%;">精选和推荐</span>
  </div>
  <GameShow  :game="game"/>
  <div style="margin-bottom: 10px;margin-top: 20px">
    <span style="color: #FFFFFF;margin-left: 25%;">特别推荐</span>
  </div>
  <GameRecommand :game="game"/>
</template>

<script setup lang="ts">
import {defineAsyncComponent, onMounted, ref} from "vue";
// import GameShow from "@/components/Game/GameShow.vue"
import axios from "axios";


const game=ref([

])
const GameShow =ref()

const GameRecommand=ref()
onMounted(()=>{
  axios.get("/game/show").then((respon)=>{
    game.value=respon.data.data
    GameShow.value=defineAsyncComponent(() =>
        import("@/components/Game/GameShow.vue")
    )
    GameRecommand.value=defineAsyncComponent(() =>
        import("@/components/Game/GameRecommand.vue")
    )
  }).catch(error=>{console.log(error)})
})
</script>

<style scoped>

</style>
