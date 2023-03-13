<template>
  <div class="main">
    <div class="posts" v-for="(post,index) in postNum">
      <div>
        {{posts[index].postTitle}}
      </div>
    </div>

  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";

const postNum = ref(0)
const boardId = ref(1)
const posts = ref()

onMounted(()=>{
  axios.get("/getPosts", {params: {boardId:boardId.value}}).then((respon)=>{
    posts.value = respon.data.data
    postNum.value = posts.value.length
  }).catch(error=>{console.log(error)})
})
</script>


<style scoped>
.main{

}
.posts{
  position: relative;
  left: 25%;
}

</style>
