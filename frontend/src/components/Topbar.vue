<template>
  <div class="topbar">
    <button class="icons" @click="handleSideBar"><font-awesome-icon icon="bars"/></button>
    <div class="search_container">
        <input type="text" class="search" placeholder="Search Box">
        <button class="search_button" @click.stop="filter"><font-awesome-icon icon="filter" /></button>
        <div class="dropdown_container">
            <div v-if="isDropdown" class="dropdown" ref="dropdown">
                <ul>
                    <li><span @click="filter('subject')">Subject</span></li>
                    <li><span @click="filter('recepient')">Recepient</span></li>
                    <li><span @click="filter('sender')">Sender</span></li>
                    <li><span @click="filter('content')">Content</span></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="right_content">
        <router-link :to="{ name:'About' }" class="links"><button class="icons"><font-awesome-icon icon="question" /></button></router-link>
        <router-link :to="{ name:'Profile'}" class="links"><button class="icons"><font-awesome-icon icon="user"/></button></router-link>
    </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
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
        handleSideBar(){
            this.$emit('toggle-sidebar')
        },
        filter(attr){
            this.isDropdown = !this.isDropdown
            if(attr){

            }
        },
        closeDropdown(event) {
            const dropdownElement = this.$refs.dropdown;

            if (dropdownElement && !dropdownElement.contains(event.target)) {
                this.isDropdown = false;
            }
        },
    }
}
</script>

<style>
.topbar{
    display: flex;
    position: relative;
    align-items: center;
    justify-content: space-between;
    text-align: center;
    background-color: #8ECAE6;
    color: #023047;
    padding: 15px 0;
    text-align: left;
}
.topbar button.icons{
    background-color: transparent;
    padding: 0;
    border: None;
    cursor: pointer;
    font-size: 18px;
    font-weight: bold;
    width: 38px;
    height: 38px;
    margin-left: 5px;
    margin-right: 10px;
    color: #023047;
}

.topbar button.icons:hover{
    background-color: #FB8500;
    border-radius: 50%
}

.topbar .search_container{
    position: relative;
    display: flex;
    align-items: center;
    width: 30%;
    margin: 5px 35% 0 0;
    height: 100%;
    color: #023047;
}

.topbar .search{
    background-color: #c8e1ee;
    height: 100%;
    width: 100%;
    padding-right: 50%;
    border:3px solid #FFB703;
    border-top-right-radius: 20px;
    border-bottom-left-radius: 20px ;
    border-top-left-radius: 4px;
    border-bottom-right-radius: 4px;
    font-size: 14px;
}

.topbar .search::placeholder{
    color: #023047;
}
.topbar .search:focus{
    font-size: 15px;
    outline: 2px double #023047;
}

.topbar .search_button{
    position: absolute;
    top: 6px;
    right: 2%;
    
    width: 30px;
    height: 30px;
    cursor: pointer;
    border: none;
    background-color: transparent;
    font-size: 14px;
    font-weight: bold;
    color: #023047;
}

.topbar a.links{
    font-size:0;
}

.topbar .dropdown_container{
    position: absolute;
    top: 100%;
    right: -10%;
}
.topbar .dropdown_container .dropdown{
    background: #f7e8c7;
    border: 2px solid #023047;
    border-radius: 10px;
    width: 150px;
}
.topbar .dropdown ul{
    list-style: none;
    color: #023047;
    text-align: center;
    padding-left: 0;
    margin: 10% 10%
}
.topbar .dropdown ul li{
    margin: 10% 5%;
}
.topbar .dropdown ul li span{
    padding: 10% 20%;
}
.topbar .dropdown ul li span:hover{
    cursor: pointer;
    border-radius: 30px;
    background: #8ECAE6;
}
</style>