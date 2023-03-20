<template>
  <el-form :model="formData" :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
    <el-form-item label="Board Name" prop="boardName">
      <el-input v-model="formData.boardName"></el-input>
    </el-form-item>
    <el-form-item label="Game Name" prop="gameName">
      <el-select v-model="formData.gameName" placeholder="Please select" @change="getGameId">
        <el-option v-for="game in gameList" :key="game.gameId" :label="game.gameName" :value="game.gameName"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="Board Description" prop="boardDescription">
      <el-input type="textarea" v-model="formData.boardDescription"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('form')">Create Board</el-button>
      <el-button @click="resetForm('form')">Reset</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup lang="ts">

</script>

<style scoped>

</style>
