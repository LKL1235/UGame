<template>
  <a style="color: #9099A1;position: relative;left: 25vw">{{route.query.gameName!=""?route.query.gameName:"所有产品"}}</a>
  <SearchList :games="game"></SearchList>
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {defineAsyncComponent, onMounted, ref} from "vue";
import axios from "axios";

const route=useRoute()
const game=ref([])
const SearchList=ref()

onMounted(()=>{
  axios.get("/game/search/?gameName="+route.query.gameName+"&page=1").then((respon)=>{
    game.value=respon.data.data
    SearchList.value=defineAsyncComponent(() =>
        import("@/components/Game/SearchList.vue")
    )
  }).catch(error=>{console.log(error)})
})
</script>

<style scoped>

</style>
