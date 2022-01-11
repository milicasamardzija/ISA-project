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
        <div v-for="(user, index) in users" :key="index">
          <tr>
                  <td>{{user.name}}</td>
                  <td> {{user.lastname}}</td>
                  <td>{{user.email}}</td>
                  <td>{{user.telephone}}</td>
                  <td>{{user.role}}</td>
                  <td><button type="button" class="btn btn-success">Prihvati zahtev</button></td>
                  <td><button type="button" class="btn btn-success">Odbij zahtev</button></td></tr>
                </div>
    </tbody>
</table>
    </div>
</template>

<script>
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
      users: [],
    }
  },

  methods: {
    async findUsers(){
      const user = await fetch("http://localhost:8080/api/user/getAll");
      const data = await user.json();
      this.users = data;
    },
  }
 
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
