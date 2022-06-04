<template>
    <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
</div>

  <div>
        <div  style="width: 22rem; height: 3rem">
        <table> <tr> <td><h3>Naziv avanture</h3></td><td><h4>{{adventure.nameOfAdventure}}</h4></td> </tr></table>
        </div>
        <div  style="width: 35rem; height: 3rem">
       <table><tr> <td><h3>Adresa avanture</h3></td><td><h4>{{adventure.address.street}} {{adventure.address.number}}, {{adventure.address.city}}, {{adventure.address.country}},</h4></td></tr></table>  
        </div>
      </div>
        <div  style="width: 35rem; height: 3rem">
       <table><tr><td><h3>Opis smestaja</h3></td><td><h4>{{adventure.promoDescription}}</h4></td></tr></table>  
        </div>
         <div style="width: 35rem; height: 3rem">
         <table><tr><td><h3> Pravila ponasanja</h3></td><td><h4>{{adventure.rules}} </h4></td></tr></table>  
        </div>       

         <div  style="width: 35rem; height: 3rem">
         <table>
          <tr><td><h3> Informacije</h3></td><h4>Cena: {{adventure.price}}</h4><td></td></tr>
          <tr><td></td><td><h4>Ocena: {{adventure.grade}}</h4></td></tr>
          <tr><td></td><td><h4>Navigaciona oprema: {{adventure.fishingEquipment}}</h4></td></tr>
          <tr><td></td><td><h4>Dozvoljen broj ljudi: {{adventure.maxNumberOfPeople}}</h4></td></tr>
          <tr><td></td><td><h4>Biografija instruktora: {{adventure.instructorBiografy}}</h4></td></tr>
          <tr><td></td><td> <button @click="AddAction()">Dodaj akciju</button></td></tr>
          </table>
        </div>  
       

</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";
export default ({
    name: "MyAdventureReservations",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut
    },
    data() {
        return {
          nameOfAdventure:"",
          adventure:{ id:0,realName:"", nameOfAdventure:"",maxNumberOfPeople:0,instructorBiografy:"",promoDescription:"",fishingEquipment:"",cancelationType:"",rules:"",address: {  street:"",number:"",city:"",country:""} 
            ,price:0, grade:0},
          additionalSetting: {name:"", price:0.0, id:0}
        }
    },
    methods: {
        async getAdventuree() {
                            const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res1 = await fetch("http://localhost:8081/api/adventures/getAdventure/"+this.nameOfAdventure,{headers});
           const data1 = await res1.json();
      return data1;
        },
        async AddAction() {
          localStorage.setItem("nameOfAdventure",this.nameOfAdventure);
          this.$router.push({ name: "CreateAction" });

        }
    },
    async created() {
        this.nameOfAdventure = localStorage.getItem("nameOfAdventure");
        this.adventure =  await this.getAdventuree();
        console.log(this.adventure.nameOfAdventure)
    } 
})
</script>

<style scoped>
h5,h4,label,p {

    font-family: sans-serif;   
}
h3 {
    background-color: #4caf50;
    color: #ffffff;
    text-align: left;
}
button {
  background-color: #4caf50; /* Green */
}
</style>

