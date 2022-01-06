<template>
  <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarClient />
  </div>
  <div class="card">
    <div class="container">
      <h2><i>Zakazane rezervacije:</i></h2>
      <div class="dropdown">
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
          <a class="dropdown-item" href="#" @click="sort('CenaRastuce')"
            >Cena - rastuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('CenaOpadajuce')"
            >Cena - opadajuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('DatumRastuce')"
            >Datum - rastuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('DatumOpadajuce')"
            >Datum - opadajuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('TrajanjeRastuce')"
            >Trajanje - rastuce</a
          >
          <a class="dropdown-item" href="#" @click="sort('TrajanjeOpadajuce')"
            >Trajanje - rastuce</a
          >
        </div>
      </div>
      <table
        class="table table-light table-striped table-hover"
        style="margin-bottom: 80px"
      >
        <thead>
          <tr>
            <th>Objekat rezervacije</th>
            <th>Datum pocetka</th>
            <th>Datum kraja</th>
            <th>Cena</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(reservation, index) in reservations" :key="index">
            <td>{{ reservation.entityType }}</td>
            <td>{{ format_date(reservation.dateStart) }}</td>
            <td>{{ format_date(reservation.dateEnd) }}</td>
            <td>{{ reservation.price }}</td>
            <td v-if="reservation.isCanceled != false">
              <button class="btn btn-danger">Cancel</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import NavBarLogOut from "../../../components/client/NavBarLogOut.vue";
import NavBarClient from "../../../components/client/NavBarClient.vue";
import HeaderStartPage from "../../../components/startPage/HeaderStartPage.vue";
import moment from 'moment';

export default {
  name: "ScheduledReservation",
  components: {
    HeaderStartPage,
    NavBarClient,
    NavBarLogOut,
  },

  data() {
    return {
      reservations: [],
    };
  },
  methods: {
    async fetchScheduledReservations() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch(
        "http://localhost:8081/api/reservation/scheduledReservations",
        { headers }
      );
      const data = await res.json();
      return data;
    },
    format_date(value){
         if (value) {
           return moment(value).format('MM/DD/YYYY')
          }
      },
    sort(sortParam) {
      if (sortParam == "CenaRastuce")
        this.reservations.sort(function (a, b) {
          return a.price - b.price;
        });
      if (sortParam == "CenaOpadajuce")
        this.reservations.sort(function (a, b) {
          return b.price - a.price;
        });
      if (sortParam == "DatumRastuce")
        this.reservations.sort(function (a, b) {
          return a.dateStart - b.dateStart;
        });
      if (sortParam == "DatumOpadajuce")
        this.reservations.sort(function (a, b) {
          return b.dateStart - a.dateStart;
        });
    },
  },
  async created() {
    this.reservations = await this.fetchScheduledReservations();
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
</style>
