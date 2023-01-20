<template>
  <div class="mainDiv">
    <!-- 左箭头-->
    <div class="leftAngle" @click="previousIndex">
      <n-icon size="40" color="#FFFFFF" class="angleIcon">
        <ChevronLeft/>
      </n-icon>
    </div>

    <div style="float: left">
      <img :src="props.game[0].img[1]" style="width: 306px;height: 281px">
      <div style="background-color: #20769B;width: 306px;height: 100px;margin-left: 0px;margin-top: -6px">

      </div>
    </div>

    <div style="float: left;margin-left: 14px">
      <img :src="props.game[0].img[1]" style="width: 306px;height: 281px">
      <div style="background-color: #20769B;width: 306px;height: 100px;margin-left: 0px;margin-top: -6px">

      </div>
    </div>

    <div style="float: left;margin-left: 14px">
      <img :src="props.game[0].img[1]" style="width: 306px;height: 281px">
      <div style="background-color: #20769B;width: 306px;height: 100px;margin-left: 0px;margin-top: -6px">

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
         v-for="Index in props.game.length"
         :style="{backgroundColor:Index-1===gameIndex? '#FFFFFF':'#b8b6b4'}"

    >
    </div>
  </div>

</template>

<script setup lang="ts">
import {ChevronLeft,ChevronRight} from "@vicons/fa"
import {ref} from "vue";

const gameIndex=ref(2)
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
const props = defineProps<{
  game:object[]
}>()
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
