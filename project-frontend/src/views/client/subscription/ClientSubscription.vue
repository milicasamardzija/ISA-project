<template>
<div v-if="this.role === 'ROLE_CLIENT'">
  <div>
    <NavBarClient />
  </div>
  <h2 style="margin-left: 40px; margin-top: 40px" v-if="subscriptions.length != 0">
          <i>Entiteti na koje ste pretplaceni:</i>
        </h2>
  <div class="containerInfo">
    <div class="tab-pane container active">
      <div class="row-boats" v-for="(subscription, index) in subscriptions"
          :key="index">
        <div class="col-with-picture">
          <div>
            <img
              :src="subscription.images[0]"
              class="rounded img-thumbnail img-fluid"
              alt="Responsive image"
              style="margin-top: 1rem; max-height: 300px !important; max-width: 320px !important"
            />
          </div>
        </div>
        <div class="col-info">
          <h4 style="width: 600px" class="text">Promotivni opis: {{subscription.promoDescription}}</h4>
          <h4 style="width: 600px" class="text">Naziv: {{subscription.name}}</h4>
          <h4 style="width: 600px" class="text">Adresa: {{subscription.address.street}} {{subscription.address.number}}, {{subscription.address.country}}, {{subscription.address.city}}</h4>
          <h4 style="width: 600px" class="text">Prosecna ocena: {{subscription.grade}}</h4>
          <button class="btn btn-danger" style="margin-top: 40px;" type="button" data-target="#odjava" data-toggle="modal" @click="getSelected(subscription.id)">Otkazi</button>
        </div>
      </div>
    </div>
  </div>

  <!-- Modal za odjavu na akciju -->
  <div class="modal fade" id="odjava" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel" style="color: #0b4025; padding: 5px 35px">
              Odjava
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <div class="form-group">
                <label for="name">Da li ste sigurni da zelite da se odjavite sa ove pretplate?</label>
              </div>
              <table>
                <tr>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" @click="cancel()" style="width:80px; margin-bottom:20px">
                      Potvrdi
                    </button>
                  </td>
                  <td>
                    <button type="submit" class="btn btn-success btn-block" data-dismiss="modal" style="width:80px; margin-left:230px; margin-bottom:20px">
                      Otkazi
                    </button>
                  </td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>

    <div v-if="subscriptions.length === 0">
      <p style="font-size: 20px; margin-top:60px; margin-left:80px">Nemate nijedan enitet na koji ste pretplaceni.</p>
    </div>
  </div>
</template>

<script>
import NavBarClient from "../../../components/client/NavBarClient.vue";
import axios from 'axios'
import Swal from 'sweetalert2';

export default {
  name: "ClientSubscription",
  components: {
    NavBarClient,
  },
  data(){
    return{
      subscriptions: [],
      selectedId: 0
    }
  },
  methods:{
    async fetchSubscriptions() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
     axios.get(process.env.VUE_APP_BACKEND_URL+ "/api/client/subscribedEntitites" ,{headers})
      .then (response => { 
        console.log(response);
        this.subscriptions = response.data;
          
      })
    },
    getSelected(id){
      this.selectedId = id;
    },
    cancel() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      axios.delete(process.env.VUE_APP_BACKEND_URL+ "/api/client/" + this.selectedId,{headers})
      .then (response => { 
        console.log(response);
        this.subscriptions =  this.fetchSubscriptions();
          new Swal({
             title:"Obavestenje",
             type: "warning",
             text:'Uspesno ste se odjavili sa pretplate!'
           });
      })
    }
  },
   created() {
    this.role = localStorage.getItem("role");
    this.subscriptions =  this.fetchSubscriptions();
  }
};
</script>

<style scoped>
.row-boats {
  display: flex;
}

.text {
  text-align: left;
  font-size: 15px;
  font-weight: 600;
  margin-top: 1%;
  margin-bottom: 1%;
}

.col-info {
  margin-left: 30px;
  margin-top: 10px;
}

.row {
  width: 660%;
  padding-left: 60px;
  height: 10%;
  margin-top: 100px;
  margin-left: 5000px;
  margin-right: 500px;
  opacity: 1.2;
  border-radius: 10px;
  align-content: center;
  background-color: rgba(255, 255, 255, 0.288);
  box-shadow: 0 5px 4px rgb(0 0 0 / 30%), 0 1px 1px rgb(0 0 0 / 22%);
}
/* kartica u okviru stavke menija  */
.containerInfo {
  width: 97%;
  margin-top: 20px;
  margin-left: 20px;
  margin-right: 10px;
  opacity: 1;
  border-radius: 10px;
  align-content: center;
  position: relative;
  background-color: fff;
  box-shadow: 0 19px 40px rgb(0 0 0 / 30%), 0 15px 12px rgb(0 0 0 / 22%);
}
.col-with-picture {
  margin-top: 1%;
  margin-bottom: 1%;
}
</style>
