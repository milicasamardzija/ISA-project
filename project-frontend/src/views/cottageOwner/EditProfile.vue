<template>

  <div v-if="this.role === 'ROLE_COTTAGE_OWNER'">
    <NavBarLogOut />
    <NavBarHomePage />
  </div>

  <!--KLIJENT-->
  <div v-if="this.role === 'ROLE_CLIENT'">
    <NavBarClient />
  </div>

  <div>
    <div class="tab-pane active containerInfo">
      <div v-if="this.role === 'ROLE_COTTAGE_OWNER'">
        <Search />
      </div>

      <div class="row-boats" v-if="this.role === 'ROLE_CLIENT' || this.role === 'ROLE_COTTAGE_OWNER'">
        <div class="col-with-picture" style="margin-right: 5%; margin-top: 1%">
          <div>
            <img
              src="../../assets/userImages/alba.jpg"
              class="rounded img-thumbnail img-fluid"
              alt="Responsive image"
              style="max-height: 250px !important; max-width: 300px !important"/>
          </div>
        </div>
        <div class="col-info" style="margin-top: 2%">
          <h5 style="width: 600px" class="text">Ime:</h5>
          <input type="text" class="form-control" v-model="user.firstname"/>
          <h5 style="width: 600px" class="text">Prezime:</h5>
          <input type="text" class="form-control" v-model="user.lastname"/>
          <h5 style="width: 600px" class="text">Broj telefona:</h5>
          <input type="text" class="form-control" v-model="user.telephone"/>
          <h5 style="width: 600px" class="text">Ulica:</h5>
          <input type="text" class="form-control" v-model="user.address.street"/>
          <h5 style="width: 600px" class="text">Broj:</h5>
          <input type="text" class="form-control" v-model="user.address.number"/>
          <h5 style="width: 600px" class="text">Grad:</h5>
          <input type="text" class="form-control"  v-model="user.address.city"/>
          <h5 style="width: 600px" class="text">Drzava:</h5>
          <input type="text" class="form-control" v-model="user.address.country"/>
          <button class="btn btn-secondary"  @click="edit()" style="margin-top:40px">Potvrdi</button>
          <button class="btn btn-secondary" @click="cancel()" style="margin-top:40px; margin-left:430px">Odustani</button>
        </div>
      </div>
    </div>

  </div>


</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import NavBarClient from "../../components/client/NavBarClient.vue";

export default {
  name: "EditProfile",
  components: {
    NavBarLogOut,
    NavBarHomePage,
    NavBarClient
  },
  data(){
    return {
      role: "",
      user: { id: 0, firstname: "", lastname: "", address: { id: "", street:"", number: 0, city: "", country: ""}, telephone: ""}
    }
  },
  methods: {
    async edit() {
      await fetch("http://localhost:8081/api/user/update", {
        method: "POST",
        headers: {
           Authorization: "Bearer " + localStorage.getItem("token"),
           "Content-type": "application/json"
        },
        body: JSON.stringify({ id: parseInt(this.user.id), firstname: this.user.firstname, lastname: this.user.lastname, address: { id: this.user.address.id, street:this.user.address.street, number: this.user.address.number, city: this.user.address.city, country: this.user.address.country }, telephone: this.user.telephone}),
      });
      this.$router.push({ name: "MyProfile" })
    },
    cancel(){ 
      this.$router.push({ name: "MyProfile" });
    },
    async getUser() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
        "Access-Control-Allow-Origin": "*"
      };
      const res = await fetch("http://localhost:8081/api/user/userInfo", {headers});
      const data = await res.json();
      return data;
    }
  },
  async created() {
    this.role = localStorage.getItem("role")
    this.user = await this.getUser();
  },
};
</script>

<style scoped>
.containerInfo {
  margin-left: 5%;
  margin-top: 2%;
}
.row-boats {
  display: flex;
}
.buttonMargin {
  margin-left: 5%;
}
</style>
