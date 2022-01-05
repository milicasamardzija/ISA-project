<template>
  <div class="card">
    <div>
      <NavBarLogOut />
      <HeaderStartPage />
      <NavBarClient />
    </div>
    <div class="tab">
      <div class="card">
        <h2 style="margin-left:40px; margin-top:40px"><i>Istorija rezervacija:</i></h2>
        <br />
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" style="margin-left:40px;">
          <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#cottages"
              >Vikendice</a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#boats">Brodovi</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#adventures"
              >Avanture</a
            >
          </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
          <div id="cottages" class="container tab-pane active">
            <br />
            <table class="table table-light table-striped table-hover" v-if="reservationsCottages.length != 0" style="margin-bottom:80px">
              <thead>
                <tr>
                  <th>Datum pocetka</th>
                  <th>Datum kraja</th>
                  <th>Cena</th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr  v-for="(reservation, index) in reservationsCottages" :key="index">
                  <td>{{reservation.dateStart}}</td>
                  <td>{{reservation.dateEnd}}</td>
                  <td>{{reservation.price}}</td>
                  <td><button class="btn btn-success">Napisi zalbu</button></td>
                  <td><button class="btn btn-success">Oceni uslugu</button></td>
                </tr>
              </tbody>
            </table>
          </div>
          <div v-if="reservationsCottages.length === 0">
            <p> Nemate nijednu rezervaciju. </p>
          </div>
          <div id="boats" class="container tab-pane fade">
            <br />
             <table class="table table-light table-striped table-hover" v-if="reservationsBoats.length != 0" style="margin-bottom:80px">
              <thead>
                <tr>
                  <th>Datum pocetka</th>
                  <th>Datum kraja</th>
                  <th>Cena</th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr  v-for="(reservation, index) in reservationsBoats" :key="index">
                  <td>{{reservation.dateStart}}</td>
                  <td>{{reservation.dateEnd}}</td>
                  <td>{{reservation.price}}</td>
                  <td><button class="btn btn-success">Napisi zalbu</button></td>
                  <td><button class="btn btn-success">Oceni uslugu</button></td>
                </tr>
              </tbody>
            </table>
            <div v-if="reservationsBoats.length === 0">
              <p> Nemate nijednu rezervaciju. </p>
            </div>
          </div>
          <div id="adventures" class="container tab-pane fade">
            <br />
            <table class="table table-light table-striped table-hover" v-if="reservationsAdventures.length != 0" style="margin-bottom:80px">
              <thead>
                <tr>
                  <th>Datum pocetka</th>
                  <th>Datum kraja</th>
                  <th>Cena</th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr  v-for="(reservation, index) in reservationsAdventures" :key="index">
                  <td>{{reservation.dateStart}}</td>
                  <td>{{reservation.dateEnd}}</td>
                  <td>{{reservation.price}}</td>
                  <td><button class="btn btn-success">Napisi zalbu</button></td>
                  <td><button class="btn btn-success">Oceni uslugu</button></td>
                </tr>
              </tbody>
            </table>
            <div v-if="reservationsAdventures.length === 0">
              <p> Nemate nijednu rezervaciju. </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBarLogOut from "../../../components/client/NavBarLogOut.vue";
import NavBarClient from "../../../components/client/NavBarClient.vue";
import HeaderStartPage from "../../../components/startPage/HeaderStartPage.vue";

export default {
  name: "HistoryReservation",
  components: {
    HeaderStartPage,
    NavBarClient,
    NavBarLogOut,
  },
  data(){
    return{
      reservationsCottages: [],
      reservationsBoats: [],
      reservationsAdventures: []
    }
  },
   methods:{
    async fetchCottageReservations() {
      const headers = { 
      "Authorization": "Bearer " + localStorage.getItem("token"),
      }
      const res = await fetch("http://localhost:8081/api/reservation/historyReservationsCottages", {headers});
      const data = await res.json();
      return data;
    },
    async fetchBoatsReservations() {
      const headers = { 
      "Authorization": "Bearer " + localStorage.getItem("token"),
      }
      const res = await fetch("http://localhost:8081/api/reservation/historyReservationsBoats", {headers});
      const data = await res.json();
      return data;
    },
    async fetchAdventuresReservations() {
      const headers = { 
      "Authorization": "Bearer " + localStorage.getItem("token"),
      }
      const res = await fetch("http://localhost:8081/api/reservation/historyReservationsAdventures", {headers});
      const data = await res.json();
      return data;
    }
  },
   async created() {
    this.reservationsCottages = await this.fetchCottageReservations();
    this.reservationsBoats = await this.fetchBoatsReservations();
    this.reservationsAdventures = await this.fetchAdventuresReservations();
  },
};
</script>

<style scoped>
.containerInfo {
  width: 97%;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 10px;
  opacity: 1;
  border-radius: 10px;
  align-content: center;
  position: relative;
  background-color: fff;
  box-shadow: 0 19px 40px rgb(0 0 0 / 30%), 0 15px 12px rgb(0 0 0 / 22%);
}
.container {
  margin-top: 3%;
}
.row {
  width: 660%;
  padding-left: 60px;
  height: 10%;
  margin-top: 100px;
  margin-left: 5000px;
  margin-right: 500px;
  opacity: 1.2;
  border-radius: 10px;
  align-content: center;
  background-color: rgba(255, 255, 255, 0.288);
  box-shadow: 0 5px 4px rgb(0 0 0 / 30%), 0 1px 1px rgb(0 0 0 / 22%);
}
.tabs {
  margin-left: 20%;
}
</style>
