<template>
  <div class="centered_component">
    <form class="signup" @submit.prevent="handleSubmit">
      <label for="name" @click="focus">Full Name:</label>
      <input type="name" id="name" v-model="name" required>

      <label for="username" @click="focus">Username:</label>
      <input type="username" id="username" v-model="userName" required>

      <label for="password" @click="focus">Passwrod:</label>
      <input type="password" id="password" v-model="password" required>
      <div v-if="passwordError" class="error">{{ passwordError }}</div>

      <label for="confirmPass" @click="focus">Re-type Passwrod:</label>
      <input type="password" id="confirmPass" v-model="confirmPass" required>
      <div v-if="confPassError" class="error">{{ confPassError }}</div>

      <div class="submit">
        <button>Create An Account</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios'
import VueCookies from 'vue-cookies'
export default {
  data() {
    return{
      name: '',
      userName: '',
      password: '',
      confirmPass: '',
      passwordError: '',
      confPassError: '',
    }
  },
  methods: {
    async handleSubmit() {
      console.log('Registering...');
      try {
        const response = await axios.post('http://localhost:8081/api/users/register', {
          username: this.userName,
        });

        const userId = response.data.user_id;  
        const redirectUrl = response.data.redirectUrl;

        VueCookies.set('user_id', userId);

        console.log('Register successful!');
        console.log(userId);

        this.$router.push(redirectUrl);
      } catch (error) {
        console.error('Register failed:', error.message);
      }
    },
  }
}
</script>

<style>
  .centered_component{
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
  }
  form.signup{
    width: 400px;
    max-width: 420px;
    margin: 30px auto;
    background: #fffaf1;
    text-align: left;
    padding: 40px;
    border-radius: 10px;
  }
  .signup label{
      color: #aaa;
      display: inline-block;        
      margin: 25px 0 15px;
      font-size: 0.8em;
      text-transform: uppercase;
      letter-spacing: 1px;
      font-weight: bold;
  }
  .signup input{
      display: block;
      padding: 10px 6px;
      width: 100%;
      box-sizing: border-box;
      border: none;
      border-bottom: 1px solid #ddd;
      color: #555;
      background: #fffaf1;
  }
  .signup button{
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
    .signup .submit{
        text-align: center;
    }
    .signup .error{
        color: #d41f1f;
        margin-top: 10px;
        font-size: 0.8em;
        font-weight: bold;
    }
</style>