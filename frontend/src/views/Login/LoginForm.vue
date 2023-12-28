<template>
    <form class="login" @submit.prevent="handleSubmit">
        <label for="email" @click="focus">Email:</label>
        <input type="email" id="email" v-model="email" required>

        <label for="password" @click=focus>Password:</label>
        <input type="password" id="password" v-model="password" required>
        <div v-if="passwordError" class="error">{{ passwordError }}</div>
        
        <div class="submit">
            <button>Log in</button>
        </div>
    </form>
</template>

<script>
import axios from 'axios'
import VueCookies from 'vue-cookies'
export default {
    data() {
        return{
            email: '',
            password: '',
            passwordError: ''
            
        }
    },
    methods: {
        async handleSubmit() {
            try {
                console.log('Logging in...');
                const response = await axios.post('http://localhost:8081/api/users/login', {
                    email: this.email,
                });

                const userId = response.data.user_id;  
                const redirectUrl = response.data.redirectUrl;

                VueCookies.set('user_id', userId);

                console.log('Login successful!');
                console.log(userId);

                this.$router.push(redirectUrl);
            } catch (error) {
                console.error('Login failed:', error.message);
            }
        },
    }

}
</script>

<style>
    form.login{
        max-width: 420px;
        margin: 30px auto;
        background: #fffaf1;
        text-align: left;
        padding: 40px;
        border-radius: 10px;
    }
    .login label{
        color: #aaa;
        display: inline-block;
        margin: 25px 0 15px;
        font-size: 0.8em;
        text-transform: uppercase;
        letter-spacing: 1px;
        font-weight: bold;
    }
    .login input{
        display: block;
        padding: 10px 6px;
        width: 100%;
        box-sizing: border-box;
        border: none;
        border-bottom: 1px solid #ddd;
        color: #555;
        background: #fffaf1;
    }
    .login button{
        background: #ffb703;
        border: 0;
        padding: 10px 15px;
        margin-top: 30px;
        margin-left: 10px;
        margin-right: 10px;
        color: #023047;
        font-size: .9em;
        font-weight: bold;
        border-radius: 20px;
    }
    .login .submit{
        text-align: center;
    }
    .login .error{
        color: #d41f1f;
        margin-top: 10px;
        font-size: 0.8em;
        font-weight: bold;
    }
</style>