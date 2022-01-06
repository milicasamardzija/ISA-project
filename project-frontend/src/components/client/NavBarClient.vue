<template>
  <nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
      <li class="nav-item">
        <router-link to="/client">Cottages</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/clientBoats">Boats</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/clientAdventures">Adventures</router-link>
      </li>
      <li class="nav-item">
        <a class="nav-link"
          ><router-link to="/clientReservationHistory"
            >Reservation history</router-link
          ></a
        >
      </li>
      <li class="nav-item">
        <router-link to="/clientScheduledReservations"
          >Scheduled reservations</router-link
        >
      </li>
      <li class="nav-item">
        <router-link to="clientSubscription">Pretplate</router-link>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Profile</a>
      </li>
    </ul>
  </nav>
</template>
