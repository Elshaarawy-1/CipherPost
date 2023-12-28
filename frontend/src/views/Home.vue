<template>
  <div class="dashboard">
    <Topbar @toggle-sidebar="toggleSideBar"/>
    <div class="container">
      <Navigation :sideMinimized="sideMinimized"/>
      <Message @close_msg="toggle_msgShown" v-if="messageShown" :message="msg"/>
      <Messagesview :mail_messages="mail_messages" v-if="!messageShown" @show-mail="toggle_msgShown" @get_messages="requestGetMessages"/>
    </div>
    
  </div>
</template>

<script>
import Topbar from '../components/Topbar.vue'
import Messagesview from '../components/Messagesview.vue'
import Navigation from '../components/Navigation.vue'
import Message from '../components/Message.vue'
import axios from 'axios'

export default {
  name: 'Home',
  components: {
    Navigation,
    Topbar,
    Messagesview,
    Message
  },
  mounted() { 
    this.requestGetMessages()
  },
  data() {
    return{
      sideMinimized: false,
      messageShown: false,
      msg: null,
      mail_messages: [],
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

    async requestGetMessages(page)
      {
        const host = "http://172.20.10.4:8081"
        const folderId = 1; // TODO: Replace with the actual folder ID
        const pageId = page; 

        // const sortField = 'message.timestamp';  // TODO: Get from dropdown
        // const sortDirection = 'desc';           // TODO: direction sort 
        // const searchField = '';                 // TODO: [[criteria]]This is in search bar only
        // const keyword = '';                     // TODO: search input
        const url = host+`/api/messages/get/${folderId}?page=${pageId-1}`;
        console.log(url)
        console.log('getting messages..');
        axios.get(url)
          .then(response => {
            // Handle the response data
            console.log(response);
            this.mail_messages=response.data;
            console.log('Received messages:', this.mail_messages);
            
          })
          .catch(error => {
            // Handle errors
            console.error('Error fetching messages:', error);
          });
          console.log('out')
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