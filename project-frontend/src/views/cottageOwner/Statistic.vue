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
             <select class="form-control" v-model="report.entityId" placeholder="Kliknite za izbor entiteta">
                    <option v-for="m in this.cottages" :key="m.id" :value="m.id">{{m.name}}</option>
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
                                v-model="report.fromDate"
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
                                v-model="report.toDate"
                              />
        
       </div>
       </div>
       <button type="button" @click="showChart()"> Generisi </button>
     </div>

     <div style="width: 50%, height: 20%">  
   <canvas id="myCharts" width="400" height="400" style="width: 50%, height: 20%" > </canvas>
     </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import Chart from 'chart.js/auto';
import axios from 'axios'
// import { defineComponent } from 'vue'


export default  ({
  name: "Statistic",
  components: {
    NavBarLogOut,
    NavBarHomePage,
     NavBarBoatOwner,

  },
  props: {


  },
  
  data(){
    return {
      role: "",
      report: { entityId: 0, entityName: "", type: 1, grade: 0, numberOfPeople: 0, earing: 0, fromDate: "", toDate: ""},
      cottages: "",
      boats: "",
      owner: "",
      id: 0,
      dates: ""
 


    
  }
  },

  methods:{

    showChart(){
    
     console.log(this.report);
     const headers = {
       Authorization: "Bearer " + localStorage.getItem("token"),
      };
   axios.post("http://localhost:8081/api/reportOwner/reportOwner", this.report, {headers})
   .then(response => this.dates = response.data );
   console.log(this.dates);




const ctx = document.getElementById('myCharts').getContext('2d');
const myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Prosecna ocena', 'Posecenost', 'Prihodi' ], //ime entiteta
        datasets: [{
             label: 'Posecenost', //moze ime entiteta
            data: [12, 19, 3],             //posecenost po entitetu
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                // 'rgba(75, 192, 192, 0.2)',
                // 'rgba(153, 102, 255, 0.2)',
                // 'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                // 'rgba(75, 192, 192, 1)',
                // 'rgba(153, 102, 255, 1)',
                // 'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        // },
       
        // {
        //     label: 'Prosecna ocena',
        //     data: [12, 19, 3, 5, 2, 3],              //prihodi
        //     backgroundColor: [
        //         'rgba(255, 99, 132, 0.2)',
        //         'rgba(54, 162, 235, 0.2)',
        //         'rgba(255, 206, 86, 0.2)',
        //         'rgba(75, 192, 192, 0.2)',
        //         'rgba(153, 102, 255, 0.2)',
        //         'rgba(255, 159, 64, 0.2)'
        //     ],
        //     borderColor: [
        //         'rgba(255, 99, 132, 1)',
        //         'rgba(54, 162, 235, 1)',
        //         'rgba(255, 206, 86, 1)',
        //         'rgba(75, 192, 192, 1)',
        //         'rgba(153, 102, 255, 1)',
        //         'rgba(255, 159, 64, 1)'
        //     ],
        //     borderWidth: 1
        }]
    },
    // options: {
    //     scales: {
    //         y: {
    //             beginAtZero: true
    //         }
    //     }
    // }
});

myChart;
      
    },
   
   getMyCottages(){
           const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
   axios.get("http://localhost:8081/api/cottages/ownerCottages", {headers}).then(response => this.cottages = response.data );
 console.log(this.cottages);
 
   },
    
   createReport(){

   }

  },  
  //created
   async created() {
     console.log("CREATED")
    this.role = localStorage.getItem("role");

    if(this.role =="ROLE_COTTAGE_OWNER"){

    this.getMyCottages();
    console.log(this.cottages);
    }
 


   }
 //////////
});
</script>

<style scoped>
.divStyle{
  margin-left: 20%;
  margin-top: 3%;
  font-size: larger;
}

</style>
