<template>
  <div class="main">
    {{"板块主题:"+route.query.boardName}}
    <div class="posts" v-for="(post,index) in postNum">
      <div class="post">

          <a style="color: #FFFFFF;font-size: 28px">
            {{posts[index]?.postTitle.length>15?posts[index]?.postTitle.substring(0,15)+"...":posts[index]?.postTitle}}
          </a>
          <div>
            <a style="color: #FFFFFF;font-size: 11px;">{{"来自："+posts[index]?.boardName}}</a>
          </div>
          <a style="color: #FFFFFF;font-size: 18px;float: none">
            {{posts[index]?.contents.length>40?posts[index]?.contents.substring(0,40)+"...":posts[index]?.contents}}
          </a>

      </div>
    </div>

  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";

const route =useRoute()
const postNum = ref(0)
const boardId = ref(1)
const posts = ref([])




onMounted(()=>{
    axios.get("/getPosts", {params: {boardId:route.query.boardId}}).then((respon)=>{
      posts.value = respon.data.data
      postNum.value = posts.value.length
    }).catch(error=>{console.log(error)})

})
</script>


<style scoped>
.main{
  color: #FFFFFF;
  font-size: 25px;
  text-align: center;
}
.posts{
  margin-left: 25%;
  margin-top: 10px;
  width: 48vw;
}
.post{
  overflow: hidden;
  text-overflow:ellipsis;
  background-color: #0D131B;
}
.post:hover{
  cursor: pointer;
}
</style>
