<template>
  <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarFishingInstructor />
  <form role="form">
    <div class="form-group">
      <label for="psw"
        ><span class="glyphicon glyphicon-eye-open"></span> Slobodan od:</label
      >
      <input type="date" class="form-control" id="psw" placeholder="Datum" />
      <input type="time" class="form-control" id="psw" placeholder="Vreme" />
    </div>
    <div class="form-group">
      <label for="psw"
        ><span class="glyphicon glyphicon-eye-open"></span> Slobodan do:</label
      >
      <input type="text" class="form-control" id="psw" placeholder="Datum" />
      <input type="text" class="form-control" id="psw" placeholder="Vreme" />
    </div>
    <button type="submit" class="btn btn-success"><span></span>Potvrdi</button>
  </form>  </div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarFishingInstructor from "../../components/fishingInstructor/NavBarFishingInstructor.vue";
import NavBarLogOut from "../../components/fishingInstructor/NavbarLogOut.vue";

export default {
  name: "AvailablePeriod",
  components: {
    HeaderStartPage,
    NavBarFishingInstructor,
    NavBarLogOut,
  },
};
</script>
