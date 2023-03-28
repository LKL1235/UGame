<template>
  <el-container>
    <el-aside width="15vw">
      <div style="border-style: solid;border-width: 0 1px 0 0;border-color: gray;height: 88vh" >
        <div>
          <n-avatar
              :size="48"
              :src="User.$state.user.avatar!==null?User.$state.user.avatar:'/unLogin.png'"
              class="pointerStyle"
              style="top: 5px;border-style:outset;border-color: #52a2c2"
          />
          <a style="color: #6dcff6;"> {{User.$state.user.name}}</a>
        </div>
        <n-divider />
        <div style="color: #b7ccd5;background-color: #434953;width: 100%;height: 40px;">
          {{"好友"}}
          <n-input v-model:value="friendFinder" type="text" placeholder="基本的 Input" style="width: 60%" size="small"  />
<!--    搜索      -->
          <svg xmlns="http://www.w3.org/2000/svg" @click="" style="width: 25px;height: 25px;position: relative;left: -25px;top: 7px" version="1.1" x="0px" y="0px" viewBox="0 0 100 100"><g transform="translate(0,-952.36218)"><path class="ColorSelector" d="m 40.99855,964.36216 c -15.9798,0 -28.9986,13.01864 -28.9986,28.99862 0,15.97992 13.0188,28.99862 28.9986,28.99862 6.9189,0 13.2881,-2.4349 18.2803,-6.4997 l 23.5927,23.6239 c 1.1714,1.1714 3.0784,1.1715 4.2498,0 1.1716,-1.1715 1.1716,-3.0783 0,-4.2498 l -23.6239,-23.5926 c 4.0649,-4.9923 6.4997,-11.3615 6.4997,-18.28042 0,-15.97998 -13.0187,-28.99862 -28.9986,-28.99862 z m 0,5.99972 c 12.7374,0 22.9989,10.26145 22.9989,22.9989 0,12.73732 -10.2615,22.99892 -22.9989,22.99892 -12.7374,0 -22.9989,-10.2616 -22.9989,-22.99892 0,-12.73745 10.2615,-22.9989 22.9989,-22.9989 z" fill="currentColor" fill-opacity="1" stroke="none" visibility="visible" display="inline" overflow="visible"></path></g></svg>
<!--    好友申请      -->
          <svg version="1.1" id="Layer_4" xmlns="http://www.w3.org/2000/svg" @click="" style="fill: #fff;width: 25px;height: 25px;position: relative;left:-8px;top: 7px" x="0px" y="0px" width="256px" height="256px" viewBox="0 0 256 256"><g class="friendHead" transform="matrix(1.34048,0,0,1.34048,-10.0942,-5.50445)"><circle cx="85.923" cy="47.419" r="33.526"></circle></g><path class="friendBody" d="M100.353,170.882c0-23.589,10.397-44.736,26.842-59.152c-3.352-0.423-6.773-0.649-10.257-0.649H94.231	c-39.775,0-56.481,28.271-56.481,63.099v41.88c0,0-0.3,16.369,35.917,21.813c36.217,5.444,73.651,5,73.651,5 C119.666,230.681,100.353,203.044,100.353,170.882z"></path><path class="friendBodyFull" d="M176.504,217.922v-42.624c0-35.443-17.002-64.217-57.483-64.217H106.96h-1.017H94.898 c-40.48,0.001-57.484,28.774-57.484,64.217v42.624c0,0,0,22.197,69.544,22.197C177.521,240.121,176.504,217.922,176.504,217.922z"></path><path class="friendArm" d="M18.167,63.833c6.53-0.734,39.348,39.127,50.007,47.647s21.327,16.686,15.16,25.353s-20.646,16.74-36.167,2.5 S-1.349,90.532,0,85.333S8.599,64.909,18.167,63.833z"></path><path class="redCircle" d="M178.51,103.892c-36.998,0-66.99,29.992-66.99,66.99s29.994,66.99,66.99,66.99c36.997,0,66.99-29.992,66.99-66.99 S215.508,103.892,178.51,103.892z"></path></svg>
<!--    查找好友      -->
          <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" @click="" style="fill: #fff;width: 25px;height: 25px; margin-left: 5px;position: relative;top: 7px" x="0px" y="0px" width="256px" height="256px" viewBox="0 0 256 256"><g class="friendHead" transform="matrix(1.34048,0,0,1.34048,-10.0942,-5.50445)"><circle cx="86.296" cy="47.419" r="33.526"></circle></g><path class="friendBody" d="M100.353,170.882c0-23.589,10.397-44.736,26.842-59.152c-3.352-0.423-6.773-0.649-10.257-0.649H94.231	c-39.775,0-56.481,28.271-56.481,63.099v41.88c0,0-0.3,16.369,35.917,21.813c36.217,5.444,73.651,5,73.651,5 C119.666,230.681,100.353,203.044,100.353,170.882z"></path><path class="plusCircle" d="M179.01,103.892c-36.998,0-66.99,29.992-66.99,66.99s29.994,66.989,66.99,66.989c36.997,0,66.99-29.991,66.99-66.989 S216.008,103.892,179.01,103.892z M217.893,175.882h-33.647v33.882c0,2.762-2.239,5-5,5s-5-2.238-5-5v-33.882h-33.647 c-2.762,0-5-2.238-5-5c0-2.763,2.238-5,5-5h33.647V132.47c0-2.762,2.239-5,5-5s5,2.238,5,5v33.412h33.647c2.762,0,5,2.237,5,5 C222.893,173.643,220.654,175.882,217.893,175.882z"></path></svg>

        </div>
        <div v-for="item in friendList">
          <span>{{item.friendName}}</span>
        </div>

      </div>
    </el-aside>
    <el-main>
      <div
          v-if="!isAnyTab"
          style="color: #969696;position: absolute;left: 50vw;top: 50vh"> 点击一个好友或群组聊天，开始聊天吧！</div>
      <n-tabs
          v-if="isAnyTab"
          v-model:value="tab"
          type="card"
          :closable="true"
          tab-style="min-width: 80px;"
          @close="handleClose"
      >
        <n-tab-pane v-for="panel in panelsRef" :key="panel" :name="panel">
          {{ panel }}
        </n-tab-pane>
      </n-tabs>

    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import {useUserStore} from "@/stores/User";
import {onMounted, ref} from "vue";
import axios from "axios";

const User=useUserStore()

const friendFinder = ref()

const friendApplyList = ref([])
const friendList = ref ([])
// const isAnyTab = ref(false)
const isAnyTab = ref(true)
const tab = ref(1)
const panelsRef = ref([1,2,3,4,5])


const handleClose =(name: string) => {
  const { value: panels } = panelsRef
  const nameIndex = panels.findIndex((panelName) => panelName === name)
  if (!~nameIndex) return
  panels.splice(nameIndex, 1)
  if (name === tab.value) {
    tab.value = panels[Math.min(nameIndex, panels.length - 1)]
  }
}


onMounted(()=>{
  axios.get("/getFriendApply",{params:{userName:User.user.name}}).then((respon)=>{
    friendApplyList.value = respon.data.data
  }).catch(error=>{console.log(error)})
  axios.get("/getFriend",{params:{userName:User.user.name}}).then((respon)=>{
    friendList.value = respon.data.data
  }).catch(error=>{console.log(error)})
})
</script>

<style scoped>
svg:hover{
  cursor: pointer;
}
</style>
