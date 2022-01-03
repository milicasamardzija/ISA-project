<template>
  <div style="color: whitesmoke">
    <ul class="nav nav-tabs navbar-light bg-light">
      <li class="nav-item">
        <a class="nav-link" href="#">Pocetna </a>
      </li>
      <li class="nav-item">
        <a
          class="nav-link"
          data-toggle="dropdown"
          href="#"
          role="button"
          aria-haspopup="true"
          aria-expanded="false"
          ><router-link to="/myProfile">Profil</router-link></a
        >
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/myCottages">Moje vikendice</router-link>
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          <router-link to="/statistic">Statistika poslovanja</router-link></a
        >
      </li>
      <li class="nav-item dropdown">
        <a
          class="nav-link dropdown-toggle"
          data-toggle="dropdown"
          href="#"
          role="button"
          aria-haspopup="true"
          aria-expanded="false"
          >Rezervacije</a
        >
        <div class="dropdown-menu">
          <a class="dropdown-item" href="#">Prethodne</a>
          <a class="dropdown-item" href="#">Predstojece</a>
          <a class="dropdown-item" href="#">Trenutne</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Sve rezervacije</a>
        </div>
      </li>
    </ul>
  </div>
</template>
<style>
a {
  font-size: 1.1em;
  color: #0b4025;
}
</style>
