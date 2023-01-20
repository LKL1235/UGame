<template>
  <div class="main">

    <div class="form">
      发布游戏
      <n-form
        ref="formRef"
        :model="formValue"
        style="margin-left: 5%"
      >
        <n-form-item label="游戏名称" path="gameName">
          <n-input v-model:value="formValue.gameName" round placeholder="输入游戏名称" autosize style="min-width: 30%" />
        </n-form-item>
        <n-form-item label="价格" path="price">
          <n-input v-model:value="formValue.price" round placeholder="输入价格（以元为单位）" autosize style="min-width: 30%" >
            <template #suffix>
              元
            </template>
          </n-input>
        </n-form-item>
        <n-form-item label="标签" path="tags">
          <n-input v-model:value="formValue.tags"  round placeholder="请输入游戏标签方便我们分类" autosize style="min-width: 30%" />
        </n-form-item>
        <n-form-item label="游戏简介" path="introduce">
          <n-input v-model:value="formValue.introduce" round type="textarea" placeholder="请输入游戏简介" style="width: 80%"/>
        </n-form-item>
        <n-form-item label="关于这款游戏" path="about">
          <n-input v-model:value="formValue.about" round type="textarea" placeholder="关于这款游戏（内容、玩法）" style="width: 80%" />
        </n-form-item>
      </n-form>
      <n-button type="primary" @click="upload">
        发布
      </n-button>
    </div>

      <n-upload
          action="https://www.mocky.io/v2/5e4bafc63100007100d8b70f"
          :default-file-list="previewFileList"
          list-type="image-card"
          @preview="handlePreview"
          style="position: relative;left: 48vw;top: 14vh"
      />
      <n-modal
          v-model:show="showModal"
          preset="card"
          style="width: 600px"
          title="一张很酷的图片"
      >
        <img :src="previewImageUrl" style="width: 100%">
      </n-modal>

  </div>
</template>

<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";

const formRef=ref()
const formValue=ref({
  name:'',
  gameName:'',
  price:'',
  tags:'',
  introduce:'',
  about:'',
})
const upload = () => {
  formValue.value.name='hood1235'
  axios.post("/game/upload",formValue.value).then((respon)=>{

  }).catch(error=>console.log(error))
}
</script>

<style scoped>
.main{
  display: grid;
  grid-template-columns: 3fr 7fr 3fr;
  grid-template-areas:
    ". main .";
  grid-gap: 5px;
  grid-auto-rows: 750px;
}
.form{
  grid-area: main;
  margin-top: 5%;
  background-color: #181A21;
  border-radius:2em;
  text-align: center;
  color: #b8b6b4;
  font-size: 36px;
}
</style>
