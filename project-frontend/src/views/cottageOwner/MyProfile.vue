<template>
  <div>
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div>
    <div class="tab-pane active containerInfo">
      <Search />
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
        <div class="col-info" style="margin-top: 2%">
          <h5 style="width: 600px" class="text">Ime:</h5>
          <h5 style="width: 600px" class="text">Prezime:</h5>
          <h5 style="width: 600px" class="text">Adresa:</h5>
          <h5 style="width: 600px" class="text">Email:</h5>
          <h5 style="width: 600px" class="text">Kategorija korisnika:</h5>
          <h5 style="width: 600px" class="text">Broj telefona:</h5>

          <button class="btn btn-secondary" v-on:click="editProfile">
            Izmena profila
          </button>
          <button
            class="btn btn-secondary buttonMargin"
            data-target="#password"
            data-toggle="modal"
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

    <!-- Modal za izmenu sifre-->
    <div class="modal fade" id="password" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
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
                  placeholder="Nova lozinka"
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
                  placeholder="Potvrdite lozinku"
                />
              </div>
              <button type="submit" class="btn btn-success btn-block">
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
            <form role="form">
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Da li ste
                  sigurni da zelite da obrisete svoj nalog? Ukoliko jeste,
                  navedite razlog brisanja Vaseg naloga:</label
                >
              </div>

              <div class="form-group">
                <textarea type="text" style="width: 100%; height: 50%">
                </textarea>
              </div>
              <button type="submit" class="btn btn-success btn-block">
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
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";

export default {
  name: "MyProfile",

  components: {
    NavBarLogOut,
    NavBarHomePage,
  },
  methods: {
    editProfile() {},
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
