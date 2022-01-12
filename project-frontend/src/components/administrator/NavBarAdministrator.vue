<template>
  <nav class="navbar navbar-expand-sm bg-light">
    <ul class="navbar-nav">
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/administrator">Registration requests</router-link>
        </button>
      </li>
            <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/deleteAccountRequests">Delete account requests</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="">Add administrator</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="">Entities</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="">Define percentage</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="">Income records</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="">Business report</router-link>
        </button>
      </li>
      <li class="nav-item">
        <button type="button" class="btn btn-success">
          <router-link to="/myProfile">Profile</router-link>
        </button>
      </li>
    </ul>
  </nav>
</template>
<style scoped>
button {
  background-color: #4caf50; /* Green */
}
</style>
