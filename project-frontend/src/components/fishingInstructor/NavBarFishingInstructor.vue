<template>
  <nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/fishingInstructor">Services</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/AvailablePeriod"
            >Availability period</router-link
          ></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/MyAdventureReservations">Booking history</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link" ><router-link to="/CreateAction">Create action</router-link></a>
      </li>
      <li class="nav-item">
        <a class="nav-link"><router-link to="">Fill in reports</router-link></a>
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="">Create a reservation</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="">Occupancy calendar</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/myProfile"
            >Azuriraj svoje podatke</router-link
          ></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/ChangePasswordFishingInstructor"
            >Promeni lozinku</router-link
          ></a
        >
      </li>
    </ul>
  </nav>
</template>
<style scoped>
button {
  background-color: #4caf50; /* Green */
  font-size: 14px;
  margin: 0px 5px;
}
</style>
