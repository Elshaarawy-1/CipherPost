<template>
    <div class="message_container">
        <div class="message_summary" @click="show_message">
            <div class="top">
                <p class="subject">{{ subject }}</p>
                <input type="checkbox" class="select-checkbox" @click.stop :checked="isSelected" @change="toggleSelection">
            </div>
            <div class="text">
                <slot></slot>
            </div>
            <p class="date">{{ date }}</p>
        </div>
        <p class="from">            
            <em>Sender</em> {{ from }}
        </p>
    </div>
</template>

<script>
export default {
    props: ['from', 'subject', 'date', 'id', 'isSelected'],
    data(){
        return{
        }
    },
    methods: {
        toggleSelection() {
            this.$emit('update:selected', this.id);
        },
        show_message(){
            this.$emit('show_mail', this.id)
        }
    },
}
</script>

<style>
.message_summary{
    width: 100%;
    height: 80%;
    text-align: left;
    box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.2);
    text-align: left;
    border-radius: 18px;
    background: rgb(241, 215, 181);
    padding: 0.5% 0.4% 0.5% 0.3%;
    color: #023047;
}
.message_summary:hover{
    box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
    border: 1px solid #219EBC;
    cursor: pointer;
}
.message_summary .top{
    display: flex;
    justify-content: space-between;
    padding: 0.5% 0.5% 0 1%;
}
.message_summary .subject{
    width: 80%;
    font-size: 22px;
    font-weight: bolder;
    padding: 0.5% 0 0 1%; 
    margin: 1% 1%; 
}
.message_summary .text{
    font-size: 18px;
    padding: 0 1%;
    margin: 0 3%;
    border-top: 1px solid rgba(2, 48, 71, 0.2);
}
.message_container .from{
    margin-top: 2%;
    font-size: 18px;
    width: 90%;
    font-weight: bolder;    
    text-align: left;
    padding-left: 4%;
}
.message_container .from em{
    font-size: 14px;
    font-weight: lighter;
    font-style: normal;
    padding-right: 2%
}
.message_summary .date{
    font-size: 14px;
    font-weight: lighter;
    text-align: right;
    margin-right: 3%;
    margin-top: 1%;
    margin-bottom: 0%
}
.select-checkbox {
    width: 20px;
    height: 20px;
    border-radius: 50%;
    appearance: none;
    background-color: #fff;
    border: 2px solid #3d6274;
    cursor: pointer;
    margin-right: 3%;
    margin-top: 1%;
}

.select-checkbox:checked {
    background-color: #fb8500;
}
</style>