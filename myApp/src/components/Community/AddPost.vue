<template>
  <el-form :model="formData" ref="formData" label-width="120px">
    <el-form-item label="Post Title">
      <el-input v-model="formData.postTitle"></el-input>
    </el-form-item>
    <el-form-item label="Contents">
      <el-input type="textarea" v-model="formData.contents"></el-input>
    </el-form-item>
    <el-form-item label="Board Name">
      <el-select v-model="formData.boardName" placeholder="Please select" @change="getBoardId">
        <el-option v-for="item in boardList" :key="item.id" :label="item.name" :value="item.name">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="Board Id">
      <el-input v-model="formData.boardId" readonly></el-input>
    </el-form-item>
  </el-form>
</template>

<script setup lang="ts">

</script>

<style scoped>

</style>
