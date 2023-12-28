<template>
  <div class="message_view">
    <div class="option_bar">
      <div class="left">
        <button @click="refresh" title="Refresh"><font-awesome-icon icon="rotate-right" class="icon"/></button>
        <button @click="select_all" title="Select All"><font-awesome-icon icon="square-check" class="icon"/></button>
        <button @click="sort" title="Sort"><font-awesome-icon icon="arrow-up-wide-short" class="icon"/></button>
        <button @click="remove_to_trash" title="Move To Trash"><font-awesome-icon icon="trash-can" class="icon"/></button>
        <button @click="move" title="Move To Folder "><font-awesome-icon icon="folder" class="icon"/></button>
        <div class="dropdown_container">
          <div v-if="isMoving" class="dropdown" ref="dropdown">
            <input type="text" v-model="move_to" placeholder="Enter Destination Folder" @keyup.enter="move_to_folder"/>
          </div>
      </div>
      </div>
      <div class="pages">
        <button @click="decrement_page" title="Back"><font-awesome-icon icon="caret-left" class="icon"/></button>
        <span>{{ page }} / {{ total_pages }}</span>
        <button @click="increment_page" title="Forward"><font-awesome-icon icon="caret-right" class="icon"/></button>
      </div>
    </div>
    <div class="grid-container">
      <MessageSum v-for="message in mail_messages" :key="message" @show_mail="show_mail" :from="message.sender.username" :subject="message.subject.substring(0, 32)" :date="message.time" :id="message.id" :is-selected="isSelected(message.id)" :priority="message.msg_priority" @update:selected="updateSelectedItems">
        {{ message.content.substring(0, 100) }}
      </MessageSum>
    </div>
    <button class="bottom-right-button" @click="toggleCompose"><font-awesome-icon icon="feather" /></button>
  </div>
  <Composer v-if="isComposing" @close_composer="toggleCompose"/>
</template>

<script>
  import Composer from './Composer.vue'
  import MessageSum from './MessageSum.vue'
  import axios from 'axios'
  export default{
    data(){
      return{
        isComposing: false,
        isMoving: false,
        page: 1,
        move_to:"",
        total_pages: 10,
        mail_messages: [],
        selected: [],
      }
    },
    components: {
      Composer,
      MessageSum
    },
    methods: {
      toggleCompose(){
        this.isComposing = !this.isComposing
      },
      increment_page(){
        if(this.page < this.total_pages){
          this.page++
        }
        this.requestGetMessages();          
      },
      decrement_page(){
        if(this.page>1){
          this.page--;
        }
        this.requestGetMessages();          
      },
      async requestGetMessages()
      {
        const host = "http://localhost:8081"
        const folderId = 1; // TODO: Replace with the actual folder ID
        const pageId = this.page; 

        // const sortField = 'message.timestamp';  // TODO: Get from dropdown
        // const sortDirection = 'desc';           // TODO: direction sort 
        // const searchField = '';                 // TODO: [[criteria]]This is in search bar only
        // const keyword = '';                     // TODO: search input
        const url = host+`/api/messages/get/${folderId}?page=${pageId}`;
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
      isSelected(id){
        return this.selected.includes(id)
      },
      updateSelectedItems(selectedId){
        if(this.isSelected(selectedId)){
          this.selected = this.selected.filter(id => id !== selectedId)
        } else{
          this.selected.push(selectedId)
        }
        console.log(this.selected)
      },
      show_mail(mail_id){
        const required_mail = this.mail_messages.find(item => item.id === mail_id)
        this.$emit('show-mail', required_mail)
      },
      move(){
        this.isMoving = !this.isMoving
      },
      move_to_folder(){
        this.isMoving = !this.isMoving
        console.log(this.move_to)

        //To Do

        this.move_to = ""
      },
      remove_to_trash(){
        // TODO: to delete selected messages
        if(!this.selected || this.selected.length > 0) 
          this.requestDeleteMessages(this.selected)
      },
      requestDeleteMessages(selected_msgs) {
        const folderId = 1; // TODO: Replace with the actual folder ID
        const url = `http://localhost:8081/api/messages/delete/${folderId}?msg_ids=${selected_msgs.join(',')}`;
        console.log(selected_msgs)
        console.log(url)
        axios.delete(url)
        .then(response => {
          // Handle successful deletion
          console.log('Messages deleted successfully:', response);
          this.refresh();
        })
        .catch(error => {
          // Handle deletion error
          console.error('Error deleting messages:', error);
        });
      },
      refresh(){
        this.requestGetMessages();
      },
    },
}
</script>

<style>
.message_view {
  flex: 1;
  padding: 0;
  box-sizing: border-box;
  background: #c8e1ee;
  margin-top: 0.1%;
  margin-right: 1.5%;
  margin-bottom: 0;
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
}
.message_view .bottom-right-button {
  position: fixed;
  bottom: 4%;
  right: 3%;
  padding: 25px;
  background-color: transparent;
  color: #023047;
  border: none;
  border-radius: 50%;
  cursor: pointer;
  font-size: 25px;
}

.message_view .bottom-right-button:hover{
  background-color: #FB8500;
}


.message_view .option_bar {
  background-color: #86b0c7;
  color: #023047;
  padding: 0.25% 1.5% 0.25% 0.5%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
}

.message_view .option_bar .left{
  display: flex;
 
}

.message_view .option_bar .left button{
  background: none;
  cursor: pointer;
  border: none;
  padding: 8%;
  margin: 0 6%;
}
.message_view .option_bar .left button:hover{
  border-radius: 50%;
  background: #FB8500
}
.message_view .option_bar .icon{
  font-size: 18px;
  color: #023047;
}
.message_view .option_bar .pages{
  background: rgb(198, 237, 248);
  padding: 0;
  display: flex;
  width: 8%;
  height: auto;
  border-radius: 20px;
  text-align: center;
}

.message_view .option_bar .pages button{
  background: none;
  cursor: pointer;
  border: none;
  margin: 0 6%;
}

.message_view .option_bar .pages button:hover{
  border-radius: 50%;
  background: #FB8500
}
.message_view .option_bar .pages span{
    margin-top: 3%;
    width: 80%;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-gap: 2%;
  padding: 1% 1%;
}

.grid-item {
  width: 100%;
  height: 10%;
  background-color: #3498db;
  color: #fff;
  text-align: center;
  line-height: 50px;
  font-family: Arial, sans-serif;
}

.option_bar .dropdown_container{
  position: absolute;
  top: 12%;
  left: 25%;
}
.option_bar .dropdown_container .dropdown input{
height: 30px;
width: auto;
font-size: 16px;

}
</style>
