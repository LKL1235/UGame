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
    <el-pagination v-model:current-page="currentPage" @current-change="handleCurrentChange" layout="prev, pager, next" :total="replies.length" :page-size="10" style="position: relative;left: 35%" />

    <div style="">
      <Toolbar
          style="border-bottom: 1px solid #ccc"
          :editor="editorRef"
          :defaultConfig="toolbarConfig"
          :mode="mode"
      />
      <Editor
          style="height: 200px; overflow-y: hidden;"
          v-model="valueHtml"
          :defaultConfig="editorConfig"
          :mode="mode"
          @onCreated="handleCreated"
      />
      <el-button type="primary" style="position: absolute;left: 68%" @click="addReply">发表回复</el-button>
    </div>
  </div>
  <br>
</template>

<script setup lang="ts">
import '@wangeditor/editor/dist/css/style.css'

import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
import axios from "axios";
import {useRoute,useRouter} from "vue-router";
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import {useUserStore} from "@/stores/User";
import dayjs from "dayjs";

const currentPage = ref(1)
const handleCurrentChange = ()=>{
  getReplies()
}

const editorRef = shallowRef()
const valueHtml = ref()

const toolbarConfig = {}
const editorConfig = { placeholder: '请输入内容...' }
const mode='default'


const route = useRoute()
const router = useRouter()
const store = useUserStore()

const postInfo = ref()
const replies = ref([])


const getReplies = ()=>{
  axios.get("/getReplies",{params:{postId:route.query.postId,page:currentPage.value}}).then((respon)=>{
    replies.value = respon.data.data
  }).catch(error=>{console.log(error)})
}

const addReply = ()=>{
  axios.post("/addReply",{contents:editorRef.value.getText(),userName:store.$state.user.name,postId:route.query.postId,createdTime:dayjs().toString()}).then((respon)=>{

  }).catch(error=>console.log(error))
}

onMounted(()=>{
  axios.get("/getPostInfo",{params:{postId:route.query.postId}}).then((respon)=>{
    postInfo.value = respon.data.data
  }).catch(error=>{console.log(error)})
  getReplies()

})

onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})
const handleCreated = (editor) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}
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
