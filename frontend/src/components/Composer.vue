<template>
    <div class="backdrop" @click.self="close">
      <div class="composer">
          <input v-model="recipients" type="text" class="message_attributes" placeholder="Recipients">
          <input v-model="subject" type="text" class="message_attributes" placeholder="Subject">
          <textarea v-model="mail_message" class="message" @keydown.tab.prevent="handleTab" spellcheck="false" placeholder="Type your mail message ..."></textarea>
          <div class="bottom">
              <div class="left">
                  <button class="send" title="Send" @click="send"><font-awesome-icon icon="paper-plane" class="icon"/></button>
                  <label for="file-input" class="file-label">
                      <font-awesome-icon icon="paperclip" class="icon" title="Insert Attachement"/>
                      <input type="file" id="file-input" @change="handleFileChange">
                  </label>
                  <button v-if="attachements && attachements.length>0" class="send" title="List Attachements" @click.stop="toggleDropDown"><font-awesome-icon icon="angle-up" class="smaller-icon"/></button>
                  <div class="dropdown_container" @click.stop>
                      <div v-if="isDropdown && attachements.length>0" class="dropdown" ref="dropdown">
                          <ul>
                              <li v-for="(attachement, index) in attachements" :key="index">{{ attachement.name.substring(0,20) }}<button @click="remove_attachement(attachement, index)"><font-awesome-icon icon="minus"/></button></li>
                          </ul>
                      </div>
                  </div>
              </div>
              <button class="send" title="Discard Draft" @click="discard"><font-awesome-icon icon="trash" class="icon"/></button>
          </div>
      </div>
    </div>
  </template>
  
  <script>
  import MessageDTO from '../../src/MessageDTO';
  import User from '../../src/User';
  import Attachment from '../../src/Attachement';
  import axios from 'axios';
  export default {
      data(){
          return{
              mail_message: "",
              attachements: [],
              att_count: 0,
              isDropdown: false,
              recipients: "",
              msg_subject: "",
              msg_priority: 1,// TODO: change this to v-model like the rest
          }
      },
      mounted() {
          document.addEventListener("click", this.closeDropdown);
      },
      beforeDestroy() {
          document.removeEventListener("click", this.closeDropdown);
      },
      methods: {
          close(){
              this.$emit('close_composer');
          },
          handleTab(event){
              const textarea = event.target;
              const start = textarea.selectionStart;
              const end = textarea.selectionEnd;
              
              this.mail_message = this.mail_message.substring(0, start) + "\t" + this.mail_message.substring(end);
  
              textarea.setSelectionRange(start + 1, start + 1);
          },
          handleFileChange(event){
              const file= event.target.files[0];
              
              let file_name = file.name; // Save the filename with extension
               // Extract file extension
              let fileExtension = file_name.split('.').pop(); // Get the last part after the last dot
              const reader = new FileReader();
              reader.readAsDataURL(file);
              reader.onload = () => {
                  base64String = reader.result.split(',')[1];
                  fileExtension = fileExtension.toLowerCase(); // Save the extension in a variable
                  this.attachements.push(new Attachment(file_name,fileExtension,base64String));
                  this.att_count++;            
              };
  
          },
          formatFileSize(size){
              const kbSize = size/1024;
              if(kbSize<1024) {
                  return kbSize.toFixed(2) + 'KB';
              } else {
                  return (kbSize / 1024).toFixed(2) + 'MB';
              }
          },
  
          toggleDropDown(){
              this.isDropdown = !this.isDropdown;
          },
          closeDropdown(event) {
              const dropdownElement = this.$refs.dropdown;
  
              if (dropdownElement && !dropdownElement.contains(event.target)) {
                  this.isDropdown = false;
              }
          },
          remove_attachement(attachement, index){
              console.log(attachement)
              this.attachements.splice(index, 1)
          },
          send() {
              // TODO: send message
              let sender_id = 1//TODO: change this to actual user id
              let sender_username = "duncan.runolfsdottir"//TODO:change this to actual username
              let sender = new User(sender_username,sender_id);
          
              let recipients_arr = this.recipients.split(' ');
              if (recipients_arr.length === 1 && recipients_arr[0]===""){
                  console.log('ERROR! No recipients.')
                  return;
              }
              let msg_dto = new MessageDTO(0,sender,recipients_arr, Date.now(),this.msg_priority,this.subject,this.mail_message,this.attachements,null); 
              this.requestSendMessage(msg_dto);  
          },
          async requestSendMessage(messageDTO){
              const url = 'http://172.20.10.4:8081/api/messages/send'; // Replace with your actual API endpoint
              console.log(messageDTO);
              try {
              const response = await axios.post(url, messageDTO);
  
              console.log('Message sent successfully:', response.data);
              this.$emit('close_composer')
  
              return response.data;
              } catch (error) {
                  console.error('Error sending message:', error.message);
              throw error;
              }
  
          },
          discard(){
              // TODO: differentiate between discard message(inside box event) and save to draft(outside event)
              this.$emit('close_composer')
          }
      }
  }
  </script>
  
  <style>
  .backdrop {
      top: 0;
      position: fixed;
      background: rgba(0,0,0,0.6);
      width: 100%;
      height: 100%;
  }
  
  .composer {
      width: 40%;
      padding: 20px 20px 30px 20px;
      margin: 150px auto;
      background: #d6edfa;
      height: 55%;
      border-radius: 10px;
  }
  .composer .message_attributes{
      width: 100%;
      margin-bottom: 5px;
      border: none;
      border-bottom: 1px solid rgba(146, 146, 146, 0.8);
      background: none;
  }
  
  .composer .message{
      width:100%;
      height: 82%;
      margin-top: 15px;
      margin-bottom: 0.5%;
      text-align: left;
      background: none;
      border: none;
      border-bottom: 1px solid rgba(146, 146, 146, 0.8);
      border-left: 1px solid rgba(146, 146, 146, 0.8);
      resize: none;
      overflow-y: auto;
  }
  
  .composer .bottom{
      display: flex;
      justify-content: space-between;
  }
  .composer .left{
      display: flex;
  }
  .composer .send{
      margin-left: 0;
      height: 40px;
      width: 40px;
      background: none;
      border: none;
      cursor: pointer;
      color: #023047;
  }
  
  .composer .send:hover{
      border-radius: 50%;
      background-color: #FB8500
  }
  .composer .icon{
      font-size: 20px;
  }
  
  .composer .file-label{
      padding: 10px 12px;
      background: none;
      border: none;
      cursor: pointer;
      color: #023047;
  }
  .composer .file-label:hover{
      border-radius: 50%;
      background-color: #FB8500
  }
  .composer .smaller-icon{
      font-size: 16px;
      color: #023047;
  }
  .composer .bottom input[type="file"]{
      display :none;
  }
  
  .composer .dropdown_container{
      position: relative;
      right: 16%;
      bottom: 95%
  }
  .composer .dropdown_container .dropdown{
      background: #f7e8c7;
      border: 1px solid #023047;
      border-radius: 3%;
      padding: 0;
      width: 180px;
  }
  .dropdown ul{
      padding-left: 10%;
      list-style: none;
      color: #023047;
  }
  .dropdown ul li{
      display: flex;
      justify-content: space-between;
      font-size: 12px;
      text-align: left;
  }
  .dropdown ul li button{
      background: none;
      border: none;
      cursor: pointer;
  }
  </style>