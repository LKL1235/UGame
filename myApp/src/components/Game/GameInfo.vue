<template>
  <div class="main">
    <div style="width: 75vw">
      <img alt="sss" :src="game?.imgs[0]" style="margin-left: 25vw;width: 30vw;height: 30vh;float: left">

      <div>
        <div style="color: #FFFFFF;font-size: 24px">{{ game?.gameName }}</div>
        <br>
        <div >{{ game?.introduce }}</div>
        <br>
        <div v-for="tag in game?.tags?.split(',')" style="background-color: #b8b6b4;width: 5vw;margin-left: 5px;text-align: center;float: left">
          <a style="color: #FFFFFF">{{tag}}</a>
        </div>
      </div>

    </div>


  <div style="float: none;margin-top: 10vh">
    <div style="margin-left: 60vw;border-width: 1px;border-style: solid;width: 8vw;" >
      <a style="font-size: 25px;color: #FFFFFF">
        {{"￥" + game?.price }}
      </a>
      <el-button type="success" @click="buy">立即购买</el-button>
    </div>
  </div>

    <div style="width: 50vw;margin-left: 25vw;margin-top: 5vh;color: #FFFFFF;font-size: 23px">
      <a>
        {{ game?.about }}
      </a>
    </div>

</div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import axios from "axios";
import dayjs from "dayjs";
import {useUserStore} from "@/stores/User";

const User = useUserStore()
const route = useRoute()
const router = useRouter()
const game = ref()
const pic = ref()
const picIndex = ref()

const form = ref({
  game_id: "",
  tradeNo: "",
  subject: "",
  totalAmount: "",
  userName: "",
  createTime: "",
})

const buy = () => {
  if (User.$state.user.isLogin) {
    form.value.game_id = game.value.gameId
    form.value.totalAmount = game.value.price
    form.value.userName = User.$state.user.name
    form.value.createTime = dayjs().toString()
    axios.post("/alipay/gamePay", form.value).then((response) => {
      document.write(response.data)
    }).catch(error => console.log(error))
  } else {
    router.push("/login")
  }
}

onMounted(() => {
  axios.get("/game/info/" + route.params.gameId).then((response) => {
    game.value = response.data.data
    // pic.value = game.value.imgs[0]
  }).catch(error => {
    console.log(error)
  })
})
</script>

<style scoped>
.main{
  display: flex;
  flex-direction: column;
}
</style>
