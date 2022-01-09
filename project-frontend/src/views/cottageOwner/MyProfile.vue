<template>

  <div v-if="this.role === 'ROLE_COTTAGE_OWNER'">
    <NavBarLogOut />
    <NavBarHomePage />
  </div>

  <!--KLIJENT-->
  <div v-if="this.role === 'ROLE_CLIENT'">
    <NavBarClient />
  </div>
 
  <div v-if="this.normal">
      <div style="margin-top:80px;margin-left:50px">
        <div class="tab-pane active containerInfo">
          <div class="row-boats">
            <div class="col-with-picture" style="margin-right: 5%; margin-top: 1%">
              <div>
                <img
                  src="../../assets/userImages/alba.jpg"
                  class="rounded img-thumbnail img-fluid"
                  alt="Responsive image"
                  style="max-height: 250px !important; max-width: 300px !important"
                />
              </div>
            </div>

            <div>
              <!--KLIJENT-->
              <div class="col-info" style="margin-top: 2%" v-if="this.role === 'ROLE_CLIENT'">
                <h5 style="width: 600px" class="text">Ime: {{client.firstname}}</h5>
                <h5 style="width: 600px" class="text">Prezime: {{client.lastname}}</h5>
                <h5 style="width: 600px" class="text">Adresa: {{client.address.street}} {{client.address.number}}, {{client.address.city}}, {{client.address.country}}</h5>
                <h5 style="width: 600px" class="text">Email: {{client.email}}</h5>
                <h5 style="width: 600px" class="text">Broj telefona: {{client.telephone}}</h5>
                <h5 style="width: 600px" class="text">Kategorija korisnika: {{client.loyalityType}}</h5>
                <h5 style="width: 600px" class="text">Kategorija korisnika: {{client.poents}}</h5>
                <h5 style="width: 600px" class="text">Kategorija korisnika: {{client.penals}}</h5>
              </div>

              <!--VLASNIK VIKENDICE-->
              <div class="col-info" style="margin-top: 2%" v-if="this.role === 'ROLE_COTTAGE_OWNER'">
                <h5 style="width: 600px" class="text">Ime: </h5>
                <h5 style="width: 600px" class="text">Prezime: </h5>
                <h5 style="width: 600px" class="text">Adresa: </h5>
                <h5 style="width: 600px" class="text">Email:</h5>
                <h5 style="width: 600px" class="text">Broj telefona:</h5>
                <h5 style="width: 600px" class="text">Kategorija korisnika: </h5>
                <h5 style="width: 600px" class="text">Kategorija korisnika: </h5>
                <h5 style="width: 600px" class="text">Kategorija korisnika: </h5>
              </div>

              <div style="margin-top:40px">
                <button class="btn btn-secondary" v-on:click="editProfile">
                  Izmena profila
                </button>
                <button
                  class="btn btn-secondary buttonMargin"
                  @click="changePass()"
                >
                  Promeni lozinku
                </button>
                <button
                  class="btn btn-secondary buttonMargin"
                  data-target="#deleteAccount"
                  data-toggle="modal"
                >
                  Obrisi nalog
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
     </div>

    <div v-if="normal == false" style="margin-top:40px;margin-left:40px">
      <table>
        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Nova
                  lozinka</label
                ></td>
                <td> <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Stara lozinka" 
                /></td></tr>
        <tr>
          <td>
                <label ><span class="glyphicon glyphicon-eye-open"></span> Nova
                  lozinka</label
                ></td>
                <td> <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Nova lozinka" 
                /></td></tr>
                 <tr><td><label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Ponovite
                  unos lozinke</label
                ></td>
                <td><input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Potvrdite lozinku"  v-model="this.repeatPassword"
                /></td></tr>
              <tr><td><button type="submit" class="btn btn-success btn-block" @click="changePassword()" style="width:100px;margin-top:20px">
                <span></span> Potvrdi
              </button></td>
              <td><button
                type="button" style="width:100px;margin-top:20px;margin-left:200px"
                class="btn btn-outline-secondary pull-left" @click="backToNormal()">
                Odustani
              </button></td>
          </tr>
          </table>
              
    </div>

    <!-- Modal za izmenu sifre
    <div class="modal fade" id="password" role="dialog">
      <div class="modal-dialog">
      
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Izmena lozinke
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Trenutna
                  lozinka</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Unesite lozinku"
                />
              </div>
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Nova
                  lozinka</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Nova lozinka" v-model="this.newPassword"
                />
              </div>
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Ponovite
                  unos lozinke</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Potvrdite lozinku"  v-model="this.repeatPassword"
                />
              </div>
              <button type="submit" class="btn btn-success btn-block" @click="changePassword()">
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
    </div>-->

    <!-- Modal za brisanje naloga-->
    <div class="modal fade" id="deleteAccount" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Brisanje naloga
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" >
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Da li ste
                  sigurni da zelite da obrisete svoj nalog? Ukoliko jeste,
                  navedite razlog brisanja Vaseg naloga:</label
                >
              </div>

              <div class="form-group">
                <textarea type="text" style="width: 100%; height: 50%" v-model="explanation">
                </textarea>
              </div>
              <button type="submit" class="btn btn-success btn-block" @click="sendDeleteRequest()">
                 Potvrdi
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

</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import NavBarClient from "../../components/client/NavBarClient.vue";

export default {
  name: "MyProfile",

  components: {
    NavBarLogOut,
    NavBarHomePage,
    NavBarClient
  },
  data(){
    return {
      role: "",
      client: "",
      newPassword: "",
      repeatPassword: "",
      explanation: "",
      normal: true
    }
  },
  methods: {
    editProfile() {
      this.$router.push({ name: "EditProfile" });
    },
    async getClient() {
      const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      const res = await fetch("http://localhost:8081/api/client/profileClient", {headers});
      const data = await res.json();
      return data;
    },
    changePass(){
      this.normal = false;
    },
    async changePassword(){
      console.log(localStorage.getItem("token"))
      if (this.repeatPassword != this.newPassword){
        alert("Niste dobro uneli ponovljenu sifru!Probajte ponovo!");
      } else {
        const res = await fetch("http://localhost:8081/api/user/changePassword" , {
          method: "POST",
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
            'Content-Type': 'application/json;charset=UTF-8',
            Accept: 'application/json',
          },
          body: JSON.stringify({password: this.newPassword})
        });
        const data = await res.json();
        localStorage.setItem("token", data.accessToken);
        alert("Molimo ulogujte se ponovo sa novom sifrom!")
        this.$router.push({ name: "BoatsStartPage" });
        }
    },
    async sendDeleteRequest(){
      const res = await fetch("http://localhost:8081/api/userDeleteReq" , {
        method: "POST",
        headers: {
          "Content-type": "application/json",
           Authorization: "Bearer " + localStorage.getItem("token")
        },
        body: JSON.stringify({explanation: this.explanation})
      });
     const ret = await res.data
     console.log(ret)
    },
    backToNormal(){
      this.normal = true;
    }
  },
  async created() {
    this.role = localStorage.getItem("role")
    this.client = await this.getClient();
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
