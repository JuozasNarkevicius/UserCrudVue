<template>
  <b-container>
  <h1>List of Persons</h1>


      <b-input-group class="mt-3 mb-3" size="sm">
        <b-form-input v-model="keyword" placeholder="Search" type="text"></b-form-input>
        <b-input-group-text slot="append">
          <b-btn class="p-0" :disabled="!keyword" variant="link" size="sm" @click="keyword = ''">Clear<i class="fa fa-remove"></i></b-btn>
        </b-input-group-text>
      </b-input-group>


    <b-table striped hover :items="items" :keyword="keyword" :fields="fields">
      <template #cell(actions)="row">
        <b-button @click="editItem(row.item.idperson)">Edit</b-button>
        <b-button style="margin-left: 10px;" @click="deleteItem(row.item.idperson)" variant="danger">Delete</b-button>
      </template>

    </b-table>
  </b-container>
</template>

<script>






import axios from 'axios';
export default {
name: "Persons",
  data(){
    return{
      keyword: '',
      persons:[],
      errors:[],
      fields:[
        {key:'idperson', label:'Person ID', sortable: true},
        {key:'nickname', sortable: true},
        {key:'firstname', sortable: true},
        {key:'lastname', sortable: true},
        {key:'birthday', sortable: true},
        {key:'height', sortable: true},
        {key:'gender', sortable: true},
        {key:'actions'}
      ]
    }
  },
  computed: {
    items () {
      return this.keyword
          ? this.persons.filter(person => person.nickname.includes(this.keyword) || person.firstname.includes(this.keyword) || person.lastname.includes(this.keyword))
: this.persons
    }
  },
  created(){
    axios.get("http://localhost:8080/persons")
    .then(response =>{
      this.persons = response.data
      this.persons.forEach(person=> {person.birthday = new Date(person.birthday).toISOString().substr(0,10)})
    })
    .catch(e => {
      this.errors.push(e)
        })
  },
  methods:{
    deleteItem(id){
      axios.delete("http://localhost:8080/persons/"+id)
      .then(response =>{
        console.log(response);
        const index = this.persons.findIndex(person=>person.idperson == id);
        if(~index){
          this.persons.splice(index, 1);
        }
      })
    },
    editItem(id){
      this.$router.push({path:"/editperson/"+id});
    },
  }
}
</script>

<style scoped>

</style>