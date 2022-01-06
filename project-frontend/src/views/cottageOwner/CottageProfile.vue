<template>
  <div>
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div>
    <div class="tab-pane active containerInfo">
      <div class="row">
        <div class="column" style="width: 24rem; height: 3rem">
          <h4>pakao od zivota</h4>
        </div>
        <div class="column" style="width: 40rem; height: 3rem">
          <h4>Na kraju sveta 45, Negde Daleko</h4>
        </div>
        <div class="column" style="width: 18rem; height: 3rem">
          <button class="btn btn-success">
            <router-link
              style="
                text-decoration: none !important;
                display: inline-block;
                color: white;
              "
              to="/editCottage"
              >Izmeni vikendicu
            </router-link>
          </button>
        </div>
        <div class="column" style="width: 30rem; height: 3rem">
          <button class="btn btn-success">Dodaj akciju</button>
          <button class="btn btn-success">Pretplati se</button>
        </div>
      </div>

      <div class="row">
        <div class="column">
          <img
            class="mainImage"
            src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
          />
        </div>

        <div class="column" style="width: 65rem">
          <div class="row">
            <!-- 1. red malih slika-->
            <div class="column columnSmall" style="width: 60rem; height: 14em">
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="clearfix"></div>
            </div>
          </div>
          <!-- drugi red malih slika-->
          <div class="row" style="height: 14em">
            <div class="column columnSmall2" style="width: 60rem; height: 18em">
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="images">
                <figure>
                  <img
                    src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg"
                    alt="Photo"
                    class="smallImages"
                  />
                </figure>
              </div>
              <div class="clearfix"></div>
            </div>
          </div>
        </div>
      </div>

      <div class="row aboutCottage">
        <div class="column columnAbout">
          <h4>Opis smestaja</h4>
          <label>
            Vikendica Pustolov na Uvcu nalazi se uz obalu Sjeničkog (Uvačkog
            jezera), na Zlatar planini. Udaljena je od grada Sjenice 40 km, a od
            Nove Varoši 17 km. Vikendica poseduje sopstveno ogradjeno dvorište,
            slobodno za parking gostiju. Na ulazu u vikendicu nalazi se terasa
            pogodna za sedenje i sunčanje, odakle se pruža i direktan pogled na
            jezero kao i šume u okolini. U prizemlju vikendice su dnevni boravak
            sa TV-om i free WiFi-om, kaučima za uživanje ili spavanje, kuhinja
            sa frižiderom i komplet opremljena za spremanje hrane kao i kanister
            sa pijaćom vodom. Kupatilo je takodje opremljeno kozmetičkim i
            higijenskim preparatima i fenom za kosu. Na spratu su dve spavaće
            sobe sa krevetima, čivilucima i prozorima sa pogledom na
            jezero/šumu. Svaki gost dobija svoju posteljinu i po dva peškira
            (veliki i mali) i papuče. Moguće je poručiti domaću hranu koja se
            donosi u vikendicu. Kapacitet je 6 osoba. Vikendica je petfriendly i
            pušenje je dozvoljeno. U okolini je moguće iznajmiti čamce kao i
            zakupiti dnevne ture krstarenja jezerom i meandrima reke Uvac. Pored
            vodenih aktivnosti teren je pogodan i za planinarenje do prelepih
            vidikovaca koji gledaju na meandre idealne za fotografisanje.
          </label>
        </div>

        <div class="column" style="padding-left: 10rem">
          <h4>Informacije</h4>
          <div class="row" style="height: 20rem; background: whitesmoke">
            <div class="column" style="width: 20rem">
              <p>Cena:</p>
              <p>Ocena:</p>
              <p>Maksimalan broj osoba:</p>
              <p>Broj soba:</p>
              <p>Broj kreveta po sobi:</p>
              <p>Pravila ponasanja:</p>
            </div>

            <div
              class="column"
              style="width: 10rem; height: 18rem; background: white"
            >
              <p class="pStyle">dodatne usluge:</p>
              <p>WIFI</p>
              <p>Dva parking mesta</p>
              <p>Klima</p>
            </div>
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
  name: "CottageProfile",
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
.mainImage {
  height: 30em;
  width: 45em;
}
.aboutCottage {
  margin-top: 3.5em;
  height: 35em;
}
.column {
  float: left;
  width: 45em;
  padding: 10px;
  height: 28em;
}
.columnAbout {
  float: left;
  width: 55em;
  padding: 10px;
  height: 28em;
}
.columnSmall {
  float: left;
  width: 45em;
  margin-left: 10px;
  margin-top: -12px;
  height: 14em;
}
.columnSmall2 {
  float: left;
  width: 45em;
  margin-left: 10px;
  margin-top: 15px;
  height: 14em;
}
/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}
.backgroundInfo {
  background: whitesmoke;
}
.pStyle {
  font-size: 18px;
  font-weight: bold;
  font-style: italic;
}
.smallImages {
  height: 15em;
  width: 18em;
}
.images {
  float: left;
  border-style: solid;
  border-width: 2px;
  margin: 3px;
  height: 15em;
  width: 18em;
  overflow: hidden;
  text-align: center;
}

.clearfix {
  clear: both;
}
</style>
