<template>
    <form class="form-inline" >
      <input class="form-control mr-sm-2" type="text" placeholder="Usluga" v-model="this.inputText"/>
      <button class="btn btn-success" type="submit" @click.prevent="Find()">Pronadji uslugu</button>
       <button class="btn btn-success" type="submit" @click.prevent="FindAll()">Prikazi sve</button>
          <button class="btn btn-success" type="submit" @click="AddAdventure()">Dodaj uslugu</button>
    </form>

   <table class="styled-table">
    <thead>
        <tr>
            <th>Naziv akcije</th>
            <th></th>
            <th></th>           
        </tr>
    </thead>
    <tbody>
          <tr v-for="(adventure, index) in adventures" :key="index">
                  <td>{{adventure.nameOfAdventure}}</td>
                  <td><button class="btn btn-success btn-block" >Izmeni uslugu</button></td>
                  <td><button class="btn btn-success btn-block" data-target="#prihvatanje" data-toggle="modal" @click="Save(adventure.nameOfAdventure)">Obrisi uslugu</button></td>
           </tr> 

    </tbody>
</table>

       <div class="modal fade" id="prihvatanje" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Da li ste sigurni da zelite da obrisete?
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="Delete()"
              >
                <span></span> Da
              </button>
                            <button
                type="submit"
                class="btn btn-success btn-block"
                 
              >
                <span></span> Ne
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
//import Swal from 'sweetalert2';
import axios from 'axios';
export default ({
    name: "MyServices",
    components: {
    },
    data() {
        return {
          namee:"",
          counter:0,
          inputText:"",
          userRole:"",
            adventures: "",
            adventure: {
              nameOfAdventure:""
            }
            }
    },
    methods: {
        async getActions() {
                const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/adventures/getMyAdventures", {headers});
      const data = await res.json();
      return data;
        },
        async FindActions() {
      const res1 = await fetch("http://localhost:8081/api/adventures/findAdventure/"+this.inputText);
           const data1 = await res1.json();
      return data1;
   },
    async Find() {
      this.adventures = await this.FindActions();
    },
    async FindAll() {
      this.adventures = await this.getActions();
    },
    async AddAdventure() {
      this.$router.push({ name: "AddAdventure" });
    },
    async Save(name) {
      this.namee = name;
      console.log(this.namee);
    },

    async Delete() {
      console.log(this.namee)
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.delete("http://localhost:8081/api/adventures/delete/" + this.namee,{headers})
      .then (response => { 
        console.log(response);
       // this.$router.push({ name: "MyServices" });
      })  
     // return new Swal({
     ////       type: "success",
        //     text:'Obrisali ste uslugu!'
      //     }); 

    }    
    },
      async created() {
      this.userRole = localStorage.getItem("role");
      this.adventures =  await this.getActions();
  }

})
</script>

<style scoped>
.styled-table {
    border-collapse: collapse;
    margin: 25px 20px;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}
.styled-table thead tr {
    background-color: #4caf50;
    color: #ffffff;
    text-align: left;
}
button {
  background-color: #4caf50; /* Green */
}
.styled-table th,
.styled-table td {
    padding: 12px 15px;
}
</style>

