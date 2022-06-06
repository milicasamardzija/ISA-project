<template>
  <div class="card bg-dark text-white" style="margin: 5%">
    <div class="content">
      <FullCalendar :options="calendarOptions" class="calendar" />

      <div class="info">
        <h2>Availability</h2>
        <div style="margin: 2rem 0">
          <div class="element">
          </div>
        </div>
      </div>


    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import "@fullcalendar/core/vdom"; // solves problem with Vite
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import interactionPlugin from "@fullcalendar/interaction";
import axios from "axios";
axios.defaults.baseURL = process.env.VUE_APP_URL;

export default {
  components: {
    FullCalendar, // make the <FullCalendar> tag available
  },
  setup() {
    const startDate = ref();
    const endDate = ref();

    return {
      startDate,
      endDate,
    };
  },
  data() {
    return {
        ret:"",
      disabledPickers: false,
      selectDisabled: false,
      currentEvent: "",
      selectData: [],
      entityType: "",
      calendarOptions: {
        plugins: [interactionPlugin, dayGridPlugin, timeGridPlugin],
        initialDate: new Date(),
        headerToolbar: {
          left: "prev,next today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay",
        },
        selectable: true,
        eventClick: this.event,
        selectMirror: true,
        dayMaxEvents: true,
        initialView: "dayGridMonth",
        vents: [
        ],
      },
    };
  },
  methods: {
      async getReservations() {
      console.log("caoo");
           const headers = {
        Authorization: "Bearer " + localStorage.getItem("token"),
      };
              axios.get( "http://localhost:8081/api/reservations/myRegularReservation",
         { headers })  
                       .then((res) => {
                                   console.log(res.data)
                for (let newData of res.data) {
                      newData.title = "Availability";
//
                      this.calendarOptions.events.push(newData);
                    }
              });
      }
  },
  async created() {
      this.ret = await this.getReservations();
  }
  
  
  
  }
</script>













<style scoped>
.element {
  margin-bottom: 2rem;
}
.save {
  background-color: white;
}
.save:hover {
  background-color: #2c3e50 !important;
  color: white !important;
  border: 1px solid white !important;
}

.delete-btn {
  background-color: #2f1a1a !important;
}

.delete-btn:hover {
  background-color: #502c2c !important;
  color: white !important;
  border: 1px solid white !important;
}
.content {
  display: flex;
  justify-content: space-between;
}
.calendar {
  width: 70%;
}

.info {
  background: #212529;
  width: 28%;
  border-radius: 5px;
  border: 1px solid white;
  padding: 1rem;
}

select {
  background: transparent;
  color: white;
  border: 0;
  border-bottom: 1px solid white;
  width: 100%;
}

option {
  background: #27332b;
}

.datePricker {
  margin-top: 2%;
  border: 1px solid white;
  border-radius: 5px;
  width: 100%;
  box-shadow: none !important;
}
</style>
