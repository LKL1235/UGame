<template>

  <div class="main">
    <n-form
        :model="formRegister"
        ref="formRegisterRef"
        :rules="RegisterRules"
    >
      <!-- 邮箱 -->
      <n-form-item path="email" label="邮箱地址" class="formItem" style="position: relative;top:10px">
        <n-input v-model:value="formRegister.email" placeholder="">

        </n-input>
      </n-form-item>

      <!-- 邮箱验证码 -->
      <n-form-item path="emailCode" label="邮箱验证码" class="formItem" style="width: 200px">
        <n-input v-model:value="formRegister.emailCode" placeholder="">

        </n-input>
        <button id="sendEmailCode" :disabled="emailCodeDisable" @click="sendEmailCode()">发送验证码{{countDown>0?'('+countDown+')':''}}</button>
      </n-form-item>

      <!--  账号-->
      <n-form-item path="name" label="用户名" label-style="color: #1999ff;" class="formItem"  >
        <n-input v-model:value="formRegister.name" placeholder="">
        </n-input>
        <n-popover trigger="hover">
          <template #trigger>
            <n-icon size="20" color="#1999FF">
              <HelpCircleOutline/>
            </n-icon>
          </template>
          <span>至少为6位的英文、数字或下划线</span>
        </n-popover>
      </n-form-item>

      <!--密码-->
      <n-form-item path="passWord" label="新密码" class="formItem">
        <n-input v-model:value="formRegister.passWord" type="password" show-password-on="click" placeholder="">
        </n-input>
        <n-popover trigger="hover">
          <template #trigger>
            <n-icon size="20" color="#1999FF">
              <HelpCircleOutline/>
            </n-icon>
          </template>
          <span>至少为6位的英文、数字下划线</span>
        </n-popover>
      </n-form-item>
      <!-- 确认密码  -->
      <n-form-item path="confirmPassWord" label="确认密码" class="formItem">
        <n-input v-model:value="formRegister.confirmPassWord" type="password" show-password-on="click" placeholder="">

        </n-input>
      </n-form-item>
      <!--验证码-->
      <n-form-item path="verifyCode" label="验证码" class="formItem">
        <n-input v-model:value="formRegister.verifyCode" maxlength="4" placeholder="" style="width: 200px">

        </n-input>
      </n-form-item>
      <img :src="kaptcha" @click="getKaptcha" id="kaptchaImg">
      <!--注册-->
      <n-button id="RegisterButton" @click="handleValidateButtonClick">重置密码</n-button>
    </n-form>
    <img src="/join_pc.png" style="width: 200px;position: fixed;left: 1070px;top: 210px;">
    <a style="width:200px;white-space:pre-line ;color: #BDBEBF;position: fixed;top: 380px;left: 1100px;font-size: 28px;z-index: 3">已有UGame账户？</a>
    <a style="width:200px;white-space:pre-line ;color: #BDBEBF;position: fixed;top: 480px;left: 1120px;font-size: 28px;z-index: 3">立即登录！</a>
    <button id="login" @click="gotoLogin">登录UGame</button>

  </div>


</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {FormInst, FormItemRule} from "naive-ui/lib";
import {useRouter} from "vue-router";
import {useUserStore} from "@/stores/User";
import {useMessage} from "naive-ui";
import dayjs from "dayjs";
import axios from "axios";
import md5 from 'js-md5';


const router=useRouter()
const store=useUserStore()
//使用message
const message=useMessage()
const day=dayjs().format("YYYY-MM-DD HH:mm:ss")
//是否为注册页面

//验证码
const kaptcha=ref()


//获取验证码
const getKaptcha = () => {
  axios.get("/Kaptcha").then((respon)=>{
    kaptcha.value=respon.data.data
  }).catch(error=>{console.log(error)})
}


//切换到登录页面
const gotoLogin = () => {
  router.push({name:'login'})
}

const formRegisterRef=ref<FormInst|null>(null)
//注册表单
const formRegister=ref({
  name:"",
  email:"",
  emailCode:"",
  passWord:"",
  confirmPassWord:"",
  regDate:"",
  verifyCode:"",
})
function validatePasswordStartWith (
    rule: FormItemRule,
    value: string
): boolean {
  return (
      !!formRegister.value.passWord &&
      formRegister.value.passWord.startsWith(value) &&
      formRegister.value.passWord.length >= value.length
  )
}
function validatePasswordSame (rule: FormItemRule, value: string): boolean {
  return value === formRegister.value.passWord
}
function emailValidate(rule:FormItemRule,value:string):boolean {
  if(/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(value)) {
    emailCodeDisable.value=false
    return true
  }else{
    emailCodeDisable.value=true
    return false
  }
}
function nameValidate(rule:FormItemRule,value:string):boolean {
  return (value.length>=6 && /\w*/.test(value))
}
function passWordValidate(rule:FormItemRule,value:string):boolean{
  return (value.length>=6 && /\w*/.test(value))
}
//注册规则
const RegisterRules={
  email:[{required:true,validator:emailValidate,message: "请输入正确的邮箱",trigger: ['input','blur']}],
  emailCode:{required:true,message:"请输入邮箱验证码",trigger:"blur"},
  name:{required:true,validator:nameValidate,message:"请输入用户名",trigger:["blur",'input']},
  passWord:{required:true,validator:passWordValidate,message:"请输入合法密码",trigger:"blur"},
  confirmPassWord:[
    {required:true,message:"请再次输入密码",trigger:"blur"},
    {validator:validatePasswordStartWith,message:'两次密码不一致',trigger: 'input'},
    {validator:validatePasswordSame,message: '两次密码不一致',trigger: ['blur','passWord-input']}
  ],
  verifyCode:{required:true,message:"请输入验证码",trigger:"blur"},
}
//发送请求前进行表单验证
const handleValidateButtonClick =(e: MouseEvent)=>{
  e.preventDefault()
  formRegisterRef.value?.validate((errors) => {
    if (!errors) {
      postRegister()
    } else {
      console.log(errors)
      message.error('验证失败,请完成表单')
    }
  })
}

