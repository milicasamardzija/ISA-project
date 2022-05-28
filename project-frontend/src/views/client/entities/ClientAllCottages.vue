<template>
  <div>
    <NavBarClient />
  </div>
  <!--Pretraga-->
    <div style="width: 1000px;margin-top:20px;margin-bottom:20px" v-if="cottages.length != 0">
      <nav class="navbar navbar-expand-sm navbar-dark">
        <input
          class="form-control mr-sm-2"
          type="date"
          placeholder="Datum"
          v-model="date"
          min="this.today"
        />
        <input
          class="form-control mr-sm-2"
          type="time"
          placeholder="Vreme"
          v-model="time"
        />
        <input
          class="form-control mr-sm-2"
          type="number"
          placeholder="Broj dana"
          v-model="number"
        />
        <input
          class="form-control mr-sm-2"
          type="number"
          placeholder="Broj ljudi"
          v-model="people"
        />
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Grad"
          v-model="city"
        />
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Drzava"
          v-model="country"
        />
        <button class="btn btn-success" type="submit" @click="search()">
          Pretrazi
        </button>
      </nav>
    </div>

  <!-- Sortiranje -->
  <div class="dropdown" style="margin-bottom:20px; margin-left:20px"  >
    <button
      class="btn btn-secondary dropdown-toggle"
      type="button"
      id="dropdownMenuButton"
      data-toggle="dropdown"
      aria-haspopup="true"
      aria-expanded="false"
      v-if="cottages.length != 0"
    >
      Sortitaj
    </button>
    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
      <a class="dropdown-item" href="#" @click="sort('NazivRastuce')"
        >Naziv - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('NazivOpadajuce')"
        >Naziv - opadajuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('OcenaRastuce')"
        >Ocena - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('OcenaOpadajuce')"
        >Ocena - opadajuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('UlicaRastuce')"
        >Ulica - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('UlicaOpadajuce')"
        >Ulica - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('GradRastuce')"
        >Grad - rastuce</a
      >
      <a class="dropdown-item" href="#" @click="sort('GradOpadajuce')"
        >Grad - rastuce</a
      >
    </div>
  </div>

  <!--Filtriranje-->

  <div class="filter" style="margin-left:20px;margin-top: 20px">
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle drop-btn" ref="btnToggle" id="dropdownMenuButton" data-toggle="dropdown" 
        aria-haspopup="true" aria-expanded="false" v-if="cottages.length != 0">
            Filtriraj - ocena &ensp;
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <a class="dropdown-item" href="#a" @click="filterData(5)" >5</a>
          <a class="dropdown-item" href="#a" @click="filterData(4)" >4</a> 
          <a class="dropdown-item" href="#a" @click="filterData(3)" >3</a>
          <a class="dropdown-item" href="#a" @click="filterData(2)" >2</a>
          <a class="dropdown-item" href="#a" @click="filterData(1)" >1</a>
        </div>
        <button class="btn btn-secondary" style="margin-left:20px" @click="filterData(-1)" v-if="cottages.length != maxLength"><i class="fa fa-close"></i></button>  
    </div>   
  </div>

  <div class="containerInfo">
    <div class="tab-pane container active" v-if="this.cottages.length!=0">
      <div class="row-boats" v-for="(cottage, index) in cottages" :key="index">
        <div class="col-with-picture" >
          <div v-if="cottage.images[0].length != 0">
            <img
              :src="getImgUrl(cottage.images[0].filePath)"
              style="height: 250px !important; width: 300px !important"  @click="goToCottage()"
            />
          </div>
        </div>
        <div class="col-info">
          <h4 style="width: 600px" class="text">
            Promotivni opis: {{ cottage.promoDescription }}
          </h4>
          <h4 style="width: 600px" class="text">Naziv: {{ cottage.name }}</h4>
          <h4 style="width: 600px" class="text">
            Adresa: {{ cottage.address.street }} {{ cottage.address.number }},
            {{ cottage.address.city }}, {{ cottage.address.country }}
          </h4>
          <h4 style="width: 600px" class="text">Ocena: {{ cottage.grade }}</h4>
          <h4 style="width: 600px" class="text">Cena: {{cottage.price}}</h4>
          <button class="btn btn-success" type="submit" @click="openModal(), getSelected(cottage)" v-if="this.s == true">
            Rezerviši
          </button>
        </div>
      </div>
    </div>
  </div>

  <!-- MODAL -->
  <div v-if="modalOpened" class="custom-modal-overlay" @click="closeModal()"></div>
  <div v-if="modalOpened" class="custom-modal">
    <div class="custom-modal-header">
      <h1>Rezervisite vikendicu</h1>
      <button @click="closeModal()">X</button>
    </div>
    <div class="custom-modal-content">
      <h4 style="width: 600px" class="text">Naziv: {{this.selectedEntity.name}}</h4>
      <h4 style="width: 600px" class="text">
      Adresa:   {{ this.selectedEntity.address.street }} {{ this.selectedEntity.address.number }},
            {{ this.selectedEntity.address.city }}, {{ this.selectedEntity.address.country }}
      </h4>
      <h4 style="width: 600px" class="text">Ocena: {{this.selectedEntity.grade}}</h4>
      <h4 style="width: 600px" class="text">Cena: {{this.selectedEntity.price}}</h4>
      <h4 style="width: 600px" class="text">Datum pocetka: {{this.date}}</h4>
      <h4 style="width: 600px" class="text">Broj dana: {{this.number}}</h4>
      <h4 style="width: 600px" class="text">Broj osoba: {{this.people}}</h4>
      <select width=300 style="width: 350px"
            size="8" multiple>
        <option value='blue'>Wi-fi</option>
        <option value='green'>Klima</option>
        <option value='red'>Terasa</option>
      </select>
      <h4 style="width: 600px" class="text">Konacna cena: {{this.price}}</h4>
      <button class="btn btn-success" type="submit" style="margin-top:10px" @click="makeReservation()">
        Rezerviši
      </button>
      <button style="margin-left:700px" class="btn" type="submit">
        Odustani
      </button>
    </div>
  </div>

