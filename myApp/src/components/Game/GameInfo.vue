<template>
  <div>
    <img alt="sss" :src="pic" style="width: 30vw;height: 30vh;float: left">
    <div>
      <a>{{game?.gameName}}</a>
      <a>{{game?.introduce}}</a>
      <a>{{game?.tags}}</a>
    </div>
  </div>

  <div style="float: none;">
    <div>
      <a>
        {{game?.price}}
      </a>
      <button >立即购买</button>
    </div>

    <div>
      <a>
        {{game?.about}}
      </a>
    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted,ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import axios from "axios";


const route=useRoute()
const game=ref()
const pic=ref()

onMounted(()=>{
  axios.get("/game/info/"+route.params.gameId).then((respon)=>{
    game.value=respon.data.data
    pic.value=game.value.imgs[0]
  }).catch(error=>{console.log(error)})
})
</script>

<style scoped>

</style>
