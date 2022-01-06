<template>
  <div>
    <nav class="navbar navbar-expand-sm navbar-light bg-light">
      <div class="collapse navbar-collapse">
        <a class="navbar-brand">
          <h4 style="color: #0b4025" class="font-italic">
            Vikendice & avanture
          </h4>
        </a>
        <ul class="navbar-nav ml-auto">
          <li class="nav-item" style="margin-right: 15px">
            <button
              type="button"
              class="btn btn-outline-success"
              data-target="#prijava"
              data-toggle="modal"
            >
              Prijavi se
            </button>
          </li>

          <li class="nav-item">
            <button
              type="button"
              class="btn btn-success"
              data-target="#registracija"
              data-toggle="modal"
            >
              Registruj se
            </button>
          </li>
          <!--<li class="nav-item">
            <button type="button" class="btn btn-success">
              <router-link to="/client">KLIJENT</router-link>
            </button>
          </li>
          <li class="nav-item">
            <button type="button" class="btn btn-success">
              <router-link to="/administrator">Administrator</router-link>
            </button>
          </li>
          <li class="nav-item">
            <button type="button" class="btn btn-success">
              <router-link to="/fishingInstructor"
                >Fishing instructor</router-link
              >
            </button>
          </li>
           <li class="nav-item"> <button  type="button" class="btn btn-success"><router-link to="/cottageOwnerHomePage">Vlasnik vik</router-link></button> </li>
          -->
          <li class="nav-item">
            <button type="button" class="btn btn-success">
              <router-link to="/cottageOwnerHomePage">Vlasnik vik</router-link>
            </button>
          </li>
        </ul>
      </div>
    </nav>

    <!--Modal prijava-->
    <div class="modal fade" id="prijava" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Prijavljivanje
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form" @submit.prevent="Login">
              <div class="form-group">
                <label for="name">E-mail:</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite Vas e-mail"
                  v-model="email"
                />
              </div>
              <div class="form-group">
                <label for="psw"
                  ><span class="glyphicon glyphicon-eye-open"></span>
                  Lozinka:</label
                >
                <input
                  type="password"
                  class="form-control"
                  id="psw"
                  placeholder="Unesite lozinku"
                  v-model="password"
                />
              </div>
              <button
                type="submit"
                class="btn btn-success btn-block"
                @click="Login()"
              >
                <span></span> Uloguj se
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

    <!-- Modal registracija-->
    <div class="modal fade" id="registracija" role="dialog">
      <div class="modal-dialog">
        <!-- Modal content -->
        <div class="modal-content">
          <div class="modal-header">
            <h5
              class="modal-title"
              id="exampleModalLabel"
              style="color: #0b4025; padding: 5px 35px"
            >
              Registracija
            </h5>
          </div>
          <div class="modal-body" style="padding: 15px 50px">
            <form role="form">
              <div class="form-group">
                <label for="name">Ime</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite Vase ime"
                  v-model="newUser.firstname"
                />
                <label for="name">Prezime</label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite Vase prezime"
                  v-model="newUser.lastname"
                />
                <label for="name">Email </label>
                <input
                  type="text"
                  class="form-control"
                  placeholder="Unesite Vas mejl"
                  v-model="newUser.email"
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
                  placeholder="Unesite Vas telefon"
                />

                <div class="form-group">
                  <label>Izaberite odgovarajuci tip registracije</label>
                  <select class="form-control">
                    <option>Vlasnik vikendice</option>
                    <option>Vlasnik broda</option>
                    <option>Instruktor</option>
                    <option>Korisnik</option>
                  </select>
                </div>
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
                  v-model="newUser.password"
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
                @click="register()"
              >
                <span></span> Registruj se
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
export default {
  data() {
    return {
      email: "",
      password: "",
      newUser: {
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        role: "ROLE_CLIENT",
      },
    };
  },
  computed: {
    userRole() {
      return this.$store.getters.getRole;
    },
  },
  methods: {
    async Login() {
      const res = await fetch("http://localhost:8081/api/auth/login", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify({ email: this.email, password: this.password }),
      });
      const data = await res.json();
      localStorage.setItem("token", data.accessToken);
      localStorage.setItem("role", data.role);
      console.log(localStorage.getItem("token"));
      console.log(localStorage.getItem("role"));
      if (localStorage.getItem("role") == "ROLE_CLIENT") {
        this.$router.push({ name: "ClientAllCottages" });
        //this.$router.go(0);
        console.log(localStorage.getItem("token"));
        console.log(localStorage.getItem("role"));
      } else if (localStorage.getItem("role") == "ROLE_COTTAGE_OWNER") {
        this.$router.push({ name: "HomePageInProfil" });
      }
    },
    async register() {
      const res = await fetch("http://localhost:8081/api/auth/signup", {
        method: "POST",
        headers: {
          "Content-type": "application/json",
        },
        body: JSON.stringify(this.newUser),
      });
      const data = await res.json();
      console.log(data);
      if (data != null) {
        alert("Uspesno ste se registrovali!");
      }
    },
  },
};
</script>
