<template>
      <div>
  <div v-if="this.userRole === 'ROLE_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator />
  </div>
    <div v-if="this.userRole === 'ROLE_PREDEF_ADMIN'">
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarPredefAdministrator />
  </div>
  </div>
    <div>
            <form role="form">
              <div class="form-group">
                <label for="name">Ime</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite ime"
                />
                <label for="name">Prezime</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite prezime"
                />
                <label for="name">Email </label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite mejl"
                />

                <label for="text">Adresa</label>
                <div class="form-group">
                  <table>
                    <tr>
                      <td>
                        <input
                          type="text"
                          class="form-control"
                          placeholder="Ulica"
                        />
                      </td>

                      <td>
                        <input
                          type="text"
                          class="form-control"
                          placeholder="Broj"
                        />
                      </td>
                    </tr>
                    <tr>
                      <td>
                        <input
                          type="text"
                          class="form-control"
                          placeholder="Grad"
                        />
                      </td>
                      <td>
                        <input
                          type="text"
                          class="form-control"
                          placeholder="Drzava"
                        />
                      </td>
                    </tr>
                  </table>
                </div>
                <label for="name">Broj telefona</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite telefon"
                />

              </div>
               <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span>
                  Lozinka</label
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
              <button
                type="submit"
                class="btn btn-success btn-block"
              >
                <span></span> Registruj admina
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

</template>
<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage.vue";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator.vue";
import NavBarLogOut from "../../components/administrator/NavBarLogOut.vue";
import NavBarPredefAdministrator from "../../components/administrator/NavBarPredefAdministrator.vue";

export default {
  name: "AddAdministrator",

  components: {
    NavBarLogOut,
    HeaderStartPage,
    NavBarAdministrator,
    NavBarPredefAdministrator,
  },
  data() {
      return {
      userRole:"",
      }
  },
    async created() {
    this.userRole = localStorage.getItem("role");
  },
}

</script>
