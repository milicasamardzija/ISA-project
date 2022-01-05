<template>
  <div>
    <NavBarLogOut />
    <NavBarHomePage />
  </div>
  <div>
    <div class="tab-pane active containerInfo">
      <div class="row"> 
        <div class="column" style="width: 24rem; height: 3rem">   <input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                  /></div>
        <div class="column" style="width: 30rem; height: 3rem"><input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                  /> </div>
        <div class="column" style="width: 18rem;  height: 3rem"> <button class="btn btn-success"> Sacuvaj izmene</button></div>

      
     
      </div>
    
       <div class="row rowPic" style="width: 90rem"> 
        
          
         <div class="column columnPic " > 
               
                    <!-- 1. red malih slika-->
                  <div class="column columnPic " style="width: 100rem; height:14em">
                        <div class="images">
                            <figure>
                                <img src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg" alt="Photo" class="smallImages"/>
                                <input id="input-b6" name="input-b6[]" type="file" accept="image/jpg, image/png " multiple>
                            </figure>
                          </div> 
                        <div class="images">
                          <figure>
                              <img src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg" alt="Photo" class="smallImages" />
                            
                          </figure>
                        </div>
                          <div class="images">
                          <figure>
                              <img src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg" alt="Photo" class="smallImages" />
                              
                          </figure>
                        </div>
                           <div class="images">
                            <figure>
                                <img src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg" alt="Photo" class="smallImages"/>
                                <input id="input-b6" name="input-b6[]" type="file" accept="image/jpg, image/png " multiple>
                            </figure>
                          </div>
                             <div class="images">
                            <figure>
                                <img src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg" alt="Photo" class="smallImages"/>
                                <input id="input-b6" name="input-b6[]" type="file" accept="image/jpg, image/png " multiple>
                            </figure>
                          </div>
                             <div class="images">
                            <figure>
                                <img src="../../../src/assets/cottageImages/galerija-vikendica-pustolov-uvac-20.jpg" alt="Photo" class="smallImages"/>
                                <input id="input-b6" name="input-b6[]" type="file" accept="image/jpg, image/png " multiple>
                            </figure>
                          </div>
                      <div class="clearfix"></div>
                  </div>
                
     

                
          

             
          </div>
         
     
      </div>


     
     <div class=" row aboutCottage"> 
       <div class="column columnAbout "><h4>Opis smestaja </h4>
    <textarea
                    type="text"
                    id="message"
                    name="message"
                    rows="10"
                    class="form-control md-textarea"
                  ></textarea>  </div>
     
     <div class="column" style="padding-left: 5rem; width: 50em"><h4>Informacije </h4> 
     <div class="row" style=" height: 20rem;  background: whitesmoke">
       <div class="column" style="width: 18rem"> 
           <table>
               <tr> <td><p> Cena: </p> </td> <td> <input  type="text"/> </td> </tr>
                    <tr> <td>  <p > Ocena: </p> </td> <td> <input  type="text"/> </td> </tr>
                         <tr> <td> <p> Maksimalan broj osoba: </p>  </td> <td> <input  type="text"/> </td> </tr>
                              <tr> <td> <p> Broj soba: </p> </td> <td> <input  type="text"/> </td> </tr>
                                   <tr> <td> <p> Broj kreveta po sobi: </p> </td> <td> <input  type="text"/> </td> </tr>
                                        <tr> <td><p> Pravila ponasanja: </p>  </td> <td> <input  type="text"/> </td> </tr>
                </table> 

       </div>

       <div class="column" style="width: 22rem; height: 18rem; background: white;margin-left: 3rem; "> 
           
          <p class="pStyle" > dodatne usluge: </p>
          <table>
               <tr> <td><p> WIFI: </p> </td> <td> <input  class="smallInput" type="text"/> </td> <td> <button> ukloni </button></td> </tr>
                    <tr> <td>  <p > PARKING: </p> </td> <td> <input  class="smallInput" type="text"/> </td> <td> <button> ukloni </button></td> </tr>
                   <tr> <td>   <input  type="text" style="width: 150px" /> </td> <td>   <input class="smallInput"  type="text" /> </td> <td>  <button class="btn btn-outline-success"> dodaj </button></td></tr>      
          </table> 
      
         
       </div>
      </div>
     </div></div>
    </div>
  </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";

export default {
  name: "EditCottage",
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
.mainImage{
  height: 30em;
  width: 45em;
}
.aboutCottage{
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
  height: 16em;
  
}
.columnAbout {
  float: left;
  width: 50em;
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
.backgroundInfo{


  background: whitesmoke;
}
.pStyle{
  font-size: 18px;
  font-weight: bold;
  font-style: italic;
}
.smallImages{
  height: 15em;
  width: 18em;
}
.images {
    float:left;
    border-style:solid;
    border-width:2px;
    margin:3px;
    height:18em;
    width:16em;
    overflow:hidden;
    text-align:center;
}

.clearfix {
    clear: both;
}

.smallInput{
    width: 100px;
}


</style>
