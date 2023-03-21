<template>
  <div class="main">

    <div class="boards" v-for="(board,index) in boardNum">
      <div class="board">
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
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";

const route = useRoute()
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
  background-color: #0D131B;
}
.board:hover{
  cursor: pointer;
}
</style>
