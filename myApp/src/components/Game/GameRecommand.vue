<template>
  <div class="mainDiv" @mouseover="mouseIn" @mouseout="mouseOut">
    <!-- 左箭头-->
    <div class="leftAngle" @click="previousIndex">
      <n-icon size="40" color="#FFFFFF" class="angleIcon">
        <ChevronLeft/>
      </n-icon>
    </div>

    <div style="float: left">
      <img :src="props.game[gameIndex*3].img[1]" style="width: 306px;height: 281px">
      <div style="background-color: #20769B;width: 306px;height: 100px;margin-left: 0px;margin-top: -6px;text-align: center;color: #FFFFFF;font-size: 20px">
        <a style="margin-top: 5%">{{props.game[gameIndex*3].gameName}}</a>
        <div>
          <div v-for="tag in props.game[gameIndex*3].tags" style="background-color: #b8b6b4;width: 20%;margin-left: 5%;margin-top:5%;text-align: center;float: left;font-size: 14px">
            <a style="color: #FFFFFF">{{tag}}</a>
          </div>
        </div>
        <div style="position: relative;left: 10%;top: 30%;color: #BAE327">
          {{props.game[gameIndex*3].price==0.00?'免费开玩':'￥'+props.game[gameIndex*3].price}}
        </div>
      </div>
    </div>

    <div style="float: left;margin-left: 14px">
      <img :src="props.game[1+gameIndex*3].img[1]" style="width: 306px;height: 281px">
      <div style="background-color: #20769B;width: 306px;height: 100px;margin-left: 0px;margin-top: -6px;text-align: center;color: #FFFFFF;font-size: 20px">
        {{props.game[1+gameIndex*3].gameName}}
        <div>
          <div v-for="tag in props.game[1+gameIndex*3].tags" style="background-color: #b8b6b4;width: 20%;margin-left: 5%;margin-top:5%;text-align: center;float: left;font-size: 14px">
            <a style="color: #FFFFFF">{{tag}}</a>
          </div>
        </div>
        <div style="position: relative;left: 10%;top: 30%;color: #BAE327">
<!--          {{'￥'+props.game[1+gameIndex*3].price}}-->
          {{props.game[1+gameIndex*3].price==0.00?'免费开玩':'￥'+props.game[1+gameIndex*3].price}}
        </div>
      </div>
    </div>

    <div style="float: left;margin-left: 14px">
      <img :src="props.game[2+gameIndex*3].img[1]" style="width: 306px;height: 281px">
      <div style="background-color: #20769B;width: 306px;height: 100px;margin-left: 0px;margin-top: -6px;text-align: center;color: #FFFFFF;font-size: 20px">
        {{props.game[2+gameIndex*3].gameName}}
        <div>
          <div v-for="tag in props.game[2+gameIndex*3].tags" style="background-color: #b8b6b4;width: 20%;margin-left: 5%;margin-top:5%;text-align: center;float: left;font-size: 14px">
            <a style="color: #FFFFFF">{{tag}}</a>
          </div>
        </div>
        <div style="position: relative;left: 10%;top: 30%;color: #BAE327">
<!--          {{'￥'+props.game[2+gameIndex*3].price}}-->
          {{props.game[2+gameIndex*3].price==0.00?'免费开玩':'￥'+props.game[2+gameIndex*3].price}}
        </div>
      </div>
    </div>
    <!--  右箭头  -->
    <div class="rightAngle" @click="nextIndex">
      <n-icon size="40" color="#FFFFFF" style="top: -330%">
        <ChevronRight/>
      </n-icon>
    </div>

  </div>

  <div style="position: relative;left: -30%;top: 20px">
    <div class="indexDiv"
         v-for="Index in props.game.length/3"
         :style="{backgroundColor:Index-1===gameIndex? '#FFFFFF':'#b8b6b4'}"
         @click="change(Index-1)"
    >
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
const maxIndex=props.game.length/3
const gameIndex=ref(0)
const previousIndex = () => {
  if(gameIndex.value==0){
    gameIndex.value=maxIndex-1
  }else{
    gameIndex.value-=1
  }
}
const nextIndex = () => {
  if(gameIndex.value==maxIndex-1){
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



.rightAngle{
  position: relative;
  left: 98%;
  top: 30%;
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
  border-radius: 5px;
  color: #FFFFFF;
  float: left;
  margin-left: 20px;
}
</style>
