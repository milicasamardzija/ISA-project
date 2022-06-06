<template>
<div v-if="this.role ==='ROLE_COTTAGE_OWNER'"> 
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
        <div class="col-md-7 mb-md-0 mb-3">
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
              <div class="col-md-7">
                <div class="md-form mb-0">
                  <input
                    type="text"
                    id="name"
                    name="name"
                    placeholder="Naziv"
                    v-model="cottage.name"
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
                    placeholder="Ulica"
                      v-model="cottage.address.street"
                  />
                </div>
              </div>
              <div class="col-md-2">
                <div class="md-form mb-2">
                  <input
                    type="number"
                    id="name"
                    name="name"
                    class="form-control"
                    placeholder="broj"
                      v-model="cottage.address.number"
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
                    placeholder="Grad"
                      v-model="cottage.address.city"
                  />
                </div>
              </div>
            </div>

            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class=""></label>
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
                    placeholder="drzava"
                      v-model="cottage.address.country"
                  />
                </div>
              </div>
              <div class="col-md-2">
                <div class="md-form mb-2">
                  <input
                    type="number"
                    id="name"
                    name="name"
                    class="form-control"
                    placeholder="longitude"
                      v-model="cottage.address.longitude"
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
                    placeholder="latitude"
                      v-model="cottage.address.latitude"
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
              <!--Grid column   id="input-b6"  name="input-b6[]"-->
              <div class="col-md-6">
                <div class="md-form mb-0">

                   <div class="upload-images">
                    <input type="file"  @change="imageAdded"/>
                </div> <br/>
                <div v-if="imagesFrontend" class="images-preview">
                        <div v-for="image in imagesFrontend" :key="image">
                            <img :src="image" />
                        </div>
                </div> 
                </div>
              </div>
            </div>

             

            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class=""></label>
                </div>
              </div>
              <!--Grid column  OVDE DA SE IZLISTAJU UPLOADOVANE SLIKE-->
              <div class="col-md-6">
                <div class="md-form mb-0">
                  <div></div>
                </div>
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
              <div class="col-md-7">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="4"
                    class="form-control md-textarea"
                      v-model="cottage.promoDescription"
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
              <div class="col-md-7">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="2"
                    class="form-control md-textarea"
                      v-model="cottage.rules"
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
              <div class="col-md-4">
                <div class="md-form">
                  <input
                    type="text"
                    id="message"
                    name="message"
                    placeholder="naziv"
                    rows="2"
                    class="form-control "
                      v-model="adServ.name"
                 /> 
                </div>
              </div>
                         <!--Grid column-->
                 <!--Grid column-->
            <div class="col-md-2">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control"
                      v-model="adServ.price"
                    aria-label="Amount (to the nearest dollar)"
                  />
                  <div class="input-group-append">
                    <span class="input-group-text">RSD</span>
                  </div>
                </div>
              </div>
            </div>
             
               <div class="col-md-1">
                <div class="md-form">
                 <button class="btn btn-secondary" type="button" @click="addService()">  Dodaj </button>
                </div>
                
              </div>
              <!--Grid column
              <div class="col-md-6">
                <div class="md-form">
                  <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="2"
                    class="form-control md-textarea"
                      v-model="cottage.additionalServices"
                  ></textarea>
                </div>
              </div>-->
            </div>


            <!--Tabela za ispis dodatih dodatnih usluga-->
         
            <!--Grid row-->
            <div class="row" style="margin-bottom: 1%;">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class=""></label>
                </div>
              </div>
                     <!--Grid column v-if="cottage.additionalServices.length != 0"-->
              <div class="col-md-10" >
                <div class="form control" v-for="adS in cottage.additionalServices" :key="adS">
                 <table> 
                   <tr style="width: 55rem"> <td >Naziv dodatne usluge:  </td><td style="width: 5rem"> </td> <td>Cena </td> </tr>
                   
                    <tr> <td>{{adS.name}} </td> <td> </td><td>{{adS.price}} din  </td> </tr>
                   </table> 
                </div>
              </div>
             
            </div>
          </form>
        </div>

        <!--Grid column-->
        <div class="col-md-5">
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
                      v-model="cottage.price"
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
                  <input type="text" class="form-control"   v-model="cottage.roomsNumber"/>
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
                  <input type="text" class="form-control" v-model="cottage.bedsByRoom"/>
                </div>
              </div>
            </div>
          </div>

       <div class="row" style="width: 100%" >
            <!--Grid column-->
 <div class="col-md-12">
  <div class="map-style">

    <ol-map :loadTilesWhileAnimating="true" :loadTilesWhileInteracting="true" style="height:400px">

    <ol-view ref="view" :center="center" :rotation="rotation" :zoom="zoom" 
    :projection="projection" />

           <ol-tile-layer>
          <ol-source-osm />
          </ol-tile-layer>

           <ol-vector-layer>
        <ol-source-vector ref="vectors" >
           <ol-interaction-draw @drawstart="drawstart" :type="drawType" :v-bind="this.cottage.address.street" >
        </ol-interaction-draw>
        </ol-source-vector>

         <ol-style>
        <ol-style-icon :src="markerIcon" :scale="0.2" ></ol-style-icon>
      </ol-style>

    </ol-vector-layer>
    

          </ol-map>
        </div>
        </div>
          </div>

               <div class="row" style="margin-left: 50%; margin-top: -10%">
            <!--Grid column-->

            <!--Grid column-->
            <div class="col-md-5">
              <div class="md-form mb-2">
                <div class="input-group">
                  <button class="btn btn-success" type="button" @click="createCottage()">Sacuvaj vikendicu</button>
                </div>
              </div>
            </div>
          </div>
   
        </div>
        <!--Grid column-->
      </div>
    </section>
  </div>
