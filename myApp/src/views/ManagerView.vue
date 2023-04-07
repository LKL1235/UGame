<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <h1 style="color: #FFFFFF;margin-left: 2vw">管理菜单</h1>
        <el-menu
            default-active="1"
            style="height: 100vh;background-color: #1b2939;"

        >
          <el-menu-item index="1" @click="router.push({name:'userManager'})">
            <span >用户管理</span>
          </el-menu-item>
          <el-menu-item index="2" @click="router.push({name:'gameManager'})">
            <span >游戏管理</span>
          </el-menu-item>
          <el-menu-item index="3" @click="router.push({name:'postManager'})">
            <span >讨论管理</span>
          </el-menu-item>

          <el-menu-item index="4" @click="router.push({name:'boardManager'})">
            <span >板块管理</span>
          </el-menu-item>

        </el-menu>

      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";

const router=useRouter()


</script>

<style scoped>
span{
  color: #FFFFFF;
}
</style>
