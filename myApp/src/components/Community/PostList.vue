<template>
  <a v-if="route.query.boardName" style="position: relative;left: 25vw;color: #FFFFFF;font-size: 24px">{{"板块主题： "+route.query.boardName}}</a>
  <el-button @click="router.push({name:'addPost',query:{boardId:route.query.boardId,boardName:route.query.boardName}})" v-if="route.query.boardId && route.query.boardName" type="primary" style="position: absolute;left: 68%;margin-top: 10px">发表帖子</el-button>
  <br>
  <div class="main">
    <div class="posts" v-for="(post,index) in postNum">
      <div class="post" >

          <a style="color: #FFFFFF;font-size: 28px" @click="router.push({name:'postInfo',query:{postId:posts[index].postId}})">
            {{posts[index].postTitle.length>15?posts[index].postTitle.substring(0,15)+"...":posts[index].postTitle}}
          </a>
          <div style="text-align: right">
            <a style="color: #FFFFFF;font-size: 11px;" @click="router.push({path:'/community/postList',query:{boardId:posts[index].boardId,boardName:posts[index].boardName}})">
              {{"来自："+posts[index].boardName+" 最后回复时间: "+ (posts[index]?.lastReplyTime==undefined?"无":posts[index]?.lastReplyTime?.substring(0,19))}}
            </a>
          </div>
          <a style="color: #FFFFFF;font-size: 18px;float: none" @click="router.push({name:'postInfo',query:{postId:posts[index].postId}})">
            {{posts[index].contents.length>40?posts[index].contents.substring(0,40)+"...":posts[index].contents}}
          </a>

      </div>
    </div>

  </div>
  <br>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import {useRoute, useRouter} from "vue-router";


const router = useRouter()
const route = useRoute()
const postNum = ref(0)
const posts = ref()
const pageIndex = ref(1)



onMounted(()=>{

    axios.get("/getPosts", {params: {name: route.query.postName,boardId:route.query.boardId?route.query.boardId:"",page:pageIndex.value}}).then((respon) => {
      posts.value = respon.data.data
      postNum.value = posts.value.length
    }).catch(error => {
      console.log(error)
    })
})
</script>


<style scoped>
.main{
  margin-top: 30px;
}
.posts{
  margin-left: 25%;
  margin-top: 10px;
  width: 48vw;
}
.post{
  overflow: hidden;
  text-overflow:ellipsis;
  border-style: solid;
  border-color: #26384f;
  border-width: 1px;
  background-color: #1F2E41;
  box-shadow: 0 0 5px #000;
  padding: 30px 100px 30px 100px;
}
.post:hover{
  cursor: pointer;
}
</style>
