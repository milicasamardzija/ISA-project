<template>
    <div>
    <NavBarLogOut/>
    <HeaderStartPage />
    <NavBarClient />
    <AdventureDetails />
    </div>
</template>

<script>
import HeaderStartPage from '../../components/startPage/HeaderStartPage'
import NavBarLogOut from '../../components/client/NavBarLogOut.vue'
import AdventureDetails from '../../components/adventure/AdventureDetails.vue'
import NavBarClient from '../../components/client/NavBarClient.vue'

export default {
  name: "MyService",
  components: {
      HeaderStartPage,
      NavBarLogOut,
      AdventureDetails,
      NavBarClient
  },
};
</script>