</div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import axios from 'axios';
import markerIcon from "../../assets/logo.png"



//import $ from "jquery";

import {ref} from 'vue'

export default {
  name: "NewCottage",
  data(){


   const center = ref([20,44 ])
        const projection = ref('EPSG:4326')
        const zoom = ref(8)
        const rotation = ref(0)

         const markers = ref(null);
    const drawType = ref("Point")
   //const drawedMarker = ref()
    const vectors = ref([]);
          const radius = ref(2)
        const strokeWidth = ref(10)
        const strokeColor = ref('red')
        const fillColor = ref('white')
        const coordinate = ref([20,44 ])



  

         const drawstart = (event) => {
      //vectors.value.source.removeFeature(drawedMarker.value);
      //drawedMarker.value = event.feature;
     // this.cottage.address.street = event.feature.values.geometryChangeKey_.target.flatCoordinates[0];
      
     this.cottage.address.longitude = event.feature.values_.geometry.flatCoordinates[0]; //ISPISUJE
      this.cottage.address.latitude= event.feature.values_.geometry.flatCoordinates[1];
      console.log(event)

    }

    return {
      images: "", 
      markerIcon,
        markers,
      drawType,
        vectors,
      drawstart,
       imagesFrontend: [],
       role: "",
      adServ: {name:"", price: 0},
      cottage: {id: 0, name: "",roomsNumber: 0, bedsByRoom: 0, address: { street: "", number: 0, city: "", country: "", longitude: 0, latitude: 0,  id: 0}, promoDescription:"", rules: "", grade: 1.0 , images: [], price: 0, additionalServices: [] },
     center,
       projection,
        zoom,
       rotation,
              radius,
            strokeWidth,
            strokeColor,
            fillColor,
            coordinate,


            }

  },
      
  
    components: {
    NavBarLogOut,
    NavBarHomePage,
  },
  methods: {
     addService(){
      this.cottage.additionalServices.push({ name: this.adServ.name, price: this.adServ.price});
      console.log(this.cottage.additionalServices)
        },

      createCottage(){
   
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      console.log(this.images);
      axios.post(process.env.VUE_APP_BACKEND_URL+"/api/cottages/newCottage/", this.cottage,  {headers}).then( response => response.json());
   this.$router.push({name: "MyCottages"});
      },
               imageAdded(e) {
        
                const file = e.target.files[0];  
                console.log(file)        
                this.createBase64Image(file);
                this.imagesFrontend.push(URL.createObjectURL(file));
            },
            createBase64Image(file){
                const reader= new FileReader();
            
                reader.onload = (e) =>{
                    let img = e.target.result;
                     console.log(img)  
                    this.cottage.images.push(img);
                }
                reader.readAsDataURL(file);
            }, 


  },
  async created(){
this.role= localStorage.getItem("role");
  }
    

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
    .images-preview img{
        width:10%;
        height: 10%;
    }

  .map-style{
    height: 20%;
    width: 50%;
  }
  .ol-style-icon{
    height: 2px; 
    width: 2px;
  }
</style>
