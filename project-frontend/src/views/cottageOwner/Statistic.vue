<template> 
<div> 
    <NavBarLogOut/>
    <NavBarHomePage/>
</div>    
 <div>
    
       
 </div>


</template>

<script>

import NavBarLogOut from '../../components/cottageOwner/NavBarLogOut.vue'
import NavBarHomePage from '../../components/cottageOwner/NavBarHomePage.vue'

export default{
name: "Statistic",
components:
{
  
    NavBarLogOut,
    NavBarHomePage

}
};
</script>

<style scoped>

</style>