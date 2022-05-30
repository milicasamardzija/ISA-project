<template>
  <div>
    <NavBarLogOut />
    <NavBarBoatOwner />
  </div>
  <div class="">
    <div class="tab-pane active containerInfo">

      <table style="width: 90%">
        <tr >
          <td style="width: 6%"> </td>
          <!-- <td v-if="this.cottages.length != 0" ><Search /></td> -->
                    <nav class="navbar navbar-expand-sm navbar-dark">
    <form class="form-inline" @submit.prevent="searchBoats()">
      <div class="row">
        <div class="">
          <input
            class="form-control mr-bg-4 mr-sm-4"
            type="text"
            placeholder="naziv broda"
            v-model="search.name"
          />
          <input class="form-control mr-sm-2" type="text" placeholder="ulica" v-model="search.street"/>
           <input class="form-control mr-sm-2" type="text" placeholder="grad" v-model="search.city"/>
          <button class="btn btn-success" type="submit" @click="searchBoats()">Search</button>
        </div>
      </div>
    </form>
  </nav>
        <td v-if="this.cottages.length == 0" ><h4> Nemate brodove. </h4></td> 
          <td style="width: 28%">
            <button class="btn btn-danger" style="margin-left: 39%">
              <router-link
                style="
                  text-decoration: none !important;
                  display: inline-block;
                  color: white;
                "
                to="/newBoat"
              >
                Dodaj novi brod</router-link
              >
            </button>
          </td>
          <td></td>
        </tr>
      </table>
<div class="containerInfo" >
      <div class="row-boats"  v-for="cottage in cottages" :key="cottage" >
       
        <div class="col-with-picture" style="margin-right: 5%; margin-top: 1%">
          <div>
            <img
              src="../../assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
              class="rounded img-thumbnail img-fluid"
              alt="Responsive image"
              style="max-height: 300px !important; max-width: 320px !important"
            />
          </div>
        </div>

        <div class="col-info" style="margin-top: 3%">
         
          <h4 style="width: 600px" class="text">Naziv:   {{cottage.name}}</h4>
          <h4 style="width: 600px" class="text">Adresa: {{ cottage.address.number }}, {{ cottage.address.city }},
              {{ cottage.address.country }}</h4>
          <h4 style="width: 600px" class="text">Prosecna ocena: {{cottage.grade}}</h4>
           <h4 style="width: 600px" class="text">Cena: {{cottage.price}} din</h4> 
           <h6 style="width: 600px" class="text">Promotivni opis:   {{cottage.promoDescription}}</h6>
        </div>
        <div class="col-info" style="margin-left: 10%; margin-top: 4%">
          <div class="row" style="margin-bottom: 15%">
            <button
              class="btn btn-secondary"
              data-target="#delete"
              data-toggle="modal"
              @click="getSelected(cottage.id)"
            >
              Obrisi brod
            </button>
          </div>
          <div class="row">
            <button class="btn btn-success" @click="showBoat(cottage)" >
             <!--  <router-link to="{name: 'CottageProfile', params: { id: 'cottage.id'} }"></router-link>
               <router-link  style="
                  text-decoration: none !important;
                  display: inline-block;
                  color: white;
                " to="`/cottageProfile/${cottage.id}`"></router-link> -->
            Prikazi brod
             
            </button>
          </div>
        </div>
        
      </div>
</div>
    </div>

    <!-- Modal za brisanje-->
    <div class="modal fade" id="delete" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Brisanje vikendice
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Da li ste
                  sigurni da zelite da obrisete ovaj brod?</label
                >
              </div>

              <button type="submit" class="btn btn-success btn-block" @click="deleteBoat()">
                <span></span> Potvrdi
              </button>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-outline-secondary pull-left"
              data-dismiss="modal"
            >
              Odustani
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import Search from "../../components/cottageOwner/Search.vue";
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import axios from 'axios'

export default {
  name: "MyBoats",
  components: {
    // Search,
    NavBarLogOut,

    NavBarBoatOwner
  },
  data() {
    return {
      cottages: [],
      name: "",
      city: "",
      boatOwner: "",
      notEmpty: false,
       search: { name: "", street: "", city: ""},
    }
  },

  methods: {
    async fetchOwner(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/boatOwner/profileBoatOwner", {headers});
      const data = await res.json();

      return data;
    },
   
    async getMyBoats(){
      //this.cottages = await this.fetchOwner().cottageList;
      //this.cottageOwner = this.fetchOwner();
     /// if(this.cottageOwner.cottageList != null) {
      // 
   
    fetch("http://localhost:8081/api/boats/myBoats/"+ this.boatOwner.id).then( response => response.json()).then(data => this.cottages = data );
 
   
  
   },
      searchBoats(){
       const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/boats/searchBoatOwner", this.search, {headers}).then( response=> {
         console.log(response);
         this.cottages = response.data
      }
      
      )
   },

      async showBoat(cottage){
        this.$router.push({ name: 'BoatProfile', params: { id: cottage.id}})
   },
   
    getSelected(id){
      this.selectedId = id;
    },
   //ne radi
     deleteBoat(){
       console.log(this.selectedId)
         axios.get("http://localhost:8081/api/cottages/delete/"+ this.selectedId).then(
          
           response => { 
        console.log(response);
        
        
           }
         );
   
      this.cottages = this.getMyBoats();
     // this.$router.go(0);

       
   }
  },
   async created() {
     this.boatOwner = await this.fetchOwner();
    this.cottages = await this.getMyBoats();
    
  },
};

</script>

<style scoped>
.containerInfo {
  margin-top: 1%;
  margin-left: 5%;
}
.row-boats {
  display: flex;
}
</style>
