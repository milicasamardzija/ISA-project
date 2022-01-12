<template>
  
    <div>
      <NavBarClient />
    </div>
    <div class="tab">
      <div>
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
      </div>
        <!-- Tab panes -->
        <div class="tab-content">
<!----------------------------------------------------------------------------COTTAGES-------------------------------------------------------->
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
                  >Trajanje - opadajuce</a
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
                  <th>Trajanje</th>
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
                  <td>{{ reservation.duration }} 
                    <span v-if="reservation.entityType === 'COTTAGE' || reservation.entityType === 'BOAT'"> dana </span>
                    <span v-if="reservation.entityType === 'ADVENTURE'"> h </span>  
                  </td>
                  <td>{{ reservation.price }}</td>
                  <td><button class="btn btn-success" data-target="#zalba" data-toggle="modal" @click="getSelected(reservation.id)">Napisi zalbu</button></td>
                  <td><button class="btn btn-success" data-target="#ocena" data-toggle="modal">Oceni uslugu</button></td>
                </tr>
              </tbody>
            </table>
          </div>
     
        <!----------------------------------------------------------------------------BOATS-------------------------------------------------------->
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
              >Trajanje - opadajuce</a
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
                  <th>Trajanje</th>
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
                  <td>{{ reservation.duration }} 
                    <span v-if="reservation.entityType === 'COTTAGE' || reservation.entityType === 'BOAT'"> dana </span>
                    <span v-if="reservation.entityType === 'ADVENTURE'"> h </span>  
                  </td>
                  <td>{{ reservation.price }}</td>
                  <td><button class="btn btn-success" data-target="#zalba" data-toggle="modal" @click="getSelected(reservation.id)">Napisi zalbu</button></td>
                  <td><button class="btn btn-success" data-target="#ocena" data-toggle="modal">Oceni uslugu</button></td>
                </tr>
              </tbody>
            </table>
            <div v-if="reservationsBoats.length === 0">
              <p style="font-size: 20px; margin-bottom:60px">Nemate nijednu rezervaciju.</p>
            </div>
      </div>

<!----------------------------------------------------------------------------ADVENTURES-------------------------------------------------------->

    <div id="adventures" class="container tab-pane fade">
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
              >Trajanje - opadajuce</a
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
                  <th>Trajanje</th>
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
                  <td>{{ reservation.duration }} 
                    <span v-if="reservation.entityType === 'COTTAGE' || reservation.entityType === 'BOAT'"> dana </span>
                    <span v-if="reservation.entityType === 'ADVENTURE'"> h </span>  
                  </td>
                  <td>{{ reservation.price }}</td>
                  <td><button class="btn btn-success" data-target="#zalba" data-toggle="modal" @click="getSelected(reservation.id, reservation.entityType)">Napisi zalbu</button></td>
                  <td><button class="btn btn-success" data-target="#ocena" data-toggle="modal">Oceni uslugu</button></td>
                </tr>
              </tbody>
            </table>
            <div v-if="reservationsAdventures.length === 0">
              <p style="font-size: 20px; margin-top:60px">Nemate nijednu rezervaciju.</p>
            </div>
    </div>
       
  </div>
    

  <!-- Modal za pisanje zalbe -->
  <div class="modal fade" id="zalba" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel" style="color: #0b4025; padding: 5px 35px">
              Zalba
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" @submit.prevent="Cancel">
              <div class="form-group">
                  <table>
                    <tr>
                      <td><label>Ime vlasnika: </label> </td>
                      <td><label>{{this.user.firstname}} {{this.user.lastname}}</label> </td>
                    </tr>
                    <tr>
                      <td v-if="this.selectedReservationType === 'COTTAGE'"><label>Naziv vikendice: </label> </td>
                      <td v-if="this.selectedReservationType === 'BOAT'"><label>Naziv broda: </label> </td>
                      <td v-if="this.selectedReservationType === 'ADVENTURE'"><label>Naziv avanture: </label> </td>
                      <td><label>{{this.entity.name}}</label> </td>
                    </tr>
                  
                    <tr v-if="selectedReservationType == 'COTTAGE' || selectedReservationType == 'BOAT'" >
                      <td  span="2">
                      Komentar na vlasnika:
                      </td>
                    </tr> 
                    <tr v-if="selectedReservationType == 'ADVENTURE'">
                      Komentar na instruktora:
                    </tr>  
                    <tr > 
                      <td  colspan="2">
                      <textarea type="text" style="width: 100%; height: 50%" v-model="contentUser">
                      </textarea>
                      </td>
                    </tr>
                    <tr>
                      Komentar na vikendicu:
                    </tr> 
                    <tr>
                      <td  colspan="2">
                      <textarea  type="text" style="width: 100%; height: 50%" v-model="contentEntity">
                      </textarea>
                      </td>
                    </tr>
                  </table>
              </div>
              <table>
                <tr>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" @click="sendComplaint()" style="width:80px; margin-bottom:20px">
                      Potvrdi
                    </button>
                  </td>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" style="width:80px; margin-left:230px; margin-bottom:20px">
                      Otkazi
                    </button>
                  </td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal za pisanje ocene -->
  <div class="modal fade" id="ocena" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel" style="color: #0b4025; padding: 5px 35px">
              Ocena
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" @submit.prevent="Cancel">
              <div class="form-group">
                <table>
                  <tr>
                    <td><label>Ime vlasnika: </label> </td>
                    <td><label>{{this.user.firstname}} {{this.user.lastname}} </label> </td>
                  </tr>
                  <tr v-if="selectedReservationType == 'COTTAGE' || selectedReservationType == 'BOAT'">
                    Komentar na vlasnika:
                  </tr> 
                  <tr v-if="selectedReservationType == 'ADVENTURE'">
                    Komentar na instruktora:
                  </tr> 
                  <tr v-if="selectedReservationType == 'COTTAGE' || selectedReservationType == 'BOAT'">
                    <textarea type="text" style="width: 100%; height: 50%">
                    </textarea>
                  </tr>
                  <tr>
                    <td v-if="selectedReservationType == 'COTTAGE'"><label>Naziv vikendice: </label> </td>
                    <td v-if="selectedReservationType == 'BOAT'"><label>Naziv broda: </label> </td>
                    <td v-if="selectedReservationType == 'ADVENTURE'"><label>Naziv avanture: </label> </td>
                    <td><label>{{this.entity.name}}</label> </td>
                  </tr>
                   <tr v-if="selectedReservationType == 'COTTAGE'">
                    Komentar na vikendicu:
                    </tr> 
                    <tr v-if="selectedReservationType == 'BOAT'">
                    Komentar na brod:
                    </tr> 
                  <tr v-if="selectedReservationType == 'COTTAGE' || selectedReservationType == 'BOAT'">
                    <textarea type="text" style="width: 100%; height: 50%">
                    </textarea>
                  </tr>
                  <tr>
                    <td><label>Unesite ocenu: </label> </td>
                    <td style="width: 200px"> 
                      <select  v-model="review.grade"> Ocena
                        <option  v-bind:value="5">5</option>
                        <option  v-bind:value="4">4</option>
                        <option  v-bind:value="3">3</option>
                        <option  v-bind:value="2">2</option>
                        <option  v-bind:value="1">1</option>
                      </select>
                    </td>
                  </tr>
                </table>
              </div>
              <table>
                <tr>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" @click="sendReview()" style="width:80px; margin-bottom:20px">
                      Potvrdi
                    </button>
                  </td>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" style="width:80px; margin-left:230px; margin-bottom:20px">
                      Otkazi
                    </button>
                  </td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBarClient from "../../../components/client/NavBarClient.vue";
