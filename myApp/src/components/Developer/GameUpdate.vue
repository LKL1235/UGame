<template>
  <h2 style="left: 25vw;color: #FFFFFF">{{"游戏ID："+route.query.gameId}}</h2>

  <el-upload
      ref="upload"
      class="upload-demo"
      action="http://127.0.0.1:9000/gameUpload"
      :limit="1"
      :on-exceed="handleExceed"
      :auto-upload="false"
      :data="{'gameId':route.query.gameId}"
      style="position: absolute;left: 40vw;top: 35vh"
  >
    <template #trigger>
      <el-button type="primary">选择文件</el-button>
    </template>
    <el-button class="ml-3" type="success" @click="submitUpload">
      上传
    </el-button>
    <template #tip>
      <div class="el-upload__tip text-red">
        limit 1 file, new file will cover the old file
      </div>
    </template>
  </el-upload>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { genFileId } from 'element-plus'
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'
import {useUserStore} from "@/stores/User";
import {useRoute} from "vue-router";

const User = useUserStore()
const route = useRoute()


const upload = ref<UploadInstance>()

const handleExceed: UploadProps['onExceed'] = (files) => {
  upload.value!.clearFiles()
  const file = files[0] as UploadRawFile
  file.uid = genFileId()
  upload.value!.handleStart(file)
}

const submitUpload = () => {
  upload.value!.submit()
}
</script>

<style scoped>

</style>
