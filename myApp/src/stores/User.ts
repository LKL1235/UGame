import {computed, ref} from 'vue'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', () => {
  // const count = ref(0)
  // const doubleCount = computed(() => count.value * 2)
  // function increment() {
  //   count.value++
  // }
  const user=ref({
    isLogin:false,
    name:"",
    nickName:null,
    email:"",
    avatar:"",
    balance:0,
    regDate:"",
    latestDate:"",
    isRoot:false
  })
  return { user }
})
