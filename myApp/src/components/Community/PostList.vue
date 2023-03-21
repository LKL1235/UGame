<template>
  <div class="main">
    <div class="posts" v-for="(post,index) in postNum">
      <div class="post" >

          <a style="color: #FFFFFF;font-size: 28px" @click="router.push({name:'postInfo',query:{postId:posts[index].postId}})">
            {{posts[index].postTitle.length>15?posts[index].postTitle.substring(0,15)+"...":posts[index].postTitle}}
          </a>
          <div style="text-align: right">
            <a style="color: #FFFFFF;font-size: 11px;" @click="router.push({path:'/community/postList',query:{boardId:posts[index].boardId,boardName:posts[index].boardName}})">
              {{"来自："+posts[index].boardName+" 最后回复时间: "+ (posts[index]?.lastReplyTime=="null"?"无":posts[index]?.lastReplyTime?.substring(0,19))}}
            </a>
          </div>
          <a style="color: #FFFFFF;font-size: 18px;float: none" @click="router.push({name:'postInfo',query:{postId:posts[index].postId}})">
            {{posts[index].contents.length>40?posts[index].contents.substring(0,40)+"...":posts[index].contents}}
          </a>

      </div>
    </div>

  </div>
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
