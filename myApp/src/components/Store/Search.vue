<template>
  <a style="color: #9099A1;position: relative;left: 25vw">{{route.query.gameName!=""?route.query.gameName:"所有产品"}}</a>
  <SearchList :games="game"></SearchList>
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

const route=useRoute()
const game=ref([])
const SearchList=ref()

const currentPage=ref(1)
const total = ref(21)
const handleCurrentChange = (page:number) => {
  currentPage.value=page
  axios.get("/game/search/?gameName="+route.query.gameName+"&page="+currentPage.value).then((respon)=>{
    game.value=respon.data.data
    SearchList.value=defineAsyncComponent(() =>
        import("@/components/Game/SearchList.vue")
    )
  }).catch(error=>{console.log(error)})
}


onMounted(()=>{
  axios.get("/game/search/?gameName="+route.query.gameName+"&page="+currentPage.value).then((respon)=>{
    game.value=respon.data.data
    SearchList.value=defineAsyncComponent(() =>
        import("@/components/Game/SearchList.vue")
    )
  }).catch(error=>{console.log(error)})
  axios.get(`/game/total?gameName=${route.query.gameName}`).then((respon)=>{
    total.value=respon.data.data
  }).catch(error=>{console.log(error)})
})
</script>

<style scoped>

</style>
