<template>
  <div>
    <NavBarLogOut />
    <NavBarBoatOwner />
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
                  <label for="name" class="">Naziv broda</label>
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
                    v-model="boat.name"
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
                      v-model="boat.address.street"
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
                      v-model="boat.address.number"
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
                      v-model="boat.address.city"
                  />
                </div>
              </div>
            </div>
    <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Koordinate</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                    placeholder="Ulica"
                      v-model="boat.address.longitude"
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
                      v-model="boat.address.latitude"
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
                    placeholder="Drzava"
                      v-model="boat.address.country"
                  />
                </div>
              </div>
            </div>

    <div class="row" style="margin-bottom: 1%">
            <div class="col-md-9">
  <div class="map-style">

    <ol-map :loadTilesWhileAnimating="true" :loadTilesWhileInteracting="true" style="height:400px">

    <ol-view ref="view" :center="center" :rotation="rotation" :zoom="zoom" 
    :projection="projection" />

           <ol-tile-layer>
          <ol-source-osm />
          </ol-tile-layer>

           <ol-vector-layer>
        <ol-source-vector ref="vectors" >
           <ol-interaction-draw @drawstart="drawstart" :type="drawType" >
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
                      v-model="boat.promoDescription"
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
                      v-model="boat.rules"
                  ></textarea>
                </div>
              </div>
            </div>

    <!--Grid row-->
            <div class="row" style="margin-bottom: 1%">
              <!--Grid column-->
              <div class="col-md-2">
                <div class="md-form mb-0">
                  <label for="name" class="">Pecaroska oprema </label>
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
                      v-model="boat.fishingEquipment"
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
                <div class="form control" v-for="adS in boat.additionalServices" :key="adS">
                 <table> 
                   <tr style="width: 55rem"> <td >Naziv dodatne usluge:  </td><td style="width: 5rem"> </td> <td>Cena </td> </tr>
                   
                    <tr> <td>{{adS.name}} </td> <td> </td><td>{{adS.price}} din  </td> </tr>
                   </table> 
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
          </form>
        </div>
        <!--Button inspo   <div class="text-center text-md-left">
                <a class="btn btn-primary" onclick="document.getElementById('contact-form').submit();">Send</a>
            </div>
            <div class="status"></div>      -->

        <!--Grid column-->
        <div class="col-md-5">
          <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
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
                      v-model="boat.price"
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
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Broj motora:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="number" class="form-control"   v-model="boat.motorNumber"/>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Snaga:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="number" class="form-control" v-model="boat.power"/>
                </div>
              </div>
            </div>
          </div>

                <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Kapacitet ljudi:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="number" class="form-control" v-model="boat.quantity"/>
                </div>
              </div>
            </div>
          </div>
           <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Duzina:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-2">
                <div class="input-group">
                  <input type="number" class="form-control" v-model="boat.lenght"/>
                </div>
              </div>
            </div>
          </div>

              
                   <!--Grid row-->
            <div class="row" >
              <!--Grid column-->
              <div class="col-md-4">
                <div class="md-form mb-0">
                  <label for="name" class="">Tip broda</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-4">
                <div class="md-form">
             <select class="form-select" aria-label="Default select example" v-model="boatTypeString" style="height:2rem; width: 13rem;margin-bottom: 0.8rem; background-color: rgb(241, 241, 241); ">
                    <option selected>Izaberite tip</option>
                    <option value="0">jahta</option>
                    <option value="1">camac</option>
                    <option value="2">brod</option>
                    </select>
                </div>
              </div>
            </div>


                       <!--Grid row-->
            <div class="row" >
              <!--Grid column-->
              <div class="col-md-4">
                <div class="md-form mb-0">
                  <label for="name" class="">Navigaciona oprema</label>
                </div>
              </div>
              <!--Grid column-->
              <div class="col-md-5">
                <div class="md-form ">
             <select class="form-select" v-model="equipment" style="height:2rem; width: 13rem; margin-bottom: 0.8rem; background-color: rgb(241, 241, 241); " aria-label="Default select example" >
                   
                    <option value="GPS">Gps</option>
                    <option value="radar">radar</option>
                    <option value="VHF radio">VHF radio</option>
                      <option value="fishfinder">fishfinder</option>
                    </select>
                </div>
              </div>
            </div>
     
        
         <div class="row">
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form mb-0">
                <label for="name" class="">Max brzina:</label>
              </div>
            </div>
            <!--Grid column-->
            <div class="col-md-4">
              <div class="md-form ">
                <div class="input-group">
                  <input type="number" class="form-control" v-model="boat.maxSpeed"/>
                </div>
              </div>
            </div>
          </div>

 <div class="row" style="margin-left: 19.5em; margin-top: 5em">
            <!--Grid column-->
            <div class="col-md-5">
              <div class="md-form mb-2">
                <div class="input-group">
                  <button class="btn btn-success" type="button" @click="createBoat()">Sacuvaj brod</button>
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
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import axios from 'axios';
//import $ from "jquery";
import Swal from "sweetalert2"
import markerIcon from "../../assets/logo.png"
import {ref} from 'vue'
export default {
  name: "NewBoat",
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
     this.boat.address.longitude = event.feature.values_.geometry.flatCoordinates[0]; //ISPISUJE
      this.boat.address.latitude= event.feature.values_.geometry.flatCoordinates[1];
      console.log(event)
         }

    return {
        boatTypeString: "",
        equipment: "",
          imagesFrontend: [],
      images: "", 
      adServ: {name:"", price: 0},
      boat: {id: 0, name: "", address: { street: "", number: 0, city: "", country: "", id: 0, longitude: 0, latitude: 0}, promoDescription:"", rules: "", grade: 1.0 , images: [], price: 0, additionalServices: [], boatType: 0, lenght: 0, motorNumber: 0, power: 0, maxSpeed: 0, navigationEquipment: "", fishingEquipment: "", quantity: 0, cancelationType: 0 },
   
        markerIcon,
        markers,
      drawType,
        vectors,
      drawstart,
   
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
    NavBarBoatOwner,
  },
  methods: {
     addService(){
      this.boat.additionalServices.push({ name: this.adServ.name, price: this.adServ.price});
      console.log(this.boat.additionalServices)
        },

      createBoat(){
              const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
      this.boat.boatType = parseInt(this.boatTypeString);
      console.log( this.boat.boatType )
    this.boat.navigationEquipment = this.equipment
    console.log(this.boat.navigationEquipment)
      console.log(this.images);
      axios.post(process.env.VUE_APP_BACKEND_URL+"/api/boats/newBoat", this.boat,  {headers}).then( response =>{
console.log(response);

                  return new Swal({
             title:"Uspesno",
             type: "success",
             text:'Brod je uspesno dodat!'
           })
      } ).then(this.$router.push({name: "MyBoats"}));
    
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
                    this.boat.images.push(img);
                }
                reader.readAsDataURL(file);
            }, 

  },

};

// $(document).ready(function () {
//   $("#input-b6").fileinput({
//     showUpload: true,
//     dropZoneEnabled: false,
//     maxFileCount: 7,
//     mainClass: "input-group-lg",
//   });
// });
</script>

<style scoped>
.containerInfo {
  margin-top: 1%;
  margin-left: 2%;
}

table {
  max-width: 50%;
  background-color: rgb(241, 241, 241);
}
  .images-preview img{
        width:20%;
        height: 15%;
    }
</style>
