import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createPinia } from 'pinia'
import '@/assets/style/init.less'


const app = createApp(App)

app
  .use(createPinia())
  .use(ElementPlus)
  .use(router)
  .mount('#app')
