<template>
  <div>
    <HeaderLogAndRegister/>
    <HeaderStartPage/>
  </div>
  <router-view></router-view>
</template>

<script>
import HeaderStartPage from "./components/HeaderStartPage.vue";
import HeaderLogAndRegister from "./components/HeaderLogAndRegister.vue";
export default {
  name: "App",
  components: {
    HeaderStartPage,
    HeaderLogAndRegister

  };
}
</script>

<style>

</style>

