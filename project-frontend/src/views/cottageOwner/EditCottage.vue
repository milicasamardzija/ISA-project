<template> 
<div v-if="this.role==='ROLE_COTTAGE_OWNER'"> 
  <div >
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div>
    <div class="tab-pane active containerInfo">

      <div  class="row"> 
        <div class="col" > 
      <div class="row">
        
          <div class="col-md-1">
                <div class="md-form mb-0">
                  <label for="name" style=" margin-top: 0.5rem; margin-left: 2rem">Naziv </label>
                </div>
           </div> 
          
            <div class="col-md-3">
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

              <div class="col-md-6"> <button type="button" style=" margin-left: 90%; " class="btn btn-success" @click="saveCottage()">
       Sacuvaj izmene
          </button>  </div>
<!--mapa -->
             <div class="col-md-7">
             <div style="width: 50%; height: 20%; margin-left: 2rem; margin-top: 1rem;">
  <ol-map :loadTilesWhileAnimating="true" :loadTilesWhileInteracting="true" style="height:400px">
    <ol-view ref="view" :center="center" :rotation="rotation" :zoom="zoom" 
    :projection="projection" />

           <ol-tile-layer>
          <ol-source-osm />
          </ol-tile-layer>
           <ol-vector-layer>
        <ol-source-vector>
            <ol-feature>
                <ol-geom-multi-point :coordinates="[[this.cottage.address.longitude,this.cottage.address.latitude]]"></ol-geom-multi-point>
                <ol-style>
                    <ol-style-circle :radius="radius">
                        <ol-style-fill :color="fillColor"></ol-style-fill>
                        <ol-style-stroke :color="strokeColor" :width="strokeWidth"></ol-style-stroke>
                    </ol-style-circle>
                </ol-style>
            </ol-feature>

        </ol-source-vector>
    </ol-vector-layer>
<!-- izbor pozicije novi -->
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

           <div class="col"> 
          <div style="margin-right: 40%; margin-left: -20rem;">
          <h4>Opis smestaja</h4>
          <textarea
            type="text"
            id="message"
            name="message"
            rows="10"
            class="form-control md-textarea"
            v-model="cottage.promoDescription"
          ></textarea>
            <h4  style="margin-top: 1rem;">Pravila ponasanja</h4>
          <textarea
            type="text"
            id="message"
            name="message"
            rows="5"
            class="form-control md-textarea"
            v-model="cottage.rules"
          ></textarea>
        </div>
          </div>
 </div>

<div class="row" style=" margin-top: 1rem;">
              <div class="col-md-1" style=" margin-top: -1rem">
                <div class="md-form mb-0">
                  <label for="name" style="margin-left: 2rem; ">Adresa </label>
                </div>
           </div> 
           <div class="col-md-1">
                <div class="md-form mb-0">
                 <input type="text" id="name" name="name"  style="margin-top: -1rem" class="form-control" v-model="cottage.address.street"/>
                </div>
              </div>   
               <div class="col-md-1">
                <div class="md-form mb-0">
                 <input type="number" id="name" name="name" class="form-control" style="margin-top: -1rem" v-model="cottage.address.number"/>
                </div>
              </div> 
                <div class="col-md-1">
                <div class="md-form mb-0">
                 <input type="text" id="name" name="name" class="form-control" style="margin-top: -1rem" v-model="cottage.address.city"/>
                </div>
              </div>       
        </div>
  
    <div class="row" style="height:1rem "> </div>
    <div class="row">
         <div class="col-md-1">
                <div class="md-form mb-0">
                  <label for="name" style="margin-right: 3rem; margin-left: 2rem; margin-top: 1rem">Koordinate </label>
                </div>
           </div> 

 <div class="col-md-1">
                <div class="md-form mb-0">
                 <input type="text" id="name" name="name" class="form-control" v-model="cottage.address.longitude"/>
                </div>
              </div>

               <div class="col-md-1">
                <div class="md-form mb-0">
                 <input type="text" id="name" name="name" class="form-control" v-model="cottage.address.latitude"/>
                </div>
              </div>

              <div class="col-md-1">
                <div class="md-form mb-0">
                 <input type="text" id="name" name="name" class="form-control" v-model="cottage.address.country"/>
                </div>
              </div>

       </div>
       </div>

       
    </div>



      <div class="row aboutCottage">

        <div class="column" style="padding-left: 5rem; width: 60em">
          <h4>Informacije</h4>
          <div class="row" style="height: 20rem; background: whitesmoke">
            <div class="column" style="width: 23rem">
              <table>
                <tr>
                  <td style="width: 10rem"><p>Cena:</p></td>
                  <td><input type="text"      class="form-control" v-model="cottage.price"/> </td>
                     <td> <div class="input-group-append">
                    <span class="input-group-text">RSD</span>
                  </div></td>
                </tr>    
                <tr>
                  <td><p>Broj soba:</p></td>
                  <td><input type="number"     class="form-control"  v-model="cottage.roomsNumber" /></td>
                  <td></td>
                </tr>
                <tr>
                  <td><p>Broj kreveta po sobi:</p></td>
                  <td><input type="number"     class="form-control"  v-model="cottage.bedsByRoom" /></td>
                </tr>
              </table>
            </div>

            <div
              class="column"
              style="
                width: 25rem;
                height: 18rem;
                background: white;
                margin-left: 3rem;
              "
            >
              <p class="pStyle">dodatne usluge:</p>
                 <div class="row" style="margin-bottom: 1%; width: 40rem" >
              <!--Grid column-->
              <div class="col-md-3">
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
            <div class="col-md-3">
              <div class="md-form mb-5">
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
             
            </div>


        

                 <div class="col-md-10" >
                  <p v-if="this.cottage.additionalServices.length  != 0"> Trenutno definisane dodatne usluge </p>
                <div class="form control" v-for="(adS, index) in cottage.additionalServices" :key="index">
                 <table> 
                  
                    <tr style="width: 25rem;"> <td style="width: 8rem;">{{adS.name}} </td> <td> </td><td style="width: 5rem;">{{adS.price}} din  </td>  <td> <button type="button" class="btn btn-secondary" @click="remove(index)">x </button></td> </tr>
                   </table> 
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col">
              <div class="md-form mb-0">
                <h4 style=" margin-left"> Dodajte slike: </h4>
               <div class="upload-images" style="margin-top: 3rem;">
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
      
    </div> 
   
    </div>
  </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";
