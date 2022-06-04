<template>
<div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
</div>
<h2>Rezervacije za moje avanture:</h2>
<table class="styled-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Datum pocetka</th>
            <th>Datum kraja</th>
            <th>Entitet</th>
            <th>Cena</th>
            <th></th>          
        </tr>
    </thead>
    <tbody>
          <tr v-for="(reservation, index) in ret" :key="index">
                  <td>{{reservation.id}}</td>
                  <td>{{reservation.dateStart}}</td>
                  <td>{{reservation.dateEnd}}</td>
                  <td>{{reservation.entity.name}}</td>
                  <td>{{reservation.price}} €</td>
                  <td><button class="btn btn-success btn-block" data-target="#prikazKlijena" data-toggle="modal" @click="findClient(reservation.clientID)">Prikazi profil klijenta</button></td>
           </tr>

    </tbody>
</table>

<h2>Moje brze rezervacije:</h2>
<table class="styled-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Datum pocetka</th>
            <th>Datum kraja</th>
            <th>Entitet</th>
            <th>Cena</th>
            <th></th>          
        </tr>
    </thead>
    <tbody>
          <tr v-for="(reservation, index) in quickRet" :key="index">
                  <td>{{reservation.id}}</td>
                  <td>{{reservation.dateStart}}</td>
                  <td>{{reservation.dateEnd}}</td>
                  <td>{{reservation.entity.name}}</td>
                  <td>{{reservation.price}} €</td>
           </tr>

    </tbody>
</table>

   <div class="modal fade" id="prikazKlijena" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Profil klijenta
            </h5>
          </div>
              <div class="col-info" style="margin-top: 2%">
                <h5 style="width: 600px" class="text">Ime: {{client.firstname}}</h5>
                <h5 style="width: 600px" class="text">Prezime: {{client.lastname}}</h5>
                <h5 style="width: 600px" class="text">Adresa: {{client.address.street}} {{client.address.number}}, {{client.address.city}}, {{client.address.country}}</h5>
                <h5 style="width: 600px" class="text">Email: {{client.email}}</h5>
                <h5 style="width: 600px" class="text">Broj telefona: {{client.telephone}}</h5>
                <h5 style="width: 600px" class="text">Kategorija korisnika: {{client.loyalityType}}</h5>
                <h5 style="width: 600px" class="text">Poeni korisnika: {{client.poents}}</h5>
                <h5 style="width: 600px" class="text">Penali korisnika: {{client.penals}}</h5>
              </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary pull-left"
              data-dismiss="modal"
            >
              Vrati se
            </button>
          </div>
        </div>
      </div>
    </div>

</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
export default ({
    name: "MyAdventureReservations",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut
    },
    data() {
        return {
          quickRet:"",
          namee:"",
          counter:0,
          inputText:"",
          userRole:"",
          id:"",
             ret: "",
      reservation: { id:0, dateStart: "", dateEnd: "", entity: { id:0, name: ""}, price: 0,clientID:""},
      client: {firstname:"", lastname:"", email:"", role:"", penals:0, poents:0, address: { country: "",  city: "", street: "",number:"" }, telephone:""}
            }
    },
    methods: {
        async getReservations() {
                const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/reservation/myRegularReservation", {headers});
      const data = await res.json();
      return data;
        },
        async getMyQuickReservations() {
                const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/reservation/myActionReservation", {headers});
      const data = await res.json();
      return data;
        } ,
        async findClient(clientID) {
            this.id = clientID;
            console.log(this.id);
                            const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
       const res = await fetch("http://localhost:8081/api/client/getClient/"+this.id, {headers});
      this.client = await res.json();        
      console.log(this.client.firstname);
        }
    },
      async created() {
      this.userRole = localStorage.getItem("role");
      this.ret =  await this.getReservations();
      this.quickRet = await this.getMyQuickReservations();
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
h5 {
     margin: 25px 20px;
    font-family: sans-serif;   
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