</template>

<script>
import NavBarClient from "../../../components/client/NavBarClient.vue";
import axios from 'axios'

export default {
  name: "ClietAllCottages",
  components: {
    NavBarClient,
  },

  data() {
    return {
      cottages: [],
      date: "",
      time:"",
      country: "",
      city: "",
      number: "",
      maxLength:0,
      modalOpened: false,
      people: "",
      selectedEntity: {},
      price: 2000,
      s: false,
      today: ""
    };
  },

  methods: {
    getSelected(entity){
      this.selectedEntity = entity;
    },
    async fetchCottages() {
      const res = await fetch("http://localhost:8081/api/cottages");
      const data = await res.json();
      return data;
    },
    async search() { 
     if (this.date == "" || this.time == "" || this.number == "") {
        alert("Morate uneti datum, vreme i broj dana!")
      } else {
        if(this.date > this.today){
          alert(this.date)
          alert(this.today)
          alert("Morate izabrati datum koji je danasnji ili posle danasnjeg!")
        } else {
          const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/cottages/reservationSearch" ,{
          date: this.date,
          time: this.time,
          city: this.city,
          country: this.country,
          number: this.number,
          people: this.people
        },{headers})
      .then (response => { 
        console.log(response.data);
        this.cottages = response.data
        this.s = true;
      }) }
      }
    },
    async makeReservation() {
      if (this.date == "" || this.time == "" || this.number == "") {
        alert("Morate uneti datum, vreme i broj dana!")
      } else {
        const res = await fetch("http://localhost:8081/api/reservation", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
          "Authorization": "Bearer " + localStorage.getItem("token"),
        },
        body: JSON.stringify({
          dateStart: this.date,
          timeStart: this.time,
          price: this.price,
          duration: this.number,
          entityId: this.selectedEntity.id
        }),
      });
      const data = await res.json();
      console.log(data)
      this.closeModal();
      }
    },
    getImgUrl(img) {
      var images = require.context('../../../assets/cottageImages/', false, /.jpg$/)
    return images('./' + img + ".jpg")
    },
    goToCottage(){
      this.$router.push({ name: "CottageProfile" });
    },
    sort(sortParam) {
      if (sortParam == "NazivRastuce")
        this.cottages.sort(function (a, b) {
          return a.name - b.name;
        });
      if (sortParam == "NazivOpadajuce")
        this.cottages.sort(function (a, b) {
          return b.name - a.name;
        });
      if (sortParam == "OcenaRastuce")
        this.cottages.sort(function (a, b) {
          return a.grade - b.grade;
        });
      if (sortParam == "OcenaOpadajuce")
        this.cottages.sort(function (a, b) {
          return b.grade - a.grade;
        });
        if (sortParam == "UlicaRastuce")
        this.cottages.sort(function (a, b) {
          return a.address.street - b.address.street;
        });
      if (sortParam == "UlicaOpadajuce")
        this.cottages.sort(function (a, b) {
          return b.address.street - a.address.street;
        });
      if (sortParam == "GradRastuce")
        this.cottages.sort(function (a, b) {
          return a.address.city - b.address.city;
        });
      if (sortParam == "GradOpadajuce")
        this.cottages.sort(function (a, b) {
          return b.address.city - a.address.city;
        });
    },
    async filterData(param){
      this.cottages = this.cottages.filter((c) => c.grade == param);
      if (param == -1) {
        this.cottages =  await this.fetchCottages();
      }
    },
    openModal() {
      this.modalOpened = true;
    },
    closeModal() {
      this.modalOpened = false;
    },
    dates(){
      var today = new Date();
      var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
      var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
      var dateTime = date+' '+time;
      return dateTime;
    }
  },

  async created() {
    this.cottages = await this.fetchCottages();
    this.maxLength = this.cottages.length;
    this.today = this.dates();
    console.log(this.today)
  }
};
</script>

<style scoped>
.row-boats {
  display: flex;
}

.text {
  text-align: left;
  font-size: 15px;
  font-weight: 600;
  margin-top: 1%;
  margin-bottom: 1%;
}

.col-info {
  margin-left: 30px;
  margin-top: 10px;
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
/* kartica u okviru stavke menija  */
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
.col-with-picture {
  margin-top: 1%;
  margin-bottom: 1%;
}

.custom-modal-overlay{
  background-color: rgba(0, 0, 0, 0.6);
  position: fixed;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 100;
}

.custom-modal {
  width: 50vw;

  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 101;

  padding: 1.5rem;
  background-color: white;
  border-radius: 5px;
}

.custom-modal-header {
  display: flex;
  justify-content: space-between;

  border-bottom: 1px solid rgb(202, 202, 202);
}

.custom-modal-header > h1 {
  font-size: 2rem;
}

.custom-modal-header > button {
  background-color: transparent;
  border: none;
  font-weight: bold;
  outline: none;
}

.custom-modal-content {
  padding: 1rem 0;
}
</style>
