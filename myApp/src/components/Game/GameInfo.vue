<template>
  <div>
    <img alt="sss" :src="pic" style="width: 30vw;height: 30vh;float: left">
    <div>
      <a>{{ game?.gameName }}</a>
      <a>{{ game?.introduce }}</a>
      <a>{{ game?.tags }}</a>
    </div>
  </div>

  <div style="float: none;">
    <div>
      <a>
        {{ game?.price }}
      </a>
      <button @click="buy">立即购买</button>
    </div>

    <div>
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
    pic.value = game.value.imgs[0]
  }).catch(error => {
    console.log(error)
  })
})
</script>

<style scoped>

</style>
