<template>
    <nav class="navbar navbar-expand-sm bg-light">
        <ul class="navbar-nav">
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="/fishingInstructor">Usluge</router-link></button></li>
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="/AvailablePeriod">Period dostupnosti</router-link></button></li>
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="">Istorija rezervacija</router-link></button></li>
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="">Kreiraj akciju</router-link></button></li>
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="">Popuni izvestaj</router-link></button></li>
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="">Kreiraj rezervaciju</router-link></button></li>
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="">Kalendar zauzetosti</router-link></button></li>
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="/fishingInstructorProfile">Azuriraj svoje podatke</router-link></button></li>
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="">Zahtev za brisanje</router-link></button></li>
            <li class="nav-item">
            <button type="button" class="btn btn-success"><router-link to="/ChangePasswordFishingInstructor">Promeni lozinku</router-link></button></li>
      
        </ul>
    </nav>
</template>
<style scoped>
    button {
        background-color: #4CAF50; /* Green */
        font-size: 14px;
        margin: 0px 5px;
    }
</style>
