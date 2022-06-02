<template>
<div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
</div>
<div>
    <h1>Dodavanje usluge</h1>
          <table>
        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Naziv avanture</label></td>
                <td> <input class="form-control"  id="psw" v-model="nameOfAdventure"/></td></tr>

       <tr>
         <td>
           <label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Maksimalan broj ljudi </label></td>
          <td><input  type="number" class="form-control" id="psw" v-model="maxNumberOfPeople"/></td></tr>
  
       <tr>
          <td>
              <label ><span class="glyphicon glyphicon-eye-open"></span> Biografija instruktora</label></td>
          <td> <input class="form-control" id="psw" v-model="instructorBiografy"/></td></tr>

       <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Opis usluge</label></td>
          <td> <input class="form-control" id="psw" v-model="promoDescription"/></td></tr>

        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Potrebna oprema</label></td>
          <td> <input class="form-control" id="psw"  v-model="fishingEquipment"/></td></tr>

            <tr>
                <td><label ><span class="glyphicon glyphicon-eye-open"></span> Izaberi uslov otkaza rezervacije</label
                ></td>
                <td><select class="form-control" v-model="cancelationType">
                    <option value="BESPLATNO">Besplatno</option>
                    <option value="SA_PROCENTOM">Sa procentom</option>
                  </select></td>
            </tr>
          <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span>Pravila</label
                ></td>
                <td> <input
                  class="form-control"
                  id="psw"
                  v-model="rules"
                /></td></tr>

        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Adresa</label></td>
          <td> <input class="form-control" id="psw"  v-model="street"/></td>
          <td> <input class="form-control" id="psw"  v-model="number"/></td>
          </tr>
        <tr>
          <td> <input class="form-control" id="psw"  v-model="city"/></td>
          <td> <input class="form-control" id="psw"  v-model="country"/></td>
          </tr>

              <tr><td><button class="btn btn-success btn-block" style="width:100px;margin-top:20px" @click="GoBack()">
                <span></span> Odustani
              </button></td><td><button type="submit" class="btn btn-success btn-block" style="width:100px;margin-top:20px" @click="AddAdventure()">
                <span></span> Potvrdi
              </button></td></tr>

          </table>
</div>
</template>

<script>
import axios from "axios";
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
export default ({
    name: "AddAdventure",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut
    },
    data() {
        return {
            adventures:"",
            nameOfAdventure:"",
            maxNumberOfPeople:0,
            instructorBiografy:"",
            promoDescription:"",
            fishingEquipment:"",
            cancelationType:"",
            rules:"",
            street:"",
            number:0,
            city:"",
            country:""
            }
    },
    methods: {
        async GoBack() {
            this.$router.push({ name: "MyService" });
        },
        async AddAdventure() {

        const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.post("http://localhost:8081/api/adventures/addAdventure",{ 
        nameOfAdventure : this.nameOfAdventure, 
        maxNumberOfPeople : this.maxNumberOfPeople, 
        instructorBiografy : this.instructorBiografy, 
        promoDescription : this.promoDescription, 
        fishingEquipment : this.fishingEquipment, 
        cancelationType : this.cancelationType,
        rules: this.rules,
        street: this.street,
        number: this.number,
        city: this.city,
        country: this.country
       },{
        headers
      })
      .then (response => { 
        console.log(response);
        this.$router.push({ name: "AddAdventure" });
      }) 

        alert("Dodali ste avanturu!")
      this.$router.go(0);
        }
    },


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
.styled-table thead tr {
    background-color: #4caf50;
    color: #ffffff;
    text-align: left;
}
button {
  background-color: #4caf50; /* Green */
}
.styled-table th,
.styled-table td {
    padding: 12px 15px;
}
</style>

