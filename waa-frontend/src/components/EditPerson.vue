<template>
  <b-container class="hello">
    <h1>Edit Person</h1>
    <b-form @submit="onSubmit">
      <b-form-group
          id="input-group-nickname"
          label="Nickname:"
          label-for="input-nickname"
          description="Please provide your personal nickname."
      >
        <b-form-input
            id="input-nickname"
            v-model="person.nickname"
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
            v-model="person.firstname"
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
            v-model="person.lastname"
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
            v-model="person.height"
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
            v-model="person.birthday"
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
        <b-form-select v-model="person.gender" :options="options"></b-form-select>
      </b-form-group>
      <b-button type="submit" variant="primary">Edit</b-button>
    </b-form>
  </b-container>
</template>

<script>
import axios from "axios";
export default {
name: "EditPerson",
  data(){
    return{
      person:{},
      //selected: "male"
      options: [
        { value: 'male', text: 'Male' },
        { value: 'female', text: 'Female' }
      ]

    }
  },
  created(){
  this.getPerson();
  },
  methods:{
    onSubmit(event){
      event.preventDefault();
      axios.put("http://localhost:8080/persons",this.person)
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
    },
    getPerson(){
      axios.get("http://localhost:8080/persons/"+this.$route.params.id)
      .then((response)=>{
        this.person=response.data;
        this.person.birthday = new Date(this.person.birthday).toISOString().substr(0,10)
        console.log(response.data)});
    }
  }
}
</script>

<style scoped>

</style>