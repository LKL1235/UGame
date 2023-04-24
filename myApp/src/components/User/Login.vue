<template >
<!--  全局背景-->
  <div style="background-color: #181A21;height: 833px">
<!--    背景图-->
    <img src="/background.jpg" style="position: relative;left:200px;z-index: 1;height: 826px">
<!--    登录框-->
    <a style="color: #FFFFFF;position: absolute;top: 100px;left: 620px;font-size: 40px;z-index: 2">{{isRegister?"注册":"登录"}}</a>

    <div id="main" class="main">

<!--   登录-->
      <div v-if="!isRegister" >
      <n-form
        :model="form"
        ref="formRef"
        :rules="formRules"
      >
<!--  账号-->
        <n-form-item path="name" label="用户名" label-style="color: #1999ff;" class="formItem" style="top:20px" >
          <n-input v-model:value="form.name" placeholder="">

          </n-input>
        </n-form-item>
<!--密码-->
        <n-form-item path="passWord" label="密码" class="formItem" style="top:10px">
          <n-input v-model:value="form.passWord" type="password" show-password-on="click" placeholder="">

          </n-input>
        </n-form-item>
<!--验证码-->
        <n-form-item path="verifyCode" label="验证码" class="formItem">
          <n-input v-model:value="form.verifyCode" maxlength="4" placeholder="" style="width: 200px">

          </n-input>
        </n-form-item>
        <img :src="kaptcha" @click="getKaptcha" id="kaptchaImg">
<!-- 记住我-->
          <n-checkbox v-model:checked="form.remember" style="position: fixed;left: 626px;top: 434px" >
            记住我
          </n-checkbox>
<!--登录-->
        <button id="loginButton" @click="handleValidateLoginButtonClick">登录</button>
        <router-link to="forget" class="link">请求帮助，我无法登录</router-link>
      </n-form>
      <img src="../../../public/join_pc.png" style="width: 200px;position: fixed;left: 1070px;top: 210px;">
      <a style="width:200px;white-space:pre-line ;color: #BDBEBF;position: fixed;top: 360px;left: 1100px;font-size: 28px">加入 UGame，</a>
      <a style="width:200px;white-space:pre-line ;color: #BDBEBF;position: fixed;top: 400px;left: 1090px;font-size: 28px">探索数千款精彩游戏。</a>
      <button id="register" @click="gotoRegister">加入UGame</button>
      </div>

<!-- 注册-->
      <div v-if="isRegister">
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
          <n-form-item path="name" label="用户名(不可更改的登录凭证)" label-style="color: #1999ff;" class="formItem"  >
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
          <n-form-item path="passWord" label="密码" class="formItem">
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
          <n-button id="RegisterButton" @click="handleValidateButtonClick">注册</n-button>
        </n-form>
        <img src="/join_pc.png" style="width: 200px;position: fixed;left: 1070px;top: 210px;">
        <a style="width:200px;white-space:pre-line ;color: #BDBEBF;position: fixed;top: 380px;left: 1100px;font-size: 28px;z-index: 3">已有UGame账户？</a>
        <a style="width:200px;white-space:pre-line ;color: #BDBEBF;position: fixed;top: 480px;left: 1120px;font-size: 28px;z-index: 3">立即登录！</a>
        <button id="login" @click="gotoLogin">登录UGame</button>

      </div>
    </div>
  </div>

</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import dayjs, {Dayjs} from 'dayjs'
import { useMessage} from "naive-ui";
import type {FormInst, FormItemRule} from "naive-ui/lib";
import md5 from 'js-md5';
import {HelpCircleOutline} from "@vicons/ionicons5"
import {useUserStore} from "@/stores/User";
import {useRouter} from "vue-router";

const router=useRouter()
const store=useUserStore()
//使用message
const message=useMessage()
const day=dayjs().format("YYYY-MM-DD HH:mm:ss")
//是否为注册页面
const isRegister=ref(false)

//验证码
const kaptcha=ref()

const formRef=ref<FormInst|null>(null)
//登录表单
const form=ref({
  name:"",
  passWord:"",
  verifyCode:"",
  latestDate:"",
  //是否记住
  remember:false,
})
//登录表单规则
const formRules= {
  name:{required:true,validator:nameValidate,message:"请输入正确的用户名",trigger:['blur','input']},
  passWord:{required:true,validator:passWordValidate,message:"请输入正确的密码",trigger:['blur','input']},
  verifyCode:{required:true,message:"请输入验证码",trigger:['blur','input']},
}



const handleValidateLoginButtonClick =(e: MouseEvent)=>{
  e.preventDefault()
  formRef.value?.validate((errors) => {
    if (!errors) {
      postLogin()
    } else {
      console.log(errors)
      message.error('验证失败,请完成表单')
    }
  })
}

//获取验证码
const getKaptcha = () => {
  axios.get("/Kaptcha").then((respon)=>{
    kaptcha.value=respon.data.data
  }).catch(error=>{console.log(error)})
}

//登录
const postLogin = () => {
  form.value.latestDate=dayjs()
  form.value.passWord=md5(form.value.passWord)
  axios.post("/login",form.value).then((respon)=>{
    if(respon.data.code===200){
      message.success("登录成功")
      store.$state.user={...respon.data.data}
      store.$state.user.isLogin=true
      router.push("/")
    }else {
      message.error(respon.data.msg)
      getKaptcha()
      form.value.passWord=''
      form.value.verifyCode=''
    }
  }).catch(error=>console.log(error))
}

//切换到注册页面
const gotoRegister =  () => {
  Object.keys(formRegister.value).forEach(key => (formRegister.value[key] = ''))
  Object.keys(form.value).forEach(key=>(form.value[key] = ''))
  getKaptcha()
  setTimeout( ()=>{isRegister.value=true},300)
    document.getElementById("main").style.height="600px"
}
//切换到登录页面
const gotoLogin = () => {
  Object.keys(formRegister.value).forEach(key => (formRegister.value[key] = ''))
  Object.keys(form.value).forEach(key=>(form.value[key] = ''))
  getKaptcha()
  setTimeout( ()=>{isRegister.value=false},150)
    document.getElementById("main").style.height="380px"
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
  axios.post("/user/register",formRegister.value).then((respon)=>{
    if(respon.data.code===200){
      message.success("注册成功")
      getKaptcha()
      gotoLogin()
      return true
    }else{
      message.error("注册失败"+respon.data?.msg)
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

.main{
  width: 700px;
  height: 380px;
  background-color: #181A21;
  position: absolute;
  left: 607px;
  top: 200px;
  z-index: 2;
  transition: height 600ms;
}
#sendEmailCode{
  position: fixed;
  top: 310px;
  left: 900px;
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
