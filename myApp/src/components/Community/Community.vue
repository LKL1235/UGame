<template>
<div class="main">
  <div class="box">
    <div class="titleDiv">
      <a style="color: #FFFFFF;font-size: 26px">社区中心</a>
      <br>
      <a style="color: #9099A1;font-size: 16px">UGame 上所有游戏与软件的社区和官方内容。 </a>

    </div>

    <div class="leftDiv">
      <a style="color: #b8b6b4;margin: 10px 10px 10px 10px">热门的板块</a>
      <div class="leftDivGrid">
        <div v-for="index in 8" @click="()=>{router.push({name:'postList',query:{boardId:boardList[index-1].boardId,boardName:boardList[index-1].boardName}})}" class="boardTop">
          {{boardList?boardList[index-1]?.boardName:" "}}
        </div>
      </div>
    </div>

    <div class="rightDiv">
      查找板块
      <div style="">
        <n-input v-model:value="boardSearch" type="text" placeholder="板块名称" style="width: 10vw;margin-left: 2vw;margin-top: 25px"  />
        <!-- 搜索按钮-->
        <div style="background-color: #64B6E5;width: 28px;height: 28px;border-radius: 2px;float: right;position: relative;left: -2.5vw;top:27px;" class="boardSearchBox" @click="gotoBoardList(boardSearch)">
          <n-icon size="26" color="#2E4D6A">
            <Search />
          </n-icon>
        </div>
      </div>
<!--   分割线   -->
      <div style="background-color: #000000;height: 5px;width: 16vw;margin-top: 25px"></div>

      <div style="">
        查找帖子
        <br>
        <n-input v-model:value="postSearch" type="text" placeholder="帖子名称" style="width: 10vw;margin-left: 2vw;margin-top: 25px"  />
        <!-- 搜索按钮-->
        <div style="background-color: #64B6E5;width: 28px;height: 28px;border-radius: 2px;float: right;position: relative;left: -2.5vw;top:27px;" class="boardSearchBox" @click="gotoPostList(postSearch)">
          <n-icon size="26" color="#2E4D6A">
            <Search />
          </n-icon>
        </div>
      </div>

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
  <router-view :key="$route.fullPath" />

<!--  <router-view> </router-view>-->

</div>
</template>

<script setup lang="ts">
import {Search} from "@vicons/ionicons5"
import {onMounted, ref} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";


const router = useRouter()
const boardList = ref()
const boardSearch = ref("")
const postSearch = ref("")

const gotoBoardList = (boardName:string) => {
  router.push({path:'boardList',query:{boardName:boardName}})
}
const gotoPostList = (postName:string) => {
  router.push({path:'postList',query:{postName:postName}})
}

onMounted(()=>{
  axios.get("/getBoards").then((respon)=>{
    boardList.value=respon.data.data
  }).catch(error=>{console.log(error)})

  // axios.get("/getPosts").then((respon)=>{
  //   postList.value = respon.data.data
  //   postNum.value = postList.value.length
  // }).catch(error=>{console.log(error)})

})
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
.leftDivGrid{
  display: grid;
  grid-template-columns: 1fr 1fr;
}
.boardTop{
  overflow:hidden;
  text-overflow:ellipsis;
  color: #FFFFFF;
  margin-left: 0.3vw;
  width: 15vw;
  height: 42px;
  border-style: solid;
  border-color: #20769B;
  border-bottom-width: 1px;
  border-top-width: 2px;
  text-align: center;
}
.boardTop:hover{
  cursor: pointer;
}
.rightDiv{
  margin-top: 10px;
  grid-area: right;
  width: 16vw;
  height:225px;
  opacity: 0.5;
  background-color: #0F1922;
  color: #b8b6b4;
}
.boardSearchBox:hover{
  cursor: pointer;
}
</style>
