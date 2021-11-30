<template>
    <div style="">
    <div>
    <HeaderLogAndRegister/>
    <HeaderStartPage />
    <NavBarStartPage />
    <AdventureSearch />
    </div>
    <div class="containerInfo" >
        <div class=" tab-pane container active b">
            <div class="row-boats">
                 <div class = "col-with-picture">
                    <div><img src="../../assets/fishingStart.jpg"  style="height:250px !important; width:300px !important"></div>
                </div>
                <div class="col-info">
                    <h4 style="width: 600px;" class="text">Promotivni opis: </h4>
                    <h4 style="width: 600px;" class="text">Naziv:  </h4>
                    <h4 style="width: 600px;" class="text">Adresa:</h4>
                    <h4 style="width: 600px;" class="text">Prosecna ocena:</h4>
                    <button  type="button" class="btn btn-success">Detalji</button>
                </div>
            </div>
        </div>
    </div>
    </div>
</template>



<script>
import HeaderStartPage from '../../components/startPage/HeaderStartPage.vue'
import NavBarStartPage from '../../components/startPage/NavBarStartPage.vue'
import HeaderLogAndRegister from '../../components/startPage/HeaderLogAndRegister.vue'
import AdventureSearch from '../../components/client/cottages,boats,adventures/AdventureSearch.vue'

export default {
  name: "AdventuresStartPage",
  components: {
      HeaderStartPage,
      NavBarStartPage,
      HeaderLogAndRegister,
      AdventureSearch
  },
};

</script>

<style scoped>
    .row-boats {
    display: flex;
    }  

    .text {
    text-align: left;
    font-size: 15px;
    font-weight: 600;
    margin-top: 1%;
    margin-bottom: 1%;
    }

    .col-info {
        margin-left: 30px;
        margin-top: 10px;
    }

    .row {
    width: 660%;
    padding-left: 60px;
    height: 10%;
    margin-top: 100px;
    margin-left: 5000px;
    margin-right: 500px;
    opacity: 1.2;
    border-radius: 10px;
    align-content: center;
    background-color: rgba(255, 255, 255, 0.288);
    box-shadow: 0 5px 4px rgb(0 0 0 / 30%), 0 1px 1px rgb(0 0 0 / 22%);
    }
    /* kartica u okviru stavke menija  */
    .containerInfo {
    width: 97%;
    margin-top: 20px;
    margin-left: 20px;
    margin-right: 10px;
    opacity: 1;
    border-radius: 10px;
    align-content: center;
    position: relative;
    background-color: #FDFBEC;
    box-shadow: 0 19px 40px rgb(0 0 0 / 30%), 0 15px 12px rgb(0 0 0 / 22%);
    }
    .col-with-picture{
    margin-top: 1%;
    margin-bottom: 1%;
    }
    .b{
    background-color: #FDFBEC;
    }
</style>
