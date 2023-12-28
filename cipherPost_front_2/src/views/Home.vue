<template>
  <div class="dashboard">
    <Topbar @toggle-sidebar="toggleSideBar"/>
    <div class="container">
      <Navigation :sideMinimized="sideMinimized"/>
      <Message @close_msg="toggle_msgShown" v-if="messageShown" :message="msg"/>
      <Messagesview v-if="!messageShown" @show-mail="toggle_msgShown"/>
    </div>
    
  </div>
</template>

<script>
import Topbar from '../components/Topbar.vue'
import Messagesview from '../components/Messagesview.vue'
import Navigation from '../components/Navigation.vue'
import Message from '../components/Message.vue'

export default {
  name: 'Home',
  components: {
    Navigation,
    Topbar,
    Messagesview,
    Message
  },
  data() {
    return{
      sideMinimized: false,
      messageShown: false,
      msg: null
    }
  },
  methods: {
    toggleSideBar(){
      this.sideMinimized = !this.sideMinimized
    },
    toggle_msgShown(msg){
      this.msg = msg
      this.messageShown = !this.messageShown
    },

  },
}
</script>

<style>
.dashboard {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.container {
  display: flex;
  flex: 1;
}
</style>