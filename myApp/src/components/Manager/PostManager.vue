<template>
  <el-table :data="tableData" style="width: 55vw;margin-top: 5vh;margin-left: 10vw">
    <el-table-column prop="postId" label="postId" width="80" />
    <el-table-column prop="postTitle" label="postTitle" width="280" />
    <el-table-column prop="contents" label="contents" width="350" />
    <el-table-column prop="userName" label="userName" width="120" />
    <el-table-column prop="boardId" label="boardId" width="80" />

    <el-table-column fixed="right" label="Operations" width="120">
      <template #default="scope">
        <el-button type="danger" size="small" @click.prevent="deleteFunc(tableData[scope.$index].postId)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

</template>

<script setup lang="ts">
import {ref,onMounted} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";

const tableData =ref([
      {
        avatar: "http://127.0.0.1:8889/hood1235.jpg",
        balance:0,
        email:"2510052266@qq.com",
        latestDate:"2023-04-03T10:59:28.000+00:00",
        name:"hood1235",
        nickName:null,
        regDate:"2022-11-30T10:16:24.000+00:00"
      },

    ]
)

const deleteFunc = (id:string) => {
  axios.get(`/post/delete?postId=${id}`).then((respon)=>{
    ElMessage.success("操作成功")
    getAll()
  }).catch(error=>{console.log(error)})
}

const getAll = ()=>{
  axios.get("/post/all").then((respon)=>{
    tableData.value = respon.data.data
  }).catch(error=>{console.log(error)})
}

onMounted(()=>{
  getAll()
})

</script>

<style scoped>

</style>
