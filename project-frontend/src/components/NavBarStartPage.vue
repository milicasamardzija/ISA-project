<template>
    <nav class="navbar navbar-expand-sm bg-light navbar-dark">
    <ul class="navbar-nav">
      <li class="nav-item" style="position: center;">
          <button class="m-1 btn btn-light btn-outline-primary" Button><router-link to="/"> Boats </router-link></button>
      </li>
      <li class="nav-item">
          <button class="m-1 btn btn-light btn-outline-primary"  Button><router-link to="/cottages">Cottages</router-link></button>
      </li>
      <li class="nav-item">
          <button class="m-1 btn btn-light btn-outline-primary" Button><router-link to="/adventures">Adventures</router-link></button>
      </li>
    </ul>
  </nav>
</template>

<script></script>

<style scoped>
 .btn{
    background-color: #51D491;
  }
  ul{
    margin-left: 40%;
  }
  .nav-item{
    margin-right: 5%;
    width: 30%;
  }

</style>