//注册
const postRegister = ():boolean=>{
  formRegister.value.regDate=dayjs()
  formRegister.value.passWord=md5(formRegister.value.passWord)
  axios.post("/user/forget",formRegister.value).then((respon)=>{
    if(respon.data.code===200){
      message.success("重置成功")
      getKaptcha()
      gotoLogin()
      return true
    }else{
      message.error("重置失败"+respon.data?.msg)
      formRegister.value.passWord=''
      formRegister.value.confirmPassWord=''
      getKaptcha()
    }
  }).catch(error=>{console.log(error)
    return false
  })
}

//发送验证码可用
const emailCodeDisable=ref(true)

//倒计时
const countDown=ref(0)

//发送邮箱验证码
const sendEmailCode = () => {

  axios.get("/Email/"+formRegister.value.email).then((respon)=>{
    //禁用发送验证码
    if(respon.data.code===200) {
      message.success("验证码已发往邮箱，请登录查看")
      emailCodeDisable.value = true
      countDown.value = 60
      let time = setInterval(() => {
        countDown.value -= 1
      }, 1000)
      setTimeout(() => {
        window.clearInterval(time)
        emailCodeDisable.value = false
      }, 60000)
    }else {message.error("出现了错误，但是不用担心，这不是你的错")}
  }).catch(error=>{
    console.log(error)
    message.error("出现了错误，但是不用担心，这不是你的错")
  })
}

onMounted(()=>{
  //获取验证码
  getKaptcha()
})
</script>


<style scoped>
.main{
  margin-left: 25vw;
  margin-top: 10vh;
  height: 65vh;
  width: 45vw;
  background-color: #181A21;
}
body{
  height: 800px;
}
/*表单项*/
.formItem{
  position: relative;
  left: 20px;
  width: 416px;
}
/*登录按钮*/
#loginButton{
  position: fixed;
  top: 475px;
  left: 700px;
  width: 267px;
  height: 54px;
  font-size: 18px;
  color: #FFFFFF;
  border-radius: 5px;
  border-color:#06BFFF;
  border-style: solid;
  border-width: 0.5px;
  background-image: radial-gradient(circle at 10px 27px,#08BDFF 10%,#2C73FF 80%);
}
#loginButton:hover{
  background-image: radial-gradient(circle at 10px 27px,#08BDFF 30%,#2C73FF 80%);
  cursor:pointer;
}

/*注册按钮*/
#RegisterButton{
  position: fixed;
  top: 710px;
  left: 680px;
  width: 267px;
  height: 54px;
  font-size: 18px;
  color: #FFFFFF;
  border-radius: 5px;
  border-color:#06BFFF;
  border-style: solid;
  border-width: 0.5px;
  background-image: radial-gradient(circle at 10px 27px,#08BDFF 10%,#2C73FF 80%);
}
#RegisterButton:hover{
  background-image: radial-gradient(circle at 10px 27px,#08BDFF 30%,#2C73FF 80%);
  cursor:pointer;
}

/*找回密码*/
.link{
  position: fixed;
  top: 540px;
  left: 770px;
  color: #AFAFAF;
}
.link:hover{
  color: #BDBEBF;
}
/*加入UGame*/
#register{
  width:150px;
  background-color:#171a21;
  position: fixed;
  top: 500px;
  left: 1110px;
  color: #FFFFFF;
  border-color: #b8b6b4;
  border-style: solid;
  border-width: 0.5px;
  border-radius: 2px;
  font-size: 18px
}
#register:hover{
  cursor:pointer;
}
/*立即登录*/
#login{
  width:150px;
  background-color:#171a21;
  position: fixed;
  top: 550px;
  left: 1110px;
  color: #FFFFFF;
  border-color: #b8b6b4;
  border-style: solid;
  border-width: 0.5px;
  border-radius: 2px;
  font-size: 18px
}
#login:hover{
  cursor:pointer;
}
/*验证码图*/
#kaptchaImg{
  position: relative;
  left: 280px;
  top: -60px;
}
#kaptchaImg:hover{
  cursor:pointer
}

#sendEmailCode{
  position: fixed;
  top: 310px;
  left: 40vw;
  width: 120px;
  height: 30px;
  font-size: 18px;
  color: #FFFFFF;
  border-radius: 5px;
  border-color:#06BFFF;
  border-style: solid;
  border-width: 0.5px;
  background-image: radial-gradient(circle at 10px 27px,#08BDFF 10%,#2C73FF 80%);
}
#sendEmailCode:hover{
  background-image: radial-gradient(circle at 10px 27px,#08BDFF 30%,#2C73FF 80%);
  cursor:pointer;
}
#sendEmailCode:disabled{
  width: 150px;
  background-image: radial-gradient(circle at 10px 27px,#b8b6b4 30%,#b8b6b4 80%);
}
</style>
