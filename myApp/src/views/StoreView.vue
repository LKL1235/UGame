<template>
  <!--  页面背景-->
  <div class="storeMain">

    <!--  固定头  -->
    <div style="position:relative;background-color: rgb(0 0 0 / 0%);z-index: 5;margin-bottom: 50px">
      <!--愿望单-->
      <div class="wishList pointerStyle">
        <a>愿望单</a>
      </div>


      <!--    搜索框-->
      <div class="searchBox">

        <!--        <img src="/src/components/icons/titleLogo.jpg" width="17px" height="17px" style="margin: 4px 10px 4px 0px;">-->
<!-- 小头像-->
        <div>
          <n-avatar
              :size="20"
              :src="avatar===null?'/unLogin.png':avatar"
              style="float: left;margin: 7px 6px 0px 7px"
          />
        </div>
<!--  商店选单-->
        <div class="searchBoxItem pointerStyle">
          <n-dropdown class="" trigger="hover" :options="options" @select="handleSelect">
            <a style="position: relative;">您的商店</a>
          </n-dropdown>
        </div>
<!--新鲜推荐-->
        <div class="searchBoxItem pointerStyle">
          <n-dropdown class="" trigger="hover" :options="options" @select="handleSelect">
            <a style="position: relative;top: 1px">新鲜推荐</a>
          </n-dropdown>
        </div>
<!--类别-->
        <div class="searchBoxItem pointerStyle">
          <n-dropdown class="" trigger="hover" :options="options" @select="handleSelect">
            <a style="position: relative;top: 1px">类别</a>
          </n-dropdown>
        </div>
<!-- 搜索框-->
        <n-input v-model:value="search"
                 type="text"
                 placeholder="搜索"
                 style="width: 200px;
                 background-color: #316282;
                 position: relative;left: 50%;" >
        </n-input>
<!-- 搜索按钮-->
        <div style="background-color: #64B6E5;width: 28px;height: 28px;position: relative;left: 903px;top: -31px;border-radius: 2px;" @click="gotoSearch">
          <n-icon size="26" color="#2E4D6A">
            <Search class="pointerStyle"/>
          </n-icon>
        </div>

      </div>

    </div>
    <router-view/>
  </div>

</template>

<script setup lang="ts">
import {Search,Help} from "@vicons/ionicons5"
import {computed, ref} from "vue";
import {useRouter} from "vue-router";
import {useUserStore} from "@/stores/User";

const router=useRouter()
const user=useUserStore()
//小头像
const avatar=computed(()=>{
  return user.$state.user.avatar
})
//搜索内容
const search=ref<String>("")
const gotoSearch = () => {
  router.push({name:'search',query:{gameName:search.value}})
}
</script>

<style scoped>

/*主体颜色*/
.storeMain{
  height: 100vh;
}
/*指针样式*/
.pointerStyle{
  cursor:pointer
}
/*愿望单*/
.wishList{
  position: relative;
  left: 68%;
  top:10px;
  background-color: #779fa9;
  width: 124px;
  text-align: center;
  color: #FFFFFF
}
.wishList:hover{
  position: relative;
  left: 68%;
  top:10px;
  background-image: -webkit-gradient(linear, 0% 0%, 100% 100%, from(rgb(255, 255, 255)), to(rgb(145, 154, 163)));
  width: 124px;
  text-align: center;
  color: #111111
}
/*搜索盒子*/
.searchBox{
  background: linear-gradient(90deg, rgba(62, 103, 150, 0.919) 11.38%, rgba(58, 120, 177, 0.8) 25.23%, rgb(15, 33, 110) 100%);
  height: 35px;
  position: relative;
  top: 20px;
  left: 24vw;
  width: 100vh;
  color: #e5e5e5;
}
.searchBoxItem{
  alignment: center;
  float: left;
  margin-right: 20px;
  padding: 5px;
  height: 24px;
}
.searchBoxItem:hover{
  background: rgba( 103, 193, 245, 0.4 );
}
</style>
