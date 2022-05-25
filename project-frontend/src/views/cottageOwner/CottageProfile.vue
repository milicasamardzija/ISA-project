<template>
  <div v-if="this.role === 'ROLE_COTTAGE_OWNER'">
    <NavBarLogOut />
    <NavBarHomePage />
  </div>

  <!--OBICNI KORISNIK-->
  <div v-if="this.role === ''">
    <HeaderLogAndRegister />
    <HeaderStartPage />
    <NavBarStartPage />
  </div>
  
  <!--KLIJENT-->
  <div v-if="this.role === 'ROLE_CLIENT'">
    <NavBarClient/>
  </div>
  
  <div>
    <div class="tab-pane active containerInfo">
      <div class="row">
        <div class="column" style="width: 22rem; height: 3rem">
          <h4>{{this.cottage.name}}</h4>
          
        </div>
        <div class="column" style="width: 35rem; height: 3rem">
          <h4>{{cottage.address.street}} {{cottage.address.number}}, {{cottage.address.city}}, {{cottage.address.country}},</h4>
        </div>
        <div class="column" style="width: 18rem; height: 3rem">
          <button class="btn btn-success" v-if="this.role === 'ROLE_COTTAGE_OWNER'">
            <router-link
              style="
                text-decoration: none !important;
                display: inline-block;
                color: white;
              "
              to="/editCottage" 
              >Izmeni vikendicu
            </router-link>
          </button>
        </div>
        <div class="column" style="width: 28rem; height: 3rem" >
          <button class="btn btn-success" v-if="this.role === 'ROLE_COTTAGE_OWNER'">Dodaj akciju</button>
          <button class="btn btn-success" v-if="this.role === 'ROLE_CLIENT'">Pretplati se</button>
        </div>
      </div>

      <div class="row">
        <div class="column">
          <img
            class="mainImage"
            src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
          />
        </div>

        <div class="column" style="width: 50rem">
          <div class="row">
            <!-- 1. red malih slika-->
            <div class="column columnSmall" style="width:60rem; height: 14em">
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="clearfix"></div>
            </div>
          </div>
          <!-- drugi red malih slika-->
          <div class="row" style="height: 14em">
            <div class="column columnSmall2" style="width: 60rem; height: 18em">
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="clearfix"></div>
            </div>
          </div>
        </div>
      </div>

      <div class="row aboutCottage">
        <div class="column columnAbout">
          <h4>Opis smestaja</h4>
          <label>
           {{cottage.promoDescription}}
          </label>
        </div>

        <div class="column" style="padding-left: 10rem">
          <h4>Informacije</h4>
          <div class="row" style="height: 20rem; background: whitesmoke">
            <div class="column" style="width: 20rem">
              <p>Cena: {{cottage.price}}</p>
              <p>Ocena: {{cottage.grade}}</p>
              <p>Maksimalan broj osoba: {{capacity}}</p>
              <p>Broj soba: {{cottage.roomsNumber}}</p>
              <p>Broj kreveta po sobi: {{cottage.bedsByRoom}}</p>
              <p>Pravila ponasanja: {{cottage.rules}}</p>
            </div>

            <div
              class="column"
              style="width: 10rem; height: 18rem; background: white"
            >
              <p class="pStyle">dodatne usluge:</p>
              <p>WIFI</p>
              <p>Dva parking mesta</p>
              <p>Klima</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarClient from "../../components/client/NavBarClient.vue"
import HeaderLogAndRegister from "../../components/startPage/HeaderLogAndRegister.vue";
import NavBarStartPage from '../../components/startPage/NavBarStartPage.vue';



export default {
  name: "CottageProfile",
  components: {
    NavBarLogOut,
    NavBarHomePage,
    HeaderStartPage,
    NavBarClient,
    HeaderLogAndRegister,
    NavBarStartPage
  },
  data() {
    return {
      role:"",
      id: "",
      cottage: "",
      capacity: 0
    };
  },
  async created() {
        this.role = localStorage.getItem("role");
      this.id = this.$route.params.id;
  
     this.cottage = this.getCottage(this.id); 
    
  },
  methods: {
    async getCottage(id) {
      const res = await fetch("http://localhost:8081/api/cottages/cottage/" + id);
      const data = await res.json();  
       console.log(data) ;
       this.cottage= data;  //samo u created nije radilo
       this.capacity = this.cottage.bedsByRoom * this.cottage.roomsNumber;
      return data;
    },
  },
  
};
</script>

<style scoped>
.containerInfo {
  margin-left: 3%;
  margin-top: 2%;
}
.row-boats {
  display: flex;
}
.buttonMargin {
  margin-left: 5%;
}
.mainImage {
  height: 30em;
  width: 43em;
}
.aboutCottage {
  margin-top: 3.5em;
  height: 35em;
}
.column {
  float: left;
  width: 43em;
  padding: 9px;
  height: 28em;
}
.columnAbout {
  float: left;
  width: 50em;
  padding: 10px;
  height: 28em;
}
.columnSmall {
  float: left;
  width: 45em;
  margin-left: 5px;
  margin-top: -12px;
  height: 14em;
}
.columnSmall2 {
  float: left;
  width: 45em;
  margin-left: 5px;
  margin-top: 15px;
  height: 14em;
}
/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
.backgroundInfo {
  background: whitesmoke;
}
.pStyle {
  font-size: 18px;
  font-weight: bold;
  font-style: italic;
}
.smallImages {
  height: 15em;
  width: 18em;
}
.images {
  float: left;
  border-style: solid;
  border-width: 2px;
  margin: 3px;
  height: 15em;
  width: 16em;
  overflow: hidden;
  text-align: center;
}

.clearfix {
  clear: both;
}
</style>
