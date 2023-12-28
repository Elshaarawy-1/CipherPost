<template>
    <div class="mail">
        <div class="top_bar">
            <div class="left">
                <button @click="back" title="Back"><font-awesome-icon icon="arrow-left" class="icon"/></button>
                <button @click="remove_to_trash" title="Move To Trash"><font-awesome-icon icon="trash-can" class="icon"/></button>
                <button @click="move" title="Move To Folder"><font-awesome-icon icon="folder" class="icon"/></button>
            </div>
        </div>
        <div class="message">
            <div class="subject">
                <strong>{{ message.subject }}</strong>
            </div>
            <div class="to">
                TO: 
                <em class="display"  v-for="person in message.to" :key="person">
                    {{ person }}
                </em>
            </div>
            <div class="from">
                FROM:
                <em class="display">{{ message.from }}</em>
                <div v-if="message.cc !== null">
                    CC: 
                    <em class="display"  v-for="person in message.cc" :key="person">
                        {{ person }}
                    </em>
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
        },
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
    height: auto;
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
  .mail .message{
    padding: 1%;
    text-align: left;
    max-height: 550px;
  }
  .mail .message .subject{
    padding: 1%;
    font-size: 28px; 
    font-weight: bolder;
  }

  .mail .message .to{
    padding: 1% 2% 0.5% 2%;
    font-size: 16px;
  }
  .mail .message .to .display{
    padding: 0.1% 0.7%;
    font-size: 22px;
    font-style: normal;
    font-weight: bold;
  }
  .mail .message .from{
    padding: 1% 2% 0.5% 2%;
    font-size: 16px;
  }
  .mail .message .from .display{
    padding: 1% 0.7%;
    font-size: 22px;
    font-style: normal;
    font-weight: bold;
  }
  .mail .message .from div{
    padding: 1.5% 0;
  }
  .mail .message div.content{
    font-size: 18px;
    line-height: 28px;
    background: rgb(241, 215, 181);
    margin: 1% 25%;
    overflow-y: auto;
    border-left: 2px solid #219ebc;
    border-bottom: 2px solid #219ebc;
    border-bottom-left-radius: 20px;
    max-height: 500px;
    padding: 1% 0.5%;
    box-shadow: -10px 0 10px -10px rgba(0, 0, 0, 0.5), 0 10px 10px -10px rgba(0, 0, 0, 0.5);
  }
</style>