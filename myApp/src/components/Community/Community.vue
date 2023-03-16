<template>
<div class="main">
  <div class="box">
    <div class="titleDiv">
      <a style="color: #FFFFFF;font-size: 26px">社区中心</a>
      <br>
      <a style="color: #9099A1;font-size: 16px">UGame 上所有游戏与软件的社区和官方内容。 </a>
    </div>
    <div class="leftDiv">

    </div>
    <div class="rightDiv">

    </div>
  </div>
  <div style="
    background-color: #0d121a;
    height: 5px;
    margin-left: 25vw;
    width: 48.4vw;
    margin-top: 25px;
    ">
  </div>
  <router-view> </router-view>

</div>
</template>

<script setup lang="ts">





</script>

<style scoped>
.main{
  height: 100%;
  background-position: center top;
  background-attachment: fixed;
  background-repeat: no-repeat;
  background-size: cover;
  background-image: url('http://127.0.0.1:8889/background/communitybackground.jpg');
}
.box{
  display: grid;
  grid-template-areas:
  " . text . ."
  " . left right .";
  grid-template-columns: 2.3fr 3fr 1.95fr 2fr
}
.titleDiv{
  grid-area: text;
  margin-top: 20px;
}
.leftDiv{
  margin-top: 10px;
  grid-area: left;
  width: 32vw;
  height:225px;
  opacity: 0.5;
  background-color: #0F1922;
}
.rightDiv{
  margin-top: 10px;
  grid-area: right;
  width: 16vw;
  height:225px;
  opacity: 0.5;
  background-color: #0F1922;
}
</style>
