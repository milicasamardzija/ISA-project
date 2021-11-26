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
            <a class="nav-link" href="#">Reservation history</a>
            </li>
            <li class="nav-item">
            <router-link to="/clientScheduledReservations">Scheduled reservations</router-link>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="#">Subsription</a>
            </li>
            <li class="nav-item">
            <a class="nav-link" href="#">Profile</a>
            </li>
        </ul>
    </nav>
</template>