<template>
  <div>
    <HeaderLogAndRegister/>
    <HeaderStartPage/>
  </div>

</template>

<script>
import HeaderStartPage from "./components/HeaderStartPage.vue";
import HeaderLogAndRegister from "./components/HeaderLogAndRegister.vue";
export default {
  name: "App",
  components: {
    HeaderStartPage,
    HeaderLogAndRegister,
  },
};
</script>

<style></style>
