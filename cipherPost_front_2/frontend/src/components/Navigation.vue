<template>
  <div class="sidebar" :class="{'minimized': sideMinimized}">
    <router-link :to="{ name:'Contacts' }"><font-awesome-icon icon="address-book" class="icon"/><em class="text">Contacts</em></router-link>
    <div class="horizontal_line"></div>
    <router-link :to="{ name:'Home' }"><font-awesome-icon icon="envelopes-bulk" class="icon"/><em class="text">Inbox</em></router-link>
    <span @click="showTrash"><font-awesome-icon icon="trash-can" class="icon"/><em class="text">Trash</em></span>
    <span @click="showDraft"><font-awesome-icon icon="file" class="icon"/><em class="text">Draft</em></span>
    <span @click="showSpam"><font-awesome-icon icon="bullhorn" class="icon"/><em class="text">Spam</em></span>
    <span @click="showSent"><font-awesome-icon icon="paper-plane" class="icon"/><em class="text">Sent</em></span>
    <span @click="showFolders"><font-awesome-icon icon="book" class="icon"/><em class="text">Folders</em></span>
  </div>
  <router-view/>
</template>

<script>
export default{
  props: ['sideMinimized'],
  methods: {
    showDraft(){
      console.log("Draft")
    },
    showSpam(){
      console.log("Spam")
    },
    showTrash(){
      console.log("Trash")
    },
    showSent(){
      console.log("Sent")
    },
    showFolders(){
      console.log("Folders")
    },
  },
}
</script>

<style>
.sidebar {
  height: 100%;
  width: 15%;
  background-color: #8ECAE6;
  color: #fff;
  padding-right:10px;
  box-sizing: border-box;
  text-align: left;
}

.sidebar.minimized {
  width: 50px;
  margin-right: 0.5%;
}

.sidebar em.text{
  font-style: normal;
}

.sidebar.minimized em.text {
  display: None;
}

.sidebar a, .sidebar span{
  display: flex;
  color: #023047;
  text-decoration: none;
  margin: 7px 0px 18px 0;
  padding: 10px 50px 10px 15px;
  font-weight: bold;
  font-size: larger;
  cursor: pointer;
}

.sidebar.minimized a, .sidebar.minimized span{
  padding-right: 80%;

}
.sidebar .icon{
  margin-right: 10px; 
}
.sidebar a:hover, .sidebar span:hover {
  border-top: 2px solid rgba(251,133,0);
  border-right: 2px solid rgba(251,133,0);
  border-bottom: 2px solid rgba(251,133,0); 
  border-top-right-radius: 15px;
  border-bottom-right-radius: 15px;
  color: rgba(251,133,0);
  font-weight: bolder;
}

.horizontal_line{
  height: 1px;
  width: 70%;
  background-color: #219EBC;
  margin: 0 10px;
}
.sidebar.minimized .horizontal_line{
  width: 30px;
}

</style>
