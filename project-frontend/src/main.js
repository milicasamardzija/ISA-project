import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";


import "jquery/src/jquery.js";

import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';


import OpenLayersMap from 'vue3-openlayers'
import 'vue3-openlayers/dist/vue3-openlayers.css'

const app = createApp(App);

app.use(VueSweetalert2);
window.Swal =  app.config.globalProperties.$swal;
createApp(App).use(router).use(OpenLayersMap).mount("#app");

