<template>
  <div class="main">

    <div class="form">
      <a style="position: relative;left: 42%">发布游戏</a>
      <n-form
        ref="formRef"
        :model="formValue"
        :rules="rules"
        style="margin-left: 5%"
      >
        <n-form-item label="游戏名称" path="gameName">
          <n-input v-model:value="formValue.gameName" round placeholder="输入游戏名称" autosize style="min-width: 30%" />
        </n-form-item>
        <n-form-item label="价格" path="price">
          <n-input v-model:value="formValue.price" round placeholder="输入价格（以元为单位）" autosize style="min-width: 30%" >
            <template #suffix>
              元
            </template>
          </n-input>
        </n-form-item>
        <n-form-item label="标签" path="tags">
          <n-input v-model:value="formValue.tags"  round placeholder="请输入游戏标签方便我们分类" autosize style="min-width: 30%" />
        </n-form-item>
        <n-form-item label="游戏简介" path="introduce">
          <n-input v-model:value="formValue.introduce" round type="textarea" placeholder="请输入游戏简介" style="width: 80%"/>
        </n-form-item>
        <n-form-item label="关于这款游戏" path="about">
          <n-input v-model:value="formValue.about" round type="textarea" placeholder="关于这款游戏（内容、玩法）" style="width: 80%" />
        </n-form-item>
      </n-form>
      <n-button type="primary" style="margin-left:42%;width: 150px;height: 50px" @click="upload">
        发布
      </n-button>
    </div>

    <n-upload
        ref="uploadRef"
        action="http://127.0.0.1:9000/ImgUpload"
        :default-upload="false"
        :data="uploadData"
        multiple
        v-model:file-list="fileList"
        list-type="image-card"
        @change="handleUploadChange"
        @update:file-list="handleFileListChange"
        style="position: relative;left: 48vw;top: 13vh;height: 220px"
    />

  </div>
</template>

<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";
import { useMessage } from 'naive-ui'
import {useUserStore} from "@/stores/User";
import {useRouter} from "vue-router";

const router=useRouter()
const userInfo=useUserStore()
const message = useMessage()
const uploadRef=ref()
const uploadData=ref({name:""})

const formRef=ref()
const formValue=ref({
  name:'',
  gameName:'',
  price:'',
  tags:'',
  introduce:'',
  about:'',
})
const rules={
  gameName:[{
    required:true,
    message:'请输入游戏名称',
    trigger:["input","blur"]
  }],
  price:[{
    required:true,
    message:'请输入价格',
    trigger:["input","blur"]
  }],
  introduce:[{
    required:true,
    message:'请输入游戏简介',
    trigger:["input","blur"]
  }],
  about:[{
    required:true,
    message:'请输入关于这款游戏（玩法、内容）',
    trigger:["input","blur"]
  }],
}

const fileList=ref([])

const handleUploadChange=(data: { fileList })=> {
  fileList.value = data.fileList
}

const handleFileListChange = () => {
  if (fileList.value.length>5){
    fileList.value.pop()
    message.error('最多上传5张')
  }
}

const upload = () => {
  if(userInfo.$state.user.isLogin) {
    formValue.value.name=userInfo.$state.user.name
    axios.post("/upload",formValue.value).then((respon)=>{
      if(respon.data.code===200){
        uploadData.value.name=formValue.value.gameName
        uploadRef.value?.submit()
        router.push({name:"developer"})
      }else{
        message.error("发布失败")
      }
    }).catch(error=>console.log(error))
  }
}
</script>

<style scoped>
.main{
  display: grid;
  grid-template-columns: 3fr 7fr 3fr;
  grid-template-areas:
    ". main .";
  grid-gap: 5px;
  grid-auto-rows: 750px;
}
.form{
  grid-area: main;
  margin-top: 5%;
  background-color: #181A21;
  border-radius:2em;
  text-align: left;
  color: #b8b6b4;
  font-size: 36px;
}
</style>
