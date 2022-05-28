<template>

  <div v-if="this.role ==='ROLE_BOAT_OWNER'">
    <NavBarLogOut />
    <NavBarBoatOwner />
  </div>

  <div v-if="this.role ==='ROLE_COTTAGE_OWNER'">
   <NavBarLogOut />
    <NavBarHomePage />

  </div>
</template>

<script>
import NavBarLogOut from "../../components/cottageOwner/NavBarLogOut.vue";
import NavBarBoatOwner from "../../components/boatOwner/NavBarBoatOwner.vue";
import NavBarHomePage from "../../components/cottageOwner/NavBarHomePage.vue";

export default {
  name: "Statistic",
  components: {
    NavBarLogOut,
    NavBarHomePage,
     NavBarBoatOwner,
  },
  data(){
    return {
      role: "",
    }

  },

  methods:{

   
  },   async created() {
    this.role = localStorage.getItem("role");
  
 
 
  },
};
</script>

<style scoped></style>
