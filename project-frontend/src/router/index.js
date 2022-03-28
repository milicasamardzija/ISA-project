import { createRouter, createWebHistory } from "vue-router";

import BoatsStartPage from "../views/startPage/BoatsStartPage";
import CottagesStartPage from "../views/startPage/CottagesStartPage";
import AdventuresStartPage from "../views/startPage/AdventuresStartPage";
import ClientAllCottages from "../views/client/entities/ClientAllCottages";
import ClientAllBoats from "../views/client/entities/ClientAllBoats";
import ClientAllAdventures from "../views/client/entities/ClientAllAdventures";
import ScheduledReservation from "../views/client/reservations/ScheduledReservation";
import ReservationHistory from "../views/client/reservations/ReservationHistory";
import HomePageInProfil from "../views/cottageOwner/HomePageInProfil";
import MyCottages from "../views/cottageOwner/MyCottages";
import MyProfile from "../views/cottageOwner/MyProfile";
import Statistic from "../views/cottageOwner/Statistic";
import EditProfile from "../views/cottageOwner/EditProfile";
import NewCottage from "../views/cottageOwner/NewCottage";
import EditCottage from "../views/cottageOwner/EditCottage";
import AllRegistrationRequests from "../views/administrator/AllRegistrationRequests";
import MyService from "../views/fishingInstructor/MyService";
import AdventurePage from "../views/adventure/AdventurePage";
import FishingInstructorProfile from "../views/fishingInstructor/FishingInstructorProfile";
import ChangePasswordFishingInstructor from "../views/fishingInstructor/ChangePasswordFishingInstructor";
import AvailablePeriod from "../views/fishingInstructor/AvailablePeriod";
import CottageProfile from "../views/cottageOwner/CottageProfile";
import ClientSubscription from "../views/client/subscription/ClientSubscription"
import AdminProfile from "../views/administrator/AdminProfile"
import DeleteAccountRequests from "../views/administrator/DeleteAccountRequests"
import Percentage from "../views/administrator/Percentage"
import IncomeRecords from "../views/administrator/IncomeRecords"
import SeeAndDeleteEntities from "../views/administrator/SeeAndDeleteEntities"
import AddAdministrator from "../views/administrator/AddAdministrator"
import ChangePassword from "../views/administrator/ChangePassword"

const routes = [
  {
    path: "/",
    name: "BoatsStartPage",
    component: BoatsStartPage,
  },
  {
    path: "/percentage",
    name: "Percentage",
    component: Percentage,
  },

  {
    path: "/incomeRecords",
    name: "IncomeRecords",
    component: IncomeRecords,
  },
  {
    path: "/deleteAccountRequests",
    name: "DeleteAccountRequests",
    component: DeleteAccountRequests,
  },
  {
    path: "/cottages",
    name: "CottageStartPage",
    component: CottagesStartPage,
  },
  {
    path: "/adventures",
    name: "AdventuresStartPage",
    component: AdventuresStartPage,
  },
  {
    path: "/client",
    name: "ClientAllCottages",
    component: ClientAllCottages,
  },
  {
    path: "/addAdministrator",
    name: "AddAdministrator",
    component: AddAdministrator,
  },
  {
    path: "/adventurePage",
    name: "AdventurePage",
    component: AdventurePage,
  },
  {
    path: "/administrator",
    name: "AllRegistrationRequests",
    component: AllRegistrationRequests,
  },
  {
    path: "/fishingInstructor",
    name: "MyService",
    component: MyService,
  },
  {
    path: "/ChangePasswordFishingInstructor",
    name: "ChangePasswordFishingInstructor",
    component: ChangePasswordFishingInstructor,
  },
  {
    path: "/AvailablePeriod",
    name: "AvailablePeriod",
    component: AvailablePeriod,
  },
  {
    path: "/seeAndDeleteEntities",
    name: "SeeAndDeleteEntities",
    component: SeeAndDeleteEntities,
  },
  {
    path: "/fishingInstructorProfile",
    name: "FishingInstructorProfile",
    component: FishingInstructorProfile,
  },
  {
    path: "/clientBoats",
    name: "ClientAllBoats",
    component: ClientAllBoats,
  },
  {
    path: "/clientAdventures",
    name: "ClientAllAdventures",
    component: ClientAllAdventures,
  },
  {
    path: "/clientScheduledReservations",
    name: "ScheduledReservation",
    component: ScheduledReservation,
  },
  {
    path: "/clientReservationHistory",
    name: "ReservationHistory",
    component: ReservationHistory,
  },
  {
    path: "/cottageOwnerHomePage",
    name: "HomePageInProfil",
    component: HomePageInProfil,
  },
  {
    path: "/myCottages",
    name: "MyCottages",
    component: MyCottages,
  },
  {
    path: "/changePassword",
    name: "ChangePassword",
    component: ChangePassword,
  },
  {
    path: "/myProfile",
    name: "MyProfile",
    component: MyProfile,
  },
  {
    path: "/adminProfile",
    name: "AdminProfile",
    component: AdminProfile,
  },
  {
    path: "/editProfile",
    name: "EditProfile",
    component: EditProfile,
  },
  {
    path: "/statistic",
    name: "Statistic",
    component: Statistic,
  },
  {
    path: "/newCottage",
    name: "NewCottage",
    component: NewCottage,
  },
  {
    path: "/cottageProfile",
    name: "CottageProfile",
    component: CottageProfile,
  },
  {
    path: "/editCottage",
    name: "EditCottage",
    component: EditCottage,
  },
  {
    path: "/ClientSubscription",
    name: "ClientSubscription",
    component: ClientSubscription,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
