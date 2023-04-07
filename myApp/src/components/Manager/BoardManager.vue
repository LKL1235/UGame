<template>
  <el-button style="position: absolute;left: 70vw;height: 30px" type="primary" size="small" @click.prevent="openAdd()">添加板块</el-button>
  <el-table :data="tableData" style="width: 55vw;margin-top: 5vh;margin-left: 10vw">
    <el-table-column prop="boardId" label="boardId" width="150" />
    <el-table-column prop="gameId" label="gameId" width="150" />
    <el-table-column prop="boardName" label="boardName" width="280" />
    <el-table-column prop="description" label="description" width="350" />


    <el-table-column fixed="right" label="Operations" width="120">
      <template #default="scope">
        <el-button type="danger" size="small" @click.prevent="deleteFunc(tableData[scope.$index].boardId)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>


  <el-dialog v-model="dialogFormVisible" title="Shipping address">
    <el-form :model="form">

      <el-form-item label="gameId" >
        <el-input v-model="form.gameId" autocomplete="off" />
      </el-form-item>

      <el-form-item label="boardName" >
        <el-input v-model="form.boardName" autocomplete="off" />
      </el-form-item>

      <el-form-item label="description" >
        <el-input type="textarea" v-model="form.description" autocomplete="off" />
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible = false;addFunc()">
          Confirm
        </el-button>
      </span>
    </template>

  </el-dialog>

</template>

<script setup lang="ts">
import {ref,onMounted,reactive} from "vue";
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


const dialogFormVisible = ref(false)

const openAdd = ()=>{
  dialogFormVisible.value = true
}

const addFunc=()=>{
    axios.post("/addBoard",form).then((respon)=>{
      if (respon.data.code == 200){
        ElMessage.success("添加成功")
        form.gameId = ""
        form.boardName = ""
        form.description = ""
        getAll()
      }else{
        ElMessage.error("添加失败")
      }
    }).catch(error=>console.log(error))
}

const form = reactive({
  gameId:'',
  boardName:'',
  description:''
})

const deleteFunc = (id:string) => {
  axios.get(`/board/delete?boardId=${id}`).then((respon)=>{
    ElMessage.success("操作成功")
    getAll()
  }).catch(error=>{console.log(error)})
}

const getAll = ()=>{
  axios.get("/getBoards").then((respon)=>{
    tableData.value = respon.data.data
  }).catch(error=>{console.log(error)})
}

onMounted(()=>{
  getAll()
})

</script>

<style scoped>

</style>
