<template>

  <n-form
      :model="form"
  >
    <n-form-item>
      <n-input v-model="form.outTradeNo">

      </n-input>
    </n-form-item>

    <n-form-item >
      <n-input v-model="form.subject">

      </n-input>
    </n-form-item>

    <n-form-item>
      <n-input v-model="form.totalAmount">

      </n-input>
    </n-form-item>

    <n-form-item>
      <n-input v-model="form.body">

      </n-input>
    </n-form-item>
    <button @click="post1">222</button>
  </n-form>
</template>

<script setup lang="ts">
import {ref} from "vue";

const form=ref({
  outTradeNo:"",
  subject:"",
  totalAmount:"",
  body:"",
})
const post1 = () => {
  axios.post("/",).then((respon)=>{
      
  }).catch(error=>console.log(error))
}
</script>

<style scoped>

</style>
