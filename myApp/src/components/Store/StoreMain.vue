<template>
  <div style="margin-bottom: 10px">
    <span style="color: #FFFFFF;margin-left: 25%;">精选和推荐</span>
  </div>
  <GameShow  :game="game"/>

</template>

<script setup lang="ts">
import {ref} from "vue";
import GameShow from "@/components/Game/GameShow.vue"


const game=ref([
  {
    gameName:'cs',
    img:[
      'http://139.9.32.27/src/ugame/images/%E5%88%B7%E5%8D%A1%E9%97%AF%E5%85%B3%E5%A4%A7%E4%BD%9C%E6%88%98/0.jpg',
      'http://139.9.32.27/src/ugame/images/%E5%88%B7%E5%8D%A1%E9%97%AF%E5%85%B3%E5%A4%A7%E4%BD%9C%E6%88%98/1.jpg',
      'http://139.9.32.27/src/ugame/images/%E5%88%B7%E5%8D%A1%E9%97%AF%E5%85%B3%E5%A4%A7%E4%BD%9C%E6%88%98/2.jpg',
      'http://139.9.32.27/src/ugame/images/%E5%88%B7%E5%8D%A1%E9%97%AF%E5%85%B3%E5%A4%A7%E4%BD%9C%E6%88%98/3.jpg',
      'http://139.9.32.27/src/ugame/images/%E5%88%B7%E5%8D%A1%E9%97%AF%E5%85%B3%E5%A4%A7%E4%BD%9C%E6%88%98/4.jpg'
    ],
    tags:['射击游戏','热销商品'],
    price:12.5,
},
  {
    gameName:'NBA 2k 22',
    img:[
      'http://139.9.32.27/src/ugame/images/nba_2k_22/0.jpg',
      'http://139.9.32.27/src/ugame/images/nba_2k_22/1.jpg',
      'http://139.9.32.27/src/ugame/images/nba_2k_22/2.jpg',
      'http://139.9.32.27/src/ugame/images/nba_2k_22/3.jpg',
      'http://139.9.32.27/src/ugame/images/nba_2k_22/4.jpg',
    ],
    tags:['热销商品','体育游戏'],
    price:89.0,
  },

])

</script>

<style scoped>

</style>
