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
export default {
    data(){
        return{
            mail_message: "",
            attachements: [],
            att_count: 0,
            isDropdown: false,
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
            this.attachements.push(file);
            this.att_count++;
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

        },
        discard(){

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