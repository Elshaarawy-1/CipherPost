<template>
    <div class="mail">
        <div class="top_bar">
            <div class="left">
                <button @click="back" title="Back"><font-awesome-icon icon="arrow-left" class="icon"/></button>
                <button @click="remove_to_trash" title="Move To Trash"><font-awesome-icon icon="trash-can" class="icon"/></button>
                <button @click="move" title="Move To Folder"><font-awesome-icon icon="folder" class="icon"/></button>
            </div>
            <div class="pages">
                <button @click="decrement_page" title="Back"><font-awesome-icon icon="caret-left" class="icon"/></button>
                <span>{{ message_count }} / {{ total_messages }}</span>
                <button @click="increment_page" title="Forward"><font-awesome-icon icon="caret-right" class="icon"/></button>
            </div>
        </div>
        <div class="message">
            <div class="subject">
                {{ message.subject }}
            </div>
            <div class="to">
                To: 
                <div class="display"  v-for="person in message.to" :key="person">
                    {{ person }}
                </div>
            </div>
            <div class="from">
                From: {{ message.from }}
                <div v-if="message.cc !== null">
                    CC: 
                    <div class="display"  v-for="person in message.cc" :key="person">
                        {{ person }}
                    </div>
                </div>
            </div>
            <div class="content">
                {{ message.text }}
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['message'],
    data(){
        return{
            message_count: 1,
            total_messages: 100
        }
    },
    methods: {
        back(){
            this.$emit('close_msg', this.msg)
        }
    }
}
</script>

<style>
  .mail {
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
  .mail .top_bar {
    background-color: #86b0c7;
    color: #023047;
    padding: 0.25% 1.5% 0.25% 0.5%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
  }

  .mail .top_bar .left{
    display: flex;
  }
  .mail .top_bar .left button{
    background: none;
    cursor: pointer;
    border: none;
    padding: 8%;
    margin: 1% 10%;
  }

  .mail .top_bar .left button:hover{
    border-radius: 50%;
    background: #FB8500
  }

  .mail .top_bar .icon{
    font-size: 18px;
    color: #023047;
  }

  .mail .top_bar .pages{
    background: rgb(198, 237, 248);
    padding: 0;
    display: flex;
    width: 8%;
    height: auto;
    border-radius: 20px;
    text-align: center;
  }

  .mail .top_bar .pages button{
    background: none;
    cursor: pointer;
    border: none;
    margin: 0 6%;
  }

  .mail .top_bar .pages button:hover{
    border-radius: 50%;
    background: #FB8500
  }
  .mail .top_bar .pages span{
      margin-top: 3%;
      width: 100%;
  }
</style>