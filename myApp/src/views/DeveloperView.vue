<template>
  <div class="main">
    <div class="release" @click="()=>{router.push({name:'gameUpLoad'})}">
      <a>发布游戏</a>
    </div>
    <div class="update" @click="()=>{router.push({name:'gameUpLoad'})}">
      <a>发布更新</a>
    </div>
    <div class="yield" @click="()=>{router.push({name:'gameUpLoad'})}">
      <a>查看收益</a>
    </div>
    <div class="repository" @click="()=>{router.push({name:'repository'})}">
      <a>我的仓库</a>
    </div>
  </div>
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";

const router=useRouter()
</script>

<style scoped>

a{
  cursor:pointer
}
.main{
  display: grid;
  grid-template-columns: 3fr 4fr 4fr 3fr;
  grid-template-areas:
    ". release update ."
    ". yield repository .";
  grid-auto-rows: 382px;
}
.release{
  grid-area: release;
  margin-top: 15%;
  height: 300px;
  background-image: linear-gradient(#e66465, #9198e5);
  color: #ffffff;
  font-size: 80px;
  display: flex; /**/
  justify-content: center; /*水平居中*/
  align-items: Center; /*垂直居中*/
  border-top-left-radius: 10px;

}
.update{
  grid-area: update;
  margin-top: 15%;
  height: 300px;
  background-image: linear-gradient(	#4EFEB3, #9198e5);
  color: #ffffff;
  font-size: 80px;
  display: flex; /**/
  justify-content: center; /*水平居中*/
  align-items: Center; /*垂直居中*/
  border-top-right-radius: 10px;
}
.yield{
  grid-area: yield;
  height: 300px;
  background-image: linear-gradient(#9198e5,	#4EFEB3);
  color: #ffffff;
  font-size: 80px;
  display: flex; /**/
  justify-content: center; /*水平居中*/
  align-items: Center; /*垂直居中*/
  border-bottom-left-radius: 10px;
}
.repository{
  grid-area: repository;
  height: 300px;
  background-image: linear-gradient(#9198e5,	#e66465);
  color: #ffffff;
  font-size: 80px;
  display: flex; /**/
  justify-content: center; /*水平居中*/
  align-items: Center; /*垂直居中*/
  border-bottom-right-radius: 10px;
}
</style>
