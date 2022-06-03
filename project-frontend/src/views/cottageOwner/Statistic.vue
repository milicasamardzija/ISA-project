<template>

  <div v-if="this.role ==='ROLE_BOAT_OWNER'">
    <NavBarLogOut />
    <NavBarBoatOwner />
  </div>

  <div v-if="this.role ==='ROLE_COTTAGE_OWNER'">
   <NavBarLogOut />
    <NavBarHomePage />

  </div>
  <div class="divStyle">
    <div class="row"  >
      <div class="column" style="width: 22rem;" > 
        <label> Izaberite entitet za izvestaj:</label>
      </div>
      <div class="column" >
     <div class="md-form">
             <select class="form-select" aria-label="Default select example" v-model="boatTypeString" style="height:2rem; width: 11rem;margin-bottom: 0.8rem; background-color: rgb(241, 241, 241); ">
                    
                    <option value="">jahta</option>
                    <option value="1">camac</option>
                    <option value="2">brod</option>
                    </select>
      </div>
        
       </div>
       </div>

    <div class="row" >
      <div class="column" style="width: 22rem; margin-top:1rem"> 
        <label> Pocetni period za koji kreirate izvestaj:</label>
      </div>
      <div class="column">
         <input 
                                class="form-control mr-sm-2"
                                type="date"
                                placeholder="Vreme"
                                v-model="report.dateFrom"
                              />
        
       </div>
       </div>

           <div class="row" >
      <div class="column" style="width: 22rem; margin-top:1rem"> 
        <label> Krajnji period za koji kreirate izvestaj:</label>
      </div>
      <div class="column">
         <input 
                                class="form-control mr-sm-2"
                                type="date"
                                placeholder="Vreme"
                                v-model="report.dateTo"
                              />
        
       </div>
       </div>
     </div>

     <div>  

     </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";


export default {
  name: "Statistic",
  components: {
    NavBarLogOut,
    NavBarHomePage,
     NavBarBoatOwner,

  },

   
  data(){
    return {
      role: "",
      report: {},
      cottages: "",
      boats: "",
      owner: "",
    
      id: 0,
 


    
  }
  },

  methods:{
     async fetchCottageOwner(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/cottageOwner/profileCottageOwner", {headers});
      const data = await res.json();
  
  // console.log(this.id);

      return data;
    },

       async fetchBoatOwner(){
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/boatOwner/profileBoatOwner", {headers})
      const data = await res.json();
  
    //  this.boats = this.getMyBoats();
      return data;
    },
  async getMyCottages(){
         
    fetch("http://localhost:8081/api/cottages/myCottages/"+ this.owner.id).then( response => response.json()).then(data => this.cottages = data );
 
 
   },
     async getMyBoats(){
    fetch("http://localhost:8081/api/boats/myBoats/"+ this.id).then( response => response.json()).then(data => this.boats = data );
 
 
   },

  },  
  //created
   async created() {
    this.role = localStorage.getItem("role");
    if(this.role =="ROLE_COTTAGE_OWNER"){
     this.owner = await this.fetchCottageOwner();
    this.cottages = await this.getMyCottages();
    }
  else if(this.role =="ROLE_BOAT_OWNER")
 {
     this.owner  = await this.fetchBoatOwner();
    this.boats = await this.getMyBoats();
 }


   }
 //////////
};
</script>

<style scoped>
.divStyle{
  margin-left: 20%;
  margin-top: 3%;
  font-size: larger;
}

</style>
