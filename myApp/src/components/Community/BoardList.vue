<template>
  <div class="main">

    <div class="boards" v-for="(board,index) in boardNum">
      <div class="board">
        <a style="color: #FFFFFF;font-size: 28px">
          {{boards[index]?.postTitle.length>15?boards[index]?.postTitle.substring(0,15)+"...":boards[index]?.postTitle}}
        </a>
        <div>
          <a style="color: #FFFFFF;font-size: 11px;">{{"来自："+boards[index]?.boardName}}</a>
        </div>
        <a style="color: #FFFFFF;font-size: 18px;float: none">
          {{boards[index]?.contents.length>40?boards[index]?.contents.substring(0,40)+"...":boards[index]?.contents}}
        </a>

      </div>
    </div>

  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";

const boards = ref()
const boardNum = ref()


onMounted(()=>{
  axios.get(("/getBoard")).then((response=>{
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
  background-color: #0D131B;
}
.board:hover{
  cursor: pointer;
}
</style>
