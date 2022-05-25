<template>
  <div>
    <NavBarClient />
  </div>
  <!--Pretraga-->
    <div style="width: 1000px;margin-top:20px;margin-bottom:20px" v-if="cottages.length != 0">
      <nav class="navbar navbar-expand-sm navbar-dark">
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Naziv"
          v-model="name"
        />
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Ulica"
          v-model="street"
        />
        <input
          class="form-control mr-sm-2"
          type="text"
          placeholder="Grad"
          v-model="city"
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
    <div class="tab-pane container active">
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
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBarClient from "../../../components/client/NavBarClient.vue";

export default {
  name: "ClietAllCottages",
  components: {
    NavBarClient,
  },

  data() {
    return {
      cottages: [],
      name: "",
      street: "",
      city: "",
      maxLength:0
    };
  },

  methods: {
    async fetchCottages() {
      const res = await fetch("http://localhost:8081/api/cottages");
      const data = await res.json();
      return data;
    },
    async search() {
      const res = await fetch("http://localhost:8081/api/cottages/search", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify({
          name: this.name,
          street: this.street,
          city: this.city,
        }),
      });
      const data = await res.json();
      this.cottages = data;
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
    }
  },

  async created() {
    this.cottages = await this.fetchCottages();
    this.maxLength = this.cottages.length;
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
</style>
