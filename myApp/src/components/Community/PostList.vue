<template>
  <div class="main">
    <div class="posts" v-for="(post,index) in postNum">
      <div >
        <a style="color: #FFFFFF;font-size: 28px">
          {{posts[index].postTitle.length>15?posts[index].postTitle.substring(0,15)+"...":posts[index].postTitle}}
        </a>
        <br>
        <a style="color: #FFFFFF;font-size: 18px;float: none">
          {{posts[index].contents.length>40?posts[index].contents.substring(0,40)+"...":posts[index].contents}}
        </a>
      </div>
    </div>

  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";

const postNum = ref(0)
const boardId = ref(1)
const posts = ref()



const getPosts = (board:number) => {
  axios.get("/getPosts", {params: {boardId:board}}).then((respon)=>{
    posts.value = respon.data.data
    postNum.value = posts.value.length
  }).catch(error=>{console.log(error)})

}

onMounted(()=>{
  axios.get("/getPosts").then((respon)=>{
    posts.value = respon.data.data
    postNum.value = posts.value.length
  }).catch(error=>{console.log(error)})

})
</script>


<style scoped>
.main{

}
.posts{
  margin-left: 25%;
  margin-top: 10px;
}

</style>
