<template>

  <GameList :games="game"></GameList>
  <el-pagination
      v-model:current-page="currentPage"
      :page-size="20"
      layout="total, prev, pager, next"
      :total="total"
      @current-change="handleCurrentChange"
      style="margin-left: 40vw"
  />
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {defineAsyncComponent, onMounted, ref} from "vue";
import axios from "axios";
import {useUserStore} from "@/stores/User";

const User = useUserStore()
const route=useRoute()
const game=ref([])
const GameList=ref()

const currentPage=ref(1)
const total = ref(21)
const handleCurrentChange = (page:number) => {
  currentPage.value=page
  axios.get(`/game/repository?userName=${User.$state.user.name}&page=${currentPage.value}`).then((respon)=>{
    game.value=respon.data.data
    GameList.value=defineAsyncComponent(() =>
        import("@/components/Developer/GameList.vue")
    )
  }).catch(error=>{console.log(error)})
}


onMounted(()=>{
  axios.get(`/game/repository?userName=${User.$state.user.name}&page=${currentPage.value}`).then((respon)=>{
    game.value=respon.data.data
    GameList.value=defineAsyncComponent(() =>
        import("@/components/Developer/GameList.vue")
    )
  }).catch(error=>{console.log(error)})
  axios.get(`/game/repositoryTotal?userName=${User.$state.user.name}`).then((respon)=>{
    total.value=respon.data.data
  }).catch(error=>{console.log(error)})
})
</script>

<style scoped>

</style>
