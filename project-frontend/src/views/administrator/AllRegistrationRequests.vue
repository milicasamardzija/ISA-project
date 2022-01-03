<template>
  <div>
    <NavBarLogOut />
    <HeaderStartPage />
    <NavBarAdministrator />
  </div>
</template>

<script>
import HeaderStartPage from "../../components/startPage/HeaderStartPage";
import NavBarAdministrator from "../../components/administrator/NavBarAdministrator";
import NavBarLogOut from "../../components/administrator/NavBarLogOut";

export default {
  name: "AllRegistrationRequests",
  components: {
    HeaderStartPage,
    NavBarAdministrator,
    NavBarLogOut,
  },
};
</script>
