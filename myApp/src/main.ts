import { createApp } from 'vue'
import naive from 'naive-ui'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'
import axios from "axios";
// @ts-ignore
import VueAxios from "vue-axios";

axios.defaults.baseURL='/api'
axios.defaults.withCredentials = true

const pinia = createPinia()
const app = createApp(App)


app.use(naive)
app.use(ElementPlus)
app.use(router)
app.use(pinia)
app.use(VueAxios, axios)
app.mount('#app')
