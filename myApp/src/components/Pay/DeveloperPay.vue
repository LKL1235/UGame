<template>

  <n-form
      :model="form"
      style="width: 50vw;margin-left: 25vw"
  >

    <n-form-item label="名称">
      <n-input v-model:value="form.subject" disabled>

      </n-input>
    </n-form-item>

    <n-form-item label="价格">
      <n-input v-model:value="form.totalAmount" disabled>

      </n-input>
    </n-form-item>

    <button @click="post1">提交</button>
  </n-form>
</template>

<script setup lang="ts">
import {ref} from "vue";
import axios from "axios";
import {useUserStore} from "@/stores/User";
import dayjs from "dayjs";

const User=useUserStore()

const form=ref({
  tradeNo:"",
  subject:"开发者保证金",
  totalAmount:"100.0",
  userName:"",
  createTime:"",
})
const post1 = () => {
  form.value.userName=User.$state.user.name
  form.value.createTime=dayjs().toString()
  axios.post("/alipay/developerPay",form.value).then((respon)=>{
    console.log(respon)
    document.write(respon.data)
  }).catch(error=>console.log(error))
}
</script>

<style scoped>

</style>
