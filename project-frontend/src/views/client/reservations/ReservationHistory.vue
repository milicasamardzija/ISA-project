<template>
  <div class="card">
    <div>
      <NavBarLogOut />
      <HeaderStartPage />
      <NavBarClient />
    </div>
    <div class="tab">
      <div class="card">
        <h2 style="margin-left: 40px; margin-top: 40px">
          <i>Istorija rezervacija:</i>
        </h2>
        <br />
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" style="margin-left: 40px">
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

        <!-- Sortiranje -->
        <div class="dropdown" style="margin-bottom:20px">
          <button
            class="btn btn-secondary dropdown-toggle"
            type="button"
            id="dropdownMenuButton"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            Sortitaj
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" v-if="reservationsCottages.length != 0">
            <a class="dropdown-item" href="#" @click="sort('CenaRastuce','cottage')"
              >Cena - rastuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('CenaOpadajuce','cottage')"
              >Cena - opadajuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('DatumRastuce','cottage')"
              >Datum - rastuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('DatumOpadajuce','cottage')"
              >Datum - opadajuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('TrajanjeRastuce','cottage')"
              >Trajanje - rastuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('TrajanjeOpadajuce','cottage')"
              >Trajanje - rastuce</a
            >
          </div>
        </div>

            <table
              class="table table-light table-striped table-hover"
              v-if="reservationsCottages.length != 0"
              style="margin-bottom: 80px"
            >
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
                <tr
                  v-for="(reservation, index) in reservationsCottages"
                  :key="index">
                  <td>{{ format_date(reservation.dateStart) }}</td>
                  <td>{{ format_date(reservation.dateEnd) }}</td>
                  <td>{{ reservation.price }}</td>
                  <td><button class="btn btn-success">Napisi zalbu</button></td>
                  <td><button class="btn btn-success">Oceni uslugu</button></td>
                </tr>
              </tbody>
            </table>
          </div>
          <div v-if="reservationsCottages.length === 0">
            <p>Nemate nijednu rezervaciju.</p>
          </div>
          <div id="boats" class="container tab-pane fade">
            <br />

            <!-- Sortiranje -->
        <div class="dropdown" style="margin-bottom:20px" v-if="reservationsBoats.length != 0">
          <button
            class="btn btn-secondary dropdown-toggle"
            type="button"
            id="dropdownMenuButton"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            Sortitaj
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="#" @click="sort('CenaRastuce','boat')"
              >Cena - rastuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('CenaOpadajuce','boat')"
              >Cena - opadajuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('DatumRastuce','boat')"
              >Datum - rastuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('DatumOpadajuce','boat')"
              >Datum - opadajuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('TrajanjeRastuce','boat')"
              >Trajanje - rastuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('TrajanjeOpadajuce','boat')"
              >Trajanje - rastuce</a
            >
          </div>
        </div>

            <table
              class="table table-light table-striped table-hover"
              v-if="reservationsBoats.length != 0"
              style="margin-bottom: 80px"
            >
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
                <tr
                  v-for="(reservation, index) in reservationsBoats"
                  :key="index"
                >
                  <td>{{ format_date(reservation.dateStart) }}</td>
                  <td>{{ format_date(reservation.dateEnd) }}</td>
                  <td>{{ reservation.price }}</td>
                  <td><button class="btn btn-success">Napisi zalbu</button></td>
                  <td><button class="btn btn-success">Oceni uslugu</button></td>
                </tr>
              </tbody>
            </table>
            <div v-if="reservationsBoats.length === 0">
              <p>Nemate nijednu rezervaciju.</p>
            </div>
          </div>
          <div id="adventures" class="container tab-pane fade">
            <br />

            <!-- Sortiranje -->
        <div class="dropdown" style="margin-bottom:20px" v-if="reservationsAdventures.length != 0">
          <button
            class="btn btn-secondary dropdown-toggle"
            type="button"
            id="dropdownMenuButton"
            data-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            Sortitaj
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="#" @click="sort('CenaRastuce','adventure')"
              >Cena - rastuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('CenaOpadajuce','adventure')"
              >Cena - opadajuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('DatumRastuce','adventure')"
              >Datum - rastuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('DatumOpadajuce','adventure')"
              >Datum - opadajuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('TrajanjeRastuce','adventure')"
              >Trajanje - rastuce</a
            >
            <a class="dropdown-item" href="#" @click="sort('TrajanjeOpadajuce','adventure')"
              >Trajanje - rastuce</a
            >
          </div>
        </div>


            <table
              class="table table-light table-striped table-hover"
              v-if="reservationsAdventures.length != 0"
              style="margin-bottom: 80px"
            >
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
                <tr
                  v-for="(reservation, index) in reservationsAdventures"
                  :key="index"
                >
                  <td>{{ format_date(reservation.dateStart) }}</td>
                  <td>{{ format_date(reservation.dateEnd) }}</td>
                  <td>{{ reservation.price }}</td>
                  <td><button class="btn btn-success">Napisi zalbu</button></td>
                  <td><button class="btn btn-success">Oceni uslugu</button></td>
                </tr>
              </tbody>
            </table>
            <div v-if="reservationsAdventures.length === 0">
              <p>Nemate nijednu rezervaciju.</p>
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
import moment from 'moment';

