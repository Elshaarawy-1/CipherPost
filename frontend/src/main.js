import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { VueCookies } from 'vue-cookies'

import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fas } from '@fortawesome/free-solid-svg-icons';


library.add(fas)

createApp(App)
.component('font-awesome-icon', FontAwesomeIcon)
.use(router)
.use(VueCookies)
.mount('#app')
