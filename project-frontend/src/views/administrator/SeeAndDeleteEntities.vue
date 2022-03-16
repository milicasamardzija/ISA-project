<template>
      <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator />
  </div>
  <div>
<table class="styled-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Naziv</th>
            <th>Ocena</th>
            <th>Cena</th>
            <th>Promo opis</th>
            <th>Pravila</th> 
            <th>Adresa</th>  
            <th></th>         
        </tr>
    </thead>
    <tbody>
          <tr v-for="(entity, index) in entities" :key="index">
                  <td>{{entity.id}}</td>
                  <td>{{entity.name}}</td>
                  <td>{{entity.grade}}</td>
                  <td>{{entity.price}} €</td>
                  <td>{{entity.promoDescription}}</td>
                  <td>{{entity.rules}}</td>
                  <td>{{entity.address.street}} {{entity.address.number}} {{entity.address.city}}</td>
                  <td><button class="btn btn-success btn-block" data-target="#brisanje" data-toggle="modal" @click="saveId(entity.id)">Obrisi entitet</button></td>
           </tr> 
    </tbody>
</table>
  </div>

  <div class="modal fade" id="brisanje" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Da li ste sigurni da zelite da obrisete entitet?
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
             <form role="form">
              <div class="form-group">
                <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="deleteEntity()"
              >Da</button>
              </div>
              <button
                type="submit"
                class="btn btn-success btn-block"
                 @click="refreshPage()"
              >
                <span></span> Ne
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import axios from 'axios';
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";

export default {
  name: "SeeAndDeleteEntities",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
  },
    data() {
    return {
        deleteId: 0,
        entities: "",
        entity: { id:0, name: "", address: { id: "", street:"", number: 0, city: ""}, promoDescription: "", rules:"", price:0, grade: 0}
    }
  },
  methods: {
    async getEntities() {
      const res = await fetch("http://localhost:8081/api/entities/getAllEntities");
      const data = await res.json();
      return data;
    },
    async refreshPage() {
        this.deleteId = 0;
        this.$router.push({ name: "SeeAndDeleteEntities" });
        this.$router.go(0);
    },
    async saveId(id) {
        this.deleteId = id;
    },
    async deleteEntity() {
        const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
        axios.delete("http://localhost:8081/api/entities/deleteEntity/"+this.deleteId,{headers})
        .then (response => { 
        console.log(response);
        this.$router.push({ name: "SeeAndDeleteEntities" });
      })  
      alert("Obrisali ste entitet!")
      this.$router.go(0); 
    }
  },
    async created() {
      this.entities = await this.getEntities();
  },
}

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