export default {
  name: "HistoryReservation",
  components: {
    HeaderStartPage,
    NavBarClient,
    NavBarLogOut,
  },
  data() {
    return {
      reservationsCottages: [],
      reservationsBoats: [],
      reservationsAdventures: [],
    };
  },
  methods: {
    async fetchCottageReservations() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch(
        "http://localhost:8081/api/reservation/historyReservationsCottages",
        { headers }
      );
      const data = await res.json();
      return data;
    },
    async fetchBoatsReservations() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch(
        "http://localhost:8081/api/reservation/historyReservationsBoats",
        { headers }
      );
      const data = await res.json();
      return data;
    },
    async fetchAdventuresReservations() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch(
        "http://localhost:8081/api/reservation/historyReservationsAdventures",
        { headers }
      );
      const data = await res.json();
      return data;
    },
    sort(sortParam, tab) {
      if (sortParam == "CenaRastuce"  && tab == "cottage") 
        this.reservationsCottages.sort(function (a, b) {
          return a.price - b.price;
        });
      if (sortParam == "CenaOpadajuce" && tab == "cottage" )
        this.reservationsCottages.sort(function (a, b) {
          return b.price - a.price;
        });
      if (sortParam == "DatumRastuce" && tab == "cottage")
        this.reservationsCottages.sort(function (a, b) {
          return a.dateStart - b.dateStart;
        });
      if (sortParam == "DatumOpadajuce" && tab == "cottage")
        this.reservationsCottages.sort(function (a, b) {
          return b.dateStart - a.dateStart;
        });

      if (sortParam == "CenaRastuce"  && tab == "boat") 
        this.reservationsBoats.sort(function (a, b) {
          return a.price - b.price;
        });
      if (sortParam == "CenaOpadajuce" && tab == "boat" )
        this.reservationsBoats.sort(function (a, b) {
          return b.price - a.price;
        });
      if (sortParam == "DatumRastuce" && tab == "boat")
        this.reservationsBoats.sort(function (a, b) {
          return a.dateStart - b.dateStart;
        });
      if (sortParam == "DatumOpadajuce" && tab == "boat")
        this.reservationsBoats.sort(function (a, b) {
          return b.dateStart - a.dateStart;
        });

      if (sortParam == "CenaRastuce"  && tab == "adventure") 
        this.reservationsAdventures.sort(function (a, b) {
          return a.price - b.price;
        });
      if (sortParam == "CenaOpadajuce" && tab == "adventure" )
        this.reservationsAdventures.sort(function (a, b) {
          return b.price - a.price;
        });
      if (sortParam == "DatumRastuce" && tab == "adventure")
        this.reservationsAdventures.sort(function (a, b) {
          return a.dateStart - b.dateStart;
        });
      if (sortParam == "DatumOpadajuce" && tab == "adventure")
        this.reservationsAdventures.sort(function (a, b) {
          return b.dateStart - a.dateStart;
        });
    },
     format_date(value){
      if (value) {
        return moment(value).format('MM/DD/YYYY')
      }
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
