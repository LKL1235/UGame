<template>

  <div class="mainDiv" @mouseover="mouseIn" @mouseout="mouseOut">
<!-- 左箭头-->
    <div class="leftAngle" @click="previousIndex">
      <n-icon size="40" color="#FFFFFF" class="angleIcon">
        <ChevronLeft/>
      </n-icon>
    </div>
    <!--  图片-->
      <img :src="props.game[gameIndex].img[0]" class="imgSlot" :alt="props.game[gameIndex].gameName">
<!--  信息栏  -->
    <div class="info">
      <div>
        <a style="font-size: 28px;color: #FFFFFF;">{{props.game[gameIndex].gameName}}</a>
      </div>
<!--图片4连-->
      <div class="miniImgDiv">
        <img class="miniImg" :src="props.game[gameIndex].img[1]">
      </div>
      <div class="miniImgDiv">
        <img class="miniImg" :src="props.game[gameIndex].img[2]">
      </div>
      <div class="miniImgDiv">
        <img class="miniImg" :src="props.game[gameIndex].img[3]">
      </div>
      <div class="miniImgDiv">
        <img class="miniImg" :src="props.game[gameIndex].img[4]">
      </div>

      <div style="text-align: left;height: 80%;">
        <div>
          <a style="color: #FFFFFF;font-size: 21px;margin-left: 5%;">现已推出</a>
        </div>
      <!-- tags -->
        <div v-for="tag in props.game[gameIndex].tags" style="background-color: #b8b6b4;width: 20%;margin-left: 5%;text-align: center;float: left">
          <a style="color: #FFFFFF">{{tag}}</a>
        </div>
      </div>
<!--  价格  -->
      <div style="text-align: left">
        <a style="color: #FFFFFF;margin-left: 5%">￥{{props.game[gameIndex].price}}</a>
      </div>
    </div>
    <!--  右箭头  -->
    <div class="rightAngle" @click="nextIndex">
      <n-icon size="40" color="#FFFFFF" class="angleIcon">
        <ChevronRight/>
      </n-icon>
    </div>

<!-- 下方index-->
    <div >
      <div
          v-for="Index in props.game.length"
          class="indexDiv" :style="{left:-400+Index*20+'px',backgroundColor:Index-1===gameIndex? '#FFFFFF':'#b8b6b4'}"
          @click="change(Index-1)"
      >
      </div>
    </div>

  </div>
</template>

<script setup lang="ts">
import {ChevronLeft,ChevronRight} from "@vicons/fa"
import {onMounted, ref} from "vue";


var timer
const props = defineProps<{
  game:object[]
}>()
const gameIndex=ref(0)
const previousIndex = () => {
  if(gameIndex.value==0){
      gameIndex.value=props.game.length-1
  }else{
    gameIndex.value-=1
  }
}
const nextIndex = () => {
  if(gameIndex.value==props.game.length-1){
    gameIndex.value=0
  }else{
    gameIndex.value+=1
  }
}
const change = (Index:number) => {
  gameIndex.value=Index
}
const mouseIn = () => {
  clearInterval(timer)
}
const mouseOut = () => {
  timer=setInterval(()=>{
    nextIndex()
  },5000)
}

onMounted(()=>{
  timer=setInterval(()=>{
    nextIndex()
  },5000)
})
</script>

<style scoped>
.mainDiv{
  margin-left: 22%;
  width: 54%;
  height: 378px;
}
.leftAngle{
  float: left;
  margin-right: 1%;
  position: relative;
  top: 30%;
  width: 4%;
  height: 28%;
  background-image: linear-gradient(to right, #131C28 , #1B2636 );
}
.leftAngle:hover{
  background-image: linear-gradient(to right, #486075 , #131B26);
}
.angleIcon{
  margin-top: 75%;
}
.imgSlot{
  position: relative;
  float: left;
  width:60%;
  height: 90%;
  box-shadow: 0 0 5px 2px #0e0d0d;
}
.info{
  text-align: center;
  margin-left: 65%;
  width:32%;
  height: 90%;
  background-color: #0F1922;
  box-shadow: 0 0 5px 2px #0e0d0d;
}
.miniImgDiv{
  width: 45%;
  height: 20%;
  float: left;
  margin-top: 5%;
  margin-right: 5%;
  z-index: 0;
}
.miniImg{
  height: 100%;
  width: 100%;
}
.rightAngle{
  margin-left: 98%;
  margin-top: -22%;
  margin-right: 1%;
  width: 4%;
  height: 28%;
  background-image: linear-gradient(to left, #131C28 , #1B2636 );
}
.rightAngle:hover{
  background-image: linear-gradient(to left, #486075 , #131B26);
}
.indexDiv{
  height: 15px;
  width: 18px;
  position: relative;
  top: 120px;
  float: left;
  border-radius: 5px;
}
</style>
