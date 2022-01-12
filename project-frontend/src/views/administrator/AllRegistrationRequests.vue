<template>
  <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator />
  </div>
   <div id="#example-2">
        <table class="styled-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Email</th>
            <th>Broj telefona</th>
            <th>Tip registracije</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
          <tr v-for="(user, index) in users" :key="index">
                  <td>{{user.id}}</td>
                  <td>{{user.firstname}} </td>
                  <td> {{user.lastname}}</td>
                  <td>{{user.email}}</td>
                  <td>{{user.telephone}}</td>
                  <td>{{user.role}}</td>
                  <td><button class="btn btn-success btn-block" @click="confirmRequest(user.id) " >Prihvati zahtev</button></td>
                  <td><button class="btn btn-success btn-block" data-target="#odbijanje" data-toggle="modal" @click="SaveID(user.id)">Odbij zahtev</button></td></tr>
    </tbody>
</table>
    </div>

    <div class="modal fade" id="odbijanje" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Razlog odbijanja
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <div class="form-group">
                <label for="name">Obrazlozenje:</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="reason"
                />
              </div>
              <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="RejectRequest()"
              >
                <span></span> Posalji
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
import axios from 'axios'
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";

export default {
  name: "AllRegistrationRequests",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
  },
  data() {
    return {
      users: "",
      selectID: 0,
      reason:"",
      role: "",
      user: { id: 0, firstname: "", lastname: "", address: { id: "", street:"", number: 0, city: "", country: ""}, telephone: ""}
   
    }
  },

  methods: {
    async getUsers() {
      const res = await fetch("http://localhost:8081/api/user/getAllRequestUser");
      const data = await res.json();
      return data;
    },
    async confirmRequest(id) {
       this.selectID = id;
       const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/user/confirm/"+this.selectID,{headers})
      .then (response => { 
        console.log(response);
        this.$router.push({ name: "AllRegistrationRequests" });
      })  

      alert("Vas nalog jos uvek nije aktiviran ili ste pogresili prilikom unosa kredencijala!")
      this.$router.go(0);
    },
    RejectRequest() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.delete("http://localhost:8081/api/user/reject/" + this.selectID,{headers})
      .then (response => { 
        console.log(response);
        this.$router.push({ name: "AllRegistrationRequests" });
      })  

   },

       async SaveID(id) {
      this.selectID = id;
  },
  },
    async created() {
      this.users = await this.getUsers();
  },
};
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
