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

          <button class="btn btn-secondary">Izmena profila</button>
          <button class="btn btn-secondary buttonMargin">
            Promeni lozinku
          </button>
          <button class="btn btn-secondary buttonMargin">Obrisi nalog</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";

export default {
  name: "EditProfile",
  components: {
    NavBarLogOut,
    NavBarHomePage,
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
