<template>
    <div>
    <NavBarLogOut/>
    <HeaderStartPage />
    <NavBarClient />
    </div>
    <div class="card">
        <div class="container">
        <h2><i>You're incoming reservations:</i></h2>    
        `<table class="table table-light table-striped table-hover">
            <thead>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
                <th>Date</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>John</td>
                <td>Doe</td>
                <td>john@example.com</td>
                <td>20.01.2022.</td>
                <td><button class="btn btn-success" >Details</button></td>
                <td><button class="btn btn-danger" >Cancel</button></td>
            </tr>
            <tr>
                <td>Mary</td>
                <td>Moe</td>
                <td>mary@example.com</td>
                <td>20.01.2022.</td>
                <td><button class="btn btn-success" >Details</button></td>
                <td><button class="btn btn-danger" >Cancel</button></td>
            </tr>
            <tr>
                <td>July</td>
                <td>Dooley</td>
                <td>july@example.com</td>
                <td>20.01.2022.</td>
                <td><button class="btn btn-success" >Details</button></td>
                <td><button class="btn btn-danger" >Cancel</button></td>
            </tr>
            </tbody>
        </table>
        </div>
    </div>
</template>

<script>
import NavBarLogOut from '../../../components/client/NavBarLogOut.vue'
import NavBarClient from '../../../components/client/NavBarClient.vue'
import HeaderStartPage from '../../../components/startPage/HeaderStartPage.vue'

export default {
  name: "ScheduledReservation",
  components: {
      HeaderStartPage,
      NavBarClient,
      NavBarLogOut
  },
};

</script>

<style scoped>
    .containerInfo {
    width: 97%;
    margin-top: 20px;
    margin-left: 20px;
    margin-right: 10px;
    opacity: 1;
    border-radius: 10px;
    align-content: center;
    position: relative;
    background-color: fff;
    box-shadow: 0 19px 40px rgb(0 0 0 / 30%), 0 15px 12px rgb(0 0 0 / 22%);
    }
    .container{
        margin-top: 3%;
    }
    .row {
    width: 660%;
    padding-left: 60px;
    height: 10%;
    margin-top: 100px;
    margin-left: 5000px;
    margin-right: 500px;
    opacity: 1.2;
    border-radius: 10px;
    align-content: center;
    background-color: rgba(255, 255, 255, 0.288);
    box-shadow: 0 5px 4px rgb(0 0 0 / 30%), 0 1px 1px rgb(0 0 0 / 22%);
    }
</style>