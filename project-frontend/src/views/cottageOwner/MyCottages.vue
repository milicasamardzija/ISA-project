<template>
  <div>
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div class="">
    <div class="tab-pane active containerInfo">
      <table style="width: 80%">
        <tr>
          <td><Search /></td>
          <td style="width: 28%">
            <button class="btn btn-danger" style="margin-left: 39%">
              <router-link
                style="
                  text-decoration: none !important;
                  display: inline-block;
                  color: white;
                "
                to="/newCottage"
              >
                Dodaj novu vikendicu</router-link
              >
            </button>
          </td>
          <td></td>
        </tr>
      </table>

      <div class="row-boats">
        <div class="col-with-picture" style="margin-right: 5%; margin-top: 1%">
          <div>
            <img
              src="../../assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
              class="rounded img-thumbnail img-fluid"
              alt="Responsive image"
              style="max-height: 250px !important; max-width: 300px !important"
            />
          </div>
        </div>
        <div class="col-info" style="margin-top: 3%">
          <h4 style="width: 600px" class="text">Promotivni opis:</h4>
          <h4 style="width: 600px" class="text">Naziv:</h4>
          <h4 style="width: 600px" class="text">Adresa:</h4>
          <h4 style="width: 600px" class="text">Prosecna ocena:</h4>
        </div>
        <div class="col-info" style="margin-left: 10%; margin-top: 4%">
          <div class="row" style="margin-bottom: 15%">
            <button
              class="btn btn-secondary"
              data-target="#delete"
              data-toggle="modal"
            >
              Obrisi vikendicu
            </button>
          </div>
          <div class="row">
            <button class="btn btn-success">
              <router-link
                style="
                  text-decoration: none !important;
                  display: inline-block;
                  color: white;
                "
                to="/cottageProfile"
                >Prikazi vikendicu
              </router-link>
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal za brisanje-->
    <div class="modal fade" id="delete" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Brisanje vikendice
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span> Da li ste
                  sigurni da zelite da obrisete ovu vikendicu?</label
                >
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
import Search from "../../components/cottageOwner/Search.vue";
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";

export default {
  name: "MyCottages",
  components: {
    Search,
    NavBarLogOut,
    NavBarHomePage,
  },
};
</script>

<style scoped>
.containerInfo {
  margin-top: 1%;
  margin-left: 5%;
}
.row-boats {
  display: flex;
}
</style>
