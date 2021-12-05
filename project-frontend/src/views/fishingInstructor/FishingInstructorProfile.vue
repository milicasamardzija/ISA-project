<template>
    <div>
    <NavBarLogOut/>
    <HeaderStartPage />
    <NavBarFishingInstructor />
    <InstructorProfile/>
    </div>
</template>

<script>
import HeaderStartPage from '../../components/startPage/HeaderStartPage'
import NavBarFishingInstructor from '../../components/fishingInstructor/NavBarFishingInstructor.vue'
import NavBarLogOut from '../../components/fishingInstructor/NavbarLogOut.vue'
import InstructorProfile from '../../components/fishingInstructor/InstructorProfile.vue'

export default {
  name: "FishingInstructorProfile",
  components: {
      HeaderStartPage,
      NavBarFishingInstructor,
      NavBarLogOut,
      InstructorProfile
  },
};

</script>
