<template>
<div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
</div>
<div>
    <h1>Dodavanje akcije</h1>
          <table>
        <tr>
            <td><label ><span class="glyphicon glyphicon-eye-open"></span>Datum pocetka</label></td>
            <td> <input class="form-control" id="psw" type="date" v-model="dateStart"/></td></tr>
        <tr>
            <td><label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Datum zavrsetka </label></td>
            <td> <input class="form-control" id="psw" type="date" v-model="dateEnd"/></td></tr>
  
        <tr>
          <td><label ><span class="glyphicon glyphicon-eye-open"></span>Cena</label></td>
          <td> <input class="form-control" id="psw" type="number" v-model="price"/></td></tr>

        <tr>
          <td><label ><span class="glyphicon glyphicon-eye-open"></span>Izaberi entitet</label></td>
          <td>   <select v-model="name" class="form-control">
                        <option v-for="entity in entities" :value="item" :key="entity.id">
                            {{ entity.name }}
                        </option>
  </select></td></tr>

        <tr>
            <td><label ><span class="glyphicon glyphicon-eye-open"></span>Validno od</label></td>
            <td> <input class="form-control" id="psw" type="date" v-model="validFrom"/></td></tr>
        <tr>
            <td><label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Validno do </label></td>
            <td> <input class="form-control" id="psw" type="date" v-model="validTo"/></td></tr>
        <tr><td><button type="submit" class="btn btn-success btn-block" style="width:100px;margin-top:20px" @click="AddAction()">
                <span></span> Potvrdi
              </button></td></tr>
          </table>
</div>
</template>

<script>
//import axios from "axios";
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
import axios from "axios";
export default ({
    name: "CreateAction",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut
    },
    data() {
        return {
            entities:"",
            entity: { id:0, name: "", address: { id: "", street:"", number: 0, city: ""}, promoDescription: "", rules:"", price:0, grade: 0},
            dateStart:"",
            dateEnd:"",
            price:0,
            name:"",
            validFrom:"",
            validTo:"",
            }
    },
    methods: {
    async getEntities() {
      const res = await fetch("http://localhost:8081/api/entities/getAllEntities");
      const data = await res.json();
      return data;
    },
    async AddAction() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/reservation/addAction",{ 
        dateStart : this.dateStart, 
        dateEnd : this.dateEnd,
        price : this.price,
        name: this.name,
        validFrom: this.validFrom,
        validTo: this.validTo
       },{
        headers
      })
      .then (response => { 
        console.log(response);
       // this.$router.push({ name: "" });
      }) 

        alert("Dodali ste admina!")
     // this.$router.go(0);
    }
    },
    async created() {
        this.entities = await this.getEntities();
    }
})
</script>

<style scoped>
.styled-table {
    border-collapse: collapse;
    margin: 25px 20px;
    font-size: 0.9em;
    font-family: sans-serif;
    min-width: 400px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

h1 {
    margin: 25px 20px;
    font-family: sans-serif;
}
.tr {
    margin: 25px 20px;
    color: #ffffff;
    text-align: left;
}
button {
    margin: 25px 20px;
  background-color: #4caf50; /* Green */
}
th,
td {
    margin: 25px 20px;
    padding: 12px 15px;
}
</style>