import axios from 'axios';
import {ref} from 'vue';
import markerIcon from "../../assets/logo.png"
export default {
  name: "EditCottage",
  components: {
    NavBarLogOut,
    NavBarHomePage,
  },
  data(){

     const center = ref([20,44 ])
        const projection = ref('EPSG:4326')
        const zoom = ref(8)
        const rotation = ref(0)
       

          const radius = ref(2)
        const strokeWidth = ref(10)
        const strokeColor = ref('red')
        const fillColor = ref('white')

          const markers = ref(null);
    const drawType = ref("Point")
const vectors = ref([]);
     
        const coordinate = ref([20,44 ])


           const drawstart = (event) => {  
     this.cottage.address.longitude = event.feature.values_.geometry.flatCoordinates[0]; //ISPISUJE
      this.cottage.address.latitude= event.feature.values_.geometry.flatCoordinates[1];
      console.log(event)
         }

    return{
       imagesFrontend: [],
      cottage: "",
      id: 0,
       adServ: {name:"", price: 0},
       
       //mapa
      center,
            projection,
            zoom,
            rotation,
            radius,
            strokeWidth,
            strokeColor,
            fillColor,

            //za pin
                markerIcon,
        markers,
      drawType,
        vectors,
         drawstart,
          coordinate,
    }


     
  },
  methods:{

    addService(){
      this.cottage.additionalServices.push({ name: this.adServ.name, price: this.adServ.price});
      console.log(this.cottage.additionalServices)
        },
   async getCottage(id) {
      const res = await fetch(process.env.VUE_APP_BACKEND_URL+"/api/cottages/cottage/" + id);
      const data = await res.json();  
       console.log(data) ;
       this.cottage= data;  //samo u created nije radilo
       this.capacity = this.cottage.bedsByRoom * this.cottage.roomsNumber;
      return data;
    },
      saveCottage(){
   
        const headers = {
       Authorization: "Bearer " + localStorage.getItem("token"),
         };

  
      axios.post(process.env.VUE_APP_BACKEND_URL+"/api/cottages/editCottage", this.cottage,  {headers}).then( response => response.json());
      this.$router.push({name: "MyCottages"});
      },

     remove(index){
      this.cottage.additionalServices.splice(index, 1);
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
  async created() {
this.role = localStorage.getItem("role");
    this.id = this.$route.params.id;
    this.cottage = this.getCottage(this.id);
  }
};
</script>

<style scoped>
.containerInfo {
  margin-left: 2%;
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
  height: 16em;
}
.columnPic {
  float: left;
  width: 80em;
  padding: 10px;
  /* height: 16em; */
}
.columnAbout {
  float: left;
  width: 35em;
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
  height: 18em;
  width: 16em;
  overflow: hidden;
  text-align: center;
}

.clearfix {
  clear: both;
}

.smallInput {
  width: 100px;
}
 .images-preview img{
        width:10%;
        height: 10%;
    }
</style>
