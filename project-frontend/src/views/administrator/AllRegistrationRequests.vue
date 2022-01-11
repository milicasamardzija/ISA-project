<template>
  <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator />
    <RegistrationRequests/>
  </div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator";
import NavBarLogOut from "../../components/administrator/NavBarLogOut";
import RegistrationRequests from "../../components/administrator/RegistrationRequests"

export default {
  name: "AllRegistrationRequests",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
    RegistrationRequests
  },
};
</script>