import moment from 'moment';
import axios from 'axios';
export default {
  name: "HistoryReservation",
  components: {
    NavBarClient,
  },
  data() {
    return {
      reservationsCottages: [],
      reservationsBoats: [],
      reservationsAdventures: [],
      complaint: "",
      review:"",
      user: {},
      entity: {},
      selectedReservationId:0,
      selectedReservationType:"",
      show:false,
      contentUser: "",
      contentEntity: ""
    };
  },
  methods: {
    async getSelected(id, type){
      this.selectedReservationId = id;
      this.selectedReservationType = type;
      alert(type)
      this.entity = await this.loadEntity();
      this.user = await this.loadUser(this.entity.id);
    },
    clear(){
      this.selectedReservationId = 0;
      this.user = "";
      this.entity = "";
    },
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
       if (sortParam == "TrajanjeRastuce")
        this.reservationsCottages.sort(function (a, b) {
          return a.duration - b.duration;
        });
      if (sortParam == "TrajanjeOpadajuce")
        this.reservationsCottages.sort(function (a, b) {
          return b.duration - a.duration;
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
      if (sortParam == "TrajanjeRastuce")
        this.reservationsBoats.sort(function (a, b) {
          return a.duration - b.duration;
        });
      if (sortParam == "TrajanjeOpadajuce")
        this.reservationsBoats.sort(function (a, b) {
          return b.duration - a.duration;
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
      if (sortParam == "TrajanjeRastuce")
        this.reservationsAdventures.sort(function (a, b) {
          return a.duration - b.duration;
        });
      if (sortParam == "TrajanjeOpadajuce")
        this.reservationsAdventures.sort(function (a, b) {
          return b.duration - a.duration;
        });
      
    },
    async loadEntity(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch(
        "http://localhost:8081/api/reservation/entity/" + this.selectedReservationId,
        { headers }
      );
      const data = await res.json();
      return data;
    },
    async loadUser(id){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch(
        "http://localhost:8081/api/cottageOwner/cottageOwnerUser/" + id,
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
    sendComplaint(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/complaint" ,{contentUser: this.contentUser, contentEntity: this.contentEntity, user: this.user, entity: this.entity},{headers})
      .then (response => { 
        console.log(response);
        alert("Uspesno ste se poslali zalbu!")
      })
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