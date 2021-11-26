<template>
  <div class="card">
    <div>
      <NavBarLogOut/>
      <HeaderStartPage />
      <NavBarClient />
    </div>
    <div class="tab">
      <div class="card">
            <h2><i>You're reservation history</i></h2>
        <br>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
          <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#cottages">Cottages</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#boats">Boats</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#adventures">Adventures</a>
          </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
          <div id="cottages" class="container tab-pane active"><br>
            <ReservationHistoryCottagesTable /> 
          </div>
          <div id="boats" class="container tab-pane fade"><br>
            <ReservationHistoryBoatsTable />
          </div>
          <div id="adventures" class="container tab-pane fade"><br>
            <ReservationHistoryAdventuresTable />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBarLogOut from '../../../components/client/NavBarLogOut.vue'
import NavBarClient from '../../../components/client/NavBarClient.vue'
import HeaderStartPage from '../../../components/startPage/HeaderStartPage.vue'
import ReservationHistoryCottagesTable from '../../../components/client/reservations/ReservationHistoryCottagesTable.vue'
import ReservationHistoryBoatsTable from '../../../components/client/reservations/ReservationHistoryBoatsTable.vue'
import ReservationHistoryAdventuresTable from '../../../components/client/reservations/ReservationHistoryAdventuresTable.vue'

export default {
  name: "HistoryReservation",
  components: {
      HeaderStartPage,
      NavBarClient,
      NavBarLogOut,
      ReservationHistoryCottagesTable,
      ReservationHistoryBoatsTable,
      ReservationHistoryAdventuresTable
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
    .container{
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