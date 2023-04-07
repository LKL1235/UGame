<template>
  <el-table :data="tableData" style="width: 55vw;margin-top: 5vh;margin-left: 10vw">
    <el-table-column prop="name" label="Name" width="220" />
    <el-table-column prop="email" label="email" width="220" />
    <el-table-column prop="regDate" label="regDate" width="220" />
    <el-table-column prop="latestDate" label="latestDate" width="220" />

    <el-table-column fixed="right" label="Operations" width="120">
      <template #default="scope">
        <el-button type="danger" size="small" @click.prevent="deleteFunc(tableData[scope.$index].name)">删除</el-button>
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

const deleteFunc = (name:string) => {
  axios.get(`/user/delete?name=${name}`).then((respon)=>{
    ElMessage.success("操作成功")
    getAll()
  }).catch(error=>{console.log(error)})
}

const getAll = ()=>{
  axios.get("/user/all").then((respon)=>{
    tableData.value = respon.data.data
  }).catch(error=>{console.log(error)})
}

onMounted(()=>{
  getAll()
})

</script>

<style scoped>

</style>
