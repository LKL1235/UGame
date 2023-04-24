<template>
  <div style="position: absolute;left: 25%">
    <a style="color: #FFFFFF;font-size: 24px">发表帖子</a>
    <br>
    <a style="color: #FFFFFF;font-size: 24px" >{{"板块： "+route.query.boardName}} </a>

    <el-form :model="formData"  label-width="120px" style="width: 50vw;max-height: 100%">

      <el-form-item label="Post Title">
        <el-input v-model="formData.postTitle" placeholder="请输入标题" maxlength="50" show-word-limit style="width: 70%"></el-input>
      </el-form-item>

      <el-form-item label="Contents" >
        <el-input type="textarea"  v-model="formData.contents" placeholder="请输入内容" maxlength="255" show-word-limit ></el-input>
      </el-form-item>

    </el-form>

    <el-button type="primary" @click="addPost" style="position: absolute;left: 90%">
      发表帖子
    </el-button>
    <br>
  </div>

</template>

<script setup lang="ts">
import {onMounted, ref,reactive} from "vue";
import {useRoute,useRouter} from "vue-router";
import dayjs from "dayjs";
import axios from "axios";
import {useUserStore} from "@/stores/User";
import {ElMessage} from "element-plus";

const route = useRoute()
const router = useRouter()
const store = useUserStore()

const formData = reactive({
  postTitle:"",
  contents:"",
  userName:"",
  createdTime:"",
  lastReplyTime:"",
  boardId:"",
})

const addPost = () => {
  formData.boardId = route.query.boardId
  formData.userName = store.$state.user.name
  formData.createdTime = dayjs().toString()
  formData.lastReplyTime = dayjs().toString()
  axios.post("/addPost",formData).then((respon)=>{
    if(respon.data.code==200){
      ElMessage.success("发布成功")
    }else{
      ElMessage.error("发布失败")
    }
    router.push({name:'postList'})
  }).catch(error=>console.log(error))
}

</script>

<style scoped>

</style>
