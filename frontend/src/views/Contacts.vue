<template>
<div class="contacts-page">
    <Topbar @toggle-sidebar="toggleSideBar"/>
    <div class="container">
      <Navigation :sideMinimized="sideMinimized"/>
      <div class="contacts-table">
        <div :class="{'table-container': !sideMinimized}">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Emails</th>
              <th>Message</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="contact in contacts" :key="contact">
              <td>{{ contact.id }}</td>
              <td>{{ contact.name }}</td>
              <td v-for="email in contact.emails" :key="email">{{ email }}</td>
              <td><button @click="send_message(contact.id)"><font-awesome-icon icon="envelope" class="icon"/></button></td>
              <td><button @click="delete_contact(contact.id)"><font-awesome-icon icon="user-xmark" class="icon"/></button></td>
            </tr>
          </tbody>
        </table>
      </div>
      </div>
    </div>
  </div>
  <Composer v-if="isComposing" @close_composer="toggleCompose"/>
</template>

<script>
import Navigation from '../components/Navigation.vue'
import Topbar from '../components/Topbar.vue'
import Composer from '../components/Composer.vue'

export default{
  components: {Navigation, Topbar, Composer},
  data() {
    return{
      sideMinimized: false,
      isComposing: false,
      sendTo: "",
      contacts: [
        { id: 1, name: 'John Doe', emails: ['john@example.com'] },
        { id: 2, name: 'Jane Doe', emails: ['jane@example.com'] },
        { id: 3, name: 'John Doe', emails: ['john@example.com'] },
        { id: 4, name: 'Jane Doe', emails: ['jane@example.com'] },
        { id: 5, name: 'John Doe', emails: ['john@example.com'] },
        { id: 6, name: 'Jane Doe', emails: ['jane@example.com'] },
        { id: 7, name: 'John Doe', emails: ['john@example.com'] },
        { id: 8, name: 'Jane Doe', emails: ['jane@example.com'] },
        { id: 9, name: 'John Doe', emails: ['john@example.com'] },
        { id: 10, name: 'Jane Doe', emails: ['jane@example.com'] },
        { id: 11, name: 'John Doe', emails: ['john@example.com'] },
      ],
    }
  },
  methods: {
    toggleSideBar(){
      this.sideMinimized = !this.sideMinimized
    },
    toggleCompose(){
      this.isComposing = !this.isComposing
    },
    delete_contact(contactId){
      this.contacts.splice(this.contacts.findIndex(item => item.id === contactId), 1)
    },
    send_message(contactId){
      this.toggleCompose();
      const index = this.contacts.findIndex(item => item.id === contactId)
      this.send_to = this.contacts[index].name
    }
  }
}
</script>
<style>
.contacts-page {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.container {
  display: flex;
  flex: 1;
}

.contacts-table{
  flex: 1;
  box-sizing: border-box;
  background: #c8e1ee;
  margin-top: 0.1%;
  margin-right: 1.5%;
  margin-bottom: 0;
  padding: 2%;
  border-top-left-radius: 20px;
  border-top-right-radius: 20px;
}
.table-container{
  max-height: 800px;
  width: 1500px;
  overflow-y: auto;
}
table {
  overflow-y: auto;
  border: 1px solid #023047;
  width: 100%;
  border-collapse: collapse;
  overflow-y: auto;
  background: rgb(241, 215, 181);
}

th{
  border-bottom: 1px solid #023047;
  padding: 8px;
  text-align: center  ;
}
td {
  border-top: 1px solid #023047;
  border-bottom: 1px solid #023047;
  padding: 8px;
  text-align: center  ;
}

th {
  background-color: rgb(241, 215, 181);
}
.contacts-page .contacts-table button{
  background: none;
  cursor: pointer;
  border: none;
  padding: 3%;
}
.contacts-page .contacts-table button:hover{
  border-radius: 50%;
  background: #FB8500
}
.contacts-page .contacts-table button .icon{
  font-size: 18px;
  color: #023047;
}
</style>