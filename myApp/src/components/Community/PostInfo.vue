<template>
  <div class="main">
    <div class="postContent">
      <a style="font-size: 28px">{{postInfo?.postTitle}}</a>
      <br>
      <a>{{postInfo?.contents}}</a>
      <div style="text-align: right">
        <a class="pointer" style="font-size: 14px" @click="router.push({name:'postList',query:{boardName:postInfo.boardName,boardId:postInfo.boardId}})">
          {{"来自："+postInfo?.userName+" 板块："+postInfo?.boardName+" 发布时间："+postInfo?.createdTime?.substring(0,19)}}
        </a>
      </div>
    </div>
    <div class="replies" v-for="(item,index) in replies">
      <a>
        {{item?.contents}}
      </a>

      <div style="text-align: right">
        <a class="pointer" style="font-size: 14px">
          {{"来自："+item?.userName+" 发布时间："+item?.createdTime?.substring(0,19)}}
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

  axios.get("/getReplies",{params:{postId:route.query.postId}}).then((respon)=>{
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
.postContent{
  color: #FFFFFF;
  font-size: 20px;
  border-style: solid;
  border-color: #26384f;
  border-width: 1px;
  background-image: linear-gradient(to right bottom,#395174,#212F43);
  box-shadow: 0 0 5px #000;
  margin-top: 10px;
  margin-bottom: 10px;
  padding-left: 10px;
}
.replies{
  color: #FFFFFF;
  font-size: 20px;
  border-style: solid;
  border-color: #26384f;
  border-width: 1px;
  background-color: #1F2E41;
  box-shadow: 0 0 5px #000;
  padding: 30px 100px 30px 100px;
}
</style>
