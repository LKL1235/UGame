<template>
  <div class="main">

    <div class="boards" v-for="(board,index) in boardNum">
      <div class="board" @click="router.push({name:'postList',query:{boardId:boards[index]?.boardId,boardName:boards[index]?.boardName}})">
        <a style="color: #FFFFFF;font-size: 28px">
          {{boards[index]?.boardName.length>15?boards[index]?.boardName.substring(0,15)+"...":boards[index]?.boardName}}
        </a>
        <br>
        <a style="color: #FFFFFF;font-size: 18px;float: none">
          {{boards[index]?.description.length>40?boards[index]?.description.substring(0,40)+"...":boards[index]?.description}}
        </a>

      </div>
    </div>

  </div>
  <br>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import {useRoute,useRouter} from "vue-router";

const route = useRoute()
const router = useRouter()
const boards = ref()
const boardNum = ref()


onMounted(()=>{
  axios.get(("/getBoardsLike"),{params:{name:route.query.boardName}}).then((response=>{
    boards.value = response.data.data
    boardNum.value = boards.value.length
  })).catch(error=>console.log(error))
})
</script>

<style scoped>
.boards{
  margin-left: 25%;
  margin-top: 10px;
  width: 48vw;
}
.board{
  overflow: hidden;
  text-overflow:ellipsis;
  border-style: solid;
  border-color: #26384f;
  border-width: 1px;
  background-color: #1F2E41;
  box-shadow: 0 0 5px #000;
  padding: 30px 100px 30px 100px;
}
.board:hover{
  cursor: pointer;
}
</style>
