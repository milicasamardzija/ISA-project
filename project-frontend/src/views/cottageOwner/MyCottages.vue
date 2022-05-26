<template>
  <div>
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div class="">
    <div class="tab-pane active containerInfo">
      <table style="width: 90%">
        <tr >
          <td style="width: 6%"> </td>
          <td  ><Search /></td>
        <!-- <td v-if="!notEmpty" ><h4> Jos uvek nemate vikendice. </h4></td> -->
          <td style="width: 28%">
            <button class="btn btn-danger" style="margin-left: 39%">
              <router-link
                style="
                  text-decoration: none !important;
                  display: inline-block;
                  color: white;
                "
                to="/newCottage"
              >
                Dodaj novu vikendicu</router-link
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
              Obrisi vikendicu
            </button>
          </div>
          <div class="row">
            <button class="btn btn-success" @click="showCottage(cottage)" >
             <!--  <router-link to="{name: 'CottageProfile', params: { id: 'cottage.id'} }"></router-link>
               <router-link  style="
                  text-decoration: none !important;
                  display: inline-block;
                  color: white;
                " to="`/cottageProfile/${cottage.id}`"></router-link> -->
            Prikazi vikendicu
             
            </button>
          </div>
        </div>
        
      </div>
<!-- SAMO SA OVIM DOLE PRIKAZUJE???????-->
      <lable style="color: transparent">{{ cottages[0].name}}</lable>
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
                  sigurni da zelite da obrisete ovu vikendicu?</label
                >
              </div>

              <button type="submit" class="btn btn-success btn-block" @click="deleteCottage()">
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
import Search from "../../components/cottageOwner/Search.vue";
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import axios from 'axios'

export default {
  name: "MyCottages",
  components: {
    Search,
    NavBarLogOut,
    NavBarHomePage,
  },
  data() {
    return {
      cottages: [],
      name: "",
      city: "",
      cottageOwner: "",
      notEmpty: false
    }
  },

  methods: {
    async fetchOwner(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/cottageOwner/profileCottageOwner", {headers});
      const data = await res.json();
      console.log(data);
      return data;
    },
   
    async getMyCottages(){
      //this.cottages = await this.fetchOwner().cottageList;
      //this.cottageOwner = this.fetchOwner();
     /// if(this.cottageOwner.cottageList != null) {
      // 
    const res = await fetch("http://localhost:8081/api/cottages/myCottages/"+ this.cottageOwner.id);
    const data = await res.json();

  
      this.cottages = data;
      console.log(this.cottages)
      
  
   },

      async showCottage(cottage){
        this.$router.push({ name: 'CottageProfile', params: { id: cottage.id}})
   },
   
    getSelected(id){
      this.selectedId = id;
    },
   //ne radi
     deleteCottage(){
       console.log(this.selectedId)
         axios.get("http://localhost:8081/api/cottages/delete/"+ this.selectedId).then(
          
           response => { 
        console.log(response);
        
        
           }
         );
   
      this.cottages = this.getMyCottages();
     // this.$router.go(0);

       
   }
  },
   async created() {
     this.cottageOwner = await this.fetchOwner();
    this.cottages = await this.getMyCottages();
    
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
