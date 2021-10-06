<template>
  <b-container class="hello">
    <h1>New Person</h1>
    <b-form @submit="onSubmit">
      <b-form-group
          id="input-group-nickname"
          label="Nickname:"
          label-for="input-nickname"
          description="Please provide your personal nickname."
      >
        <b-form-input
            id="input-nickname"
            v-model="newPerson.nickname"
            type="text"
            placeholder="Enter nickname"
            required
        ></b-form-input>
      </b-form-group>
      <b-form-group
          id="input-group-firstname"
          label="Firstname:"
          label-for="input-firstname"
          description="Please provide your firstname."
      >
        <b-form-input
            id="input-firstname"
            v-model="newPerson.firstname"
            type="text"
            placeholder="Enter firstname"
            required
        ></b-form-input>
      </b-form-group>
      <b-form-group
          id="input-group-lastname"
          label="Lastname:"
          label-for="input-lastname"
          description="Please provide your lastname."
      >
        <b-form-input
            id="input-lastname"
            v-model="newPerson.lastname"
            type="text"
            placeholder="Enter lastname"
            required
        ></b-form-input>
      </b-form-group>
      <b-form-group
          id="input-group-height"
          label="Height:"
          label-for="input-height"
          description="Please provide your height in cm."
      >
        <b-form-input
            id="input-height"
            v-model="newPerson.height"
            type="number"
            placeholder="Enter height"
            required
        ></b-form-input>
      </b-form-group>
      <b-form-group
          id="input-group-birthday"
          label="Birthday:"
          label-for="input-birthday"
          description="Please provide the date of your birth."
      >
        <b-form-input
            id="input-birthday"
            v-model="newPerson.birthday"
            type="date"
            placeholder="Enter birthday"
            required
        ></b-form-input>
      </b-form-group>
      <b-form-group
          id="input-group-gender"
          label="Gender:"
          label-for="input-gender"
          description="Please provide your gender."
      >
        <b-form-select v-model="newPerson.gender" :options="options"></b-form-select>
      </b-form-group>
      <b-button type="submit" variant="primary">Create</b-button>
    </b-form>
  </b-container>
</template>

<script>
import axios from 'axios';
export default {
  name: 'NewPerson',
  data(){
    return{
      newPerson: {
        nickname: '',
        firstname: '',
        lastname: '',
        height: 0,
        birthday: '',
        gender: ''
      },
      selected: null,
      options: [
        { value: 'male', text: 'Male' },
        { value: 'female', text: 'Female' },
      ]
    }
  },
  methods:{
    onSubmit(event){
      event.preventDefault();
      //this.newPerson.birthday = new Date(this.newPerson.birthday).toISOString().substr(0,10)
      axios.post("http://localhost:8080/persons",this.newPerson)
      .then((res)=>{
        console.log(res);
        // happy case, new person was added
      })
      .catch((error)=>{
          console.log(error);
        // error.response.status, show some error message
      })
      .finally(()=>{
        // performed for all cases
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
