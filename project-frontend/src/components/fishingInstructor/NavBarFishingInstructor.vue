<template>
  <nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/fishingInstructor">Usluge</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/AvailablePeriod"
            >Period dostupnosti</router-link
          ></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="">Istorija rezervacija</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"><router-link to="">Kreiraj akciju</router-link></a>
      </li>
      <li class="nav-item">
        <a class="nav-link"><router-link to="">Popuni izvestaj</router-link></a>
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="">Kreiraj rezervaciju</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="">Kalendar zauzetosti</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/fishingInstructorProfile"
            >Azuriraj svoje podatke</router-link
          ></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="">Zahtev za brisanje</router-link></a
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
