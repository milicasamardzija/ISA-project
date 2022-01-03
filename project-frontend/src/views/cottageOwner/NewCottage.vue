<template>
  <div>
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div class="form-group containerInfo">
    <section class="mb-5">
      <!--Section heading-->

      <!--Section description-->

      <div class="row" style="margin-bottom: 1%">
        <!--Grid column-->
        <div class="col-md-8 mb-md-0 mb-3">
          <form id="contact-form" name="contact-form">
            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Naziv vikendice</label>
                </div>
              </div>

              <!--Grid column-->
              <div class="col-md-6">
                <div class="md-form mb-0">
                  <input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                  />
                </div>
              </div>
            </div>
            <!--Grid row-->

            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Adresa</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-3">
                <div class="md-form mb-0">
                  <input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-1">
                <div class="md-form mb-0">
                  <input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                  />
                </div>
              </div>
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                  />
                </div>
              </div>
            </div>

            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Slike</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-6">
                <div class="md-form mb-0"></div>
              </div>
            </div>
            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Opis smestaja</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-6">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="4"
                    class="form-control md-textarea"
                  ></textarea>
                </div>
              </div>
            </div>

            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Pravila ponasanja</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-6">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="2"
                    class="form-control md-textarea"
                  ></textarea>
                </div>
              </div>
            </div>
            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Dodatne usluge</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-6">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="2"
                    class="form-control md-textarea"
                  ></textarea>
                </div>
              </div>
            </div>
          </form>
        </div>
        <!--Button inspo   <div class="text-center text-md-left">
                <a class="btn btn-primary" onclick="document.getElementById('contact-form').submit();">Send</a>
            </div>
            <div class="status"></div>      -->

        <!--Grid column-->
        <div class="col-md-4">
          <div class="row">
            <!--Grid column-->
            <div class="col-md-2">
              <div class="md-form mb-0">
                <label for="name" class="">Cena:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control"
                    aria-label="Amount (to the nearest dollar)"
                  />
                  <div class="input-group-append">
                    <span class="input-group-text">RSD</span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <!--Grid column-->
            <div class="col-md-2">
              <div class="md-form mb-0">
                <label for="name" class="">Broj soba:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="text" class="form-control" />
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <!--Grid column-->
            <div class="col-md-2">
              <div class="md-form mb-0">
                <label for="name" class="">Broj kreveta:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="text" class="form-control" />
                </div>
              </div>
            </div>
          </div>
          <div class="row" style="margin-left: 20%; margin-top: 40%">
            <!--Grid column-->

            <!--Grid column-->
            <div class="col-md-5">
              <div class="md-form mb-2">
                <div class="input-group">
                  <button class="btn btn-success">Sacuvaj vikendicu</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--Grid column-->
      </div>
    </section>
  </div>

  <div></div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";

export default {
  name: "NewCottage",
  components: {
    NavBarLogOut,
    NavBarHomePage,
  },
};
</script>

<style scoped>
.containerInfo {
  margin-top: 1%;
  margin-left: 2%;
}

table {
  max-width: 50%;
}
</style>
