<template>
    <div>
    <NavBarLogOut/>
    <HeaderStartPage />
    <NavBarFishingInstructor />
    <AvailablePeriodComponent/>
    </div>
</template>

<script>
import HeaderStartPage from '../../components/startPage/HeaderStartPage'
import NavBarFishingInstructor from '../../components/fishingInstructor/NavBarFishingInstructor.vue'
import NavBarLogOut from '../../components/fishingInstructor/NavbarLogOut.vue'
import AvailablePeriodComponent from '../../components/fishingInstructor/AvailablePeriodComponent.vue'

export default {
  name: "AvailablePeriod",
  components: {
      HeaderStartPage,
      NavBarFishingInstructor,
      NavBarLogOut,
      AvailablePeriodComponent
  },
};

</script>