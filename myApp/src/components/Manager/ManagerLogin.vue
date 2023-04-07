<template>
  <h1 style="color: #FFFFFF;position: fixed;left: 35vw;top:25vh">UGame后台管理系统</h1>
  <div class="div_box">
    管理员登录
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
        status-icon
        style="width: 80%;margin-top:10%;margin-left: 10%"
    >
      <el-form-item label="Administrator" prop="name">
        <el-input v-model="ruleForm.name" />
      </el-form-item>

      <el-form-item label="passWord" prop="passWord">
        <el-input v-model="ruleForm.passWord" show-password/>
      </el-form-item>

      <el-form-item label="verify code" prop="verifyCode">
        <el-input v-model="ruleForm.verifyCode" />
      </el-form-item>

      <img :src="kaptcha" @click="getKaptcha" id="kaptchaImg">

      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)">
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref,onMounted } from 'vue'
import type { FormInstance, FormRules} from 'element-plus'
import { ElMessage } from 'element-plus'
import axios from "axios";
import {useUserStore} from "@/stores/User";
import {useRouter} from "vue-router";

const router = useRouter()
const User = useUserStore()
const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive({
  name: '',
  passWord:'',
  verifyCode:"",
})

const rules = reactive<FormRules>({
  name: [
    { required: true, message: 'Please input username', trigger: 'blur' },
  ],
  passWord: [
    {
      required: true,
      message: 'Please input password',
      trigger: 'blur',
    },
  ],
  verifyCode: [
    {
      required: true,
      message: 'Please input verifyCode',
      trigger: 'blur',
    },
  ],
})

const kaptcha=ref()

const getKaptcha = () => {
  axios.get("/Kaptcha").then((respon)=>{
    kaptcha.value=respon.data.data
  }).catch(error=>{console.log(error)})
}


const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      axios.post("/managerLogin",ruleForm).then((respon)=>{
        if (respon.data.code == 200){
          ElMessage.success("登录成功")
          User.$state.user.isRoot = true
          router.push({name:'manager'})
        }else{
          ElMessage.error("登录失败")
        }
      }).catch(error=>{console.log(error)})
    } else {
      User.$state.user.isRoot = false
      ElMessage.error("wrong input")
    }
  })
}

onMounted(()=>{
  getKaptcha()
})
</script>

<style scoped>
.div_box{
  background-color: #FFFFFF;
  width: 20vw;
  height: 35vh;
  position: fixed;
  left: 60vw;
  top: 25vh;
  text-align: center;
}

</style>
