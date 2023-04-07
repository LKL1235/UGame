<template>

  <LibraryList :games="game"></LibraryList>

</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {defineAsyncComponent, onMounted, ref} from "vue";
import axios from "axios";
import {useUserStore} from "@/stores/User";

const User = useUserStore()
const route=useRoute()
const game=ref([])
const LibraryList=ref()



onMounted(()=>{
  axios.get(`/getLibrary/${User.$state.user.name}`).then((respon)=>{
    game.value=respon.data.data
    LibraryList.value=defineAsyncComponent(() =>
        import("@/components/User/LibraryList.vue")
    )
  }).catch(error=>{console.log(error)})

})
</script>

<style scoped>

</style>
