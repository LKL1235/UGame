<template>

  <n-form
      :model="form"
  >
    <n-form-item label="订单号">
      <n-input v-model:value="form.tradeNo">

      </n-input>
    </n-form-item>

    <n-form-item label="名称">
      <n-input v-model:value="form.subject">

      </n-input>
    </n-form-item>

    <n-form-item label="价格">
      <n-input v-model:value="form.totalAmount">

      </n-input>
    </n-form-item>

    <button @click="post1">222</button>
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
  subject:"",
  totalAmount:"",
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
