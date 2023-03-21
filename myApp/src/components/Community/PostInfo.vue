<template>
  <div class="main">
    <div style="color: #FFFFFF;height: 100px;font-size: 20px">
      <h2>{{postInfo?.postTitle}}</h2>

      <a>{{postInfo?.contents}}</a>
      <div style="text-align: right">
        <a class="pointer" @click="router.push({name:'postList',query:{boardName:postInfo.boardName,boardId:postInfo.boardId}})">
          {{"来自："+postInfo.userName+" 板块："+postInfo?.boardName+" 发布时间："+postInfo?.createdTime?.substring(0,19)}}
        </a>
      </div>

    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import {useRoute,useRouter} from "vue-router";

const route = useRoute()
const router = useRouter()
const postInfo = ref()
const replies = ref([])


onMounted(()=>{
  axios.get("/getPostInfo",{params:{postId:route.query.postId}}).then((respon)=>{
    postInfo.value = respon.data.data
    console.log(postInfo.value.postTitle)
  }).catch(error=>{console.log(error)})

  axios.get("/getPostReply").then((respon)=>{
    replies.value = respon.data.data
  }).catch(error=>{console.log(error)})
})
</script>

<style scoped>
.main{
  margin-left: 25vw;
  max-width: 48vw;
}
.pointer:hover{
  cursor: pointer;
}
</style>
